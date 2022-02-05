<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="common/head.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${tag.tag} | ${blogger.nickName}'s Blog</title>


	<!-- Bootstrap core CSS -->
	<link
			href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
			rel="stylesheet"/>
	<!-- Custom styles for this template -->


	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css"/>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/sort.css"/>
</head>
<body>
<div class="head_line"></div>

<div class="container" id="main">

	<div id="header"></div>

	<div class="row c_center">
		<div class="col-md-3" id="left_content">

			<div id="title">
				<h2>
					<a href="${pageContext.request.contextPath}/homepage.jhtml/${blogger.userId}">${blogger.nickName}'s
						Blog</a>
				</h2>
				<h5 class="text-muted">Winner Winner Chicken Dinner!</h5>
			</div>

			<div class="c_center" id="person_info">
				<img src="${pageContext.request.contextPath}/img/avatar/${blogger.avatarImg}" height="130" width="130"
					 alt="what?" class="img-circle">
				<h5 class="text-muted">Chicken Dinner Again!</h5>
			</div>


			<div id="list">
				<table class="table table-hover c_center">
					<tr>
						<td><a href="${pageContext.request.contextPath}/homepage.jhtml/${blogger.userId}"><span
								class="glyphicon glyphicon-home"></span>
							&nbsp;&nbsp;首页</a></td>
					</tr>
					<tr>
						<td class="active"><a href="${pageContext.request.contextPath}/SortServlet?get=all"><span
								class="glyphicon glyphicon-list"></span>
							&nbsp;&nbsp;分类</a></td>
					</tr>
					<tr>
						<td><a href="${pageContext.request.contextPath}/TagsServlet?get=all"><span
								class="glyphicon glyphicon-tags"></span>
							&nbsp;&nbsp;标签</a></td>
					</tr>
					<tr>
						<td><a href="${pageContext.request.contextPath}/AxisServlet"><span
								class="glyphicon glyphicon-book"></span>
							&nbsp;&nbsp;时间轴</a></td>
					</tr>
					<tr>
						<td><a href="${pageContext.request.contextPath}/page/about.html"><span
								class="glyphicon glyphicon-user"></span>
							&nbsp;&nbsp;关于</a></td>
					</tr>
				</table>
			</div>
			<!-- list -->
			<br/>
		</div>
		<div class="col-md-2" id="center_content"></div>
		<div class="col-md-7 " id="right_Content">
			<br/> <br/>
			<div class="list-group">
				<a href="#" class="list-group-item active">${tag.tag}</a>
				<!-- 这里初始化文章列表 -->
				<c:forEach var="article" items="${articleList}">
					<div class="list-group-item">
						<h4>
							<a href="${pageContext.request.contextPath}/article.jhtml/${blogger.userId}/${article.id}">${article.title}</a>
						</h4>
						<br/>
						<span>${article.time}&nbsp;&nbsp;|</span>
						<a href="${pageContext.request.contextPath}/category.jhtml/${blogger.userId}/${article.categoryId}">${article.category}</a>&nbsp;&nbsp;|
						<span>阅读次数: ${article.visit}</span>
						<c:if test="${article.introduction!=null&&article.introduction!=''}">
							<br/><br/>
							<span>${article.introduction}</span>
							<br/><br/><br/>
						</c:if>
						<a href="${pageContext.request.contextPath}/article.jhtml/${blogger.userId}/${article.id}">阅读全文</a>
						<br/>
					</div>
				</c:forEach>
				<!-- 初始化文章列表完成 -->
			</div>
		</div>
	</div>

	<div class="foot_line"></div>
</div>
<!-- container -->



<!-- footer -->

</body>
</html>