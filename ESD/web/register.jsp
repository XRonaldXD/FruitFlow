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
                        <input type="hidden" id="shopStaff" name="type" value="1">
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
                            <label for="city">City</label>
                            <select id="city" name="city" class="form-control">
                                <option name="city" value="HongKong" disabled>HongKong</option>
                                <option name="city" value="HongKongIsland">Hong Kong Island</option>
                                <option name="city" value="Kowloon">Kowloon</option>
                                <option name="city" value="NewTerritories">New Territories</option>
                                <option name="city" value="Japan" disabled>Japan</option>
                                <option name="city" value="Tokyo">Tokyo</option>
                                <option name="city" value="Osaka">Osaka</option>
                                <option name="city" value="Nagoya">Nagoya</option>
                                <option name="city" value="USA" disabled>USA</option>
                                <option name="city" value="NewYork">New York</option>
                                <option name="city" value="LosAngeles">Los Angeles</option>
                                <option name="city" value="Chicago">Chicago</option>
                            </select>
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
                        <input type="hidden" id="warehouseStaff" name="type" value="2">
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
                            <label for="country">Country</label>
                            <select id="country" name="country" class="form-control">
                                <option name="country" value="Japan" selected>Japan</option>
                                <option name="country" value="HongKong">Hong Kong</option>
                                <option name="country" value="USA">USA</option>
                            </select>
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
