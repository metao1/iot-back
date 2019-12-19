package com.iot.web.controller;

import com.iot.model.rpicomponent.component.MoistureSensor;
import com.iot.repository.rpicomponent.MoistureSensorRepository;
import com.iot.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/component/moisture")
public class MoistureSensorController extends AbstractRestController<MoistureSensor,MoistureSensor, Integer> {

    @Autowired
    public MoistureSensorController(MoistureSensorRepository repository) {
        super(repository);
    }

    @Override
    public WebUtils<MoistureSensor> getWebUtils() {
        return null;
    }
}
