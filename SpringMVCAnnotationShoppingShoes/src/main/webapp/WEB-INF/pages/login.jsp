<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/style.css">
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp" />
	<div class="page-title">Login (For Customer, Employee, Manager)</div>
	<div class="login-container">
		<h3>Enter username and password</h3>
		<br>

		<!-- /login?error=true -->
		<c:if test="${param.error == 'true'}">
			<div style="color: red; margin: 10px 0px;">

				Login Failed!!!<br /> Reason :
				${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}

			</div>
		</c:if>

		<form
			action="${pageContext.request.contextPath}/j_spring_security_check"
			method="POST">
			<table>
				<tr>
					<td>User Name*</td>
					<td><input name="userName"></td>
				</tr>
				<tr>
					<td>Password*</td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" value="Login" /> <input type="reset"
						value="Reset" /></td>
				</tr>
			</table>
			<span class="error-message">${error }</span>
		</form>
	</div>
</body>
</html>
