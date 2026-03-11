# Contributing Guide

## Introduction

This document describes the conventions and practices for contributing tests to the automation framework.

The goal is to maintain a **consistent, maintainable, and scalable test suite**.

---

# Project Structure

Tests should follow the existing project structure.

```
src/test/java/com/chukwuma/sdet
 ├── api/tests
 ├── ui/playwright
 ├── ui/selenium
 └── database
```

New tests should be added to the appropriate module.

---

# Writing UI Tests

UI tests should:

* extend the appropriate BaseTest class
* use Page Object Model
* avoid hardcoded waits
* rely on framework utilities when possible

Example:

```java
public class LoginTest extends BaseTest {

    @Test
    void userCanLogin() {
        page.navigate(ConfigReader.get("BASE_URL"));
        // test steps
    }
}
```

---

# Writing API Tests

API tests should:

* use the service layer
* validate responses with assertions
* avoid direct HTTP calls inside test classes

Example structure:

```
api
 ├── service
 └── tests
```

---

# Test Data

Test data should be managed using:

* `TestDataFactory` for dynamic values
* `TestDataLoader` for static JSON datasets
* `ConfigReader` for environment configuration

Avoid hardcoding credentials or environment URLs.

---

# Failure Diagnostics

Tests automatically capture diagnostic artifacts on failure:

* screenshots
* Playwright trace files
* HTML page source
* execution logs

These artifacts are attached to Allure reports and CI pipeline runs.

---

# CI Pipeline

Tests run automatically in CI environments. The framework detects CI execution using environment variables and automatically runs browsers in headless mode.

---

# Best Practices

When contributing tests:

* keep tests independent
* avoid shared mutable state
* generate unique test data when creating records
* ensure tests can run repeatedly without manual cleanup

Following these guidelines ensures the test suite remains stable and maintainable.
