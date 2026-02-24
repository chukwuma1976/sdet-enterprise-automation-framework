package com.chukwuma.sdet.tests.ui.playwright;

import com.chukwuma.sdet.base.BaseTest;
import com.chukwuma.sdet.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidLoginTest extends BaseTest {

    @Test
    void userCannotLoginWithInvalidCredentials() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.navigateToLogin();
        loginPage.login("Admin", "wrongpassword");

        String errorMessage = loginPage.getLoginErrorMessage();

        assertEquals("Invalid credentials", errorMessage);
    }
}
