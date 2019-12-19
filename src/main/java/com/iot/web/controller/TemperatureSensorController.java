package com.iot.web.controller;

import com.iot.model.rpicomponent.component.TemperatureSensor;
import com.iot.repository.rpicomponent.TemperatureSensorRepository;
import com.iot.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/component/temperature")
public class TemperatureSensorController extends AbstractRestController<TemperatureSensor, TemperatureSensor, Integer> {

    @Autowired
    public TemperatureSensorController(TemperatureSensorRepository repository) {
        super(repository);
    }

    @Override
    public WebUtils<TemperatureSensor> getWebUtils() {
        return new WebUtils<>(TemperatureSensor.class);
    }
}
