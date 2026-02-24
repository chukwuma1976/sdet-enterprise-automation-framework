package com.chukwuma.sdet.tests.ui.playwright;

import com.chukwuma.sdet.base.BaseTest;
import com.chukwuma.sdet.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class InvalidLoginTest extends BaseTest {

    @ParameterizedTest
    @CsvSource({
            "wrongUser, admin123",
            "Admin, wrongPass",
            "wrongUser, wrongPass"
    })
    void userCannotLoginWithInvalidCredentials(String username, String password) {

        LoginPage loginPage = new LoginPage(page);

        loginPage.navigateToLogin();
        loginPage.login(username, password);

        loginPage.verifyErrorMessageText("Invalid credentials");
    }

    @Test
    void userCannotLoginWhenUsernameBlank() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.navigateToLogin();
        loginPage.login("", "admin123");

        assertTrue(loginPage.isUsernameRequiredDisplayed());
    }

    @Test
    void userCannotLoginWhenPasswordBlank() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.navigateToLogin();
        loginPage.login("Admin", "");

        assertTrue(loginPage.isPasswordRequiredDisplayed());
    }
}