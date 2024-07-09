<%-- 
    Document   : registerPage
    Created on : Oct 15, 2021, 12:54:33 PM
    Author     : Phước Hà
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
        <title>Register Page</title>
    </head>
    <body>
        <h1>Register Page</h1>
        <form action="MainController" method="POST">
            <div class="position-absolute top-50 start-50 translate-middle">
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Email</label>
                    <input type="text" name="userId" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
                    <p style="color: red">${requestScope.USER_ERROR.getUserIDError()}</p>
                </div>

                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Password</label>
                    <input type="password" name="Password" class="form-control" id="exampleInputPassword1">
                    <p style="color: red">${requestScope.USER_ERROR.getPasswordError()}</p>
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Re Password</label>
                    <input type="password" name="rePassword" class="form-control" id="exampleInputPassword1">
                    <p style="color: red">${requestScope.USER_ERROR.getConfirmPasswordError()}</p>
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Full Name</label>
                    <input type="text" name="fullName" class="form-control" id="exampleInputPassword1">
                    <p style="color: red">${requestScope.USER_ERROR.getNameError()}</p>
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Address</label>
                    <input type="text" name="address" class="form-control" id="exampleInputPassword1">
                    <p style="color: red">${requestScope.USER_ERROR.getAddress()}</p>
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">phone Number</label>
                    <input type="text" name="phoneNumber" class="form-control" id="exampleInputPassword1">
                    <p style="color: red">${requestScope.USER_ERROR.getPhoneNumberError()}</p>
                </div>


                <br>
                <button type="submit" name ="btnAction" value="CreateServlet" class="btn btn-primary">Create</button>

            </div>
        </form>

    </body>
</html>
