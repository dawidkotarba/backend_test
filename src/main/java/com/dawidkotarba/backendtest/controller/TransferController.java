package com.dawidkotarba.backendtest.controller;


import com.dawidkotarba.backendtest.dto.TransferDto;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;

import javax.validation.Valid;

@Validated
@Controller("/api")
public class TransferController {

    @Post("/transfer")
    public void transferAmount(@Valid final TransferDto transfer) {
        System.out.println(transfer); // TODO: 02.12.18
    }
}
