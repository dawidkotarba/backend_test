package com.dawidkotarba.backendtest.exception;

import java.util.UUID;

public class ApplicationRuntimeExceptionResponse {

    private final UUID errorIdentifier;
    private ExceptionType exceptionType;
    private String message;
    private String detailedMessage;

    public ApplicationRuntimeExceptionResponse(final AbstractApplicationRuntimeException exception) {
        errorIdentifier = exception.getUuid();
        detailedMessage = exception.getMessage();
        exceptionType = exception.getExceptionType();
        message = exception.getExceptionType().getMessage();
    }

    public UUID getErrorIdentifier() {
        return errorIdentifier;
    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(final ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getDetailedMessage() {
        return detailedMessage;
    }

    public void setDetailedMessage(final String detailedMessage) {
        this.detailedMessage = detailedMessage;
    }
}
