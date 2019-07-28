package com.gro.scheduling;

import com.gro.model.relay.RelayScheduleJob;
import org.quartz.*;

import java.util.HashMap;
import java.util.Map;

public class RelayJobFactory implements ScheduleJobFactory<RelayScheduleJob> {

    @Override
    public JobDetail getJobDetail(RelayScheduleJob schedule) {
        Map<String, Object> jobData = new HashMap<>();
        jobData.put("schedule", schedule);
        return JobBuilder.newJob()
                .ofType(RelayJob.class)
                .storeDurably()
                .withIdentity(JobKey.jobKey(String.valueOf(schedule.getId())))
                .usingJobData(new JobDataMap(jobData))
                .build();
    }

    @Override
    public Trigger getTrigger(JobDetail jobDetail, RelayScheduleJob schedule) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(String.valueOf(schedule.getId()), "test")
                .withSchedule(
                        CronScheduleBuilder.dailyAtHourAndMinute(schedule.getTime().getHours(), schedule.getTime().getMinutes())
                ).build();
    }

}
