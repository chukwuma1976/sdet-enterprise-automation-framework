package com.chukwuma.sdet.tests.database;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import com.chukwuma.sdet.utils.DatabaseConnector;
import com.chukwuma.sdet.utils.DatabaseSeeder;

import io.qameta.allure.Feature;

@Tag("regression")
@Tag("database")
@Feature("HRM Database validations")
public class HRMDatabaseTest extends DatabaseTestBase {

    @BeforeAll
    public static void resetData() throws Exception {
        DatabaseSeeder.reseed();
    }

    @Test
    @DisplayName("Can retrieve a user from the database by username")
    public void getUserByName() throws Exception {
        try (Connection conn = DatabaseConnector.getConnection();) {

            ResultSet rs = UserRepository.getUserByUsername(conn, "jamesbrown3531");

            rs.next();

            assertNotNull(rs.getInt("id"));
            assertEquals(rs.getString("first_name"), "James");
            assertEquals(rs.getString("middle_name"), "T");
            assertEquals(rs.getString("last_name"), "Brown");
            assertNotNull(rs.getBoolean("deleted"));
            assertNotNull(rs.getBoolean("status"));
            assertNotNull(rs.getString("role_name"));

        }
    }

    @Test
    @DisplayName("Cannot retrieve a user with an invalid username")
    public void getUsers() throws Exception {
        try (Connection conn = DatabaseConnector.getConnection();) {

            ResultSet rs = UserRepository.getUserByUsername(conn, "invalidusername");

            assertFalse(rs.next());

        }
    }

    @Test
    @DisplayName("Can update a user status")
    public void updateAUserStatus() throws Exception {
        try (Connection conn = DatabaseConnector.getConnection();) {

            UserRepository.updateUserStatus(conn, "Annalise.Olson", true);

            ResultSet rs = UserRepository.getUserByUsername(conn, "Annalise.Olson");
            rs.next();
            assertTrue(rs.getBoolean("status"));

        }
    }

    @Test
    @DisplayName("Can soft delete user")
    public void softDeleteUser() throws Exception {
        try (Connection conn = DatabaseConnector.getConnection();) {

            UserRepository.softDeleteUser(conn, "Cricket1");

            ResultSet rs = UserRepository.getUserByUsername(conn, "Cricket1");
            rs.next();
            assertTrue(rs.getBoolean("deleted"));

        }
    }

    @Test
    @DisplayName("Create user")
    public void createUser() throws Exception {
        try (Connection conn = DatabaseConnector.getConnection();) {

            UserRepository.createUser(conn, 919, "seleniumDriver", 174, 1);

            ResultSet rs = UserRepository.getUserByUsername(conn, "seleniumDriver");
            rs.next();
            assertEquals(rs.getInt("id"), 919);
            assertEquals(rs.getString("role_name"), "Admin");

        }
    }
}