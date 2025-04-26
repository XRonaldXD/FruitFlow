/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import bean.Reservation;
import bean.User;
import db.ReservationDB;
import db.UserDB;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import bean.Fruit;
import bean.Warehouse;
import db.FruitManagementDB;
import db.ShopDB;
import db.WarehouseDB;
import models.Shop;

/**
 *
 * @author user
 */
@WebServlet(name = "AdminHandler", urlPatterns = {"/AdminHandler"})
public class AdminHandler extends HttpServlet {

    private ReservationDB reservationDB;
    private UserDB userDB;
    private FruitManagementDB fruitManagementDB;
    private ShopDB shopDB;
    private WarehouseDB warehouseDB;

    public void init() {
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");

        reservationDB = new ReservationDB(dbUrl, dbUser, dbPassword);
        userDB = new UserDB(dbUrl, dbUser, dbPassword);
        fruitManagementDB = new FruitManagementDB(dbUrl, dbUser, dbPassword);
        shopDB = new ShopDB(dbUrl, dbUser, dbPassword);
        warehouseDB = new WarehouseDB(dbUrl, dbUser, dbPassword);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equals("viewReserveNeeds")) {

            List<Reservation> reservations = reservationDB.getAllReservations();

            request.setAttribute("reservations", reservations);

            // Forward to the JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("./seniorManagement/reserveNeeds.jsp");
            dispatcher.forward(request, response);
        } else if (action.equals("viewUserManagement")) {

            List<User> users = (List<User>) userDB.getAllUsers();
            List<Shop> shops = shopDB.getAllShops();
            List<Warehouse> warehouses = warehouseDB.getAllWarehouses();
            request.setAttribute("users", users);
            request.setAttribute("shops", shops);
            request.setAttribute("warehouses", warehouses);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./seniorManagement/userManagement.jsp");
            dispatcher.forward(request, response);

        } else if (action.equals("addUser")) {
            // Retrieve parameters from the request
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            String shopId = request.getParameter("shopId");
            String warehouseId = request.getParameter("warehouseId");

            // Validate inputs
            if (username == null || email == null || password == null || role == null || username.isEmpty() || email.isEmpty() || password.isEmpty() || role.isEmpty()) {
                request.setAttribute("message", "All fields are required.");
                request.setAttribute("alertType", "danger");
            } else {
                // Determine the ID to assign based on the role
                Integer id = null;
                if (role.equals("BakeryShopStaff")) {
                    id = (shopId != null && !shopId.isEmpty()) ? Integer.parseInt(shopId) : null;
                } else if (role.equals("WarehouseStaff")) {
                    id = (warehouseId != null && !warehouseId.isEmpty()) ? Integer.parseInt(warehouseId) : null;
                }

                // Call the createUser method in UserDB
                int userId = userDB.createUser(username, email, password, role, id);

                // Set success or failure message
                if (userId != -1) {
                    request.setAttribute("message", "User added successfully. User ID: " + userId);
                    request.setAttribute("alertType", "success");
                } else {
                    request.setAttribute("message", "Failed to add user.");
                    request.setAttribute("alertType", "danger");
                }
            }

            // Fetch the updated list of users
            List<User> users = userDB.getAllUsers();
            request.setAttribute("users", users);

            // Forward to the user management JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("./seniorManagement/userManagement.jsp");
            dispatcher.forward(request, response);
        } else if (action.equals("edit_userManagement")) {
            // Edit User Logic
            int userId = Integer.parseInt(request.getParameter("userId"));
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password"); // Retrieve the password
            String role = request.getParameter("role");
            String shopId = request.getParameter("shopId");
            String warehouseId = request.getParameter("warehouseId");

            // Determine the ID to assign based on the role
            Integer id = null;
            if (role.equals("BakeryShopStaff")) {
                id = (shopId != null && !shopId.isEmpty()) ? Integer.parseInt(shopId) : null;
            } else if (role.equals("WarehouseStaff")) {
                id = (warehouseId != null && !warehouseId.isEmpty()) ? Integer.parseInt(warehouseId) : null;
            }

            // Call the updateUser method in UserDB
            boolean success = userDB.updateUser(userId, username, email, password, role, id);

            // Set success or failure message
            if (success) {
                request.setAttribute("message", "User updated successfully.");
                request.setAttribute("alertType", "success");
            } else {
                request.setAttribute("message", "Failed to update user.");
                request.setAttribute("alertType", "danger");
            }

            // Fetch the updated list of users
            List<User> users = userDB.getAllUsers();
            request.setAttribute("users", users);

            // Forward to the user management JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("./seniorManagement/userManagement.jsp");
            dispatcher.forward(request, response);
        } else if (action.equals("delete_userManagement")) {

            // Get the user ID from the request
            int userId = Integer.parseInt(request.getParameter("userId"));

            // Call the DAO to delete the user
            boolean success = userDB.deleteUser(userId);

            // Fetch the updated list of users
            List<User> users = userDB.getAllUsers();
            request.setAttribute("users", users);

            // Set a success or failure message
            if (success) {
                request.setAttribute("message", "User deleted successfully.");
                request.setAttribute("alertType", "success");
            } else {
                request.setAttribute("message", "Failed to delete user.");
                request.setAttribute("alertType", "warning");
            }

            // Forward to the user management JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("./seniorManagement/userManagement.jsp");
            dispatcher.forward(request, response);
        } else if (action.equals("view_fruitManagement")) {
            List<Fruit> fruits = (List<Fruit>) fruitManagementDB.getAllFruits();
            request.setAttribute("fruits", fruits);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./seniorManagement/fruitManagement.jsp");
            dispatcher.forward(request, response);
        } else if (action.equals("add_fruit")) {
            String fruitName = request.getParameter("fruitName");
            String sourceLocation = request.getParameter("sourceLocation");
            boolean success = fruitManagementDB.addFruit(fruitName, sourceLocation);
            if (success) {
                request.setAttribute("message", "Fruit added successfully.");
                request.setAttribute("alertType", "success");
            } else {
                request.setAttribute("message", "Failed to add fruit.");
                request.setAttribute("alertType", "warning");
            }
            List<Fruit> fruits = (List<Fruit>) fruitManagementDB.getAllFruits();
            request.setAttribute("fruits", fruits);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./seniorManagement/fruitManagement.jsp");
            dispatcher.forward(request, response);
        } else if (action.equals("update_fruit")) {
            int fruitId = Integer.parseInt(request.getParameter("fruitId"));
            String fruitName = request.getParameter("fruitName");
            String sourceLocation = request.getParameter("sourceLocation");

            boolean success = fruitManagementDB.updateFruit(fruitId, fruitName, sourceLocation);
            if (success) {
                request.setAttribute("message", "Fruit updated successfully.");
                request.setAttribute("alertType", "success");
            } else {
                request.setAttribute("message", "Failed to update fruit.");
                request.setAttribute("alertType", "warning");
            }
            List<Fruit> fruits = (List<Fruit>) fruitManagementDB.getAllFruits();
            request.setAttribute("fruits", fruits);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./seniorManagement/fruitManagement.jsp");
            dispatcher.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
