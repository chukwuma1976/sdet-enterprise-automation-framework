# 🚀 Enterprise Test Automation Framework
![CI Pipeline](https://github.com/chukwuma1976/sdet-enterprise-automation-framework/actions/workflows/ci-pipeline.yml/badge.svg)

## Playwright (Java) | JUnit 5 | CI/CD | Parallel-Ready

A production-style UI test automation framework built with **Java + Playwright** designed for **scalability, determinism, and CI reliability.**

This project demonstrates how enterprise SDET teams architect automation frameworks — focusing on lifecycle control, isolation, parallel execution, and flakiness mitigation.

# 🏗 Architecture Overview

```text
                        ┌────────────────────────────┐
                        │        Test Classes        │
                        │  (Login, CRUD, etc.)       │
                        └─────────────┬──────────────┘
                                      │
                        ┌─────────────▼──────────────┐
                        │         BaseTest           │
                        │  Browser Lifecycle Mgmt    │
                        └─────────────┬──────────────┘
                                      │
              ┌───────────────────────▼───────────────────────┐
              │              Page Objects (POM)               │
              │ LoginPage | DashboardPage | EmployeePage etc. │
              └───────────────────────┬───────────────────────┘
                                      │
                      ┌───────────────▼───────────────┐
                      │        Test Data Layer        │
                      │ JSON Models | Data Factories  │
                      └───────────────────────────────┘
```

# 📊 Execution Metrics

Execution times measured locally (parallel enabled):

```text
| Suite Type | Command                        | Tests | Execution Time |
| ---------- | ------------------------------ | ----- | -------------- |
| Smoke      | `mvn test -Dgroups=smoke`      | 9     | ~40 seconds    |
| Regression | `mvn test -Dgroups=regression` | 34    | ~2 minutes     |
| API Only   | `mvn test -Dgroups=api`        | 12    | ~8 seconds     |
| UI Only    | `mvn test -Dgroups=ui`         | 31    | ~2 minutes 42s |

```

## Key Principles

* Separation of concerns
* One `BrowserContext` per test
* No shared mutable state
* Deterministic test execution
* CI-first architecture
* Parallel-safe design
* Failure diagnostics built-in

# 🧰 Technology Stack

```text
| Layer             | Technology                 |
| ----------------- | -------------------------- |
| Language          | Java 17                    |
| UI Automation     | Playwright (Java)          |
| Test Runner       | JUnit 5                    |
| Build Tool        | Maven                      |
| CI/CD             | GitHub Actions             |
| Design Pattern    | Page Object Model          |
| Test Data         | JSON + Model Mapping       |
| Config Management | Properties + Env Variables |

```
# 🔄 Browser Lifecycle Management

```text
@BeforeAll   → Launch Playwright
@BeforeEach  → Create BrowserContext
@BeforeEach  → Create Page
@Test        → Execute Test Logic
@AfterEach   → Capture Screenshot on Failure
@AfterEach   → Close Context
@AfterAll    → Close Browser
```

Key design decisions:

* Context-per-test isolation
* Screenshot capture via TestExecutionExceptionHandler
* Cleanup guaranteed even during failure
* No cross-test contamination

# ⚡ Parallel Execution Strategy

The framework is designed for safe parallel execution.

## How it works:

* New `BrowserContext per test`
* No static Page objects
* No shared test state
* Deterministic test data
* Isolated teardown logic

Parallel execution is configured via:

```text
junit.jupiter.execution.parallel.enabled=true
```
Benefits:
* Faster feedback cycles
* Reliable CI execution
* Order-independent tests
* Enterprise scalability

# 🧪 Current Automated Coverage

## 🔐Authentication Suite

* ✅ Successful login
* ✅ Invalid username
* ✅ Invalid password
* ✅ Required field validation
* ✅ Negative authentication scenarios

Designed to validate:

* UI state transitions
* Error messaging
* Input validation
* Access control behavior

# 📸 Failure Observability Strategy

To improve CI debuggability:

* Screenshot captured at failure point
* Uses TestExecutionExceptionHandler (not TestWatcher)
* Avoids lifecycle timing issues
* Failure artifacts available in CI logs

Result: Deterministic, observable, and debuggable automation.

# 🛠 Flakiness Mitigation Strategy

UI automation fails when synchronization is weak. This framework prevents flakiness by:

* Leveraging Playwright’s built-in auto-waiting
* Avoiding `Thread.sleep()`
* Using semantic, label-based locators
* Avoiding `nth()` selectors
* Context isolation per test
* Deterministic test data generation
* Explicit state-based assertions

Result: Stable, repeatable CI execution.

# ▶️ Running Tests Locally

## 1️⃣ Clone the Repository
```text
git clone https://github.com/chukwuma1976/sdet-enterprise-automation-framework.git
cd sdet-enterprise-automation-framework
```
## 2️⃣ Execute Tests
```text
mvn clean test
```
## 3️⃣ Debug Mode (Headed Browser)
Set in `config.properties`:
```text
headless=false
```

# 🤖 CI Execution (GitHub Actions)

Pipeline file:
```text
.github/workflows/ci-pipeline.yml
```
CI Pipeline Steps:

1. Checkout repository
2. Set up Java 17
3. Install dependencies
4. Run Maven test suite
5. Run in headless mode
6. Fail pipeline on test failure

Designed for deterministic, headless execution.

# 📁 Project Structure

```text
src
├── main/java/com/chukwuma/sdet
│   ├── config
│   ├── pages
│   └── utilities
│
├── test/java/com/chukwuma/sdet
│   ├── base
│   ├── models
│   ├── utils
│   └── tests
│        ├── ui
│        ├── api
│        └── database
```

Scalable for:

* UI Automation
* API Automation
* Database Validation
* Cross-layer verification

# ⚙️ Configuration Management

* Centralized `ConfigReader`
* `config.properties`
* Environment variable override support
* Designed for multi-environment execution (dev / staging / prod)
* CI-friendly configuration injection

# 🛣 Roadmap

* ✅ UI Authentication Suite
* 🔄 Full CRUD automation
* 🔜 API automation layer
* 🔜 Integrated reporting
* 🔜 Dockerized execution
* 🔜 Cross-browser support
* 🔜 Storage state optimization strategy

## 📊 Test Report

Live Allure Report:
```text
https://chukwuma1976.github.io/sdet-enterprise-automation-framework
```

# 🎯 Why This Project Stands Out

This is not a tutorial project.

It demonstrates:

* Enterprise automation architecture
* Parallel-safe framework design
* CI-integrated execution
* Deterministic lifecycle management
* Failure observability engineering
* Clean separation of concerns

Designed and implemented with real-world SDET practices.

# 👤 Author
## Chukwuma Anyadike
Software Development Engineer in Test (SDET)

Automation Engineering | Playwright | Java | CI/CD | Parallel Architecture | API & UI Testing