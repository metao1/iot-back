package com.gro.model.rpicomponent.preferences;

import com.gro.model.rpicomponent.AbstractSensorPreferences;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Entity
public class TemperatureSensorPreferences extends AbstractSensorPreferences {

    @Enumerated(EnumType.STRING)
    @NotNull
    private TemperatureUnit temperatureUnit = TemperatureUnit.CELSIUS;

    public TemperatureUnit getTemperatureUnit() {
        return temperatureUnit;
    }

    public void setTemperatureUnit(TemperatureUnit temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

}
