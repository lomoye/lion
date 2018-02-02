package com.lomoye.lion.core.manager;

import com.lomoye.common.manager.DomainManager;
import com.lomoye.lion.core.domain.SportItemLog;

import java.util.List;
import java.util.Set;


public interface SportItemLogManager extends DomainManager<Long,SportItemLog> {
    List<SportItemLog> listByRecordIds(Long userId, Set<Long> recordIds);
}
