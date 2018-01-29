package com.lomoye.lion.core.manager.impl;

import com.lomoye.common.dao.BasicMapper;
import com.lomoye.lion.core.dao.WeightRecordMapper;
import com.lomoye.lion.core.domain.WeightRecord;
import com.lomoye.lion.core.manager.WeightRecordManager;
import com.lomoye.common.manager.AbstractManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class WeightRecordManagerImpl extends AbstractManager<WeightRecord> implements WeightRecordManager{

    @Autowired
    private WeightRecordMapper mapper;


    @Override
    protected BasicMapper<Long, WeightRecord> getMapper() {
        return mapper;
    }
}
