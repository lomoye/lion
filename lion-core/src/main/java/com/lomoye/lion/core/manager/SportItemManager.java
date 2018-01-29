package com.lomoye.lion.core.manager;

import com.lomoye.common.manager.DomainManager;
import com.lomoye.lion.core.domain.SportItem;

import java.util.List;
import java.util.Set;


public interface SportItemManager extends DomainManager<Long,SportItem> {
    List<SportItem> listByNames(Long userId, Set<String> sportItemNames);
}
