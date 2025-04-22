<%-- 
    Document   : viewReservations
    Created on : 2025年4月22日, 下午6:12:12
    Author     : user
--%>

<%@page import="bean.Reservation"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Reservations</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="./bakeryShopStaff/dashboard.jsp">Bakery Dashboard</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="./logout.jsp">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Main Container -->
    <div class="container mt-4">
        <h1 class="mb-4">View Reservations</h1>

        <!-- Reservations Table -->
        <div class="card">
            <div class="card-body">
                <h3 class="card-title">Your Reservations</h3>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Reservation ID</th>
                            <th>Fruit Name</th>
                            <th>Quantity</th>
                            <th>Reservation Date</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                            // Example: Replace this with actual database data
                            List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
                            if (reservations != null && !reservations.isEmpty()) {
                                for (Reservation reservation : reservations) {
                        %>
                        <tr>
                            <td><%= reservation.getReservationId() %></td>
                            <td><%= reservation.getFruitName() %></td>
                            <td><%= reservation.getQuantity() %></td>
                            <td><%= reservation.getReservationDate() %></td>
                            <td><%= reservation.getStatus() %></td>
                        </tr>
                        <% 
                                }
                            } else {
                        %>
                        <tr>
                            <td colspan="5" class="text-center">No reservations found.</td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>