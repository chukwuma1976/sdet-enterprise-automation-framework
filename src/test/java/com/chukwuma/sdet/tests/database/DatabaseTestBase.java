package com.chukwuma.sdet.tests.database;

import org.junit.jupiter.api.BeforeAll;

public class DatabaseTestBase {

    @BeforeAll
    static void startDatabase() {
        DatabaseTestContainer.start();
    }

    // no explicit @AfterAll stop() needed — Testcontainers' Ryuk shutdown hook
    // tears the container down when the JVM exits, same reasoning as the TestNG
    // version
}