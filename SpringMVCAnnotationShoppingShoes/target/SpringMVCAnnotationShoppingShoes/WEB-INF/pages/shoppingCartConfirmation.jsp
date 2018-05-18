<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta charset="UTF-8">
<title>Shopping Cart Confirmation</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/style.css">
</head>
<body>
	<div class="cointainer-fluid">
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp" />
		<fmt:setLocale value="en_US" scope="session" />

		<div class="row page-title">Confirmation</div>

		<div class="customer-info-container">
			<h3>Customer Infomation</h3>
			<ul>
				<li>Name: ${myCart.customerInfo.name}</li>
				<li>Email: ${myCart.customerInfo.email}</li>
				<li>Phone: ${myCart.customerInfo.phone}</li>
				<li>Address: ${myCart.customerInfo.address}</li>
			</ul>
			<h3>Cart Summary:</h3>
			<ul>
				<li>Quantity: ${myCart.quantityTotal}</li>
				<li>Total: <span class="total"> <fmt:formatNumber
							value="${myCart.amountTotal}" type="currency" />
				</span></li>
			</ul>
		</div>

		<form
			action="${pageContext.request.contextPath}/shoppingCartConfirmation"
			method="POST">
			<!-- Back and edit info in cart -->
			<a class="navi-item"
				href="${pageContext.request.contextPath}/shoppingCart">Edit Cart</a>
			<!-- Back and edit customer info -->
			<a class="navi-item"
				href="${pageContext.request.contextPath}/shoppingCartCustomer">Edit
				Customer Info</a>

			<!-- Send/Save -->
			<input type="submit" value="Send" class="button-send-sc" />
		</form>
		<!-- All item info in Cart -->
		<div class="container">

			<c:forEach items="${myCart.cartLines}" var="cartLineInfo">
				<div class="product-preview-container">
					<ul>
						<li><img class="product-image"
							src="${pageContext.request.contextPath}/productImage?code=${cartLineInfo.productInfo.code}" /></li>
						<li>Code: ${cartLineInfo.productInfo.code} <input
							type="hidden" name="code"
							value="${cartLineInfo.productInfo.code}" />
						</li>
						<li>Name: ${cartLineInfo.productInfo.name}</li>
						<li>Price: <span class="price"> <fmt:formatNumber
									value="${cartLineInfo.productInfo.price}" type="currency" />
						</span>
						</li>
						<li>Quantity: ${cartLineInfo.quantity}</li>
						<li>Subtotal: <span class="subtotal"> <fmt:formatNumber
									value="${cartLineInfo.amount}" type="currency" />
						</span>
						</li>
					</ul>
				</div>
			</c:forEach>

		</div>
		<jsp:include page="_footer.jsp" />
	</div>
</body>
</html>
