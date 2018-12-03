package com.dawidkotarba.backendtest.service.impl;

import com.dawidkotarba.backendtest.domain.account.Account;
import com.dawidkotarba.backendtest.domain.transfer.TransferRequest;
import com.dawidkotarba.backendtest.domain.transfer.TransferStatus;
import com.dawidkotarba.backendtest.exception.impl.InvalidRequestException;
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
        final Account senderAccount = accountRepository.find(senderAccountId).orElseThrow(() -> new InvalidRequestException("Sender's account cannot be found."));

        final Long receiverAccountId = transferRequest.getReceiverAccountId();
        final Account receiverAccount = accountRepository.find(receiverAccountId).orElseThrow(() -> new InvalidRequestException("Receiver's account cannot be found."));

        final BigDecimal transferAmount = transferRequest.getAmount();

        transferRepository.save(transferRequest.withStatus(TransferStatus.STARTED));

        // pessimistic lock for transfer amount subtraction
        synchronized (senderAccount) {
            if (senderAccount.isAmountAvailable(transferAmount)) {
                senderAccount.add(transferAmount.negate());
                transferRepository.save(transferRequest.withStatus(TransferStatus.AMOUNT_SUBSTRACTED_FROM_SENDER));
            } else {
                transferRepository.save(transferRequest.withStatus(TransferStatus.INSUFFICIENT_AMOUNT));
                throw new InvalidRequestException("Insufficient amount"); // TODO: 03.12.18 more biz exception here
            }
        }

        // optimistic for transfer amount addition
        receiverAccount.add(transferAmount);
        transferRepository.save(transferRequest.withStatus(TransferStatus.SUCCESS));
    }
}
