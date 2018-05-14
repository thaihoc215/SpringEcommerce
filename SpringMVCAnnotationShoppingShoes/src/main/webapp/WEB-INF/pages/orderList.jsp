<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order List</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/style.css">

</head>
<body>
	<div class="container-fluid">
		<jsp:include page="_header.jsp" />
		<jsp:include page="_menu.jsp" />

		<fmt:setLocale value="en_US" scope="session" />

		<div class="page-title">Order List</div>

		<div>Total Order Count: ${paginationResult.totalRecords}</div>

		<table class="table table-condensed" style="width: 100%">
			<tr class="success">
				<th>Order Num</th>
				<th>Order Date</th>
				<th>Customer Name</th>
				<th>Customer Address</th>
				<th>Customer Email</th>
				<th>Amount</th>
				<th>View</th>
			</tr>
			<c:forEach items="${paginationResult.list}" var="orderInfo">
				<tr>
					<td>${orderInfo.orderNum}</td>
					<td><fmt:formatDate value="${orderInfo.orderDate}"
							pattern="dd-MM-yyyy HH:mm" /></td>
					<td>${orderInfo.customerName}</td>
					<td>${orderInfo.customerAddress}</td>
					<td>${orderInfo.customerEmail}</td>
					<td style="color: red;"><fmt:formatNumber
							value="${orderInfo.amount}" type="currency" /></td>
					<td><a class="btn btn-info"
						href="${pageContext.request.contextPath}/order?orderId=${orderInfo.id}">
							Details</a></td>
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
	</div>
</body>
</html>