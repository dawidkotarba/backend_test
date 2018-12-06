[![Build Status](https://travis-ci.com/dawidkotarba/backend_test.svg?branch=master)](https://travis-ci.com/dawidkotarba/backend_test)
[![Coverage Status](https://coveralls.io/repos/github/dawidkotarba/backend_test/badge.svg?branch=master)](https://coveralls.io/github/dawidkotarba/backend_test?branch=master)
[![codebeat badge](https://codebeat.co/badges/47ebdf95-528a-45a7-b84a-be42416cb430)](https://codebeat.co/projects/github-com-dawidkotarba-backend_test-master)
# Backend Test

The implementation of tasks to transfer money between two accounts.
The ~70% of test code coverage is a result of a high ratio of model classes' code comparing to the business logic.

#### Tech stack:
- Gradle: build
- Micronaut, Netty: DI + Web Server
- In-memory data store: synchronized map + atomic sequence
- Spock: testing
- Travis: CI
- Checkstyle: code style analysis
- Jacoco + Coveralls: code coverage
- Codebeat: static analysis
- JMeter: check deadlocks (backend_test_deadlock_check.jmx file)

#### Tests and run:
```bash
./gradlew clean test run
```

#### Docker image:
```bash
docker pull dawidkotarba/backend_test
```

#### REST API:
Post request example:
```bash
curl -X POST http://localhost:8080/api/transfer -H 'content-type: application/json' -d '{"title": "Example transfer","amount": "10.25","senderAccountId": "1","receiverAccountId" : "2"}'
```

JSON request:
```json
{
"title": "Example transfer",
"amount": "10.25",
"senderAccountId": "1",
"receiverAccountId" : "2"
}
```

Reply example:
```json
{
    "transferId": 1
}
```

#### Additional links:
- https://hub.docker.com/r/dawidkotarba/backend_test - Docker repository
- build/reports/tests/test/index.html - test reports