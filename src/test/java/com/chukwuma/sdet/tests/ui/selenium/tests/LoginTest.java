package com.chukwuma.sdet.tests.ui.selenium.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junitpioneer.jupiter.RetryingTest;

import com.chukwuma.sdet.config.ConfigReader;
import com.chukwuma.sdet.tests.ui.selenium.base.BaseTest;
import com.chukwuma.sdet.tests.ui.selenium.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Tag("selenium")
@Tag("ui")
@Epic("Selenium Login UI Tests")
@Feature("Successful Login")
public class LoginTest extends BaseTest {

    @RetryingTest(maxAttempts = 3)
    @DisplayName("User can login with valid credentials")
    @Description("Verify that users can login with valid username and password")
    void shouldLoginSuccessfully() {

        driver.get(ConfigReader.get("BASE_URL"));

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.get("APP_USERNAME"), ConfigReader.get("APP_PASSWORD"));

        Assertions.assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }

}
