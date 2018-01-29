package com.lomoye.lion.core.manager.impl;

import com.lomoye.common.dao.BasicMapper;
import com.lomoye.lion.core.dao.SportItemLogMapper;
import com.lomoye.lion.core.domain.SportItemLog;
import com.lomoye.lion.core.manager.SportItemLogManager;
import com.lomoye.common.manager.AbstractManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SportItemLogManagerImpl extends AbstractManager<SportItemLog> implements SportItemLogManager{

    @Autowired
    private SportItemLogMapper mapper;


    @Override
    protected BasicMapper<Long, SportItemLog> getMapper() {
        return mapper;
    }
}
