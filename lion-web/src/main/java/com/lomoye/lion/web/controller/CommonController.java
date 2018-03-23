package com.lomoye.lion.web.controller;

import com.lomoye.common.dto.ResultData;
import com.lomoye.common.exception.BusinessException;
import com.lomoye.lion.core.constant.ErrorCode;
import com.lomoye.lion.core.domain.User;
import com.lomoye.lion.core.enums.UploadDir;
import com.lomoye.lion.core.util.UploadUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by lomoye on 2017/12/22.
 * 一些公用的接口
 */
@RestController
@Scope(WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("/api/common")
public class CommonController extends BaseController {


    /**
     * 上传图片获得图片链接
     */
    @RequestMapping(value = "/picture", method = RequestMethod.POST)
    ResultData<String> uploadPicture(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) {
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
        if (!fileName.endsWith(".png") && !fileName.endsWith(".PNG") && !fileName.endsWith(".jpg") && !fileName.endsWith(".JPG")
                && !fileName.endsWith(".jpeg") && !fileName.endsWith(".JPEG")) {
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
            String picUrl = UploadUtil.upload(UploadDir.lion, name, file.getInputStream());
            return new ResultData<>(picUrl);
        } catch (IOException e) {
            LOGGER.warn("uploadPicture error.|userId={}", user.getId(), e);
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "上传图片失败");
        }
    }
}
