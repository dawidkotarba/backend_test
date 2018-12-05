package com.dawidkotarba.backendtest.facade;

import com.dawidkotarba.backendtest.dto.TransferRequestDto;
import com.dawidkotarba.backendtest.dto.TransferResponseDto;

public interface TransferFacade {

    TransferResponseDto transfer(TransferRequestDto transferRequestDto);

}
