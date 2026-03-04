package com.chukwuma.sdet.tests.api.tests;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.chukwuma.sdet.tests.api.base.BaseApiTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Tag("regression")
@Tag("api")
@Epic("User API Tests")
@Feature("CRUD operations on Users")
class UserSchemaTest extends BaseApiTest {

    @Test
    @Description("Validate the user schema for a single user")
    void validateUserSchema() {

        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users/2")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("user-schema.json"));
    }
}