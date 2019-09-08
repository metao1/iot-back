package com.iot.web.controller;

import com.iot.handler.ObjectFactory;
import com.iot.model.dto.FixedTimeScheduleJobDto;
import com.iot.repository.schedule.FixedTimeScheduleRepository;
import com.iot.repository.schedule.IntervalScheduleRepository;
import com.iot.repository.schedule.ScheduleRepository;
import com.iot.scheduling.model.AbstractScheduleJob;
import com.iot.scheduling.model.FixedTimeScheduleJob;
import com.iot.scheduling.model.IntervalScheduleJob;
import com.iot.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private FixedTimeScheduleRepository fixedTimeScheduleRepository;

    @Autowired
    ObjectFactory objectFactory;

    @Autowired
    private IntervalScheduleRepository intervalScheduleRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public List<AbstractScheduleJob> getAllScheduleJob() {
        return scheduleRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/fixed", method = RequestMethod.GET)
    public List<FixedTimeScheduleJobDto> getAllFixedSchedules() {
        WebUtils<FixedTimeScheduleJobDto> fixedTimeScheduleJobWebUtils = new WebUtils<>(FixedTimeScheduleJobDto.class);
        List<FixedTimeScheduleJob> all = fixedTimeScheduleRepository.findAll();
        List<FixedTimeScheduleJobDto> dtos = new ArrayList<>();
        for (FixedTimeScheduleJob fixedTimeScheduleJob : all) {
            FixedTimeScheduleJobDto job = fixedTimeScheduleJobWebUtils.convertToObject(fixedTimeScheduleJob);
            dtos.add(job);
        }
        return dtos;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/fixed", method = RequestMethod.POST)
    public FixedTimeScheduleJobDto postFixedTimeSchedule(@RequestBody FixedTimeScheduleJobDto schedule) {
        WebUtils<FixedTimeScheduleJob> fixedTimeScheduleJobWebUtils = new WebUtils<>(FixedTimeScheduleJob.class);
        FixedTimeScheduleJob fixedTimeScheduleJob = fixedTimeScheduleJobWebUtils.convertToObject(schedule);
        FixedTimeScheduleJob saved = fixedTimeScheduleRepository.save(fixedTimeScheduleJob);
        WebUtils<FixedTimeScheduleJobDto> fixedTimeScheduleJobDtoWebUtils = new WebUtils<>(FixedTimeScheduleJobDto.class);
        return fixedTimeScheduleJobDtoWebUtils.convertToObject(saved);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/interval", method = RequestMethod.GET)
    public List<IntervalScheduleJob> getAllIntervalSchedules() {
        return intervalScheduleRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/interval", method = RequestMethod.POST)
    public IntervalScheduleJob postIntervalScheduleJob(@RequestBody IntervalScheduleJob schedule) {
        return null;
    }

}
