package com.dawidkotarba.backendtest.converter.impl


import com.dawidkotarba.backendtest.dto.TransferRequestDto
import com.dawidkotarba.backendtest.service.TimeService
import spock.lang.Specification

import java.time.LocalDateTime

class TransferRequestConverterSpec extends Specification {

    def timeService = Mock(TimeService)
    def sut = new TransferRequestConverter(timeService)

    def setup() {
        timeService.getCurrentTime() >> LocalDateTime.now()
    }

    def "Should convert transfer dto to entity"() {
        given:
        def testSenderAccountId = 1L
        def testReceiverAccountId = 2L
        def testAmount = BigDecimal.TEN
        def testTitle = "test title"

        def dto = new TransferRequestDto()
        dto.with {
            senderAccountId = testSenderAccountId
            receiverAccountId = testReceiverAccountId
            amount = testAmount
            title = testTitle
        }

        when:
        def entity = sut.convert(dto)

        then:
        entity.senderAccountId == testSenderAccountId
        entity.receiverAccountId == testReceiverAccountId
        entity.amount == testAmount
        entity.title == testTitle
        entity.timestamp != null
    }
}
