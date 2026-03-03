package com.chukwuma.sdet.tests.api.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.chukwuma.sdet.models.User;

@Tag("api")
@Epic("Auth API Tests")
@Feature("Authorizatoion and Authentication")
class DummyJsonAuthTest {

    @Test
    @Description("Login using dynamically fetched user and access protected endpoint")
    void loginUsingDynamicUser() {

        // 🔹 Fetch user dynamically
        User user = fetchUser();

        // 🔹 Login
        String token = given()
                .contentType("application/json")
                .body(user) // automatic serialization
                .when()
                .post("https://dummyjson.com/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .path("accessToken");

        // 🔹 Access protected endpoint
        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://dummyjson.com/auth/me")
                .then()
                .statusCode(200)
                .body("username", equalTo(user.getUsername()));
    }

    @Test
    @Description("Access protected endpoint without token should fail")
    void shouldFailWithoutToken() {

        given()
                .when()
                .get("https://dummyjson.com/auth/me")
                .then()
                .statusCode(401);
    }

    @Test
    @Description("Access protected endpoint with invalid token should fail")
    void shouldFailWithInvalidToken() {

        given()
                .header("Authorization", "Bearer invalid_token")
                .when()
                .get("https://dummyjson.com/auth/me")
                .then()
                .statusCode(401);
    }

    private User fetchUser() {

        return given()
                .when()
                .get("https://dummyjson.com/users/1")
                .then()
                .statusCode(200)
                .extract()
                .as(User.class);
    }
}