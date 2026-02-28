package com.chukwuma.sdet.utils;

import java.util.UUID;

public class TestDataFactory {

    public static String generateEmployeeId() {
        return UUID.randomUUID()
                .toString()
                .replaceAll("[^0-9]", "")
                .substring(0, 8);
    }
}
