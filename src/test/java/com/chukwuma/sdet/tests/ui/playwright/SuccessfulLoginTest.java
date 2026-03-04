package com.chukwuma.sdet.tests.ui.playwright;

import com.chukwuma.sdet.base.BaseTest;
import com.chukwuma.sdet.config.ConfigReader;
import com.chukwuma.sdet.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

@Tag("smoke")
@Tag("ui")
@Epic("Login UI Tests")
@Feature("Successful Login")
public class SuccessfulLoginTest extends BaseTest {

    @Test
    @DisplayName("User can login with valid credentials")
    @Description("Verify that users can login with valid username and password")
    void userCanLoginWithValidCredentials() {

        LoginPage loginPage = new LoginPage(page);

        String username = ConfigReader.get("APP_USERNAME");
        String password = ConfigReader.get("APP_PASSWORD");
        String baseUrl = ConfigReader.get("BASE_URL");

        page.navigate(baseUrl);

        loginPage.login(username, password);

        page.waitForURL(ConfigReader.get("dashboard.path"));

        assertTrue(loginPage.isDashboardVisible(), "Dashboard should be visible after login");
    }
}