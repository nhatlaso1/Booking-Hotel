<%-- 
    Document   : viewOrder
    Created on : Oct 17, 2021, 9:26:40 PM
    Author     : Phước Hà
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Order</title>
        <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
    </head>
    <body>
        <!-- Header -->
        <div id="header">
            <div class="shell">
                <!-- Logo + Top Nav -->
                <div id="top">
                    <h1><a href="#">Order</a></h1>
                    <div id="top-navigation">

                        <c:if test="${sessionScope.ACC != null}">
                            <c:if test="${sessionScope.ACC.roleId eq '1'}">
                                <c:redirect url="login.html"></c:redirect>
                            </c:if>
                            <c:if test="${sessionScope.ACC.roleId ne '1'}">
                                Welcome, ${sessionScope.ACC.userId}
                                <a href="MainController?btnAction=logout">Log out</a>
                            </c:if>
                            <span>|</span>

                        </c:if>
                        <c:if test="${sessionScope.ACC == null}">
                            <span>|</span>
                            <a href="MainController?btnAction=loginPage">Log In</a>
                        </c:if>




                    </div>
                </div>
                <!-- End Logo + Top Nav -->
                <!-- Main Nav -->
                <div id="navigation">
                    <ul>
                        <li><a href="MainController?btnAction=" ><span>Home Page</span></a></li>
                        <li><a href="MainController?btnAction=ViewCart"><span>View Cart</span></a></li>
                        <li><a href="MainController?btnAction=ViewOrder"class="active" ><span>View Order</span></a></li>
                    </ul>
                </div>
                <!-- End Main Nav -->
            </div>
        </div>
        <!-- End Header -->
        <!-- Container -->
        <div id="container">
            <div class="shell">
                <!-- Small Nav -->
                <!-- End Small Nav -->
                <!-- Message OK -->
                <!-- End Message OK -->
                <!-- Message Error -->
                <!-- End Message Error -->
                <br />
                <!-- Main -->
                <div id="main">
                    <div class="cl">&nbsp;</div>
                    <!-- Content -->
                    <div id="content">
                        <!-- Box -->
                        <div class="box">
                            <!-- Box Head -->
                            <div class="box-head">
                                <h2 class="left">Current Cart</h2>
                                <p class="right">${requestScope.VIEWORDER_MSG}</p>

                            </div>
                            <!-- End Box Head -->
                            <!-- Table -->
                            <div class="table">
                                <c:if test="${requestScope.LIST_ORDER != null}">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <th>No.</th>
                                            <th>Order Id</th>
                                            <th>UserId</th>

                                            <th>Order Date</th>
                                            <th>Total</th>
                                            <th>Status</th>
                                            <th width="110" class="ac">Action</th>
                                        </tr>

                                        <c:forEach var="o" items="${requestScope.LIST_ORDER}" varStatus="count">
                                            <form action="MainController">
                                                <tr>
                                                    <td>${count.count}</td>
                                                    <td><h3><a href="#">${o.orderId}</a></h3></td>
                                                    <td>${o.userId}</td>

                                                    <td>${o.orderDate}</td> 
                                                    <td>${o.total} .vnđ</td>
                                                    <td>${o.status}</td>

                                                    <td>
                                                        <a href="MainController?btnAction=viewOrderDetails&orderId=${o.orderId}" class="ico edit">Details</a>
                                                        <a href="MainController?btnAction=deleteOrder&orderId=${o.orderId}" class="ico del"onclick="return confirm('Are you sure delete?')">Delete</a>

                                                    </td>

                                                </tr>
                                            </form>
                                        </c:forEach>


                                    </table>
                                </c:if>

                                <!-- Pagging -->

                                <!-- End Pagging -->
                            </div>
                            <!-- Table -->
                        </div>
                        <!-- End Box -->
                        <!-- Box -->
                        <!-- End Box -->
                    </div>
                    <!-- End Content -->
                    <!-- Sidebar -->



                    <!-- End Sidebar -->
                    <div class="cl">&nbsp;</div>
                </div>
                <!-- Main -->
            </div>
        </div>
        <!-- End Container -->
        <!-- Footer -->
        <div id="footer">
            <div class="shell"> <span class="left">&copy; 2010 - CompanyName</span> <span class="right"> Design by <a href="http://chocotemplates.com">Chocotemplates.com</a> </span> </div>
        </div>
        <!-- End Footer -->
    </body>
</html>
