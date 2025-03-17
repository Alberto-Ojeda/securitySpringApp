package com.microservice.securityapp.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {
  @NonNull
  private String email;
  @NonNull
  private String username;
  @NonNull
  private String password;
  @NonNull
  private Set<String>  roles;
}
