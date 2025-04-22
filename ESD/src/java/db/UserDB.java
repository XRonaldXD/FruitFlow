/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.io.IOException;
import java.sql.*;

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

    public int createUser(String username, String email, String password, String role, Integer shopID) {
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet generatedKeys = null; // To hold the generated keys
        int userID = -1; // Default to -1 to indicate failure

        try {
            conn = getConnection();
            String sql = "INSERT INTO `users` (`username`, `email`, `password`, `role`, `shop_id`) VALUES (?, ?, ?, ?, ?);";

            pStmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); // Enable key retrieval

            pStmt.setString(1, username);
            pStmt.setString(2, email);
            pStmt.setString(3, password);
            pStmt.setString(4, role);

            // Handle null for shopID
            if (shopID != null) {
                pStmt.setInt(5, shopID);
            } else {
                pStmt.setNull(5, java.sql.Types.INTEGER);
            }

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
}
