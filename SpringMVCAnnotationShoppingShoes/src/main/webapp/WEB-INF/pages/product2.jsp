<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta charset="UTF-8">
<title>PRODUCT (MANAGER)</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/style.css">

</head>
<body>
	<div class="container-fluid">
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp" />
		<div class="row page-title">Product info</div>
		<c:if test="${not empty errorMessage }">
			<div class="error-message">${errorMessage}</div>
		</c:if>
		<form:form modelAttribute="productForm" method="POST"
			enctype="multipart/form-data">
			<table style="text-align: left;">
				<tr>
					<td>Code *</td>
					<!-- kiem tra xem ma code san pham co empty hay khong, neu co thi 
				hien thi o nhap code ung voi viec them moi san pham
				neu khong thi dua va form hidden -->
					<td style="color: red;"><c:if
							test="${not empty productForm.code}">
							<form:hidden path="code" />
                       ${productForm.code}
                  </c:if> <c:if test="${empty productForm.code}">
							<form:input path="code" class="form-control" />
							<form:hidden path="newProduct" />
						</c:if></td>
					<td><form:errors path="code" class="error-message" /></td>
				</tr>
				<tr>
					<td>Name *</td>
					<td><form:input path="name" class="form-control" /></td>
					<td><form:errors path="name" class="error-message" /></td>
				</tr>

				<tr>
					<td>Price *</td>
					<td><form:input path="price" class="form-control" /></td>
					<td><form:errors path="price" class="error-message" /></td>
				</tr>
				<tr>
					<td>Thumbnail Image</td>
					<td>
						<!-- <img class="product-img"
					src="${pageContext.request.contextPath}/productImage?code=${productForm.code}"
					width="100" /> --> <c:if test="${not empty productForm.code}">
							<img class="thumbnail-img"
								src="${pageContext.request.contextPath}/img/${productForm.code}.jpg" />
						</c:if>
					</td>
					<td></td>
				</tr>
				<!-- <tr>
				<td>Upload Thumbnail Image</td>
				<td><form:input type="file" path="fileDatas" /></td>
				<td></td>
			</tr> -->
				<tr>
					<td>Image</td>
					<td>
						<!-- <img class="product-img"
					src="${pageContext.request.contextPath}/productImage?code=${productForm.code}"
					width="100" /> --> <c:if test="${not empty productForm.code}">
							<img class="product-img"
								src="${pageContext.request.contextPath}/img/${productForm.code}.jpg" />
						</c:if>
					</td>
					<td></td>
				</tr>
				<tr>
					<td>Upload Image</td>
					<td><label class="btn btn-default" for="my-file-selector">
							<form:input type="file" path="fileDatas" id="my-file-selector"
								style="display: none;"
								onchange="$('#upload-file-info').html(this.files[0].name)" />
							Choose File
					</label> <span class='label label-info' id="upload-file-info">No
							file chosen</span> <!--  <form:input type="file" path="fileDatas" /> --></td>
					<td></td>
				</tr>


				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" value="Save" class="btn btn-primary" />
						<input type="reset" value="Reset" class="btn btn-warning"
						onclick="$('#upload-file-info').html('No file chosen')" /></td>
				</tr>
			</table>
		</form:form>

		<jsp:include page="_footer.jsp"></jsp:include>
	</div>
</body>
</html>
