package com.gro.messaging.service;

import com.gro.model.notification.Notification;
import com.gro.model.rpicomponent.AbstractRPiComponent;
import com.gro.repository.rpicomponent.RPiComponentRepository;
import com.gro.web.service.ObjectSseEmitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
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
