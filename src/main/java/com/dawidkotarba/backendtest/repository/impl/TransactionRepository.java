package com.dawidkotarba.backendtest.repository.impl;

import com.dawidkotarba.backendtest.domain.Transaction;
import com.dawidkotarba.backendtest.infrastructure.db.DataStore;

import javax.inject.Singleton;

@Singleton
class TransactionRepository extends AbstractRepository<Transaction> {

    private final DataStore<Transaction> dataStore;

    public TransactionRepository(final DataStore<Transaction> dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public DataStore<Transaction> getDataStore() {
        return dataStore;
    }
}
