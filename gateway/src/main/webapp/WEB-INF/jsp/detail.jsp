<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>OnLu购物车</title>
    <%--<base href="/books/" />--%>
    <link rel='shortcut icon' type='image/x-icon' href="../../static/img/logo.jpg"/>
    <link rel="stylesheet"
          href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="../../static/js/jquery2.1.4.min.js"></script>
    <script src="../../static/js/bootstrap.min.js"></script>
    <script src="../../static/js/detailbook.js"></script>
    <script src="../../static/js/login.js"></script>
    <style>
        /* 1.页面框架
            2.导航框架
            3.搜索框架
            4.轮播图框架
            5.商品图片框架

        */
        body {
            padding-top: 70px;
        }
        #addimg {
            width: 200px;
            height:30px;
            float:left;
            padding-bottom:15px;
            padding-left: 50px;
            font-size:17px;
        }
        .addimg:hover {
            cursor: pointer;
        }
        /* 导航中的注册,登录,在线人数 */
        #example-navbar-collapse li {
            padding-right: 50px;
        }

        /*  商品图片框架 */
        /*  全部商品图片的框架    */
        #detailbox {
            height: 200px;
            width: 1100px;
            margin: 20px auto;
            float: none;
        }

        /*  每个商品图片最外层    */
        #detailbook {
            transition: all 1s;
            margin: 0 auto;
            width: 800px;
            height: 130px;
            float: none;
        }

        #image {
            width: 300px;
            height: 150px;
            float: left;
            padding-left: 100px;
            padding-top: 10px;
        }

        #tother {
            width: 450px;
            height: 150px;
            float: left;
            padding-left: 10px;
        }

        /*  每个商品图片    */
        .img-responsive {
            transition: all 1s;
            height: 130px;
        }

        .img-responsive:hover {
            transform: scale(1.2);
        }

        .img-hidden {
            overflow: hidden;
        }

        table tr {
            text-align: left;
            height: 40px;
        }

        table .infor1 {
            width: 200px;
        }

        table .infor2 {
            width: 300px;
        }

        table .infor3 {
            width: 400px;
        }
    </style>
</head>
<body>
<!-- 头部导航栏 -->
<%@include file='head.jsp' %>

<!-- 	书籍详情 -->
<div style="height: 250px; width: 200px; overflow: auto; position: relative; margin: 40px auto; font-size: 30px; padding-top: 200px">书籍介绍
</div>
<div class="container" id="detailbox">
    <div class="row" id="detailbook"></div>
</div>


<div style="height: 250px; width: 200px; overflow: auto; position: relative; margin: 0 auto; font-size: 30px; padding-top: 200px">书籍评价</div>
<div style="height: 100px; width: 1000px; margin: 0 auto; font-size: 20px; padding-top: 60px">
    <div class="row">
        <table class="table table-hover text-center">
            <tr>
                <td class='infor1' style="padding-left: 100px">&nbsp;&nbsp;&nbsp;&nbsp;用户</td>
                <td class='infor2' style="padding-left: 200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;评价时间</td>
                <td class='infor3' style="padding-left: 200px">&nbsp;&nbsp;&nbsp;&nbsp;评价内容</td>
            </tr>
        </table>
    </div>
</div>


<div data-spy="scroll" data-target="#navbar-example" data-offset="0"
     style="height: 500px; width: 1000px; overflow: auto; position: relative; margin: 0 auto;">
    <div class="container" style="margin-top: 20px;"
         id="commentbox" style="height: 600px;">
        <div class="row">
            <table class="table table-hover text-center">
                <tbody id="comment" style="height: 700px;"></tbody>
            </table>
        </div>
    </div>
</div>
<div style="height: 300px; width: 800px; margin: 0 auto; font-size: 15px; padding-top: 10px; padding-left: 20px;">
    评价内容：
    <div style="height: 220px; width: 700px; position: relative; margin: 0 70px;">
			<textarea class="form-control" style="width: 600px; height: 200px; padding-bottom: 160px;"
                      id="ccont" type="text" placeholder="评论内容"></textarea>
    </div>
    <div style="width: 500px; margin: 0 400px;">
        <button class="btn btn-primary" type="submit"
                onclick="insertcomment()">评价
        </button>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="/index">
            <button class="btn btn-danger">返回主界面</button>
        </a>
    </div>
</div>

<%@include file='foot.jsp' %>

</body>
</html>