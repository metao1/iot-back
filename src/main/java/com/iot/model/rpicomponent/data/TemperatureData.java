package com.iot.model.rpicomponent.data;

import com.iot.model.rpicomponent.AbstractRPiComponentData;
import com.iot.model.rpicomponent.component.TemperatureSensor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class TemperatureData extends AbstractRPiComponentData {

    @NotNull
    private Double temperature;

    @ManyToOne
    private TemperatureSensor component;

    public TemperatureData() {
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public TemperatureSensor getComponent() {
        return component;
    }

    public void setComponent(TemperatureSensor component) {
        this.component = component;
    }

}
