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
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp" />
	<fmt:setLocale value="en_US" scope="session" />

	<div class="page-title">My Cart</div>

	<!-- truong hop gio hang ko co san pham -->
	<!-- Check empty cho san danh sach san pham trong cartform cau hinh o controller -->
	<c:if test="${empty cartForm or empty cartForm.cartLines }">
		<h2>There is no items in Cart</h2>
		<a href="${pageContext.request.contextPath}/productList">Show
			Product List</a>
	</c:if>

	<c:if test="${not empty cartForm and not empty cartForm.cartLines   }">
		<form:form method="POST" modelAttribute="cartForm"
			action="${pageContext.request.contextPath }/shoppingCart">

		</form:form>
	</c:if>
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
