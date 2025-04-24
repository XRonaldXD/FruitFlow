/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import db.UserDB;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet(name = "RegisterHandler", urlPatterns = {"/RegisterHandler"})
public class RegisterHandler extends HttpServlet {

    private UserDB userDB;

    public void init() {
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");

        userDB = new UserDB(dbUrl, dbUser, dbPassword);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userID = -1;

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        boolean isValid = userDB.isValidUser(username, password);

        PrintWriter out = response.getWriter();

        if (!isValid) { // User does not exist, proceed with registration

            if (role.equals("BakeryShopStaff")) {
                int shopID = Integer.parseInt(request.getParameter("shopID"));
                userID = userDB.createUser(username, email, password, role, shopID); // Register Bakery Shop Staff
            } else if (role.equals("WarehouseStaff")) {
                int warehouseID = Integer.parseInt(request.getParameter("warehouseID"));
                userID = userDB.createUser(username, email, password, role, warehouseID); // Register Warehouse Staff
            }

            if (userID > 0) {
                request.setAttribute("message", "Registration successful! You can now log in.");
                request.setAttribute("alertType", "success");
            } else {
                request.setAttribute("message", "Registration failed. Please try again.");
                request.setAttribute("alertType", "danger");
            }
        } else { // User already exists
            request.setAttribute("message", "User already exists with the provided username or email.");
            request.setAttribute("alertType", "warning");
        }

// Forward back to the registration page
        RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
        dispatcher.forward(request, response);
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
