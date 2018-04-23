<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: matty
  Date: 19.04.2018
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"
         import="com.epam.internetshop.services.manager.ServiceFactory" %>
<html>
<head>
    <title>Profile</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- //Custom Theme files -->
    <!-- font-awesome icons -->
    <link href="css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons -->
    <!-- js -->
    <script src="js/jquery.min.js"></script>
    <!-- //js -->
    <!-- web fonts -->
    <link href='//fonts.googleapis.com/css?family=Glegoo:400,700' rel='stylesheet' type='text/css'>
    <link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
          rel='stylesheet' type='text/css'>
    <!-- //web fonts -->
    <!-- start-smooth-scrolling -->
    <script type="text/javascript">
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();
                $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1000);
            });
        });
    </script>
    <!-- //end-smooth-scrolling -->
</head>
<body>
<!-- for bootstrap working -->
<script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
<!-- //for bootstrap working -->

<!-- header modal -->
<!-- header -->
<div class="header" id="home1">
    <div class="container">
        <div class="w3l_logo">
            <h1 style="margin-left: 50px"><a href="logined.jsp">Electronic
                Store<span>Your stores. Your place.</span></a></h1>
        </div>
        <form action="process" method="post">
            <input type="hidden" name="command" value="logout"/>
            <button class="w3view-cart" type="submit" name="submit" value="">
                <i style="color: white" class="icons bs-glyphicons glyphicon glyphicon-log-out" aria-hidden="true"></i>
            </button>
            <br/>
            <br/>
        </form>
        <c:if test="${!user.isBlackListed()}">
            <div class="cart cart box_1">
                <form action="bucket.jsp" method="post" class="last">
                    <button class="w3view-cart" type="submit" name="submit" value=""><i class="fa fa-cart-arrow-down"
                                                                                        aria-hidden="true"></i></button>
                </form>
            </div>
        </c:if>
    </div>
</div>
<!-- //header -->
<!-- banner -->
<div class="banner banner10">
    <div class="container">
        <h2>Profile</h2>
    </div>
</div>
<!-- //banner -->
<div class="header">
    <div class="container">
        <div class="w31_logo">
            <h1>User name: ${user.login}</h1>
            <c:if test="${user.isBlackListed()}">
                You were put in blacklist by administrator
            </c:if>
        </div>
    </div>
</div>
<br/>
<br/>
<div class="container">
    <div class="grid_3 grid_5 agile">
        <h3 class="w3ls-hdg">Add some money</h3>
        <div class="bs-example w3layouts" data-example-id="default-navbar">
            <nav class="navbar navbar-default">
                Balance: ${user.account}<br/>
                <c:if test="${message != null}">
                    ${message}<br/>
                    <c:remove var="message" scope="session"/>
                </c:if>
                <div class="container-fluid">
                    <form class="navbar-form navbar-left" action="process" method="post">
                        <input type="hidden" name="command" value="addMoney">
                        <div class="form-group w3-agile">
                            <input type="number" min="1" max="20000" class="form-control" placeholder="Amount"
                                   name="amount">
                        </div>
                        <button type="submit" class="btn btn-sm btn-danger">Add</button>
                    </form>
                </div>
            </nav>
        </div>
    </div>
</div>

<br/>
<br/>
<c:if test="${user.isAdmin()}">
    <%request.setAttribute("userList", ServiceFactory.newUserService().getAllUsers());%>
    <div class="container mb-4">
        <h3 class="w3ls-hdg">Black list</h3>
        <div class="row">
            <div class="col-12">
                <div class="table-responsive">
                    <table class="table table-striped" style="width: 92%; margin: auto">
                        <thead>
                        <tr>
                            <th scope="col" style="color: black">Name</th>
                            <th scope="col" style="color: black">Is blacklisted</th>
                            <th scope="col" style="color: black"></th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${userList}" var="users">
                            <tr>
                                <td>${users.login}</td>
                                <c:choose>
                                    <c:when test="${users.isBlackListed()}">
                                        <td style="font-weight: bold">Yes</td>
                                        <td class="text-right">
                                            <form action="process" method="post">
                                                <input type="hidden" name="command" value="unblack">
                                                <input type="hidden" name="userLogin" value="${users.login}">
                                                <button class="btn btn-sm btn-danger">Remove from BL</button>
                                            </form>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>No</td>
                                        <td class="text-right">
                                            <form action="process" method="post">
                                                <input type="hidden" name="command" value="black">
                                                <input type="hidden" name="userLogin" value="${users.login}">
                                                <button class="btn btn-sm btn-danger">Add to BL</button>
                                            </form>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="typo codes">
        <div class="container">
            <div class="grid_3 grid_4">
                <h3 class="w3ls-hdg">Add product</h3>
                <c:if test="${newProductMessage != null}">
                    ${newProductMessage}
                    <br/>
                    <c:remove var="newProductMessage" scope="session"/>
                </c:if>
                <div class="tab-content">
                    <div class="tab-pane active" id="horizontal-form">
                        <form class="form-horizontal" action="process" method="post">
                            <input type="hidden" name="command" value="newProduct">
                            <div class="form-group">
                                <label for="mediuminput1" class="col-sm-2 control-label">Product</label>
                                <div class="col-sm-8">
                                    <input type="text" name="productName" class="form-control1" id="mediuminput1"
                                           placeholder="Product">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="mediuminput2" class="col-sm-2 control-label">Description</label>
                                <div class="col-sm-8">
                                    <input type="text" name="productDescription" class="form-control1" id="mediuminput2"
                                           placeholder="Description">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="mediuminput3" class="col-sm-2 control-label">Available</label>
                                <div class="col-sm-8">
                                    <input type="number" name="productAvailable" class="form-control1" id="mediuminput3"
                                           placeholder="Available">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="mediuminput" class="col-sm-2 control-label">Price</label>
                                <div class="col-sm-8">
                                    <input type="number" name="productPrice" class="form-control1" id="mediuminput"
                                           placeholder="Price">
                                </div>
                            </div>
                            <input class="btn btn-sm btn-danger" type="submit" value="Send">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container mb-4">
        <div class="row">
            <div class="col-12">
                <h3 class="w3ls-hdg">Change item count</h3>
                <div class="table-responsive">
                    <table style="width: 88%; margin: auto" class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col" style="color: black">Name</th>
                            <th scope="col" style="color: black">Description</th>
                            <th scope="col" style="color: black">Available</th>
                            <th scope="col" style="color: black"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            request.setAttribute("productsList", ServiceFactory.newProductService().getAll());
                        %>
                        <c:forEach items="${productsList}" var="prod">
                            <tr>
                                <td>${prod.name}</td>
                                <td>${prod.description}</td>
                                <form action="process" method="post">
                                <td>
                                    <input type="hidden" name="command" value="changeCount">
                                    <div class="form-group w3-agile">
                                        <input type="number" min="0" class="form-control" placeholder="${prod.count}"
                                               name="productCount">
                                        <input type="hidden" name="productId" value="${prod.id}"/>
                                    </div>
                                </td>
                                <td><button class="btn btn-sm btn-danger">Change</button></td>
                                </form>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</c:if>
<!-- footer -->
<div class="footer">

    <div class="footer-copy">
        <div class="footer-copy1">
            <div class="footer-copy-pos">
                <a href="#home1" class="scroll"><img src="images/arrow.png" alt=" " class="img-responsive"/></a>
            </div>
        </div>
        <div class="container">
            <p>&copy; 2018 Electronic Store. EPAM Lab</p>
        </div>
    </div>
</div>
<!-- //footer -->
</body>
</html>
