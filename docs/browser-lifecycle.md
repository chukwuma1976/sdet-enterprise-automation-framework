# 🔄 Browser Lifecycle Management

```
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
