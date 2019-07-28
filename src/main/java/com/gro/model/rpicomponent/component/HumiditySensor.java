package com.gro.model.rpicomponent.component;

import com.gro.model.rpicomponent.AbstractRPiComponent;
import com.gro.model.rpicomponent.RPiComponentType;
import com.gro.model.rpicomponent.preferences.HumiditySensorPreferences;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class HumiditySensor extends AbstractRPiComponent {
    private static final long serialVersionUID = -1697372017855638391L;

    @OneToOne(cascade = CascadeType.ALL)
    private HumiditySensorPreferences preferences;

    public HumiditySensor() {
        this.type = RPiComponentType.HUMIDITY;
    }

    public HumiditySensorPreferences getPreferences() {
        return preferences;
    }

    public void setPreferences(HumiditySensorPreferences preferences) {
        this.preferences = preferences;
    }

}
