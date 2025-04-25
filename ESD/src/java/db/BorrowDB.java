/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import bean.Borrow;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class BorrowDB {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public BorrowDB(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    // Get database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public boolean createBorrowRequest(int fromShopId, int toShopId, int fruitId, int quantity, String borrowDate) {
        String sql = "INSERT INTO borrowing (from_shop_id, to_shop_id, fruit_id, quantity, borrow_date, status) "
                + "VALUES (?, ?, ?, ?, ?, 'Pending')";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

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

    // Fetch all borrow requests for a specific shop
    public List<Borrow> getBorrowRequests(int fromShopId) {
        List<Borrow> borrows = new ArrayList<>();
        String sql = "SELECT * FROM borrowing WHERE from_shop_id = ? AND status = 'Pending'";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, fromShopId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int borrowId = rs.getInt("borrow_id");
                int toShopId = rs.getInt("to_shop_id");
                int fruitId = rs.getInt("fruit_id");
                int quantity = rs.getInt("quantity");
                String borrowDate = rs.getString("borrow_date");
                String status = rs.getString("status");

                borrows.add(new Borrow(borrowId, fromShopId, toShopId, fruitId, quantity, borrowDate, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return borrows;
    }
    
    public List<Borrow> getBorrowRequestsByToShopId(int toShopId) {
        List<Borrow> borrows = new ArrayList<>();
        String sql = "SELECT * FROM borrowing WHERE to_shop_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, toShopId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int borrowId = rs.getInt("borrow_id");
                int fromShopId = rs.getInt("from_shop_id");
                int fruitId = rs.getInt("fruit_id");
                int quantity = rs.getInt("quantity");
                String borrowDate = rs.getString("borrow_date");
                String status = rs.getString("status");

                borrows.add(new Borrow(borrowId, fromShopId, toShopId, fruitId, quantity, borrowDate, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return borrows;
    }

    // Approve a borrow request
    public boolean approveBorrow(int borrowId) {
        String sql = "UPDATE borrowing SET status = 'Approved' WHERE borrow_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, borrowId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Reject a borrow request
    public boolean rejectBorrow(int borrowId) {
        String sql = "UPDATE borrowing SET status = 'Rejected' WHERE borrow_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, borrowId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
