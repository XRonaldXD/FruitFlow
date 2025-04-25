/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import bean.Fruit;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class FruitManagementDB {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public FruitManagementDB(String dbUrl, String dbUser, String dbPassword) {
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
        String sql = "SELECT * FROM fruits";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int fruitId = rs.getInt("fruit_id");
                String fruitName = rs.getString("fruit_name");
                String sourceLocation = rs.getString("source_location");
                fruits.add(new Fruit(fruitId, fruitName, sourceLocation));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fruits;
    }

    // Add a new fruit
    public boolean addFruit(String fruitName, String sourceLocation) {
        String sql = "INSERT INTO fruits (fruit_name, source_location) VALUES (?,?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fruitName);
            stmt.setString(2, sourceLocation);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update a fruit
    public boolean updateFruit(int fruitId, String fruitName, String sourceLocation) {
        String sql = "UPDATE fruits SET fruit_name = ?, source_location = ? WHERE fruit_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fruitName);
            stmt.setString(2, sourceLocation);
            stmt.setInt(3, fruitId);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a fruit
    public boolean deleteFruit(int fruitId) {
        String sql = "DELETE FROM fruits WHERE fruit_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, fruitId);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
