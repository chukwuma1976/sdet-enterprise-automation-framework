                        +-----------------------+
                        |   Test Cases          |
                        |-----------------------|
                        | UI Tests (Playwright) |
                        | UI Tests (Selenium)   |
                        | API Tests             |
                        | Database Tests        |
                        +-----------+-----------+
                                    |
                                    v
                         +----------------------+
                         |     Base Test Layer  |
                         |----------------------|
                         | Playwright BaseTest  |
                         | Selenium BaseTest    |
                         | JUnit Lifecycle      |
                         +-----------+----------+
                                     |
                                     v
                         +-----------------------+
                         |  Framework Components |
                         |-----------------------|
                         | Page Objects          |
                         | API Service Layer     |
                         | Test Utilities        |
                         | JUnit Extensions      |
                         +-----------+-----------+
                                     |
                                     v
                         +-----------------------+
                         |  Test Infrastructure  |
                         |-----------------------|
                         | ConfigReader          |
                         | TestDataLoader        |
                         | TestDataFactory       |
                         | Logging (SLF4J + MDC) |
                         +-----------+-----------+
                                     |
                                     v
                        +------------------------+
                        | Observability & CI     |
                        |------------------------|
                        | Screenshots on Failure |
                        | Playwright Trace Files |
                        | Allure Reports         |
                        | CI Artifacts           |
                        +-----------+------------+
                                    |
                                    v
                         +-----------------------+
                         | CI Pipeline           |
                         |-----------------------|
                         | GitHub Actions        |
                         | Jenkins               |
                         | Azure DevOps          |
                         | GitLab CI             |
                         +-----------------------+