<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="UTF-8">
<title>Account info</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/style.css">
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp" />
		<div class="page-title">Account Info</div>
		<div class="account-container">
			<!-- <ul>
				<li>User Name: ${pageContext.request.userPrincipal.name }</li>
				<li>Role:
					<ul>
						<c:forEach items="${userDetails.authorities }" var="auth">
							<li>${auth.authority }</li>
						</c:forEach>
					</ul>
				</li>
			</ul>-->

			<form:form modelAttribute="accountInfo" method="POST">
				<table>
					<tr>
						<td><label for="userName" class="control-label">User
								Name*</label></td>
						<td style="color: red;">${accountInfo.userName}<form:input
								path="userName" type="hidden" /></td>
					</tr>
					<tr>
						<td><label for="email" class="control-label">Email*</label></td>
						<td><form:input path="email" class="form-control"
								required="required" /></td>
						<td><form:errors path="email" class="error-message" /></td>
					</tr>
					<tr>
						<td><label for="name" class="control-label">Name*</label></td>
						<td><form:input path="name" class="form-control"
								required="required" /></td>
						<td><form:errors path="name" class="error-message" /></td>
					</tr>
					<tr>
						<td><label for="password" class="control-label">Password*</label></td>
						<td><form:input path="password" type="password"
								class="form-control" required="required" /></td>
						<td><form:errors path="password" class="error-message" /></td>
					</tr>
					<tr>
						<td><label for="roles">Role</label></td>
						<td style="color: red;">${accountInfo.userRole}<form:input
								path="userRole" type="hidden" /></td>
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
							value="Submit" /> <input type="reset" class="btn btn-warning"
							value="Reset" />
						<td></td>
					</tr>
				</table>
			</form:form>
		</div>
		<jsp:include page="_footer.jsp" />
	</div>
</body>
</html>
