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
│   │   ├── java
│   │   │   └── com/chukwuma/sdet/
│   │   │       ├── config
│   │   │       │     └── ConfigReader.java
│   │   │       │
│   │   │       ├── pages
│   │   │       │     └── LoginPage.java
│   │   │       │
│   │   │       └── utilities
│   │   │
│   │   └── resources
│   │        └── config.properties
│   │
│   └── test
│       ├── java
│       │   └── com/chukwuma/sdet/
│       │       ├── base
│       │       │     └── BaseTest.java
│       │       │
│       │       ├── models
│       │       │     ├── LoginData.java
│       │       │     └── User.java
│       │       │
│       │       ├── utils
│       │       │     └── TestDataLoader.java
│       │       │
│       │       └── tests
│       │            ├── ui
│       │            │     └── LoginTest.java
│       │            │
│       │            ├── api
│       │            └── database
│       │
│       └── resources
│            └── testdata
│                 └── loginData.json
│
├── test-data
├── docs
├── .github/workflows
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

## Configuration Management

This framework externalizes environment configuration using a centralized ConfigReader utility.
Configuration values are loaded from config.properties and may be overridden using environment variables.
This enables seamless switching between development, staging, and production environments.

## Author

**Chukwuma Anyadike**  
Software Development Engineer in Test (SDET)

Automation Engineering | Playwright | Java | CI/CD | API & UI Testing