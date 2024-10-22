package com.example.crud.repositories;

import com.example.crud.entities.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {


    Optional<Role> findByName(String name);
}
