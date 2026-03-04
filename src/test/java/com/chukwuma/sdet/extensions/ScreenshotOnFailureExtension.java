package com.chukwuma.sdet.extensions;

import com.chukwuma.sdet.base.BaseTest;
import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.*;

import java.io.ByteArrayInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScreenshotOnFailureExtension
        implements TestExecutionExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ScreenshotOnFailureExtension.class);

    @Override
    public void handleTestExecutionException(
            ExtensionContext context,
            Throwable throwable) throws Throwable {

        Object testInstance = context.getRequiredTestInstance();

        if (testInstance instanceof BaseTest baseTest) {

            Page page = baseTest.getPage(); // USE GETTER

            if (page != null && !page.isClosed()) {

                byte[] screenshot = page.screenshot();

                Allure.addAttachment(
                        "Failure Screenshot - " +
                                context.getDisplayName(),
                        new ByteArrayInputStream(screenshot));

                log.error("Screenshot captured for failed test: {}",
                        context.getDisplayName());
            }
        }

        // IMPORTANT: rethrow so test still fails
        throw throwable;
    }
}