package com.chukwuma.sdet.tests.ui.playwright;

import com.chukwuma.sdet.base.BaseTest;
import com.chukwuma.sdet.pages.dashboard.DashboardPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import com.chukwuma.sdet.core.auth.AuthHelper;

import org.junitpioneer.jupiter.RetryingTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

@Tag("playwright")
@Tag("smoke")
@Tag("ui")
@Epic("Dashboard UI Tests")
@Feature("Dashboard Display")
public class DashboardTest extends BaseTest {

    @BeforeEach
    void login() {
        new AuthHelper(page).loginAsDefaultUser();
    }

    @RetryingTest(maxAttempts = 3)
    @DisplayName("Dashboard is displayed after successful login")
    @Description("Dashboard should be displayed after successful login")
    void shouldDisplayDashboardAfterSuccessfulLogin() {

        DashboardPage dashboardPage = new DashboardPage(page);

        assertTrue(
                dashboardPage.isLoaded(),
                "Dashboard should be visible after successful login");
    }

    @RetryingTest(maxAttempts = 3)
    @DisplayName("Dashboard widgets should be visible")
    @Description("Dashboard header, image, and widgets should be visible")
    void dashboardWidgetsShouldBeVisible() {

        DashboardPage dashboardPage = new DashboardPage(page);

        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(dashboardPage.isDashboardVisible())
                .as("Dashboard header should be visible")
                .isTrue();

        softly.assertThat(dashboardPage.isProfilePictureVisible())
                .as("Profile picture should be visible")
                .isTrue();

        dashboardPage.generateWidgetList().forEach(widget -> {
            String message = widget.textContent().trim() + " should be visible.";
            softly.assertThat(dashboardPage.isWidgetVisible(widget))
                    .as(message)
                    .isTrue();
        });

        softly.assertAll();
    }

}