package com.lomoye.lion.core.dao;

import com.lomoye.common.dao.PagedMapper;
import com.lomoye.lion.core.domain.SportItemLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SportItemLogMapper extends PagedMapper<Long,SportItemLog> {
    List<SportItemLog> listByRecordIds(@Param("userId") Long userId, @Param("recordIds") Set<Long> recordIds);
}
