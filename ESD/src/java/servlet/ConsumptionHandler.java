/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import bean.User;
import db.ConsumptionDB;
import db.FruitDB;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.ConsumptionRecord;
import models.Fruit;

/**
 *
 * @author user
 */
@WebServlet(name = "ConsumptionHandler", urlPatterns = {"/ConsumptionHandler"})
public class ConsumptionHandler extends HttpServlet {

    private ConsumptionDB consumptionDB;
    private FruitDB fruitDB;

    public void init() {
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");

        consumptionDB = new ConsumptionDB(dbUrl, dbUser, dbPassword);
        fruitDB = new FruitDB(dbUrl, dbUser, dbPassword);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        User user = (User) request.getSession().getAttribute("user");

        if (action.equals("view")) {
            List<Fruit> fruits = (List<Fruit>) fruitDB.getAllFruits();

            request.setAttribute("fruits", fruits);

            // Forward to the JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("./bakeryShopStaff/uploadConsumption.jsp");
            dispatcher.forward(request, response);
        } else if (action.equals("uploadConsumption")) {
            List<Fruit> fruits = (List<Fruit>) fruitDB.getAllFruits();

            request.setAttribute("fruits", fruits);
            int shopId = user.getShopId();
            int fruitId = Integer.parseInt(request.getParameter("fruitId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String consumptionDate = request.getParameter("consumptionDate");
            String season = request.getParameter("season");

            boolean success = consumptionDB.addConsumption(shopId, fruitId, quantity, consumptionDate, season);

            if (success) {
                request.setAttribute("message", "Consumption record added successfully!");
                request.setAttribute("alertType", "success");
            } else {
                request.setAttribute("message", "Failed to add consumption record. Please try again.");
                request.setAttribute("alertType", "danger");
            }

            // Forward to the JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("./bakeryShopStaff/uploadConsumption.jsp");
            dispatcher.forward(request, response);
        } else if (action.equals("view_consumptionRecords")) {
            // Fetch all consumption records
            List<ConsumptionRecord> records = consumptionDB.getAllConsumptionRecords();

            // Set records as a request attribute
            request.setAttribute("records", records);

            // Forward to the JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("./seniorManagement/consumptionRecords.jsp");
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
