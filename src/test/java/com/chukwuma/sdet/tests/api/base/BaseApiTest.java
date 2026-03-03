package com.chukwuma.sdet.tests.api.base;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.MDC;

import com.chukwuma.sdet.base.BaseTest;
import com.chukwuma.sdet.tests.api.config.ApiConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseApiTest {

    protected static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = ApiConfig.BASE_URL;

        // Attach Allure filter globally for reporting
        Filter allureFilter = new AllureRestAssured();

        RestAssured.filters(allureFilter);
    }

    @BeforeEach
    void setUp(TestInfo testInfo) {
        String testName = testInfo.getDisplayName();

        MDC.put("testName", testName);

        log.info("========== START TEST: {} ==========", testName);
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {

        log.info("========== END TEST: {} ==========",
                testInfo.getDisplayName());

        MDC.clear();
    }
}