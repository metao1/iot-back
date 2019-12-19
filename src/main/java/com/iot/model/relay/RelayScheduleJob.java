package com.iot.model.relay;

import com.iot.model.rpicomponent.data.RelayState;
import com.iot.scheduling.model.FixedTimeScheduleJob;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Entity
public class RelayScheduleJob extends FixedTimeScheduleJob {

    @NotNull
    @Enumerated(EnumType.STRING)
    private RelayState state;

    public RelayScheduleJob() {
    }

    public RelayState getState() {
        return state;
    }

    public void setState(RelayState state) {
        this.state = state;
    }

}
