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
