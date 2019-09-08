package com.iot.model.rpicomponent.component;

import com.iot.model.rpicomponent.AbstractRPiComponent;
import com.iot.model.rpicomponent.RPiComponentType;
import com.iot.model.rpicomponent.preferences.TemperatureSensorPreferences;

import javax.persistence.*;

@Entity
public class TemperatureSensor extends AbstractRPiComponent {
    private static final long serialVersionUID = -2861056769929307256L;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private TemperatureSensorPreferences preferences;

    public TemperatureSensor() {
        this.type = RPiComponentType.TEMPERATURE;
    }

    public TemperatureSensorPreferences getPreferences() {
        return preferences;
    }

    public void setPreferences(TemperatureSensorPreferences preferences) {
        this.preferences = preferences;
    }

}
