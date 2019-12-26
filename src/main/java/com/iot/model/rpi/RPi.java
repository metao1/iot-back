package com.iot.model.rpi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iot.model.rpicomponent.AbstractRPiComponent;
import com.iot.model.rpipin.RPiPin;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
public class RPi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RPiType type = RPiType.RPI_3_MODEL_B;

    @NotNull
    private String ip;

    @NotNull
    private Integer port;

    @NotNull
    private String mqttUsername;

    @NotNull
    private String mqttPassword;

    private String imageUrl;

    public RPi() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RPiType getType() {
        return type;
    }

    public void setType(RPiType type) {
        this.type = type;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getMqttUsername() {
        return mqttUsername;
    }

    public void setMqttUsername(String mqttUsername) {
        this.mqttUsername = mqttUsername;
    }

    public String getMqttPassword() {
        return mqttPassword;
    }

    public void setMqttPassword(String mqttPassword) {
        this.mqttPassword = mqttPassword;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
