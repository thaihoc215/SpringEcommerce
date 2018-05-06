<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<div class="menu-container">
	<a href="${pageContext.request.contextPath}/">Home</a> |<a
		href="${pageContext.request.contextPath}/productList">Product List</a>
	<security:authorize access="!hasAnyRole('ROLE_MANAGER')">
		|<a href="${pageContext.request.contextPath}/shoppingCart">My Cart</a>

	</security:authorize>
	<!-- Kiểm tra điều kiện đủ quyền không mới hiển thị  -->
	<!-- hien thi danh sach don hang -->
	<security:authorize access="hasAnyRole('ROLE_EMPLOYEE')">
		|<a href="${pageContext.request.contextPath}/orderList"> Order
			List </a>

	</security:authorize>

	<security:authorize access="hasRole('ROLE_MANAGER')">
		<!-- Quan ly san pham -->
		|<a href="${pageContext.request.contextPath}/product"> Create
			Product </a>
		<!-- quan ly account -->
			|<a href="${pageContext.request.contextPath}/manageAccount">
			Manage Account </a>
	</security:authorize>
</div>