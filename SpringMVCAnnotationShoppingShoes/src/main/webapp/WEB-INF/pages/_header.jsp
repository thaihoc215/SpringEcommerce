<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/style.css">

<div class="header-container">
	<div class="site-name">DEMO</div>
	<div class="header-bar">
		<!-- hiển thị tên người dùng và link logout nếu tồn tại tài khoản đăng nhập  -->
		<c:if test="${pageContext.request.userPrincipal.name != null}">
		
		Hello
		<!--  di chuyen den account info -->
			<a href="${pageContext.request.contextPath}/accountInfo">
				${pageContext.request.userPrincipal.name} </a>
         &nbsp;|&nbsp;
         <a href="${pageContext.request.contextPath}/logout">Logout</a>
		</c:if>
		<!-- Tạo button login -->
		<c:if test="${pageContext.request.userPrincipal.name == null}">
			<a href="${pageContext.request.contextPath}/login">Login</a>
            &nbsp;|&nbsp;
            <a href="${pageContext.request.contextPath}/signup">Signup</a>
		</c:if>
	</div>
</div>