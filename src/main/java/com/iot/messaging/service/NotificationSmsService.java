package com.iot.messaging.service;

import com.iot.model.notification.Notification;
import com.iot.repository.rpicomponent.RPiComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class NotificationSmsService {

    @Autowired
    private TextMessageService textMessageService;

    @Autowired
    private RPiComponentRepository rPiComponentRepository;

    @ServiceActivator(inputChannel = "notificationNotifyChannel")
    public void process(Message<Notification> message) {
        System.out.println("NOTIFICATION : Sensing SMS");
        // construct more meaningful message with Notification class is complete
        //textMessageService.sendSms(message.getPayload().getMessage());

    }

}
