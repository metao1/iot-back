package com.iot.messaging.service;

import com.iot.model.rpicomponent.data.HumidityDTO;
import com.iot.web.service.ObjectSseEmitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

import java.util.HashMap;
import java.util.Map;

@MessageEndpoint
public class HumidityEmitterService {

    @Value("${sse.event.humidity}")
    private String eventName;

    @Autowired
    private ObjectSseEmitterService objectSseEmitterService;

    @ServiceActivator(inputChannel = "humidityServiceChannel")
    public void process(Message<HumidityDTO> message) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("event", eventName);
        obj.put("payload", message.getPayload());
        objectSseEmitterService.emit(obj);
    }

}
