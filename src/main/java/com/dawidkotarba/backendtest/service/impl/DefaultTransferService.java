package com.dawidkotarba.backendtest.service.impl;

import com.dawidkotarba.backendtest.domain.account.Account;
import com.dawidkotarba.backendtest.domain.transfer.TransferRequest;
import com.dawidkotarba.backendtest.repository.Repository;
import com.dawidkotarba.backendtest.service.TransferService;
import com.dawidkotarba.backendtest.service.validator.TransferRequestValidator;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class DefaultTransferService implements TransferService {

    private final Repository<Account> accountRepository;
    private final Repository<TransferRequest> transferRepository;
    private final TransferRequestValidator requestValidator;

    @Inject
    public DefaultTransferService(@Named("accountRepository") final Repository<Account> accountRepository,
                                  @Named("transferRepository") final Repository<TransferRequest> transferRepository,
                                  final TransferRequestValidator requestValidator) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
        this.requestValidator = requestValidator;
    }

    @Override
    public void transfer(final TransferRequest transferRequest) {
        requestValidator.validate(transferRequest);

        // TODO: 02.12.18 implement
    }
}
