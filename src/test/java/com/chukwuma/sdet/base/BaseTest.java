package com.chukwuma.sdet.base;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeAll
    void launchBrowser() {

        playwright = Playwright.create();

        boolean isCI = System.getenv("CI") != null;

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(isCI));
    }

    @BeforeEach
    void createContext() {

        context = browser.newContext(); // 🔹 Isolation happens here
        page = context.newPage();

        page.setDefaultTimeout(60000);
    }

    @AfterEach
    void closeContext() {

        if (context != null) {
            context.close(); // 🔹 Critical for parallel safety
        }
    }

    @AfterAll
    void closeBrowser() {

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
    }
}