<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>结果 | MyBlog</title>
<!-- Bootstrap core CSS -->
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->

<!-- 引入页面公共样式 -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css" />

</head>
<body>
	<div class="head_line"></div>
	<div class="container" id="main">
		<div class="row c_center" style="margin:0, auto;">

			<h4><a href="${pageContext.request.contextPath}/homepage.jhtml/${sessionScope.user.userId}">添加成功，点击返回首页</a></h4>

		
			
		</div>		
		<div class="foot_line"></div>
	</div>	
	<!-- container -->
	<div id="footer">
		<a href="${pageContext.request.contextPath}/homepage.jhtml/${sessionScope.user.userId}">&nbsp;&nbsp;MyBlog</a>
	</div>
	<!-- footer -->
</body>
</html>