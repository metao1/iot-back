package com.iot.security;

import com.iot.handler.PersoInfoAccessDeniedHandler;
import com.iot.handler.PersoInofAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtAccessDeniedHandler accessDeniedHandler;

    public SecurityConfiguration(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() throws Exception {
        return new CorsFilter();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .csrf()
            .disable()
            .exceptionHandling()
            .authenticationEntryPoint(unauthorizedHandler)
            .accessDeniedHandler(accessDeniedHandler)
            .authenticationEntryPoint(new PersoInofAuthenticationEntryPoint())
            .accessDeniedHandler(new PersoInfoAccessDeniedHandler())
            .and()
            .headers()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/api/authenticate").permitAll()
            .antMatchers("/api/event").permitAll()
            .antMatchers("/assets/*").permitAll()
            .antMatchers("/api/**").authenticated()
            .and()
            .apply(securityConfigurerAdapter());
        // @formatter:on
        http
            .addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class)
            //.addFilterBefore(securityConfigurerAdapter(), UsernamePasswordAuthenticationFilter.class)
            .headers()
            .cacheControl();
    }


    private JWTConfigurer securityConfigurerAdapter() {
        //return new JWTFilter(tokenProvider);
        return new JWTConfigurer(tokenProvider);
    }

}
