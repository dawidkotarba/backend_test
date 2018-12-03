package com.dawidkotarba.backendtest.domain.transfer;

public enum TransferStatus {
    STARTED,
    INSUFFICIENT_AMOUNT,
    AMOUNT_SUBSTRACTED_FROM_SENDER,
    SUCCESS
}
