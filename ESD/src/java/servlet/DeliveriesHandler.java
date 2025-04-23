/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import bean.Reservation;
import bean.User;
import db.DeliveryDB;
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
import models.Delivery;

/**
 *
 * @author user
 */
@WebServlet(name = "DeliveriesHandler", urlPatterns = {"/DeliveriesHandler"})
public class DeliveriesHandler extends HttpServlet {

    private DeliveryDB deliveryDB;
    private ReservationDB reservationDB;

    public void init() {
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");

        deliveryDB = new DeliveryDB(dbUrl, dbUser, dbPassword);
        reservationDB = new ReservationDB(dbUrl, dbUser, dbPassword);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String action = request.getParameter("action");
        if (action.equals("view")) {
            List<Reservation> reservations = reservationDB.getReservationsByWarehouse(user.getWarehouseId());

            // Set deliveries as a request attribute
            request.setAttribute("reservations", reservations);

            // Forward to the JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("/warehouseStaff/arrangeDeliveriesToWarehouse.jsp");
            dispatcher.forward(request, response);
        } else if (action.equals("arrange")) {
            int reservationId = Integer.parseInt(request.getParameter("reservationId"));
            int fruitId = Integer.parseInt(request.getParameter("fruitId"));
            int fromWarehouseId = user.getWarehouseId();
            Integer toWarehouseId = Integer.parseInt(request.getParameter("toWarehouseId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String deliveryDate = request.getParameter("deliveryDate");

            // Create a new delivery
            boolean deliveryCreated = deliveryDB.createDelivery(reservationId, fruitId, fromWarehouseId, toWarehouseId, null, quantity, deliveryDate);

            if (deliveryCreated) {
                // Attempt to update the reservation status
                boolean updateReservationStatus = reservationDB.updateReservationStatus(reservationId, "In Transit");
                if (updateReservationStatus) {
                    request.setAttribute("message", "Delivery arranged successfully!");
                    request.setAttribute("alertType", "success");
                } else {
                    request.setAttribute("message", "Delivery created, but failed to update reservation status.");
                    request.setAttribute("alertType", "warning");
                }
            } else {
                request.setAttribute("message", "Failed to arrange delivery. Please try again.");
                request.setAttribute("alertType", "danger");
            }

            // Forward back to the delivery arrangement page
            RequestDispatcher dispatcher = request.getRequestDispatcher("./warehouseStaff/arrangeDeliveriesToWarehouse.jsp?action=view");
            dispatcher.forward(request, response);
        } else if (action.equals("view_toShop")) {
            List<Reservation> reservations = reservationDB.getPendingReservations();

            // Set deliveries as a request attribute
            request.setAttribute("reservations", reservations);

            // Forward to the JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("./warehouseStaff/arrangeDeliveriesToShop.jsp");
            dispatcher.forward(request, response);
        } else if (action.equals("arrange_toShop")) {
            int reservationId = Integer.parseInt(request.getParameter("reservationId"));
            int fruitId = Integer.parseInt(request.getParameter("fruitId"));
            Integer fromWarehouseId = user.getWarehouseId();
            Integer toShopId = Integer.parseInt(request.getParameter("toShopId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String deliveryDate = request.getParameter("deliveryDate");

            // Create a new delivery
            boolean deliveryCreated = deliveryDB.createDelivery(reservationId, fruitId, fromWarehouseId, null, toShopId, quantity, deliveryDate);

            if (deliveryCreated) {
                boolean updateReservationStatus = reservationDB.updateReservationStatus(reservationId, "In Transit");
                if (updateReservationStatus) {
                    request.setAttribute("message", "Delivery arranged successfully!");
                    request.setAttribute("alertType", "success");
                } else {
                    request.setAttribute("message", "Delivery created, but failed to update reservation status.");
                    request.setAttribute("alertType", "warning");
                }

            } else {
                request.setAttribute("message", "Failed to arrange delivery. Please try again.");
                request.setAttribute("alertType", "danger");
            }
            List<Reservation> reservations = reservationDB.getPendingReservations();
            request.setAttribute("reservations", reservations);

            // Forward back to the delivery arrangement page
            RequestDispatcher dispatcher = request.getRequestDispatcher("./warehouseStaff/arrangeDeliveriesToShop.jsp?action=view_toShop");
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
