package com.chukwuma.sdet.tests.api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.lessThan;

import com.chukwuma.sdet.tests.api.config.ApiPerformanceThresholds;

public class ResponseSpecs {

        public static ResponseSpecification okResponse = new ResponseSpecBuilder()
                        .expectStatusCode(200) // DELETE returns 200 in JSONPlaceholder
                        .expectResponseTime(lessThan(ApiPerformanceThresholds.STANDARD_RESPONSE_TIME))
                        .build();

        public static ResponseSpecification createdResponse = new ResponseSpecBuilder()
                        .expectStatusCode(201)
                        .build();

        public static ResponseSpecification noContentResponse = new ResponseSpecBuilder()
                        .expectStatusCode(204)
                        .build();
}
