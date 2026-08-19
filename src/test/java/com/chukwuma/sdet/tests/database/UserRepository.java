package com.chukwuma.sdet.tests.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository {

    public static ResultSet getUserByUsername(Connection conn, String userName) throws Exception {
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT u.id, u.user_name, u.deleted, u.status, " +
                        "       e.employee_id, e.first_name, e.middle_name, e.last_name, " +
                        "       r.name AS role_name " +
                        "FROM users u " +
                        "JOIN employees e ON u.emp_number = e.emp_number " +
                        "JOIN user_roles r ON u.user_role_id = r.id " +
                        "WHERE u.user_name = ?");
        stmt.setString(1, userName);
        return stmt.executeQuery();
    }

    public static void updateUserStatus(Connection conn, String userName, boolean status) throws Exception {
        PreparedStatement stmt = conn.prepareStatement(
                "UPDATE users SET status = ? WHERE user_name = ?");
        stmt.setBoolean(1, status);
        stmt.setString(2, userName);
        stmt.executeUpdate();
    }

    public static void softDeleteUser(Connection conn, String userName) throws Exception {
        // OrangeHRM soft-deletes rather than hard-deleting — 'deleted' flag matches the
        // real app's behavior
        PreparedStatement stmt = conn.prepareStatement(
                "UPDATE users SET deleted = TRUE WHERE user_name = ?");
        stmt.setString(1, userName);
        stmt.executeUpdate();
    }

    public static void createUser(Connection conn, int id, String userName, int empNumber, int roleId)
            throws Exception {
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO users (id, user_name, deleted, status, emp_number, user_role_id) " +
                        "VALUES (?, ?, FALSE, TRUE, ?, ?)");
        stmt.setInt(1, id);
        stmt.setString(2, userName);
        stmt.setInt(3, empNumber);
        stmt.setInt(4, roleId);
        stmt.executeUpdate();
    }
}