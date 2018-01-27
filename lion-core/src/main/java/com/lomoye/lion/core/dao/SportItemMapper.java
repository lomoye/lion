package com.lomoye.lion.core.dao;

import com.lomoye.common.dao.PagedMapper;
import com.lomoye.lion.core.domain.SportItem;
import org.springframework.stereotype.Repository;

@Repository
public interface SportItemMapper extends PagedMapper<Long,SportItem> {
}
