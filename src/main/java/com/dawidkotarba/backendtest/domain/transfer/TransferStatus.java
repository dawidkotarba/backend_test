package com.dawidkotarba.backendtest.domain.transfer;

public enum TransferStatus {
    STARTED,
    INSUFFICIENT_AMOUNT,
    AMOUNT_SUBTRACTED_FROM_SENDER,
    TRANSFERRED
}
