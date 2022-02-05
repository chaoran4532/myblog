<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@include file="head.jsp" %>
<title>用户管理</title>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>用户管理页面</span>
    </div>
    <div class="search">
        <form method="post" action="${pageContext.request.contextPath }/admin/userAdmin.jhtml">
            <span>用户ID：</span>
            <input name="queryUserId" class="input-text" type="text" value="${queryUserId }">
            <span>用户名：</span>
            <input name="queryUserName" class="input-text" type="text" value="${queryUserName }">
            <span>用户昵称：</span>
            <input name="queryNickname" class="input-text" type="text" value="${queryNickname }">

<%--            <input type="hidden" name="pageIndex" value="1"/>--%>
            <input value="查 询" type="submit" id="searchbutton">
        </form>
    </div>
    <!--用户-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th width="10%">用户ID</th>
            <th width="10%">用户名</th>
            <th width="10%">用户昵称</th>
            <th width="20%">个性签名</th>
            <th width="10%">文章数量</th>
            <th width="10%">账号状态</th>
            <th width="30%">操作</th>
        </tr>
        <c:forEach var="user" items="${userList}" varStatus="status">
            <tr>
                <td>
                    <span>${user.userId }</span>
                </td>
                <td>
                    <span>${user.userName }</span>
                </td>

                <td>
                    <span>${user.nickName}</span>
                </td>
                <td>
                    <span>${user.signature}</span>
                </td>
                <td>
                    <span>${user.articleNum}</span>
                </td>
                <td>
							<span>
								<c:if test="${user.status==0}">封禁中</c:if>
								<c:if test="${user.status==1}">正常</c:if>
							</span>
                </td>
                <td>
                    <span><a class="viewUser" href="${pageContext.request.contextPath}/homepage.jhtml/${user.userId}"><img
                            src="${pageContext.request.contextPath }/img/read.png" alt="查看" title="查看"/></a></span>
                    <c:if test="${user.status==1}">
                    <span><a class="modifyUser" href="${pageContext.request.contextPath}/admin/setUserStatus.action/${user.userId}/0"><img
                            src="${pageContext.request.contextPath }/img/update.png" alt="封禁" title="封禁"/></a></span>
                    </c:if>
                    <c:if test="${user.status==0}">
                    <span><a class="modifyUser" href="${pageContext.request.contextPath}/admin/setUserStatus.action/${user.userId}/1"><img
                            src="${pageContext.request.contextPath }/img/update.png" alt="解封" title="解封"/></a></span>
                    </c:if>
                    <span><a class="deleteUser" href=" javascript:;" userId=${user.userId } username=${user.userName }><img
                            src="${pageContext.request.contextPath }/img/delete.png" alt="删除" title="删除"/></a></span>
                </td>
            </tr>
        </c:forEach>
    </table>
<%--    <input type="hidden" id="totalPageCount" value="${totalPageCount}"/>--%>
<%--    <c:import url="../rollpage.jsp">--%>
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
            <p>你确定要删除该用户吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>
<footer class="footer">
    MyBlog
</footer>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/userAdmin.js"></script>
</body>
</html>
