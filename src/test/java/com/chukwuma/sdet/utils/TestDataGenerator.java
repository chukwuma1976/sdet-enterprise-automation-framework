package com.chukwuma.sdet.utils;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.chukwuma.sdet.models.Booking;
import com.chukwuma.sdet.models.BookingDates;

public class TestDataGenerator {

    public static Booking createBooking() {
        Booking booking = new Booking();
        booking.setFirstname("James" + System.currentTimeMillis());
        booking.setLastname("Brown" + System.currentTimeMillis());
        booking.setTotalprice(150);
        booking.setDepositpaid(true);

        BookingDates dates = new BookingDates();
        dates.setCheckin("2024-01-01");
        dates.setCheckout("2024-01-10");
        booking.setBookingdates(dates);

        booking.setAdditionalneeds("Breakfast");

        return booking;
    }

    public static Map<String, String> createUIBookingData() {
        Map<String, String> bookingData = new HashMap<>();
        bookingData.put("firstname", "John");
        bookingData.put("lastname", "Doe");
        bookingData.put("email", "john.doe@example.com");
        bookingData.put("phone", "123-456-7890");

        return bookingData;
    }

    public static Map<String, String> messagePayload() {
        Map<String, String> messagePayload = new HashMap<>();

        messagePayload.put("name", "Paul Uzoma");
        messagePayload.put("email", "paul.uzoma@gmail.com");
        messagePayload.put("phone", "(800) 123-4567");
        messagePayload.put("subject", "Thank you");
        messagePayload.put("description", "Thank you for allowing me to do API testing using RestAssured");

        return messagePayload;
    }

    public static String getTestFilePath(String fileName) {
        return Paths.get(
                System.getProperty("user.dir"),
                "src",
                "test",
                "resources",
                fileName).toAbsolutePath().toString();
    }

    public static Map<String, String> createUserProfile() {
        Map<String, String> userProfile = new HashMap<>();

        userProfile.put("name", "Tester" + System.currentTimeMillis());
        userProfile.put("email", userProfile.get("name") + "@gmail.com");
        userProfile.put("password", "Pwd" + System.currentTimeMillis());

        return userProfile;
    }

}
