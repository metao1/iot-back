package com.gro.model;

import com.gro.model.rpicomponent.AbstractDataDTO;

import java.util.Date;

public class MoistureDTO extends AbstractDataDTO {

    private Double moisture;

    public MoistureDTO() {
    }

    public MoistureDTO(Date timestamp, Double moisture) {
        super(timestamp);
        this.moisture = moisture;
    }

    public MoistureDTO(Date timestamp, Double moisture, Integer componentId) {
        super(timestamp, componentId);
        this.moisture = moisture;
    }

    public Double getMoisture() {
        return moisture;
    }

    public void setMoisture(Double moisture) {
        this.moisture = moisture;
    }

}
