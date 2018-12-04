package com.dawidkotarba.backendtest.exception.impl;

import com.dawidkotarba.backendtest.exception.AbstractApplicationRuntimeException;
import com.dawidkotarba.backendtest.exception.ExceptionType;

public class AccountNotFoundException extends AbstractApplicationRuntimeException {

    public AccountNotFoundException(final String message) {
        super(message);
    }

    @Override
    protected ExceptionType getExceptionType() {
        return ExceptionType.ACCOUNT_NOT_FOUND;
    }
}
