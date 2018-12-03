package com.dawidkotarba.backendtest.service.impl

import com.dawidkotarba.backendtest.service.TimeService
import spock.lang.Specification

class DefaultTimeServiceSpec extends Specification {

    def "Should provide current time"() {
        given:
        TimeService sut = new DefaultTimeService()

        when:
        def result = sut.currentTime

        then:
        result.isAfter(result.minusMinutes(1))
    }
}
