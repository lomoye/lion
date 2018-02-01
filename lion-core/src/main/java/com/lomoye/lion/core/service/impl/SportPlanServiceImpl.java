package com.lomoye.lion.core.service.impl;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.lomoye.common.exception.BusinessException;
import com.lomoye.common.util.SplitterUtil;
import com.lomoye.lion.core.constant.ErrorCode;
import com.lomoye.lion.core.domain.SportItem;
import com.lomoye.lion.core.domain.SportPlan;
import com.lomoye.lion.core.domain.User;
import com.lomoye.lion.core.manager.SportItemManager;
import com.lomoye.lion.core.manager.SportPlanManager;
import com.lomoye.lion.core.service.SportPlanService;
import com.lomoye.lion.core.validator.SportPlanValidator;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(SportPlanServiceImpl.class);

    @Autowired
    private SportPlanManager sportPlanManager;
    @Autowired
    private SportItemManager sportItemManager;

    @Override
    public SportPlan addSportPlan(User user, SportPlan sportPlan) {
        SportPlanValidator.ensureAddSportPlanParam(user.getId(), sportPlan);
        checkAlreadyHasNoExpiredSportPlan(user.getId());//检查是否还有没有过期的计划 只能有一个

        String sportItemIds = addSportItems(user.getId(), sportPlan.getSportItemList());

        sportPlan.setSportItemIds(sportItemIds);
        sportPlan.setUserId(user.getId());
        sportPlanManager.save(sportPlan);
        return sportPlan;
    }

    @Override
    public List<SportPlan> listSportPlan(User user, Boolean isExpired) {
        List<SportPlan> sportPlanList = sportPlanManager.listSportPlan(user.getId(), isExpired);
        if (CollectionUtils.isEmpty(sportPlanList)) {
            return sportPlanList;
        }

        for (SportPlan sportPlan : sportPlanList) {
            List<Long> itemIdList = SplitterUtil.splitToLongList(sportPlan.getSportItemIds(), ",", null);
            List<SportItem> sportItems = sportItemManager.listByItemIds(user.getId(), itemIdList);
            sportPlan.setSportItemList(sportItems);
        }

        return sportPlanList;
    }

    private void checkAlreadyHasNoExpiredSportPlan(Long userId) {
        SportPlan sportPlan = sportPlanManager.findNoExpiredSportPlan(userId);
        if (sportPlan != null) {
            LOGGER.warn("checkAlreadyHasNoExpiredSportPlan has noExpired sportPlan|userId={}", userId);
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "只能有一个正在进行中的运动计划");
        }
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
