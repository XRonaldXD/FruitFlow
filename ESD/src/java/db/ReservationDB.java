/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.*;

/**
 *
 * @author user
 */
public class ReservationDB {
    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public ReservationDB(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    // Get database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }
    
    public boolean createReservation(int shopId, int fruitId, int quantity, String reservationDate) {
    String sql = "INSERT INTO reservations (shop_id, fruit_id, quantity, reservation_date, status) " +
                 "VALUES (?, ?, ?, ?, 'Pending')";

    try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, shopId);
        stmt.setInt(2, fruitId);
        stmt.setInt(3, quantity);
        stmt.setString(4, reservationDate);

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0; // Return true if the reservation was successfully created
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false; // Return false if an error occurred
}
}
