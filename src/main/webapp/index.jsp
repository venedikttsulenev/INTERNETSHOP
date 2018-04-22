<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;
charset=UTF-8" pageEncoding="UTF-8" import="com.epam.internetshop.services.manager.ServiceFactory" import="com.epam.internetshop.domain.Product"%>
<html>
<head>
<title>Electronic Store. EPAM Lab</title>
<!-- Custom Theme files -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- //Custom Theme files -->

<!-- js -->
<script src="js/jquery.min.js"></script>
<!-- //js -->

<!-- web fonts -->
<link href='//fonts.googleapis.com/css?family=Glegoo:400,700' rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
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

	<div class="modal fade" id="myModal88" tabindex="-1" role="dialog" aria-labelledby="myModal88"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;</button>
					<h4 class="modal-title" id="myModalLabel">Don't Wait, Login now!</h4>
				</div>
				<div class="modal-body modal-body-sub">
					<div class="row">
						<div class="col-md-8 modal_body_left modal_body_left1" style="border-right: 1px dotted #C2C2C2;padding-right:3em;">
							<div class="sap_tabs">
								<div id="horizontalTab" style="display: block; width: 100%; margin: 0px;">
									<ul>
										<li class="resp-tab-item" aria-controls="tab_item-0"><span>Sign in</span></li>
										<li class="resp-tab-item" aria-controls="tab_item-1"><span>Sign up</span></li>
									</ul>
									<div class="tab-1 resp-tab-content" aria-labelledby="tab_item-0">
										<div class="facts">
											<div class="register">
												<form action="login" method="post">
													<input type="hidden" name="command" value="login">
													<input name="login" placeholder="Login" type="text" required="required">
													<input name="password" placeholder="Password" type="password" required="required">
													<div class="sign-up">
														<input type="submit" value="Sign in"/>
													</div>
												</form>
											</div>
										</div>
									</div>
									<div class="tab-2 resp-tab-content" aria-labelledby="tab_item-1">
										<div class="facts">
											<div class="register">
												<form action="register" method="post">
                                                    <input type="hidden" name="command" value="register">
													<input placeholder="Login" name="login" type="text" required="required">
													<input placeholder="Password" name="password" type="password" required="required">
													<input placeholder="Confirm Password" name="password2" type="password" required="required">
													<div class="sign-up">
														<input type="submit" value="Create Account"/>
													</div>
												</form>
											</div>
										</div>
									</div>
								</div>
							</div>
							<script src="js/easyResponsiveTabs.js" type="text/javascript"></script>
							<script type="text/javascript">
								$(document).ready(function () {
									$('#horizontalTab').easyResponsiveTabs({
										type: 'default', //Types: default, vertical, accordion
										width: 'auto', //auto or any width like 600px
										fit: true   // 100% fit in a container
									});
								});
							</script>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$('#myModal88').modal('show');
	</script>
	<!-- header modal -->
	<!-- header -->
	<div class="header" id="home1">
		<div class="container">
			<div class="w3l_login">
				<a href="#" data-toggle="modal" data-target="#myModal88"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></a>
			</div>
			<div class="w3l_logo">
				<h1><a href="index.jsp">Electronic Store<span>Your stores. Your place.</span></a></h1>
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
	<br/>
	<br/>
	<br/>
	<jsp:scriptlet>
        request.setAttribute("productsList", ServiceFactory.newProductService().getAll());
    </jsp:scriptlet>

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
								<c:choose>
                                    <c:when test="${prod.count > 0}">
                                        <a href="" data-toggle="modal" data-target="#myModal88"/>
                                        <button class="btn btn-sm btn-danger">Add to cart</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button class="btn btn-sm btn-danger" disabled>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Out&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
                                    </c:otherwise>
                                </c:choose>
							</td>
						</tr>
						</c:forEach>
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
			<div class="container">
				<p>&copy; 2018 Electronic Store. EPAM Lab</p>
			</div>
		</div>
	</div>
	<!-- //footer -->
</body>
</html>