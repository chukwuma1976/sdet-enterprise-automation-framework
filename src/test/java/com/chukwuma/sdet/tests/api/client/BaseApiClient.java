package com.chukwuma.sdet.tests.api.client;

import com.chukwuma.sdet.tests.api.specs.RequestSpecFactory;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseApiClient {

    protected Response get(String endpoint) {
        return given()
                .spec(RequestSpecFactory.getRequestSpec())
                .when()
                .get(endpoint);
    }

    protected Response post(String endpoint, Object body) {
        return given()
                .spec(RequestSpecFactory.getRequestSpec())
                .body(body)
                .when()
                .post(endpoint);
    }

    protected Response put(String endpoint, Object body) {
        return given()
                .spec(RequestSpecFactory.getRequestSpec())
                .body(body)
                .when()
                .put(endpoint);
    }

    protected Response delete(String endpoint) {
        return given()
                .spec(RequestSpecFactory.getRequestSpec())
                .when()
                .delete(endpoint);
    }
}