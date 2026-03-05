package com.chukwuma.sdet.tests.api.base;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.MDC;

import com.chukwuma.sdet.tests.api.config.ApiConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseApiTest {

    protected static final Logger log = LoggerFactory.getLogger(BaseApiTest.class);
    private long startTime;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = ApiConfig.BASE_URL;

        // Attach Allure filter globally for reporting
        Filter allureFilter = new AllureRestAssured();

        RestAssured.filters(allureFilter);
    }

    @BeforeEach
    void beforeEach(TestInfo testInfo) {
        startTime = System.currentTimeMillis();
        MDC.put("testName", testInfo.getDisplayName());
        log.info("========== START TEST ==========");
    }

    @AfterEach
    void afterEach(TestInfo testInfo) {
        long duration = System.currentTimeMillis() - startTime;
        log.info("========== END TEST ({} ms) ==========", duration);
        MDC.clear();
    }
}