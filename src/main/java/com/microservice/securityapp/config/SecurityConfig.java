package com.microservice.securityapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
 @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
   return httpSecurity.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(
           auth ->
           {auth.requestMatchers("/hello").permitAll();
                   auth.anyRequest().authenticated(); })
           .sessionManagement(session -> {
             session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
           }).httpBasic().and()
           .build();
 }
  /* @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
  return httpSecurity.
    csrf(AbstractHttpConfigurer::disable).//Modified Cors si son necesarios
    authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
      authorizationManagerRequestMatcherRegistry.requestMatchers("/v1/createUser").permitAll().anyRequest().authenticated()
          ).formLogin(AuthenticationSuccessHandler->successHandler()).
    formLogin(AbstractAuthenticationFilterConfigurer::permitAll).
    sessionManagement(
            httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .invalidSessionUrl("/login").maximumSessions(1).expiredUrl("/login").sessionRegistry(sessionRegistry())).
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

*/

  @Bean
   UserDetailsService userDetailsService() {
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(
            User.withUsername("alberto").
            password("1234").roles().
            build());
    return manager;
  }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

  @Bean
   AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder) throws Exception {

      return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder).and().build();

  }

}
