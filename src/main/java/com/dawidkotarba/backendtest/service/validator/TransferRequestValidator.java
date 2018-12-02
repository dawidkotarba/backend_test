package com.dawidkotarba.backendtest.service.validator;

import com.dawidkotarba.backendtest.configuration.TransferConfiguration;
import com.dawidkotarba.backendtest.dto.TransferRequestDto;
import com.dawidkotarba.backendtest.exception.impl.InvalidRequestException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

@Singleton
public class TransferRequestValidator {

    private final TransferConfiguration configuration;

    @Inject
    public TransferRequestValidator(final TransferConfiguration configuration) {
        this.configuration = configuration;
    }

    public void validate(final TransferRequestDto request) {
        final boolean isFiled = Stream.of(request, request.getSenderAccountId(), request.getReceiverAccountId(),
                request.getAmount(), request.getTitle())
                .allMatch(Objects::nonNull);

        if (!isFiled) {
            throw new InvalidRequestException("One or more fields are null.");
        }

        if (!validateAccountIds(request.getSenderAccountId(), request.getReceiverAccountId())) {
            throw new InvalidRequestException("Invalid account(s) specified.");
        }

        if (!validateTransferAmount(request.getAmount())) {
            throw new InvalidRequestException("Invalid transfer amount.");
        }

        // TODO: 02.12.18 Finish this
    }

    private boolean validateAccountIds(final Long... accountIds) {
        return Arrays.stream(accountIds).allMatch(account -> account > 0);
    }

    private boolean validateTransferAmount(final BigDecimal transferAmount) {
        return transferAmount.compareTo(configuration.getMinTransferAmount()) >= 0
                && transferAmount.compareTo(configuration.getMaxTransferAmount()) <= 0;
    }
}
