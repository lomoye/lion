package com.lomoye.lion.core.dao;

import com.lomoye.common.dao.PagedMapper;
import com.lomoye.lion.core.domain.SportPlan;
import org.springframework.stereotype.Repository;

@Repository
public interface SportPlanMapper extends PagedMapper<Long,SportPlan> {
}
