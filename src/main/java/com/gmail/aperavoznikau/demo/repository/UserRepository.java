package com.gmail.aperavoznikau.demo.repository;

import com.gmail.aperavoznikau.demo.repository.model.User;

import java.util.Optional;

public interface UserRepository extends GenericRepository<Long, User> {

    Optional<User> findByUsername(String username);

}
