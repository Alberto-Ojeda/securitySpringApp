package com.microservice.securityapp.repository;

import com.microservice.securityapp.models.RoleEntity;
import org.springframework.data.repository.CrudRepository;

import javax.management.relation.Role;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

}
