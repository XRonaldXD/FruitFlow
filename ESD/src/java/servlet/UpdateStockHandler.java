/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import bean.User;
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
import models.Fruit;

/**
 *
 * @author user
 */
@WebServlet(name = "UpdateStockHandler", urlPatterns = {"/UpdateStockHandler"})
public class UpdateStockHandler extends HttpServlet {

    private FruitDB fruitDB;

    public void init() {
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");

        fruitDB = new FruitDB(dbUrl, dbUser, dbPassword);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        User user = (User) request.getSession().getAttribute("user");

        if (action.equals("view")) {
            // Fetch all fruits to process their stock updates
            List<Fruit> fruits = fruitDB.getFruitsWithStockLevels(user.getShopId());
            request.setAttribute("fruits", fruits);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./bakeryShopStaff/updateStockLevels.jsp");
            dispatcher.forward(request, response);

        } else if (action.equals("update")) {
            boolean success = true;

            List<Fruit> fruits = fruitDB.getFruitsWithStockLevels(user.getShopId());

            for (Fruit fruit : fruits) {
                String paramName = "stock_" + fruit.getFruitId();
                String newStockLevelStr = request.getParameter(paramName);
                String oldStockLevelStr = request.getParameter("old_stockLevel_" + fruit.getFruitId());

                if (newStockLevelStr != null) {
                    try {
                        int newStockLevel = Integer.parseInt(newStockLevelStr);
                        int oldStockLevel = Integer.parseInt(oldStockLevelStr);
                        if (newStockLevel != oldStockLevel) {
                            boolean updated = fruitDB.insertOrUpdateStockLevel(fruit.getFruitId(), user.getShopId(), newStockLevel);

                            if (!updated) {
                                success = false;
                            }
                        }

                    } catch (NumberFormatException e) {
                        System.err.println("Invalid stock level input for fruit ID: " + fruit.getFruitId());
                        e.printStackTrace();
                        success = false; // Set to false only if the exception affects the operation
                    }
                }
            }

            // Set success or failure message
            if (success) {
                request.setAttribute("message", "Stock levels updated successfully!");
                request.setAttribute("alertType", "success");
            } else {
                request.setAttribute("message", "Failed to update some stock levels. Please try again.");
                request.setAttribute("alertType", "danger");
            }

            // Fetch updated fruits list and forward back to the JSP
            List<Fruit> updatedFruits = fruitDB.getFruitsWithStockLevels(user.getShopId());
            request.setAttribute("fruits", updatedFruits);

            RequestDispatcher dispatcher = request.getRequestDispatcher("./bakeryShopStaff/updateStockLevels.jsp");
            dispatcher.forward(request, response);
        }
        else if (action.equals("view_warehouse")){
            // Fetch all fruits to process their stock updates
            List<Fruit> fruits = fruitDB.getFruitsWithStockLevelsByWarehouse(user.getWarehouseId());
            request.setAttribute("fruits", fruits);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./warehouseStaff/viewStockLevels.jsp");
            dispatcher.forward(request, response);
        }else if (action.equals("update_warehouse")){
            boolean success = true;

            List<Fruit> fruits = fruitDB.getFruitsWithStockLevelsByWarehouse(user.getWarehouseId());

            for (Fruit fruit : fruits) {
                String paramName = "stock_" + fruit.getFruitId();
                String newStockLevelStr = request.getParameter(paramName);
                String oldStockLevelStr = request.getParameter("old_stockLevel_" + fruit.getFruitId());

                if (newStockLevelStr != null) {
                    try {
                        int newStockLevel = Integer.parseInt(newStockLevelStr);
                        int oldStockLevel = Integer.parseInt(oldStockLevelStr);
                        if (newStockLevel != oldStockLevel) {
                            boolean updated = fruitDB.insertOrUpdateStockLevelByWarehouse(fruit.getFruitId(), user.getWarehouseId(), newStockLevel);

                            if (!updated) {
                                success = false;
                            }
                        }

                    } catch (NumberFormatException e) {
                        System.err.println("Invalid stock level input for fruit ID: " + fruit.getFruitId());
                        e.printStackTrace();
                        success = false; // Set to false only if the exception affects the operation
                    }
                }
            }

            // Set success or failure message
            if (success) {
                request.setAttribute("message", "Stock levels updated successfully!");
                request.setAttribute("alertType", "success");
            } else {
                request.setAttribute("message", "Failed to update some stock levels. Please try again.");
                request.setAttribute("alertType", "danger");
            }

            // Fetch updated fruits list and forward back to the JSP
            List<Fruit> updatedFruits = fruitDB.getFruitsWithStockLevelsByWarehouse(user.getWarehouseId());
            request.setAttribute("fruits", updatedFruits);

            RequestDispatcher dispatcher = request.getRequestDispatcher("./warehouseStaff/viewStockLevels.jsp");
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
