/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.ConsumptionReport;

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

    // Fetch consumption records by shop, city, or country under a specific season
    public List<ConsumptionReport> getConsumptionRecords(String filterType, int filterId, String season) {
        List<ConsumptionReport> report = new ArrayList<>();
        String sql = "";

        if (filterType.equals("shop")) {
            sql = "SELECT f.fruit_name, SUM(c.quantity) AS total_quantity "
                    + "FROM consumption c "
                    + "JOIN fruits f ON c.fruit_id = f.fruit_id "
                    + "WHERE c.shop_id = ? AND c.season = ? "
                    + "GROUP BY f.fruit_name";
        } else if (filterType.equals("city")) {
            sql = "SELECT f.fruit_name, SUM(c.quantity) AS total_quantity "
                    + "FROM consumption c "
                    + "JOIN fruits f ON c.fruit_id = f.fruit_id "
                    + "JOIN shops s ON c.shop_id = s.shop_id "
                    + "WHERE s.city_id = ? AND c.season = ? "
                    + "GROUP BY f.fruit_name";
        } else if (filterType.equals("country")) {
            sql = "SELECT f.fruit_name, SUM(c.quantity) AS total_quantity "
                    + "FROM consumption c "
                    + "JOIN fruits f ON c.fruit_id = f.fruit_id "
                    + "JOIN shops s ON c.shop_id = s.shop_id "
                    + "JOIN cities ci ON s.city_id = ci.city_id "
                    + "WHERE ci.country_id = ? AND c.season = ? "
                    + "GROUP BY f.fruit_name";
        }

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, filterId);
            stmt.setString(2, season);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String fruitName = rs.getString("fruit_name");
                int totalQuantity = rs.getInt("total_quantity");

                report.add(new ConsumptionReport(fruitName, totalQuantity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return report;
    }

    // Fetch all consumption records (optional method for general reporting)
    public List<ConsumptionReport> getAllConsumptionRecords() {
        List<ConsumptionReport> report = new ArrayList<>();
        String sql = "SELECT f.fruit_name, SUM(c.quantity) AS total_quantity, c.season "
                + "FROM consumption c "
                + "JOIN fruits f ON c.fruit_id = f.fruit_id "
                + "GROUP BY f.fruit_name, c.season";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String fruitName = rs.getString("fruit_name");
                int totalQuantity = rs.getInt("total_quantity");
                String season = rs.getString("season");

                report.add(new ConsumptionReport(fruitName, totalQuantity, season));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return report;
    }

    public boolean addConsumption(int shopId, int fruitId, int quantity, String consumptionDate, String season) {
        String sql = "INSERT INTO consumption (shop_id, fruit_id, quantity, consumption_date, season) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, shopId);
            stmt.setInt(2, fruitId);
            stmt.setInt(3, quantity);
            stmt.setString(4, consumptionDate);
            stmt.setString(5, season);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
}
