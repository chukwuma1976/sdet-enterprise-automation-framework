package com.chukwuma.sdet.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage {

    private Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void navigateToLogin() {

        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Wait for the actual login form (not just DOM)
        page.waitForSelector("input[name='username']");
        page.waitForSelector("input[name='password']");

        // VERY important — wait for network to settle
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void login(String username, String password) {

        Locator usernameField = page.getByPlaceholder("Username");
        Locator passwordField = page.getByPlaceholder("Password");

        usernameField.fill(username);
        passwordField.fill(password);

        Locator loginButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Login"));

        // Wait explicitly for it to become enabled
        loginButton.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));

        page.waitForCondition(() -> loginButton.isEnabled());

        loginButton.click();
    }

    public boolean isDashboardVisible() {
        page.waitForSelector("h6:has-text('Dashboard')");
        String dashboardHeader = page.locator("header h6").textContent().trim();
        return dashboardHeader.equals("Dashboard");
    }

    public String getLoginErrorMessage() {
        page.waitForSelector(".oxd-alert-content-text");
        return page.locator(".oxd-alert-content-text").textContent().trim();
    }
}