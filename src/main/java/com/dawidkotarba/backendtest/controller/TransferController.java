package com.dawidkotarba.backendtest.controller;


import com.dawidkotarba.backendtest.dto.TransferRequestDto;
import com.dawidkotarba.backendtest.dto.TransferResponseDto;
import com.dawidkotarba.backendtest.facade.TransferFacade;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;

import javax.inject.Inject;
import javax.validation.Valid;

import static com.dawidkotarba.backendtest.constants.Constants.REST_API_PREFIX;

/**
 * This is the main and single controller in the application.
 */
@Validated
@Controller(REST_API_PREFIX)
class TransferController {

    private final TransferFacade transferFacade;

    @Inject
    public TransferController(final TransferFacade transferFacade) {
        this.transferFacade = transferFacade;
    }

    @Post("/transfer")
    public HttpResponse<TransferResponseDto> transferAmount(@Valid final TransferRequestDto request) {
        final TransferResponseDto transferResponseDto = transferFacade.transfer(request);
        return HttpResponse.ok(transferResponseDto);
    }
}
