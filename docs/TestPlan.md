# 📊 Test Strategy

The framework follows a layered testing strategy to balance speed, reliability, and coverage.

| Layer                 | Purpose                                   | Tooling       |
|-----------------------|-------------------------------------------|---------------|
| API Tests             | Validate backend behavior and contracts   | RestAssured   |
| UI Tests (Playwright) | Fast modern browser automation            | Playwright    |
| UI Tests (Selenium)   | Cross-browser compatibility validation    | Selenium      |

Playwright provides fast modern browser automation while Selenium is retained for cross-browser validation and legacy ecosystem compatibility.