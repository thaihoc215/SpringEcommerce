<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta charset="UTF-8">
<title>Shoes Shopping Cart</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/style.css">
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp" />
		<fmt:setLocale value="en_US" scope="session" />

		<div class="row page-title">My Cart</div>

		<!-- truong hop gio hang ko co san pham -->
		<!-- Check empty cho san danh sach san pham trong cartform cau hinh o controller -->
		<c:if test="${empty cartForm or empty cartForm.cartLines }">
			<h2>There is no items in Cart</h2>
			<a href="${pageContext.request.contextPath}/productList">Show
				Product List</a>
		</c:if>

		<!-- trường hợp có sản phẩm trong giỏ hàng -->
		<c:if test="${not empty cartForm and not empty cartForm.cartLines   }">
			<form:form method="POST" modelAttribute="cartForm"
				action="${pageContext.request.contextPath }/shoppingCart">
				<c:forEach items="${cartForm.cartLines }" var="cartLineInfo"
					varStatus="varStatus">
					<div class="product-preview-container">
						<ul>
							<!-- image -->
							<li><img class="product-image" alt=""
								src="${pageContext.request.contextPath }/productImage?code=${cartLineInfo.productInfo.code}" />
							</li>
							<!-- code -->
							<li>Code:${cartLineInfo.productInfo.code } <!-- Lay theo so thu tu trong cartlines -->
								<form:hidden
									path="cartLines[${varStatus.index}].productInfo.code" />
							</li>
							<!-- name -->
							<li>Name: ${cartLineInfo.productInfo.name}</li>
							<!-- price -->
							<li>Price: <span class="price"> <fmt:formatNumber
										value="${cartLineInfo.productInfo.price}" type="currency" />

							</span></li>
							<!-- quantity -->
							<li><label>Quantity:</label> <form:input
									class="form-control"
									path="cartLines[${varStatus.index}].quantity" /></li>

							<!-- total cost -->
							<li>Subtotal: <span class="subtotal"> <fmt:formatNumber
										value="${cartLineInfo.amount}" type="currency" />

							</span>
							</li>

							<!-- xoa sp ra khoi gio hang -->
							<li><a
								href="${pageContext.request.contextPath}/shoppingCartRemoveProduct?code=${cartLineInfo.productInfo.code}">
									Delete </a></li>
						</ul>
					</div>
				</c:forEach>
				<div style="clear: both"></div>
				<input type="submit" class="btn btn-primary" value="Update Quantity" />
				<a class="btn btn-success"
					href="${pageContext.request.contextPath}/shoppingCartCustomer">Enter
					Customer Info</a>
				<a class="btn btn-default"
					href="${pageContext.request.contextPath}/productList">Continue
					Buy</a>
			</form:form>
		</c:if>
		<jsp:include page="_footer.jsp"></jsp:include>
	</div>
</body>
</html>
