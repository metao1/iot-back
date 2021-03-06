package com.iot.web.controller;

import com.iot.model.rpicomponent.AbstractRPiComponent;
import com.iot.model.rpicomponent.component.TemperatureSensor;
import com.iot.model.rpicomponent.data.TemperatureData;
import com.iot.model.rpicomponent.exception.RPiComponentNotFoundException;
import com.iot.repository.data.TemperatureDataRepository;
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

@RestController
@RequestMapping(value = "/api/component")
public class TemperatureDataController {

    @Autowired
    private TemperatureDataRepository temperatureDataRepository;

    @Autowired
    private RPiComponentRepository rPiComponentRepository;

    @Value("${exception.rpi-component-not-found}")
    private String componentNotFoundException;

    @RequestMapping(value = "/{id}/temperature", method = RequestMethod.GET)
    public Page<TemperatureData> getTemperatureDataPage(
        @PathVariable("id") Integer id,
        @PageableDefault(sort = {"timestamp"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {

        AbstractRPiComponent component = validateComponent(id);
        return temperatureDataRepository.findAllByComponent(component, pageable);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}/temperature", method = RequestMethod.POST)
    public void postTemperatureData(@PathVariable("id") Integer id,
                                    @RequestBody TemperatureData data) {
        AbstractRPiComponent component = validateComponent(id);
        data.setComponent((TemperatureSensor) component);
        temperatureDataRepository.save(data);
    }

    @GetMapping(value = "/{id}/temperature/monthly/average")
    public Page<TemperatureData> getTemperatureDataMonthlyAverage(
        @PathVariable("id") Integer id,
        @PageableDefault(sort = {"timestamp"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {
        AbstractRPiComponent component = validateComponent(id);
        return temperatureDataRepository.findMonthlyAverageByComponent(component, pageable);
    }

    @GetMapping(value = "/{id}/temperature/daily/average")
    public Page<TemperatureData> getTemperatureDataDailyAverage(
        @PathVariable("id") Integer id,
        @PageableDefault(sort = {"timestamp"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {
        AbstractRPiComponent component = validateComponent(id);
        return temperatureDataRepository.findDailyAverageByComponent(component, pageable);
    }

    @RequestMapping(value = "/{id}/temperature/daily/high", method = RequestMethod.GET)
    public Page<TemperatureData> getTemperatureDataDailyHigh(
        @PathVariable("id") Integer id,
        @PageableDefault(sort = {"timestamp"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {

        AbstractRPiComponent component = validateComponent(id);
        return temperatureDataRepository.findDailyHighByComponent(component, pageable);
    }

    @RequestMapping(value = "/{id}/temperature/daily/low", method = RequestMethod.GET)
    public Page<TemperatureData> getTemperatureDataDailyLow(
        @PathVariable("id") Integer id,
        @PageableDefault(sort = {"timestamp"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {

        AbstractRPiComponent component = validateComponent(id);
        return temperatureDataRepository.findDailyLowByComponent(component, pageable);
    }

    @RequestMapping(value = "/{id}/temperature/hourly/average", method = RequestMethod.GET)
    public Page<TemperatureData> getTemperatureDataHourlyAverage(
        @PathVariable("id") Integer id,
        @PageableDefault(sort = {"timestamp"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {
        AbstractRPiComponent component = validateComponent(id);
        return temperatureDataRepository.findHourlyAverageByComponent(component, pageable);
    }

    @RequestMapping(value = "/{id}/temperature/hourly/high", method = RequestMethod.GET)
    public Page<TemperatureData> getTemperatureDataHourlyHigh(
        @PathVariable("id") Integer id,
        @PageableDefault(sort = {"timestamp"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {

        AbstractRPiComponent component = validateComponent(id);
        return temperatureDataRepository.findHourlyHighByComponent(component, pageable);
    }

    @RequestMapping(value = "/{id}/temperature/monthly/low", method = RequestMethod.GET)
    public Page<TemperatureData> getTemperatureDataMonthlyLow(
        @PathVariable("id") Integer id,
        @PageableDefault(sort = {"timestamp"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {

        AbstractRPiComponent component = validateComponent(id);
        return temperatureDataRepository.findMonthlyLowByComponent(component, pageable);

    }

    @RequestMapping(value = "/{id}/temperature/hourly/low", method = RequestMethod.GET)
    public Page<TemperatureData> getTemperatureDataHourlyLow(
        @PathVariable("id") Integer id,
        @PageableDefault(sort = {"timestamp"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {

        AbstractRPiComponent component = validateComponent(id);
        return temperatureDataRepository.findHourlyLowByComponent(component, pageable);

    }

    private AbstractRPiComponent validateComponent(Integer id) {
        Optional<AbstractRPiComponent> component = rPiComponentRepository.findById(id);
        if (!component.isPresent())
            throw new RPiComponentNotFoundException(componentNotFoundException);
        else
            return component.get();
    }

}
