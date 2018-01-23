package com.lomoye.lion.web.controller;

import com.google.common.base.Strings;
import com.google.common.io.Files;
import com.lomoye.common.dto.ResultData;
import com.lomoye.common.exception.BusinessException;

import com.lomoye.lion.core.constant.ErrorCode;
import com.lomoye.lion.core.constant.SessionConstant;
import com.lomoye.lion.core.domain.User;


import com.lomoye.lion.core.manager.UserManager;


import com.lomoye.lion.core.util.MobileCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


/**
 * Created by lomoye on 2017/6/12.
 * 测试用
 */
@Controller
@Scope(WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("/api/user")
public class UserController extends BaseController {
    @Autowired
    private UserManager userManager;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    ResultData<User> getUser(HttpServletRequest request) {
        User user = getSessionUser(request);
        user.setPassword(null);
        return new ResultData<>(user);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    ResultData<Void> registerUser(HttpServletRequest request, @RequestBody User user) {
        if (user == null) {
            LOGGER.info("registerUser|no user");
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "用户不能为空");
        }
        if (Strings.isNullOrEmpty(user.getMobile())) {
            LOGGER.info("registerUser|no mobile");
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "手机号不能为空");
        }
        if (!MobileCheckUtil.isEffectivePhone(user.getMobile())) {
            LOGGER.info("registerUser|invalid mobile={}", user.getMobile());
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "手机号格式不正确");
        }
        if (Strings.isNullOrEmpty(user.getNick())) {
            LOGGER.info("registerUser|no nick");
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "昵称不能为空");
        }

        User selectUser = userManager.findByMobile(user.getMobile());
        if (selectUser != null) {
            LOGGER.warn("selectUser is exist|mobile={}", selectUser.getMobile());
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "该手机号已被注册");
        }

        userManager.save(user);
        return new ResultData<>();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    ResultData<User> userLogin(HttpServletRequest request, @RequestBody User user) {
        if (user == null) {
            LOGGER.info("userLogin|no user");
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "用户不能为空");
        }
        if (user.getMobile() == null) {
            LOGGER.info("userLogin|no mobile");
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "手机号不能为空");
        }

        if (Strings.isNullOrEmpty(user.getPassword())) {
            LOGGER.info("userLogin|no password");
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "密码不能为空");
        }

        User selectUser = userManager.findByMobileAndPassword(user.getMobile(), user.getPassword());
        if (selectUser == null) {
            LOGGER.info("userLogin|no selectUser");
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "用户名或者密码错误");
        }

        //将用户放入session中
        request.getSession().setAttribute(SessionConstant.USER, selectUser);

        return new ResultData<>(selectUser);
    }

    @RequestMapping(value = "/icon", method = RequestMethod.POST)
    @ResponseBody
    ResultData<Boolean> uploadIcon(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) {
        User user = getSessionUser(request);
        if (file == null) {
            LOGGER.warn("upload user icon is null|userId={}", user.getId());
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "图片不能为空");
        }
        if (file.getSize() >= 1024 * 1014 * 2) {
            LOGGER.warn("icon size >= 2M|userId={}", user.getId());
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "图片大小最大2M");
        }

        String fileName = file.getOriginalFilename();
        if (!fileName.endsWith(".png") && !fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg")) {
            LOGGER.warn("icon name invalid|{}", file.getOriginalFilename());
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "图片格式只能是jpg或者png");
        }
        try {
            BufferedImage iconImg = ImageIO.read(file.getInputStream());
            if (iconImg == null) {
                LOGGER.warn("iconImg is null|userId={}", user.getId());
                throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "WARNING:请确定你传入的是否是图片格式！");
            }

            String name = UUID.randomUUID().toString().replace("-", "");
            File targetFile = new File("/image/" + name);
            Files.write(file.getBytes(), targetFile);

            User selectUser = userManager.getById(user.getId());
            selectUser.setIcon("http://47.93.253.116/image/" + name);
            userManager.update(selectUser);
        } catch (IOException e) {
            LOGGER.warn("upload user icon io error.|userId={}", user.getId(), e);
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "上传图片失败");
        }


        return new ResultData<>(true);
    }
}
