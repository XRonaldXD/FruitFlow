/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Fruit;
import models.ShopStock;

public class FruitDB {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public FruitDB(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    // Get database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public List<Fruit> getAllFruits() {
        List<Fruit> fruits = new ArrayList<>();
        String sql = "SELECT fruit_id, fruit_name FROM fruits";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int fruitId = rs.getInt("fruit_id");
                String fruitName = rs.getString("fruit_name");
                fruits.add(new Fruit(fruitId, fruitName, 0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fruits;
    }

    // Get stock levels for a specific shop
    public List<Fruit> getStockByShop(int shopId) {
        List<Fruit> fruits = new ArrayList<>();
        String sql = "SELECT f.fruit_id, f.fruit_name, s.stock_level "
                + "FROM stock s "
                + "JOIN fruits f ON s.fruit_id = f.fruit_id "
                + "WHERE s.shop_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, shopId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int fruitId = rs.getInt("fruit_id");
                String fruitName = rs.getString("fruit_name");
                int stockLevel = rs.getInt("stock_level");
                fruits.add(new Fruit(fruitId, fruitName, stockLevel));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fruits;
    }

    // Get stock levels for other shops in the same city
    public List<ShopStock> getStockByCity(int cityId, int currentShopId) {
        List<ShopStock> shopStocks = new ArrayList<>();
        String sql = "SELECT s.shop_name, fr.fruit_name, st.stock_level "
                + "FROM stock st "
                + "JOIN shops s ON st.shop_id = s.shop_id "
                + "JOIN fruits fr ON st.fruit_id = fr.fruit_id "
                + "WHERE s.city_id = ? AND st.shop_id != ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set parameters for cityId and currentShopId
            stmt.setInt(1, cityId);
            stmt.setInt(2, currentShopId);

            ResultSet rs = stmt.executeQuery();

            // Iterate through the result set and populate the list
            while (rs.next()) {
                String shopName = rs.getString("shop_name");
                String fruitName = rs.getString("fruit_name");
                int stockLevel = rs.getInt("stock_level");
                shopStocks.add(new ShopStock(shopName, fruitName, stockLevel));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shopStocks;
    }

    public boolean insertOrUpdateStockLevel(int fruitId, int shopId, int stockLevel) {
        String checkSql = "SELECT COUNT(*) FROM stock WHERE fruit_id = ? AND shop_id = ?";
        String insertSql = "INSERT INTO stock (fruit_id, shop_id, stock_level) VALUES (?, ?, ?)";
        String updateSql = "UPDATE stock SET stock_level = ? WHERE fruit_id = ? AND shop_id = ?";

        try (Connection conn = getConnection(); PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            // Check if the stock entry exists
            checkStmt.setInt(1, fruitId);
            checkStmt.setInt(2, shopId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                // Stock entry exists, perform an update
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setInt(1, stockLevel);
                    updateStmt.setInt(2, fruitId);
                    updateStmt.setInt(3, shopId);
                    int rowsAffected = updateStmt.executeUpdate();
                    return rowsAffected > 0; // Return true if the update was successful
                }
            } else {
                // Stock entry does not exist, perform an insert
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setInt(1, fruitId);
                    insertStmt.setInt(2, shopId);
                    insertStmt.setInt(3, stockLevel);
                    int rowsAffected = insertStmt.executeUpdate();
                    return rowsAffected > 0; // Return true if the insert was successful
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Return false if an error occurred
    }

    public boolean insertOrUpdateStockLevelByWarehouse(int fruitId, int warehouseId, int stockLevel) {
        String checkSql = "SELECT COUNT(*) FROM stock WHERE fruit_id = ? AND warehouse_id = ?";
        String insertSql = "INSERT INTO stock (fruit_id, warehouse_id, stock_level) VALUES (?, ?, ?)";
        String updateSql = "UPDATE stock SET stock_level = ? WHERE fruit_id = ? AND warehouse_id = ?";

        try (Connection conn = getConnection(); PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            // Check if the stock entry exists
            checkStmt.setInt(1, fruitId);
            checkStmt.setInt(2, warehouseId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                // Stock entry exists, perform an update
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setInt(1, stockLevel);
                    updateStmt.setInt(2, fruitId);
                    updateStmt.setInt(3, warehouseId);
                    int rowsAffected = updateStmt.executeUpdate();
                    return rowsAffected > 0; // Return true if the update was successful
                }
            } else {
                // Stock entry does not exist, perform an insert
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setInt(1, fruitId);
                    insertStmt.setInt(2, warehouseId);
                    insertStmt.setInt(3, stockLevel);
                    int rowsAffected = insertStmt.executeUpdate();
                    return rowsAffected > 0; // Return true if the insert was successful
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Return false if an error occurred
    }

    public String getFruitNameById(int fruitId) {
        String fruitName = null;
        String sql = "SELECT fruit_name FROM fruits WHERE fruit_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, fruitId); // Set the fruitId parameter
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                fruitName = rs.getString("fruit_name"); // Get the fruit name
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fruitName; // Return the fruit name or null if not found
    }

    public List<Fruit> getFruitsWithStockLevels(int shopId) {
        List<Fruit> fruits = new ArrayList<>();
        String sql = "SELECT f.fruit_id, f.fruit_name, "
                + "COALESCE(s.stock_level, 0) AS stock_level "
                + "FROM fruits f "
                + "LEFT JOIN stock s ON f.fruit_id = s.fruit_id AND s.shop_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, shopId); // Set the shopId parameter
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int fruitId = rs.getInt("fruit_id");
                String fruitName = rs.getString("fruit_name");
                int stockLevel = rs.getInt("stock_level");

                fruits.add(new Fruit(fruitId, fruitName, stockLevel));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fruits;
    }

    public List<Fruit> getFruitsWithStockLevelsByWarehouse(int warehouseId) {
        List<Fruit> fruits = new ArrayList<>();
        String sql = "SELECT f.fruit_id, f.fruit_name, "
                + "COALESCE(s.stock_level, 0) AS stock_level "
                + "FROM fruits f "
                + "LEFT JOIN stock s ON f.fruit_id = s.fruit_id AND s.warehouse_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, warehouseId); // Set the warehouseId parameter
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int fruitId = rs.getInt("fruit_id");
                String fruitName = rs.getString("fruit_name");
                int stockLevel = rs.getInt("stock_level");

                fruits.add(new Fruit(fruitId, fruitName, stockLevel));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fruits;
    }

    public int getCityId(int shopId) {
        int cityId = -1; // Default value if no city is found
        String sql = "SELECT city_id FROM shops WHERE shop_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, shopId); // Set the shopId parameter
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cityId = rs.getInt("city_id"); // Retrieve the city_id
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cityId; // Return the city_id or -1 if not found
    }
}
