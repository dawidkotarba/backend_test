package com.dawidkotarba.backendtest.repository.impl;

import com.dawidkotarba.backendtest.domain.transfer.TransferRequest;
import com.dawidkotarba.backendtest.infrastructure.db.DataStore;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * This repository allows to manipulate {@link TransferRequest} model.
 */
@Singleton
class TransferRepository extends AbstractRepository<TransferRequest> {

    private final DataStore<TransferRequest> dataStore;

    @Inject
    public TransferRepository(final DataStore<TransferRequest> dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public DataStore<TransferRequest> getDataStore() {
        return dataStore;
    }
}
