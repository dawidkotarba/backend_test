package com.dawidkotarba.backendtest.exception;

import java.util.UUID;

/**
 * This class allows to generate the unique identifier that can be later on used to track down
 * particular issues.
 */
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
