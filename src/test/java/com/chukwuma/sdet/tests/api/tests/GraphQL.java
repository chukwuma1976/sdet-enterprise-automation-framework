package com.chukwuma.sdet.tests.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.chukwuma.sdet.tests.api.config.ApiConfig;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;

@Tag("regression")
@Tag("api")
@Epic("GraphQL API Tests")
@Feature("GraphQL API Testing")
public class GraphQL {
    private String graphQLUrl = ApiConfig.GRAPHQL_URL;

    @Test
    @Description("Get a users list")
    void shouldGetACountry() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("code", "JP");

        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put(
                "query",
                """
                        query GetCountry($code: ID!) {
                        country(code: $code) {
                                        code
                                        name
                                        capital
                                        currency
                                        phone
                                        emoji
                        }
                        }
                        """);

        requestBody.put("variables", variables);

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(graphQLUrl)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("country-schema.json"));

    }

    @Test
    @Description("Get a users list")
    void shouldGetCountries() {

        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put(
                "query",
                """
                        query GetCountries {
                        countries {
                                        code
                                        name
                                        capital
                                        currency
                                        phone
                                        emoji
                        }
                        }
                        """);

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(graphQLUrl)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("countries-schema.json"));

    }

}
