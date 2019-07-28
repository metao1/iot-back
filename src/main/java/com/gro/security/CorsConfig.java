package com.gro.security;

import com.gro.resources.ObjectMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {

    @Bean
    public Jackson2JsonObjectMapper jackson2JsonObjectMapper() {
        return ObjectMapperFactory.getMapper();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE", "PATCH")
                .allowedOrigins("*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:///home/upload-dir/");
    }


}
