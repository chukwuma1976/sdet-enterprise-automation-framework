package com.chukwuma.sdet.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseSeeder {

    public static void reseed() throws Exception {
        try (Connection conn = DatabaseConnector.getConnection();
                Statement stmt = conn.createStatement()) {

            // wipe in FK-safe order — children before parents
            stmt.execute("TRUNCATE TABLE users, employees, user_roles RESTART IDENTITY CASCADE");

            String seedSql = Files.readString(Path.of("src/test/resources/db/seed.sql"));
            stmt.execute(seedSql);
        }
    }
}