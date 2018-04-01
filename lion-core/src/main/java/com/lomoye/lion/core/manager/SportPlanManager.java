package com.lomoye.lion.core.manager;

import com.lomoye.common.manager.DomainManager;
import com.lomoye.lion.core.domain.SportPlan;

import java.util.List;


public interface SportPlanManager extends DomainManager<Long,SportPlan> {
    SportPlan findNoExpiredSportPlan(Long userId);

    List<SportPlan> listSportPlan(Long userId, Boolean isExpired);

    SportPlan findById(Long userId, Long id);
}
