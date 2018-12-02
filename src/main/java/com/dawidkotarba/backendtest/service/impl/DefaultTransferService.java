package com.dawidkotarba.backendtest.service.impl;

import com.dawidkotarba.backendtest.domain.account.Account;
import com.dawidkotarba.backendtest.domain.audit.TransferAuditEntry;
import com.dawidkotarba.backendtest.dto.TransferDto;
import com.dawidkotarba.backendtest.repository.Repository;
import com.dawidkotarba.backendtest.service.TransferService;

import javax.inject.Singleton;

@Singleton
public class DefaultTransferService implements TransferService {

    private final Repository<Account> accountRepository;
    private final Repository<TransferAuditEntry> transferAuditRepository;

    public DefaultTransferService(final Repository<Account> accountRepository, final Repository<TransferAuditEntry> transferAuditRepository) {
        this.accountRepository = accountRepository;
        this.transferAuditRepository = transferAuditRepository;
    }

    @Override
    public void transfer(final TransferDto transferDto) {
        // TODO: 02.12.18 implement
    }
}
