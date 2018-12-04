package com.dawidkotarba.backendtest.exception;

import java.util.UUID;

public abstract class AbstractApplicationRuntimeException extends RuntimeException {

    private UUID uuid;

    public AbstractApplicationRuntimeException(final String message) {
        super(message);
        init();
    }

    protected abstract ExceptionType getExceptionType();

    private void init() {
        uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }
}
