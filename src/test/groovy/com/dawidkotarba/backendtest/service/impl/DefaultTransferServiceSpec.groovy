package com.dawidkotarba.backendtest.service.impl

import com.dawidkotarba.backendtest.domain.account.Account
import com.dawidkotarba.backendtest.domain.transfer.TransferRequest
import com.dawidkotarba.backendtest.exception.impl.InvalidRequestException
import com.dawidkotarba.backendtest.repository.Repository
import com.dawidkotarba.backendtest.service.TransferService
import com.dawidkotarba.backendtest.service.validator.Validator
import spock.lang.Specification

class DefaultTransferServiceSpec extends Specification {

    Repository<Account> accountRepository
    Repository<TransferRequest> transferRepository
    Validator requestValidator

    TransferService sut

    long senderAccountId
    long receiverAccountId
    BigDecimal defaultBalance

    Account senderAccount
    Account receiverAccount

    def setup() {
        accountRepository = Mock(Repository)
        transferRepository = Mock(Repository)
        requestValidator = Mock(Validator)
        sut = new DefaultTransferService(accountRepository, transferRepository, requestValidator)

        setupAccounts()
    }

    def setupAccounts() {
        senderAccountId = 1L
        receiverAccountId = 2L
        defaultBalance = BigDecimal.TEN

        senderAccount = new Account().withBalance(defaultBalance)
        senderAccount.setId(senderAccountId)

        receiverAccount = new Account().withBalance(defaultBalance)
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
        thrown InvalidRequestException
    }

    def "Should throw exception when receiver's account cannot be found"() {
        given:
        accountRepository.find(senderAccountId) >> Optional.of(senderAccount)
        accountRepository.find(receiverAccountId) >> Optional.empty()

        def transferRequest = new TransferRequest(1L, 2L, BigDecimal.TEN, "test")

        when:
        sut.transfer(transferRequest)

        then:
        thrown InvalidRequestException
    }

    def "Should throw an exception when insufficient amount for a transfer"() {
        given:
        accountRepository.find(senderAccountId) >> Optional.of(senderAccount)
        accountRepository.find(receiverAccountId) >> Optional.of(receiverAccount)

        def transferRequest = new TransferRequest(1L, 2L, new BigDecimal(100), "test")

        when:
        sut.transfer(transferRequest)

        then:
        thrown InvalidRequestException
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
