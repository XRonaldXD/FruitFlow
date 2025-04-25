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
                <a class="navbar-brand" href="./dashboard.jsp">Senior Management Dashboard</a>
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
                    <p class="card-text">Role: Senior Management</p>
                </div>
            </div>
            <div class="card mb-4">
                <div class="card-body">
                    <h3 class="mb-4">Actions</h3>
                    <div class="row row-cols-1 row-cols-md-3 g-4">
                        <!-- View Reserve Needs -->
                        <div class="col">
                            <a href="../AdminHandler?action=viewReserveNeeds" class="text-decoration-none text-dark">
                                <div class="card h-100 text-center bg-primary text-white">
                                    <div class="card-body">
                                        <h5 class="card-title">View Reserve Needs</h5>
                                        <p class="card-text">Check the reserve needs for fruits in the warehouse.</p>
                                    </div>
                                </div>
                            </a>
                        </div>

                        <!-- View Consumption Records -->
                        <div class="col">
                            <a href="../ConsumptionHandler?action=view_consumptionRecords" class="text-decoration-none text-dark">
                                <div class="card h-100 text-center bg-success text-white">
                                    <div class="card-body">
                                        <h5 class="card-title">View Consumption Records</h5>
                                        <p class="card-text">View detailed records of fruit consumption.</p>
                                    </div>
                                </div>
                            </a>
                        </div>

                        <!-- Manage Users -->
                        <div class="col">
                            <a href="../AdminHandler?action=viewUserManagement" class="text-decoration-none text-dark">
                                <div class="card h-100 text-center bg-warning text-dark">
                                    <div class="card-body">
                                        <h5 class="card-title">Manage Users</h5>
                                        <p class="card-text">Add, edit, or remove users in the system.</p>
                                    </div>
                                </div>
                            </a>
                        </div>

                        <!-- Manage Fruit Types -->
                        <div class="col">
                            <a href="../AdminHandler?action=view_fruitManagement" class="text-decoration-none text-dark">
                                <div class="card h-100 text-center bg-info text-white">
                                    <div class="card-body">
                                        <h5 class="card-title">Manage Fruit Types</h5>
                                        <p class="card-text">Add, edit, or remove fruit types in the system.</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <!-- Bootstrap JS Bundle -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>