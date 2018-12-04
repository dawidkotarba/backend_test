package com.dawidkotarba.backendtest.controller

import com.dawidkotarba.backendtest.domain.account.Account
import com.dawidkotarba.backendtest.repository.Repository
import groovy.json.JsonBuilder
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.HttpClient
import io.micronaut.inject.qualifiers.Qualifiers
import io.micronaut.runtime.server.EmbeddedServer
import org.junit.BeforeClass
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class TransferControllerSpec extends Specification {

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

    def "Should transfer money"() {
        given:
        def requestBody = createRequestBody(1L, 2L, BigDecimal.TEN)

        when:
        HttpResponse response = client.toBlocking().exchange(HttpRequest.POST("/api/transfer", requestBody));

        then:
        response.getStatus().getCode() == 200
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
