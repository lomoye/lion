package com.lomoye.lion.core.manager.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.lomoye.common.dao.BasicMapper;
import com.lomoye.common.dao.OrderCondition;
import com.lomoye.lion.core.dao.SportItemLogMapper;
import com.lomoye.lion.core.domain.SportItemLog;
import com.lomoye.lion.core.manager.SportItemLogManager;
import com.lomoye.common.manager.AbstractManager;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;


@Component
public class SportItemLogManagerImpl extends AbstractManager<SportItemLog> implements SportItemLogManager{

    @Autowired
    private SportItemLogMapper mapper;


    @Override
    protected BasicMapper<Long, SportItemLog> getMapper() {
        return mapper;
    }

    @Override
    public List<SportItemLog> listByRecordIds(Long userId, Set<Long> recordIds) {
        Preconditions.checkArgument(userId != null && CollectionUtils.isNotEmpty(recordIds));

        return nonEmptyList(mapper.listByRecordIds(userId, recordIds));
    }

    @Override
    public List<SportItemLog> listBySportPlanId(Long userId, Long sportPlanId) {
        Preconditions.checkArgument(userId != null && sportPlanId != null);
        SportItemLog condition = new SportItemLog();
        condition.setUserId(userId);
        condition.setSportPlanId(sportPlanId);
        return nonEmptyList(listByCondition(condition, Lists.newArrayList(new OrderCondition("`day`", "desc"))));
    }
}
