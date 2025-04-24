<%-- 
    Document   : reserveNeeds
    Created on : 2025年4月25日, 上午2:32:34
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
        <title>Reservations</title>
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
                            <a class="nav-link" href="../logout.jsp">Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container mt-4">
            <h1 class="mb-4">Reservations</h1>

            <!-- Search Bar with Dropdown -->
            <div class="row mb-3">
                <div class="col-md-4">
                    <select id="searchOption" class="form-select">
                        <option value="all" selected>Search All</option>
                        <option value="shopName">Shop Name</option>
                        <option value="warehouseName">Warehouse Name</option>
                        <option value="countryName">Country Name</option>
                    </select>
                </div>
                <div class="col-md-8">
                    <input type="text" id="searchInput" class="form-control" placeholder="Search...">
                </div>
            </div>

            <!-- Reservations Table -->
            <table class="table table-striped" id="reservationsTable">
                <thead>
                    <tr>
                        <th>Reservation ID</th>
                        <th>Fruit Name</th>
                        <th>Shop Name</th>
                        <th>Warehouse Name</th>
                        <th>Country Name</th>
                        <th>Quantity</th>
                        <th>Reservation Date</th>
                        <th>Status</th>
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
                        <td><%= reservation.getFruitName()%></td>
                        <td><%= reservation.getShopName()%></td>
                        <td><%= reservation.getWarehouseName() != null ? reservation.getWarehouseName() : "N/A"%></td>
                        <td><%= reservation.getCountryName()%></td>
                        <td><%= reservation.getQuantity()%></td>
                        <td><%= reservation.getReservationDate()%></td>
                        <td><%= reservation.getStatus()%></td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="8">No reservations found.</td>
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

                    $("#reservationsTable tbody tr").filter(function () {
                        // Get column values
                        var shopName = $(this).find("td:nth-child(3)").text().toLowerCase(); // Shop Name (3rd column)
                        var warehouseName = $(this).find("td:nth-child(4)").text().toLowerCase(); // Warehouse Name (4th column)
                        var countryName = $(this).find("td:nth-child(5)").text().toLowerCase(); // Country Name (5th column)

                        // Toggle row visibility based on search option and input
                        if (searchOption === "shopName") {
                            $(this).toggle(shopName.indexOf(value) > -1);
                        } else if (searchOption === "warehouseName") {
                            $(this).toggle(warehouseName.indexOf(value) > -1);
                        } else if (searchOption === "countryName") {
                            $(this).toggle(countryName.indexOf(value) > -1);
                        } else {
                            // Search all columns
                            $(this).toggle(
                                    shopName.indexOf(value) > -1 ||
                                    warehouseName.indexOf(value) > -1 ||
                                    countryName.indexOf(value) > -1
                                    );
                        }
                    });
                });
            });
        </script>
    </body>
</html>