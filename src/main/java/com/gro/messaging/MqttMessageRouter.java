package com.gro.messaging;

import org.springframework.messaging.handler.annotation.Header;

public class MqttMessageRouter {

    public String route(@Header("mqtt_topic") String topic) {
        String route = null;
        switch (topic) {
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
