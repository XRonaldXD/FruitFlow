<%-- 
    Document   : fruitManagement
    Created on : 2025年4月25日, 下午4:43:53
    Author     : user
--%>

<%@page import="java.util.List"%>
<%@page import="bean.Fruit"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Fruit Management</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
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
            <h1 class="mb-4">Fruit Management</h1>
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
            <!-- Add Fruit Form -->
            <form action="AdminHandler" method="POST" class="mb-4">
                <input type="hidden" name="action" value="add_fruit">
                <div class="row">
                    <div class="col-md-5">
                        <input type="text" name="fruitName" class="form-control" placeholder="Fruit Name" required>
                    </div>
                    <div class="col-md-5">
                        <input type="text" name="sourceLocation" class="form-control" placeholder="Source Location" required>
                    </div>
                    <div class="col-md-2">
                        <button type="submit" class="btn btn-primary">Add Fruit</button>
                    </div>
                </div>
            </form>

            <!-- Fruit Table -->
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Fruit ID</th>
                        <th>Fruit Name</th>
                        <th>Source Location</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Fruit> fruits = (List<Fruit>) request.getAttribute("fruits");
                        if (fruits != null) {
                            for (Fruit fruit : fruits) {
                    %>
                    <tr>
                        <td><%= fruit.getFruitId()%></td>
                        <td><%= fruit.getFruitName()%></td>
                        <td><%= fruit.getSourceLocation()%></td>
                        <td>
                            <button type="button" class="btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#updateFruitModal"
                                    data-fruitid="<%= fruit.getFruitId()%>"
                                    data-fruitname="<%= fruit.getFruitName()%>"
                                    data-sourcelocation="<%= fruit.getSourceLocation()%>">
                                Update
                            </button>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="4">No Fruits found.</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
        <!-- Update Fruit Modal -->
        <div class="modal fade" id="updateFruitModal" tabindex="-1" aria-labelledby="updateFruitModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="AdminHandler" method="POST">
                        <input type="hidden" name="action" value="update_fruit">
                        <input type="hidden" id="modalFruitId" name="fruitId">
                        <div class="modal-header">
                            <h5 class="modal-title" id="updateFruitModalLabel">Update Fruit</h5>                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="mb-3">
                                <label for="modalFruitName" class="form-label">Fruit Name</label>
                                <input type="text" class="form-control" id="modalFruitName" name="fruitName" required>
                            </div>
                            <div class="mb-3">
                                <label for="modalSourceLocation" class="form-label">Source Location</label>
                                <input type="text" class="form-control" id="modalSourceLocation" name="sourceLocation" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                            <button type="submit" class="btn btn-primary">Update</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Bootstrap JS Bundle -->

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <!-- jQuery Script for Modal -->
        <script>
            $(document).ready(function () {
                // Populate modal fields and update the modal title when the Update button is clicked
                $('#updateFruitModal').on('show.bs.modal', function (event) {
                    var button = $(event.relatedTarget); // Button that triggered the modal
                    var fruitId = button.data('fruitid'); // Get the Fruit ID
                    var fruitName = button.data('fruitname'); // Get the Fruit Name
                    var sourceLocation = button.data('sourcelocation'); // Get the Source Location

                    // Debugging: Log the Fruit ID
                    //console.log('Fruit ID:', fruitId);

                    // Populate the modal fields
                    $('#modalFruitId').val(fruitId);
                    $('#modalFruitName').val(fruitName);
                    $('#modalSourceLocation').val(sourceLocation);

                    // Update the modal title with the Fruit ID
                    $('#updateFruitModalLabel').text('Update Fruit ID ' + fruitId);
                });
            });
        </script>
    </body>
</html>