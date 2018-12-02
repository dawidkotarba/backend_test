package com.dawidkotarba.backendtest.service;

import com.dawidkotarba.backendtest.dto.TransferRequestDto;

public interface TransferService {

    void transfer(TransferRequestDto transferRequestDto);
}
