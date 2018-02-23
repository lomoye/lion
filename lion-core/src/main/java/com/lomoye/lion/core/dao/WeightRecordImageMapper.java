package com.lomoye.lion.core.dao;

import com.lomoye.common.dao.PagedMapper;
import com.lomoye.lion.core.domain.WeightRecordImage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface WeightRecordImageMapper extends PagedMapper<Long,WeightRecordImage> {
    List<WeightRecordImage> listByRecordIds(@Param("userId") Long userId, @Param("recordIds") Set<Long> recordIds);
}
