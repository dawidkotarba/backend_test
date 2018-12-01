package com.dawidkotarba.backendtest.db.impl

import com.dawidkotarba.backendtest.domain.Identifiable
import com.dawidkotarba.backendtest.infrastructure.db.impl.InMemoryDataStore
import spock.lang.Specification

class InMemoryDataStoreSpec extends Specification {

    def sut = new InMemoryDataStore()

    def testEntityName = "testEntityName"
    def testEntity = new TestEntity(testEntityName)

    def "Should create entity"() {
        given:
        sut.count() == 0

        when:
        def result = sut.save(testEntity)

        then:
        result.getId() != null
        and:
        sut.count() == 1
    }

    def "Should find entity by id"() {
        given:
        sut.save(testEntity)

        when:
        def result = sut.get(1)

        then:
        result == testEntity

    }

    def "Should find all entities"() {
        given:
        def testEntity2 = new TestEntity("testEntity2")
        def testEntity3 = new TestEntity("testEntity3")

        when:
        def result = sut.saveAll(testEntity, testEntity2, testEntity3)

        then:
        sut.count() == 3
        result.containsAll(testEntity, testEntity2, testEntity3)

    }

    class TestEntity extends Identifiable {
        String testName

        TestEntity(String testName) {
            this.testName = testName
        }
    }
}
