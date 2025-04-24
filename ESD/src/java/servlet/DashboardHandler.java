/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import bean.User;
import db.FruitDB;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Fruit;
import models.ShopStock;

/**
 *
 * @author user
 */
@WebServlet(name = "DashboardHandler", urlPatterns = {"/DashboardHandler"})
public class DashboardHandler extends HttpServlet {
    
    private FruitDB fruitDB;

    public void init() {
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");

        fruitDB = new FruitDB(dbUrl, dbUser, dbPassword);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

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
