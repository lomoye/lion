package com.lomoye.lion.core.schedule;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * Created by lomoye on 2017/12/25.
 * 定时任务管理中心
 */
public class ScheduleManagerImpl implements ScheduleManager {

    //定时任务存储
    private final ConcurrentHashMap<String, ScheduledJob> scheduledJobMap = new ConcurrentHashMap<>();

    private final ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();

    public ScheduleManagerImpl() {
        threadPoolTaskScheduler.setPoolSize(100);
        threadPoolTaskScheduler.initialize();
    }


    @Override
    public int add(ScheduledJob scheduledJob) {
        Preconditions.checkArgument(scheduledJob != null && !Strings.isNullOrEmpty(scheduledJob.getCron())
        && !Strings.isNullOrEmpty(scheduledJob.getUniqueKey()) && scheduledJob.getJob() != null);

        ScheduledJob oldJob = scheduledJobMap.putIfAbsent(scheduledJob.getUniqueKey(), scheduledJob);
        if (oldJob != null) {
            return -1;//原来已经有了
        }

        //启动新增的任务
        ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(scheduledJob.getJob(), new CronTrigger(scheduledJob.getCron()));
        scheduledJob.setFuture(future);
        return 1;
    }

    @Override
    public int remove(String uniqueKey) {
        ScheduledJob job = select(uniqueKey);
        if (job == null) {
            return -1;
        }

        if (job.getFuture().cancel(true)) {
            scheduledJobMap.remove(uniqueKey);
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int update(ScheduledJob scheduledJob) {
        return 0;
    }

    @Override
    public ScheduledJob select(String uniqueKey) {
        return scheduledJobMap.get(uniqueKey);
    }
}
