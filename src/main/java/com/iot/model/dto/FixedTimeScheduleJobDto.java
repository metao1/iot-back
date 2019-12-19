package com.iot.model.dto;

import com.iot.model.rpicomponent.AbstractRPiComponent;

import java.io.Serializable;
import java.util.Date;

public class FixedTimeScheduleJobDto implements Serializable {

    private Integer id;

    private Date time;

    private AbstractRPiComponent component;

    private Boolean enabled;

    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public AbstractRPiComponent getComponent() {
        return component;
    }

    public void setComponent(AbstractRPiComponent component) {
        this.component = component;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
