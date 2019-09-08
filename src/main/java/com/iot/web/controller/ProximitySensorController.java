package com.iot.web.controller;

import com.iot.model.rpicomponent.component.ProximitySensor;
import com.iot.repository.rpicomponent.ProximitySensorRepository;
import com.iot.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/component/proximity")
public class ProximitySensorController extends AbstractRestController<ProximitySensor, ProximitySensor, Integer> {

    @Autowired
    public ProximitySensorController(ProximitySensorRepository repository) {
        super(repository);
    }

    @Override
    public WebUtils<ProximitySensor> getWebUtils() {
        return null;
    }
}
