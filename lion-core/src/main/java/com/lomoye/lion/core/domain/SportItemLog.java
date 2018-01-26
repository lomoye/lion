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

    private Long weightRecordId;//基于哪条记录的

    private Long sportPlanId;//属于哪个计划的

    private Date day;//哪天的

    private Integer isDone;//完成了么
}
