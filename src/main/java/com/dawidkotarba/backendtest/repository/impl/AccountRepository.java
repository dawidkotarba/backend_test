package com.dawidkotarba.backendtest.repository.impl;

import com.dawidkotarba.backendtest.domain.Account;
import com.dawidkotarba.backendtest.infrastructure.db.DataStore;

import javax.inject.Singleton;

@Singleton
public class AccountRepository extends AbstractRepository<Account> {

    private final DataStore<Account> dataStore;

    public AccountRepository(final DataStore<Account> dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public DataStore<Account> getDataStore() {
        return dataStore;
    }
}