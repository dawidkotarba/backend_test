package com.dawidkotarba.backendtest.controller

import com.dawidkotarba.backendtest.domain.account.Account
import com.dawidkotarba.backendtest.dto.TransferResponseDto
import com.dawidkotarba.backendtest.repository.Repository
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.inject.qualifiers.Qualifiers
import io.micronaut.runtime.server.EmbeddedServer
import org.junit.BeforeClass
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class TransferControllerSpec extends Specification {

    def FIRST_TRANSFER_REQUEST_SEQUENCE_ID = 1L

    @Shared
    @AutoCleanup
    def embeddedServer = ApplicationContext.run(EmbeddedServer)

    @Shared
    @AutoCleanup
    def client = HttpClient.create(embeddedServer.URL)

    @Shared
    def accountRepository = embeddedServer.applicationContext.getBean(Repository, Qualifiers.byName("accountRepository"))

    @BeforeClass
    def setupAccounts() {
        accountRepository.save(new Account().withBalance(BigDecimal.TEN))
        accountRepository.save(new Account().withBalance(BigDecimal.ONE))
    }

    def "Should return a transfer request ID for a successful transaction"() {
        given:
        def requestBody = createRequestBody(1L, 2L, BigDecimal.TEN)

        when:
        def response = client.toBlocking().retrieve(HttpRequest.POST("/api/transfer", requestBody));
        def parsedResponse = new JsonSlurper().parseText(response)
        def responseDto = new TransferResponseDto(parsedResponse)
        then:
        responseDto.getTransferId() == FIRST_TRANSFER_REQUEST_SEQUENCE_ID
    }

    def createRequestBody(long testSenderAccountId, long tesReceiverAccountId, BigDecimal testAmount) {
        def jsonBuilder = new JsonBuilder()
        jsonBuilder.call(
                {
                    senderAccountId testSenderAccountId
                    receiverAccountId tesReceiverAccountId
                    amount testAmount
                    title "Test title"
                }
        )
        return jsonBuilder.toString()
    }
}
