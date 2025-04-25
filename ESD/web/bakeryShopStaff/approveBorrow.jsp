<%-- 
    Document   : approveBorrow
    Created on : 2025年4月25日, 下午6:10:22
    Author     : user
--%>

<%@page import="java.util.List"%>
<%@page import="bean.Borrow"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Approve Borrow Requests</title>
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
            <h1 class="mb-4">Approve Borrow Requests</h1>
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
            <!-- Borrow Requests Table -->
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Borrow ID</th>
                        <th>Request From</th>
                        <th>Fruit Name</th>
                        <th>Quantity</th>
                        <th>Borrow Date</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Borrow> borrows = (List<Borrow>) request.getAttribute("borrows");
                        if (borrows != null && !borrows.isEmpty()) {
                            for (Borrow borrow : borrows) {
                    %>
                    <tr>
                        <td><%= borrow.getBorrowId()%></td>
                        <td><%= borrow.getToShopName()%></td>
                        <td><%= borrow.getFruitName()%></td>
                        <td><%= borrow.getQuantity()%></td>
                        <td><%= borrow.getBorrowDate()%></td>
                        <td><%= borrow.getStatus()%></td>
                        <td>
                            <form action="BorrowFruitsHandler" method="POST" style="display:inline;">
                                <input type="hidden" name="action" value="approve">
                                <input type="hidden" name="borrowId" value="<%= borrow.getBorrowId()%>">
                                <input type="hidden" name="toShopId" value="<%= borrow.getToShopId()%>">
                                <button type="submit" class="btn btn-success btn-sm">Approve</button>
                            </form>
                            <form action="BorrowFruitsHandler" method="POST" style="display:inline;">
                                <input type="hidden" name="action" value="reject">
                                <input type="hidden" name="borrowId" value="<%= borrow.getBorrowId()%>">
                                <input type="hidden" name="toShopId" value="<%= borrow.getToShopId()%>">
                                <button type="submit" class="btn btn-danger btn-sm">Reject</button>
                            </form>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="7">No borrow requests found.</td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>
    </body>
</html>