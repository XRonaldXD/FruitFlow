/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.City;

public class CityDB {
    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    // Constructor
    public CityDB(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    // Get a database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    // Fetch all cities
    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT city_id, city_name, country_id FROM cities";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int cityId = rs.getInt("city_id");
                String cityName = rs.getString("city_name");
                int countryId = rs.getInt("country_id");

                cities.add(new City(cityId, cityName, countryId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cities;
    }

    // Fetch cities by country ID
    public List<City> getCitiesByCountry(int countryId) {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT city_id, city_name, country_id FROM cities WHERE country_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, countryId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int cityId = rs.getInt("city_id");
                String cityName = rs.getString("city_name");

                cities.add(new City(cityId, cityName, countryId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cities;
    }

    // Fetch a city by ID
    public City getCityById(int cityId) {
        String sql = "SELECT city_id, city_name, country_id FROM cities WHERE city_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cityId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String cityName = rs.getString("city_name");
                int countryId = rs.getInt("country_id");

                return new City(cityId, cityName, countryId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if no city is found
    }
}
