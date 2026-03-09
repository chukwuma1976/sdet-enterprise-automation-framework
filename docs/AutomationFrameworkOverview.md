# Automation Framework Overview

## 1. Introduction

This repository contains a **hybrid enterprise-grade automation framework** designed to support **UI, API, and database testing** within a unified architecture. The framework follows modern SDET best practices including:

* Page Object Model (POM)
* Service Layer for API tests
* JUnit 5 extensions for failure handling
* CI/CD pipeline integration
* Observability through logs, artifacts, and trace files

The framework is designed to be **scalable, maintainable, and CI-friendly**, allowing tests to run both **locally and in pipeline environments**.

---

# 2. Project Structure

The framework separates **application-level components** from **test implementations**.

```
src
├── main/java/com/chukwuma/sdet
│   ├── config                # environment configuration
│   ├── pages                 # page objects for UI automation
│   └── utilities             # shared utility classes
│
└── test/java/com/chukwuma/sdet
    ├── base                  # base test setup and configuration
    ├── models                # API data models / DTOs
    ├── utils                 # test utilities
    ├── extensions            # JUnit extensions (screenshots, listeners)
    │
    ├── api
    │   ├── tests             # API test cases
    │   └── service           # API service layer
    │
    ├── ui
    │   ├── selenium          # Selenium UI tests
    │   └── playwright        # Playwright UI tests
    │
    └── database              # database-related tests
```

This structure promotes **separation of concerns**, making it easier to maintain and scale the framework.

---

# 3. Framework Design Principles

The framework was designed around several key principles:

### Modularity

Each testing layer (UI, API, Database) is isolated into its own packages.

### Reusability

Shared utilities and base classes reduce duplication and simplify maintenance.

### Observability

Tests generate logs, screenshots, and trace artifacts to support debugging in CI pipelines.

### CI Compatibility

Tests automatically detect CI environments and adjust configuration such as running browsers in headless mode.

---

# 4. Base Test Architecture

The framework provides base classes that manage browser lifecycle, test configuration, and observability features.

Two UI automation stacks are supported:

* **Playwright**
* **Selenium**

Each has its own BaseTest implementation.

---

# 5. Playwright BaseTest Implementation

The Playwright BaseTest manages:

* browser lifecycle
* context creation
* tracing
* test logging
* failure diagnostics

Key features include:

### Browser Lifecycle Management

The browser launches once per test class and closes after execution.

```
@BeforeAll
void launchBrowser()
```

### Context Isolation

Each test receives a fresh browser context and page instance.

```
@BeforeEach
context = browser.newContext();
page = context.newPage();
```

This ensures **tests remain isolated and do not share state**.

### Automatic Trace Capture

Playwright tracing captures:

* screenshots
* DOM snapshots
* network events
* source files

Tracing starts at test execution and only persists if the test fails.

```
context.tracing().start(...)
```

If the test fails, the trace file is attached to the report.

### Allure Reporting Integration

On failure the framework automatically attaches:

* Playwright trace file
* HTML page source
* Current page URL

These artifacts improve debugging in CI pipelines.

### Structured Logging

The framework uses **SLF4J logging with MDC context** to associate log output with specific test cases.

```
MDC.put("testName", testInfo.getDisplayName());
```

This allows logs to be correlated with individual tests.

---

# 6. Selenium BaseTest Implementation

The Selenium BaseTest provides a similar lifecycle management pattern.

Responsibilities include:

* WebDriver initialization
* ChromeDriver setup via WebDriverManager
* CI-friendly browser configuration
* driver cleanup after each test

### Driver Setup

The framework automatically resolves the correct ChromeDriver version.

```
WebDriverManager.chromedriver().setup();
```

### CI Environment Configuration

When running in CI:

* browsers run headless
* sandboxing is disabled
* GPU acceleration is disabled

This ensures stable execution in containerized pipeline environments.

### Local Execution

For local development, the browser launches maximized for easier debugging.

---

# 7. Failure Handling

Test failures are handled through a **JUnit 5 extension**:

```
ScreenshotOnFailureExtension
```

This extension intercepts test exceptions and captures:

* screenshots
* failure state information

Using extensions keeps failure handling **separate from test logic**, improving maintainability.

---

# 8. API Testing Architecture

API tests follow a **service-layer design pattern**.

```
api
 ├── service
 └── tests
```

The service layer:

* handles HTTP requests
* manages endpoints
* parses responses

Tests focus only on **validation and assertions**, keeping them readable and maintainable.

---

# 9. Page Object Model (UI Tests)

UI automation follows the **Page Object Model (POM)** pattern.

Page classes live in:

```
src/main/java/com/chukwuma/sdet/pages
```

Each page object encapsulates:

* locators
* actions
* page-specific logic

This approach:

* reduces test duplication
* improves readability
* isolates UI changes to a single location.

---

# 10. CI/CD Integration

The framework is designed to integrate with CI pipelines such as:

* GitHub Actions
* Jenkins
* Azure DevOps
* GitLab CI

Key CI features include:

* automatic headless browser execution
* test artifacts generation
* trace and screenshot capture
* Allure report publishing

---

# 11. Observability and Debugging

To support fast debugging, the framework produces several artifacts:

Artifacts generated on failure include:

* screenshots
* Playwright trace files
* HTML page source
* test execution logs

These artifacts are uploaded in CI pipelines and can be reviewed after a pipeline run.

This improves **test observability and root cause analysis**.

---

# 12. Future Enhancements

Planned improvements for the framework include:

* containerized browser environments
* visual regression testing
* improved test data management
* flaky test detection

---

# 13. Conclusion

This automation framework provides a **scalable, maintainable testing architecture** supporting multiple testing layers within a single repository.

By combining:

* Playwright
* Selenium
* API testing
* database validation
* CI observability

the framework enables teams to maintain **high software quality while supporting rapid development cycles**.
