<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/style.css">
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp" />
		<div class="row page-title">Login (For Customer, Employee,
			Manager)</div>
		<div class="row login-container">
			<h3>Enter username and password</h3>
			<br>

			<!-- /login?error=true -->
			<c:if test="${param.error == 'true'}">
				<div style="color: red; margin: 10px 0px;">

					Login Failed!!!<br /> Reason :
					${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}

				</div>
			</c:if>

			<form class="form-horizontal"
				action="${pageContext.request.contextPath}/j_spring_security_check"
				method="POST">
				<table>
					<tr>
						<td><label for="userName" class="control-label">User
								Name*</label></td>
						<td><input name="userName" class="form-control"
							required="required"
							></td>
					</tr>
					<tr>
						<td><label for="password" class="control-label">Password*</label></td>
						<td><input type="password" name="password"
							class="form-control" required="required"
							></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><input type="submit" class="btn btn-primary"
							value="Login" /> <input type="reset" class="btn btn-warning"
							value="Reset" /></td>
					</tr>
				</table>
				<span class="error-message">${error }</span>
			</form>
		</div>
		<jsp:include page="_footer.jsp"></jsp:include>
	</div>

</body>
</html>
