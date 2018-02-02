package com.lomoye.lion.core.manager;

import com.lomoye.common.manager.DomainManager;
import com.lomoye.lion.core.domain.WeightRecord;
import com.lomoye.lion.core.model.WeightRecordSearchModel;

import java.util.Date;
import java.util.List;


public interface WeightRecordManager extends DomainManager<Long,WeightRecord> {
    WeightRecord findByDay(Long userId, Date day);

    List<WeightRecord> search(Long userId, WeightRecordSearchModel searchModel);

    long searchCount(Long userId, WeightRecordSearchModel searchModel);
}
