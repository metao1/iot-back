package com.gro.model.rpipin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gro.model.rpi.RPi;
import com.gro.model.rpicomponent.AbstractRPiComponent;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class RPiPin implements Serializable {

    private static final long serialVersionUID = -2178663258584172348L;

    @Id
    @GeneratedValue(
        strategy= GenerationType.AUTO,
        generator="native"
    )
    @GenericGenerator(
        name = "native",
        strategy = "native"
    )
    private Integer id;

    @NotNull
    private String description;

    @NotNull
    private Integer physicalPin;

    @NotNull
    private Boolean usable;

    @Enumerated(EnumType.STRING)
    private RPiPinDirection direction;

    @ManyToOne
    @JsonIgnoreProperties(value = {"pins"})
    private AbstractRPiComponent component;

    @ManyToOne
    @JsonIgnoreProperties(value = {"pins", "components"})
    private RPi rpi;

    public RPiPin() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPhysicalPin() {
        return physicalPin;
    }

    public void setPhysicalPin(Integer physicalPin) {
        this.physicalPin = physicalPin;
    }

    public Boolean getUsable() {
        return usable;
    }

    public void setUsable(Boolean usable) {
        this.usable = usable;
    }

    public RPiPinDirection getDirection() {
        return direction;
    }

    public void setDirection(RPiPinDirection direction) {
        this.direction = direction;
    }

    public AbstractRPiComponent getComponent() {
        return component;
    }

    public void setComponent(AbstractRPiComponent component) {
        this.component = component;
    }

    public RPi getRpi() {
        return rpi;
    }

    public void setRpi(RPi rpi) {
        this.rpi = rpi;
    }

}
