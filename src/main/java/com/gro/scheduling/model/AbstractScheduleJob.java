package com.gro.scheduling.model;

import com.gro.model.rpicomponent.AbstractRPiComponent;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "schedule_job")
public abstract class AbstractScheduleJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @NotNull
    private AbstractRPiComponent component;

    @NotNull
    private Boolean enabled;

    @Column(name = "type", insertable = false, updatable = false)
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AbstractRPiComponent getComponent() {
        return component;
    }

    public void setComponent(AbstractRPiComponent component) {
        this.component = component;
    }

    public Boolean isEnabled() {
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
