package com.gro.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Configuration
@Profile("dev")
public class DBInitializeConfig {


    @PostConstruct
    public void initialize() {

    }
}


