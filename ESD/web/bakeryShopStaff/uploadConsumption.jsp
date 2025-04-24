<%-- 
    Document   : uploadConsumption
    Created on : 2025年4月25日, 上午1:36:10
    Author     : user
--%>

<%@page import="models.Fruit"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Upload Consumption Report</title>
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
        <div class="container mt-4">
            <h2 class="text-center">Upload Consumption Report</h2>

            <!-- Display alert messages -->
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

            <!-- Manual Input Form -->
            <form action="ConsumptionHandler" method="POST">
                <input type="hidden" name="action" value="uploadConsumption">
                <div class="form-group">
                    <label for="fruitId">Fruit</label>
                    <select class="form-control" id="fruitId" name="fruitId" required>
                        <%
                            List<Fruit> fruits = (List<Fruit>) request.getAttribute("fruits");
                            if (fruits != null) {
                                for (Fruit fruit : fruits) {
                        %>
                        <option value="<%= fruit.getFruitId()%>"><%= fruit.getFruitName()%></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="quantity">Quantity</label>
                    <input type="number" class="form-control" id="quantity" name="quantity" placeholder="Enter quantity" required>
                </div>
                <div class="form-group">
                    <label for="consumptionDate">Consumption Date</label>
                    <input type="date" class="form-control" id="consumptionDate" name="consumptionDate" required>
                </div>
                <div class="form-group">
                    <label for="season">Season</label>
                    <select class="form-control" id="season" name="season" required>
                        <option value="Spring">Spring</option>
                        <option value="Summer">Summer</option>
                        <option value="Autumn">Autumn</option>
                        <option value="Winter">Winter</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary btn-block mt-3">Submit</button>
            </form>
        </div>

        <!-- Bootstrap JS Bundle -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
