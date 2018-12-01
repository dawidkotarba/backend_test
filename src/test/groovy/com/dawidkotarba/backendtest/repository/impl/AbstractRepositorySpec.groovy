package com.dawidkotarba.backendtest.repository.impl

import com.dawidkotarba.backendtest.infrastructure.db.DataStore
import com.dawidkotarba.backendtest.testdata.TestIdentifiableEntity
import spock.lang.Specification

class AbstractRepositorySpec extends Specification {

    def mockDataStore = Mock(DataStore.class)
    def testEntity = new TestIdentifiableEntity()

    def sut = new AbstractRepository<TestIdentifiableEntity>() {
        @Override
        DataStore<TestIdentifiableEntity> getDataStore() {
            return mockDataStore
        }
    }

    def "Should return empty result if entity cannot be found"() {
        given:
        def testId = 1L

        when:
        def result = sut.find(testId)

        then:
        result == Optional.empty()
    }

    def "Should return a result if can be found"() {
        given:
        def testId = 1L
        mockDataStore.get(testId) >> testEntity

        when:
        def result = sut.find(testId)

        then:
        result == Optional.of(testEntity)
    }
}
