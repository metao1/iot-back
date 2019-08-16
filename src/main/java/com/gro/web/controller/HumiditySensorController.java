package com.gro.web.controller;

import com.gro.model.rpicomponent.component.HumiditySensor;
import com.gro.repository.rpicomponent.HumiditySensorRepository;
import com.gro.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/component/humidity")
public class HumiditySensorController extends AbstractRestController<HumiditySensor, HumiditySensor, Integer> {

    @Autowired
    public HumiditySensorController(HumiditySensorRepository repository) {
        super(repository);
    }

    @Override
    public WebUtils<HumiditySensor> getWebUtils() {
        return null;
    }
}
