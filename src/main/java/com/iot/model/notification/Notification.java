package com.iot.model.notification;

import com.iot.model.rpicomponent.AbstractRPiComponent;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date timestamp;

    @NotNull
    private String message;

    @ManyToOne
    @NotNull
    private AbstractRPiComponent component;

    @NotNull
    private Boolean isRead = false;


    public Notification() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AbstractRPiComponent getComponent() {
        return component;
    }

    public void setComponent(AbstractRPiComponent component) {
        this.component = component;
    }

    public Boolean isRead() {
        return isRead;
    }

    public void setRead(Boolean isRead) {
        this.isRead = isRead;
    }

}
