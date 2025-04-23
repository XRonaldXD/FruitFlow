<%-- 
    Document   : viewStockLevels
    Created on : 2025年4月23日, 下午4:20:39
    Author     : user
--%>

<%@page import="models.Fruit"%>
<%@page import="models.Stock"%>
<%@page import="java.util.List"%>
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
                <a class="navbar-brand" href="./warehouseStaff/dashboard.jsp">Warehouse Dashboard</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="../logout.jsp">Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Main Container -->
        <div class="container mt-4">
            <h1 class="mb-4">Update Stock Levels</h1>

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

            <!-- Stock Update Form -->
            <form action="UpdateStockHandler" method="POST">
                <input type="hidden" name="action" value="update_warehouse">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Fruit Name</th>
                            <th>Current Stock</th>
                            <th>New Stock Level</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Fruit> fruits = (List<Fruit>) request.getAttribute("fruits");
                            if (fruits != null) {
                                for (Fruit fruit : fruits) {
                        %>
                        <tr>
                            <td><%= fruit.getFruitName()%></td>
                            <td><%= fruit.getStockLevel()%></td>
                            <td>
                                <input type="hidden" name="old_stockLevel_<%= fruit.getFruitId()%>" value="<%= fruit.getStockLevel()%>">
                                <input type="number" class="form-control" name="stock_<%= fruit.getFruitId()%>" 
                                       placeholder="Enter new stock level" min="0" value="<%= fruit.getStockLevel()%>">
                            </td>
                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="3" class="text-center">No fruits available to update.</td>
                        </tr>
                        <% }%>
                    </tbody>
                </table>
                <button type="submit" class="btn btn-primary">Update Stock</button>
            </form>
        </div>

        <!-- Bootstrap JS Bundle -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>