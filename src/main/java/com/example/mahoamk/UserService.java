package com.example.mahoamk;

import java.sql.*;

public class UserService {
    private final String url = "jdbc:mysql://localhost:3306/userdb";
    private final String user = "hieu";
    private final String password = "123";

    public boolean register(String username, String password) {
        try (Connection conn = DriverManager.getConnection(url, user, this.password);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean authenticate(String username, String password) {
        try (Connection conn = DriverManager.getConnection(url, user, this.password);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
