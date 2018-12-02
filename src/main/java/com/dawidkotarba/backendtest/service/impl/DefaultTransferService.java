package com.dawidkotarba.backendtest.service.impl;

import com.dawidkotarba.backendtest.domain.account.Account;
import com.dawidkotarba.backendtest.domain.audit.TransferAuditEntry;
import com.dawidkotarba.backendtest.dto.TransferRequestDto;
import com.dawidkotarba.backendtest.repository.Repository;
import com.dawidkotarba.backendtest.service.TransferService;

import javax.inject.Singleton;
import java.util.Objects;

@Singleton
public class DefaultTransferService implements TransferService {

    private final Repository<Account> accountRepository;
    private final Repository<TransferAuditEntry> transferAuditRepository;

    public DefaultTransferService(final Repository<Account> accountRepository, final Repository<TransferAuditEntry> transferAuditRepository) {
        this.accountRepository = accountRepository;
        this.transferAuditRepository = transferAuditRepository;
    }

    @Override
    public void transfer(final TransferRequestDto transferRequestDto) {
        Objects.requireNonNull(transferRequestDto, "Transfer DTO cannot be null");


        // TODO: 02.12.18 implement
    }
}
