package com.dawidkotarba.backendtest.repository.impl;

import com.dawidkotarba.backendtest.domain.Identifiable;
import com.dawidkotarba.backendtest.infrastructure.db.DataStore;
import com.dawidkotarba.backendtest.repository.Repository;

import java.util.Optional;

abstract class AbstractRepository<T extends Identifiable> implements Repository<T> {

    @Override
    public T save(final T entity) {
        return getDataStore().save(entity);
    }

    @Override
    public Optional<T> find(final long id) {
        return Optional.ofNullable(getDataStore().get(id));
    }

    protected abstract DataStore<T> getDataStore();
}
