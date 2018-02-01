package com.lomoye.lion.core.manager;

import com.lomoye.common.manager.DomainManager;
import com.lomoye.lion.core.domain.WeightRecord;

import java.util.Date;


public interface WeightRecordManager extends DomainManager<Long,WeightRecord> {
    WeightRecord findByDay(Long userId, Date day);
}
