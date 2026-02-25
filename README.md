# Enterprise SDET Automation Framework (Playwright + Java)

A production-style UI automation framework built to simulate how automated testing is structured inside a real enterprise QA organization.

This project demonstrates scalable, maintainable test automation using modern tooling and CI-driven execution.

The framework automates the OrangeHRM demo application:
https://opensource-demo.orangehrmlive.com

## This repository is not a tutorial project.

It represents how a QA engineering team designs automation for:

* reliability
* maintainability
* scalability
* CI integration
* deterministic test execution (no flaky behavior)

The focus is on architecture and synchronization strategy — not just making tests pass.

## What This Framework Demonstrates

* Page Object Model (POM) architecture
* Parameterized test execution (JUnit 5)
* Positive and negative authentication validation
* Explicit synchronization to prevent flaky UI tests
* Cross-browser execution support
* Headless execution in CI environments
* Maven-based build lifecycle
* GitHub Actions CI pipeline integration

## Technology Stack

```text
| Area           | Technology        |
| -------------- | ----------------- |
| Language       | Java 17           |
| UI Automation  | Playwright (Java) |
| Test Runner    | JUnit 5           |
| Build Tool     | Maven             |
| CI/CD          | GitHub Actions    |
| Design Pattern | Page Object Model |
```

## Running the Test Suite

Clone the repository and execute:
`mvn clean test`

The suite runs automated login scenarios including:

* Valid authentication
* Invalid credentials
* Required field validation
* Negative login coverage

# File structure

```text
sdet-enterprise-automation-framework
│
├── src
│   ├── main
│   │   └── java
│   │       └── com/chukwuma/sdet/
│   │           ├── pages        → Page Objects (application behavior)
│   │           ├── utilities    → Reusable helpers
│   │           └── config       → Environment configuration
│   │
│   └── test
│       └── java
│           └── com/chukwuma/sdet/
│               ├── tests
│               │    ├── ui
│               │    │    ├── playwright
│               │    │    └── selenium
│               │    ├── api
│               │    └── database
│               └── base          → Browser setup & teardown
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

This repository serves as a foundation for expanding into:

* API automation
* Test reporting
* Parallel execution
* Containerized test execution

## Author
Built and maintained by Chukwuma Anyadike
SDET | Java | Automation Engineering | CI/CD