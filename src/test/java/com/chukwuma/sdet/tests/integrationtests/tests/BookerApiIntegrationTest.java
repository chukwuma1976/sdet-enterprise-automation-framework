package com.chukwuma.sdet.tests.integrationtests.tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.chukwuma.sdet.config.ConfigReader;
import com.chukwuma.sdet.models.Booking;
import com.chukwuma.sdet.tests.api.base.BaseApiTest;
import com.chukwuma.sdet.utils.TestDataGenerator;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Tag("integration")
@Tag("api")
@Epic("Integration API Tests")
@Feature("End to end integration tests")
public class BookerApiIntegrationTest extends BaseApiTest {
    private String BASE_API_URL = ConfigReader.get("BOOKER_API_URL");
    private int createdBookingId;
    private String token;
    private Booking bookingPayload;

    @Test
    @Description("Create, update, and delete a booking")
    void createUpdateDeleteBooking() {
        bookingPayload = TestDataGenerator.createBooking();

        // Create a booking and validate status code, response content, and schema
        Response response = given()
                .baseUri(BASE_API_URL)
                .headers("Content-Type", "application/json")
                .body(bookingPayload)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .body("bookingid", notNullValue())
                .body(matchesJsonSchemaInClasspath("created-booking-schema.json"))
                .extract().response();

        // Extract booking id
        createdBookingId = response.jsonPath().get("bookingid");

        // Create updated booking payload
        bookingPayload.setFirstname("updatedFirstName");
        bookingPayload.setLastname("updatedLastName");

        // Update booking using extracted token
        token = extractToken();
        given()
                .baseUri(BASE_API_URL)
                .headers("Content-Type", "application/json", "Cookie", "token=" + token)
                .body(bookingPayload)
                .when()
                .put("/booking/{id}", createdBookingId)
                .then()
                .statusCode(200)
                .body("firstname", equalTo("updatedFirstName"))
                .body("lastname", equalTo("updatedLastName"));

        // Retrieve updated booking, confirm response contents, validate schema
        given()
                .baseUri(BASE_API_URL)
                .pathParam("id", createdBookingId)
                .when()
                .get("/booking/{id}")
                .then()
                .statusCode(200)
                .body("firstname", equalTo("updatedFirstName"))
                .body("lastname", equalTo("updatedLastName"))
                .assertThat().body(matchesJsonSchemaInClasspath("reservation-schema.json"));

        // Delete booking after extracting another token
        token = extractToken();
        given()
                .baseUri(BASE_API_URL)
                .headers("Content-Type", "application/json", "Cookie", "token=" + token)
                .pathParam("id", createdBookingId)
                .when()
                .delete("/booking/{id}")
                .then()
                .statusCode(201);

        // Attempt to retrieve booking to confirm it is has been deleted (404 Not Found)
        given()
                .baseUri(BASE_API_URL)
                .pathParam("id", createdBookingId)
                .when()
                .get("/booking/{id}")
                .then()
                .statusCode(404);

    }

    private String extractToken() {
        Response token = given()
                .baseUri(BASE_API_URL)
                .headers("Content-Type", "application/json")
                .body("{ \"username\": \"admin\", \"password\": \"password123\" }")
                .post("/auth");

        return token.jsonPath().get("token");
    }

}
