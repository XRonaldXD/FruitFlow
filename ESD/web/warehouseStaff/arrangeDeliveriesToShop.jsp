<%-- 
    Document   : arrangeDeliveriesToShop
    Created on : 2025年4月23日, 下午7:58:07
    Author     : user
--%>

<%@page import="models.Shop"%>
<%@page import="bean.Reservation"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Arrange Delivery to Shops</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="./warehouseStaff/dashboard.jsp">Warehouse Dashboard</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="logout.jsp">Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container mt-4">
            <h1 class="mb-4">Arrange Delivery to Shops</h1>

            <!-- Alert Message -->
            <%
                String message = (String) request.getAttribute("message");
                String alertType = (String) request.getAttribute("alertType");
                if (message != null && alertType != null) {
            %>
            <div class="alert alert-<%= alertType%> alert-dismissible fade show" role="alert">
                <%= message%>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <% } %>

            <!-- Pending Reservations Table -->
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Reservation ID</th>
                        <th>Fruit ID</th>
                        <th>From Shop</th>
                        <th>Quantity</th>
                        <th>Delivery Date</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
                        if (reservations != null && !reservations.isEmpty()) {
                            for (Reservation reservation : reservations) {
                    %>
                    <tr>
                        <td><%= reservation.getReservationId()%></td>
                        <td><%= reservation.getFruitId()%></td>
                        <td><%= reservation.getShopName()%></td>
                        <td><%= reservation.getQuantity()%></td>
                        <td><%= reservation.getReservationDate()%></td>
                        <td>
                            <form action="DeliveriesHandler" method="POST">
                                <input type="hidden" name="reservationId" value="<%= reservation.getReservationId()%>">
                                <input type="hidden" name="fruitId" value="<%= reservation.getFruitId()%>">
                                <input type="hidden" name="toShopId" value="<%= reservation.getShopId()%>">
                                <input type="hidden" name="quantity" value="<%= reservation.getQuantity()%>">
                                <input type="date" class="form-control" name="deliveryDate" required>
                                <button type="submit" name="action" value="arrange_toShop" class="btn btn-primary mt-2">Arrange Delivery</button>
                            </form>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="8" class="text-center">No pending reservations found.</td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>

        <!-- Bootstrap JS Bundle -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>