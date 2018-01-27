package com.lomoye.lion.core.manager.impl;

import com.lomoye.common.dao.BasicMapper;
import com.lomoye.lion.core.dao.SportPlanMapper;
import com.lomoye.lion.core.domain.SportPlan;
import com.lomoye.lion.core.manager.SportPlanManager;
import com.lomoye.common.manager.AbstractManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SportPlanManagerImpl extends AbstractManager<SportPlan> implements SportPlanManager{

    @Autowired
    private SportPlanMapper mapper;


    @Override
    protected BasicMapper<Long, SportPlan> getMapper() {
        return mapper;
    }
}
