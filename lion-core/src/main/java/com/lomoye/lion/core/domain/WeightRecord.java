package com.lomoye.lion.core.domain;

import com.lomoye.common.domain.CommonDomain;

import java.util.Date;
import java.util.List;

/**
 * Created by lomoye on 2018/1/26.
 * 体重记录
 */
public class WeightRecord extends CommonDomain {
    private Long userId;

    private Date day;//日期

    private Long weight;//体重*100  66.1 -> 6610 必填

    private Long bust;//胸围*10 mm 可选

    private Long waist;//腰围*10 mm 可选

    private Long hip;//臀围*10 mm 可选

    private Long arm;//臂围*10 mm 可选

    private Long thigh;//大腿围*10 mm 可选

    private Long calf;//小腿围*10 mm 可选

    private Integer isSport;//有无运动

    private Integer isBreakfast;//吃早饭了没有

    private Integer isLunch;//吃午饭了没有

    private Integer isDinner;//吃晚饭了没有

    private String desc;//备注


    /**
     * 以下是不存数据库的字段
     */
    private List<WeightRecordImage> weightRecordImageList;//上传的图片

    private SportPlan sportPlan;//运动计划

    private List<SportItemLog> sportItemLogList;//运动项目记录


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Long getBust() {
        return bust;
    }

    public void setBust(Long bust) {
        this.bust = bust;
    }

    public Long getWaist() {
        return waist;
    }

    public void setWaist(Long waist) {
        this.waist = waist;
    }

    public Long getHip() {
        return hip;
    }

    public void setHip(Long hip) {
        this.hip = hip;
    }

    public Long getArm() {
        return arm;
    }

    public void setArm(Long arm) {
        this.arm = arm;
    }

    public Long getThigh() {
        return thigh;
    }

    public void setThigh(Long thigh) {
        this.thigh = thigh;
    }

    public Long getCalf() {
        return calf;
    }

    public void setCalf(Long calf) {
        this.calf = calf;
    }

    public Integer getIsSport() {
        return isSport;
    }

    public void setIsSport(Integer isSport) {
        this.isSport = isSport;
    }

    public Integer getIsBreakfast() {
        return isBreakfast;
    }

    public void setIsBreakfast(Integer isBreakfast) {
        this.isBreakfast = isBreakfast;
    }

    public Integer getIsLunch() {
        return isLunch;
    }

    public void setIsLunch(Integer isLunch) {
        this.isLunch = isLunch;
    }

    public Integer getIsDinner() {
        return isDinner;
    }

    public void setIsDinner(Integer isDinner) {
        this.isDinner = isDinner;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<WeightRecordImage> getWeightRecordImageList() {
        return weightRecordImageList;
    }

    public void setWeightRecordImageList(List<WeightRecordImage> weightRecordImageList) {
        this.weightRecordImageList = weightRecordImageList;
    }

    public SportPlan getSportPlan() {
        return sportPlan;
    }

    public void setSportPlan(SportPlan sportPlan) {
        this.sportPlan = sportPlan;
    }

    public List<SportItemLog> getSportItemLogList() {
        return sportItemLogList;
    }

    public void setSportItemLogList(List<SportItemLog> sportItemLogList) {
        this.sportItemLogList = sportItemLogList;
    }
}
