package com.lomoye.lion.core.manager.impl;

import com.lomoye.common.dao.BasicMapper;
import com.lomoye.lion.core.dao.SportItemMapper;
import com.lomoye.lion.core.domain.SportItem;
import com.lomoye.lion.core.manager.SportItemManager;
import com.lomoye.common.manager.AbstractManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SportItemManagerImpl extends AbstractManager<SportItem> implements SportItemManager{

    @Autowired
    private SportItemMapper mapper;


    @Override
    protected BasicMapper<Long, SportItem> getMapper() {
        return mapper;
    }
}
