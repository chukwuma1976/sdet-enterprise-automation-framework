package com.chukwuma.sdet.pages;

import com.microsoft.playwright.Page;

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

    public void navigateToUserManagement() {
        page.click("text=Users");
    }

    public void logout() {
        page.click("text=Logout");
    }
}