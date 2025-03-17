package com.microservice.securityapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
  return httpSecurity.
    csrf(AbstractHttpConfigurer::disable).//Modified Cors si son necesarios
    authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
      authorizationManagerRequestMatcherRegistry.requestMatchers("/v1/createUser").permitAll().anyRequest().authenticated()
          ).formLogin(AuthenticationSuccessHandler->successHandler()).
    formLogin(AbstractAuthenticationFilterConfigurer::permitAll).
    sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.ALWAYS).invalidSessionUrl("/login").maximumSessions(1).expiredUrl("/login").sessionRegistry(sessionRegistry())).
    sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionFixation().migrateSession()).
    build();
  }

  public AuthenticationSuccessHandler successHandler(){
    return ((request, response, authentication) -> {
      response.sendRedirect("/v1/session");
    });
  }
  @Bean
  public SessionRegistry sessionRegistry(){
    return new SessionRegistryImpl();
  }
}
