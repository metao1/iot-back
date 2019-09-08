package com.iot.scheduling.adapter;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ScheduleJobExecutionAdapter implements JobExecutionAdapter {

    Logger logger = LoggerFactory.getLogger(ScheduleJobExecutionAdapter.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {

        logger.info("executing the job");
    }

}
