package com.dawidkotarba.backendtest.domain

import com.dawidkotarba.backendtest.testdata.TestIdentifiableEntity
import spock.lang.Specification

class IdentifiableSpec extends Specification {

    def entity = new TestIdentifiableEntity()

    def "Should generate UUID"() {
        expect:
        entity.getUuid() != null
    }

    def "Should allow to set ID once"() {
        when:
        entity.setId(1L)

        then:
        notThrown IllegalArgumentException
    }

    def "Should not allow reassignment of ID"() {
        given:
        entity.setId(1L)

        when:
        entity.setId(2L)

        then:
        thrown IllegalArgumentException
    }
}
