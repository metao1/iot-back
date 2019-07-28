package com.gro.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gro.model.AbstractAuditingEntity;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity(
    name = "User"
)
@Table(
    name = "user"
)
@Cache(
    usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE
)
public class User extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    @Column(
        name = "id"
    )
    private Long id;
    @JsonIgnore
    @NotNull
    @Size(
        min = 60,
        max = 60
    )
    @Column(
        name = "password_hash",
        length = 60,
        nullable = false
    )
    private String password;
    @Size(
        max = 50
    )
    @Column(
        name = "name",
        length = 50
    )
    private String name;
    @Email
    @Size(
        min = 5,
        max = 254
    )
    @Column(
        length = 254,
        unique = true
    )
    private String email;
    @NotNull
    @Column(
        nullable = false
    )
    private boolean activated = false;
    @Size(
        min = 2,
        max = 6
    )
    @Column(
        name = "lang_key",
        length = 6
    )
    private String langKey;
    @Size(
        max = 256
    )
    @Column(
        name = "image_url",
        length = 256
    )
    private String imageUrl;

    @Column(
        name = "last_password_reset_date"
    )
    private Instant lastPasswordResetDate;

    @Size(
        max = 20
    )
    @Column(
        name = "activation_key",
        length = 20
    )
    @JsonIgnore
    private String activationKey;
    @Size(
        max = 20
    )
    @Column(
        name = "reset_key",
        length = 20
    )
    @JsonIgnore
    private String resetKey;
    @Column(
        name = "reset_date"
    )
    private Instant resetDate = null;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "user_authority",
        joinColumns = {@JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
        )},
        inverseJoinColumns = {@JoinColumn(
            name = "authority_name",
            referencedColumnName = "name"
        )}
    )
    @Cache(
        usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE
    )
    @BatchSize(
        size = 20
    )
    private List<Authority> authorities = new ArrayList<>();

    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setLastPasswordResetDate(Instant lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public Instant getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean getActivated() {
        return this.activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getActivationKey() {
        return this.activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getResetKey() {
        return this.resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public Instant getResetDate() {
        return this.resetDate;
    }

    public void setResetDate(Instant resetDate) {
        this.resetDate = resetDate;
    }

    public String getLangKey() {
        return this.langKey;
    }

    public void setLangKey(String langKey) {
    }

    public List<Authority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof User)) {
            return false;
        } else {
            return this.id != null && this.id.equals(((User) o).id);
        }
    }

    public int hashCode() {
        return 31;
    }

    public String toString() {
        return "User{  name='" + this.name + '\'' + ", email='" + this.email + '\'' + ", imageUrl='" + this.imageUrl + '\'' + ", activated='" + this.activated + '\'' + ", langKey='" + this.langKey + '\'' + ", activationKey='" + this.activationKey + '\'' + "}";
    }

}
