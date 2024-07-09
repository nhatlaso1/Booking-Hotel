<%-- 
    Document   : login
    Created on : Oct 14, 2021, 9:18:59 PM
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
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <form action="MainController" method="POST">
            <div class="position-absolute top-50 start-50 translate-middle">
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Username</label>
                    <input type="text" name="Username" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Password</label>
                    <input type="password" name="Password" class="form-control" id="exampleInputPassword1">
                </div>
                <c:if test="${not empty requestScope.LOGIN_MSG}">

                    <p style="color: red">${requestScope.LOGIN_MSG}</p>
                </c:if>
                <c:if test="${not empty requestScope.CREATE_MSG}">
                    <p style="color: red">${requestScope.CREATE_MSG}</p>
                </c:if>
                <c:if test="${not empty requestScope.VIEWDETAIL_MSG}">
                    <p style="color: red">${requestScope.VIEWDETAIL_MSG}</p>
                </c:if>
                <c:if test="${not empty requestScope.CART_MSG}">
                    <p style="color: red">${requestScope.CART_MSG}</p>
                </c:if>
                <c:if test="${not empty requestScope.VIEWORDER_MSG}">
                    <p style="color: red">${requestScope.VIEWORDER_MSG}</p>
                </c:if>
                <c:if test="${not empty requestScope.ORDERDETAILS_MSG}">
                    <p style="color: red">${requestScope.ORDERDETAILS_MSG}</p>
                </c:if>
                <c:if test="${not empty requestScope.FEEDBACK_MSG}">
                    <p style="color: red">${requestScope.FEEDBACK_MSG}</p>
                </c:if>
                <c:if test="${not empty requestScope.CHECKDISCOUNT_MSG}">
                    <p style="color: red">${requestScope.CHECKDISCOUNT_MSG}</p>
                </c:if>






                <br>
                <button type="submit" name ="btnAction" value="loginServlet" class="btn btn-primary">Submit</button>
                <a class="btn btn-primary" href="MainController?btnAction=registerPage">Register</a>
                <a class="btn btn-danger" href="search?searchValue=">Home Page</a>
            </div>
        </form>

    </body>
</html>
