/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import bean.User;
import db.FruitDB;
import db.ReservationDB;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Fruit;

/**
 *
 * @author user
 */
@WebServlet(name = "ReserveFruitsHandler", urlPatterns = {"/ReserveFruitsHandler"})
public class ReserveFruitsHandler extends HttpServlet {

    private ReservationDB reservationDB;
    private FruitDB fruitDB;

    public void init() {
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");

        reservationDB = new ReservationDB(dbUrl, dbUser, dbPassword);
        fruitDB = new FruitDB(dbUrl, dbUser, dbPassword);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("view")) {
            // Fetch the list of fruits from the database
            List<Fruit> fruits = fruitDB.getAllFruits();

            // Set the fruits list as a request attribute
            request.setAttribute("fruits", fruits);

            // Forward the request to the JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("/bakeryShopStaff/reserveFruits.jsp");
            dispatcher.forward(request, response);
        } else if (action.equals("reserve")) {
            int fruitId = Integer.parseInt(request.getParameter("fruitId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String reservationDate = request.getParameter("reservationDate");

            // Get shopId from session (assuming the user is logged in)
            User user = (User) request.getSession().getAttribute("user");
            int shopId = user.getShopId();

            // Save reservation to the database
            boolean success = reservationDB.createReservation(shopId, fruitId, quantity, reservationDate);
            List<Fruit> fruits = fruitDB.getAllFruits();

            // Set the fruits list as a request attribute
            request.setAttribute("fruits", fruits);
            if (success) {
                // Set success message as a request attribute
                request.setAttribute("message", "Reservation successful!");
                request.setAttribute("alertType", "success"); // Bootstrap alert type
            } else {
                // Set failure message as a request attribute
                request.setAttribute("message", "Reservation failed. Please try again.");
                request.setAttribute("alertType", "danger"); // Bootstrap alert type
            }

// Forward the request back to the same page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/bakeryShopStaff/reserveFruits.jsp?action=view");
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
