package com.gmail.aperavoznikau.demo.repository.impl;

import com.gmail.aperavoznikau.demo.repository.GenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class GenericRepositoryImpl<I, T> implements GenericRepository<I, T> {

    protected Class<T> clazz;

    @PersistenceContext
    protected EntityManager em;

    public GenericRepositoryImpl() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Override
    public void add(T entity) {
        em.persist(entity);
    }

    @Override
    public T update(T entity) {
        return em.merge(entity);
    }

    @Override
    public List<T> findAll() {
        String queryString = "from " + clazz.getSimpleName();
        Query query = em.createQuery(queryString);
        return query.getResultList();
    }

    @Override
    public T find(I id) {
        return em.find(clazz, id);
    }
}
