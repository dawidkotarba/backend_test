package com.dawidkotarba.backendtest.repository.impl;

import com.dawidkotarba.backendtest.domain.Identifiable;
import com.dawidkotarba.backendtest.infrastructure.db.DataStore;
import com.dawidkotarba.backendtest.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class AbstractRepository<T extends Identifiable> implements Repository<T> {

    @Override
    public T save(final T entity) {
        Objects.requireNonNull(entity, "Entity cannot be null");
        return getDataStore().create(entity);
    }

    @Override
    public Optional<T> find(final long id) {
        return Optional.ofNullable(getDataStore().get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(getDataStore().getAll());
    }

    @Override
    public long count() {
        return getDataStore().count();
    }

    @Override
    public void remove(final long id) {
        getDataStore().delete(id);
    }

    @Override
    public void removeAll() {
        getDataStore().deleteAll();
    }

    public abstract DataStore<T> getDataStore();
}
