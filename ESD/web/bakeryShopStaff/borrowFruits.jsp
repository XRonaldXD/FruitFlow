<%-- 
    Document   : borrowFruits
    Created on : 2025年4月22日, 下午6:12:00
    Author     : user
--%>

<%@page import="bean.Borrow"%>
<%@page import="models.Shop"%>
<%@page import="java.util.List"%>
<%@page import="models.Fruit"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Borrow Fruits</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="./DashboardHandler">Bakery Dashboard</a>
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
            <h1 class="mb-4">Borrow Fruits</h1>

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

            <!-- Borrow Fruits Form -->
            <div class="card">
                <div class="card-body">
                    <h3 class="card-title">Borrow Fruits from Other Shops</h3>
                    <form action="BorrowFruitsHandler" method="POST">
                        <input type="hidden" name="action" value="borrow">
                        <input type="hidden" name="toShopId" value="">
                        <!-- Fruit Selection -->
                        <div class="mb-3">
                            <label for="fruitId" class="form-label">Select Fruit</label>
                            <select class="form-select" id="fruitId" name="fruitId" required>
                                <%
                                    List<Fruit> fruits = (List<Fruit>) request.getAttribute("fruits");
                                    if (fruits != null) {
                                        for (Fruit fruit : fruits) {
                                %>
                                <option name="fruitId" value="<%= fruit.getFruitId()%>"><%= fruit.getFruitName()%></option>
                                <%
                                    }
                                } else {
                                %>
                                <option disabled>No fruits available</option>
                                <% } %>
                            </select>
                        </div>

                        <!-- Shop Selection -->
                        <div class="mb-3">
                            <label for="shopId" class="form-label">Select Shop to Borrow From</label>
                            <select class="form-select" id="fromShopId" name="fromShopId" required>
                                <%
                                    List<Shop> shops = (List<Shop>) request.getAttribute("shops");
                                    if (shops != null) {
                                        for (Shop shop : shops) {
                                %>
                                <option name="fromShopId" value="<%= shop.getShopId()%>"><%= shop.getShopName()%></option>
                                <%
                                    }
                                } else {
                                %>
                                <option disabled>No shops available</option>
                                <% }%>F
                            </select>
                        </div>

                        <!-- Quantity -->
                        <div class="mb-3">
                            <label for="quantity" class="form-label">Quantity</label>
                            <input type="number" class="form-control" id="quantity" name="quantity" min="1" required>
                        </div>

                        <!-- Borrow Date -->
                        <div class="mb-3">
                            <label for="borrowDate" class="form-label">Borrow Date</label>
                            <input type="date" class="form-control" id="borrowDate" name="borrowDate" required>
                        </div>

                        <!-- Submit Button -->
                        <button type="submit" class="btn btn-primary">Borrow</button>
                    </form>
                </div>
            </div>
                            
            <!-- Current Borrow List -->
            <div class="card">
                <div class="card-body">
                    <h3 class="card-title">Current Borrow List</h3>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Borrow ID</th>
                                <th>Borrow From</th>
                                <th>Fruit</th>
                                <th>Quantity</th>
                                <th>Borrow Date</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<Borrow> borrows = (List<Borrow>) request.getAttribute("borrows");
                                if (borrows != null && !borrows.isEmpty()) {
                                    for (Borrow borrow : borrows) {
                            %>
                            <tr>
                                <td><%= borrow.getBorrowId()%></td>
                                <td><%= borrow.getFromShopName()%></td>
                                <td><%= borrow.getFruitName()%></td>
                                <td><%= borrow.getQuantity()%></td>
                                <td><%= borrow.getBorrowDate()%></td>
                                <td><%= borrow.getStatus()%></td>
                            </tr>
                            <%
                                }
                            } else {
                            %>
                            <tr>
                                <td colspan="6">No current borrow requests found.</td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS Bundle -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
