package com.dawidkotarba.backendtest.facade;

import com.dawidkotarba.backendtest.dto.TransferRequestDto;

public interface TransferFacade {

    void transfer(TransferRequestDto transferRequestDto);

}
