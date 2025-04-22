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
    <title>Bakery Shop Staff Dashboard</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Bakery Dashboard</a>
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
                <p class="card-text">Role: Bakery Shop Staff</p>
            </div>
        </div>

        <!-- Actions Section -->
        <div class="card mb-4">
            <div class="card-body">
                <h3 class="card-title">Actions</h3>
                <div class="d-grid gap-2">
                    <a href="../ViewStock?shopId=<jsp:getProperty name="user" property="shopId"/>" class="btn btn-primary">View Stock</a>
                    <a href="../ReserveFruitsHandler?action=view" class="btn btn-success">Reserve Fruits</a>
                    <a href="../BorrowFruitsHandler?action=view" class="btn btn-warning">Borrow Fruits</a>
                    <a href="../ViewReservations" class="btn btn-info">View Reservations</a>
                    <a href="../UpdateStockHandler?action=view" class="btn btn-secondary">Update Stock Levels</a>
                </div>
            </div>
        </div>

        <!-- Notifications Section -->
        <div class="card">
            <div class="card-body">
                <h3 class="card-title">Notifications</h3>
                <ul class="list-group">
                    <li class="list-group-item">Reservation #123 approved.</li>
                    <li class="list-group-item">Borrow request #456 pending.</li>
                    <li class="list-group-item">Stock for "Apples" is running low.</li>
                </ul>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>