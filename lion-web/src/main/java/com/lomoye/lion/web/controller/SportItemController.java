package com.lomoye.lion.web.controller;

import com.lomoye.common.dto.ResultData;
import com.lomoye.lion.core.domain.SportItem;
import com.lomoye.lion.core.domain.User;
import com.lomoye.lion.core.validator.SportItemValidator;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lomoye on 2018/1/29.
 * 运动项目控制器
 */
@RestController
@Scope(WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("/api/sportItem")
public class SportItemController extends BaseController {

    @RequestMapping(value = "", method = RequestMethod.POST)
    ResultData<SportItem> addSportItem(HttpServletRequest request, @RequestBody SportItem sportItem) {
        User user = getSessionUser(request);
        SportItemValidator.ensureAddSportItemParam(user.getId(), sportItem);
        return new ResultData<>(sportItem);
    }
}
