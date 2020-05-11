<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>OnLu购物车</title>
    <link rel='shortcut icon' type='image/x-icon' href="../../static/img/logo.jpg"/>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="../../static/js/jquery2.1.4.min.js"></script>
    <script src="../../static/js/bootstrap.min.js"></script>
    <script src="../../static/js/order.js"></script>
    <script src="../../static/js/login.js"></script>
    <style>
        /* 1.页面框架
            2.导航框架
            3.搜索框架
            4.轮播图框架
            5.商品图片框架

        */

        /* 页面框架 */
        body {
            padding-top: 70px;
        }

        /* 导航中的注册,登录,在线人数 */
        #example-navbar-collapse li {
            padding-right: 50px;
        }

        table .infor {
            vertical-align: middle !important;
        }

        table th {
            text-align: center;
        }


    </style>
</head>
<body>
<!-- 头部导航栏 -->
<%@include file='head.jsp' %>

<!-- 		订单信息 -->
<div class="container" style="margin-top: 80px;">
    <div class="row" id="orbox" style="height:2000px;text-align:center;">
        <table class="table table-hover text-center">
            <thead>
            <tr id="orderTitle" style="text-align:center;margin:0 auto;">
                <td><h1>借书信息</h1></td>
            </tr>
            <tbody id="order" style="height:500px;text-align:center;"></tbody>
            </thead>
        </table>
    </div>
</div>
<div class="container" style="margin-top: 80px;">
    <div class="row" id="orbox" style="height:2000px;text-align:center;">
        <table class="table table-hover text-center">
            <thead>
            <tr id="orderTitle" style="text-align:center;margin:0 auto;">
                <td><h1>还书信息</h1></td>
            </tr>
            <tbody id="orderre" style="height:500px;text-align:center;"></tbody>
            </thead>

        </table>
    </div>
</div>

<%@include file='foot.jsp' %>

</body>
</html>