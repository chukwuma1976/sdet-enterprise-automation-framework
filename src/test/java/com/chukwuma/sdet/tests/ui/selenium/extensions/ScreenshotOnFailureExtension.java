package com.chukwuma.sdet.tests.ui.selenium.extensions;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Allure;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotOnFailureExtension implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {

        Object testInstance = context.getRequiredTestInstance();

        try {

            var field = testInstance.getClass().getDeclaredField("driver");
            field.setAccessible(true);

            WebDriver driver = (WebDriver) field.get(testInstance);

            if (driver != null) {

                File screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.FILE);

                String timestamp = LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

                String testName = context.getRequiredTestMethod().getName();

                Path destination = Path.of(
                        "target/screenshots",
                        testName + "_" + timestamp + ".png");

                Files.createDirectories(destination.getParent());

                Files.copy(
                        screenshot.toPath(),
                        destination,
                        StandardCopyOption.REPLACE_EXISTING);

                Allure.addAttachment(
                        "Failure Screenshot",
                        Files.newInputStream(destination));

                System.out.println("Screenshot saved: " + destination);
            }

        } catch (Exception e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }

        throw throwable;
    }
}
