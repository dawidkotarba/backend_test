package com.dawidkotarba.backendtest.service.impl;

import com.dawidkotarba.backendtest.domain.account.Account;
import com.dawidkotarba.backendtest.domain.transfer.TransferRequest;
import com.dawidkotarba.backendtest.domain.transfer.TransferStatus;
import com.dawidkotarba.backendtest.exception.impl.AccountNotFoundException;
import com.dawidkotarba.backendtest.exception.impl.InsufficientAmountException;
import com.dawidkotarba.backendtest.repository.Repository;
import com.dawidkotarba.backendtest.service.TransferService;
import com.dawidkotarba.backendtest.service.validator.Validator;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.math.BigDecimal;

@Singleton
class DefaultTransferService implements TransferService {

    private final Repository<Account> accountRepository;
    private final Repository<TransferRequest> transferRepository;
    private final Validator<TransferRequest> requestValidator;

    @Inject
    public DefaultTransferService(@Named("accountRepository") final Repository<Account> accountRepository,
                                  @Named("transferRepository") final Repository<TransferRequest> transferRepository,
                                  final Validator<TransferRequest> requestValidator) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
        this.requestValidator = requestValidator;
    }

    @Override
    public void transfer(final TransferRequest transferRequest) {
        requestValidator.validate(transferRequest);

        final Long senderAccountId = transferRequest.getSenderAccountId();
        final Account senderAccount = accountRepository.find(senderAccountId)
                .orElseThrow(() -> new AccountNotFoundException("Sender's account [" + senderAccountId + "] cannot be found."));

        final Long receiverAccountId = transferRequest.getReceiverAccountId();
        final Account receiverAccount = accountRepository.find(receiverAccountId)
                .orElseThrow(() -> new AccountNotFoundException("Receiver's account [" + receiverAccountId + "] cannot be found."));

        final BigDecimal transferAmount = transferRequest.getAmount();

        transferRepository.save(transferRequest.withStatus(TransferStatus.STARTED));

        // pessimistic lock for transfer amount subtraction
        synchronized (senderAccount) {
            if (senderAccount.isAmountAvailable(transferAmount)) {
                senderAccount.add(transferAmount.negate());
                accountRepository.save(senderAccount);
                transferRepository.save(transferRequest.withStatus(TransferStatus.AMOUNT_SUBTRACTED_FROM_SENDER));
            } else {
                transferRepository.save(transferRequest.withStatus(TransferStatus.INSUFFICIENT_AMOUNT));
                throw new InsufficientAmountException("Sender [" + senderAccountId + "] does not have a sufficient amount to transfer.");
            }
        }

        // optimistic for transfer amount addition
        receiverAccount.add(transferAmount);
        accountRepository.save(receiverAccount);
        transferRepository.save(transferRequest.withStatus(TransferStatus.SUCCESS));
    }
}
