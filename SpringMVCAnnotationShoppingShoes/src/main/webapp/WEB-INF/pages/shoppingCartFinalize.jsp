<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Shopping Cart Finalize</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/style.css">

</head>
<body>
	<div class="container-fluid">
		<jsp:include page="_header.jsp" />

		<jsp:include page="_menu.jsp" />

		<div class="page-title">Finalize</div>

		<div class="container">
			<h3>Thank you for Order</h3>
			<!-- Hien thi ma so mua hang -->
			Your order number is: ${lastOrderedCart.orderNum}

		</div>

		<jsp:include page="_footer.jsp" />
	</div>
</body>
</html>