package com.gro.model.rpicomponent.component;

import com.gro.model.rpicomponent.AbstractRPiComponent;
import com.gro.model.rpicomponent.RPiComponentType;
import com.gro.model.rpicomponent.preferences.MoistureSensorPreferences;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class MoistureSensor extends AbstractRPiComponent {
    private static final long serialVersionUID = -8458254453273797900L;

    @OneToOne(cascade = CascadeType.ALL)
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
