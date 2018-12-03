package com.dawidkotarba.backendtest.service.validator.impl;

import com.dawidkotarba.backendtest.configuration.TransferConfiguration;
import com.dawidkotarba.backendtest.domain.transfer.TransferRequest;
import com.dawidkotarba.backendtest.exception.impl.InvalidRequestException;
import com.dawidkotarba.backendtest.service.validator.Validator;
import io.micronaut.core.util.StringUtils;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

@Singleton
public class TransferRequestValidator implements Validator<TransferRequest> {

    private final TransferConfiguration configuration;

    @Inject
    public TransferRequestValidator(final TransferConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void validate(final TransferRequest request) {
        final boolean isFilled = Stream.of(request, request.getSenderAccountId(), request.getReceiverAccountId(),
                request.getAmount(), request.getTitle())
                .allMatch(Objects::nonNull);

        if (!isFilled) {
            throw new InvalidRequestException("One or more fields are null.");
        }

        if (!validateAccountIds(request.getSenderAccountId(), request.getReceiverAccountId())) {
            throw new InvalidRequestException("Invalid account(s) specified.");
        }

        if (!validateTransferAmount(request.getAmount())) {
            throw new InvalidRequestException("Invalid transfer amount.");
        }

        if (!validateTitle(request.getTitle())) {
            throw new InvalidRequestException("Incorrect transfer title.");
        }
    }

    private boolean validateAccountIds(final Long... accountIds) {
        return Arrays.stream(accountIds).allMatch(account -> account > 0);
    }

    private boolean validateTransferAmount(final BigDecimal transferAmount) {
        return transferAmount.compareTo(configuration.getMinTransferAmount()) >= 0
                && transferAmount.compareTo(configuration.getMaxTransferAmount()) <= 0
                && transferAmount.scale() <= configuration.getScale();
    }

    private boolean validateTitle(final String title) {
        return !StringUtils.isEmpty(title) && title.length() <= configuration.getMaxTitleLength();
    }
}
