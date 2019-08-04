package com.gro.model.rpicomponent.component;

import com.gro.model.rpicomponent.AbstractRPiComponent;
import com.gro.model.rpicomponent.RPiComponentType;
import com.gro.model.rpicomponent.preferences.TemperatureSensorPreferences;

import javax.persistence.*;

@Entity
public class TemperatureSensor extends AbstractRPiComponent {
    private static final long serialVersionUID = -2861056769929307256L;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
