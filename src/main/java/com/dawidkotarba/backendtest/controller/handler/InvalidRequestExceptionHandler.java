package com.dawidkotarba.backendtest.controller.handler;

import com.dawidkotarba.backendtest.exception.ApplicationRuntimeExceptionResponse;
import com.dawidkotarba.backendtest.exception.impl.InvalidRequestException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {InvalidRequestException.class, ExceptionHandler.class})
public class InvalidRequestExceptionHandler implements ExceptionHandler<InvalidRequestException, HttpResponse> {

    @Override
    public HttpResponse<ApplicationRuntimeExceptionResponse> handle(final HttpRequest request, final InvalidRequestException exception) {
        final ApplicationRuntimeExceptionResponse response = new ApplicationRuntimeExceptionResponse(exception);
        return HttpResponse.badRequest(response);
    }
}
