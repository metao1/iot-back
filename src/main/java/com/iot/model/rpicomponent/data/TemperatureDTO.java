package com.iot.model.rpicomponent.data;

import com.iot.model.rpicomponent.AbstractDataDTO;

import java.util.Date;

public class TemperatureDTO extends AbstractDataDTO {

    private Double temperature;

    public TemperatureDTO() {
    }

    public TemperatureDTO(Date timestamp, Double temperature) {
        super(timestamp);
        this.temperature = temperature;
    }

    public TemperatureDTO(Date timestamp, Double temperature, Integer componentId) {
        super(timestamp, componentId);
        this.temperature = temperature;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

}
