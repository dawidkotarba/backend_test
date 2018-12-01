package com.dawidkotarba.backendtest.infrastructure.db.impl;

import com.dawidkotarba.backendtest.domain.Identifiable;
import com.dawidkotarba.backendtest.infrastructure.db.DataStore;
import io.micronaut.context.annotation.Prototype;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Prototype
public class InMemoryDataStore<T extends Identifiable> implements DataStore<T> {
    private final ConcurrentHashMap<Long, T> data = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    @Override
    public T create(final T entity) {
        final Long sequenceId = getSequenceId();
        entity.setId(sequenceId);
        data.put(sequenceId, entity);
        return entity;
    }

    @Override
    public T get(final long id) {
        return data.get(id);
    }

    @Override
    public Collection<T> getAll() {
        return data.values();
    }

    @Override
    public void delete(final long id) {
        data.remove(id);
    }

    @Override
    public void deleteAll() {
        data.clear();
    }

    @Override
    public Long getSequenceId() {
        return sequence.incrementAndGet();
    }
}