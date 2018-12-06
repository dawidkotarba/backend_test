package com.dawidkotarba.backendtest.service.validator

import com.dawidkotarba.backendtest.configuration.TransferConfiguration
import com.dawidkotarba.backendtest.domain.transfer.TransferRequest
import com.dawidkotarba.backendtest.exception.impl.InvalidRequestException
import com.dawidkotarba.backendtest.service.validator.impl.TransferRequestValidator
import spock.lang.Specification

class TransferRequestValidatorSpec extends Specification {

    def configuration = new TransferConfiguration()
    def sut = new TransferRequestValidator(configuration)

    def "Should validate transfer request"() {
        when:
        sut.validate(transferRequest)

        then:
        thrown expectedException

        where:
        transferRequest                                      | expectedException
        new TransferRequest(-1L, 1L, BigDecimal.ONE, "test") | InvalidRequestException
        new TransferRequest(1L, -1L, BigDecimal.ONE, "test") | InvalidRequestException
        new TransferRequest(1L, 2L, BigDecimal.ZERO, "test") | InvalidRequestException
        new TransferRequest(1L, 2L, BigDecimal.ONE, "")      | InvalidRequestException
        new TransferRequest(1L, 2L, BigDecimal.ONE, "")      | InvalidRequestException
        new TransferRequest(1L, 1L, BigDecimal.ONE, "")      | InvalidRequestException
        new TransferRequest(null, 2L, BigDecimal.ONE, "")    | InvalidRequestException
        new TransferRequest(1L, null, BigDecimal.ONE, "")    | InvalidRequestException
        new TransferRequest(1L, 2L, null, "")        | InvalidRequestException
        new TransferRequest(1L, 2L, BigDecimal.ONE, null)    | InvalidRequestException
    }

    def "Should not throw any exception for a valid transfer request"() {
        given:
        def transferRequest = new TransferRequest(1L, 2L, BigDecimal.ONE, "test")

        when:
        sut.validate(transferRequest)

        then:
        notThrown InvalidRequestException
    }
}
