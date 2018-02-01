package com.lomoye.lion.web.controller;

import com.lomoye.common.dto.ResultList;
import com.lomoye.lion.core.domain.SportItem;
import com.lomoye.lion.core.domain.User;
import com.lomoye.lion.core.manager.SportItemManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lomoye on 2018/1/29.
 * 运动项目控制器
 */
@RestController
@Scope(WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("/api/sportItem")
public class SportItemController extends BaseController {
    @Autowired
    private SportItemManager sportItemManager;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    ResultList<SportItem> listSportItem(HttpServletRequest request) {
        User user = getSessionUser(request);
        List<SportItem> sportItemList = sportItemManager.listByUserId(user.getId());
        return new ResultList<>(sportItemList);
    }
}
