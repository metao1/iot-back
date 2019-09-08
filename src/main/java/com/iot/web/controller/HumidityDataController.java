package com.iot.web.controller;

import com.iot.model.rpicomponent.AbstractRPiComponent;
import com.iot.model.rpicomponent.data.HumidityData;
import com.iot.model.rpicomponent.exception.RPiComponentNotFoundException;
import com.iot.repository.data.HumidityDataRepository;
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
public class HumidityDataController {

    @Autowired
    private RPiComponentRepository rPiComponentRepository;

    @Autowired
    private HumidityDataRepository humidityDataRepository;

    @Value("${exception.rpi-component-not-found}")
    private String componentNotFoundException;


    @GetMapping(value = "/{id}/humidity")
    public Page<HumidityData> getHumidityDataPage(
            @PathVariable("id") Integer id,
            @PageableDefault(sort = {"timestamp"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {

       Optional< AbstractRPiComponent> component = rPiComponentRepository.findById(id);
        if (!component.isPresent())
            throw new RPiComponentNotFoundException(componentNotFoundException);
        return humidityDataRepository.findAllByComponent(component.get(), pageable);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/{id}/humidity")
    public void postHumidityData(@RequestBody HumidityData data) {
        humidityDataRepository.save(data);
    }

    @RequestMapping(value = "/{id}/humidity/monthly/average", method = RequestMethod.GET)
    public Page<HumidityData> getHumidityDataMonthlyAverage(
            @PathVariable("id") Integer id,
            @PageableDefault(sort = {"timestamp"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {

       Optional< AbstractRPiComponent> component = rPiComponentRepository.findById(id);
        if (!component.isPresent())
            throw new RPiComponentNotFoundException(componentNotFoundException);
        return humidityDataRepository.findMonthlyAverageByComponent(component.get(), pageable);
    }


    @RequestMapping(value = "/{id}/humidity/daily/average", method = RequestMethod.GET)
    public Page<HumidityData> getHumidityDataDailyAverage(
            @PathVariable("id") Integer id,
            @PageableDefault(sort = {"timestamp"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {

       Optional< AbstractRPiComponent> component = rPiComponentRepository.findById(id);
        if (!component.isPresent())
            throw new RPiComponentNotFoundException(componentNotFoundException);
        return humidityDataRepository.findDailyAverageByComponent(component.get(), pageable);
    }


    @RequestMapping(value = "/{id}/humidity/daily/high", method = RequestMethod.GET)
    public Page<HumidityData> getHumidityDataDailyHigh(
            @PathVariable("id") Integer id,
            @PageableDefault(sort = {"timestamp"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {

       Optional< AbstractRPiComponent> component = rPiComponentRepository.findById(id);
        if (!component.isPresent())
            throw new RPiComponentNotFoundException(componentNotFoundException);
        return humidityDataRepository.findDailyHighByComponent(component.get(), pageable);
    }


    @RequestMapping(value = "/{id}/humidity/daily/low", method = RequestMethod.GET)
    public Page<HumidityData> getHumidityDataDailyLow(
            @PathVariable("id") Integer id,
            @PageableDefault(sort = {"timestamp"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {

       Optional< AbstractRPiComponent> component = rPiComponentRepository.findById(id);
        if (!component.isPresent())
            throw new RPiComponentNotFoundException(componentNotFoundException);
        return humidityDataRepository.findDailyLowByComponent(component.get(), pageable);
    }


    @RequestMapping(value = "/{id}/humidity/hourly/average", method = RequestMethod.GET)
    public Page<HumidityData> getHumidityDataHourlyAverage(
            @PathVariable("id") Integer id,
            @PageableDefault(sort = {"timestamp"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {

       Optional< AbstractRPiComponent> component = rPiComponentRepository.findById(id);
        if (!component.isPresent())
            throw new RPiComponentNotFoundException(componentNotFoundException);
        return humidityDataRepository.findHourlyAverageByComponent(component.get(), pageable);
    }


    @RequestMapping(value = "/{id}/humidity/hourly/high", method = RequestMethod.GET)
    public Page<HumidityData> getHumidityDataHourlyHigh(
            @PathVariable("id") Integer id,
            @PageableDefault(sort = {"timestamp"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {

       Optional< AbstractRPiComponent> component = rPiComponentRepository.findById(id);
        if (!component.isPresent())
            throw new RPiComponentNotFoundException(componentNotFoundException);
        return humidityDataRepository.findHourlyHighByComponent(component.get(), pageable);
    }


    @RequestMapping(value = "/{id}/humidity/hourly/low", method = RequestMethod.GET)
    public Page<HumidityData> getHumidityDataHourlyLow(
            @PathVariable("id") Integer id,
            @PageableDefault(sort = {"timestamp"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {

       Optional< AbstractRPiComponent> component = rPiComponentRepository.findById(id);
        if (!component.isPresent())
            throw new RPiComponentNotFoundException(componentNotFoundException);
        return humidityDataRepository.findHourlyLowByComponent(component.get(), pageable);
    }

}
