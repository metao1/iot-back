package com.gro;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author karamim
 */
public class AccountCredential implements Serializable {

    @NotEmpty
    @ApiModelProperty(value = "Email", dataType = "String", required = true)
    private String username;


    @NotEmpty
    @ApiModelProperty(value = "Password", dataType = "String", required = true)
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
