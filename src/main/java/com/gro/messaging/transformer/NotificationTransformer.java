package com.gro.messaging.transformer;

import com.gro.model.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@MessageEndpoint
public class NotificationTransformer {

    @Autowired
    private Jackson2JsonObjectMapper jackson2JsonObjectMapper;

    @Transformer(inputChannel = "notificationTransformerChannel",
            outputChannel = "notificationPersistenceChannel")
    public Message<Notification> transform(Message<String> message) throws Exception {
        String payload = message.getPayload();
        Notification data = jackson2JsonObjectMapper.fromJson(payload, Notification.class);
        return MessageBuilder.createMessage(data, message.getHeaders());
    }

}
