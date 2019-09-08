package com.iot;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author karamim
 */
public class AccountCredential implements Serializable {

    @NotEmpty
    private String username;


    @NotEmpty
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
