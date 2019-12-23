package com.iot.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iot.handler.ObjectFactory;
import com.iot.resources.ObjectMapperFactory;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ObjectFactory dtoFactory() {
        return new ObjectFactory(getModelMapper(), getObjectMapper());
    }

    @Bean
    public Jackson2JsonObjectMapper provideJackson2JsonObjectMapper(){
        return ObjectMapperFactory.getMapper();
    }
}
