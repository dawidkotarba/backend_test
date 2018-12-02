package com.dawidkotarba.backendtest.repository.impl;

import com.dawidkotarba.backendtest.domain.audit.TransferAuditEntry;
import com.dawidkotarba.backendtest.infrastructure.db.DataStore;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class TransferAuditRepository extends AbstractRepository<TransferAuditEntry> {

    private final DataStore<TransferAuditEntry> dataStore;

    @Inject
    public TransferAuditRepository(final DataStore<TransferAuditEntry> dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public DataStore<TransferAuditEntry> getDataStore() {
        return dataStore;
    }
}
