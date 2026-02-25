# Enterprise Test Automation Framework (SDET) — Playwright + Java + CI/CD

A production-style UI test automation framework built with **Java Playwright** that executes automatically inside a CI/CD pipeline.

The project demonstrates how an SDET team designs reliable, maintainable automated tests in a real enterprise environment — not tutorial-style scripts.

The framework validates the OrangeHRM web application and focuses on test stability, repeatability, and maintainable architecture.

## Engineering Goals

* Eliminate flaky UI tests through explicit synchronization and locator strategy
* Maintain a scalable Page Object Model architecture
* Provide positive and negative authentication coverage
* Execute tests automatically on every commit via CI
* Support fast local debugging during development

## CI Status
![Playwright Tests](https://github.com/chukwuma1976/sdet-enterprise-automation-framework/actions/workflows/ci-pipeline.yml/badge.svg)

## Technology Stack

```text
| Area              | Technology                                       |
| ------------------| -------------------------------------------------|
| Language          | Java 17                                          |
| UI Automation     | Playwright (Java, Headless Browser Automation)   |
| Test Runner       | JUnit 5                                          |
| Build Tool        | Maven                                            |
| CI/CD             | GitHub Actions                                   |
| Design Pattern    | Page Object Model                                |
```

## Running the Test Suite

Clone the repository and execute:
```text
mvn clean test
```

### Current Automated Coverage

* Successful login (happy path authentication)
* Invalid username/password combinations
* Required field validation
* Negative authentication scenarios

# File structure

```text
sdet-enterprise-automation-framework
│
├── src
│   ├── main
│   │   └── java
│   │       └── com/chukwuma/sdet/
│   │           ├── pages              → Page Objects (UI behavior)
│   │           ├── config             → Config readers & environment setup
│   │           └── utilities          → Shared reusable helpers
│   │
│   └── test
│       ├── java
│       │   └── com/chukwuma/sdet/
│       │       ├── base               → BaseTest (browser setup/teardown)
│       │       ├── models             → Test data models (POJOs)
│       │       │     ├── LoginData.java
│       │       │     └── User.java
│       │       │
│       │       ├── utils              → Test utilities (data loaders, helpers)
│       │       │     └── TestDataLoader.java
│       │       │
│       │       └── tests
│       │            ├── ui
│       │            │    ├── playwright
│       │            │    │     ├── SuccessfulLoginTest.java
│       │            │    │     └── InvalidLoginTest.java
│       │            │    │
│       │            │    └── selenium
│       │            │
│       │            ├── api
│       │            └── database
│       │
│       └── resources
│            ├── config.properties
│            └── testdata
│                  └── loginData.json
│
├── test-data                         → Optional external datasets (future expansion)
├── docs                              → Architecture notes & documentation
├── .github/workflows                 → CI/CD pipelines
└── pom.xml
```

## Engineering Approach

The framework prioritizes:

* Clear separation of concerns
* Reusable browser lifecycle management
* Stable element synchronization strategies
* Scalable test organization for future API and database automation
* CI validation on every push

## Planned Enhancements

* API automation
* Test reporting
* Parallel execution
* Containerized test execution

## Author

**Chukwuma Anyadike**  
Software Development Engineer in Test (SDET)

Automation Engineering | Playwright | Java | CI/CD | API & UI Testing