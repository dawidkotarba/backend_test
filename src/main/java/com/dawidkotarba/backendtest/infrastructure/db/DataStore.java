package com.dawidkotarba.backendtest.infrastructure.db;

import com.dawidkotarba.backendtest.domain.Identifiable;

import java.util.Collection;

public interface DataStore<T extends Identifiable> {
    T save(T entity);

    Collection<T> saveAll(T... entities);

    Collection<T> saveAll(Collection<T> entities);

    T get(long id);

    Collection<T> getAll();

    long count();

    void delete(long id);

    void deleteAll();

    Long getSequenceId();
}
