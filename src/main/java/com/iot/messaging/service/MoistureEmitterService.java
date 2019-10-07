package com.iot.messaging.service;

import com.iot.model.MoistureDTO;
import com.iot.web.service.ObjectSseEmitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@MessageEndpoint
public class MoistureEmitterService {

    @Value("${sse.event.moisture}")
    private String eventName;

    @Autowired
    private ObjectSseEmitterService objectSseEmitterService;

    @ServiceActivator(inputChannel = "moistureServiceChannel")
    public void process(Message<MoistureDTO> message) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("event", eventName);
        obj.put("payload", message.getPayload());
        this.objectSseEmitterService.emit(obj);
    }

}
