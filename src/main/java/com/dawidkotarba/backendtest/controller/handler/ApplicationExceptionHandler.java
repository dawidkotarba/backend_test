package com.dawidkotarba.backendtest.controller.handler;

import com.dawidkotarba.backendtest.exception.AbstractApplicationRuntimeException;
import com.dawidkotarba.backendtest.exception.ApplicationRuntimeExceptionResponse;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {AbstractApplicationRuntimeException.class, ExceptionHandler.class})
public class ApplicationExceptionHandler implements ExceptionHandler<AbstractApplicationRuntimeException, HttpResponse> {

    @Override
    public HttpResponse handle(final HttpRequest request, final AbstractApplicationRuntimeException exception) {
        final ApplicationRuntimeExceptionResponse response = new ApplicationRuntimeExceptionResponse(exception);
        return HttpResponse.serverError(response);
    }
}
