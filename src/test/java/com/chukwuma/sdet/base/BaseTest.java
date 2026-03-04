package com.chukwuma.sdet.base;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.junit.jupiter.api.extension.ExtendWith;
import com.chukwuma.sdet.extensions.ScreenshotOnFailureExtension;

@ExtendWith(ScreenshotOnFailureExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    protected static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    @BeforeAll
    void launchBrowser() {

        playwright = Playwright.create();

        boolean isCI = System.getenv("CI") != null;

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(isCI));
    }

    @BeforeEach
    void setUp(TestInfo testInfo) {

        String testName = testInfo.getDisplayName();

        MDC.put("testName", testName);

        log.info("========== START TEST: {} ==========", testName);
    }

    @BeforeEach
    void createContext() {

        context = browser.newContext(); // 🔹 Isolation happens here
        page = context.newPage();

        page.setDefaultTimeout(60000);
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {

        log.info("========== END TEST: {} ==========",
                testInfo.getDisplayName());

        MDC.clear();
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

    public Page getPage() {
        return page;
    }
}