<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="UTF-8">
<title>Create Account</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/style.css">
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp" />
		<div class="row page-title">Create Account</div>
		<form:form modelAttribute="accountInfo" method="POST">
			<table>
				<tr>
					<td><label for="userName" class="control-label">User
							User Name*</label></td>
					<td><form:input path="userName" class="form-control" /></td>
					<td><form:errors path="userName" class="error-message" /></td>
				</tr>
				<tr>
					<td><label for="email" class="control-label">Email*</label></td>
					<td><form:input path="email" class="form-control" /></td>
					<td><form:errors path="email" class="error-message" /></td>
				</tr>
				<tr>
					<td><label for="name" class="control-label">Name*</label></td>
					<td><form:input path="name" class="form-control" /></td>
					<td><form:errors path="name" class="error-message" /></td>
				</tr>
				<tr>
					<td><label for="password" class="control-label">Password*</label></td>
					<td><form:input path="password" type="password"
							class="form-control" /></td>
					<td><form:errors path="password" class="error-message" /></td>
				</tr>
				<tr>
					<td><label for="roles">Role</label></td>
					<td><select name="roles" class="form-control">
							<option value="" selected></option>
							<option value="CUSTOMER">CUSTOMER</option>
							<option value="EMPLOYEE">EMPLOYEE</option>
							<option value="MANAGER">MANAGER</option>
					</select></td>
				</tr>
				<tr>
					<td><label for="phoneNumber" class="control-label">Phone
							Number</label></td>
					<td><form:input path="phoneNumber" class="form-control" /></td>
					<td><form:errors path="phoneNumber" class="error-message" /></td>
				</tr>
				<tr>
					<td><label for="address" class="control-label">Address</label></td>
					<td><form:input path="address" class="form-control" /></td>
					<td><form:errors path="address" class="error-message" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" class="btn btn-primary"
						value="Create" /> <input type="reset" class="btn btn-warning"
						value="Reset" /> <input type="button" onclick="history.back()"
						class="btn btn-default" value="Back" /></td>
					<td></td>
				</tr>
			</table>
		</form:form>
		<jsp:include page="_footer.jsp" />
	</div>
</body>
</html>
