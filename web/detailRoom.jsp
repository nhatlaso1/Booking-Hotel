<%-- 
    Document   : detailRoom
    Created on : Oct 16, 2021, 8:33:14 PM
    Author     : Phước Hà
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail Room</title>
        <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
    </head>
    <body>
        <div id="header">
            <div class="shell">
                <!-- Logo + Top Nav -->
                <div id="top">
                    <h1><a href="#">SpringTime</a></h1>
                    <div id="top-navigation"> <c:if test="${sessionScope.ACC != null}">
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
                        </c:if>  </div>
                </div>
                <!-- End Logo + Top Nav -->
                <!-- Main Nav -->
                <div id="navigation">
                    <ul>
                        <li><a href="MainController?btnAction=" ><span>Home Page</span></a></li>
                        <li><a href="MainController?btnAction=ViewCart"><span>View Cart</span></a></li>
                        <li><a href="MainController?btnAction=ViewOrder"><span>View Order</span></a></li>
                        <li><a href="#" class="active" ><span>Detail Room</span></a></li>
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

                        <div class="box">
                            <!-- tblHotel.hotelId, tblHotel.hotelName, roomNo, roomName, typeId, roomPrice -->
                            <div class="box-head">
                                <h2>Detail Room</h2>
                            </div>
                            <!-- End Box Head -->
                            <form action="MainController" method="post">
                                <!-- Form -->
                                <div class="form">
                                    <p> 
                                        <label>hotel Name</label>
                                        <input type="hidden" name="hotelId" class="field size1" value="${requestScope.ROOM_DETAIL.hotelId}" readonly=""/>

                                        <input type="text" name="hotelName" class="field size1" value="${requestScope.ROOM_DETAIL.hotelName}" readonly=""/>
                                    </p>
                                    <p> 
                                        <label>room No</label>
                                        <input type="text" name="roomNo" class="field size1" value="${requestScope.ROOM_DETAIL.roomNo}" readonly=""/>
                                    </p>
                                    <p> 
                                        <label>room Name</label>
                                        <input type="text" name="roomName" class="field size1" value="${requestScope.ROOM_DETAIL.roomName}" readonly=""/>
                                    </p>
                                    <p> 
                                        <label>Quantity</label>
                                        <input type="number" name="quantity" class="field size1" value="${requestScope.ROOM_DETAIL.quantity}"/>
                                    </p>
                                    <p> 
                                        <label>type Id</label>
                                        <input type="text" name="typeId" class="field size1" value="${requestScope.ROOM_DETAIL.typeId}" readonly=""/>
                                    </p>
                                    
                                    <p> 
                                        <label>room Price</label>
                                        <input type="text" name="roomPrice" class="field size1" value="${requestScope.ROOM_DETAIL.price}" readonly=""/>
                                    </p>
                                    <p> 
                                        <label>Check In</label>
                                        <input type="date" name="checkIn" class="field size1" value="${sessionScope.CHECKIN_DATE}"  readonly=""/>
                                    </p>
                                    <p> 
                                        <label>Check Out</label>
                                        <input type="date" name="checkOut" class="field size1" value="${sessionScope.CHECKOUT_DATE}" readonly=""/>
                                    </p>
                                    <p> 
                                        <c:if test="${requestScope.ORDER_MSG != null}">
                                            <label style="color: red">${requestScope.ORDER_MSG}</label>
                                        </c:if>
                                    </p>



                                </div>
                                <!-- End Form -->
                                <!-- Form Buttons -->
                                <div class="buttons">
                                    <input type="submit" name="btnAction" class="button" value="Order Now" />
                                </div>
                                <!-- End Form Buttons -->
                            </form>
                        </div>
                        <!-- End Box -->
                    </div>
                    <!-- End Content -->
                    <!-- Sidebar -->
                    <div id="sidebar">
                        <!-- Box -->
                        <div class="box">
                            <form action="MainController">
                                <!-- Box Head -->
                                <div class="box-head">
                                    <h2>Rating</h2>
                                </div>

                                <c:if test="${requestScope.START_MSG != null}">
                                    ${requestScope.START_MSG}
                                </c:if>

                                <c:if test="${requestScope.START_VALUE != null}">
                                    Rating: ${requestScope.START_VALUE}/10
                                </c:if>
                                <!-- Date Picker -->

                                <hr>
                                <!-- End Box Head-->

                                <!-- End Sort -->
                            </form>
                        </div>

                        <!-- End Box -->
                    </div>
                    <!-- Box -->

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
