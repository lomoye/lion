package com.lomoye.lion.core.dao;

import com.lomoye.common.dao.PagedMapper;
import com.lomoye.lion.core.domain.SportItemLog;
import org.springframework.stereotype.Repository;

@Repository
public interface SportItemLogMapper extends PagedMapper<Long,SportItemLog> {
}
