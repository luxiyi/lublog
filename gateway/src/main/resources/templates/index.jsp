<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
	<meta charset=" UTF-8 />">
	<title>YouLu</title>
<%--<base href="${pageContext.request.contextPath}" />--%>

<link rel='shortcut icon' type='image/x-icon' href="../../static/img/logo.jpg" />
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="../../static/js/jquery2.1.4.min.js"></script>
<script src="../../static/js/bootstrap.min.js"></script>
<script src="../../static/js/login.js"></script>
<script src="../../static/js/register.js"></script>
<script src="../../static/js/books.js"></script>

<style type="text/css">
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

/*  商品图片框架 */
#image{
 	width:170px; 
 	height:150px; 
 	float:left; 
 	padding-top:10px;
}
#tother{
 	width:520px; 
 	height:130px; 
 	float:left; 	
}
#operate{
	width:250px; 
 	height:130px; 
 	float:left;
	padding-left:20px;
	padding-top:20px;
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
/* 加入书架的图片 */
#addimg {
	width: 200px;
	height:30px;
	float:left;
	padding-bottom:15px;
	padding-left: 50px;
	font-size:17px;
}
/* 删除图书的图片 */
#deletebook{
	width: 200px;
	height:30px;
	float:left;
	padding-left: 50px;
	padding-top:10px;
	font-size:17px;
}
#alterbook{
	width: 200px;
	height:30px;
	float:left;
	padding-left: 50px;
	padding-top:20px;
	font-size:17px;
}
.alterbook:hover {
	cursor: pointer;
}
.deletebook:hover {
	cursor: pointer;
}
.addimg:hover {
	cursor: pointer;
} 
#pagebox :hover {
	cursor: pointer;
}

</style>
</head>
<script type="text/javascript">
	function getCode() {
		var code = document.getElementById("code");
		code.src = "checkcode?a=" + Math.random();

	}
</script>
<body>
	<div id="outer" style="width: 100%;height: 3000px;float: none;margin: 0 auto;">
		<!-- 头部导航栏 -->
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/index" style="font-size: 30px;">BookLu.com</a>
			</div>
			<div class="collapse navbar-collapse" id="example-navbar-collapse" >
				<ul class="nav navbar-nav navbar-right" >
					<ul class="nav navbar-nav navbar-header" >
						<%
							Object obj = session.getAttribute("admin");
							if (obj == null) {
						%>
						<li></li>
						<li></li>
						<li><a href="#" data-toggle="modal"
							data-target="#registerform"><span
								class="glyphicon glyphicon-user"></span> 注册</a></li>
						<li><a href="#" data-toggle="modal"
							data-target="#loginform"><span
								class="glyphicon glyphicon-log-in"></span> 登录</a></li>	
						<li></li>
						<%
							} else {
						%>
						<li><a href="#" data-toggle="modal"
							data-target="#addbookform"><span class="glyphicon glyphicon-plus"></span> 增加新书</a></li>
						<li><a href="/order">借阅信息</a></li>
						<li><a><span>欢迎来到BookLu</span></a></li>
						<li style="width:40px;"><div class="contentDiv" style="display:inline-block;margin:5px auto;
							width:40px;height:40px;border-radius:100px;-webkit-border-radius:100px;-moz-border-radius:100px;
								border:2px solid #fff;box-shadow:0 0 4px #ccc;overflow:hidden;">
						<img alt="头像" src="../../static/img/${userinfo.usericon}" style="width:100%;min-height:100%;"/></div></li>
						<li><a href="#" style="padding-left:1px;" data-toggle="modal" data-target="#userinfoform"  onclick="queryuser()">
								${user.luser}</a></li>
						
						<%
							}
						%>
						<li id="logoutbox"><a href="loginout"><span
								class="glyphicon glyphicon-off"></span> 注销</a></li>
					</ul>
					<%-- <li><a>在线人数：${applicationScope.online}</a></li> --%>
				</ul>
			</div>
		</div>
		</nav>
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<div class="indeximg" style="width: 200px;height: 100px;">
						<a href="/index"><img src="../../static/img/书logo.jpg" height="100px" /></a>
					</div>
				</div>
				<div class="col-md-4" id="inputbig" style="width: 370px;height:50px;margin-top:30px;">
					<!-- 搜索框 input-lg是框的大小 -->
					<div class="input-group" style="width: 250px;float:left;">
						<input type="text" class="form-control input-lg " id="find" placeholder="休息一下，找点书看 " > 
					</div>
					<div class="butto" style="width: 70px;height:50px;float:left; padding-top:2px;">
					<span><button class=" btn btn-primary btn-lg" 
							style="color: white; font-size: 16px; background: orangered;" type="submit" onclick="findBlog()">搜索</button></span>
					</div>
				</div>
				<div class="col-md-4">
					<div class="shopcar" style="height: 100px;width: 300px;margin: 30px;margin-left: 100px;padding-left: 10px;">
				<% if (obj == null) { %>  <% } else { %>
					<!-- 购物车 -->
						<a href="/cart" style="text-decoration:none;font-size: 30px;color: #eea236">
							<img src='../../static/img/玩具.png' width='70px'/>&nbsp;&nbsp;&nbsp;秘密花园
						</a>
				<% } %>
					</div>
				</div>
			</div>
		</div>

		<!-- 注册窗口    modal fade模态框 -->
		<div id="registerform" class="modal fade" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<button class="close" data-dismiss="modal">
							<span>&times;</span>
						</button>
					</div>
					<div class="modal-title">
						<h1 class="text-center">注册</h1>
					</div>
					<div class="modal-body">
						<div class="form-group">
								<label>用户名</label> <input class="form-control"
									id="registerphone" type="text" placeholder="首字母为英文字母">
							</div>
							<div class="form-group">
								<label>密码</label> <input class="form-control"
									id="registerpass" type="password"
									placeholder="首字母大写，6-18位包含字母和数字">
							</div>
							<div class="form-group">
								<label>再次输入密码</label> <input class="form-control"
									id="scomfirm" type="password" placeholder="首字母大写，6-18位包含字母和数字">
							</div>
<!-- 							<div class="form-group"> -->
<!-- 							<label>验证码</label><br /> <input type="text" id="checkcode"> -->
<!-- 							<img id="code" src="checkcode" onclick="getCode()" /> -->
<!-- 							</div> -->
							<div id="reginfo" style="width: 270px;height: 50px;margin: 0 auto;color: red;"></div>
							<div class="text-right">
								<button class="btn btn-primary" type="submit" onclick="register()">提交</button>
								<button class="btn btn-danger" data-dismiss="modal">取消</button>
							</div>
					</div>
				</div>
			</div>
		</div>

		<!-- 登录窗口 -->
		<div id="loginform" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<button class="close" data-dismiss="modal">
							<span>&times;</span>
						</button>
					</div>
					<div class="modal-title">
						<h1 class="text-center">登录</h1>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label>用户名</label> <input class="form-control" type="text" 
								id="luser" placeholder="请输入账号">
						</div>
						<div class="form-group">
							<label>密码</label> <input class="form-control" type="password"
								id="pass" placeholder="请输入密码">
						</div>
<!-- 						<div class="form-group"> -->
<!-- 							<label>验证码</label><br/> <input type="text" id="checkcode"> -->
<!-- 							<img id="code" src="checkcode" onclick="getCode()" /> -->
<!-- 						</div> -->
						<div id="info" style="width: 270px;height: 50px;margin: 0 auto;color: red;text-align: center;font-size: 15px;"></div>
						<div class="text-right">
							<button class="btn btn-primary" type="submit" onclick="login()">登录</button>
							<button class="btn btn-danger" data-dismiss="modal">取消</button>
						</div>

					</div>
				</div>
			</div>
		</div>
		<!-- 个人信息 -->
		<div id="userinfoform" class="modal fade" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<button class="close" data-dismiss="modal">
							<span>&times;</span>
						</button>
					</div>
					<div class="modal-title">
						<h1 class="text-center">个人信息</h1>
					</div>
					<div class="modal-body">
						<div class="form-group">
						<label>更改头像</label><input id="userhead" type="file" name="userhead" />  
						</div>
						<div class="form-group">
								<label>用户名</label> <input class="form-control"
									id="userinfo" type="text">
							</div>
							<div class="form-group">
								<label>姓名</label> <input class="form-control"
									id="uname" type="text">
							</div>
							<div class="form-group">
								<label>手机号</label> <input class="form-control"
									id="phone" type="text">
							</div>
							<div class="form-group">
								<label>年龄</label> <input class="form-control"
									id="age" type="text">
							</div>
							<div class="form-group">
								<label>性别</label> <input class="form-control"
									id="sex" type="text">
							</div>
							<div class="form-group">
								<label>住址</label> <input class="form-control"
									id="addr" type="text">
							</div>
							<div id="queryuserinfo" style="width: 270px;height: 50px;margin: 0 auto;color: red;"></div>
							<div class="text-right">
								<button class="btn btn-primary" type="submit" onclick="updateuser()">提交</button>
								<button class="btn btn-danger" data-dismiss="modal">取消</button>
							</div>
					</div>
				</div>
			</div>
		</div>


		<!-- 		添加新书 -->
		<div id="addbookform" class="modal fade" tabindex="-1">
			<div class="modal-dialog" style="height:500px">
				<div class="modal-content"  >
					<div class="modal-body">
						<button class="close" data-dismiss="modal">
							<span>&times;</span>
						</button>
					</div>
					<div class="modal-title">
						<h1 class="text-center">增加新书</h1>
					</div>
					<div class="modal-body" >
						<div class="form-group">
							<label>书名</label> <input class="form-control" type="text" 
								id="bname" placeholder="请输入书名">
						</div>
						<div class="form-group">
							<label>作者</label> <input class="form-control" type="text" 
								id="author" placeholder="请输入作者">
						</div>
						<div class="form-group">
							<label>价格</label> <input class="form-control" type="text" 
								id="price" placeholder="请输入价格">
						</div>
						<div class="form-group">
							<label>库存数量</label> <input class="form-control" type="text" 
								id="bcount" placeholder="请输入库存数量">
						</div>
						<div class="form-group">
							<label>出版日期</label> <input class="form-control" type="text" 
								id="pubdate" placeholder="请输入出版日期">
						</div>
						<div class="form-group">
							<label>出版社</label> <input class="form-control" type="text" 
								id="press" placeholder="请输入出版社">
						</div>
						<div class="form-group">
							<label>图片信息</label> <input class="form-control" type="text" 
								id="img" placeholder="请输入图片信息">
						</div>
						<div class="form-group">
							<label>书籍介绍</label> <input class="form-control" type="text" 
								id="intro" placeholder="请输入书籍介绍">
						</div>
						<div id="addbookinfo" style="width: 270px;height: 50px;margin: 0 auto;color: red;"></div>
						<div class="text-right">
							<button class="btn btn-primary" type="submit" onclick="addbook()">确定新增</button>
							<button class="btn btn-danger" data-dismiss="modal">取消</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 		更新内容 -->
		
		<div id="updatebookform" class="modal fade" tabindex="-1">
			<div class="modal-dialog" style="height:500px">
				<div class="modal-content"  >
					<div class="modal-body">
						<button class="close" data-dismiss="modal">
							<span>&times;</span>
						</button>
					</div>
					<div class="modal-title">
						<h1 class="text-center">更新内容</h1>
					</div>
					<div class="modal-body" >
						<div class="form-group">
							<label>序列号</label> <input class="form-control" type="text" 
								id="ubid"> 
						</div>
						<div class="form-group">
							<label>书名</label> <input class="form-control" type="text" 
								id="ubname"> 
						</div>
						<div class="form-group">
							<label>作者</label> <input class="form-control" type="text" 
								id="uauthor">
						</div>
						<div class="form-group">
							<label>价格</label> <input class="form-control" type="text" 
								id="uprice">
						</div>
						<div class="form-group">
							<label>库存数量</label> <input class="form-control" type="text" 
								id="ubcount">
						</div>
						<div class="form-group">
							<label>出版日期</label> <input class="form-control" type="text" 
								id="upubdate">
						</div>
						<div class="form-group">
							<label>出版社</label> <input class="form-control" type="text" 
								id="upress">
						</div>
						<div class="form-group">
							<label>图片信息</label> <input class="form-control" type="text" 
								id="uimg">
						</div>
						<div class="form-group">
							<label>书籍介绍</label> <textarea class="form-control"  type="text" 
								id="uintro" style="height: 100px;"></textarea>
						</div>
						<div id="updatebookinfo" style="width: 270px;height: 50px;margin: 0 auto;color: red;"></div>
						<div class="text-right">
							<button class="btn btn-primary" type="submit" onclick="updatebook(${book.bid})">确定更新</button>
							<button class="btn btn-danger" data-dismiss="modal">取消</button>
							
						</div>
						
					</div>
				</div>
			</div>
		</div>
		
<%-- 		<s:debug></s:debug> --%>

		<!-- 商品图片容器 -->
		<div  class="container" id="booksbox" style="height:2500px;width:1000px;margin: 40px auto;float:none; ">
			<div  class="row" id="blogContents" style="transition: all 1s;width:1000px;height:130px;float:none;">
			</div>
		</div>


		<!-- 页码 -->
		<div class="container" id="totalpagebox" style="margin: 0 auto;width: 700px;height: 200px;">
			<div class="row" id="pagerow" style="padding-left: 150px;">
				<ul class="pagination pagination-lg" id="pagebox">
					<li id="first" onclick="findAllBlog(1)"><span>首页</span></li>
					<li id="top" onclick="prepage()"><span>上一页</span></li>
					<li class="xifenye" id="xifenye"><span><span
							id="currentpage">1</span>/ <span id="totalpage">1</span></span></li>
					<li id="down" onclick="nextpage()"><span>下一页</span></li>
					<li id="last" onclick="endpage()"><span>末页</span></li>
				</ul>
			</div>
		</div>
		<!-- 底部 -->
		<div class="container" id="downbox" style="width: 100%;height: 200px;float: left;background: url(../../static/img/24-2.jpg);background-size: 150%;
			text-align: center;line-height: 200px;font-size: 15px;">
			<div class="row" id="downrow">
				©2020-OnLu <a href="http://icp.chinaz.com/info?q=luxiyi.top" target="_blank"
							  style="display:inline-block;text-decoration:none;height:20px;line-height:20px;color: black;">
				蜀ICP备20008951号</a>

				<a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=51012402000457"
				   style="display:inline-block;text-decoration:none;height:20px;line-height:20px;color: black;">
					<img src="../../static/img/beian.png" style="float:left;"/>
					川公网安备51012402000457号</a>
			</div>
	</div>

</body>

</html>