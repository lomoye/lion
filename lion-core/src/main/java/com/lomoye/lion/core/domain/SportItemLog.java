package com.lomoye.lion.core.domain;

import com.lomoye.common.domain.CommonDomain;

import java.util.Date;

/**
 * Created by lomoye on 2018/1/26.
 * 基于运动项目的日常记录
 */
public class SportItemLog extends CommonDomain {
    private Long userId;

    private Long sportItemId;//属于哪个运动项目的

    private Long sportItemName;//运动名称

    private Long weightRecordId;//基于哪条记录的

    private Long sportPlanId;//属于哪个计划的

    private Date day;//哪天的


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSportItemId() {
        return sportItemId;
    }

    public void setSportItemId(Long sportItemId) {
        this.sportItemId = sportItemId;
    }

    public Long getSportItemName() {
        return sportItemName;
    }

    public void setSportItemName(Long sportItemName) {
        this.sportItemName = sportItemName;
    }

    public Long getWeightRecordId() {
        return weightRecordId;
    }

    public void setWeightRecordId(Long weightRecordId) {
        this.weightRecordId = weightRecordId;
    }

    public Long getSportPlanId() {
        return sportPlanId;
    }

    public void setSportPlanId(Long sportPlanId) {
        this.sportPlanId = sportPlanId;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

}
