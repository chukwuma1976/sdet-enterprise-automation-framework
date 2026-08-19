package com.chukwuma.sdet.tests.ui.selenium.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junitpioneer.jupiter.RetryingTest;

import com.chukwuma.sdet.config.ConfigReader;
import com.chukwuma.sdet.tests.ui.selenium.base.BaseTest;
import com.chukwuma.sdet.tests.ui.selenium.pages.IframePage;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Tag("selenium")
@Tag("ui")
@Epic("Selenium Iframes")
@Feature("Handle Iframes in Selenium")
public class IframeTest extends BaseTest {
    IframePage iframePage;

    @BeforeEach
    void navigateAndSetUpPage() {
        driver.get(ConfigReader.get("PRACTICE_TESTING_URL") + "iframe");
        iframePage = new IframePage(driver);
    }

    @RetryingTest(maxAttempts = 3)
    @DisplayName("Handle YouTube iframe")
    void demonstrateYouTubeIframe() {
        iframePage.handleYouTubeIframe();
    }

    @RetryingTest(maxAttempts = 3)
    @DisplayName("Handle TinyMce iframe")
    void demonstrateTinyMceIframe() {
        iframePage.handleTinyMceIframe();
    }

    @RetryingTest(maxAttempts = 3)
    @DisplayName("Handle input text iframe")
    void demonstrateInputTextIframe() {
        iframePage.handleSubscriptionIframe();
    }

}
