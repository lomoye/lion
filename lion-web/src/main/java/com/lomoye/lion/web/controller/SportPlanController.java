package com.lomoye.lion.web.controller;

import com.lomoye.common.dto.ResultData;
import com.lomoye.lion.core.domain.SportPlan;
import com.lomoye.lion.core.domain.User;
import com.lomoye.lion.core.manager.SportPlanManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lomoye on 2018/1/29.
 * 运动计划控制类
 */
@RestController
@Scope(WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("/api/sportPlan")
public class SportPlanController extends BaseController {

    @Autowired
    private SportPlanManager sportPlanManager;

    @RequestMapping(value = "", method = RequestMethod.POST)
    ResultData<SportPlan> addSportPlan(HttpServletRequest request, @RequestBody SportPlan sportPlan) {
        User user = getSessionUser(request);

        return new ResultData<>(sportPlan);
    }
}
