package com.chukwuma.sdet.tests.ui.playwright;

import com.chukwuma.sdet.base.BaseTest;
import com.chukwuma.sdet.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    void userCanLogin() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.navigateToLogin();
        loginPage.login("Admin", "admin123");

        // page.waitForSelector("h6:has-text('Dashboard')");
        page.waitForURL("**/dashboard/**");

        assertTrue(loginPage.isDashboardVisible(), "Dashboard should be visible after login");
    }
}