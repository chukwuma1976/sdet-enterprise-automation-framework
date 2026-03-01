package com.chukwuma.sdet.tests.ui.playwright;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.chukwuma.sdet.base.BaseTest;
import com.chukwuma.sdet.config.ConfigReader;
import com.chukwuma.sdet.pages.DashboardPage;
import com.chukwuma.sdet.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Tag("ui")
@Epic("Employee UI Tests")
@Feature("Successful Logout")
public class SuccessfulLogoutTest extends BaseTest {

    @Test
    @DisplayName("User can logout after successful login")
    @Description("Verify that users can logout after successful login and are redirected to the login page")
    void shouldLogoutSuccessfully() {
        LoginPage loginPage = new LoginPage(page);
        DashboardPage dashboardPage = new DashboardPage(page);

        String username = ConfigReader.get("APP_USERNAME");
        String password = ConfigReader.get("APP_PASSWORD");
        String baseUrl = ConfigReader.get("BASE_URL");

        page.navigate(baseUrl);

        loginPage.login(username, password);

        dashboardPage.logout();
        assertTrue(dashboardPage.isOnLoginPage());
    }
}
