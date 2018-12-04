package com.dawidkotarba.backendtest.infrastructure.db.impl;

import com.dawidkotarba.backendtest.configuration.DataStoreConfiguration;
import com.dawidkotarba.backendtest.domain.Identifiable;
import com.dawidkotarba.backendtest.infrastructure.db.DataStore;
import io.micronaut.context.annotation.Prototype;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Prototype
class InMemoryDataStore<T extends Identifiable> implements DataStore<T> {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryDataStore.class);

    private static final int SEQ_INITIAL_VALUE = 0;
    private final ConcurrentHashMap<Long, T> data = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(SEQ_INITIAL_VALUE);
    private final DataStoreConfiguration configuration;

    @Inject
    public InMemoryDataStore(final DataStoreConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public T save(final T entity) {
        setIdFromSequenceIfEmpty(entity);
        data.put(entity.getId(), entity);

        if (configuration.isPrintOnSave()) {
            LOG.info(entity.toString());
        }

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