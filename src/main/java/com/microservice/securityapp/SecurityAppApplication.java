package com.microservice.securityapp;

import com.microservice.securityapp.models.Erole;
import com.microservice.securityapp.models.RoleEntity;
import com.microservice.securityapp.models.UserEntity;
import com.microservice.securityapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class SecurityAppApplication {

  public static void main(String[] args) {
    SpringApplication.run(SecurityAppApplication.class, args);
  }

  @Bean
  CommandLineRunner init(PasswordEncoder passwordEncoder, UserRepository userRepository){
    return args -> {
      UserEntity user = new UserEntity().builder().email("admin@gmail.com").password(passwordEncoder.encode("1234")).
              username("admin").roles(Set.of(RoleEntity.builder().name(Erole.valueOf(Erole.ADMIN.name())).
              build())).build();
      UserEntity user1 = new UserEntity().builder().email("alberto@gmail.com").password(passwordEncoder.encode("1234")).
              username("alberto").roles(Set.of(RoleEntity.builder().name(Erole.valueOf(Erole.USER.name())).
                      build())).build();
      UserEntity user2 = new UserEntity().builder().email("user@admin.com").password(passwordEncoder.encode("1234")).
              username("user").roles(Set.of(RoleEntity.builder().name(Erole.valueOf(Erole.INVITE.name())).
                      build())).build();
      userRepository.save(user);
      userRepository.save(user1);
      userRepository.save(user2);
    };
  }
}
