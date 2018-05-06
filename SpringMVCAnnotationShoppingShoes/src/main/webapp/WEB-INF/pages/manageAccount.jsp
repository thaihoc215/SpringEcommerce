<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Accounts</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/style.css">

</head>
<body>

	<jsp:include page="_header.jsp" />
	<jsp:include page="_menu.jsp" />

	<fmt:setLocale value="en_US" scope="session" />

	<div class="page-title">Account List</div>
	<div align="right">
		<a href="${pageContext.request.contextPath}/createAccount"> Create
			Account </a> | <a href="#" onclick="history.back()"> Back </a>
	</div>

	<div>Total Account: ${paginationResult.totalRecords}</div>

	<table border="1" style="width: 100%">
		<tr>
			<th>User Name</th>
			<th>Role</th>
			<th>Email</th>
			<th>Phone</th>
			<th>Name</th>
			<th>Address</th>
			<th>Date Created</th>
			<th></th>
			<th></th>
		</tr>
		<c:forEach items="${paginationResult.list}" var="accountInfo">
			<tr>
				<td>${accountInfo.userName}</td>
				<td>${accountInfo.userRole}</td>
				<td>${accountInfo.email}</td>
				<td>${accountInfo.phoneNumber}</td>
				<td>${accountInfo.name}</td>
				<td>${accountInfo.address}</td>
				<td><fmt:formatDate value="${accountInfo.dateCreate}"
						pattern="dd-MM-yyyy HH:mm" /></td>
				<td><a
					href="${pageContext.request.contextPath}/order?orderId=${accountInfo.userName}">
						Info</a></td>
				<td><c:if test="${accountInfo.active == 'true'}">
						<a
							href="${pageContext.request.contextPath}/updateAccStatus?userName=${accountInfo.userName}">
							Deactive</a>
					</c:if>
					<c:if test="${accountInfo.active == 'false'}">
						<a
							href="${pageContext.request.contextPath}/updateAccStatus?userName=${accountInfo.userName}">
							Active</a>
					</c:if></td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${paginationResult.totalPages > 1}">
		<div class="page-navigator">
			<c:forEach items="${paginationResult.navigationPages}" var="page">
				<c:if test="${page != -1 }">
					<a href="orderList?page=${page}" class="nav-item">${page}</a>
				</c:if>
				<c:if test="${page == -1 }">
					<span class="nav-item"> ... </span>
				</c:if>
			</c:forEach>

		</div>
	</c:if>




	<jsp:include page="_footer.jsp" />

</body>
</html>