package com.iot.web.controller;

import com.iot.model.rpicomponent.AbstractRPiComponent;
import com.iot.model.rpicomponent.data.MoistureData;
import com.iot.model.rpicomponent.exception.RPiComponentNotFoundException;
import com.iot.repository.data.MoistureDataRepository;
import com.iot.repository.rpicomponent.RPiComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping(value = "/api/component")
@RestController
public class MoistureDataController {

    @Autowired
    private RPiComponentRepository rPiComponentRepository;

    @Autowired
    private MoistureDataRepository moistureDataRepository;

    @Value("${exception.rpi-component-not-found}")
    private String componentNotFoundException;


    @GetMapping(value = "/{id}/moisture")
    public Page<MoistureData> getMoistureDataPage(
        @PathVariable("id") Integer id,
        @PageableDefault(sort = {"timestamp"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {

       Optional<AbstractRPiComponent> component = rPiComponentRepository.findById(id);
        if (!component.isPresent())
            throw new RPiComponentNotFoundException(componentNotFoundException);
        return moistureDataRepository.findAllByComponent(component.get(), pageable);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/{id}/moisture")
    public void postMoistureData(@RequestBody MoistureData data) {
        moistureDataRepository.save(data);
    }

    @RequestMapping(value = "/{id}/moisture/monthly/average", method = RequestMethod.GET)
    public Page<MoistureData> getMoistureDataMonthlyAverage(
        @PathVariable("id") Integer id,
        @PageableDefault(sort = {"timestamp"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {

        Optional<AbstractRPiComponent> component = rPiComponentRepository.findById(id);
        if (!component.isPresent())
            throw new RPiComponentNotFoundException(componentNotFoundException);
        return moistureDataRepository.findMonthlyAverageByComponent(component.get(), pageable);
    }


    @RequestMapping(value = "/{id}/moisture/daily/average", method = RequestMethod.GET)
    public Page<MoistureData> getMoistureDataDailyAverage(
        @PathVariable("id") Integer id,
        @PageableDefault(sort = {"timestamp"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {

        Optional<AbstractRPiComponent> component = rPiComponentRepository.findById(id);
        if (!component.isPresent())
            throw new RPiComponentNotFoundException(componentNotFoundException);
        return moistureDataRepository.findDailyAverageByComponent(component.get(), pageable);
    }


    @RequestMapping(value = "/{id}/moisture/daily/high", method = RequestMethod.GET)
    public Page<MoistureData> getMoistureDataDailyHigh(
        @PathVariable("id") Integer id,
        @PageableDefault(sort = {"timestamp"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {

        Optional<AbstractRPiComponent> component = rPiComponentRepository.findById(id);
        if (!component.isPresent())
            throw new RPiComponentNotFoundException(componentNotFoundException);
        return moistureDataRepository.findDailyHighByComponent(component.get(), pageable);
    }


    @RequestMapping(value = "/{id}/moisture/daily/low", method = RequestMethod.GET)
    public Page<MoistureData> getMoistureDataDailyLow(
        @PathVariable("id") Integer id,
        @PageableDefault(sort = {"timestamp"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {

        Optional<AbstractRPiComponent> component = rPiComponentRepository.findById(id);
        if (!component.isPresent())
            throw new RPiComponentNotFoundException(componentNotFoundException);
        return moistureDataRepository.findDailyLowByComponent(component.get(), pageable);
    }


    @RequestMapping(value = "/{id}/moisture/hourly/average", method = RequestMethod.GET)
    public Page<MoistureData> getMoistureDataHourlyAverage(
        @PathVariable("id") Integer id,
        @PageableDefault(sort = {"timestamp"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {

        Optional<AbstractRPiComponent> component = rPiComponentRepository.findById(id);
        if (!component.isPresent())
            throw new RPiComponentNotFoundException(componentNotFoundException);
        return moistureDataRepository.findHourlyAverageByComponent(component.get(), pageable);
    }


    @RequestMapping(value = "/{id}/moisture/hourly/high", method = RequestMethod.GET)
    public Page<MoistureData> getMoistureDataHourlyHigh(
        @PathVariable("id") Integer id,
        @PageableDefault(sort = {"timestamp"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {

        Optional<AbstractRPiComponent> component = rPiComponentRepository.findById(id);
        if (!component.isPresent())
            throw new RPiComponentNotFoundException(componentNotFoundException);
        return moistureDataRepository.findHourlyHighByComponent(component.get(), pageable);
    }


    @RequestMapping(value = "/{id}/moisture/hourly/low", method = RequestMethod.GET)
    public Page<MoistureData> getMoistureDataHourlyLow(
        @PathVariable("id") Integer id,
        @PageableDefault(sort = {"timestamp"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {

        Optional<AbstractRPiComponent> component = rPiComponentRepository.findById(id);
        if (!component.isPresent())
            throw new RPiComponentNotFoundException(componentNotFoundException);
        return moistureDataRepository.findHourlyLowByComponent(component.get(), pageable);
    }

}
