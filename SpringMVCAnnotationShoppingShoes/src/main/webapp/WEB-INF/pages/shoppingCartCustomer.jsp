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
	<div class="cointainer-fluid">
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp" />
		<div class="row page-title">Customer Information</div>

		<form:form modelAttribute="customerForm"
			action="${pageContext.request.contextPath}/shoppingCartCustomer"
			method="POST">
			<table>
				<tr>
					<td><label for="name">Name</label> *</td>
					<td><form:input path="name" class="form-control" /></td>
					<td><form:errors path="name" class="error-message" /></td>
				</tr>
				<tr>
					<td><label for="email">Email *</label></td>
					<td><form:input path="email" class="form-control" /></td>
					<td><form:errors path="email" class="error-message" /></td>
				</tr>
				<tr>
					<td><label for="phone">Phone *</label></td>
					<td><form:input path="phone" class="form-control" /></td>
					<td><form:errors path="phone" class="error-message" /></td>
				</tr>

				<tr>

					<td><label for="address">Address *</label></td>
					<td><form:input path="address" class="form-control" /></td>
					<td><form:errors path="address" class="error-message" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" class="btn btn-primary"
						value="Submit" /> <input type="reset" class="btn btn-warning"
						value="Reset" /></td>
				</tr>
			</table>
		</form:form>
		<jsp:include page="_footer.jsp" />
	</div>
</body>
</html>
