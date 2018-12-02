package com.dawidkotarba.backendtest.service.validator;

import com.dawidkotarba.backendtest.dto.TransferRequestDto;

import java.util.Objects;
import java.util.stream.Stream;

public class TransferRequestValidator {

    public boolean validate(final TransferRequestDto request) {
        final boolean isNotInitialized = Stream.of(request, request.getSenderAccountId(), request.getReceiverAccountId(),
                request.getAmount(), request.getTitle())
                .anyMatch(Objects::isNull);
        return !isNotInitialized;
    }
}
