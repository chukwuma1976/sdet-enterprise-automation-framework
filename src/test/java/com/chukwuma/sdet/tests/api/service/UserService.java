package com.chukwuma.sdet.tests.api.service;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import com.chukwuma.sdet.tests.api.specs.RequestSpecFactory;

public class UserService {

    public Response getUsers() {
        return given()
                .spec(RequestSpecFactory.getRequestSpec())
                .when()
                .get("/users");
    }

    public Response getUserById(int id) {
        return given()
                .spec(RequestSpecFactory.getRequestSpec())
                .when()
                .get("/users/" + id);
    }

    public Response createUser(String name, String username) {
        String body = "{ \"name\": \"" + name + "\", \"username\": \"" + username + "\" }";
        return given()
                .spec(RequestSpecFactory.getRequestSpec())
                .body(body)
                .when()
                .post("/users");
    }

    public Response updateUser(int id, String name, String job) {
        String body = "{ \"name\": \"" + name + "\", \"job\": \"" + job + "\" }";
        return given()
                .spec(RequestSpecFactory.getRequestSpec())
                .body(body)
                .when()
                .put("/users/" + id);
    }

    public Response deleteUser(int id) {
        return given()
                .spec(RequestSpecFactory.getRequestSpec())
                .when()
                .delete("/users/" + id);
    }
}