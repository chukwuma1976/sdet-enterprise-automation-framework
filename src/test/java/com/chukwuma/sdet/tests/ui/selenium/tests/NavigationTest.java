package com.chukwuma.sdet.tests.ui.selenium.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junitpioneer.jupiter.RetryingTest;

import com.chukwuma.sdet.config.ConfigReader;
import com.chukwuma.sdet.tests.ui.selenium.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Tag("selenium")
@Tag("ui")
@Epic("Selenium Test Navigation Tests")
@Feature("Navigation using Selenium")
public class NavigationTest extends BaseTest {

    @RetryingTest(maxAttempts = 3)
    @DisplayName("User can navigate to Selenium and Playwright documentation")
    @Description("Verify that users can navigate to Selenium and Playwright documentation")
    void shouldNavigate() {

        driver.get("https://www.google.com");
        assert (driver.getTitle()).equals("Google");
        assert (driver.getCurrentUrl()).contains("google");
        assert (!driver.getPageSource().isBlank());

        driver.navigate().to("https://playwright.dev/");
        assert (driver.getTitle()).equals("Fast and reliable end-to-end testing for modern web apps | Playwright");
        driver.navigate().to("https://www.selenium.dev/");
        assert (driver.getTitle()).equals("Selenium");

        driver.navigate().back();
        assert (driver.getCurrentUrl()).contains("playwright");
        driver.navigate().forward();
        assert (driver.getCurrentUrl()).contains("selenium");

        driver.navigate().refresh();
        assert (driver.getCurrentUrl()).contains("selenium");
        driver.manage().window().maximize();

    }
}
