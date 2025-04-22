<%-- 
    Document   : viewStock
    Created on : 2025年4月22日, 下午6:11:32
    Author     : user
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Stock Levels</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="dashboard.jsp">Bakery Dashboard</a>
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
        <h1 class="mb-4">View Stock Levels</h1>

        <!-- Current Shop Stock -->
        <div class="card mb-4">
            <div class="card-body">
                <h3 class="card-title">Current Shop Stock</h3>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Fruit Name</th>
                            <th>Stock Level</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                            // Example: Replace this with actual database data
                            List<Fruit> shopStock = (List<Fruit>) request.getAttribute("shopStock");
                            if (shopStock != null) {
                                for (Fruit fruit : shopStock) {
                        %>
                        <tr>
                            <td><%= fruit.getFruitName() %></td>
                            <td><%= fruit.getStockLevel() %></td>
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
            </div>
        </div>

        <!-- Other Shops in the Same City -->
        <div class="card">
            <div class="card-body">
                <h3 class="card-title">Other Shops in the Same City</h3>
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
                            // Example: Replace this with actual database data
                            List<ShopStock> cityStock = (List<ShopStock>) request.getAttribute("cityStock");
                            if (cityStock != null) {
                                for (ShopStock stock : cityStock) {
                        %>
                        <tr>
                            <td><%= stock.getShopName() %></td>
                            <td><%= stock.getFruitName() %></td>
                            <td><%= stock.getStockLevel() %></td>
                        </tr>
                        <% 
                                }
                            } else {
                        %>
                        <tr>
                            <td colspan="3">No stock data available for other shops.</td>
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
