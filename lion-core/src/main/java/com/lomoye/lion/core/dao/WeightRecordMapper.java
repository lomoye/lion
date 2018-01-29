package com.lomoye.lion.core.dao;

import com.lomoye.common.dao.PagedMapper;
import com.lomoye.lion.core.domain.WeightRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface WeightRecordMapper extends PagedMapper<Long,WeightRecord> {
}
