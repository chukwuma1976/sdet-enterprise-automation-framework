# 🚀 Enterprise Test Automation Framework

## Playwright (Java) | JUnit 5 | CI/CD | Parallel-Ready

A production-style UI test automation framework built with **Java + Playwright** that executes automatically inside a CI/CD pipeline.

The project demonstrates how enterprise teams design **reliable, maintainable automated tests in a real enterprise environment** — not tutorial-style scripts.

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
## Key Principles

* Separation of concerns
* One BrowserContext per test
* No shared mutable state
* Deterministic test execution
* CI-first architecture

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
# ⚡ Parallel Execution Strategy

The framework is designed for safe parallel execution.

## How it works:

* New **BrowserContext per test**
* No static Page objects
* No shared test state
* Unique deterministic test data
* Isolated cleanup logic

Parallel execution is configured via:

```text
junit.jupiter.execution.parallel.enabled=true
```
Benefits:
* Faster execution
* Test isolation
* Enterprise scalability
* CI-friendly performance

# 🧪 Current Automated Coverage

##Authentication Suite

* ✅ Successful login
* ✅ Invalid username
* ✅ Invalid password
* ✅ Required field validation
* ✅ Negative authentication scenarios

# 🛠 Flakiness Mitigation Strategy

UI automation fails when synchronization is weak. This framework prevents flakiness by:

* Using Playwright’s built-in auto-waiting
* Avoiding `Thread.sleep()`
* Using structural, label-based locators
* Avoiding index-based (`nth()`) selectors
* Isolating BrowserContext per test
* Deterministic test data
* Explicit assertions for UI state validation

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
Update configuration to disable headless mode:
```text
headless=false
```

# 🤖 Running in CI (GitHub Actions)

Tests execute automatically on every push via:
```text
.github/workflows/ci-pipeline.yml
```
CI Pipeline Steps:

1. Checkout repository
2. Set up Java 17
3. Install dependencies
4. Run Maven test suite
5. Execute in headless mode
6. Fail pipeline if tests fail

CI Badge: 

![Playwright Tests](https://github.com/chukwuma1976/sdet-enterprise-automation-framework/actions/workflows/ci-pipeline.yml/badge.svg)

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

# ⚙️ Configuration Management

* Centralized `ConfigReader`
* `config.properties`
* Environment variable override support
* Designed for multi-environment execution (dev / staging / prod)

# 🛣 Roadmap

* ✅ UI Authentication Suite
* 🔄 Full CRUD automation
* 🔜 API automation layer
* 🔜 Integrated reporting
* 🔜 Dockerized execution
* 🔜 Cross-browser support

# 🎯 Why This Project Stands Out

This is not a tutorial project.

* It demonstrates:
* Real SDET architecture thinking
* CI-integrated automation
* Parallel-ready design
* Enterprise-grade test isolation
* Scalable project structure
* Clean separation of concerns

# 👤 Author
## Chukwuma Anyadike
Software Development Engineer in Test (SDET)

Automation Engineering | Playwright | Java | CI/CD | Parallel Architecture | API & UI Testing