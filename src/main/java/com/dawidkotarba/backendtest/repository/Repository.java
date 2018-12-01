package com.dawidkotarba.backendtest.repository;

import com.dawidkotarba.backendtest.domain.Identifiable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface Repository<T extends Identifiable> {

    T save(T entity);

    Collection<T> saveAll(T... entities);

    Collection<T> saveAll(Collection<T> entities);

    Optional<T> find(long id);

    List<T> findAll();

    long count();

    void remove(long id);

    void removeAll();

}
