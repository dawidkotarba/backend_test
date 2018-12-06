package com.dawidkotarba.backendtest.controller.handler;

import com.dawidkotarba.backendtest.exception.ApplicationRuntimeExceptionResponse;
import com.dawidkotarba.backendtest.exception.impl.AccountNotFoundException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;

/**
 * This handler helps to get the pretty formatted response when {@link AccountNotFoundException} is thrown.
 */
@Produces
@Singleton
@Requires(classes = {AccountNotFoundException.class, ExceptionHandler.class})
public class AccountNotFoundExceptionHandler implements ExceptionHandler<AccountNotFoundException, HttpResponse> {
    @Override
    public HttpResponse<ApplicationRuntimeExceptionResponse> handle(final HttpRequest request, final AccountNotFoundException exception) {
        final ApplicationRuntimeExceptionResponse response = new ApplicationRuntimeExceptionResponse(exception);
        return HttpResponse.badRequest(response);
    }
}
