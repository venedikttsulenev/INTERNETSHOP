<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
         import="com.epam.internetshop.services.manager.ServiceFactory"
         import="java.util.HashMap"
         import="com.epam.internetshop.domain.Product" %>
<html lang="en">
<head>
    <title>Electronic Store. EPAM Lab</title>
    <!-- Custom Theme files -->
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- //Custom Theme files -->
    <!-- font-awesome icons -->
    <link href="css/font-awesome.css" rel="stylesheet"/>
    <!-- //font-awesome icons -->
    <!-- js -->
    <script src="js/jquery.min.js"></script>
    <!-- //js -->
    <!-- web fonts -->
    <link href='//fonts.googleapis.com/css?family=Glegoo:400,700' rel='stylesheet' type='text/css'/>
    <link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
          rel='stylesheet' type='text/css'/>
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
        <div class="w3l_login">
            <a href="profile.jsp"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></a>
        </div>
        <div class="w3l_logo">
            <h1><a href="logined.jsp">Electronic Store<span>Your stores. Your place.</span></a></h1>
        </div>
        <form action="process" method="post">
            <input type="hidden" name="command" value="logout"/>
            <button class="w3view-cart" type="submit" name="submit" value="">
                <i style="color: white" class="icons bs-glyphicons glyphicon glyphicon-log-out" aria-hidden="true"></i>
            </button>
            <br/>
            <br/>
        </form>
        <div class="cart cart box_1">
            <form action="bucket.jspx" method="post" class="last">
                <button class="w3view-cart" type="submit" name="submit" value=""><i class="fa fa-cart-arrow-down"
                                                                                    aria-hidden="true"></i></button>
            </form>
        </div>
    </div>
</div>
<!-- //header -->
<!-- banner -->
<div class="banner">
    <div class="container">
        <h3>Electronic Store, <span>Special Offers</span></h3>
    </div>
</div>
<!-- //banner -->
<%
    request.setAttribute("productsList", ServiceFactory.newProductService().getAll());
    HashMap<Product, Long> bucketList = (HashMap<Product, Long>) session.getAttribute("bucketList");
    if (bucketList == null)
        bucketList = new HashMap<>();
    session.setAttribute("bucketList", bucketList);
%>
<br/>
<br/>
<br/>
<div class="container mb-4">
    <div class="row">
        <div class="col-12">
            <div class="table-responsive">
                <table style="width: 88%; margin: auto" class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col" style="color: black">Name</th>
                        <th scope="col" style="color: black">Descritption</th>
                        <th scope="col" style="color: black">Available</th>
                        <th scope="col" style="color: black">Price</th>
                        <th scope="col" style="color: black"></th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${productsList}" var="prod">
                        <tr>
                            <td>${prod.name}</td>
                            <td>${prod.description}</td>
                            <td>${prod.count}</td>
                            <td>${prod.price}</td>
                            <td class="text-center">
                                <form action="process" method="post">
                                    <input type="hidden" name="command" value="addToCart"/>
                                    <input type="hidden" name="productId" value="${prod.id}"/>
                                    <input type="hidden" name="productCount" value="1"/>
                                    <!--<a href="#" data-toggle="modal" data-target="#myModal88"/>-->
                                    <c:choose>
                                        <c:when test="${prod.count > 0}">
                                            <button class="btn btn-sm btn-danger">Add to cart</button>
                                        </c:when>
                                        <c:otherwise>
                                            <button class="btn btn-sm btn-danger" disabled>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Out&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
                                        </c:otherwise>
                                    </c:choose>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
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