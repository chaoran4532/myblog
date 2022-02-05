<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/head.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/adminStyle.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script>
</head>
<body>
<!--头部-->
<header class="publicHeader">
            <h1><a href="${pageContext.request.contextPath}/admin/admin.jhtml/${sessionScope.admin.id}">MyBlog后台</a></h1>
            <div class="publicHeaderR">
                <p><span>你好！</span><span style="color: #fff21b"> ${sessionScope.user.nickName }</span></p>
                <a href="${pageContext.request.contextPath }/admin/logout.action">退出</a>
            </div>
</header>
<section class="publicMian ">
    <div class="left">
        <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
        <nav>
            <ul class="list">
                <li><a href="${pageContext.request.contextPath }/admin/userAdmin.jhtml">用户管理</a></li>
                <li><a href="${pageContext.request.contextPath }/admin/articleAdmin.jhtml">文章管理</a></li>
                <li><a href="${pageContext.request.contextPath }/admin/categoryAdmin.jhtml">类别管理</a></li>
                <li><a href="${pageContext.request.contextPath }/admin/tagAdmin.jhtml">标签管理</a>
                </li>
            </ul>
        </nav>
    </div>
    <input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
    <input type="hidden" id="referer" name="referer" value="<%=request.getHeader("Referer")%>"/>