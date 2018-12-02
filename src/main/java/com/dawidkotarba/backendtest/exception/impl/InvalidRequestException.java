package com.dawidkotarba.backendtest.exception.impl;

import com.dawidkotarba.backendtest.exception.AbstractApplicationRuntimeException;
import com.dawidkotarba.backendtest.exception.ExceptionType;

public class InvalidRequestException extends AbstractApplicationRuntimeException {

    public InvalidRequestException(final String message) {
        super(message);
    }

    public InvalidRequestException(final Throwable cause) {
        super(cause);
    }

    public InvalidRequestException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Override
    protected ExceptionType getExceptionType() {
        return ExceptionType.INVALID_REQUEST;
    }
}
