package com.chukwuma.sdet.tests.ui.selenium.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junitpioneer.jupiter.RetryingTest;

import com.chukwuma.sdet.config.ConfigReader;
import com.chukwuma.sdet.tests.ui.selenium.base.BaseTest;
import com.chukwuma.sdet.tests.ui.selenium.pages.MultiplePage;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Tag("selenium")
@Tag("ui")
@Epic("Selenium Multiple Windows Tests")
@Feature("Successful opening of multiple windows")
public class MultipleWindowsTest extends BaseTest {

    @RetryingTest(maxAttempts = 3)
    @DisplayName("User can open multiple windows")
    void shouldOpenMultipleWindows() {

        driver.get(ConfigReader.get("PRACTICE_TESTING_URL") + "windows");
        MultiplePage testingPage = new MultiplePage(driver);
        testingPage.verifyOriginalWindowTitle();
        testingPage.openNewWindow();
        assert (!testingPage.getOriginalTitle().equals(testingPage.getNewTitle()));

    }

}
