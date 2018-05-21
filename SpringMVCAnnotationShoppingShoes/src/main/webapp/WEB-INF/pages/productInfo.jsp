<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta charset="UTF-8">
<title>View product information</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp" />
		<div class="page-title">PRODUCT INFO</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-s-12 col-md-8">
					<div class="glass_image_viewer">
						<div class="images-container">
							<img class="product-img"
								src="${pageContext.request.contextPath}/productImage?code=${productForm.code}"
								width="100" />
						</div>
					</div>
				</div>
				<div class="col-s-12 col-md-3">
					<div class="order_information">
						<div class="row">
							<div class="product_category light">
								<div class="subtitle">Category</div>
							</div>
							<div class="product_information">
								<h1 class="product_information_title product_title">${productForm.name }</h1>
								<div class="main">
									<span class="price-big gl-price"><fmt:formatNumber
											value="${productForm.price}" type="currency" /></span>
								</div>
							</div>
						</div>
						<div class="row" style="margin-top: 10px;">
							<form:form modelAttribute="productForm" method="POST">
								<div id="size_selector" class="size_selector col-s-12 col-md-8">
									<select name="shoes-size" class="form-control dropdow_select"
										required="required">
										<option value="" selected>SELECT SIZE</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
									</select>
								</div>
								<div id="quantity-selector"
									class="quantity_selector col-s-12 col-md-3">
									<select name="shoes-number" class="form-control dropdow_select"
										required="required">
										<option value="1" selected>1</option>
										<option value="2">2</option>
										<option value="3">3</option>
									</select>
								</div>
								<div>
									<input type="submit" class="btn btn-primary" value="ADD TO BAG" />
									<input type="button" class="btn btn-default"
										onclick="history.back()" value="BACK" />
								</div>

							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="_footer.jsp"></jsp:include>
	</div>
</body>
</html>
