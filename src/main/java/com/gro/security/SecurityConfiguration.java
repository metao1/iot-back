package com.gro.security;

import com.gro.handler.PersoInfoAccessDeniedHandler;
import com.gro.handler.PersoInofAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final TokenProvider tokenProvider;

  private final CorsFilter corsFilter;

  public SecurityConfiguration(TokenProvider tokenProvider, CorsFilter corsFilter) {
    this.tokenProvider = tokenProvider;
    this.corsFilter = corsFilter;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
      .antMatchers(HttpMethod.OPTIONS, "/**")
      .antMatchers("/app/**/*.{js,html}")
      .antMatchers("/i18n/**")
      .antMatchers("/content/**")
      .antMatchers("/h2-console/**")
      .antMatchers("/swagger-ui/index.html")
      .antMatchers("/test/**");
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    // @formatter:off
    http
      .csrf()
      .disable()
      .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
      .exceptionHandling()
      .authenticationEntryPoint(new PersoInofAuthenticationEntryPoint())
      .accessDeniedHandler(new PersoInfoAccessDeniedHandler())
      .and()
      .headers()
      .frameOptions()
      .disable()
      .and()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .authorizeRequests()
      .antMatchers("/api/authenticate").permitAll()
      .antMatchers("/api/register").permitAll()
      .antMatchers("/api/activate").permitAll()
      .antMatchers("/api/account/reset-password/init").permitAll()
      .antMatchers("/api/account/reset-password/finish").permitAll()
      .antMatchers("/assets/*").permitAll()
      .antMatchers("/api/**").authenticated()
      .antMatchers("/management/health").permitAll()
      .antMatchers("/management/info").permitAll()
      .antMatchers("/management/prometheus").permitAll()
      .antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN)
      .and()
      .apply(securityConfigurerAdapter());
    // @formatter:on
  }

  private JWTConfigurer securityConfigurerAdapter() {
    return new JWTConfigurer(tokenProvider);
  }

}
