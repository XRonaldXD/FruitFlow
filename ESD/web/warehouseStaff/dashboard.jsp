<%-- 
    Document   : dashboard
    Created on : 2025年4月22日, 下午4:16:35
    Author     : user
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Warehouse Staff Dashboard</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="./dashboard.jsp">Warehouse Dashboard</a>
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
            <!-- Welcome Section -->
            <div class="card mb-4">
                <div class="card-body">
                    <jsp:useBean id="user" class="bean.User" scope="session"></jsp:useBean>
                    <h3 class="card-title">Welcome, <jsp:getProperty name="user" property="username"/>!</h3>
                    <p class="card-text">Role: Warehouse Staff</p>
                    <p class="card-text">Warehouse ID: <jsp:getProperty name="user" property="warehouseId"/></p>
                </div>
            </div>
            <div class="container mt-4">
                <!-- Actions Section -->
                <h3 class="mb-4">Actions</h3>
                <div class="row row-cols-1 row-cols-md-3 g-4">
                    <!-- View/Update Stock Levels -->
                    <div class="col">
                        <a href="../UpdateStockHandler?action=view_warehouse" class="text-decoration-none text-dark">
                            <div class="card h-100 text-center bg-primary text-white">
                                <div class="card-body">
                                    <h5 class="card-title">View/Update Stock Levels</h5>
                                    <p class="card-text">Manage and update stock levels in the warehouse.</p>
                                </div>
                            </div>
                        </a>
                    </div>

                    <!-- Reserve Fruits -->
                    <div class="col">
                        <a href="../ReserveFruitsHandler?action=reserveFruit_warehouse" class="text-decoration-none text-dark">
                            <div class="card h-100 text-center bg-secondary text-white">
                                <div class="card-body">
                                    <h5 class="card-title">Reserve Fruits</h5>
                                    <p class="card-text">Reserve fruits for future use or special orders.</p>
                                </div>
                            </div>
                        </a>
                    </div>

                    <!-- Approve Reservations -->
                    <div class="col">
                        <a href="../ReserveFruitsHandler?action=view_warehouse" class="text-decoration-none text-dark">
                            <div class="card h-100 text-center bg-success text-white">
                                <div class="card-body">
                                    <h5 class="card-title">Approve Reservations</h5>
                                    <p class="card-text">Approve or reject fruit reservations from bakery shops.</p>
                                </div>
                            </div>
                        </a>
                    </div>

                    <!-- Arrange Deliveries to Warehouse -->
                    <div class="col">
                        <a href="../DeliveriesHandler?action=view" class="text-decoration-none text-dark">
                            <div class="card h-100 text-center bg-warning text-dark">
                                <div class="card-body">
                                    <h5 class="card-title">Arrange Deliveries to Warehouse</h5>
                                    <p class="card-text">Organize deliveries of fruits to the warehouse.</p>
                                </div>
                            </div>
                        </a>
                    </div>

                    <!-- Arrange Deliveries to Bakery Shop -->
                    <div class="col">
                        <a href="../DeliveriesHandler?action=view_toShop" class="text-decoration-none text-dark">
                            <div class="card h-100 text-center bg-info text-white">
                                <div class="card-body">
                                    <h5 class="card-title">Arrange Deliveries to Bakery Shop</h5>
                                    <p class="card-text">Organize deliveries of fruits to bakery shops.</p>
                                </div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Bootstrap JS Bundle -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>