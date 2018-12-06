package com.dawidkotarba.backendtest.infrastructure.db.impl;

import com.dawidkotarba.backendtest.domain.Identifiable;
import com.dawidkotarba.backendtest.infrastructure.db.DataStore;
import io.micronaut.context.annotation.Prototype;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * As specified in requirements, this in-memory data store can be used to persist
 * entities. It allows to operate on entities that extend {@link Identifiable} class.
 * The sequence generation is done by using atomic long that guarantees a thread safety.
 * Additionally, a double-locking approach is used to persist the ID of the entity without
 * the unnecessary overhead of synchronization.
 *
 * @param <T>
 */
@Prototype
class InMemoryDataStore<T extends Identifiable> implements DataStore<T> {
    private static final int SEQ_INITIAL_VALUE = 0;
    private final ConcurrentHashMap<Long, T> data = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(SEQ_INITIAL_VALUE);

    @Override
    public T save(final T entity) {
        setIdFromSequenceIfEmpty(entity);
        data.put(entity.getId(), entity);
        return entity;
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
    public long count() {
        return data.size();
    }

    @Override
    public Long getSequenceId() {
        return sequence.incrementAndGet();
    }

    private void setIdFromSequenceIfEmpty(final T entity) {
        if (entity.getId() == null) {
            synchronized (entity) {
                if (entity.getId() == null) {
                    final Long sequenceId = getSequenceId();
                    entity.setId(sequenceId);
                }
            }
        }
    }
}