package com.dawidkotarba.backendtest.facade.impl;

import com.dawidkotarba.backendtest.converter.impl.TransferRequestConverter;
import com.dawidkotarba.backendtest.domain.transfer.TransferRequest;
import com.dawidkotarba.backendtest.dto.TransferRequestDto;
import com.dawidkotarba.backendtest.facade.TransferFacade;
import com.dawidkotarba.backendtest.service.TransferService;

import javax.inject.Inject;

public class DefaultTransferFacade implements TransferFacade {

    private final TransferService transferService;

    @Inject
    public DefaultTransferFacade(final TransferService transferService) {
        this.transferService = transferService;
    }

    @Override
    public void transfer(final TransferRequestDto transferRequestDto) {
        final TransferRequestConverter converter = new TransferRequestConverter();
        final TransferRequest transferRequest = converter.convert(transferRequestDto);
        transferService.transfer(transferRequest);
    }
}
