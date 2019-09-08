package com.iot.messaging.service;

import com.iot.model.notification.Notification;
import com.iot.model.rpicomponent.AbstractRPiComponent;
import com.iot.repository.NotificationRepository;
import com.iot.repository.rpicomponent.RPiComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationPersistenceService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private RPiComponentRepository rPiComponentRepository;

    @ServiceActivator(inputChannel = "notificationPersistenceChannel",
            outputChannel = "notificationNotifyChannel")
    public Message<Notification> process(Message<Notification> message) {
        Notification notification = message.getPayload();
        Optional<AbstractRPiComponent> component = rPiComponentRepository.findById(notification.getComponent().getId());
        notification.setComponent(component.get());
        Notification result = this.notificationRepository.save(notification);
        return MessageBuilder.createMessage(result, message.getHeaders());
    }

}
