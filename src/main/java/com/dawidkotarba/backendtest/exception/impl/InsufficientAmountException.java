package com.dawidkotarba.backendtest.exception.impl;

import com.dawidkotarba.backendtest.exception.AbstractApplicationRuntimeException;
import com.dawidkotarba.backendtest.exception.ExceptionType;

public class InsufficientAmountException extends AbstractApplicationRuntimeException {

    public InsufficientAmountException(final String message) {
        super(message);
    }

    @Override
    protected ExceptionType getExceptionType() {
        return ExceptionType.INTERNL_SERVER_ERROR;
    }
}
