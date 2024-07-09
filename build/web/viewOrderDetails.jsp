<%-- 
    Document   : viewOrderDetails
    Created on : Oct 17, 2021, 9:57:57 PM
    Author     : Phước Hà
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Order Details</title>
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
                        <li><a href="MainController?btnAction=ViewOrder"><span>View Order</span></a></li>
                        <li><a href="#" class="active" ><span>Order Detail</span></a></li>
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
                                <p class="right">${requestScope.ORDERDETAILS_MSG}</p>
                                
                                <p class="right">${requestScope.FEEDBACK_MSG}</p>

                            </div>
                            <!-- End Box Head -->
                            <!-- Table -->
                            <div class="table">
                                <c:if test="${requestScope.LIST_ORDERDETAILS != null}">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <th>No.</th>
                                            <th>HotelName</th>
                                            <th>RoomName</th>
                                            <th>Quantity</th>
                                            <th>Night</th>
                                            <th>CheckIn</th>
                                            <th>CheckOut</th>
                                            <th>RoomPrice</th>
                                            <th>Action</th>


                                        </tr>

                                        <c:forEach var="o" items="${requestScope.LIST_ORDERDETAILS}" varStatus="count">
                                            <form action="MainController">
                                                <tr>
                                                    <td>${count.count}</td>
                                                    <td><h3><a href="#">${o.hotelName}</a></h3></td>
                                                    <td>${o.roomName}</td>

                                                    <td>${o.orderQuantity}</td> 
                                                    <td>${o.night}</td>
                                                    <td>${o.checkIn} </td>
                                                    <td>${o.checkOut}</td>
                                                    <td>${o.roomPrice} .vnđ</td>
                                                    <td>
                                                        <a href="MainController?btnAction=feedBackPage&roomNo=${o.roomNo}&orderId=${o.orderId}" class="ico edit">FeedBack</a>
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
                    <c:if test="${requestScope.FEEDBACK_ROOM != null}">
                        <div id="sidebar">
                            <!-- Box -->
                            <div class="box">
                                <form action="MainController">
                                    <!-- Box Head -->
                                    <div class="box-head">
                                        <h2>Feed Back</h2>
                                    </div>

                                    <p>Feed back For RoomId = ${requestScope.FEEDBACK_ROOM}</p>

                                    <input type="hidden" name="orderId" value="${requestScope.ORDER_ID}">
                                    <input type="hidden" name="roomNo" value="${requestScope.FEEDBACK_ROOM}">
                                    <select style="width: 100%" name="value">
                                        <option value="1">1 Not Sactified</option>
                                        <option value="2">2 Star</option>
                                        <option value="3">3 Star</option>
                                        <option value="4">4 Star</option>
                                        <option value="5">5 Normal</option>
                                        <option value="6">6 Star</option>
                                        <option value="7">7 Star</option>
                                        <option value="8">8 Star</option>
                                        <option value="9">9 Star</option>
                                        <option value="10">10 Very Sactified</option>
                                    </select>
                                    <!-- Date Picker -->

                                    <!-- End Box Head-->
                                    <!-- Sort -->

                                    <!-- End Sort -->
                                    <button name="btnAction" value="FeedBackServlet" class="btn btn-primary" style="width: 100%">FeedBack</button>
                                </form>
                            </div>

                            <!-- End Box -->
                        </div>
                    </c:if>
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
