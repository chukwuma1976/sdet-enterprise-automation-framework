package com.chukwuma.sdet.tests.api.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.chukwuma.sdet.tests.api.base.BaseApiTest;
import com.chukwuma.sdet.tests.api.config.ApiConfig;
import com.chukwuma.sdet.tests.api.models.ApiUser;

@Tag("api")
@Epic("Auth API Tests")
@Feature("Authentication and Authorization, Role-Based Access Control")
class AuthTest extends BaseApiTest {

    private String loginUrl = ApiConfig.AUTH_BASE_URL + "auth/login";
    private String meUrl = ApiConfig.AUTH_BASE_URL + "auth/me";
    private String usersUrl = ApiConfig.AUTH_BASE_URL + "users";

    @Test
    @Description("Login using dynamically fetched user and access protected endpoint")
    void loginUsingDynamicUser() {

        // 🔹 Fetch user dynamically
        ApiUser user = fetchUserById(1);

        // 🔹 Login
        String token = login(user);

        // 🔹 Access protected endpoint
        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(meUrl)
                .then()
                .statusCode(200)
                .body("username", equalTo(user.getUsername()));
    }

    @Test
    @Description("Access protected endpoint without token should fail")
    void shouldFailWithoutToken() {

        given()
                .when()
                .get(meUrl)
                .then()
                .statusCode(401);
    }

    @Test
    @Description("Access protected endpoint with invalid token should fail")
    void shouldFailWithInvalidToken() {

        given()
                .header("Authorization", "Bearer invalid_token")
                .when()
                .get(meUrl)
                .then()
                .statusCode(401);
    }

    @Test
    @Description("Admin user should have admin role")
    void adminShouldHaveAdminRole() {
        int adminId = getFirstUserIdByRole("admin");
        ApiUser admin = fetchUserById(adminId);
        String token = login(admin);

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(meUrl)
                .then()
                .statusCode(200)
                .body("role", equalTo("admin"));
    }

    @Test
    @Description("Regular user should have user role")
    void userShouldHaveUserRole() {

        int userId = getFirstUserIdByRole("user");
        ApiUser user = fetchUserById(userId); // regular user
        String token = login(user);

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(meUrl)
                .then()
                .statusCode(200)
                .body("role", equalTo("user"));
    }

    @Test
    @Description("Moderator user should have moderator role")
    void moderatorShouldHaveModeratorRole() {

        int moderatorId = getFirstUserIdByRole("moderator");
        ApiUser moderator = fetchUserById(moderatorId); // moderator user
        String token = login(moderator);

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(meUrl)
                .then()
                .statusCode(200)
                .body("role", equalTo("moderator"));
    }

    private ApiUser fetchUserById(int id) {

        return given()
                .when()
                .get(usersUrl + "/" + id)
                .then()
                .statusCode(200)
                .extract()
                .as(ApiUser.class);
    }

    private String login(ApiUser user) {
        return given()
                .contentType("application/json")
                .body(user)
                .when()
                .post(loginUrl)
                .then()
                .statusCode(200)
                .extract()
                .path("accessToken");
    }

    private int getFirstUserIdByRole(String role) {

        return given()
                .when()
                .get(usersUrl)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getInt("users.find { it.role == '" + role + "' }.id");
    }
}