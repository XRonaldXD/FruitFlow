<%-- 
    Document   : dashboard
    Created on : 2025年4月22日, 下午4:16:35
    Author     : user
--%>
<%@page import="models.ShopStock"%>
<%@page import="models.Fruit"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Bakery Shop Staff Dashboard</title>
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
            <!-- Welcome Section -->
            <div class="card mb-4">
                <div class="card-body">
                    <jsp:useBean id="user" class="bean.User" scope="session"></jsp:useBean>
                    <h3 class="card-title">Welcome, <jsp:getProperty name="user" property="username"/>!</h3>
                    <p class="card-text">Role: Bakery Shop Staff</p>
                </div>
            </div>
            <!-- View Stock Section -->
            <div class="card mb-4">
                <div class="card-body">
                    <h3 class="card-title">View Stock Levels</h3>

                    <!-- Current Shop Stock -->
                    <h4>Current Shop Stock</h4>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Fruit Name</th>
                                <th>Stock Level</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<Fruit> shopStock = (List<Fruit>) request.getAttribute("shopStock");
                                if (shopStock != null) {
                                    for (Fruit fruit : shopStock) {
                            %>
                            <tr>
                                <td><%= fruit.getFruitName()%></td>
                                <td><%= fruit.getStockLevel()%></td>
                            </tr>
                            <%
                                }
                            } else {
                            %>
                            <tr>
                                <td colspan="2">No stock data available.</td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>

                    <!-- Other Shops in the Same City -->
                    <h4>Other Shops in the Same City</h4>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Shop Name</th>
                                <th>Fruit Name</th>
                                <th>Stock Level</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<ShopStock> cityStock = (List<ShopStock>) request.getAttribute("cityStock");
                                if (cityStock != null) {
                                    for (ShopStock stock : cityStock) {
                            %>
                            <tr>
                                <td><%= stock.getShopName()%></td>
                                <td><%= stock.getFruitName()%></td>
                                <td><%= stock.getStockLevel()%></td>
                            </tr>
                            <%
                                }
                            } else {
                            %>
                            <tr>
                                <td colspan="3">No stock data available for other shops.</td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                </div>
            </div>

            <!-- Actions Section -->
            <div class="card mb-4">
                <div class="card-body">
                    <h3 class="card-title">Actions</h3>
                    <div class="d-grid gap-2">
                        <a href="./ReserveFruitsHandler?action=view" class="btn btn-success">Reserve Fruits</a>
                        <a href="./BorrowFruitsHandler?action=view" class="btn btn-warning">Borrow Fruits</a>
                        <a href="./ViewReservations" class="btn btn-info">View Reservations</a>
                        <a href="./UpdateStockHandler?action=view" class="btn btn-secondary">Update Stock Levels</a>
                        <a href="./ConsumptionHandler?action=view" class="btn btn-primary">Upload Consumption</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Bootstrap JS Bundle -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>