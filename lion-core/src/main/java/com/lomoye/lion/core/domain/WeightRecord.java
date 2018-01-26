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

    private Long weight;//体重*100  66.1 -> 6610

    private Integer isSport;//有无运动

    private Integer isBreakfast;//吃早饭了没有

    private Integer isLunch;//吃午饭了没有

    private Integer isDinner;//吃晚饭了没有

    private String desc;//备注


    /**
     * 以下是不存数据库的字段
     */
    List<WeightRecordImage> weightRecordImageList;//上传的图片

    private SportPlan sportPlan;//运动计划

}
