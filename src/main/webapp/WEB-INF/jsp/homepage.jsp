<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="common/head.jsp"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>首页 | ${blogger.nickName}'s Blog</title>
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->

    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
</head>
<body>
<div class="head_line"></div>

<div class="container" id="main">

    <div id="header"></div>

    <div class="row c_center">
        <div class="col-md-3" id="left_content">

            <div id="title">
                <h2><a href="${pageContext.request.contextPath}/homepage.jhtml/${blogger.userId}">${blogger.nickName}'s
                    Blog</a></h2>
                <h5 class="text-muted">Winner Winner Chicken Dinner!</h5>
            </div>

            <div class="c_center" id="person_info">
                <img src="${pageContext.request.contextPath}/img/avatar/${blogger.avatarImg}" height="130" width="130"
                     alt="丢失了我的头像?" class="img-circle">
                <h4 class="text-muted">${blogger.userName}</h4>
                <h5 class="text-muted">${blogger.signature}</h5>
            </div>

            <div class="c_center">
                <!-- 这里初始化侧边栏的4个标签 -->
                <div class="inline ">
                    <a href="#">${articleCount}<br/>日志</a>
                </div>
                <div class="inline ">
                    <a href="${pageContext.request.contextPath}/category.jhtml/${blogger.userId}"><span> ${categoryCount} </span><br/>分类</a>
                </div>
                <div class="inline ">
                    <a href=""><span>${tagCount}</span><br/>标签</a>
                </div>

            </div>


            <div id="list">
                <table class="table table-hover c_center">
                    <tr class="active">
                        <td><a href="${pageContext.request.contextPath}/homepage.jhtml/${blogger.userId}	"><span
                                class="glyphicon glyphicon-home"></span>
                            &nbsp;&nbsp;首页</a></td>
                    </tr>
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/category.jhtml/${blogger.userId}"><span
                                class="glyphicon glyphicon-list"></span>
                            &nbsp;&nbsp;分类</a></td>
                    </tr>
                    <c:if test="${sessionScope.user!=null}">
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/user/personalCenter.jhtml"><span
                                class="glyphicon glyphicon-tags"></span>
                            &nbsp;&nbsp;个人中心</a></td>
                    </tr>
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/user/addArticle.jhtml"><span
                                class="glyphicon glyphicon-book"></span>
                            &nbsp;&nbsp;写文章</a></td>
                    </tr>
                    </c:if>
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/about.jhtml"><span
                                class="glyphicon glyphicon-user"></span>
                            &nbsp;&nbsp;关于</a></td>
                    </tr>
                </table>
            </div>
            <!-- list -->
            <br/>

            <div class="sort">
                <div class="list-group">
                    <span class="list-group-item active">分类</span>
                    <!-- 这里初始化分类 -->
                    <c:forEach var="category" items="${categoryList}">
                        <a href="${pageContext.request.contextPath}/category.jhtml/${blogger.userId}/${category.id}"
                           class="list-group-item">${category.categoryName}(${category.articleCount})</a>
                    </c:forEach>
                    <!-- 初始化结束 -->
                </div>
            </div><!-- sort -->


            <div class="visit">
                <div class="list-group">
                    <span class="list-group-item active">阅读排行</span>
                    <!-- 这里初始化阅读排行 -->
                    <c:forEach var="article" items="${articlesTopTen}">
                        <a href="${pageContext.request.contextPath}/article.jhtml/${blogger.userId}/${article.id}"
                           class="list-group-item">${article.title}&nbsp;&nbsp; <span
                                class="c_right">(${article.visit})</span></a>
                    </c:forEach>
                    <!-- 初始化结束 -->
                </div>
            </div><!-- visit-->


            <div id="tag">
                <div class="list-group">
                    <span class="list-group-item active">标签</span>
                    <br/>

                    <!-- 这里初始化标签 -->
                    <c:forEach var="tag" varStatus="status" items="${tagList}">
                        <c:choose>
                            <c:when test="${status.count%2==1}">
							<span class="label label-info"><a href="${pageContext.request.contextPath}/tag.jhtml/${blogger.userId}/${tag.id}">
							&nbsp;${tag.tag}&nbsp;</a></span>
                            </c:when>
                            <c:otherwise>
								<span class="label label-success"><a href="${pageContext.request.contextPath}/tag.jhtml/${blogger.userId}/${tag.id}">
								&nbsp;${tag.tag}&nbsp;</a></span>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                    <!-- 初始化标签完成 -->
                </div>
            </div><!-- tag -->


            <!-- admin here -->
            <c:if test="${sessionScope.user!=null}">
                <a href="${pageContext.request.contextPath}/user/addArticle.jhtml">
                    <span class="glyphicon glyphicon-plus">&nbsp;&nbsp;写新文章&nbsp;&nbsp;</span>
                </a>
                <a href="${pageContext.request.contextPath}/user/personalCenter.jhtml">
                    <span class="glyphicon glyphicon glyphicon-user">&nbsp;&nbsp;管理更多&nbsp;&nbsp;</span>
                </a>
            </c:if>
            <!--  -->

        </div>
        <div class="col-md-2" id="center_content">
        </div>


        <div class="col-md-7 " id="right_Content">
            <br/>
            <br/>
            <div class="list-group">
                <a href="#" class="list-group-item active">文章</a>
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
</div><!-- container -->
<div id="footer">
    <div class="r_div">
        <a href="#"><input class="btn btn-default" value="返回顶部" style="width:50%;"/></a>
        <h6> 被访问了 ${visited } 次</h6>
        <h6> 你是第 ${member}个访问者</h6>
    </div>

</div><!-- footer -->
</body>
</html>