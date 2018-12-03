package com.dawidkotarba.backendtest.controller;


import com.dawidkotarba.backendtest.dto.TransferRequestDto;
import com.dawidkotarba.backendtest.facade.TransferFacade;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;

import javax.inject.Inject;
import javax.validation.Valid;

@Validated
@Controller("/api")
public class TransferController {

    private final TransferFacade transferFacade;

    @Inject
    public TransferController(final TransferFacade transferFacade) {
        this.transferFacade = transferFacade;
    }

    @Post("/transfer")
    public void transferAmount(@Valid final TransferRequestDto request) {
        transferFacade.transfer(request);
    }
}
