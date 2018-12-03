package com.dawidkotarba.backendtest.controller;


import com.dawidkotarba.backendtest.dto.TransferRequestDto;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;

import javax.validation.Valid;

@Validated
@Controller("/api")
public class TransferController {

//    private final TransferService transferService;

//    @Inject
//    public TransferController(final TransferService transferService) {
//        this.transferService = transferService;
//    }

    @Post("/transfer")
    public void transferAmount(@Valid final TransferRequestDto request) {
//        transferService.transfer(request);
    }
}
