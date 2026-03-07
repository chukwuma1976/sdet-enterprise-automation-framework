# 🚀 Enterprise Test Automation Framework
![CI Pipeline](https://github.com/chukwuma1976/sdet-enterprise-automation-framework/actions/workflows/ci-pipeline.yml/badge.svg)

## Playwright (Java) | Selenium | RestAssured | JUnit 5 | CI/CD | Parallel-Ready

A production-style UI test automation framework built with **Java + Playwright** designed for **scalability, determinism, and CI reliability.**

This project demonstrates how enterprise SDET teams architect automation frameworks — focusing on lifecycle control, isolation, parallel execution, and flakiness mitigation.

# 🏗 Architecture Overview

```
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

## 📁 Project Structure

```
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

## 🚀 CI/CD Pipeline

The framework is designed for CI-first execution using GitHub Actions.

Tests run in parallel across separate jobs to improve feedback speed.

Pipeline file:
```text
.github/workflows/ci-pipeline.yml
```

## 🔁 CI/CD Execution Flow

```
Developer Push
      │
      ▼
GitHub Actions Trigger
      │
      ▼
Parallel Test Jobs
 ├── API Tests
 ├── Playwright UI Tests
 └── Selenium UI Tests
      │
      ▼
Allure Results Collected
      │
      ▼
Unified Allure Report Generated
      │
      ▼
Report Published via GitHub Pages
```

# 🧭 Design Principles

The framework was designed with long-term maintainability and CI reliability in mind.

Core principles include:

**Test Independence**  
      Tests run safely in any order, enabling deterministic parallel execution.

**Separation of Concerns**  
      Test logic, page interactions, configuration, and utilities are clearly separated.

**Maintainable Locators**  
      All UI locators live inside Page Objects to reduce duplication and simplify updates.

**Reusable Components**  
      Shared behaviors such as authentication, configuration loading, and test data management are abstracted into reusable utilities.

**CI-First Execution**  
      Tests are designed for reliable CI execution through headless mode, failure observability, and deterministic state management.

# 📊 Test Strategy

The framework follows a layered testing strategy to balance speed, reliability, and coverage.

| Layer                 | Purpose                                   | Tooling       |
|-----------------------|-------------------------------------------|---------------|
| API Tests             | Validate backend behavior and contracts   | RestAssured   |
| UI Tests (Playwright) | Fast modern browser automation            | Playwright    |
| UI Tests (Selenium)   | Cross-browser compatibility validation    | Selenium      |

Playwright provides fast modern browser automation while Selenium is retained for cross-browser validation and legacy ecosystem compatibility.

## Testing Pyramid

           E2E UI Tests
        -------------------
        Integration Tests
     -------------------------
        API / Service Tests
   -----------------------------
           Unit Tests

## Execution Strategy

**Smoke Tests**
  - Run on every pull request
  - Validate critical user flows

**Regression Tests**
  - Run on scheduled builds or full CI runs
  - Provide broader coverage of functionality

This layered approach helps detect failures earlier in the testing pyramid while still validating full user workflows.

# 📊 Execution Metrics

Execution times measured locally (parallel enabled):


| Suite Type | Command                        | Tests | Execution Time |
| ---------- | ------------------------------ | ----- | -------------- |
| Smoke      | `mvn test -Dgroups=smoke`      | 10    | ~66 seconds    |
| Regression | `mvn test -Dgroups=regression` | 34    | ~2 minutes 22s |
| API Only   | `mvn test -Dgroups=api`        | 12    | ~12 seconds    |
| UI Only    | `mvn test -Dgroups=ui`         | 34    | ~4 minutes 6s  |

# 🧰 Technology Stack

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

# 🚦 Smoke vs Regression Strategy

The framework uses JUnit 5 tags to control test scope.

## Smoke Tests
Smoke tests validate critical user journeys and run on every CI pipeline execution.

Typical coverage:

* Login
* Core navigation
* Critical CRUD flows
* Basic API health checks

Execution time target:
```text
< 1 minute

```
# Regression Suite

Regression tests validate **broader system functionality** including:

* edge cases
* validation rules
* negative scenarios
* full UI/API coverage

Regression suites are typically executed:

* nightly
* before release
* during extended pipeline validation

# 🔁 Retry Strategy Explanation

Automation reliability requires distinguishing between:

* **legitimate product defects**
* **environmental flakiness**

This framework intentionally avoids automatic blind retries in the test code.

Instead it emphasizes:

* deterministic test design
* Playwright auto-wait synchronization
* stable locators
* context isolation

Retries may be introduced at the **CI pipeline** layer if needed.

This ensures test failures remain **actionable signals rather than hidden noise.**

# 📸 Screenshot-on-Failure Strategy

When UI tests fail in CI environments, diagnosing issues without visual artifacts becomes difficult.

This framework implements **automatic screenshot capture at the moment of failure.**

Implementation approach:

* Uses **JUnit 5 TestExecutionExceptionHandler**
* Captures screenshot **before browser teardown**
* Avoids lifecycle timing issues caused by TestWatcher

Lifecycle flow:
```
Test Failure
      ↓
Exception Intercepted
      ↓
Screenshot Captured
      ↓
Failure Propagated
```

Benefits:

* Faster debugging
* Clear CI diagnostics
* Reduced time investigating failures

# 🏷 Running Tagged Suites

Tests are organized using **JUnit 5 tags.**

## Test Suites

The framework uses JUnit `@Tag` annotations to organize tests into logical suites.  
These tags allow selective execution of tests for different purposes such as smoke testing, regression testing, UI testing, or API validation.

| Suite Name      | Tag                   |
|-----------------|-----------------------|
| smoke           | `@Tag("smoke")`       |
| regression      | `@Tag("regression")`  |
| ui              | `@Tag("ui")`          |
| api             | `@Tag("api")`         |
| playwright      | `@Tag("playwright")`  |
| selenium        | `@Tag("selenium")`    |

### Example

```java
@Tag("smoke")
@Tag("ui")
@Test
void userCanLogin() {
    // test implementation
}
```

Run only smoke tests:
```text
mvn test -Dgroups=smoke
```
Run regression suite:
```text
mvn test -Dgroups=regression
```
Run API tests only:
```text
mvn test -Dgroups=api
```
Run UI tests only:
```text
mvn test -Dgroups=ui
```
Run a specific test class:
```text
mvn -Dtest=ClassName test
```

# 🖥 Headed vs Headless Execution

The framework supports both **headed (local debugging)** and **headless (CI execution)** modes.

## Headed Mode
Used during local debugging.
```text
headless=false
```
Benefits:

* Visual debugging
* Step-by-step test observation
* Development troubleshooting

## Headless Mode
Used in CI environments.
```text
headless=true
```
Benefits:

* Faster execution
* Reduced resource usage
* CI pipeline compatibility

CI pipelines always execute in headless mode.

# ⚙️ Configuration Management

* Centralized `ConfigReader`
* `config.properties`
* Environment variable override support
* Designed for multi-environment execution (dev / staging / prod)
* CI-friendly configuration injection

## Future Enhancement: Database Validation Layer

### Overview

This framework is designed to support **multi-layer validation**, a common practice in enterprise automation architectures. In addition to UI and API validation, many enterprise systems allow automation frameworks to verify that application actions correctly persist data in the **database layer**.

Database validation enables tests to confirm that business operations not only succeed in the UI but also produce the expected changes in the system of record.

Example validation flow:

```
UI Action → Application Logic → Database Persistence
```

A test may perform an operation through the UI and then validate that the underlying database state reflects the expected outcome.

Example test pattern:

```java
@Test
void shouldAddEmployee() {

    String employeeId = employeePage.addEmployee();

    assertTrue(EmployeeRepository.exists(employeeId));
}
```

### Proposed Architecture

To support database validation while maintaining clean test design, the framework would implement a layered structure:

```
tests
  ↓
repositories
  ↓
database utilities
  ↓
database
```

#### Database Utility Layer

A `DatabaseManager` component would manage connections and query execution:

```
core/database/DatabaseManager
```

Responsibilities:

* Establish database connections
* Execute queries
* Manage connection lifecycle
* Externalize credentials via configuration

This prevents database configuration from appearing directly in tests.

#### Repository Layer

A repository abstraction would encapsulate SQL queries and expose domain-level operations.

Example:

```
core/repositories/EmployeeRepository
```

Example method:

```java
public static boolean exists(String employeeId)
```

This ensures tests interact with business concepts rather than raw SQL.

### Benefits

This design provides several advantages commonly seen in enterprise automation frameworks:

* **Data integrity validation** beyond UI confirmation
* **Separation of concerns** between tests and infrastructure
* **Reusable data access utilities**
* **Cleaner test code** with domain-level assertions

### Environment Constraints

The public OrangeHRM demo environment used in this project does **not expose database connectivity** for external automation frameworks. As a result, direct database validation cannot be implemented against the hosted demo application.

For this reason, the database validation layer is currently **documented as a framework capability and future enhancement** rather than implemented directly.

### When Database Validation Is Appropriate

Database validation is typically used in systems where data accuracy is critical, such as:

* financial transactions
* payroll systems
* insurance platforms
* healthcare records

In these environments the database acts as the **source of truth**, and verifying persistence is an important part of automated validation.

### When Database Validation Is Not Required

In many cases, API validation or UI verification is sufficient. Overusing database validation can introduce unnecessary coupling between tests and internal system implementation.

The framework therefore treats database validation as an **optional capability**, used only when system access and test requirements justify it.


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