<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/head.css"/>

</head>
<body>
<!--头部-->
<header class="publicHeader">
    <c:choose>
        <c:when test="${sessionScope.user!=null}">
            <h1><a href="${pageContext.request.contextPath}/homepage.jhtml/${sessionScope.user.userId}">MyBolg</a></h1>
            <div class="publicHeaderR">
                <p><span>你好！</span><span style="color: #fff21b"> ${sessionScope.user.nickName }</span></p>
                <a href="${pageContext.request.contextPath }/logout.action">退出</a>
            </div>
        </c:when>
        <c:otherwise>
            <h1><a href="${pageContext.request.contextPath}/login.jhtml">MyBolg</a></h1>
            <div class="publicHeaderR">
                <p><a href="${pageContext.request.contextPath}/login.jhtml">请登录</a></p>
                <a href="${pageContext.request.contextPath }/register.jhtml">注册</a>
            </div>
        </c:otherwise>
    </c:choose>
</header>
<%--<!--时间-->--%>
<%--<section class="publicTime">--%>
<%--    <span id="time">2015年1月1日 11:11  星期一</span>--%>
<%--</section>--%>
</body>
</html>
