package com.dawidkotarba.backendtest.exception;

import java.util.UUID;

public abstract class AbstractApplicationRuntimeException extends RuntimeException {

    private UUID uuid;

    private AbstractApplicationRuntimeException() {
        super();
        init();
    }

    public AbstractApplicationRuntimeException(final String message) {
        super(message);
        init();
    }

    public AbstractApplicationRuntimeException(final Throwable cause) {
        super(cause);
        init();
    }

    public AbstractApplicationRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
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
