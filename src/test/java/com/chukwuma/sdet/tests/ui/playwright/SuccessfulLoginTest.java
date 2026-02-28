package com.chukwuma.sdet.tests.ui.playwright;

import com.chukwuma.sdet.base.BaseTest;
import com.chukwuma.sdet.config.ConfigReader;
import com.chukwuma.sdet.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;

public class SuccessfulLoginTest extends BaseTest {

    @Test
    @DisplayName("User can login with valid credentials")
    void userCanLoginWithValidCredentials() {

        LoginPage loginPage = new LoginPage(page);

        String username = ConfigReader.get("APP_USERNAME");
        String password = ConfigReader.get("APP_PASSWORD");
        String baseUrl = ConfigReader.get("BASE_URL");

        page.navigate(baseUrl);

        loginPage.login(username, password);

        page.waitForURL("**/dashboard/**");

        assertTrue(loginPage.isDashboardVisible(), "Dashboard should be visible after login");
    }
}