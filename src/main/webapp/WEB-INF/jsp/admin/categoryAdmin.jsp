<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="head.jsp"%>
<title>分类管理</title>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>类别管理页面</span>
    </div>
    <div class="search">
        <form method="post" action="${pageContext.request.contextPath }/admin/categoryAdmin.jhtml">

            <span>ID:</span>
            <input name="queryCategoryId" class="input-text" type="text" value="${queryCategoryId }">
            <span>类别名:</span>
            <input name="queryCategoryName" class="input-text" type="text" value="${queryCategoryName }">

            <%--            <input type="hidden" name="pageIndex" value="1"/>--%>
            <input value="查 询" type="submit" id="searchbutton">
                        <a href="${pageContext.request.contextPath}/admin/categoryAdd.jhtml">添加类别</a>
        </form>
    </div>
    <!--用户-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th width="10%">类别id</th>
            <th width="20%">类别名</th>
            <th width="20%">操作</th>
        </tr>
        <c:forEach var="category" items="${categoryList}" varStatus="status">
            <tr>
                <td>
                    <span>${category.id }</span>
                </td>
                <td>
                    <span>${category.categoryName }</span>
                </td>
                <td>
                    <span><a class="modifyCategory" href="${pageContext.request.contextPath }/admin/categoryUpdate.jhtml/${category.id}"><img
                            src="${pageContext.request.contextPath }/img/update.png" alt="修改" title="修改"/></a></span>
                    <span><a class="deleteCategory" href=" javascript:; " categoryId=${category.id } categoryName=${category.categoryName }><img
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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/categoryAdmin.js"></script>
</body>
</html>

