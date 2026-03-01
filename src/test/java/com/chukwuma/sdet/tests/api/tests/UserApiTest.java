package com.chukwuma.sdet.tests.api.tests;

import com.chukwuma.sdet.tests.api.base.BaseApiTest;
import com.chukwuma.sdet.tests.api.service.UserService;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static com.chukwuma.sdet.tests.api.specs.ResponseSpecs.*;

@Epic("User API Tests")
@Feature("CRUD operations on Users")
public class UserApiTest extends BaseApiTest {

    UserService userService = new UserService();

    @Test
    @Description("Get a single user by ID")
    void shouldGetUserSuccessfully() {
        Response response = userService.getUserById(2);
        response.prettyPrint();
        response.then().spec(okResponse);
        assertEquals("Ervin Howell",
                response.jsonPath().get("name"));
    }

    @Test
    @Description("Create a new user")
    void shouldCreateUserSuccessfully() {
        Response response = userService.createUser("Chukwuma", "SDET");
        response.prettyPrint();
        response.then().spec(createdResponse);
        assertEquals("Chukwuma",
                response.jsonPath().get("name"));
    }

    @Test
    @Description("Update an existing user")
    void shouldUpdateUserSuccessfully() {
        Response response = userService.updateUser(2, "UpdatedName", "QA");
        response.prettyPrint();
        response.then().spec(okResponse);
    }

    @Test
    @Description("Delete a user")
    void shouldDeleteUserSuccessfully() {
        Response response = userService.deleteUser(2);
        response.prettyPrint();
        response.then().spec(okResponse);
    }
}
