package com.chukwuma.sdet.tests.ui.playwright;

import com.chukwuma.sdet.base.BaseTest;
import com.chukwuma.sdet.models.LoginData;
import com.chukwuma.sdet.models.User;
import com.chukwuma.sdet.pages.LoginPage;
import com.chukwuma.sdet.utils.TestDataLoader;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class InvalidLoginTest extends BaseTest {

    @ParameterizedTest
    @MethodSource("invalidUserProvider")
    @DisplayName("User cannot login with invalid credentials")
    void userCannotLoginWithInvalidCredentials(User user) {

        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateToLogin();
        loginPage.login(user.username, user.password);

        loginPage.verifyErrorMessageText("Invalid credentials");
    }

    static Stream<User> invalidUserProvider() {
        LoginData data = TestDataLoader.loadLoginData();
        return data.invalidUsers.stream();
    }

    @Test
    @DisplayName("User cannot login when username is blank")
    void userCannotLoginWhenUsernameBlank() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.navigateToLogin();
        loginPage.login("", "admin123");

        assertTrue(loginPage.isUsernameRequiredDisplayed());
    }

    @Test
    @DisplayName("User cannot login when password is blank")
    void userCannotLoginWhenPasswordBlank() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.navigateToLogin();
        loginPage.login("Admin", "");

        assertTrue(loginPage.isPasswordRequiredDisplayed());
    }
}