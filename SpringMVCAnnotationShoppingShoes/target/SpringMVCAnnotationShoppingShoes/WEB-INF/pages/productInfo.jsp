<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta charset="UTF-8">
<title>View product information</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/style.css">
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp" />
	<div class="page-title">PRODUCT INFO</div>
	<form:form modelAttribute="productForm" method="POST"
		enctype="multipart/form-data">
		<table style="text-align: left;">
			<tr>
				<td>Image</td>
				<td><img class="product-img"
					src="${pageContext.request.contextPath}/productImage?code=${productForm.code}"
					width="100" /></td>
				<td></td>
			</tr>
			<tr>
				<td>Code: ${productForm.code }</td>
			</tr>
			<tr>
				<td>Name: ${productForm.name }</td>
			</tr>

			<tr>
				<td>Price: <fmt:formatNumber value="${productForm.price}"
						type="currency" /></td>

			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Buy" /> <input type="button"
					onclick="history.back()" value="Back" /></td>
			</tr>
		</table>
	</form:form>
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
