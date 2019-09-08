package com.iot.messaging.service;

import com.iot.model.notification.Notification;
import com.iot.model.rpicomponent.AbstractRPiComponent;
import com.iot.repository.rpicomponent.RPiComponentRepository;
import com.iot.web.service.ObjectSseEmitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@MessageEndpoint
public class NotificationEmitterService {

    @Value("${sse.event.notification}")
    private String eventName;

    @Autowired
    private ObjectSseEmitterService objectSseEmitterService;

    @Autowired
    private RPiComponentRepository rPiComponentRepository;

    @ServiceActivator(inputChannel = "notificationNotifyChannel")
    public void process(Message<Notification> message) {
        Notification notification = message.getPayload();
        Optional<AbstractRPiComponent> component = rPiComponentRepository.findById(notification.getComponent().getId());
        notification.setComponent(component.get());
        Map<String, Object> obj = new HashMap<>();
        obj.put("event", eventName);
        obj.put("payload", notification);
        objectSseEmitterService.emit(obj);
    }

}
