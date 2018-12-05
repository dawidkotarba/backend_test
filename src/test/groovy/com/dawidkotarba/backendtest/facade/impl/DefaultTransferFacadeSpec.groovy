package com.dawidkotarba.backendtest.facade.impl

import com.dawidkotarba.backendtest.dto.TransferRequestDto
import com.dawidkotarba.backendtest.dto.TransferResponseDto
import com.dawidkotarba.backendtest.service.TimeService
import com.dawidkotarba.backendtest.service.TransferService
import spock.lang.Specification

class DefaultTransferFacadeSpec extends Specification {

    private def transferService = Mock(TransferService)
    private def timeService = Mock(TimeService)
    def sut = new DefaultTransferFacade(transferService, timeService)

    def "Should return wrapped transfer reference id"() {
        given:
        def returnedTransferReferenceId = 1L
        transferService.transfer(_) >> returnedTransferReferenceId

        when:
        def transferResponseDto = sut.transfer(new TransferRequestDto()) as TransferResponseDto

        then:
        transferResponseDto.getTransferId() == returnedTransferReferenceId
    }
}
