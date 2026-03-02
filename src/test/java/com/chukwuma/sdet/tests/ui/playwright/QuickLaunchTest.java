package com.chukwuma.sdet.tests.ui.playwright;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.chukwuma.sdet.base.BaseTest;
import com.chukwuma.sdet.core.auth.AuthHelper;
import com.chukwuma.sdet.pages.dashboard.DashboardPage;
import com.chukwuma.sdet.pages.dashboard.QuickLaunchOption;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Tag("ui")
@Epic("Dashboard UI Tests")
@Feature("Quick Launch Navigation")
public class QuickLaunchTest extends BaseTest {

    private DashboardPage dashboardPage;

    @BeforeEach
    void setup() {
        new AuthHelper(page).loginAsDefaultUser();

        dashboardPage = new DashboardPage(page);
    }

    @Test
    @DisplayName("User can navigate to Assign Leave page from Quick Launch")
    @Description("Verify that users can navigate to the Assign Leave page from Quick Launch and that the correct page is displayed")
    void testAssignLeaveNavigation() {
        dashboardPage.selectQuickLaunchOption(QuickLaunchOption.ASSIGN_LEAVE);
        assertTrue(dashboardPage.isOnExpectedQuickLaunchPage(QuickLaunchOption.ASSIGN_LEAVE));
        dashboardPage.goBackToDashboard();
    }

    @Test
    @DisplayName("User can navigate to Leave List page from Quick Launch")
    @Description("Verify that users can navigate to the Leave List page from Quick Launch and that the correct page is displayed")
    void testLeaveListNavigation() {
        dashboardPage.selectQuickLaunchOption(QuickLaunchOption.LEAVE_LIST);
        assertTrue(dashboardPage.isOnExpectedQuickLaunchPage(QuickLaunchOption.LEAVE_LIST));
        dashboardPage.goBackToDashboard();
    }

    @Test
    @DisplayName("User can navigate to Timesheets page from Quick Launch")
    @Description("Verify that users can navigate to the Timesheets page from Quick Launch and that the correct page is displayed")
    void testTimesheetsNavigation() {
        dashboardPage.selectQuickLaunchOption(QuickLaunchOption.TIMESHEETS);
        assertTrue(dashboardPage.isOnExpectedQuickLaunchPage(QuickLaunchOption.TIMESHEETS));
        dashboardPage.goBackToDashboard();
    }

    @Test
    @DisplayName("User can navigate to Apply Leave page from Quick Launch")
    @Description("Verify that users can navigate to the Apply Leave page from Quick Launch and that the correct page is displayed")
    void testApplyLeaveNavigation() {
        dashboardPage.selectQuickLaunchOption(QuickLaunchOption.APPLY_LEAVE);
        assertTrue(dashboardPage.isOnExpectedQuickLaunchPage(QuickLaunchOption.APPLY_LEAVE));
        dashboardPage.goBackToDashboard();
    }

    @Test
    @DisplayName("User can navigate to My Leave page from Quick Launch")
    @Description("Verify that users can navigate to the My Leave page from Quick Launch and that the correct page is displayed")
    void testMyLeaveNavigation() {
        dashboardPage.selectQuickLaunchOption(QuickLaunchOption.MY_LEAVE);
        assertTrue(dashboardPage.isOnExpectedQuickLaunchPage(QuickLaunchOption.MY_LEAVE));
        dashboardPage.goBackToDashboard();
    }

    @Test
    @DisplayName("User can navigate to My Timesheet page from Quick Launch")
    @Description("Verify that users can navigate to the My Timesheet page from Quick Launch and that the correct page is displayed")
    void testMyTimesheetNavigation() {
        dashboardPage.selectQuickLaunchOption(QuickLaunchOption.MY_TIMESHEET);
        assertTrue(dashboardPage.isOnExpectedQuickLaunchPage(QuickLaunchOption.MY_TIMESHEET));
        dashboardPage.goBackToDashboard();
    }
}