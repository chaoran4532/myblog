<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="head.jsp"%>
<title>标签管理</title>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>标签管理页面</span>
    </div>
    <div class="search">
        <form method="post" action="${pageContext.request.contextPath }/admin/tagAdmin.jhtml">
            <span>ID:</span>
            <input name="queryTagId" class="input-text" type="text" value="${queryTagId }">
            <span>标签名:</span>
            <input name="queryTagName" class="input-text" type="text" value="${queryTagName }">

            <%--            <input type="hidden" name="pageIndex" value="1"/>--%>
            <input value="查 询" type="submit" id="searchbutton">
            <a href="${pageContext.request.contextPath}/admin/tagAdd.jhtml">添加标签</a>
        </form>
    </div>
    <!--用户-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th width="10%">标签id</th>
            <th width="20%">标签名</th>
            <th width="20%">操作</th>
        </tr>
        <c:forEach var="tag" items="${tagList}" varStatus="status">
            <tr>
                <td>
                    <span>${tag.id }</span>
                </td>
                <td>
                    <span>${tag.tag }</span>
                </td>
                <td>
                    <span><a class="modifyTag" href="${pageContext.request.contextPath }/admin/tagUpdate.jhtml/${tag.id}"><img
                            src="${pageContext.request.contextPath }/img/update.png" alt="修改" title="修改"/></a></span>
                    <span><a class="deleteTag" href=" javascript:;" tagId=${tag.id } tagName=${tag.tag }><img
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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/tagAdmin.js"></script>
</body>
</html>

