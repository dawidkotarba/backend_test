package com.dawidkotarba.backendtest.infrastructure.db;

import com.dawidkotarba.backendtest.domain.Identifiable;

import java.util.Collection;

public interface DataStore<T extends Identifiable> {
    T create(T entity);

    T get(long id);

    Collection<T> getAll();

    void delete(long id);

    void deleteAll();

    Long getSequenceId();
}
