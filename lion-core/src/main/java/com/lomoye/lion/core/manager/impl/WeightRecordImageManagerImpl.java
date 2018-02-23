package com.lomoye.lion.core.manager.impl;

import com.google.common.base.Preconditions;
import com.lomoye.common.dao.BasicMapper;
import com.lomoye.lion.core.dao.WeightRecordImageMapper;
import com.lomoye.lion.core.domain.WeightRecordImage;
import com.lomoye.lion.core.manager.WeightRecordImageManager;
import com.lomoye.common.manager.AbstractManager;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;


@Component
public class WeightRecordImageManagerImpl extends AbstractManager<WeightRecordImage> implements WeightRecordImageManager{

    @Autowired
    private WeightRecordImageMapper mapper;


    @Override
    protected BasicMapper<Long, WeightRecordImage> getMapper() {
        return mapper;
    }

    @Override
    public List<WeightRecordImage> listByRecordIds(Long userId, Set<Long> recordIds) {
        Preconditions.checkArgument(userId != null && CollectionUtils.isNotEmpty(recordIds));
        return nonEmptyList(mapper.listByRecordIds(userId, recordIds));
    }
}
