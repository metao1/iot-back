package com.gro.web.controller;

import com.gro.model.rpicomponent.component.MoistureSensor;
import com.gro.repository.rpicomponent.MoistureSensorRepository;
import com.gro.utils.WebUtils;
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
