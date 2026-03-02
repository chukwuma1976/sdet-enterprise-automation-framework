package com.chukwuma.sdet.core.auth;

import com.chukwuma.sdet.config.ConfigReader;
import com.chukwuma.sdet.pages.LoginPage;
import com.microsoft.playwright.Page;

public class AuthHelper {

    private final Page page;

    public AuthHelper(Page page) {
        this.page = page;
    }

    public void loginAsDefaultUser() {

        page.navigate(ConfigReader.get("BASE_URL"));

        new LoginPage(page).login(
                ConfigReader.get("APP_USERNAME"),
                ConfigReader.get("APP_PASSWORD"));
    }

    public void login(String username, String password) {
        page.navigate(ConfigReader.get("BASE_URL"));
        new LoginPage(page).login(username, password);
    }
}
