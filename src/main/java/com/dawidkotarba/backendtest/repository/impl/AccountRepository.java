package com.dawidkotarba.backendtest.repository.impl;

import com.dawidkotarba.backendtest.domain.Account;
import com.dawidkotarba.backendtest.domain.Identifiable;
import com.dawidkotarba.backendtest.repository.Repository;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Singleton
public class AccountRepository implements Repository<Account> {

    private InMemoryDatabase<Account> inMemoryDb;

    @PostConstruct
    void init() {
        inMemoryDb = new InMemoryDatabase<>();
    }

    @Override
    public Account save(final Account account) {
        Objects.requireNonNull(account, "Account cannot be null");
        return inMemoryDb.create(account);
    }

    @Override
    public void remove(final long id) {
        inMemoryDb.delete(id);
    }

    @Override
    public Optional<Account> find(final long id) {
        return Optional.ofNullable(inMemoryDb.get(id));
    }

    private class InMemoryDatabase<T extends Identifiable> {
        private final ConcurrentHashMap<Long, T> data;
        private final AtomicLong sequence;

        InMemoryDatabase() {
            data = new ConcurrentHashMap<>();
            sequence = new AtomicLong(0);
        }

        T create(final T entity) {
            data.put(getSequenceId(), entity);
            return entity;
        }

        T get(final long id) {
            return data.get(id);
        }

        void delete(final long id) {
            data.remove(id);
        }

        Long getSequenceId() {
            return sequence.incrementAndGet();
        }
    }
}