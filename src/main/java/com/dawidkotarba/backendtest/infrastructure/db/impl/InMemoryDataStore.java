package com.dawidkotarba.backendtest.infrastructure.db.impl;

import com.dawidkotarba.backendtest.domain.Identifiable;
import com.dawidkotarba.backendtest.infrastructure.db.DataStore;
import io.micronaut.context.annotation.Prototype;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Prototype
public class InMemoryDataStore<T extends Identifiable> implements DataStore<T> {
    private static final int SEQ_INITIAL_VALUE = 0;
    private final ConcurrentHashMap<Long, T> data = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(SEQ_INITIAL_VALUE);

    @Override
    public T save(final T entity) {
        final Long sequenceId = getSequenceId();
        entity.setId(sequenceId);
        data.put(sequenceId, entity);
        return entity;
    }

    @Override
    public Collection<T> saveAll(final T... entities) {
        Objects.requireNonNull(entities, "Entities cannot be null");
        return Arrays.stream(entities).map(this::save).collect(Collectors.toList());
    }

    @Override
    public Collection<T> saveAll(final Collection<T> entities) {
        Objects.requireNonNull(entities, "Entities cannot be null");
        return entities.stream().map(this::save).collect(Collectors.toList());
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
    public long count() {
        return data.size();
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