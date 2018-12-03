package com.dawidkotarba.backendtest.domain.account

import spock.lang.Specification

class AccountSpec extends Specification {

    def "Should add amount to account's balance"() {
        given:
        def account = new Account().withBalance(BigDecimal.TEN)

        when:
        account.add(BigDecimal.ONE)

        then:
        account.getBalance() == BigDecimal.TEN + BigDecimal.ONE
    }

    def "Should be positive if amount is available"() {
        given:
        def account = new Account().withBalance(BigDecimal.TEN)

        when:
        def result = account.isAmountAvailable(BigDecimal.ONE)

        then:
        result == true
    }

    def "Should be negative if amount is not available"() {
        given:
        def account = new Account().withBalance(BigDecimal.ONE)

        when:
        def result = account.isAmountAvailable(BigDecimal.TEN)

        then:
        result == false
    }
}
