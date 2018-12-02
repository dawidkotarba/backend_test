package com.dawidkotarba.backendtest.exception.impl;

import com.dawidkotarba.backendtest.exception.AbstractApplicationRuntimeException;
import com.dawidkotarba.backendtest.exception.ExceptionType;

public class InvalidRequestException extends AbstractApplicationRuntimeException {

    @Override
    protected ExceptionType getExceptionType() {
        return ExceptionType.INVALID_REQUEST;
    }
}
