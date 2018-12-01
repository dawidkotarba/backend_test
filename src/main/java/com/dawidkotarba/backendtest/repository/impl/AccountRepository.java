package com.dawidkotarba.backendtest.repository.impl;

import com.dawidkotarba.backendtest.domain.Account;
import com.dawidkotarba.backendtest.infrastructure.db.DataStore;
import com.dawidkotarba.backendtest.infrastructure.db.impl.InMemoryDataStore;
import com.dawidkotarba.backendtest.repository.Repository;

import javax.inject.Singleton;
import java.util.Objects;
import java.util.Optional;

@Singleton
public class AccountRepository implements Repository<Account> {

    private final DataStore<Account> dataStore;

    public AccountRepository(final InMemoryDataStore<Account> dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Account save(final Account account) {
        Objects.requireNonNull(account, "Account cannot be null");
        return dataStore.create(account);
    }

    @Override
    public void remove(final long id) {
        dataStore.delete(id);
    }

    @Override
    public Optional<Account> find(final long id) {
        return Optional.ofNullable(dataStore.get(id));
    }
}