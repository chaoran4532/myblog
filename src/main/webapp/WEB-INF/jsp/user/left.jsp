<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="../common/head.jsp"/>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/adminStyle.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script>
</head>
<body>
<section class="publicMian ">
    <div class="left">
        <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
        <nav>
            <ul class="list">
                <li><a href="${pageContext.request.contextPath }/user/articleList.jhtml">文章管理</a></li>
                <li><a href="${pageContext.request.contextPath }/user/pwdModify.jhtml">密码修改</a></li>
                <li><a href="${pageContext.request.contextPath }/homepage.jhtml/${sessionScope.user.userId}">返回博客首页</a>
                </li>
            </ul>
        </nav>
    </div>
    <input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
    <input type="hidden" id="referer" name="referer" value="<%=request.getHeader("Referer")%>"/>
