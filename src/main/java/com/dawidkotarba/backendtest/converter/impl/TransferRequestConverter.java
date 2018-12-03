package com.dawidkotarba.backendtest.converter.impl;

import com.dawidkotarba.backendtest.converter.Converter;
import com.dawidkotarba.backendtest.domain.transfer.TransferRequest;
import com.dawidkotarba.backendtest.dto.TransferRequestDto;
import com.dawidkotarba.backendtest.service.TimeService;

public class TransferRequestConverter implements Converter<TransferRequestDto, TransferRequest> {

    private final TimeService timeService;

    public TransferRequestConverter(final TimeService timeService) {
        this.timeService = timeService;
    }

    @Override
    public TransferRequest convert(final TransferRequestDto dto) {
        return new TransferRequest(dto.getSenderAccountId(),
                dto.getReceiverAccountId(),
                dto.getAmount(),
                dto.getTitle())
                .withTimestamp(timeService.getCurrentTime());
    }
}
