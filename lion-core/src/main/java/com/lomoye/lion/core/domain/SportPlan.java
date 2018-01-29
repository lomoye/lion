package com.lomoye.lion.core.domain;

import com.lomoye.common.domain.CommonDomain;

import java.util.Date;
import java.util.List;

/**
 * Created by lomoye on 2018/1/26.
 * 运动计划
 */
public class SportPlan extends CommonDomain {
    private Long userId;

    private Long targetWeight;//目标体重*100 公斤

    private Date startTime;

    private Date endTime;

    private String sportItemIds;//运动条目ids


    /**
     * 非持久化属性
     */
    private List<SportItem> sportItemList;//运动计划条目集合

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(Long targetWeight) {
        this.targetWeight = targetWeight;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSportItemIds() {
        return sportItemIds;
    }

    public void setSportItemIds(String sportItemIds) {
        this.sportItemIds = sportItemIds;
    }

    public List<SportItem> getSportItemList() {
        return sportItemList;
    }

    public void setSportItemList(List<SportItem> sportItemList) {
        this.sportItemList = sportItemList;
    }
}
