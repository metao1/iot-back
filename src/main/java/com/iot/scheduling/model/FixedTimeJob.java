package com.iot.scheduling.model;

import com.iot.scheduling.adapter.ScheduleJobExecutionAdapter;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class FixedTimeJob implements Job {

    @Autowired
    private ScheduleJobExecutionAdapter adapter;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        adapter.execute(context);
    }

}
