package com.gro.model.rpicomponent.component;

import com.gro.model.rpicomponent.AbstractRPiComponent;
import com.gro.model.rpicomponent.RPiComponentType;
import com.gro.model.rpicomponent.preferences.RelayPreferences;

import javax.persistence.*;

@Entity
public class Relay extends AbstractRPiComponent {
    private static final long serialVersionUID = 7384389994862545570L;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private RelayPreferences preferences;

    public Relay() {
        this.type = RPiComponentType.RELAY;
    }

    public RelayPreferences getPreferences() {
        return preferences;
    }

    public void setPreferences(RelayPreferences preferences) {
        this.preferences = preferences;
    }

}
