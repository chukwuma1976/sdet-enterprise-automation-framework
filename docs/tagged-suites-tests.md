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
```
mvn test -Dgroups=smoke
```
Run regression suite:
```
mvn test -Dgroups=regression
```
Run API tests only:
```
mvn test -Dgroups=api
```
Run UI tests only:
```
mvn test -Dgroups=ui
```
Run a specific test class:
```
mvn -Dtest=ClassName test
```