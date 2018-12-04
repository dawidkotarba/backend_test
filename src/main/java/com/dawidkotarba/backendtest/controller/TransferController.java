package com.dawidkotarba.backendtest.controller;


import com.dawidkotarba.backendtest.dto.TransferRequestDto;
import com.dawidkotarba.backendtest.facade.TransferFacade;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;

import javax.inject.Inject;
import javax.validation.Valid;

import static com.dawidkotarba.backendtest.constants.Constants.REST_API_PREFIX;

@Validated
@Controller(REST_API_PREFIX)
class TransferController {

    private final TransferFacade transferFacade;

    @Inject
    public TransferController(final TransferFacade transferFacade) {
        this.transferFacade = transferFacade;
    }

    @Post("/transfer")
    public Long transferAmount(@Valid final TransferRequestDto request) {
        return transferFacade.transfer(request);
    }
}
