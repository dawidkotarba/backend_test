package com.dawidkotarba.backendtest.exception;

public enum ExceptionType {
    INVALID_REQUEST("The transfer request is invalid."),
    ACCOUNT_NOT_FOUND("Specified account does not exist."),
    INSUFFICIENT_AMOUNT("Insufficient amount to transfer");

    private final String message;

    ExceptionType(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
