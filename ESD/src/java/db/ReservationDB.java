/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import bean.Reservation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.CountryReservation;
import models.ReserveReport;

/**
 *
 * @author user
 */
public class ReservationDB {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public ReservationDB(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    // Get database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public boolean createReservation(Integer shopId, int fruitId, int quantity, String reservationDate) {
        String sql = "INSERT INTO reservations (shop_id, fruit_id, quantity, reservation_date, status) "
                + "VALUES (?, ?, ?, ?, 'Pending')";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, shopId);
            stmt.setInt(2, fruitId);
            stmt.setInt(3, quantity);
            stmt.setString(4, reservationDate);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Return true if the reservation was successfully created
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Return false if an error occurred
    }

    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT "
                + "r.reservation_id, "
                + "r.fruit_id, "
                + "r.shop_id, "
                + "r.warehouse_id, "
                + "r.quantity, "
                + "r.reservation_date, "
                + "r.status "
                + "FROM reservations r";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int reservationId = rs.getInt("reservation_id");
                int fruitId = rs.getInt("fruit_id");
                Integer shopId = rs.getObject("shop_id") != null ? rs.getInt("shop_id") : null; // Handle nullable shopId
                Integer warehouseId = rs.getObject("warehouse_id") != null ? rs.getInt("warehouse_id") : null; // Handle nullable warehouseId
                int quantity = rs.getInt("quantity");
                String reservationDate = rs.getString("reservation_date");
                String status = rs.getString("status");

                reservations.add(new Reservation(reservationId, fruitId, shopId, warehouseId, quantity, reservationDate, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

    // Get all reservations for a specific shop
    public List<Reservation> getReservationsByShop(Integer shopId) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT reservation_id, shop_id, fruit_id, quantity, reservation_date, status "
                + "FROM reservations WHERE shop_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, shopId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int reservationId = rs.getInt("reservation_id");
                int fruitId = rs.getInt("fruit_id");
                int quantity = rs.getInt("quantity");
                String reservationDate = rs.getString("reservation_date");
                String status = rs.getString("status");

                reservations.add(new Reservation(reservationId, fruitId, shopId, null, quantity, reservationDate, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

    // Get all reservations for a specific shop
    public List<Reservation> getReservationsByWarehouse(Integer warehouseId) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT reservation_id, warehouse_id, fruit_id, quantity, reservation_date, status "
                + "FROM reservations WHERE warehouse_id != ? AND status = 'Pending'";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, warehouseId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int reservationId = rs.getInt("reservation_id");
                int fruitId = rs.getInt("fruit_id");
                Integer FromWarehouseId = rs.getInt("warehouse_id");
                int quantity = rs.getInt("quantity");
                String reservationDate = rs.getString("reservation_date");
                String status = rs.getString("status");

                reservations.add(new Reservation(reservationId, fruitId, null, FromWarehouseId, quantity, reservationDate, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

    // Update the status of a reservation
    public boolean updateReservationStatus(int reservationId, String status) {
        String sql = "UPDATE reservations SET status = ? WHERE reservation_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, reservationId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Return true if the status was successfully updated
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Return false if an error occurred
    }

    public boolean updateReservationStatusByCountryAndFruit(int countryId, int fruitId, String status) {
        String sql = "UPDATE reservations r "
                + "JOIN shops s ON r.shop_id = s.shop_id "
                + "JOIN cities ci ON s.city_id = ci.city_id "
                + "JOIN countries c ON ci.country_id = c.country_id "
                + "SET r.status = ? "
                + "WHERE c.country_id = ? AND r.fruit_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, countryId);
            stmt.setInt(3, fruitId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Return true if the update was successful
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Return false if an error occurred
    }

    // Delete a reservation
    public boolean deleteReservation(int reservationId) {
        String sql = "DELETE FROM reservations WHERE reservation_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reservationId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Return true if the reservation was successfully deleted
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Return false if an error occurred
    }

    public List<Reservation> getPendingReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT reservation_id, shop_id, fruit_id, warehouse_id, quantity, reservation_date, status "
                + "FROM reservations "
                + "WHERE status = 'Pending' AND warehouse_id IS NULL";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int reservationId = rs.getInt("reservation_id");
                Integer shopId = rs.getObject("shop_id") != null ? rs.getInt("shop_id") : null; // Handle nullable shop_id
                int fruitId = rs.getInt("fruit_id");
                Integer warehouseId = rs.getObject("warehouse_id") != null ? rs.getInt("warehouse_id") : null; // Handle nullable warehouse_id
                int quantity = rs.getInt("quantity");
                String reservationDate = rs.getString("reservation_date");
                String status = rs.getString("status");

                reservations.add(new Reservation(reservationId, fruitId, shopId, warehouseId, quantity, reservationDate, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

    public List<CountryReservation> getTotalReservationsByCountryAndFruit() {
        List<CountryReservation> countryReservations = new ArrayList<>();
        String sql = "SELECT c.country_id, c.country_name, f.fruit_id, f.fruit_name, SUM(r.quantity) AS total_quantity "
                + "FROM reservations r "
                + "JOIN shops s ON r.shop_id = s.shop_id "
                + "JOIN cities ci ON s.city_id = ci.city_id "
                + "JOIN countries c ON ci.country_id = c.country_id "
                + "JOIN fruits f ON r.fruit_id = f.fruit_id "
                + "WHERE r.status = 'Pending' AND r.reservation_date >= CURDATE() "
                + "GROUP BY c.country_id, c.country_name, f.fruit_id, f.fruit_name";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int countryId = rs.getInt("country_id");
                String countryName = rs.getString("country_name");
                int fruitId = rs.getInt("fruit_id");
                String fruitName = rs.getString("fruit_name");
                int totalQuantity = rs.getInt("total_quantity");

                countryReservations.add(new CountryReservation(countryId, countryName, fruitId, fruitName, totalQuantity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countryReservations;
    }

    public List<ReserveReport> getReserveNeeds(String filterType, int filterId) {
        List<ReserveReport> report = new ArrayList<>();
        String sql = "";

        if (filterType.equals("shop")) {
            sql = "SELECT f.fruit_name, SUM(r.quantity) AS total_quantity "
                    + "FROM reservations r "
                    + "JOIN fruits f ON r.fruit_id = f.fruit_id "
                    + "WHERE r.shop_id = ? AND r.status = 'Pending' "
                    + "GROUP BY f.fruit_name";
        } else if (filterType.equals("city")) {
            sql = "SELECT f.fruit_name, SUM(r.quantity) AS total_quantity "
                    + "FROM reservations r "
                    + "JOIN fruits f ON r.fruit_id = f.fruit_id "
                    + "WHERE r.city_id = ? AND r.status = 'Pending' "
                    + "GROUP BY f.fruit_name";
        } else if (filterType.equals("country")) {
            sql = "SELECT f.fruit_name, SUM(r.quantity) AS total_quantity "
                    + "FROM reservations r "
                    + "JOIN fruits f ON r.fruit_id = f.fruit_id "
                    + "WHERE r.country_id = ? AND r.status = 'Pending' "
                    + "GROUP BY f.fruit_name";
        }

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, filterId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String fruitName = rs.getString("fruit_name");
                int totalQuantity = rs.getInt("total_quantity");

                report.add(new ReserveReport(fruitName, totalQuantity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return report;
    }

    public List<ReserveReport> getReserveNeedsForAllShops(String filterType) {
        List<ReserveReport> report = new ArrayList<>();
        String sql = "";

        if (filterType.equals("city")) {
            sql = "SELECT f.fruit_name, SUM(r.quantity) AS total_quantity "
                    + "FROM reservations r "
                    + "JOIN fruits f ON r.fruit_id = f.fruit_id "
                    + "JOIN shops s ON r.shop_id = s.shop_id "
                    + "WHERE r.status = 'Pending' "
                    + "GROUP BY f.fruit_name";
        } else if (filterType.equals("country")) {
            sql = "SELECT f.fruit_name, SUM(r.quantity) AS total_quantity "
                    + "FROM reservations r "
                    + "JOIN fruits f ON r.fruit_id = f.fruit_id "
                    + "JOIN shops s ON r.shop_id = s.shop_id "
                    + "JOIN cities c ON s.city_id = c.city_id "
                    + "WHERE r.status = 'Pending' "
                    + "GROUP BY f.fruit_name";
        }

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String fruitName = rs.getString("fruit_name");
                int totalQuantity = rs.getInt("total_quantity");

                report.add(new ReserveReport(fruitName, totalQuantity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return report;
    }
}
