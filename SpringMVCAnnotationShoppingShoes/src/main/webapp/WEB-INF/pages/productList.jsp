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

		<div class="row">
			<c:forEach items="${paginationProducts.list }" var="prInfo">
				<div class="product-preview-container innercard">
					<div class="image">
						<a
							href="${pageContext.request.contextPath}/productInfo?code=${prInfo.code}">
							<img class="product-list-img show" alt="View shoes information"
							src="${pageContext.request.contextPath}/img/${prInfo.code}.jpg" />
						</a>
					</div>
					<div class="product-info-wrapper">
						<div class="product-info-inner clear clearfix">
							<div class="color-count cta-link">9 colors</div>
						</div>
						<div class="hc-separator line-dotted-light divider-hor-top"></div>
						<div class="product-info-inner-content clearfix with-badges">
							<a
								href="${pageContext.request.contextPath}/productInfo?code=${prInfo.code}"
								class="link-CQ2627 product-link clearfix "> <span
								class="title">Deerupt Runner Shoes</span> <span class="subtitle">Men's
									Originals</span>
							</a>
						</div>
					</div>

				</div>
			</c:forEach>
		</div>
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
