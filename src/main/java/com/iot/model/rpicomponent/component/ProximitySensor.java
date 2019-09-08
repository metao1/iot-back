package com.iot.model.rpicomponent.component;

import com.iot.model.rpicomponent.AbstractRPiComponent;
import com.iot.model.rpicomponent.RPiComponentType;
import com.iot.model.rpicomponent.preferences.ProximitySensorPreferences;

import javax.persistence.*;

@Entity
public class ProximitySensor extends AbstractRPiComponent {
    private static final long serialVersionUID = -4988765402801140717L;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ProximitySensorPreferences preferences;

    public ProximitySensor() {
        this.type = RPiComponentType.PROXIMITY;
    }

    public ProximitySensorPreferences getPreferences() {
        return preferences;
    }

    public void setPreferences(ProximitySensorPreferences preferences) {
        this.preferences = preferences;
    }

}
