package com.iot.messaging;

import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Router;
import org.springframework.messaging.Message;

@Configuration
public class MqttMessageRouter {

    @Router(inputChannel = "mqttInboundChannel")
    public String route(Message<String> topic) {
        String route = null;
        if (topic == null || topic.getHeaders().isEmpty() || !topic.getHeaders().containsKey("mqtt_receivedTopic")) {
            return null;
        }
        System.out.println(topic.getHeaders().containsKey("mqtt_receivedTopic"));
        switch (topic.getHeaders().get("mqtt_receivedTopic").toString().toUpperCase()) {
            case "TEMPERATURE":
                route = "temperatureTransformerChannel";
                break;
            case "HUMIDITY":
                route = "humidityTransformerChannel";
                break;
            case "TEMPERATURE_HUMIDITY":
                route = "temperatureHumidityTransformerChannel";
                break;
            case "NOTIFICATION.Alert":
                route = "notificationTransformerChannel";
                break;
            case "PROXIMITY.State":
                route = "proximityTransformerChannel";
                break;
            case "RELAY.State":
                route = "relayTransformerChannel";
                break;
            case "MOISTURE.State":
                route = "moistureTransformerChannel";
                break;
        }
        return route;
    }

}
