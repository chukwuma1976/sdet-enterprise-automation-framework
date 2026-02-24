package com.chukwuma.sdet.base;

import com.microsoft.playwright.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeEach
    void setUp() {

        playwright = Playwright.create();

        boolean isCI = System.getenv("CI") != null;

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(isCI));

        context = browser.newContext();
        page = context.newPage();

        page.setDefaultTimeout(60000);
    }

    @AfterEach
    void tearDown() {

        if (context != null) {
            context.close();
        }

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
    }
}