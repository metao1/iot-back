package com.gro.security.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "authority")
@org.hibernate.annotations.Cache(
    usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE
)
public class Authority implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull
    @Size(
        max = 50
    )
    @Id
    @Column(
        name = "name",
        length = 50
    )
    private String name;

    public Authority() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else {
            return o instanceof Authority && Objects.equals(this.name, ((Authority) o).name);
        }
    }

    public int hashCode() {
        return Objects.hashCode(this.name);
    }

    public String toString() {
        return "Authority{name='" + this.name + '\'' + "}";
    }
}
