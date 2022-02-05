<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="left.jsp"%>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>文章管理页面</span>
    </div>
    <div class="search">
        <form method="post" action="${pageContext.request.contextPath }/user/articleList.jhtml">
            <span>文章标题:</span>
            <input name="queryArticleName" class="input-text" type="text" value="${queryArticleName }">

            <span>类别：</span>
            <select name="queryCategory">
                <c:if test="${categoryList != null }">
                    <option value="0">--请选择--</option>
                    <c:forEach var="category" items="${categoryList}">
                        <option
                                <c:if test="${category.id == queryCategory }">selected="selected"</c:if>
                                value="${category.id}">${category.categoryName}</option>
                    </c:forEach>
                </c:if>
            </select>
            <span>标签：</span>
            <select name="queryTag">
                <c:if test="${tagList != null }">
                    <option value="0">--请选择--</option>
                    <c:forEach var="tag" items="${tagList}">
                        <option
                                <c:if test="${tag.id == queryTag }">selected="selected"</c:if>
                                value="${tag.id}">${tag.tag}</option>
                    </c:forEach>
                </c:if>
            </select>

<%--            <input type="hidden" name="pageIndex" value="1"/>--%>
            <input value="查 询" type="submit" id="searchbutton">
<%--            <a href="${pageContext.request.contextPath}/WEB-INF/jsp/user/useradd.jspd.jsp">添加文章</a>--%>
        </form>
    </div>
    <!--用户-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th width="10%">文章id</th>
            <th width="20%">文章标题</th>
            <th width="10%">作者</th>
            <th width="10%">类别</th>
            <th width="10%">点赞</th>
            <th width="10%">评论数</th>
            <th width="10%">播放量</th>
            <th width="20%">操作</th>
        </tr>
        <c:forEach var="article" items="${articleList}" varStatus="status">
            <tr>
                <td>
                    <span>${article.id }</span>
                </td>
                <td>
                    <span>${article.title }</span>
                </td>
                <td>
                    <span>${article.author }</span>
                </td>
                <td>
                    <span>${article.category }</span>
                </td>
                <td>
                    <span>${article.star }</span>
                </td>
                <td>
                    <span>${article.comment }</span>
                </td>
                <td>
                    <span>${article.visit }</span>
                </td>
                <td>
                    <span><a class="viewUser" href="${pageContext.request.contextPath }/article.jhtml/${sessionScope.user.userId}/${article.id}"><img
                            src="${pageContext.request.contextPath }/img/read.png" alt="查看" title="查看"/></a></span>
                    <span><a class="modifyUser" href="${pageContext.request.contextPath }/user/articleUpdate.jhtml/${article.id}"><img
                            src="${pageContext.request.contextPath }/img/update.png" alt="修改" title="修改"/></a></span>
                    <span><a class="deleteArticle" href="javascript:;" articleId=${article.id } title=${article.title }><img
                            src="${pageContext.request.contextPath }/img/delete.png" alt="删除" title="删除"/></a></span>
                </td>
            </tr>
        </c:forEach>
    </table>
<%--    <input type="hidden" id="totalPageCount" value="${totalPageCount}"/>--%>
<%--    <c:import url="../common/rollpage.jsp">--%>
<%--        <c:param name="totalCount" value="${totalCount}"/>--%>
<%--        <c:param name="currentPageIndex" value="${currentPageIndex}"/>--%>
<%--        <c:param name="totalPageCount" value="${totalPageCount}"/>--%>
<%--    </c:import>--%>
</div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该员工吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<footer class="footer">
    MyBlog
</footer>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/articleList.js"></script>
</body>
</html>

