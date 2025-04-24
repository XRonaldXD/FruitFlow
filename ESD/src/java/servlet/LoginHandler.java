/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import bean.User;
import db.FruitDB;
import java.sql.*;
import db.UserDB;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import models.Fruit;
import models.ShopStock;

/**
 *
 * @author user
 */
@WebServlet(name = "LoginHandler", urlPatterns = {"/LoginHandler"})
public class LoginHandler extends HttpServlet {

    private UserDB userDB;
    private FruitDB fruitDB;

    public void init() {
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");

        userDB = new UserDB(dbUrl, dbUser, dbPassword);
        fruitDB = new FruitDB(dbUrl, dbUser, dbPassword);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean isValid = userDB.isValidUser(username, password);
        try (PrintWriter out = response.getWriter()) {
            if (isValid) {    //login
                User user = userDB.getUser(username, password);
                String role = userDB.getRole(username, password);

                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);

                doLogin(request, response, user);
            } else {
                request.setAttribute("errorMessage", "Invalid username or password. Please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void doLogin(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {

        String targetURL = "login.jsp"; // Default to login page
        String role = user.getRole();

        if (role.equals("BakeryShopStaff")) {
            // Bakery Shop Staff
            
            int shopId = (user.getShopId()); // Current shop ID
            int cityId = fruitDB.getCityId(shopId);

            // Fetch stock data
            List<Fruit> shopStock = fruitDB.getStockByShop(shopId); // Stock for the current shop
            List<ShopStock> cityStock = fruitDB.getStockByCity(cityId, shopId); // Stock for other shops in the same city

            // Set attributes for the JSP
            request.setAttribute("shopStock", shopStock);
            request.setAttribute("cityStock", cityStock);

            // Forward to the JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("/bakeryShopStaff/dashboard.jsp");
            dispatcher.forward(request, response);

            response.sendRedirect("./bakeryShopStaff/dashboard.jsp");
        } else if (role.equals("WarehouseStaff")) {
            // Warehouse Staff
            response.sendRedirect("./warehouseStaff/dashboard.jsp");
        } else if (role.equals("SeniorManagement")) {
            // Senior Management
            response.sendRedirect("./seniorManagement/dashboard.jsp");
        } else {
            targetURL = "/login.jsp"; // Default to login page
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher(targetURL);
        rd.forward(request, response);
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
