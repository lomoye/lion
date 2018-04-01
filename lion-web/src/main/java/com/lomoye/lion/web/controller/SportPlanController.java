package com.lomoye.lion.web.controller;

import com.lomoye.common.dto.ResultData;
import com.lomoye.common.dto.ResultList;
import com.lomoye.common.util.SplitterUtil;
import com.lomoye.lion.core.domain.SportItem;
import com.lomoye.lion.core.domain.SportPlan;
import com.lomoye.lion.core.domain.User;
import com.lomoye.lion.core.manager.SportItemManager;
import com.lomoye.lion.core.manager.SportPlanManager;
import com.lomoye.lion.core.model.SportPlanReportModel;
import com.lomoye.lion.core.service.SportPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lomoye on 2018/1/29.
 * 运动计划控制类
 */
@RestController
@Scope(WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("/api/sportPlan")
public class SportPlanController extends BaseController {

    @Autowired
    private SportPlanService sportPlanService;
    @Autowired
    private SportPlanManager sportPlanManager;
    @Autowired
    private SportItemManager sportItemManager;

    @RequestMapping(value = "", method = RequestMethod.POST)
    ResultData<SportPlan> addSportPlan(HttpServletRequest request, @RequestBody SportPlan sportPlan) {
        User user = getSessionUser(request);
        sportPlanService.addSportPlan(user, sportPlan);
        return new ResultData<>(sportPlan);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    ResultData<SportPlan> getSportPlan(HttpServletRequest request) {
        User user = getSessionUser(request);
        SportPlan sportPlan = sportPlanManager.findNoExpiredSportPlan(user.getId());
        if (sportPlan != null) {
            List<Long> itemIdList = SplitterUtil.splitToLongList(sportPlan.getSportItemIds(), ",", null);
            List<SportItem> sportItems = sportItemManager.listByItemIds(user.getId(), itemIdList);
            sportPlan.setSportItemList(sportItems);
        }
        return new ResultData<>(sportPlan);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    ResultList<SportPlan> listSportPlan(HttpServletRequest request, Boolean isExpired) {
        User user = getSessionUser(request);
        List<SportPlan> sportPlanList = sportPlanService.listSportPlan(user, isExpired);
        return new ResultList<>(sportPlanList);
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    ResultData<SportPlanReportModel> getSportPlanReport(HttpServletRequest request, Long id) {
        User user = getSessionUser(request);

        return new ResultData<>(sportPlanService.getSportPlanReport(user, id));
    }
}
