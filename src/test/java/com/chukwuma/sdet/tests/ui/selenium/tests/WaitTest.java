package com.chukwuma.sdet.tests.ui.selenium.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junitpioneer.jupiter.RetryingTest;

import com.chukwuma.sdet.config.ConfigReader;
import com.chukwuma.sdet.tests.ui.selenium.base.BaseTest;
import com.chukwuma.sdet.tests.ui.selenium.pages.WaitsDemoPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Tag("selenium")
@Tag("ui")
@Epic("Selenium Waits")
@Feature("Demonstrate Explicit Waits and Fluent Waits")
public class WaitTest extends BaseTest {

    @BeforeEach
    void navigate() {
        driver.get(ConfigReader.get("SELENIUM_WAITS_URL"));
    }

    @RetryingTest(maxAttempts = 3)
    @DisplayName("Selenium Fluent Wait")
    @Description("Properly demonstrate fluent waits")
    void shouldDemonstrateFluentWait() {

        WaitsDemoPage waitsDemoPage = new WaitsDemoPage(driver);
        waitsDemoPage.fluentlyWaitForAlert();
        waitsDemoPage.verifyThatAlertIsDisplayed();

    }

    @RetryingTest(maxAttempts = 3)
    @DisplayName("Selenium Explicit Wait")
    @Description("Properly demonstrate explicit wait")
    void shouldDemonstrateExplicitWait() {

        WaitsDemoPage waitsDemoPage = new WaitsDemoPage(driver);
        waitsDemoPage.verifyPresenceOfSpinner();
        waitsDemoPage.explicitlyWaitForSpinnerToDisappear();

    }

}
