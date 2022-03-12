package com.gmail.aperavoznikau.demo.repository.impl;

import com.gmail.aperavoznikau.demo.repository.UserRepository;
import com.gmail.aperavoznikau.demo.repository.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.Optional;

@Repository
@Slf4j
public class UserRepositoryImpl extends GenericRepositoryImpl<Long, User> implements UserRepository {

    @Override
    public Optional<User> findByUsername(String username) {
        String queryString = "select u from User as u where u.username=:username";
        Query query = em.createQuery(queryString);
        query.setParameter("username", username);
        User user = (User) query.getSingleResult();
        return Optional.of(user);
    }

}
