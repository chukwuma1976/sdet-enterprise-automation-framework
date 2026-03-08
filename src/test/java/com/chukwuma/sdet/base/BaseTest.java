package com.chukwuma.sdet.base;

import com.microsoft.playwright.*;

import io.qameta.allure.Allure;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private long startTime;

    @BeforeAll
    void launchBrowser() {
        playwright = Playwright.create();
        boolean isCI = System.getenv("CI") != null;
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(isCI));
    }

    @BeforeEach
    void setUp(TestInfo testInfo) {
        startTime = System.currentTimeMillis();
        MDC.put("testName", testInfo.getDisplayName());
        log.info("========== START ATTEMPT: {} ==========", testInfo.getDisplayName());

        context = browser.newContext();
        page = context.newPage();
        page.setDefaultTimeout(60000);

        // Start Tracing
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        try {
            // Logic to check if the test failed.
            // NOTE: To strictly save on failure only, we check the 'executionException'
            // which is managed by your ScreenshotOnFailureExtension.

            boolean isFailed = ScreenshotOnFailureExtension.getTestStatus(testInfo.getDisplayName());

            if (isFailed) {
                log.info("❌ Test Failed: Saving Playwright Trace...");

                String safeFileName = testInfo.getTestMethod().get().getName()
                        + "-" + System.currentTimeMillis();

                Path tracePath = Paths.get(
                        "target/allure-results/trace-" + safeFileName + ".zip");

                context.tracing().stop(new Tracing.StopOptions().setPath(tracePath));

                try (InputStream is = Files.newInputStream(tracePath)) {
                    Allure.addAttachment("Playwright Trace", "application/zip", is, ".zip");
                }

            } else {
                log.info("✅ Test Passed: Discarding Trace.");
                context.tracing().stop();
            }

            log.info("========== END TEST ({} ms) ==========", System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            log.error("Error during trace teardown: {}", e.getMessage());
        } finally {
            if (page != null)
                page.close();
            if (context != null)
                context.close();
            MDC.clear();
            log.info("🧹 Context closed successfully.");
            // Clean up the status map for the next attempt/test
            ScreenshotOnFailureExtension.removeTestStatus(testInfo.getDisplayName());
        }
    }

    @AfterAll
    void closeBrowser() {
        if (browser != null)
            browser.close();
        if (playwright != null)
            playwright.close();
    }

    public Page getPage() {
        return page;
    }
}