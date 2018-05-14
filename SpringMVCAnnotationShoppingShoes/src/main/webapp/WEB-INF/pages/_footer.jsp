<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="footer-container">
	@Copy right <a href="//facebook.com/thaihoc1994" target="blank">Thai
		Hoc Ha Nguyen</a>
	<!-- jQuery library -->
</div>
<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->

<!-- Latest compiled JavaScript -->
<!-- <script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.min.js">
	
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/bootstrap.min.js">
	
</script>
<script type="text/javascript">
	$(document).ready(function() {
		var url = window.location;
		$('ul.nav a[href="' + url + '"]').parent().addClass('active');
		$('ul.nav a').filter(function() {
			return this.href == url;
		}).parent().addClass('active');
	});
</script>