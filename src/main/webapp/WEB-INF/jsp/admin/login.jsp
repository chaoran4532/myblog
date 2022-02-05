<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<title>MyBlog后台登陆</title>
 	<!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css" type="text/css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" type="text/css" />

</head>
<body>
	<div class="container">

		<form
			action="${pageContext.request.contextPath}/admin/login.action"
			method="post">

			<h1>
				<a href="">MyBlog后台</a>
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
			name="adminName"
			value="${cookie.adminName.value}"
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
			required>

            <span class="identifying">

                <img id="img" src="${pageContext.request.contextPath }/verificationCode.gif" width="120px" height="40px"
                     onclick="javascript:this.src='${pageContext.request.contextPath }/verificationCode.gif?id='+new Date().getMilliseconds()">

            </span>

            <input
                    type="text"
                    id="inputCode"
                    class="form-control"
                    placeholder="验证码"
                    name="verCode"
                    required >

            <label
                    for="inputCode"
                    class="sr-only">验证码</label>
			<input class="btn btn-lg btn-primary btn-block" type="submit"  value="登录"/>
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