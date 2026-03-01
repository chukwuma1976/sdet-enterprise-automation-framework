package com.chukwuma.sdet.tests.api.base;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import org.junit.jupiter.api.BeforeAll;

import com.chukwuma.sdet.tests.api.config.ApiConfig;

public class BaseApiTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = ApiConfig.BASE_URL;

        // Attach Allure filter globally for reporting
        Filter allureFilter = new AllureRestAssured();

        RestAssured.filters(allureFilter);
    }
}