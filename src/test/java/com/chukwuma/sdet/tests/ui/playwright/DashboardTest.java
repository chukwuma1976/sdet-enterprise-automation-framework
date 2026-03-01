package com.chukwuma.sdet.tests.ui.playwright;

import com.chukwuma.sdet.base.BaseTest;
import com.chukwuma.sdet.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import com.chukwuma.sdet.pages.DashboardPage;
import com.chukwuma.sdet.config.ConfigReader;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Tag;

@Tag("ui")
@Epic("Employee UI Tests")
@Feature("Dashboard Display")
public class DashboardTest extends BaseTest {

    @Test
    @Description("Dashboard should be displayed after successful login")
    void shouldDisplayDashboardAfterSuccessfulLogin() {

        LoginPage loginPage = new LoginPage(page);
        DashboardPage dashboardPage = new DashboardPage(page);

        String username = ConfigReader.get("APP_USERNAME");
        String password = ConfigReader.get("APP_PASSWORD");
        String baseUrl = ConfigReader.get("BASE_URL");

        page.navigate(baseUrl);

        loginPage.login(username, password);

        assertTrue(
                dashboardPage.isLoaded(),
                "Dashboard should be visible after successful login");
    }
}