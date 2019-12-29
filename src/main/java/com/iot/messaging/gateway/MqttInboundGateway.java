package com.iot.messaging.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway
public interface MqttInboundGateway {

    @Gateway(requestChannel = "mqttInboundChannel")
    void receive(Message<String> message);
}
