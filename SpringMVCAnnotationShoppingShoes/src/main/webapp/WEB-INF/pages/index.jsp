<html>
<head>
<meta charset="UTF-8">
<title>Shoes Shop Online</title>
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/style.css">
</head>
<body>

	<div class="container-fluid">
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp"></jsp:include>
		<div class="row page-title">ECOMERCE WEB DEMO</div>
		<div class="row page-title">SHOES ONLINE SHOP</div>
		<div class="row">
			<jsp:include page="_categories.jsp"></jsp:include>
			<h3>Demo content</h3>

			<ul>
				<li>Buy online</li>
				<li>Admin pages</li>
				<li>Reports</li>
			</ul>
		</div>
	</div>


	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
