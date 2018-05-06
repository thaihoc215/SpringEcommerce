<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="UTF-8">
<title>Create Account</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/style.css">
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp" />
	<div class="page-title">Create Account</div>
	<form:form modelAttribute="accountInfo" method="POST">
		<table border="0">
			<tr>
				<td>User Name</td>
				<td><form:input path="userName" /></td>
				<td><form:errors path="userName" class="error-message" /></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><form:input path="email" /></td>
				<td><form:errors path="email" class="error-message" /></td>
			</tr>
			<tr>
				<td>Name</td>
				<td><form:input path="name" /></td>
				<td><form:errors path="name" class="error-message" /></td>
			</tr>
			<%-- <tr>
				<td>First Name</td>
				<td><form:input path="firstName" /></td>
				<td><form:errors path="firstName" class="error-message" /></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><form:input path="lastName" /></td>
				<td><form:errors path="lastName" class="error-message" /></td>
			</tr> --%>
			<tr>
				<td>Password</td>
				<td><form:input type="password" path="password" /></td>
				<td><form:errors path="password" class="error-message" /></td>
			</tr>
			<tr>
				<td>Role</td>
				<td>
					<select name="roles" >
						<option value="" selected></option>
						<option value="CUSTOMER">CUSTOMER</option>
						<option value="EMPLOYEE">EMPLOYEE</option>
						<option value="MANAGER">MANAGER</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>Phone Number</td>
				<td><form:input path="phoneNumber" /></td>
				<td><form:errors path="phoneNumber" class="error-message" /></td>
			</tr>
			<tr>
				<td>Address</td>
				<td><form:input path="address" /></td>
				<td><form:errors path="address" class="error-message" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Create" /> <input
					type="reset" value="Reset" /> <input type="button"
					onclick="history.back()" value="Back" /></td>
				<td></td>
			</tr>
		</table>



	</form:form>
</body>
</html>
