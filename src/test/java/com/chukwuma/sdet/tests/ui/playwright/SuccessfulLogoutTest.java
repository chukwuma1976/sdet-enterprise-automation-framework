package com.chukwuma.sdet.tests.ui.playwright;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junitpioneer.jupiter.RetryingTest;

import com.chukwuma.sdet.base.BaseTest;
import com.chukwuma.sdet.core.auth.AuthHelper;
import com.chukwuma.sdet.pages.dashboard.DashboardPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Tag("playwright")
@Tag("smoke")
@Tag("ui")
@Epic("Logout UI Tests")
@Feature("Successful Logout")
public class SuccessfulLogoutTest extends BaseTest {

    @RetryingTest(maxAttempts = 3)
    @DisplayName("User can logout after successful login")
    @Description("Verify that users can logout after successful login and are redirected to the login page")
    void shouldLogoutSuccessfully() {

        new AuthHelper(page).loginAsDefaultUser();

        DashboardPage dashboardPage = new DashboardPage(page);

        dashboardPage.logout();
        assertTrue(dashboardPage.isOnLoginPage());
    }
}
