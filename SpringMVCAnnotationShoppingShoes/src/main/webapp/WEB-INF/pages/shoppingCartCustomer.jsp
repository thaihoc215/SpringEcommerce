<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
<meta charset="UTF-8">
<title>Customer Information</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/style.css">
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp" />
	<div class="page-title">Customer Information</div>

	<form:form modelAttribute="customerForm"
		action="${pageContext.request.contextPath}/shoppingCartCustomer"
		method="POST">
		<table>
			<tr>
				<td>Name *</td>
				<td><form:input path="name" /></td>
				<td><form:errors path="name" class="error-message" /></td>
			</tr>
			<tr>
				<td>Email *</td>
				<td><form:input path="email" /></td>
				<td><form:errors path="email" class="error-message" /></td>
			</tr>
			<tr>
				<td>Phone *</td>
				<td><form:input path="phone" /></td>
				<td><form:errors path="phone" class="error-message" /></td>
			</tr>

			<tr>
				<td>Address *</td>
				<td><form:input path="address" /></td>
				<td><form:errors path="address" class="error-message" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Submit" /> <input type="reset"
					value="Reset" /></td>
			</tr>
		</table>
	</form:form>
	<jsp:include page="_footer.jsp" />
</body>
</html>
