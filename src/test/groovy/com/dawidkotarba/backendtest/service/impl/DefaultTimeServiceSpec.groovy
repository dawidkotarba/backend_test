package com.dawidkotarba.backendtest.service.impl


import spock.lang.Specification

class DefaultTimeServiceSpec extends Specification {

    def sut = new DefaultTimeService()

    def "Should provide current time"() {
        when:
        def result = sut.currentTime

        then:
        result.isAfter(result.minusMinutes(1))
    }
}
