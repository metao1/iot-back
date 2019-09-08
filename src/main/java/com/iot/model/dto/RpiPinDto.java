package com.iot.model.dto;

import com.iot.model.rpipin.RPiPinDirection;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

public class RpiPinDto implements Serializable {

    private Integer id;

    private String description;

    private Integer number;

    @Enumerated(EnumType.STRING)
    private RPiPinDirection direction;

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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public RPiPinDirection getDirection() {
        return direction;
    }

    public void setDirection(RPiPinDirection direction) {
        this.direction = direction;
    }
}
