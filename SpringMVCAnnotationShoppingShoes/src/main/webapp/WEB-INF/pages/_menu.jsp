<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="menu-container">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Brand</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/">Home <span
							class="sr-only">(current)</span></a></li>
					<li><a href="${pageContext.request.contextPath}/productList">Product
							List</a></li>
					<security:authorize access="!hasAnyRole('ROLE_MANAGER')">
						<li><a href="${pageContext.request.contextPath}/shoppingCart">My
								Cart</a></li>
					</security:authorize>

					<!-- Kiểm tra điều kiện đủ quyền không mới hiển thị  -->
					<!-- hien thi danh sach don hang -->
					<security:authorize access="hasAnyRole('ROLE_EMPLOYEE')">
						<li><a href="${pageContext.request.contextPath}/orderList">
								Order List </a></li>
					</security:authorize>

					<security:authorize access="hasRole('ROLE_MANAGER')">
						<!-- Quan ly san pham -->
						<li><a href="${pageContext.request.contextPath}/product">
								Create Product </a></li>
						<!-- quan ly account -->
						<li><a
							href="${pageContext.request.contextPath}/manageAccount">
								Manage Account </a></li>
					</security:authorize>
				</ul>
				<form:form action="${pageContext.request.contextPath}/productList"
					method="GET" class="navbar-form navbar-right">
					<div class="form-group">
						<input class="form-control" type="search"
							placeholder="Product Name" aria-label="Product Name" name="likeName">
					</div>

					<button class="btn btn-default" type="submit">Search</button>
				</form:form>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

</div>