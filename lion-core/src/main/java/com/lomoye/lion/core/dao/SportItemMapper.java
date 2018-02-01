package com.lomoye.lion.core.dao;

import com.lomoye.common.dao.PagedMapper;
import com.lomoye.lion.core.domain.SportItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SportItemMapper extends PagedMapper<Long, SportItem> {
    List<SportItem> listByNames(@Param("userId") Long userId, @Param("sportItemNames") Set<String> sportItemNames);

    List<SportItem> listByItemIds(@Param("userId") Long userId, @Param("itemIdList") List<Long> itemIdList);
}
