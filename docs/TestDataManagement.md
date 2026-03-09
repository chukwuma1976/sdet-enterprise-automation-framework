# Test Data Management Strategy

## 1. Overview

This framework uses a structured test data management strategy to ensure automated tests are **repeatable, isolated, and stable across environments**.

Test data is handled through three main mechanisms:

1. Static test data files
2. Dynamic test data generation
3. Environment configuration management

These approaches ensure tests can run both **locally and in CI pipelines** without data conflicts.

---

# 2. Static Test Data

Static test data is stored in JSON files located in:

src/test/resources/testdata

Example:

```json
{
  "validUser": {
    "username": "Admin",
    "password": "admin123"
  },
  "invalidUsers": [
    {
      "username": "wrongUser",
      "password": "admin123"
    }
  ]
}
```

Static data is useful for:

* login validation tests
* negative test scenarios
* predictable test inputs

The framework loads this data using the `TestDataLoader` utility.

Example usage:

```java
LoginData loginData = TestDataLoader.loadLoginData();
```

This allows tests to retrieve structured test data without hardcoding values.

---

# 3. Dynamic Test Data Generation

For tests that create new records (such as employee creation), the framework generates **unique data dynamically** to prevent collisions.

The `TestDataFactory` utility generates unique identifiers using timestamps and UUIDs.

Example methods include:

```java
generateEmployeeId()
generateUniqueFirstName()
generateUniqueLastName()
generateUniqueMiddleName()
```

Example output:

```
Employee ID: 83451276
First Name: FN_1712345678900
Last Name: LN_1712345678900
```

Dynamic generation ensures:

* tests do not reuse existing records
* multiple test runs do not conflict
* parallel execution remains stable

---

# 4. Environment Configuration Management

Application configuration values are stored in:

config.properties

Example values include:

```
BASE_URL=https://opensource-demo.orangehrmlive.com
APP_USERNAME=Admin
APP_PASSWORD=admin123
```

The framework loads configuration using the `ConfigReader` class.

Example usage:

```java
String baseUrl = ConfigReader.get("BASE_URL");
```

This utility also supports **environment variable overrides**, which allows CI pipelines to provide secure credentials.

Example:

```
System.getenv("APP_USERNAME")
```

If an environment variable exists, it overrides the value in `config.properties`.

---

# 5. Data Isolation

To maintain test stability:

* Each test generates unique data when creating records
* Test sessions run in isolated browser contexts
* API and UI tests avoid shared mutable data

This prevents tests from interfering with one another.

---

# 6. CI Pipeline Considerations

When tests run in CI environments:

* credentials are supplied through environment variables
* dynamic test data prevents collisions between parallel builds
* configuration is automatically loaded through `ConfigReader`

This allows the same test suite to run consistently across:

* local development environments
* CI pipelines
* staging environments

---

# 7. Benefits of the Approach

The current test data strategy provides several advantages:

* predictable and repeatable test runs
* reduced data conflicts
* easy configuration across environments
* improved CI stability

By combining **static datasets, dynamic data generation, and configuration management**, the framework ensures reliable automated testing at scale.
