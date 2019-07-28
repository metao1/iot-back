package com.gro.web.controller;

import com.gro.model.rpicomponent.component.HumiditySensor;
import com.gro.repository.rpicomponent.HumiditySensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/component/humidity")
public class HumiditySensorController extends AbstractRestController<HumiditySensor, Integer> {

    @Autowired
    public HumiditySensorController(HumiditySensorRepository repository) {
        super(repository);
    }

}
