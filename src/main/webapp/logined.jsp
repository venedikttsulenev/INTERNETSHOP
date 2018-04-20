<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;
charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="com.epam.internetshop.services.manager.ServiceFactory" import="com.epam.internetshop.domain.Product" import="java.util.HashMap"%>
<html lang="en">
<head>
    <title>Electronic Store a Ecommerce Online Shopping Category Bootstrap Responsive Website Template</title>
    <!-- Custom Theme files -->
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />

    <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
    <link href="css/fasthover.css" rel="stylesheet" type="text/css" media="all" />
    <link href="css/popuo-box.css" rel="stylesheet" type="text/css" media="all" />
    <!-- //Custom Theme files -->
    <!-- font-awesome icons -->
    <link href="css/font-awesome.css" rel="stylesheet"/>
    <!-- //font-awesome icons -->
    <!-- js -->
    <script src="js/jquery.min.js"></script>
    <link rel="stylesheet" href="css/jquery.countdown.css" /> <!-- countdown -->
    <!-- //js -->
    <!-- web fonts -->
    <link href='//fonts.googleapis.com/css?family=Glegoo:400,700' rel='stylesheet' type='text/css'/>
    <link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'/>
    <!-- //web fonts -->
    <!-- start-smooth-scrolling -->
    <script type="text/javascript">
        jQuery(document).ready(function($) {
            $(".scroll").click(function(event){
                event.preventDefault();
                $('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
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
            <a href="profile.jsp" data-toggle="modal" data-target="#myModal88"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></a>
        </div>
        <div class="w3l_logo">
            <h1><a href="logined.jsp">Electronic Store<span>Your stores. Your place.</span></a></h1>
        </div>
        <!--<div class="search">-->
            <!--<input class="search_box" type="checkbox" id="search_box">-->
            <!--<label class="icon-search" for="search_box"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></label>-->
            <!--<div class="search_form">-->
                <!--<form action="#" method="post">-->
                    <!--<input type="text" name="Search" placeholder="Search...">-->
                    <!--<input type="submit" value="Send">-->
                <!--</form>-->
            <!--</div>-->
        <!--</div>-->
        <form action="process" method="post">
            <input type="hidden" name="command" value="logout"/>
            <div class="sign-up">
                <input type="submit" value="Log out"/>
            </div>
        </form>
        <div class="cart cart box_1">
            <form action="bucket.jspx" method="post" class="last">
                <button class="w3view-cart" type="submit" name="submit" value=""><i class="fa fa-cart-arrow-down" aria-hidden="true"></i></button>
            </form>s
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
    HashMap<Long, Long> bucketList = (HashMap<Long, Long>) session.getAttribute("bucketList");
    if (bucketList == null)
        bucketList = new HashMap<Long, Long>();
    session.setAttribute("bucketList", bucketList);
%>

<div class="container mb-4">
    <div class="row">
        <div class="col-12">
            <div class="table-responsive">
                <table class="table table-striped">
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
                    <jsp:scriptlet>
                    Product product = (Product) pageContext.getAttribute("prod");
                    </jsp:scriptlet>
                    <tr>
                        <td>
                            <jsp:expression>product.getName()</jsp:expression>
                        </td>
                        <td>
                            <jsp:expression>product.getDescription()</jsp:expression>
                        </td>
                        <td>
                            <jsp:expression>product.getCount()</jsp:expression>
                        </td>
                        <td>
                            <jsp:expression>product.getPrice()</jsp:expression>
                        </td>
                        <td class="text-right">
                            <form action="process" method="post">
                                <input type="hidden" name="command" value="addToCart"/>
                                <input type="hidden" name="productId" value="${id}">
                                <jsp:element name="input">
                                    <jsp:attribute name="type">hidden</jsp:attribute>
                                    <jsp:attribute name="name">productId</jsp:attribute>
                                    <jsp:attribute name="value">
                                        <jsp:expression>product.getId()</jsp:expression>
                                    </jsp:attribute>
                                </jsp:element>
                                <jsp:element name="input">
                                    <jsp:attribute name="type">hidden</jsp:attribute>
                                    <jsp:attribute name="name">productCount</jsp:attribute>
                                    <jsp:attribute name="value">1</jsp:attribute>
                                </jsp:element>
                                <!--<a href="#" data-toggle="modal" data-target="#myModal88"/>-->
                                <button class="btn btn-sm btn-danger">Add to cart </button>
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
                <a href="#home1" class="scroll"><img src="images/arrow.png" alt=" " class="img-responsive" /></a>
            </div>
        </div>
    </div>
</div>
<!-- //footer -->

</body>
</html>