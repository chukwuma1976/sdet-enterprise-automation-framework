package com.chukwuma.sdet.tests.ui.playwright;

import com.chukwuma.sdet.base.BaseTest;
import com.chukwuma.sdet.pages.LoginPage;
import com.chukwuma.sdet.pages.DashboardPage;
import com.chukwuma.sdet.config.ConfigReader;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DashboardTest extends BaseTest {

    @Test
    void shouldDisplayDashboardAfterSuccessfulLogin() {

        // Arrange
        LoginPage loginPage = new LoginPage(page);
        DashboardPage dashboardPage = new DashboardPage(page);

        String username = ConfigReader.get("APP_USERNAME");
        String password = ConfigReader.get("APP_PASSWORD");
        String baseUrl = ConfigReader.get("BASE_URL");
        System.out.println("username: " + username);
        System.out.println("password" + password);

        page.navigate(baseUrl);

        // Act
        loginPage.login(username, password);
        page.waitForURL("**/dashboard/**");

        // Assert
        assertTrue(
                dashboardPage.isLoaded(),
                "Dashboard should be visible after successful login");
    }
}