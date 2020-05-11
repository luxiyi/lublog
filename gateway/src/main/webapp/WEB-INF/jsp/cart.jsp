<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OnLu购物车</title>
<link rel='shortcut icon' type='image/x-icon' href="../../static/img/logo.jpg" />
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="../../static/js/jquery2.1.4.min.js"></script>
<script src="../../static/js/bootstrap.min.js"></script>
<script src="../../static/js/cart.js"></script>
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

.cart-img {
	width: 60px;
}

.alterbook:hover {
	cursor: pointer;
}
.deletebook:hover {
	cursor: pointer;
}
table .infor {
	vertical-align: middle !important;
}

table th {
	text-align: center;
}

.deletecart:hover {
	width: 30px;
	cursor: pointer;
}

.titleBox{
	height:50px;
	line-height:50px;
	text-align:center;
	font-size:30px;
}
.contextBox{
	height:300px;
	line-height:300px;
	text-align:center;
	font-size:20px;
}

</style>
</head>
<body>
	<!-- 头部导航栏 -->
	<%@include file='head.jsp'%>
	<div class="container" style="margin-top: 80px;" id="goodsbox" style="height: 1200px;">
		<div class="row">
			<table class="table table-hover text-center">
				<thead>
					<tr>
						<th>书籍图片</th>
						<th>书名</th>
						<th>购书单价（元）</th>
						<th>数量</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="cart">
				</tbody>
			</table>
		</div>
	</div>

		
		<!-- 		结账信息 -->
		<div id="checkoutform" class="modal fade" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<button class="close" data-dismiss="modal">
							<span>&times;</span>
						</button>
					</div>
					<div class="modal-title">
						<h1 class="text-center">结账消息</h1>
					</div>
					<div class="modal-body">
						<div  id="checkout" >
							
						</div>
						<div class="text-right">
							<a href="/cart"><button class="btn btn-primary" type="submit">确认</button></a>
							<button class="btn btn-danger" data-dismiss="modal">取消</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 		订单信息 -->
		<div id="orderform" class="modal fade" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<button class="close" data-dismiss="modal">
							<span>&times;</span>
						</button>
					</div>
					<div class="modal-title">
						<h1 class="text-center">订单消息</h1>
					</div>
					<div class="modal-body">
						<div  id="order" ></div>
						<div class="text-right">
							<a href="./jsp/cart.jsp"><button class="btn btn-primary" type="submit">确认</button></a>
							<button class="btn btn-danger" data-dismiss="modal">取消</button>
						</div>
					</div>
				</div>
			</div>
		</div>
<%@include file='foot.jsp'%>
</body>
</html>
