package com.dawidkotarba.backendtest.service;

import com.dawidkotarba.backendtest.domain.transfer.TransferRequest;

public interface TransferService {

    void transfer(TransferRequest transferRequestDto);
}
