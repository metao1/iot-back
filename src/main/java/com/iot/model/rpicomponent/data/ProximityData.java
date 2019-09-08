package com.iot.model.rpicomponent.data;

import com.iot.model.rpicomponent.AbstractRPiComponentData;
import com.iot.model.rpicomponent.component.ProximitySensor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class ProximityData extends AbstractRPiComponentData {

    @NotNull
    private Double proximity;

    @ManyToOne
    private ProximitySensor component;

    public ProximityData() {
    }

    public Double getProximity() {
        return proximity;
    }

    public void setProximity(Double proximity) {
        this.proximity = proximity;
    }

    public ProximitySensor getComponent() {
        return component;
    }

    public void setComponent(ProximitySensor component) {
        this.component = component;
    }


}
