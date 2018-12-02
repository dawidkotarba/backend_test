package com.dawidkotarba.backendtest.service.impl;

import com.dawidkotarba.backendtest.domain.account.Account;
import com.dawidkotarba.backendtest.domain.audit.TransferAuditEntry;
import com.dawidkotarba.backendtest.dto.TransferRequestDto;
import com.dawidkotarba.backendtest.exception.impl.InvalidRequestException;
import com.dawidkotarba.backendtest.repository.Repository;
import com.dawidkotarba.backendtest.service.TransferService;
import com.dawidkotarba.backendtest.service.validator.TransferRequestValidator;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class DefaultTransferService implements TransferService {

    private final Repository<Account> accountRepository;
    private final Repository<TransferAuditEntry> transferAuditRepository;
    private final TransferRequestValidator requestValidator;

    @Inject
    public DefaultTransferService(@Named("accountRepository") final Repository<Account> accountRepository,
                                  @Named("transferAuditRepository") final Repository<TransferAuditEntry> transferAuditRepository) {
        this.accountRepository = accountRepository;
        this.transferAuditRepository = transferAuditRepository;
        requestValidator = new TransferRequestValidator();
    }

    @Override
    public void transfer(final TransferRequestDto transferRequestDto) {
        final boolean validationResult = requestValidator.validate(transferRequestDto);

        if (!validationResult) {
            throw new InvalidRequestException();
        }

        // TODO: 02.12.18 implement
    }
}
