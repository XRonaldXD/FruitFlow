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
public class BorrowingDB {
    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public BorrowingDB(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    // Get database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }
    
    public boolean createBorrowRequest(int fromShopId, int toShopId, int fruitId, int quantity, String borrowDate) {
    String sql = "INSERT INTO borrowing (from_shop_id, to_shop_id, fruit_id, quantity, borrow_date, status) " +
                 "VALUES (?, ?, ?, ?, ?, 'Pending')";

    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, fromShopId); // Shop lending the fruit
        stmt.setInt(2, toShopId);   // Shop borrowing the fruit
        stmt.setInt(3, fruitId);    // Fruit being borrowed
        stmt.setInt(4, quantity);   // Quantity of fruit
        stmt.setString(5, borrowDate); // Date of borrowing

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0; // Return true if the borrowing request was successfully created
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false; // Return false if an error occurred
}
}
