package com.chukwuma.sdet.pages;

import com.microsoft.playwright.Locator;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage {

    private Page page;

    private String usernameInput = "input[name='username']";
    private String passwordInput = "input[name='password']";
    private String loginButton = "button[type='submit']";

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

        page.getByPlaceholder("Username").fill(username);
        page.getByPlaceholder("Password").fill(password);

        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Login")).click();
    }
}