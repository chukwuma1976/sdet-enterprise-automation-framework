package com.chukwuma.sdet.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage {

    private Page page;

    private final Locator usernameInput;
    private final Locator passwordInput;
    private final Locator loginButton;
    private final Locator errorMessage;

    public LoginPage(Page page) {
        this.page = page;
        this.usernameInput = page.locator("input[name='username']");
        this.passwordInput = page.locator("input[name='password']");
        this.loginButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
        this.errorMessage = page.locator(".oxd-alert-content-text");
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
        usernameInput.fill(username);
        passwordInput.fill(password);
        loginButton.click();
    }

    public boolean isDashboardVisible() {
        page.waitForSelector("h6:has-text('Dashboard')");
        String dashboardHeader = page.locator("header h6").textContent().trim();
        return dashboardHeader.equals("Dashboard");
    }

    public boolean isErrorMessageDisplayed() {
        errorMessage.waitFor();
        return true;
    }

    public String getErrorMessageText() {
        return errorMessage.textContent().trim();
    }
}