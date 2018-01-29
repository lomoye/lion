package com.lomoye.lion.core.manager.impl;

import com.lomoye.common.dao.BasicMapper;
import com.lomoye.lion.core.dao.WeightRecordImageMapper;
import com.lomoye.lion.core.domain.WeightRecordImage;
import com.lomoye.lion.core.manager.WeightRecordImageManager;
import com.lomoye.common.manager.AbstractManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class WeightRecordImageManagerImpl extends AbstractManager<WeightRecordImage> implements WeightRecordImageManager{

    @Autowired
    private WeightRecordImageMapper mapper;


    @Override
    protected BasicMapper<Long, WeightRecordImage> getMapper() {
        return mapper;
    }
}
