package com.dawidkotarba.backendtest.service;

import com.dawidkotarba.backendtest.domain.transfer.TransferRequest;

public interface TransferService {

    Long transfer(TransferRequest transferRequestDto);
}
