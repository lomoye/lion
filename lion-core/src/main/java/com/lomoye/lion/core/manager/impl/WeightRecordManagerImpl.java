package com.lomoye.lion.core.manager.impl;

import com.google.common.base.Preconditions;
import com.lomoye.common.dao.BasicMapper;
import com.lomoye.lion.core.dao.WeightRecordMapper;
import com.lomoye.lion.core.domain.WeightRecord;
import com.lomoye.lion.core.manager.WeightRecordManager;
import com.lomoye.common.manager.AbstractManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class WeightRecordManagerImpl extends AbstractManager<WeightRecord> implements WeightRecordManager{

    @Autowired
    private WeightRecordMapper mapper;


    @Override
    protected BasicMapper<Long, WeightRecord> getMapper() {
        return mapper;
    }

    @Override
    public WeightRecord findByDay(Long userId, Date day) {
        Preconditions.checkArgument(userId != null && day != null);
        WeightRecord condition = new WeightRecord();
        condition.setUserId(userId);
        condition.setDay(day);
        return getByCondition(condition);
    }
}
