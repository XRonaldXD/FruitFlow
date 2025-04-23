/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import models.Delivery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDB {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    // Constructor
    public DeliveryDB(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    // Get a database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    // Create a new delivery
    public boolean createDelivery(int reservationId, int fruitId, int fromWarehouseId, Integer toWarehouseId, Integer toShopId, int quantity, String deliveryDate) {
        String sql = "INSERT INTO deliveries (reservation_id, fruit_id, from_warehouse_id, to_warehouse_id, to_shop_id, quantity, delivery_date) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reservationId);
            stmt.setInt(2, fruitId);
            stmt.setInt(3, fromWarehouseId);
            if (toWarehouseId != null) {
                stmt.setInt(4, toWarehouseId);
            } else {
                stmt.setNull(4, java.sql.Types.INTEGER);
            }
            if (toShopId != null) {
                stmt.setInt(5, toShopId);
            } else {
                stmt.setNull(5, java.sql.Types.INTEGER);
            }
            stmt.setInt(6, quantity);
            stmt.setString(7, deliveryDate);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Return true if the insert was successful
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Return false if an error occurred
    }

    // Get deliveries by status
    public List<Delivery> getDeliveriesByStatus(String status) {
        List<Delivery> deliveries = new ArrayList<>();
        String sql = "SELECT * FROM deliveries WHERE status = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int deliveryId = rs.getInt("delivery_id");
                int reservationId = rs.getInt("reservation_id");
                int fruitId = rs.getInt("fruit_id");
                int fromWarehouseId = rs.getInt("from_warehouse_id");
                Integer toWarehouseId = rs.getObject("to_warehouse_id") != null ? rs.getInt("to_warehouse_id") : null;
                Integer toShopId = rs.getObject("to_shop_id") != null ? rs.getInt("to_shop_id") : null;
                int quantity = rs.getInt("quantity");
                Date deliveryDate = rs.getDate("delivery_date");
                String deliveryStatus = rs.getString("status");

                deliveries.add(new Delivery(deliveryId, reservationId, fruitId, fromWarehouseId, toWarehouseId, toShopId, quantity, deliveryDate.toString(), deliveryStatus));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deliveries;
    }

    // Update delivery status
    public boolean updateDeliveryStatus(int deliveryId, String status) {
        String sql = "UPDATE deliveries SET status = ? WHERE delivery_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, deliveryId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Return true if the update was successful
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Return false if an error occurred
    }

    // Get delivery by ID
    public Delivery getDeliveryById(int deliveryId) {
        String sql = "SELECT * FROM deliveries WHERE delivery_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, deliveryId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int reservationId = rs.getInt("reservation_id");
                int fruitId = rs.getInt("fruit_id");
                int fromWarehouseId = rs.getInt("from_warehouse_id");
                Integer toWarehouseId = rs.getObject("to_warehouse_id") != null ? rs.getInt("to_warehouse_id") : null;
                Integer toShopId = rs.getObject("to_shop_id") != null ? rs.getInt("to_shop_id") : null;
                int quantity = rs.getInt("quantity");
                Date deliveryDate = rs.getDate("delivery_date");
                String status = rs.getString("status");

                return new Delivery(deliveryId, reservationId, fruitId, fromWarehouseId, toWarehouseId, toShopId, quantity, deliveryDate.toString(), status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if no delivery is found
    }
}
