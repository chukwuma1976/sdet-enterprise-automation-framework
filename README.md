# SDET Enterprise Automation Framework

# Enterprise Test Automation Framework

This project is a Java-based end-to-end test automation framework built to simulate how UI automation is implemented in a real enterprise QA environment.

The framework automates the OrangeHRM demo application:
https://opensource-demo.orangehrmlive.com

It demonstrates scalable UI test design using the Page Object Model, reusable test setup, and continuous integration execution through GitHub Actions.

## Technologies

* Java 17
* Playwright (UI Automation)
* JUnit 5 (Test Runner)
* Maven (Build Management)
* GitHub Actions (CI Pipeline)

## What This Project Demonstrates

* Automated regression testing
* Page Object Model design pattern
* Cross-browser UI testing
* Headless execution in CI environments
* Continuous Integration pipeline configuration

![CI](https://github.com/chukwuma1976/sdet-enterprise-automation-framework/actions/workflows/ci-pipeline.yml/badge.svg)

Java + Playwright + JUnit5 + Maven automation framework with GitHub Actions continuous integration.
Runs UI tests automatically on every push.

# File structure

sdet-enterprise-automation-framework
│
├── src
│   ├── main
│   │   └── java
│   │       └── com/chukwuma/sdet/
│   │           ├── pages
│   │           ├── utilities
│   │           └── config
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
│               └── base
│
├── test-data
├── docs
├── .github/workflows
└── pom.xml