package com.microservice.securityapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Relation;
import javax.naming.Name;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="Users")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  private String email;

  private String username;
  private String password;

  @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleEntity.class,cascade = CascadeType.PERSIST)
  @JoinTable(name= "user_roles",joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<RoleEntity> roles;
}
