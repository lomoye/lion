package com.lomoye.lion.core.service;

import com.lomoye.lion.core.domain.SportPlan;
import com.lomoye.lion.core.domain.User;
import com.lomoye.lion.core.model.SportPlanReportModel;

import java.util.List;

/**
 * Created by lomoye on 2018/1/29.
 * 运动计划
 */
public interface SportPlanService {
    SportPlan addSportPlan(User user, SportPlan sportPlan);

    List<SportPlan> listSportPlan(User user, Boolean isEnd);

    SportPlanReportModel getSportPlanReport(User user, Long id);//运动报表
}
