/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import bean.Warehouse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Warehouse> getAllWarehouses() {
        List<Warehouse> warehouses = new ArrayList<>();
        String sql = "SELECT * FROM warehouses";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Warehouse warehouse = new Warehouse();
                warehouse.setWarehouseId(rs.getInt("warehouse_id"));
                warehouse.setWarehouseName(rs.getString("warehouse_name"));
                warehouse.setCountryId(rs.getInt("country_id"));
                warehouses.add(warehouse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return warehouses;
    }

    public String getWarehouseNameById(int warehouse_id) {
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
