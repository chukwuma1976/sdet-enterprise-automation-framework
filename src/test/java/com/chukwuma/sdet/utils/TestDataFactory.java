package com.chukwuma.sdet.utils;

import java.util.UUID;

public class TestDataFactory {

    public static String generateEmployeeId() {
        return UUID.randomUUID()
                .toString()
                .replaceAll("[^0-9]", "")
                .substring(0, 8);
    }

    public static String generateUniqueFirstName() {
        return "FN_" + System.currentTimeMillis();
    }

    public static String generateUniqueLastName() {
        return "LN_" + System.currentTimeMillis();
    }

    public static String generateUniqueMiddleName() {
        return "MN_" + System.currentTimeMillis();
    }

}
