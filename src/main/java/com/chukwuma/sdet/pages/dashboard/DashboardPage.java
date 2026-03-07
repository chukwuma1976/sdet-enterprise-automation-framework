package com.chukwuma.sdet.pages.dashboard;

import java.util.List;

import com.chukwuma.sdet.config.ConfigReader;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class DashboardPage {

    private Page page;

    private Locator dashboardHeader;
    private Locator profilePicture;
    private Locator timeAtWorkWidget;
    private Locator myActionsWidget;
    private Locator quickLaunchWidget;
    private Locator buzzWidget;
    private Locator employeesOnLeaveWidget;
    private Locator employeeDistributionSubUnit;
    private Locator employeeDistributionLocation;

    public DashboardPage(Page page) {
        this.page = page;

        dashboardHeader = page.getByRole(AriaRole.HEADING,
                new Page.GetByRoleOptions().setName("Dashboard"));

        profilePicture = page.getByRole(AriaRole.BANNER).getByRole(AriaRole.IMG,
                new Locator.GetByRoleOptions().setName("profile picture"));

        timeAtWorkWidget = page.getByText("Time at Work");
        myActionsWidget = page.getByText("My Actions");
        quickLaunchWidget = page.getByText("Quick Launch");
        buzzWidget = page.getByText("Buzz Latest Posts");
        employeesOnLeaveWidget = page.getByText("Employees on Leave Today");
        employeeDistributionSubUnit = page.getByText("Employee Distribution by Sub");
        employeeDistributionLocation = page.getByText("Employee Distribution by Location");
    }

    public boolean isLoaded() {
        page.waitForSelector("h6:has-text('Dashboard')");
        String dashboardHeader = page.locator("header h6").textContent().trim();
        return dashboardHeader.contains("Dashboard");
    }

    public void logout() {

        // Click the user dropdown icon directly
        page.locator(".oxd-userdropdown-icon").click();

        // Target logout from global page context
        Locator logoutOption = page.locator("text=Logout");

        logoutOption.waitFor();

        logoutOption.click();

        page.waitForURL(ConfigReader.get("auth_login.path"));
    }

    public boolean isOnLoginPage() {
        page.waitForURL(ConfigReader.get("auth_login.path"));
        return page.url().contains("/auth/login");
    }

    public void selectQuickLaunchOption(QuickLaunchOption option) {
        page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName(option.getLabel())).click();

        page.waitForURL("**" + option.getUrlFragment());
    }

    public void goBackToDashboard() {
        page.getByRole(
                AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Dashboard")).click();

        page.waitForURL(ConfigReader.get("dashboard.path"));
    }

    public boolean isOnExpectedQuickLaunchPage(QuickLaunchOption option) {
        return page.url().contains(option.getUrlFragment());
    }

    public void navigateToAdminPage() {
        page.navigate(ConfigReader.get("ADMIN_URL"));
        page.waitForURL(ConfigReader.get("admin_view_systems_users.path"));
    }

    public boolean isAdminAccessBlocked() {
        Locator alertLocator = page.getByRole(AriaRole.ALERT);
        alertLocator.waitFor();
        /*
         * Check if the alert contains "Credential Required" text or if we're still on
         * the dashboard
         */
        return page.url().contains("/dashboard")
                || alertLocator.filter(new Locator.FilterOptions()
                        .setHasText("Credential Required"))
                        .isVisible();
    }

    public void clickAdminModuleButton() {
        page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Admin")).click();
        page.waitForURL(ConfigReader.get("admin_view_systems_users.path"));
    }

    public boolean isAdminModuleAccessible() {
        Locator heading = page.getByRole(AriaRole.HEADING,
                new Page.GetByRoleOptions().setName("System Users"));
        heading.waitFor();
        return heading.isVisible();
    }

    // For Dashboard widgets
    public boolean isDashboardVisible() {
        dashboardHeader.waitFor();
        return dashboardHeader.isVisible();
    }

    public boolean isProfilePictureVisible() {
        profilePicture.waitFor();
        return profilePicture.isVisible();
    }

    public boolean isWidgetVisible(Locator widget) {
        widget.waitFor();
        return widget.isVisible();
    }

    public List<Locator> generateWidgetList() {
        return List.of(
                timeAtWorkWidget,
                myActionsWidget,
                quickLaunchWidget,
                buzzWidget,
                employeesOnLeaveWidget,
                employeeDistributionSubUnit,
                employeeDistributionLocation);
    }

}