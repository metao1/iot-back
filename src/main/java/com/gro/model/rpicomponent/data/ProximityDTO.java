package com.gro.model.rpicomponent.data;

import com.gro.model.rpicomponent.AbstractDataDTO;

import java.util.Date;

public class ProximityDTO extends AbstractDataDTO {

    private Double distance;

    public ProximityDTO() {
    }

    public ProximityDTO(Date timestamp, Double distance) {
        super(timestamp);
        this.distance = distance;
    }

    public ProximityDTO(Date timestamp, Double distance, Integer componentId) {
        super(timestamp, componentId);
        this.distance = distance;
    }

    public Double getDistance() {
        return this.distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

}
