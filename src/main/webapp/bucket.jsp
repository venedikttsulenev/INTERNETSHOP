<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
    <html>
    <head>
        <title>Electronic Store. Bucket. EPAM Lab</title>
        <!-- Custom Theme files -->
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
        <!-- //Custom Theme files -->
        <!-- font-awesome icons -->
        <link href="css/font-awesome.css" rel="stylesheet"/>
        <!-- //font-awesome icons -->
        <!-- js -->
        <script src="js/jquery.min.js"></script>
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
                <a href="profile.jsp"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></a>
            </div>
            <div class="w3l_logo">
                <h1 ><a href="logined.jsp">Electronic Store<span>Your stores. Your place.</span></a></h1>
            </div>
            <form action="process" method="post">
                <input type="hidden" name="command" value="logout"/>
                <button class="w3view-cart" type="submit" name="submit" value="">
                    <i style="color: white" class="icons bs-glyphicons glyphicon glyphicon-log-out" aria-hidden="true"></i>
                </button>
                <br/>
                <br/>
            </form>
        </div>
    </div>
    <!-- //header -->
    <!-- banner -->
    <div class="banner banner10" style="margin: 25px 0">
        <div class="container">
            <h2>Cart</h2>
        </div>
    </div>
    <!-- //banner -->

    <div class="container mb-4">
        <div class="row">
            <div class="col-12">
    <c:if test="${message != null}">
        ${message}
        <br/>
        <c:remove var="message" scope="session"/>
    </c:if>
    <c:set var="name" value="bucketList"/>
    <c:choose>
        <c:when test="${empty sessionScope[name]}">
            Your cart is empty
            <br/>
        </c:when>
        <c:otherwise>
            <form action="process" method="post">
                <input type="hidden" name="command" value="buy"/>
                <table style="width: 92%; margin: auto" class="table table-striped">
                    <tr>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Include in order</th>
                    </tr>
                    <c:forEach items="${bucketList}" var="ent" varStatus="status">
                        <!-- bucketList comes as session attribte -->
                        <!-- bucketList is a HashMap <Product, Long>, where Long value is count -->
                        <tr>
                            <td>${ent.key.name}</td>
                            <td>${ent.key.description}</td>
                            <td>${ent.key.price}</td>
                            <td>
                                <input type="checkbox" name="productIncluded${status.index}" value="true"
                                       checked="checked"/>
                            </td>
                            <td>
                                <input type="number" class="form-control" min="1" max="${ent.key.count}" name="productCount${status.index}"
                                       value="${ent.value}"/>
                                <input type="hidden" name="productId${status.index}" value="${ent.key.id}"/>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <button class="btn btn-sm btn-danger" type="submit">Place your order</button>
            </form>
            <form action="process" method="post">
                <input type="hidden" name="command" value="clearCart"/>
                <button class="btn btn-sm btn-danger" type="submit">Clear cart</button>
            </form>
        </c:otherwise>
    </c:choose>
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
            <div class="container">
                <p>&copy; 2018 Electronic Store. EPAM Lab</p>
            </div>
        </div>
    </div>
    <!-- //footer -->

    </body>
    </html>