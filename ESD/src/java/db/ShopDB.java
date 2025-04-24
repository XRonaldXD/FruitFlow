package db;

import models.Shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShopDB {
    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public ShopDB(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    // Get database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    // Fetch shop details by shopId
    public Shop getShopById(int shopId) {
        Shop shop = null;
        String sql = "SELECT shop_id, shop_name, city_id FROM shops WHERE shop_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, shopId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("shop_id");
                String name = rs.getString("shop_name");
                int cityId = rs.getInt("city_id");
                shop = new Shop(id, name, cityId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shop;
    }
    
    // Fetch all shops in a specific city
    public List<Shop> getShopsByCity(int cityId) {
        List<Shop> shops = new ArrayList<>();
        String sql = "SELECT shop_id, shop_name, city_id FROM shops WHERE city_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cityId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("shop_id");
                String name = rs.getString("shop_name");
                int city = rs.getInt("city_id");
                shops.add(new Shop(id, name, city));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shops;
    }

    // Fetch all shops
    public List<Shop> getAllShops() {
        List<Shop> shops = new ArrayList<>();
        String sql = "SELECT shop_id, shop_name, city_id FROM shops";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("shop_id");
                String name = rs.getString("shop_name");
                int cityId = rs.getInt("city_id");
                shops.add(new Shop(id, name, cityId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shops;
    }
}