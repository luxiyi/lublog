<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
							Object obj = session.getAttribute("user");
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
						<li><a href="/order">借阅信息</a></li>
						<li><a><span>欢迎来到BookLu</span></a></li>
						<li style="width:40px;"><div class="contentDiv" style="display:inline-block;margin:5px auto;
							width:40px;height:40px;border-radius:100px;-webkit-border-radius:100px;-moz-border-radius:100px;
								border:2px solid #fff;box-shadow:0 0 4px #ccc;overflow:hidden;">
						<img alt="头像" src="../../static/img/${userinfo.usericon}" style="width:100%;min-height:100%;"/></div></li>
						<li><a href="#" style="padding-left:1px;" data-toggle="modal" data-target="#userinfoform"  onclick="queryuser()">
								${userinfo.luser}</a></li>
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
					<div class="butto" style="width: 70px;height:50px;float:left; padding-top:2px;">
					</div>
				</div>
				<div class="col-md-4">
					<!-- 购物车 -->
					<div class="shopcar" style="border: 1px orangered solid;height: 46px;width: 160px;margin-top: 30px;margin-left: 250px;padding-left: 10px;">
						<a href="/cart" class="btn btn-group btn-lg"
							style="color: orange;"> <span
							class="glyphicon glyphicon-shopping-cart"></span>&nbsp;&nbsp;&nbsp;我的书架
						</a>
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
