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

[⬅ Back to Main README](../README.md)