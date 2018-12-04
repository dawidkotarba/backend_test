package com.dawidkotarba.backendtest.service.impl

import com.dawidkotarba.backendtest.domain.account.Account
import com.dawidkotarba.backendtest.domain.transfer.TransferRequest
import com.dawidkotarba.backendtest.exception.impl.AccountNotFoundException
import com.dawidkotarba.backendtest.exception.impl.InsufficientAmountException
import com.dawidkotarba.backendtest.exception.impl.InvalidRequestException
import com.dawidkotarba.backendtest.repository.Repository
import com.dawidkotarba.backendtest.service.validator.Validator
import spock.lang.Specification

class DefaultTransferServiceSpec extends Specification {

    def accountRepository = accountRepository = Mock(Repository)
    def transferRepository = Mock(Repository)
    def requestValidator = Mock(Validator)
    def sut = new DefaultTransferService(accountRepository, transferRepository, requestValidator)

    def senderAccountId = 1L
    def receiverAccountId = 2L
    def defaultBalance = BigDecimal.TEN

    def senderAccount = new Account().withBalance(defaultBalance)
    def receiverAccount = new Account().withBalance(defaultBalance)

    def setup() {
        senderAccount.setId(senderAccountId)
        receiverAccount.setId(senderAccountId)
    }

    def "Should validate the request"() {
        given:
        accountRepository.find(senderAccountId) >> Optional.of(senderAccount)
        accountRepository.find(receiverAccountId) >> Optional.of(receiverAccount)

        def transferRequest = new TransferRequest(1L, 2L, BigDecimal.TEN, "test")

        when:
        sut.transfer(transferRequest)

        then:
        1 * requestValidator.validate(transferRequest)
    }

    def "Should throw exception when sender's account cannot be found"() {
        given:
        accountRepository.find(senderAccountId) >> Optional.empty()

        def transferRequest = new TransferRequest(1L, 2L, BigDecimal.TEN, "test")

        when:
        sut.transfer(transferRequest)

        then:
        thrown AccountNotFoundException
    }

    def "Should throw exception when receiver's account cannot be found"() {
        given:
        accountRepository.find(senderAccountId) >> Optional.of(senderAccount)
        accountRepository.find(receiverAccountId) >> Optional.empty()

        def transferRequest = new TransferRequest(1L, 2L, BigDecimal.TEN, "test")

        when:
        sut.transfer(transferRequest)

        then:
        thrown AccountNotFoundException
    }

    def "Should throw an exception when insufficient amount for a transfer"() {
        given:
        accountRepository.find(senderAccountId) >> Optional.of(senderAccount)
        accountRepository.find(receiverAccountId) >> Optional.of(receiverAccount)

        def transferRequest = new TransferRequest(1L, 2L, new BigDecimal(100), "test")

        when:
        sut.transfer(transferRequest)

        then:
        thrown InsufficientAmountException
    }

    def "Should not throw an exception when insufficient amount for a transfer"() {
        given:
        accountRepository.find(senderAccountId) >> Optional.of(senderAccount)
        accountRepository.find(receiverAccountId) >> Optional.of(receiverAccount)

        def transferRequest = new TransferRequest(1L, 2L, BigDecimal.ONE, "test")

        when:
        sut.transfer(transferRequest)

        then:
        notThrown InvalidRequestException
    }

    def "Should subtract amount from sender"() {
        given:
        accountRepository.find(senderAccountId) >> Optional.of(senderAccount)
        accountRepository.find(receiverAccountId) >> Optional.of(receiverAccount)

        def transferAmount = BigDecimal.ONE
        def transferRequest = new TransferRequest(1L, 2L, transferAmount, "test")

        when:
        sut.transfer(transferRequest)

        then:
        senderAccount.getBalance() == defaultBalance.subtract(transferAmount)
    }

    def "Should transfer amount to receiver"() {
        given:
        accountRepository.find(senderAccountId) >> Optional.of(senderAccount)
        accountRepository.find(receiverAccountId) >> Optional.of(receiverAccount)

        def transferAmount = BigDecimal.ONE
        def transferRequest = new TransferRequest(1L, 2L, transferAmount, "test")

        when:
        sut.transfer(transferRequest)

        then:
        receiverAccount.getBalance() == defaultBalance.add(transferAmount)
    }
}
