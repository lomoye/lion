package com.lomoye.lion.core.manager.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.lomoye.common.dao.BasicMapper;
import com.lomoye.common.dao.OrderCondition;
import com.lomoye.common.dao.Page;
import com.lomoye.common.manager.AbstractManager;
import com.lomoye.lion.core.dao.WeightRecordMapper;
import com.lomoye.lion.core.domain.WeightRecord;
import com.lomoye.lion.core.manager.WeightRecordManager;
import com.lomoye.lion.core.model.WeightRecordSearchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
public class WeightRecordManagerImpl extends AbstractManager<WeightRecord> implements WeightRecordManager{

    @Autowired
    private WeightRecordMapper mapper;


    @Override
    protected BasicMapper<Long, WeightRecord> getMapper() {
        return mapper;
    }

    @Override
    public WeightRecord findByDay(Long userId, Date day) {
        Preconditions.checkArgument(userId != null && day != null);
        WeightRecord condition = new WeightRecord();
        condition.setUserId(userId);
        condition.setDay(day);
        return getByCondition(condition);
    }

    @Override
    public List<WeightRecord> search(Long userId, WeightRecordSearchModel searchModel) {
        Preconditions.checkArgument(userId != null && searchModel != null);
        Page page = new Page(searchModel);
        page.addOrder("`day`", "desc");

        WeightRecord condition = new WeightRecord();
        condition.setUserId(userId);
        return nonEmptyList(listWithPage(condition, page));
    }

    @Override
    public long searchCount(Long userId, WeightRecordSearchModel searchModel) {
        Preconditions.checkArgument(userId != null && searchModel != null);

        WeightRecord condition = new WeightRecord();
        condition.setUserId(userId);
        return count(condition);
    }

    @Override
    public List<WeightRecord> findAllByUserId(Long userId) {
        Preconditions.checkArgument(userId != null);
        WeightRecord condition = new WeightRecord();
        condition.setUserId(userId);

        return listByCondition(condition, Lists.newArrayList(new OrderCondition("`day`", "asc")));
    }
}
