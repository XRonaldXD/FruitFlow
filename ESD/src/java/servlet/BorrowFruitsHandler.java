/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import bean.Borrow;
import bean.User;
import db.BorrowDB;
import db.FruitDB;
import db.ShopDB;
import db.UserDB;
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
import models.Shop;

/**
 *
 * @author user
 */
@WebServlet(name = "BorrowFruitsHandler", urlPatterns = {"/BorrowFruitsHandler"})
public class BorrowFruitsHandler extends HttpServlet {

    private BorrowDB borrowDB;
    private FruitDB fruitDB;
    private ShopDB shopDB;

    public void init() {
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");

        borrowDB = new BorrowDB(dbUrl, dbUser, dbPassword);
        fruitDB = new FruitDB(dbUrl, dbUser, dbPassword);
        shopDB = new ShopDB(dbUrl, dbUser, dbPassword);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        User user = (User) request.getSession().getAttribute("user");

        if (action.equals("view")) {
            // Fetch the list of fruits from the database
            int toShopId = user.getShopId();
            List<Borrow> borrows = borrowDB.getBorrowRequestsByToShopId(toShopId); // Fetch current borrows
            List<Fruit> fruits = fruitDB.getAllFruits();
            List<Shop> shops = shopDB.getShopsByCity(fruitDB.getCityId(user.getShopId()), user.getShopId());
            // Set the fruits list as a request attribute
            request.setAttribute("borrows", borrows);
            request.setAttribute("fruits", fruits);
            request.setAttribute("shops", shops);

            // Forward the request to the JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("/bakeryShopStaff/borrowFruits.jsp");
            dispatcher.forward(request, response);
        } else if (action.equals("borrow")) {
            int toShopId = user.getShopId();
            int fromShopId = Integer.parseInt(request.getParameter("fromShopId"));
            int fruitId = Integer.parseInt(request.getParameter("fruitId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String borrowDate = request.getParameter("borrowDate");

            // Create borrow request
            boolean success = borrowDB.createBorrowRequest(fromShopId, toShopId, fruitId, quantity, borrowDate);
// Fetch the list of fruits from the database
            List<Fruit> fruits = fruitDB.getAllFruits();
            List<Shop> shops = shopDB.getAllShops();
            // Set the fruits list as a request attribute
            request.setAttribute("fruits", fruits);
            request.setAttribute("shops", shops);
            if (success) {
                // Set success message as a request attribute
                request.setAttribute("message", "Borrow request submitted successfully!");
                request.setAttribute("alertType", "success"); // Bootstrap alert type
            } else {
                // Set failure message as a request attribute
                request.setAttribute("message", "Failed to submit borrow request. Please try again.");
                request.setAttribute("alertType", "danger"); // Bootstrap alert type
            }
            List<Borrow> borrows = borrowDB.getBorrowRequestsByToShopId(toShopId); // Fetch current borrows
            request.setAttribute("borrows", borrows);
// Forward the request back to the same page
            RequestDispatcher dispatcher = request.getRequestDispatcher("./bakeryShopStaff/borrowFruits.jsp");
            dispatcher.forward(request, response);
        } else if (action.equals("view_borrow")) {
            int fromShopId = user.getShopId();
            List<Borrow> borrows = borrowDB.getBorrowRequests(fromShopId);
            request.setAttribute("borrows", borrows);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./bakeryShopStaff/approveBorrow.jsp");
            dispatcher.forward(request, response);
        } else if (action.equals("approve")) {
            int borrowId = Integer.parseInt(request.getParameter("borrowId"));
            boolean success = borrowDB.approveBorrow(borrowId);

            if (success) {
                request.setAttribute("message", "Borrow request approved successfully.");
                request.setAttribute("alertType", "success");
            } else {
                request.setAttribute("message", "Failed to approve borrow request.");
                request.setAttribute("alertType", "danger");
            }
            int fromShopId = user.getShopId();
            List<Borrow> borrows = borrowDB.getBorrowRequests(fromShopId);
            request.setAttribute("borrows", borrows);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./bakeryShopStaff/approveBorrow.jsp");
            dispatcher.forward(request, response);
        } else if (action.equals("reject")) {
            int borrowId = Integer.parseInt(request.getParameter("borrowId"));
            boolean success = borrowDB.rejectBorrow(borrowId);

            if (success) {
                request.setAttribute("message", "Borrow request rejected successfully.");
                request.setAttribute("alertType", "success");
            } else {
                request.setAttribute("message", "Failed to reject borrow request.");
                request.setAttribute("alertType", "danger");
            }
            int fromShopId = user.getShopId();
            List<Borrow> borrows = borrowDB.getBorrowRequests(fromShopId);
            request.setAttribute("borrows", borrows);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./bakeryShopStaff/approveBorrow.jsp");
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
