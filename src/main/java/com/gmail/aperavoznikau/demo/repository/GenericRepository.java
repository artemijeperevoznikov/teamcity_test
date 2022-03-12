package com.gmail.aperavoznikau.demo.repository;

import java.util.List;

public interface GenericRepository<I, T> {

    void add(T entity);

    T update(T entity);

    List<T> findAll();

    T find(I id);
}
