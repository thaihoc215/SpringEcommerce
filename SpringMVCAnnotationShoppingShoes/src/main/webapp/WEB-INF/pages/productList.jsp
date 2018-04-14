<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<html>
<head>
<meta charset="UTF-8">
<title>Shoes Shop Online</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/style.css">
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp" />
	<fmt:setLocale value="en_US" scope="session" />

	<div class="page-title">Product List</div>

	<c:forEach items="${paginationProducts.list }" var="prInfo">
		<div class="product-preview-container">
			<ul>
				<li><img alt=""
					src="${pageContext.request.contextPath}/productImage?code=${prInfo.code}" /></li>
				<li>Code: ${prInfo.code }</li>
				<li>Code: ${prInfo.name }</li>
				<li>Price: <fmt:formatNumber value="${prInfo.price}"
						type="currency" /></li>
				<li><a
					href="${pageContext.request.contextPath }/buyProduct?code=${prInfo.code }">Buy
						Now</a></li>
				<!-- For Manager edit Product -->
				<security:authorize access="hasRole('ROLE_MANAGER')">
					<li><a style="color: red;"
						href="${pageContext.request.contextPath}/product?code=${prInfo.code}">
							Edit Product</a></li>
				</security:authorize>
			</ul>
		</div>
	</c:forEach>

	<c:if test="${paginationProducts.totalPages > 1}">
		<div class="page-navigator">
			<c:forEach items="${paginationProducts.navigationPages }" var="page">
				<c:if test="${page!=-1 }">
					<a href="productList?page=${page }" class="nav-item">${page }</a>
					<c:if test="${page == -1 }">
						<span class="nav-item"> ... </span>
					</c:if>
				</c:if>
			</c:forEach>
		</div>
	</c:if>
	<jsp:include page="_footer.jsp" />
</body>
</html>
