package com.gmail.aperavoznikau.demo.repository.impl;

import com.gmail.aperavoznikau.demo.repository.RoleRepository;
import com.gmail.aperavoznikau.demo.repository.model.Role;
import com.gmail.aperavoznikau.demo.repository.model.RoleEnum;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class RoleRepositoryImpl extends GenericRepositoryImpl<Long, Role> implements RoleRepository {
    @Override
    public Optional<Role> findByName(RoleEnum name) {
        String queryString = "select r from Role as r where r.name=:name";
        Query query = em.createQuery(queryString);
        query.setParameter("name", name);
        Role role;
        try {
            role = (Role) query.getSingleResult();
            return Optional.of(role);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
