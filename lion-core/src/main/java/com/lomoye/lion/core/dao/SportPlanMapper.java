package com.lomoye.lion.core.dao;

import com.lomoye.common.dao.PagedMapper;
import com.lomoye.lion.core.domain.SportPlan;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportPlanMapper extends PagedMapper<Long, SportPlan> {
    SportPlan findNoExpiredSportPlan(@Param("userId") Long userId);

    List<SportPlan> findExpiredSportPLan(@Param("userId") Long userId);
}
