<%-- 
    Document   : userManagement
    Created on : 2025年4月25日, 下午3:34:32
    Author     : user
--%>

<%@page import="bean.Warehouse"%>
<%@page import="models.Shop"%>
<%@page import="java.util.List"%>
<%@page import="bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Management</title>
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
            <h1 class="mb-4">User Management</h1>

            <!-- Search Bar with Dropdown -->
            <div class="row mb-3">
                <div class="col-md-4">
                    <select id="searchOption" class="form-select">
                        <option value="all" selected>Search All</option>
                        <option value="username">Username</option>
                        <option value="role">Role</option>
                        <option value="shopId">Shop ID</option>
                        <option value="warehouseId">Warehouse ID</option>
                    </select>
                </div>
                <div class="col-md-8">
                    <input type="text" id="searchInput" class="form-control" placeholder="Search...">
                </div>
            </div>
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
            <!-- Add User Form -->
            <div class="card mb-4">
                <div class="card-body">
                    <h3 class="card-title">Add User</h3>
                    <form action="AdminHandler" method="POST">
                        <input type="hidden" name="action" value="addUser">

                        <!-- Username -->
                        <div class="mb-3">
                            <label for="username" class="form-label">Username</label>
                            <input type="text" class="form-control" id="username" name="username" required>
                        </div>

                        <!-- Email -->
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>

                        <!-- Password -->
                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>

                        <!-- Role -->
                        <div class="mb-3">
                            <label for="role" class="form-label">Role</label>
                            <select class="form-select" id="role" name="role" required onchange="toggleIdFields()">
                                <option value="">Select Role</option>
                                <option value="BakeryShopStaff">Bakery Shop Staff</option>
                                <option value="WarehouseStaff">Warehouse Staff</option>
                                <option value="SeniorManagement">Senior Management</option>
                            </select>
                        </div>

                        <!-- Shop ID (for BakeryShopStaff) -->
                        <div class="mb-3" id="shopIdField" style="display: none;">
                            <label for="shopId" class="form-label">Shop ID</label>
                            <select class="form-select" id="shopId" name="shopId">
                                <option value="">Select Shop</option>
                                <%
                                    List<Shop> shops = (List<Shop>) request.getAttribute("shops");
                                    if (shops != null) {
                                        for (Shop shop : shops) {
                                %>
                                <option value="<%= shop.getShopId()%>"><%= shop.getShopName()%></option>
                                <%
                                        }
                                    }
                                %>
                            </select>
                        </div>

                        <!-- Warehouse ID (for WarehouseStaff) -->
                        <div class="mb-3" id="warehouseIdField" style="display: none;">
                            <label for="warehouseId" class="form-label">Warehouse ID</label>
                            <select class="form-select" id="warehouseId" name="warehouseId" >
                                <option value="">Select Warehouse</option>
                                <%
                                    List<Warehouse> warehouses = (List<Warehouse>) request.getAttribute("warehouses");
                                    if (warehouses != null) {
                                        for (Warehouse warehouse : warehouses) {
                                %>
                                <option value="<%= warehouse.getWarehouseId()%>"><%= warehouse.getWarehouseName()%></option>
                                <%
                                        }
                                    }
                                %>
                            </select>
                        </div>

                        <!-- Submit Button -->
                        <button type="submit" class="btn btn-primary">Add User</button>
                    </form>
                </div>
            </div>
            <!-- User Table -->
            <table class="table table-striped" id="userTable">
                <thead>
                    <tr>
                        <th>User ID</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Shop ID</th>
                        <th>Warehouse ID</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<User> users = (List<User>) request.getAttribute("users");
                        if (users != null) {
                            for (User user : users) {
                    %>
                    <tr>
                        <td><%= user.getUserId()%></td>
                        <td><%= user.getUsername()%></td>
                        <td><%= user.getEmail()%></td>
                        <td><%= user.getRole()%></td>
                        <td><%= user.getShopId() != 0 ? user.getShopId() : "N/A"%></td>
                        <td><%= user.getWarehouseId() != 0 ? user.getWarehouseId() : "N/A"%></td>
                        <td>
                            <form action="AdminHandler" method="POST" style="display:inline;">
                                <input type="hidden" name="action" value="delete_userManagement">
                                <input type="hidden" name="userId" value="<%= user.getUserId()%>">
                                <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                            </form>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>

        <!-- Bootstrap JS Bundle -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <!-- JavaScript to Toggle Shop/Warehouse Fields -->
        <script>
                                function toggleIdFields() {
                                    const role = document.getElementById("role").value;
                                    const shopIdField = document.getElementById("shopIdField");
                                    const warehouseIdField = document.getElementById("warehouseIdField");
                                    const shopIdSelect = document.getElementById("shopId");
                                    const warehouseIdSelect = document.getElementById("warehouseId");

                                    if (role === "BakeryShopStaff") {
                                        shopIdField.style.display = "block";
                                        warehouseIdField.style.display = "none";
                                        shopIdSelect.setAttribute("required", "required");
                                        warehouseIdSelect.removeAttribute("required");
                                    } else if (role === "WarehouseStaff") {
                                        shopIdField.style.display = "none";
                                        warehouseIdField.style.display = "block";
                                        warehouseIdSelect.setAttribute("required", "required");
                                        shopIdSelect.removeAttribute("required");
                                    } else {
                                        shopIdField.style.display = "none";
                                        warehouseIdField.style.display = "none";
                                        shopIdSelect.removeAttribute("required");
                                        warehouseIdSelect.removeAttribute("required");
                                    }
                                }
        </script>
        <!-- jQuery Script for Search -->
        <script>
            $(document).ready(function () {
                // Search functionality
                $("#searchInput").on("keyup", function () {
                    var value = $(this).val().toLowerCase();
                    var searchOption = $("#searchOption").val(); // Get selected search option

                    $("#userTable tbody tr").filter(function () {
                        // Get column values
                        var username = $(this).find("td:nth-child(2)").text().toLowerCase(); // Username (2nd column)
                        var role = $(this).find("td:nth-child(4)").text().toLowerCase(); // Role (4th column)
                        var shopId = $(this).find("td:nth-child(5)").text().toLowerCase(); // Shop ID (5th column)
                        var warehouseId = $(this).find("td:nth-child(6)").text().toLowerCase(); // Warehouse ID (6th column)

                        // Toggle row visibility based on search option and input
                        if (searchOption === "username") {
                            $(this).toggle(username.indexOf(value) > -1);
                        } else if (searchOption === "role") {
                            $(this).toggle(role.indexOf(value) > -1);
                        } else if (searchOption === "shopId") {
                            $(this).toggle(shopId.indexOf(value) > -1);
                        } else if (searchOption === "warehouseId") {
                            $(this).toggle(warehouseId.indexOf(value) > -1);
                        } else {
                            // Search all columns
                            $(this).toggle(
                                    username.indexOf(value) > -1 ||
                                    role.indexOf(value) > -1 ||
                                    shopId.indexOf(value) > -1 ||
                                    warehouseId.indexOf(value) > -1
                                    );
                        }
                    });
                });
            });
        </script>
    </body>
</html>