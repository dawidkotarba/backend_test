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
        def entities = createMultipleTestEntities(3)

        when:
        def result = sut.saveAll(entities)

        then:
        sut.count() == 3
        result.containsAll(entities)
    }

    def "Should delete entity"() {
        given:
        sut.save(testEntity)
        sut.count() == 0

        when:
        sut.delete(1)

        then:
        sut.count() == 0
    }

    def "Should delete all entities"() {
        given:
        sut.saveAll(createMultipleTestEntities(3))
        sut.count() == 3

        when:
        sut.deleteAll()

        then:
        sut.count() == 0
    }

    List<TestEntity> createMultipleTestEntities(int count) {
        def entities = [] as List
        1.upto(count, {
            entities.add(new TestEntity("testEntity$it"))
        })
        return entities
    }

    class TestEntity extends Identifiable {
        String testName

        TestEntity(String testName) {
            this.testName = testName
        }
    }
}
