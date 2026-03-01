package com.chukwuma.sdet.pages;

import com.chukwuma.sdet.config.ConfigReader;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;

public class DashboardPage {

    private final Page page;

    public DashboardPage(Page page) {
        this.page = page;
    }

    public boolean isLoaded() {
        page.waitForSelector("h6:has-text('Dashboard')");
        String dashboardHeader = page.locator("header h6").textContent().trim();
        return dashboardHeader.equals("Dashboard");
    }

    public void logout() {

        page.waitForURL("**/dashboard/**");

        // Click the user dropdown icon directly
        page.locator(".oxd-userdropdown-icon").click();

        // Target logout from global page context
        Locator logoutOption = page.locator("text=Logout");

        logoutOption.waitFor();

        logoutOption.click();

        page.waitForURL("**/auth/login");
    }

    public boolean isOnLoginPage() {
        page.waitForURL("**/auth/login");
        return page.url().contains("/auth/login");
    }
}