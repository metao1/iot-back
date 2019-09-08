package com.iot.scheduling.factory;

import com.iot.scheduling.model.FixedTimeJob;
import com.iot.scheduling.model.FixedTimeScheduleJob;
import org.quartz.*;

import java.util.HashMap;
import java.util.Map;

public class FixedTimeScheduleJobFactory<T extends FixedTimeScheduleJob, D> implements ScheduleJobFactory<T, D> {

    @Override
    public JobDetail getJobDetail(T t, D d) {
        Map<String, Object> jobData = new HashMap<>();
        jobData.put("schedule", t);
        jobData.put("data", d);
        return JobBuilder.newJob()
                .ofType(FixedTimeJob.class)
                .storeDurably()
                .withIdentity(JobKey.jobKey(String.valueOf(t.getId())))
                .usingJobData(new JobDataMap(jobData))
                .build();
    }

    @Override
    public Trigger getTrigger(JobDetail jobDetail, T t) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(String.valueOf(t.getId()), t.getComponent().getClass().toString())
                .withSchedule(
                        CronScheduleBuilder.dailyAtHourAndMinute(t.getTime().getHours(), t.getTime().getMinutes())
                ).build();
    }

}
