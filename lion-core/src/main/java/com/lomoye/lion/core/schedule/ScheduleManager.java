package com.lomoye.lion.core.schedule;

/**
 * Created by lomoye on 2017/12/25.
 * 定时器管理者
 */
public interface ScheduleManager {
    int add(ScheduledJob scheduledJob);//新增定时任务

    int remove(String uniqueKey);//根据唯一key去除任务

    int update(ScheduledJob scheduledJob);//跟新原来的任务

    ScheduledJob select(String uniqueKey);//查询任务
}
