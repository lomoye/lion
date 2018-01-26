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

    private Date startTime;

    private Date endTime;

    private String sportItemIds;//运动条目ids


    /**
     * 非持久化属性
     */
    private List<SportItem> sportItemList;//运动计划条目集合
}
