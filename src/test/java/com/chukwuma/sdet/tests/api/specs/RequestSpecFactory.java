package com.chukwuma.sdet.tests.api.specs;

import com.chukwuma.sdet.tests.api.config.ApiConfig;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecFactory {

    private static RequestSpecification requestSpec;

    static {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(ApiConfig.BASE_URL)
                .setContentType("application/json")
                .build();
    }

    public static RequestSpecification getRequestSpec() {
        return requestSpec;
    }
}