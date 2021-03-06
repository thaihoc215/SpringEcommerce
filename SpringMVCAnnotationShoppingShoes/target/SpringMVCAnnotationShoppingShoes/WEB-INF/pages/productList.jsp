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
	<div class="container-fluid">
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp" />
		<fmt:setLocale value="en_US" scope="session" />

		<div class="row page-title">Product List</div>

		<c:forEach items="${paginationProducts.list }" var="prInfo">
			<div class="product-preview-container">
				<ul>
					<li><a
						<security:authorize access="hasRole('ROLE_MANAGER')">href="${pageContext.request.contextPath}/product?code=${prInfo.code}"</security:authorize>
						<security:authorize access="!hasRole('ROLE_MANAGER')">href="${pageContext.request.contextPath}/productInfo?code=${prInfo.code}"</security:authorize>>
							<!--<img class="product-list-img" alt="View shoes information"
						src="${pageContext.request.contextPath}/productImage?code=${prInfo.code}" />
						 --> <img class="product-list-img" alt="View shoes information"
							src="${pageContext.request.contextPath}/img/${prInfo.code}.jpg"
							width="100" />
					</a></li>
					<li>Code: ${prInfo.code }</li>
					<li>Name: ${prInfo.name }</li>
					<li>Price: <fmt:formatNumber value="${prInfo.price}"
							type="currency" /></li>
					<security:authorize access="!hasRole('ROLE_MANAGER')">
						<li><a class="btn btn-primary"
							href="${pageContext.request.contextPath }/buyProduct?code=${prInfo.code }">Buy
								Now</a> <a class="btn btn-primary"
							href="${pageContext.request.contextPath }/productInfo?code=${prInfo.code }">Info</a></li>
					</security:authorize>
					<!-- For Manager edit Product -->
					<security:authorize access="hasRole('ROLE_MANAGER')">
						<li><a class="btn btn-danger"
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
	</div>
</body>
</html>
