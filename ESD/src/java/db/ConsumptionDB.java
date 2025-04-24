/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.ConsumptionRecord;

public class ConsumptionDB {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    // Constructor
    public ConsumptionDB(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    // Get a database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public List<ConsumptionRecord> getAllConsumptionRecords() {
        List<ConsumptionRecord> records = new ArrayList<>();
        String sql = "SELECT "
                + "c.consumption_id, "
                + "s.shop_name, "
                + "ci.city_name, "
                + "co.country_name, "
                + "f.fruit_name, "
                + "c.quantity, "
                + "c.consumption_date, "
                + "c.season "
                + "FROM consumption c "
                + "JOIN shops s ON c.shop_id = s.shop_id "
                + "JOIN cities ci ON s.city_id = ci.city_id "
                + "JOIN countries co ON ci.country_id = co.country_id "
                + "JOIN fruits f ON c.fruit_id = f.fruit_id";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int consumptionId = rs.getInt("consumption_id");
                String shopName = rs.getString("shop_name");
                String cityName = rs.getString("city_name");
                String countryName = rs.getString("country_name");
                String fruitName = rs.getString("fruit_name");
                int quantity = rs.getInt("quantity");
                String consumptionDate = rs.getString("consumption_date");
                String season = rs.getString("season");

                records.add(new ConsumptionRecord(consumptionId, shopName, cityName, countryName, fruitName, quantity, consumptionDate, season));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
    }

    public boolean addConsumption(int shopId, int fruitId, int quantity, String consumptionDate, String season) {
        String sql = "INSERT INTO consumption (shop_id, fruit_id, quantity, consumption_date, season) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set parameters for the query
            stmt.setInt(1, shopId);
            stmt.setInt(2, fruitId);
            stmt.setInt(3, quantity);
            stmt.setString(4, consumptionDate);
            stmt.setString(5, season);

            // Execute the update
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Return true if at least one row was inserted
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an exception occurs
        }
    }

}
