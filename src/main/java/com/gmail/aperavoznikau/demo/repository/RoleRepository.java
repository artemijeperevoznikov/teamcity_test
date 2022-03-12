package com.gmail.aperavoznikau.demo.repository;

import com.gmail.aperavoznikau.demo.repository.model.Role;
import com.gmail.aperavoznikau.demo.repository.model.RoleEnum;

import java.sql.Connection;
import java.util.Optional;

public interface RoleRepository extends GenericRepository<Long, Role> {
    Optional<Role> findByName(RoleEnum name);
}
