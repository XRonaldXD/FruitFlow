<%-- 
    Document   : dashboard
    Created on : 2025年4月22日, 下午5:54:26
    Author     : user
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Senior Management Dashboard</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Senior Management Dashboard</a>
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
        <!-- Welcome Section -->
        <div class="card mb-4">
            <div class="card-body">
                <h3 class="card-title">Welcome, <%= session.getAttribute("username") %>!</h3>
                <p class="card-text">Role: Senior Management</p>
            </div>
        </div>

        <!-- Actions Section -->
        <div class="card mb-4">
            <div class="card-body">
                <h3 class="card-title">Actions</h3>
                <div class="d-grid gap-2">
                    <a href="viewAnalytics.jsp" class="btn btn-primary">View Analytics</a>
                    <a href="generateReports.jsp" class="btn btn-success">Generate Reports</a>
                    <a href="manageUsers.jsp" class="btn btn-warning">Manage Users</a>
                    <a href="manageFruits.jsp" class="btn btn-info">Manage Fruit Types</a>
                </div>
            </div>
        </div>

        <!-- Notifications Section -->
        <div class="card">
            <div class="card-body">
                <h3 class="card-title">Notifications</h3>
                <ul class="list-group">
                    <li class="list-group-item">New user account created: Warehouse Staff.</li>
                    <li class="list-group-item">Reserve needs report for April is ready.</li>
                    <li class="list-group-item">Consumption report for Q1 is available.</li>
                </ul>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>