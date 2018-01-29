package com.lomoye.lion.core.service.impl;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.lomoye.lion.core.domain.SportItem;
import com.lomoye.lion.core.domain.SportPlan;
import com.lomoye.lion.core.domain.User;
import com.lomoye.lion.core.manager.SportItemManager;
import com.lomoye.lion.core.manager.SportPlanManager;
import com.lomoye.lion.core.service.SportPlanService;
import com.lomoye.lion.core.validator.SportPlanValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by lomoye on 2018/1/29.
 *
 */
@Service
public class SportPlanServiceImpl implements SportPlanService {

    @Autowired
    private SportPlanManager sportPlanManager;
    @Autowired
    private SportItemManager sportItemManager;

    @Override
    public SportPlan addSportPlan(User user, SportPlan sportPlan) {
        SportPlanValidator.ensureAddSportPlanParam(user.getId(), sportPlan);

        String sportItemIds = addSportItems(user.getId(), sportPlan.getSportItemList());

        sportPlan.setSportItemIds(sportItemIds);

        sportPlanManager.save(sportPlan);
        return sportPlan;
    }

    private String addSportItems(Long userId, List<SportItem> sportItemList) {
        Set<String> sportItemNames = sportItemList.stream().map(SportItem::getName).collect(Collectors.toSet());
        List<SportItem> existItems = sportItemManager.listByNames(userId, sportItemNames);
        Map<String, SportItem> itemMap = Maps.uniqueIndex(existItems, SportItem::getName);

        List<Long> sportItemIds = new ArrayList<>();
        for (SportItem sportItem : sportItemList) {
            SportItem existItem = itemMap.get(sportItem.getName());
            if (existItem != null) {
                sportItemIds.add(existItem.getId());
                continue;
            }
            sportItem.setUserId(userId);
            sportItemManager.save(sportItem);
            sportItemIds.add(sportItem.getId());
        }

        return Joiner.on(",").join(sportItemIds);
    }
}
