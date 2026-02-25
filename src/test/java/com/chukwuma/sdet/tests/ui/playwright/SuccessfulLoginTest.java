package com.chukwuma.sdet.tests.ui.playwright;

import com.chukwuma.sdet.base.BaseTest;
import com.chukwuma.sdet.models.LoginData;
import com.chukwuma.sdet.models.User;
import com.chukwuma.sdet.pages.LoginPage;
import com.chukwuma.sdet.utils.TestDataLoader;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;

public class SuccessfulLoginTest extends BaseTest {

    @Test
    @DisplayName("User can login with valid credentials")
    void userCanLoginWithValidCredentials() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.navigateToLogin();
        LoginData data = TestDataLoader.loadLoginData();
        User validUser = data.validUser;

        loginPage.login(validUser.username, validUser.password);

        page.waitForURL("**/dashboard/**");

        assertTrue(loginPage.isDashboardVisible(), "Dashboard should be visible after login");
    }
}