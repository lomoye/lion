package com.lomoye.lion.core.manager;

import com.lomoye.common.manager.DomainManager;
import com.lomoye.lion.core.domain.WeightRecordImage;

import java.util.List;
import java.util.Set;


public interface WeightRecordImageManager extends DomainManager<Long,WeightRecordImage> {
    List<WeightRecordImage> listByRecordIds(Long userId, Set<Long> recordIds);
}
