package com.chukwuma.sdet.tests.ui.playwright;

import com.chukwuma.sdet.base.BaseTest;
import com.chukwuma.sdet.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InvalidLoginTest extends BaseTest {

    @Test
    void userCannotLoginWithInvalidCredentials() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.navigateToLogin();
        loginPage.login("Admin", "wrongPassword");

        Assertions.assertTrue(loginPage.isErrorMessageDisplayed());
        Assertions.assertEquals(
                "Invalid credentials",
                loginPage.getErrorMessageText());
    }
}