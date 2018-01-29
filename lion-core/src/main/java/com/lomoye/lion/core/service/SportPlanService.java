package com.lomoye.lion.core.service;

import com.lomoye.lion.core.domain.SportPlan;
import com.lomoye.lion.core.domain.User;

/**
 * Created by lomoye on 2018/1/29.
 * 运动计划
 */
public interface SportPlanService {
    SportPlan addSportPlan(User user, SportPlan sportPlan);
}
