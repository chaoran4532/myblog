<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="common/head.jsp"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${article.title} | ${blogger.nickName}'s Blog</title>
    <!-- Bootstrap core CSS -->
    <link
            href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->

    <!-- 引入本页面的特殊样式 -->
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/article.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/comment.css"/>
    <script src="${pageContext.request.contextPath}/js/common.js"></script>
    <script src="${pageContext.request.contextPath}/js/article.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
</head>
<body>
<div class="head_line"></div>
<div class="container" id="main">

    <div class="head">
        <div id="title">
            <h2>
                <a href="${pageContext.request.contextPath}/homepage.jhtml/${blogger.userId}">${blogger.nickName}'s
                    Blog</a>
            </h2>
        </div>
    </div>

    <div id="article">

        <div id="a_head ">
            <h3>${article.title}</h3>
            <br/>
            <div>
                <h5>
                    <span>${article.time}</span> <a
                        href="${pageContext.request.contextPath}/category.jhtml/${blogger.userId}/${article.categoryId}">${article.category}</a>
                    ${blogger.nickName}
                </h5>
            </div>
            <div class="r_div">
                <h5>
                    <span class="glyphicon glyphicon-eye-open">&nbsp;${article.visit}&nbsp;</span>
                    <span class="glyphicon glyphicon-heart" id="love">&nbsp;${article.star}&nbsp;</span>
                    <span class="glyphicon glyphicon-comment">&nbsp;${article.comment}&nbsp; </span>
                </h5>
            </div>
            <div id="tag">
                <c:forEach var="tag" items="${tagOfArticle}">
                    <a href="${pageContext.request.contextPath}/tag.jhtml/${blogger.userId}/${tag.id}">${tag.tag}&nbsp;</a>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="line"></div>


    <div id="a_content">
        <%--			<!-- 引入 show.jsp 显示文章内容 ${article.content}-->--%>
        <jsp:include page="/WEB-INF/jsp/show.jsp"/>
    </div>


    <div>
        <div class="f_div">
            <span class="glyphicon glyphicon-chevron-left"></span>


            <c:choose>
                <c:when test="${prevArticle!=null}">
                    <a href="${pageContext.request.contextPath}/article.jhtml/${blogger.userId}/${prevArticle.id}">&nbsp;上一篇:${prevArticle.title}</a>
                </c:when>
                <c:otherwise>
                    &nbsp;没有更早的文章了
                </c:otherwise>
            </c:choose>

        </div>
        <div class="r_div">

            <c:choose>
                <c:when test="${nextArticle!=null}">
                    <a href="${pageContext.request.contextPath}/article.jhtml/${blogger.userId}/${nextArticle.id}">下一篇:&nbsp;${nextArticle.title}</a>
                </c:when>
                <c:otherwise>
                    &nbsp;没有更多的文章了
                </c:otherwise>
            </c:choose>

            <span class="glyphicon glyphicon-chevron-right"></span>
        </div>

        <div>
            <span class="btn btn-default" style="color:#d9534f;" onclick="star_article(${article.id})">点赞</span>
        </div>
        <br/>
    </div>

    <div class="line"></div>

    <!-- 评论 -->
    <div class="comment">

        <div class="r_div">
            <a href="#comment"><span class="glyphicon glyphicon-pencil">&nbsp;写评论....</span></a>
        </div>

        <!-- 加载文章评论 -->
        <c:if test="${commentList!=null && commentList.size()!=0}">
            <c:forEach var="comm" varStatus="status" items="${commentList}">

                <div class="row">
                    <div class="f_div">
                        <img src="${pageContext.request.contextPath}/img/avatar/default.jpg" height="50" width="50"
                             class="img-circle"/>
                        &nbsp;&nbsp;
                        <span style="color: #428bca"> ${comm.nickname}</span>
                        <span>&nbsp;&nbsp;${comm.time}</span>
                    </div>
                    <div id="c_content" class="c_left">
                        <pre>${comm.content }</pre>
                    </div>
                    <div class="r_div">
                        <a><span class="glyphicon glyphicon-thumbs-up"
                                 onclick="star_comment(this,${comm.id})">${comm.star}</span></a>
                        &nbsp;
                        <a><span class="glyphicon glyphicon-thumbs-down"
                                 onclick="diss_comment(this,${comm.id})">${comm.diss}</span></a>
                        &nbsp;
                        <!-- admin here -->
                        <c:if test="${sessionScope.user!=null && sessionScope.user.userId==blogger.userId}">
                            <span class="btn btn-default" style="color:red;"
                                  onclick="deleteComment(this,${comm.id})">删除</span>

                            &nbsp;
                        </c:if>
                    </div>
                    <div class="line"></div>
                </div>

            </c:forEach>
            <%--				<c:import url="./rollpage.jsp">--%>
            <%--					<c:param name="totalCount" value="${100}"/>--%>
            <%--					<c:param name="currentPageIndex" value="${1}"/>--%>
            <%--					<c:param name="totalPageCount" value="${10}"/>--%>
            <%--				</c:import>--%>
        </c:if>
    </div>
    <!-- 这里可以扩展子评论 -->


    <!-- 写评论 -->
    <div id="comment">

        <form action="${pageContext.request.contextPath}/addNewComment.action?bloggerID=${blogger.userId}&articleID=${article.id}"
              method="post">
            <input style="width:30%" class="form-control" type="text" name="nickname"
                   value="${sessionScope.user.nickName}" readonly>

            <c:if test="${sessionScope.user==null}">
                <a href="${pageContext.request.contextPath}/login.jhtml">请先登录</a>
            </c:if>
            <br/>
            <textarea style="resize:none; width:100%; height:180px;" name="content"></textarea>
            <br/>
            <br/>
            <%--            若用户已经登录则可以评论，否则无法点击评论---%>
            <input class="btn btn-default" type="submit" value="评论" onclick="onclick"
            <c:if test="${sessionScope.user==null}">
                disabled="disabled"
            </c:if>
            />
            <br/>
        </form>
    </div>
    <!--  -->
    <div class="line"></div>

</div>
<div id="footer">
    <a href="${pageContext.request.contextPath}/homepage.jhtml/${blogger.userId}">${blogger.nickName}'s Blog首页&nbsp;&nbsp;</a>|
    <a href="#">&nbsp;&nbsp;返回顶部</a>
</div>
<!-- footer -->




</body>
</html>