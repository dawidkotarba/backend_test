package com.dawidkotarba.backendtest.repository.impl;

import com.dawidkotarba.backendtest.domain.audit.TransferAuditEntry;
import com.dawidkotarba.backendtest.infrastructure.db.DataStore;

import javax.inject.Singleton;

@Singleton
class TransactionRepository extends AbstractRepository<TransferAuditEntry> {

    private final DataStore<TransferAuditEntry> dataStore;

    public TransactionRepository(final DataStore<TransferAuditEntry> dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public DataStore<TransferAuditEntry> getDataStore() {
        return dataStore;
    }
}
