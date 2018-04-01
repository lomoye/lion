package com.lomoye.lion.core.manager.impl;

import com.google.common.base.Preconditions;
import com.lomoye.common.dao.BasicMapper;
import com.lomoye.lion.core.dao.SportItemMapper;
import com.lomoye.lion.core.domain.SportItem;
import com.lomoye.lion.core.manager.SportItemManager;
import com.lomoye.common.manager.AbstractManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Component
public class SportItemManagerImpl extends AbstractManager<SportItem> implements SportItemManager{

    @Autowired
    private SportItemMapper mapper;


    @Override
    protected BasicMapper<Long, SportItem> getMapper() {
        return mapper;
    }

    @Override
    public List<SportItem> listByNames(Long userId, Set<String> sportItemNames) {
        Preconditions.checkArgument(userId != null && !sportItemNames.isEmpty());
        return nonEmptyList(mapper.listByNames(userId, sportItemNames));
    }

    @Override
    public List<SportItem> listByItemIds(Long userId, List<Long> itemIdList) {
        Preconditions.checkArgument(userId != null && !itemIdList.isEmpty());

        return nonEmptyList(mapper.listByItemIds(userId, itemIdList));
    }

    @Override
    public List<SportItem> listByUserId(Long userId) {
        Preconditions.checkArgument(userId != null);
        SportItem condition = new SportItem();
        condition.setUserId(userId);
        return nonEmptyList(listByCondition(condition, new ArrayList<>()));
    }
}
