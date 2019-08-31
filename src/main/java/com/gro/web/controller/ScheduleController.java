package com.gro.web.controller;

import com.gro.handler.ObjectFactory;
import com.gro.model.dto.FixedTimeScheduleJobDto;
import com.gro.repository.schedule.FixedTimeScheduleRepository;
import com.gro.repository.schedule.IntervalScheduleRepository;
import com.gro.repository.schedule.ScheduleRepository;
import com.gro.scheduling.model.AbstractScheduleJob;
import com.gro.scheduling.model.FixedTimeScheduleJob;
import com.gro.scheduling.model.IntervalScheduleJob;
import com.gro.utils.WebUtils;
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
