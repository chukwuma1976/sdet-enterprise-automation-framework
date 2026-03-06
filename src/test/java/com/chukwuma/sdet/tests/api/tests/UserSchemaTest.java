package com.chukwuma.sdet.tests.api.tests;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.chukwuma.sdet.tests.api.base.BaseApiTest;
import com.chukwuma.sdet.tests.api.config.ApiConfig;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Tag("playwright")
@Tag("regression")
@Tag("api")
@Epic("User API Tests")
@Feature("Schema validation on User API")
class UserSchemaTest extends BaseApiTest {

    @Test
    @Description("Validate the user schema for a single user")
    void validateUserSchema() {

        given()
                .when()
                .get(ApiConfig.BASE_URL + "/users/2")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("user-schema.json"));
    }
}