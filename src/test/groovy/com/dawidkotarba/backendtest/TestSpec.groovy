package com.dawidkotarba.backendtest

import spock.lang.Specification

class TestSpec extends Specification {

    def "one plus one should equal two"() {
        expect:
        1 + 1 == 2
    }
}
