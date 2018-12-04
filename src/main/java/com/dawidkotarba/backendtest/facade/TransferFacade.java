package com.dawidkotarba.backendtest.facade;

import com.dawidkotarba.backendtest.dto.TransferRequestDto;

public interface TransferFacade {

    Long transfer(TransferRequestDto transferRequestDto);

}
