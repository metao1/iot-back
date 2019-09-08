package com.iot.model.rpicomponent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.iot.model.rpi.RPi;
import com.iot.model.rpipin.RPiPin;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractRPiComponent implements Serializable {

    private static final long serialVersionUID = -9072676419360409759L;
    @JsonPropertyOrder("0")
    @JsonProperty("type")
    @Column(name = "type")
    protected RPiComponentType type;

    @Id
    @GenericGenerator(name = "RPiComponentId", strategy = "com.iot.model.rpicomponent.RPiComponentIdGenerator")
    @GeneratedValue(generator = "RPiComponentId")
    private Integer id;

    @NotNull
    private String alias;

    @OneToMany(mappedBy = "component")
    @JsonIgnoreProperties(value = {"component", "rpi"})
    private List<RPiPin> pins;

    @ManyToOne
    @NotNull
    @JsonIgnoreProperties(value = {"pins", "components"})
    private RPi rpi;

    public AbstractRPiComponent() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public List<RPiPin> getPins() {
        return pins;
    }

    public void setPins(List<RPiPin> pins) {
        this.pins = pins;
    }

    public RPi getRpi() {
        return rpi;
    }

    public void setRpi(RPi rpi) {
        this.rpi = rpi;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, alias);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (!(obj instanceof AbstractRPiComponent))
            return false;

        AbstractRPiComponent comp = (AbstractRPiComponent) obj;

        return Objects.equals(this.id, comp.id) &&
            Objects.equals(this.alias, comp.alias);

    }

}
