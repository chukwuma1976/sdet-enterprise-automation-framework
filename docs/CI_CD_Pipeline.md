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