package com.chukwuma.sdet.tests.ui.playwright;

import com.chukwuma.sdet.base.BaseTest;
import com.chukwuma.sdet.pages.dashboard.DashboardPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import com.chukwuma.sdet.core.auth.AuthHelper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

@Tag("smoke")
@Tag("ui")
@Epic("Dashboard UI Tests")
@Feature("Dashboard Display")
public class DashboardTest extends BaseTest {

    @Test
    @DisplayName("Dashboard is displayed after successful login")
    @Description("Dashboard should be displayed after successful login")
    void shouldDisplayDashboardAfterSuccessfulLogin() {

        DashboardPage dashboardPage = new DashboardPage(page);

        new AuthHelper(page).loginAsDefaultUser();

        assertTrue(
                dashboardPage.isLoaded(),
                "Dashboard should be visible after successful login");
    }
}