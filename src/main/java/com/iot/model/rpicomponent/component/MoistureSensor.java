package com.iot.model.rpicomponent.component;

import com.iot.model.rpicomponent.AbstractRPiComponent;
import com.iot.model.rpicomponent.RPiComponentType;
import com.iot.model.rpicomponent.preferences.MoistureSensorPreferences;

import javax.persistence.*;

@Entity
public class MoistureSensor extends AbstractRPiComponent {
    private static final long serialVersionUID = -8458254453273797900L;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private MoistureSensorPreferences preferences;

    public MoistureSensor() {
        this.type = RPiComponentType.MOISTURE;
    }

    public MoistureSensorPreferences getPreferences() {
        return preferences;
    }

    public void setPreferences(MoistureSensorPreferences preferences) {
        this.preferences = preferences;
    }

}
