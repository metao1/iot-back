package com.gro.model.dto;

import com.gro.model.rpicomponent.RPiComponentType;

import java.io.Serializable;

import static com.gro.model.rpicomponent.RPiComponentType.TEMPERATURE;

public class RPiComponentDTO implements Serializable {

    private static final long serialVersionUID = -9072676419360409759L;

    private int rpiId;
    private String alias;
    private int pinNumber;
    protected RPiComponentType type = TEMPERATURE;

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

    public int getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
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
}
