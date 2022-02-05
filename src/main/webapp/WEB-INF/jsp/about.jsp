<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>关于 | MyBlog</title>

    <!-- Bootstrap core CSS -->
    <link
            href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
            rel="stylesheet">
    <!-- Custom styles for this template -->
    <!-- 引入页面公共样式 -->
    <link type="text/css" rel="stylesheet" href="/Blog/css/public.css" />
</head>
<body>
<div class="head_line"></div>
<div class="container" id="main">
    <div id="header"></div>
    <div class="row c_center">
        <div class="col-md-3" id="left_content">
            <div id="title">
                <h2>
                    <a href="${pageContext.request.contextPath}/homepage.jhtml/1">MyBlog</a>
                </h2>
                <h5 class="text-muted">Winner Winner Chicken Dinner!</h5>
            </div>
            <br /> <br />
            <div id="about_page">
                <ul class="nav nav-pills nav-stacked">
                    <li><a href="#">关于</a></li>
                </ul>
            </div>

            <br />
        </div>

        <div style="margin-top: 120px; text-align: center">
            <h2>about me</h2>
            MyBlog By chaoran 2021 12 10 <br />
            <div style="text-align: center">
                <h4>
                    Email : huchaoran4532@outlook.com<br />
                    phone:13655774532<br/>
                    QQ: 1050563500<br />
                </h4>
            </div>
        </div>
    </div>
</div>

<div class="foot_line"></div>
<div id="footer">
    <a href="${pageContext.request.contextPath}/homepage.jhtml/1">&nbsp;&nbsp;MyBlog</a>
</div>
</body>
</html>