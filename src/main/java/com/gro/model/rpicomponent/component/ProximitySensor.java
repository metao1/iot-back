package com.gro.model.rpicomponent.component;

import com.gro.model.rpicomponent.AbstractRPiComponent;
import com.gro.model.rpicomponent.RPiComponentType;
import com.gro.model.rpicomponent.preferences.ProximitySensorPreferences;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class ProximitySensor extends AbstractRPiComponent {
    private static final long serialVersionUID = -4988765402801140717L;

    @OneToOne(cascade = CascadeType.ALL)
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
