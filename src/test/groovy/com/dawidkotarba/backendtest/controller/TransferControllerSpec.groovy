package com.dawidkotarba.backendtest.controller

import com.dawidkotarba.backendtest.domain.account.Account
import com.dawidkotarba.backendtest.dto.TransferResponseDto
import com.dawidkotarba.backendtest.repository.Repository
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.inject.qualifiers.Qualifiers
import io.micronaut.runtime.server.EmbeddedServer
import org.junit.Before
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class TransferControllerSpec extends Specification {

    final static def TRANSFER_URL = "/api/transfer"
    final static def FIRST_TRANSFER_REQUEST_SEQUENCE_ID = 1L
    final static def SENDER_INITIAL_BALANCE = BigDecimal.TEN
    final static def RECEIVER_INITIAL_BALANCE = BigDecimal.ONE

    @Shared
    @AutoCleanup
    def embeddedServer = ApplicationContext.run(EmbeddedServer)

    @Shared
    @AutoCleanup
    def client = HttpClient.create(embeddedServer.URL)

    @Shared
    def accountRepository = embeddedServer.applicationContext.getBean(Repository, Qualifiers.byName("accountRepository"))

    @Shared
    Long senderAccountId

    @Shared
    Long receiverAccountId

    @Before
    def setupAccounts() {
        def senderAccount = accountRepository.save(new Account().withBalance(TransferControllerSpec.SENDER_INITIAL_BALANCE))
        senderAccountId = senderAccount.getId()

        def receiverAccount = accountRepository.save(new Account().withBalance(TransferControllerSpec.RECEIVER_INITIAL_BALANCE))
        receiverAccountId = receiverAccount.getId()
    }

    def "Should return a transfer request ID for a successful transaction"() {
        given:
        def requestBody = createRequestBody(senderAccountId, receiverAccountId, BigDecimal.TEN)

        when:
        def response = client.toBlocking().retrieve(HttpRequest.POST(TransferControllerSpec.TRANSFER_URL, requestBody))
        def parsedResponse = new JsonSlurper().parseText(response)
        def responseDto = new TransferResponseDto(parsedResponse)

        then:
        responseDto.getTransferId() == TransferControllerSpec.FIRST_TRANSFER_REQUEST_SEQUENCE_ID
    }

    def "Should subtract transfer amount from sender after a successful transaction"() {
        given:
        def senderAccount = accountRepository.find(senderAccountId) as Optional<Account>
        def transferAmount = BigDecimal.ONE
        def requestBody = createRequestBody(senderAccountId, receiverAccountId, transferAmount)

        when:
        client.toBlocking().retrieve(HttpRequest.POST(TransferControllerSpec.TRANSFER_URL, requestBody))

        then:
        senderAccount.get().getBalance() == TransferControllerSpec.SENDER_INITIAL_BALANCE - transferAmount
    }

    def "Should add transfer amount to receiver after a successful transaction"() {
        given:
        def receiverAccount = accountRepository.find(receiverAccountId) as Optional<Account>
        def transferAmount = BigDecimal.ONE
        def requestBody = createRequestBody(senderAccountId, receiverAccountId, transferAmount)

        when:
        client.toBlocking().retrieve(HttpRequest.POST(TransferControllerSpec.TRANSFER_URL, requestBody))

        then:
        receiverAccount.get().getBalance() == TransferControllerSpec.RECEIVER_INITIAL_BALANCE + transferAmount
    }

    def "Summed account balance should remain same after a successful transaction"() {
        given:
        def senderAccount = accountRepository.find(senderAccountId) as Optional<Account>
        def receiverAccount = accountRepository.find(receiverAccountId) as Optional<Account>
        def summedAmount = senderAccount.get().getBalance() + receiverAccount.get().getBalance()
        def transferAmount = BigDecimal.ONE
        def requestBody = createRequestBody(senderAccountId, receiverAccountId, transferAmount)

        when:
        client.toBlocking().retrieve(HttpRequest.POST(TransferControllerSpec.TRANSFER_URL, requestBody))
        senderAccount = accountRepository.find(senderAccountId) as Optional<Account>
        receiverAccount = accountRepository.find(receiverAccountId) as Optional<Account>
        def summedAmountAfterTransfer = senderAccount.get().getBalance() + receiverAccount.get().getBalance()

        then:
        summedAmount == summedAmountAfterTransfer
    }

    def "Should throw exception response when transfer request amount is higher that sender balance"() {
        given:
        def transferAmount = TransferControllerSpec.SENDER_INITIAL_BALANCE + BigDecimal.ONE
        def requestBody = createRequestBody(senderAccountId, receiverAccountId, transferAmount)

        when:
        client.toBlocking().retrieve(HttpRequest.POST(TransferControllerSpec.TRANSFER_URL, requestBody))

        then:
        thrown HttpClientResponseException
    }

    def "Should throw exception when sender account cannot be found"() {
        given:
        def requestBody = createRequestBody(0L, receiverAccountId, BigDecimal.ONE)

        when:
        client.toBlocking().retrieve(HttpRequest.POST(TransferControllerSpec.TRANSFER_URL, requestBody))

        then:
        thrown HttpClientResponseException
    }

    def "Should throw exception when receiver account cannot be found"() {
        given:
        def requestBody = createRequestBody(senderAccountId, 0L, BigDecimal.ONE)

        when:
        client.toBlocking().retrieve(HttpRequest.POST(TransferControllerSpec.TRANSFER_URL, requestBody))

        then:
        thrown HttpClientResponseException
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
