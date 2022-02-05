<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="head.jsp"%>
<title>分类管理</title>
<div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>分类修改页面</span>
            </div>
            <div class="providerAdd">
                <form id="categoryForm" name="categoryForm" method="post" action="${pageContext.request.contextPath }/admin/categoryUpdate.action">
                    <!--div的class 为error是验证错误，ok是验证成功-->
                    <div class="info">${msg}</div>
                    <div class="">
                        <label for="categoryId">标签ID：</label>
                        <input type="text" name="categoryID" id="categoryId" value="${category.id}" readonly="readonly">
                        <font color="red"></font>
                    </div>
                    <div class="">
                        <label for="categoryName">标签名：</label>
                        <input type="text" name="categoryName" id="categoryName" value="${category.categoryName}">
						<font color="red"></font>
                    </div>

                    <div class="providerAddBtn">
                        <!--<a href="#">保存</a>-->
                        <input type="submit" name="save" value="修改">
<%--                        <input type="button" name="save" id="save" value="保存" class="input-button">--%>
                    </div>
                </form>
            </div>
        </div>
    </section>
<footer class="footer">
    MyBlog
</footer>