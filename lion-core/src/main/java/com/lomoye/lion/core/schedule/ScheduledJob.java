package com.lomoye.lion.core.schedule;

import java.util.concurrent.ScheduledFuture;

/**
 * Created by lomoye on 2017/12/25.
 * 定时任务
 */
public class ScheduledJob {
    private String uniqueKey;//唯一标识 自己定义

    private String cron;// 0/5 * * * * ?

    private Runnable job;

    private ScheduledFuture<?> future;

    public ScheduledJob(String uniqueKey, String cron, Runnable job) {
        this.uniqueKey = uniqueKey;
        this.cron = cron;
        this.job = job;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Runnable getJob() {
        return job;
    }

    public void setJob(Runnable job) {
        this.job = job;
    }

    public ScheduledFuture<?> getFuture() {
        return future;
    }

    public void setFuture(ScheduledFuture<?> future) {
        this.future = future;
    }
}
