package com.iot.model.dto;

import com.iot.model.rpi.RPiType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class RPiDTO implements Serializable {

    private Integer id;

    @NotNull
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RPiType type = RPiType.RPI_3_MODEL_B;

    private String ip;

    private Integer port;

    private String mqttUsername;

    private String mqttPassword;

    private String imageUrl;


    public RPiDTO() {

    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public RPiType getType() {
        return type;
    }

    public String getIp() {
        return ip;
    }

    public Integer getPort() {
        return port;
    }

    public String getMqttUsername() {
        return mqttUsername;
    }

    public String getMqttPassword() {
        return mqttPassword;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(RPiType type) {
        this.type = type;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void setMqttUsername(String mqttUsername) {
        this.mqttUsername = mqttUsername;
    }

    public void setMqttPassword(String mqttPassword) {
        this.mqttPassword = mqttPassword;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
