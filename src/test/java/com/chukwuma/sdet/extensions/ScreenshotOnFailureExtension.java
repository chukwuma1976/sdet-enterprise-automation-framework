package com.chukwuma.sdet.extensions;

import com.chukwuma.sdet.base.BaseTest;
import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentHashMap;

public class ScreenshotOnFailureExtension implements TestExecutionExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ScreenshotOnFailureExtension.class);

    // 🔹 Status Tracking: Allows BaseTest to know if it should save or discard the
    // trace
    private static final ConcurrentHashMap<String, Boolean> failureMap = new ConcurrentHashMap<>();

    public static boolean getTestStatus(String testName) {
        return failureMap.getOrDefault(testName, false);
    }

    public static void removeTestStatus(String testName) {
        failureMap.remove(testName);
    }

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        // Mark the test as failed in our map
        failureMap.put(context.getDisplayName(), true);

        Object testInstance = context.getRequiredTestInstance();

        if (testInstance instanceof BaseTest baseTest) {
            Page page = baseTest.getPage();

            if (page != null && !page.isClosed()) {
                try {
                    // 1. Capture and Attach Screenshot
                    byte[] screenshot = page.screenshot();
                    Allure.addAttachment(
                            "Failure Screenshot - " + context.getDisplayName(),
                            new ByteArrayInputStream(screenshot));
                    log.error("📸 Screenshot captured for: {}", context.getDisplayName());

                    /*
                     * * 2. Attach Playwright Trace
                     * Note: The actual file is generated in BaseTest's @AfterEach.
                     * We search for it here to attach it to the Allure Report.
                     */
                    String safeFileName = context.getDisplayName().replaceAll("[^a-zA-Z0-9.-]", "_");
                    Path tracePath = Paths.get("target/allure-results/trace-" + safeFileName + ".zip");

                    // We check if the file exists (it might be created slightly after this call)
                    if (Files.exists(tracePath)) {
                        try (InputStream is = Files.newInputStream(tracePath)) {
                            Allure.addAttachment("Playwright Trace", "application/zip", is, ".zip");
                        }
                    }

                } catch (Exception e) {
                    log.warn("⚠️ Extension could not capture failure artifacts: {}", e.getMessage());
                }
            }
        }

        // Rethrow so the @RetryingTest logic knows to trigger a retry
        throw throwable;
    }
}