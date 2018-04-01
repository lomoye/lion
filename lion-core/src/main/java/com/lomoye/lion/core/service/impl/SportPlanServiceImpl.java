package com.lomoye.lion.core.service.impl;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lomoye.common.exception.BusinessException;
import com.lomoye.common.util.DateUtil;
import com.lomoye.common.util.SplitterUtil;
import com.lomoye.lion.core.constant.ErrorCode;
import com.lomoye.lion.core.domain.SportItem;
import com.lomoye.lion.core.domain.SportItemLog;
import com.lomoye.lion.core.domain.SportPlan;
import com.lomoye.lion.core.domain.User;
import com.lomoye.lion.core.manager.SportItemLogManager;
import com.lomoye.lion.core.manager.SportItemManager;
import com.lomoye.lion.core.manager.SportPlanManager;
import com.lomoye.lion.core.model.SportPlanReport;
import com.lomoye.lion.core.model.SportPlanReportModel;
import com.lomoye.lion.core.service.SportPlanService;
import com.lomoye.lion.core.validator.SportPlanValidator;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by lomoye on 2018/1/29.
 */
@Service
public class SportPlanServiceImpl implements SportPlanService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SportPlanServiceImpl.class);

    @Autowired
    private SportPlanManager sportPlanManager;
    @Autowired
    private SportItemManager sportItemManager;
    @Autowired
    private SportItemLogManager sportItemLogManager;

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

    @Override
    public SportPlanReportModel getSportPlanReport(User user, Long id) {
        SportPlan sportPlan = sportPlanManager.findById(user.getId(), id);
        List<SportItemLog> sportItemLogs = sportItemLogManager.listBySportPlanId(user.getId(), id);
        Map<Date, List<SportItemLog>> sportItemLogMap = initSportItemLogMap(sportPlan.getStartTime(), sportPlan.getEndTime());

        for (SportItemLog log : sportItemLogs) {
            List<SportItemLog> logs = sportItemLogMap.get(log.getDay());
            if (CollectionUtils.isEmpty(logs)) {
                sportItemLogMap.put(log.getDay(), Lists.newArrayList(log));
                continue;
            }
            logs.add(log);
        }

        List<Map<String/*key*/, Object>> reports = new ArrayList<>();

        sportItemLogMap.forEach((day, logs) -> {
            Map<String, Object> report = new HashMap<>();
            report.put("day", DateUtil.format(day, "yyyy-MM-dd"));

            logs.forEach(log -> report.put(log.getSportItemId().toString(), true));
            reports.add(report);
        });

        List<SportItem> sportItems = sportItemManager.listByItemIds(user.getId(), SplitterUtil.splitToLongList(sportPlan.getSportItemIds(), ",", null));

        List<SportPlanReport> keyInfos = new ArrayList<>();
        keyInfos.add(new SportPlanReport("day", "日期", "date"));

        sportItems.forEach(sportItem -> {
            keyInfos.add(new SportPlanReport(sportItem.getId().toString(), sportItem.getName(), "boolean"));
        });

        SportPlanReportModel model = new SportPlanReportModel();
        model.setSportPlan(sportPlan);
        model.setKeyInfos(keyInfos);
        model.setReports(reports);

        return model;
    }

    public static void main(String[] args) {
        Map<Date, Integer> map = new HashMap<>();
    }

    private Map<Date, List<SportItemLog>> initSportItemLogMap(Date startTime, Date endTime) {
        Map<Date, List<SportItemLog>> map = new LinkedHashMap<>();
        endTime = DateUtil.getDailyStartTime(endTime);
        while (!endTime.before(startTime)) {
            map.put(endTime, new ArrayList<>());

            endTime = DateUtil.addDateDays(endTime, -1);
        }

        return map;
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
