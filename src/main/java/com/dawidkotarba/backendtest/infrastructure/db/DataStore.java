package com.dawidkotarba.backendtest.infrastructure.db;

import com.dawidkotarba.backendtest.domain.Identifiable;

public interface DataStore<T extends Identifiable> {
    T create(T entity);

    T get(long id);

    void delete(long id);

    Long getSequenceId();
}
