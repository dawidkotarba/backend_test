package com.dawidkotarba.backendtest.facade.impl;

import com.dawidkotarba.backendtest.converter.impl.TransferRequestConverter;
import com.dawidkotarba.backendtest.domain.transfer.TransferRequest;
import com.dawidkotarba.backendtest.dto.TransferRequestDto;
import com.dawidkotarba.backendtest.dto.TransferResponseDto;
import com.dawidkotarba.backendtest.facade.TransferFacade;
import com.dawidkotarba.backendtest.service.TimeService;
import com.dawidkotarba.backendtest.service.TransferService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class DefaultTransferFacade implements TransferFacade {

    private final TransferService transferService;
    private final TimeService timeService;

    @Inject
    public DefaultTransferFacade(final TransferService transferService, final TimeService timeService) {
        this.transferService = transferService;
        this.timeService = timeService;
    }

    @Override
    public TransferResponseDto transfer(final TransferRequestDto transferRequestDto) {
        final TransferRequestConverter converter = new TransferRequestConverter(timeService);
        final TransferRequest transferRequest = converter.convert(transferRequestDto);
        final Long transferId = transferService.transfer(transferRequest);

        final TransferResponseDto transferResponseDto = new TransferResponseDto();
        transferResponseDto.setTransferId(transferId);
        return transferResponseDto;
    }
}
