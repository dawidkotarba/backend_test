package com.dawidkotarba.backendtest.repository.impl;

import com.dawidkotarba.backendtest.domain.Identifiable;
import com.dawidkotarba.backendtest.infrastructure.db.DataStore;
import com.dawidkotarba.backendtest.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * This abstract repository can contain methods that allow to manipulate types
 * which extend {@link Identifiable} class.
 *
 * @param <T> the model class that extends {@link Identifiable}
 */
abstract class AbstractRepository<T extends Identifiable> implements Repository<T> {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractRepository.class);

    @Override
    public T save(final T entity) {
        LOG.info("Saved entity: {}", entity.toString());
        return getDataStore().save(entity);
    }

    @Override
    public Optional<T> find(final long id) {
        return Optional.ofNullable(getDataStore().get(id));
    }

    protected abstract DataStore<T> getDataStore();
}
