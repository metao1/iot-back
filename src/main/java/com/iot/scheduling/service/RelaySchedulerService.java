package com.iot.scheduling.service;

import com.iot.model.relay.RelayScheduleJob;
import com.iot.scheduling.RelayJobFactory;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelaySchedulerService implements SchedulerService<RelayScheduleJob> {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private RelayJobFactory relayJobFactory;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void addJob(RelayScheduleJob job) {
        try {
            JobDetail current = scheduler.getJobDetail(JobKey.jobKey(String.valueOf(job.getId())));
            JobDetail jobDetail = relayJobFactory.getJobDetail(job);
            Trigger trigger = relayJobFactory.getTrigger(jobDetail, job);
            scheduler.scheduleJob(jobDetail, trigger);
            logger.debug("Job added to scheduler successfully");
        } catch (SchedulerException se) {
            logger.error(se.getMessage());
        }
    }

    @Override
    public void removeJob(RelayScheduleJob job) {
        try {
            JobDetail current = scheduler.getJobDetail(JobKey.jobKey(String.valueOf(job.getId())));
            if (current != null) {
                this.scheduler.deleteJob(JobKey.jobKey(String.valueOf(job.getId())));
            } else {
                logger.debug("Cannot deleteByTemperature non-existing job");
            }
        } catch (SchedulerException se) {
            logger.error(se.getMessage());
        }
    }

}
