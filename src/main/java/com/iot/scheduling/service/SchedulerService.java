package com.iot.scheduling.service;

public interface SchedulerService<I> {
    public void addJob(I i);

    public void removeJob(I i);
}
