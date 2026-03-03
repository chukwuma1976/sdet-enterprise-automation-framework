package com.chukwuma.sdet.tests.api.tests;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.Test;

import com.chukwuma.sdet.tests.api.base.BaseApiTest;

class UserSchemaTest extends BaseApiTest {

    @Test
    void validateUserSchema() {

        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users/2")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("user-schema.json"));
    }
}