package com.microservice.securityapp.repository;

import com.microservice.securityapp.models.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {



}
