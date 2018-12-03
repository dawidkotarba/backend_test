package com.dawidkotarba.backendtest.service.validator

import com.dawidkotarba.backendtest.configuration.TransferConfiguration
import com.dawidkotarba.backendtest.domain.transfer.TransferRequest
import com.dawidkotarba.backendtest.exception.impl.InvalidRequestException
import spock.lang.Specification

class TransferRequestValidatorSpec extends Specification {

    TransferConfiguration configuration;
    TransferRequestValidator sut;

    def setup() {
        configuration = new TransferConfiguration()
        sut = new TransferRequestValidator(configuration)
    }

    def "Should validate transfer request"() {
        when:
        sut.validate(transferRequest)

        then:
        thrown expectedException

        where:
        transferRequest                                             | expectedException
        new TransferRequest(-1L, 1L, BigDecimal.ONE, "test")        | InvalidRequestException
        new TransferRequest(1L, -1L, BigDecimal.ONE, "test")        | InvalidRequestException
        new TransferRequest(1L, 1L, BigDecimal.ZERO, "test")        | InvalidRequestException
        new TransferRequest(1L, 1L, BigDecimal.ONE, "")             | InvalidRequestException
        new TransferRequest(1L, 1L, new BigDecimal("1.000001"), "") | InvalidRequestException
    }

    def "Should not throw any exception for a valid transfer request"() {
        given:
        def transferRequest = new TransferRequest(1L, 1L, BigDecimal.ONE, "test")

        when:
        sut.validate(transferRequest)

        then:
        notThrown InvalidRequestException
    }
}
