package com.chukwuma.sdet.tests.ui.playwright;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junitpioneer.jupiter.RetryingTest;

import com.chukwuma.sdet.base.BaseTest;
import com.chukwuma.sdet.config.ConfigReader;
import com.chukwuma.sdet.core.auth.AuthHelper;
import com.chukwuma.sdet.pages.dashboard.DashboardPage;
import com.chukwuma.sdet.pages.user.UserManagementHelper;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Tag("playwright")
@Tag("regression")
@Tag("ui")
@Epic("Role Based UI Tests")
@Feature("Role-Based Access Control")
public class RoleBasedAccessTest extends BaseTest {

    private DashboardPage dashboardPage;

    @BeforeEach
    void setup() {
        dashboardPage = new DashboardPage(page);
    }

    @RetryingTest(maxAttempts = 3)
    @Description("Admin user should access Admin module")
    void AdminUserShouldAccessAdminModule() {

        new AuthHelper(page).login(
                ConfigReader.get("ADMIN_USERNAME"),
                ConfigReader.get("ADMIN_PASSWORD"));

        dashboardPage.clickAdminModuleButton();
        assertTrue(dashboardPage.isAdminModuleAccessible(), "Admin module should be accessible to Admin users");
    }

    @RetryingTest(maxAttempts = 3)
    @Description("ESS user should not access Admin module")
    void essUserShouldNotAccessAdminModule() {

        UserManagementHelper helper = new UserManagementHelper(page);
        helper.ensureEssUserExists();

        new DashboardPage(page).logout();

        new AuthHelper(page).login(
                ConfigReader.get("ESS_USERNAME"),
                ConfigReader.get("ESS_PASSWORD"));

        DashboardPage dashboardPage = new DashboardPage(page);

        dashboardPage.navigateToAdminPage();

        assertTrue(
                dashboardPage.isAdminAccessBlocked(),
                "ESS user should not access Admin module");
    }

}
