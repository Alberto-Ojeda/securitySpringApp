package com.microservice.securityapp.controller;

import com.microservice.securityapp.controller.request.CreateUserDTO;
import com.microservice.securityapp.models.Erole;
import com.microservice.securityapp.models.RoleEntity;
import com.microservice.securityapp.models.UserEntity;
import com.microservice.securityapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1")
public class PrincipalController {
  @Autowired
  private UserRepository userRepository;
  @GetMapping("/hello")
  public  String hello(){
    return "Hello World NOT SecurityApp";
  }
  @GetMapping("/helloSecured")
  public  String helloSecured(){
    return "Hello World SecurityApp";
  }
  @PostMapping("/createUser")
  public ResponseEntity<?> createUser( @RequestBody CreateUserDTO createUserDTO){
    Set<RoleEntity> roles = createUserDTO.getRoles().stream().
      map(role-> RoleEntity.builder().name(Erole.valueOf(role)).build()).collect(Collectors.toSet());
    UserEntity userEntity = UserEntity.builder().username(createUserDTO.getUsername())
      .password(createUserDTO.getPassword()).email(createUserDTO.getEmail()).roles(roles).build();
  userRepository.save(userEntity);

return ResponseEntity.ok(userEntity);
  }
@DeleteMapping("/deleteUser")
  public String deleteUser(@RequestParam("id") String id){
  userRepository.deleteById(Long.parseLong(id));
  return "User deleted Successfully" + id;
  }
}
