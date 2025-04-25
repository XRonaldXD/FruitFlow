/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import bean.User;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDB {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public UserDB(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public Connection getConnection() throws SQLException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public boolean isValidUser(String username, String password) {
        Connection conn = null;
        PreparedStatement pStmt = null;

        boolean result = false;

        try {
            conn = getConnection();
            String preQueryStatement = "SELECT * FROM users WHERE username = ? AND password = ?;";

            pStmt = conn.prepareStatement(preQueryStatement);

            pStmt.setString(1, username);
            pStmt.setString(2, password);

            ResultSet rs = null;

            rs = pStmt.executeQuery();

            if (rs.next()) {
                result = true;
            }

            pStmt.close();
            conn.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public String getRole(String username, String password) {
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;

        String result = "-1"; // Default value if no user is found

        try {
            conn = getConnection(); // Ensure this method is implemented to return a valid DB connection
            String preQueryStatement = "SELECT role FROM users WHERE username = ? AND password = ?";

            pStmt = conn.prepareStatement(preQueryStatement);
            pStmt.setString(1, username);
            pStmt.setString(2, password);

            rs = pStmt.executeQuery();

            if (rs.next()) {
                result = rs.getString("role"); // Retrieve the 'role' column value
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Log the exception for debugging
        } catch (IOException ex) {
            ex.printStackTrace(); // Handle IO exceptions
        } finally {
            // Ensure resources are closed properly
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pStmt != null) {
                    pStmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return result;
    }

    public User getUser(String username, String password) {
        User user = null;
        String sql = "SELECT * "
                + "FROM users WHERE username = ? AND password = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password); // Ensure passwords are hashed and compared securely
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setShopId(rs.getObject("shop_id") != null ? rs.getInt("shop_id") : null);
                user.setWarehouseId(rs.getObject("warehouse_id") != null ? rs.getInt("warehouse_id") : null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace(); // Handle IO exceptions
        }

        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String role = rs.getString("role");
                Integer shop_id = rs.getInt("shop_id");
                Integer warehouse_id = rs.getInt("warehouse_id");

                users.add(new User(userId, username, email, password, role, shop_id, warehouse_id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return users;
    }

    public int createUser(String username, String email, String password, String role, Integer ID) {
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet generatedKeys = null; // To hold the generated keys
        String sql = "";
        int userID = -1; // Default to -1 to indicate failure

        try {
            conn = getConnection();
            if (role.equals("BakeryShopStaff")) {
                sql = "INSERT INTO `users` (`username`, `email`, `password`, `role`, `shop_id`) VALUES (?, ?, ?, ?, ?);";
            } else if (role.equals("WarehouseStaff")) {
                sql = "INSERT INTO `users` (`username`, `email`, `password`, `role`, `warehouse_id`) VALUES (?, ?, ?, ?, ?);";
            }

            pStmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); // Enable key retrieval

            pStmt.setString(1, username);
            pStmt.setString(2, email);
            pStmt.setString(3, password);
            pStmt.setString(4, role);
            pStmt.setInt(5, ID);

            int rowCount = pStmt.executeUpdate();
            System.out.println("Rows affected: " + rowCount);

            if (rowCount >= 1) {
                // Retrieve the generated userID
                generatedKeys = pStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    userID = generatedKeys.getInt(1); // Get the generated key
                    System.out.println("Generated UserID: " + userID);
                }
            }
        } catch (SQLException ex) {
            System.err.println("SQL Exception occurred:");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.err.println("IO Exception occurred:");
            ex.printStackTrace();
        } finally {
            try {
                if (generatedKeys != null) {
                    generatedKeys.close();
                }
                if (pStmt != null) {
                    pStmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return userID; // Return the userID or -1 if creation failed
    }

    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE user_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException ex){
            ex.printStackTrace();
            return false;
        }
    }
}
