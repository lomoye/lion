package com.lomoye.lion.core.manager.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.lomoye.common.dao.BasicMapper;
import com.lomoye.lion.core.dao.SportPlanMapper;
import com.lomoye.lion.core.domain.SportPlan;
import com.lomoye.lion.core.manager.SportPlanManager;
import com.lomoye.common.manager.AbstractManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class SportPlanManagerImpl extends AbstractManager<SportPlan> implements SportPlanManager{

    @Autowired
    private SportPlanMapper mapper;


    @Override
    protected BasicMapper<Long, SportPlan> getMapper() {
        return mapper;
    }

    @Override
    public SportPlan findNoExpiredSportPlan(Long userId) {
        Preconditions.checkArgument(userId != null);
        return mapper.findNoExpiredSportPlan(userId);
    }

    @Override
    public List<SportPlan> listSportPlan(Long userId, Boolean isExpired) {
        Preconditions.checkArgument(userId != null && isExpired != null);
        if (isExpired) {
            return nonEmptyList(mapper.findExpiredSportPLan(userId));
        } else {
            SportPlan sportPlan = mapper.findNoExpiredSportPlan(userId);
            if (sportPlan == null) {
                return new ArrayList<>();
            }
            return Lists.newArrayList(sportPlan);
        }

    }

    @Override
    public SportPlan findById(Long userId, Long id) {
        Preconditions.checkArgument(userId != null && id != null);
        SportPlan condition = new SportPlan();
        condition.setUserId(userId);
        condition.setId(id);
        return getByCondition(condition);
    }
}
