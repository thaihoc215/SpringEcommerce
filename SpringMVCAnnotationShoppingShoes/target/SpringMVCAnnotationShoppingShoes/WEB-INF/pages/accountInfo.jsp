<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
			<ul>
				<li>User Name: ${pageContext.request.userPrincipal.name }</li>
				<li>Role:
					<ul>
						<c:forEach items="${userDetails.authorities }" var="auth">
							<li>${auth.authority }</li>
						</c:forEach>
					</ul>
				</li>
			</ul>
		</div>
		<jsp:include page="_footer.jsp" />
	</div>
</body>
</html>
