<%-- 
    Document   : consumptionRecords
    Created on : 2025年4月25日, 上午3:45:58
    Author     : user
--%>

<%@page import="java.util.List"%>
<%@page import="models.ConsumptionRecord"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Consumption Records</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </head>
    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="./seniorManagement/dashboard.jsp">Senior Management Dashboard</a>
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
            <h1 class="mb-4">Consumption Records</h1>

            <!-- Search Bar with Dropdown -->
            <div class="row mb-3">
                <div class="col-md-4">
                    <select id="searchOption" class="form-select">
                        <option value="all" selected>Search All</option>
                        <option value="shopName">Shop Name</option>
                        <option value="cityName">City Name</option>
                        <option value="countryName">Country Name</option>
                        <option value="fruitName">Fruit Name</option>
                        <option value="season">Season</option>
                    </select>
                </div>
                <div class="col-md-8">
                    <input type="text" id="searchInput" class="form-control" placeholder="Search...">
                </div>
            </div>

            <!-- Consumption Records Table -->
            <table class="table table-striped" id="consumptionTable">
                <thead>
                    <tr>
                        <th>Consumption ID</th>
                        <th>Shop Name</th>
                        <th>City Name</th>
                        <th>Country Name</th>
                        <th>Fruit Name</th>
                        <th>Quantity</th>
                        <th>Consumption Date</th>
                        <th>Season</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<ConsumptionRecord> records = (List<ConsumptionRecord>) request.getAttribute("records");
                        if (records != null && !records.isEmpty()) {
                            for (ConsumptionRecord record : records) {
                    %>
                    <tr>
                        <td><%= record.getConsumptionId()%></td>
                        <td><%= record.getShopName()%></td>
                        <td><%= record.getCityName()%></td>
                        <td><%= record.getCountryName()%></td>
                        <td><%= record.getFruitName()%></td>
                        <td><%= record.getQuantity()%></td>
                        <td><%= record.getConsumptionDate()%></td>
                        <td><%= record.getSeason()%></td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="8">No consumption records found.</td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>

        <!-- Bootstrap JS Bundle -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

        <!-- jQuery Script for Search -->
        <script>
            $(document).ready(function () {
                // Search functionality
                $("#searchInput").on("keyup", function () {
                    var value = $(this).val().toLowerCase();
                    var searchOption = $("#searchOption").val(); // Get selected search option

                    $("#consumptionTable tbody tr").filter(function () {
                        // Get column values
                        var shopName = $(this).find("td:nth-child(2)").text().toLowerCase(); // Shop Name (2nd column)
                        var cityName = $(this).find("td:nth-child(3)").text().toLowerCase(); // City Name (3rd column)
                        var countryName = $(this).find("td:nth-child(4)").text().toLowerCase(); // Country Name (4th column)
                        var fruitName = $(this).find("td:nth-child(5)").text().toLowerCase(); // Fruit Name (5th column)
                        var season = $(this).find("td:nth-child(8)").text().toLowerCase(); // Season (8th column)

                        // Toggle row visibility based on search option and input
                        if (searchOption === "shopName") {
                            $(this).toggle(shopName.indexOf(value) > -1);
                        } else if (searchOption === "cityName") {
                            $(this).toggle(cityName.indexOf(value) > -1);
                        } else if (searchOption === "countryName") {
                            $(this).toggle(countryName.indexOf(value) > -1);
                        } else if (searchOption === "fruitName") {
                            $(this).toggle(fruitName.indexOf(value) > -1);
                        } else if (searchOption === "season") {
                            $(this).toggle(season.indexOf(value) > -1);
                        } else {
                            // Search all columns
                            $(this).toggle(
                                    shopName.indexOf(value) > -1 ||
                                    cityName.indexOf(value) > -1 ||
                                    countryName.indexOf(value) > -1 ||
                                    fruitName.indexOf(value) > -1 ||
                                    season.indexOf(value) > -1
                                    );
                        }
                    });
                });
            });
        </script>
    </body>
</html>