package com.iot.model.dto;

import com.iot.model.rpicomponent.RPiComponentType;

import java.io.Serializable;

public class RPiComponentDTO implements Serializable {

    private static final long serialVersionUID = -9072676419360409759L;

    private int rpiId;
    private String alias;
    private RPiComponentType type;
    private RpiPinDto pin;

    public RPiComponentDTO() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public RPiComponentType getType() {
        return type;
    }

    public void setType(RPiComponentType type) {
        this.type = type;
    }

    public int getRpiId() {
        return rpiId;
    }

    public void setRpiId(int rpiId) {
        this.rpiId = rpiId;
    }

    public RpiPinDto getPin() {
        return pin;
    }

    public void setPin(RpiPinDto pin) {
        this.pin = pin;
    }
}
