package com.dawidkotarba.backendtest.converter.impl;

import com.dawidkotarba.backendtest.converter.Converter;
import com.dawidkotarba.backendtest.domain.transfer.TransferRequest;
import com.dawidkotarba.backendtest.domain.transfer.TransferStatus;
import com.dawidkotarba.backendtest.dto.TransferRequestDto;

import java.time.LocalDateTime;

public class TransferRequestConverter implements Converter<TransferRequestDto, TransferRequest> {

    @Override
    public TransferRequest convert(final TransferRequestDto dto) {
        return new TransferRequest(dto.getSenderAccountId(),
                dto.getReceiverAccountId(),
                dto.getAmount(),
                dto.getTitle())
                .withTimestamp(LocalDateTime.now())
                .withStatus(TransferStatus.PROCESSING);
    }
}
