<%-- 
    Document   : reserveFruits
    Created on : 2025年4月22日, 下午6:11:49
    Author     : user
--%>

<%@page import="java.util.List"%>
<%@page import="models.Fruit"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Reserve Fruits</title>
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
            <h1 class="mb-4">Reserve Fruits</h1>

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

            <!-- Reservation Form -->
            <div class="card">
                <div class="card-body">
                    <h3 class="card-title">Reserve Fruits from Warehouse</h3>
                    <form action="ReserveFruitsHandler" method="POST">
                        <input type="hidden" name="action" value="reserve">
                        <!-- Fruit Selection -->
                        <div class="mb-3">
                            <label for="fruitId" class="form-label">Select Fruit</label>
                            <select class="form-select" id="fruitId" name="fruitId" required>
                                <%
                                    // Example: Replace this with actual database data
                                    List<Fruit> fruits = (List<Fruit>) request.getAttribute("fruits");
                                    if (fruits != null) {
                                        for (Fruit fruit : fruits) {
                                %>
                                <option value="<%= fruit.getFruitId()%>"><%= fruit.getFruitName()%></option>
                                <%
                                    }
                                } else {
                                %>
                                <option disabled>No fruits available</option>
                                <% }%>
                            </select>
                        </div>

                        <!-- Quantity -->
                        <div class="mb-3">
                            <label for="quantity" class="form-label">Quantity</label>
                            <input type="number" class="form-control" id="quantity" name="quantity" min="1" required>
                        </div>

                        <!-- Reservation Date -->
                        <div class="mb-3">
                            <label for="reservationDate" class="form-label">Reservation Date</label>
                            <input type="date" class="form-control" id="reservationDate" name="reservationDate" required>
                        </div>

                        <!-- Submit Button -->
                        <button type="submit" class="btn btn-primary">Reserve</button>
                    </form>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS Bundle -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>