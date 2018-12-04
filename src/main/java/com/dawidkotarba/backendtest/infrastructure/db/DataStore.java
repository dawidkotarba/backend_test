package com.dawidkotarba.backendtest.infrastructure.db;

import com.dawidkotarba.backendtest.domain.Identifiable;

import java.util.Collection;

public interface DataStore<T extends Identifiable> {
    T save(T entity);

    Collection<T> saveAll(Collection<T> entities);

    T get(long id);

    long count();

    Long getSequenceId();
}
