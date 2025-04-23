<%-- 
    Document   : reservations
    Created on : 2025年4月23日, 下午5:09:00
    Author     : user
--%>

<%@page import="models.CountryReservation"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Reservations Management</title>
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

        <!-- Main Container -->
        <div class="container mt-4">
            <h1 class="mb-4">Reservations Management</h1>
            
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
            
            <!-- Reservations Table -->
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Country</th>
                        <th>Fruit</th>
                        <th>Total Quantity Needed</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<CountryReservation> countryReservations = (List<CountryReservation>) request.getAttribute("countryReservations");
                        if (countryReservations != null && !countryReservations.isEmpty()) {
                            for (CountryReservation reservation : countryReservations) {
                    %>
                    <tr>
                        <td><%= reservation.getCountryName()%></td>
                        <td><%= reservation.getFruitName()%></td>
                        <td><%= reservation.getTotalQuantity()%></td>
                        <td>
                            <form action="ReserveFruitsHandler" method="POST" class="d-inline">
                                <input type="hidden" name="countryId" value="<%= reservation.getCountryId()%>">
                                <input type="hidden" name="fruitId" value="<%= reservation.getFruitId()%>">
                                <button type="submit" name="action" value="approve" class="btn btn-success">Approve</button>
                            </form>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="4" class="text-center">No reservations found.</td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>

        <!-- Bootstrap JS Bundle -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>