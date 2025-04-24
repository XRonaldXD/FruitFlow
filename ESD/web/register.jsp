<%-- 
    Document   : register
    Created on : 2025年4月21日, 下午4:08:45
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="./style/index.css" rel="stylesheet">
    </head>
    <body>
        <div class="register-form">
            <a href="./login.jsp">Back</a>
            <h2 class="text-center">Register</h2>
            <!-- Display alert messages -->
            <%
                String message = (String) request.getAttribute("message");
                String alertType = (String) request.getAttribute("alertType");
                if (message != null && alertType != null) {
            %>
            <div class="alert alert-<%= alertType%> alert-dismissible fade show" role="alert">
                <%= message%>
            </div>
            <% }%>

            <p class="text-center">
                <button class="btn btn-primary" data-toggle="collapse" data-target="#shopStaff" aria-expanded="false" aria-controls="shopStaff" onclick="toggleCollapse('warehouseStaff')">
                    Bakery Shop Staff
                </button>
                <button class="btn btn-primary" data-toggle="collapse" data-target="#warehouseStaff" aria-expanded="false" aria-controls="warehouseStaff" onclick="toggleCollapse('shopStaff')">
                    Warehouse Staff
                </button>
            </p>
            <div class="collapse" id="shopStaff">
                <div class="card card-body">
                    <form action="RegisterHandler" method="POST">
                        <input type="hidden" id="shopStaff" name="role" value="BakeryShopStaff">
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" class="form-control" id="username" name="username" placeholder="Enter your username" required>
                        </div>
                        <div class="form-group">
                            <label for="email">Email address</label>
                            <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" required>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required>
                        </div>
                        <div class="form-group">
                            <label for="shopID">shopID</label>
                            <input type="text" class="form-control" id="shopID" name="shopID" placeholder="Enter your shopID" required>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Register</button>
                    </form>
                </div>
            </div>
            <div class="collapse" id="warehouseStaff">
                <div class="card card-body">
                    <form action="RegisterHandler" method="POST">
                        <input type="hidden" id="warehouseStaff" name="role" value="WarehouseStaff">
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" class="form-control" id="username" name="username" placeholder="Enter your username" required>
                        </div>
                        <div class="form-group">
                            <label for="email">Email address</label>
                            <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" required>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required>
                        </div>
                        <div class="form-group">
                            <label for="warehouseID">warehouseID</label>
                            <input type="text" class="form-control" id="warehouseID" name="warehouseID" placeholder="Enter your warehouseID" required>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Register</button>
                    </form>
                </div>
            </div>

        </div>
        <script>
            function toggleCollapse(id) {
                $('#' + id).collapse('hide');
            }
        </script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
