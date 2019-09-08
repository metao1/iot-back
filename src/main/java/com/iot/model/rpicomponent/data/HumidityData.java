package com.iot.model.rpicomponent.data;

import com.iot.model.rpicomponent.AbstractRPiComponentData;
import com.iot.model.rpicomponent.component.HumiditySensor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class HumidityData extends AbstractRPiComponentData {

    @NotNull
    public Double humidity;

    @ManyToOne
    private HumiditySensor component;

    public HumidityData() {
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public HumiditySensor getComponent() {
        return component;
    }

    public void setComponent(HumiditySensor component) {
        this.component = component;
    }

}
