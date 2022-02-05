<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="head.jsp"%>
<title>标签增加</title>
<div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>标签添加页面</span>
            </div>
            <div class="providerAdd">
                <form id="userForm" name="userForm" method="post" action="${pageContext.request.contextPath }/admin/tagAdd.action">
                    <!--div的class 为error是验证错误，ok是验证成功-->
                    <div class="info">${msg}</div>
                    <div class="">
                        <label for="newTagName">新标签：</label>
                        <input type="text" name="newTagName" id="newTagName" value="">
						<font color="red"></font>
                    </div>

                    <div class="providerAddBtn">
                        <!--<a href="#">保存</a>-->
                        <input type="submit" name="save" value="添加">
<%--                        <input type="button" name="save" id="save" value="保存" class="input-button">--%>
                    </div>
                </form>
            </div>
        </div>
    </section>
<footer class="footer">
    MyBlog
</footer>