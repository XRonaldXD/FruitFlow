/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class WarehouseDB {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public WarehouseDB(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }
    
    // Get database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }
    
    public String getWarehouseNameById(int warehouse_id){
        String warehouse_name = null;
        String sql = "SELECT warehouse_name FROM warehouses WHERE warehouse_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, warehouse_id); // Set the fruitId parameter
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                warehouse_name = rs.getString("warehouse_name"); // Get the fruit name
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return warehouse_name; // Return the fruit name or null if not found
    }
}
