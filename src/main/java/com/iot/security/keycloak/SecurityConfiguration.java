package com.iot.security.keycloak;

import com.iot.security.XSSFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@Slf4j
@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@KeycloakConfiguration
@EnableConfigurationProperties({ KeycloakSpringBootProperties.class, SecurityPropertiesExtension.class })
public class SecurityConfiguration extends KeycloakWebSecurityConfigurerAdapter {
    private final RoleHierarchy roleHierarchy;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable();
    }

    /**
     * Use Keycloak configuration from properties / yaml
     *
     * @return
     */
    @Bean
    public KeycloakConfigResolver keycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    @Override
    protected KeycloakAuthenticationProvider keycloakAuthenticationProvider() {

        SimpleAuthorityMapper grantedAuthorityMapper = new SimpleAuthorityMapper();
        grantedAuthorityMapper.setPrefix("ROLE_");
        grantedAuthorityMapper.setConvertToUpperCase(true);

        RoleResolvingGrantedAuthoritiesMapper resolvingMapper =
                new RoleResolvingGrantedAuthoritiesMapper(roleHierarchy, grantedAuthorityMapper);
//		RoleAppendingGrantedAuthoritiesMapper
        return new CustomKeycloakAuthenticationProvider(resolvingMapper);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(keycloakAuthenticationProvider());
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    /**
     * {@link org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate} configured
     * to use {@link org.keycloak.representations.AccessToken} of current user.
     *
     * @param requestFactory
     * @return KeycloakRestTemplate
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public KeycloakRestTemplate keycloakRestTemplate(KeycloakClientRequestFactory requestFactory) {
        return new KeycloakRestTemplate(requestFactory);
    }

    @Bean
    public FilterRegistrationBean<XSSFilter> xssPreventFilter() {
        FilterRegistrationBean<XSSFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new XSSFilter());
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}
