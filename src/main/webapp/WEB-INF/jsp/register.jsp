<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<% String basePath=request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<title>登录 | MyBlog</title>
 	<!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css" type="text/css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" type="text/css" />

</head>
<body>
	<div class="container">

		<form
			action="${pageContext.request.contextPath}/register.action"
			method="post">

			<h1>
				<a href="">MyBlog</a>
			</h1>
			<p style="color: red"> ${msg}</p>
			<label
			for="input"
			class="sr-only">用户名</label>

			<input
			type="text"
			id="input"
			class="form-control"
			placeholder="用户名"
			name="userName"
			value="${userName}"
			required>

			<label
					for="nickname"
					class="sr-only">昵称</label>

			<input
					type="text"
					id="nickname"
					class="form-control"
					placeholder="昵称"
					name="nickname"
					value="${nickname}"
					required>

			<label
			for="inputPassword"
			class="sr-only">密码</label>



			<input
			type="password"
			id="inputPassword"
			class="form-control"
			placeholder="密码"
			name="password"
			value="${password}"
			required>
			<label
					for="repeatPassword"
					class="sr-only">重复密码</label>



			<input
					type="password"
					id="repeatPassword"
					class="form-control"
					placeholder="重复密码"
					name="repeatPassword"
					value="${repeatPassword}"
					required>
			<input class="btn btn-lg btn-primary btn-block" type="submit"  value="注册"/>
				<a class="visitor" href="${pageContext.request.contextPath}/homepage.jhtml/1">访客登录</a>
		</form>
	</div>


<%--	<div id="footer">--%>
<%--		<a target="_blank"--%>
<%--			href="https://github.com/Lemonreds">--%>
<%--		<img src="${pageContext.request.contextPath}/img/github.png"--%>
<%--		width="22px"--%>
<%--		height="22px" class="img-circle">GitHub</a>--%>
<%--		by lemonreds.--%>
<%--	</div>--%>
	<!-- footer -->
</body>
</html>