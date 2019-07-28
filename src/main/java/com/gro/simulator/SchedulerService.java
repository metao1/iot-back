package com.gro.simulator;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Component
public class SchedulerService {
    private Scheduler scheduler;

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerService.class);


    public SchedulerService(@Autowired Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void createSchedule(String cronExpression) throws SchedulerException {
        LOGGER.info("creating the schedule");
        JobDetail job = newJob(HumidityJob.class)
                .withIdentity("job1", "group1")
                .build();
        CronTrigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(cronSchedule("0/10 * * * * ?"))
                .build();

        JobDetail job2 = newJob(TemperatureJob.class)
                .withIdentity("job2", "group2")
                .build();
        CronTrigger trigger2 = newTrigger()
                .withIdentity("trigger2", "group2")
                .withSchedule(cronSchedule("0/12 * * * * ?"))
                .build();

        JobDetail job3 = newJob(MoistureJob.class)
                .withIdentity("job3", "group3")
                .build();
        CronTrigger trigger3 = newTrigger()
                .withIdentity("trigger3", "group3")
                .withSchedule(cronSchedule("0/13 * * * * ?"))
                .build();

        scheduler.scheduleJob(job, trigger);
        scheduler.scheduleJob(job2, trigger2);
        scheduler.scheduleJob(job3, trigger3);
    }

    @PreDestroy
    public void preDestroy() throws SchedulerException {
        scheduler.shutdown(true);
    }
}