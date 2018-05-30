<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<div class="col-md-3">
	<div class="list-group">
		<p class="list-group-item list-group-item-success">Categories</p>
		<c:forEach items="${categories}" var="cat">
			<a
				href="${pageContext.request.contextPath}/productList?cat=${cat.id}"
				class="list-group-item">${cat.name}</a>
		</c:forEach>
	</div>
</div>
