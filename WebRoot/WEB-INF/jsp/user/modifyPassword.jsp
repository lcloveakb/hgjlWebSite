<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>重置密码</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/r29/html5.min.js"></script>
    <![endif]-->
<link rel="stylesheet" type="text/css" href="lib/css/style.css" />
<script type="text/javascript" src="lib/js/jquery-1.9.0.js"></script>
<script type="text/javascript">
$(window).ready(function(){
	$("#yes").bind("click",function(){
		var p1 = $("#p1").val();
		var passwordReg = /^[a-zA-Z]\w{7,19}$/;
		if(!passwordReg.test(password)) {
			alert("密码必须以字母开头，长度8-20个数字、字母和下划线组合");
			return false;
		}
		var p2 = $("#p2").val();
		if(p1!=p2) {
			alert("两次密码输入不一致");
			return false;
		}
		return true;
	});
});
</script>
</head>

<body>
	<div class="wrapper">
		<!-- header -->
		<div class="nav-banner" id="navBanber"></div>
		<jsp:include page="../index/header.jsp"></jsp:include>

		<!-- main -->
		<main class="main">
			<div class="container row">
				<section class="content item">
					<!-- mod -->
					<div class="login-mod">
						<div class="mod">
							<div class="login" style="line-height: 70px">
								<form action="user/userAction_modifyPassword.action" method="post">
									<input type="hidden" name="username" value="${user.username }" style="height: 30px"/>
									您正在为账号&emsp;<span style="color:red;font-size: 25px;font-family: consolas;font-style: italic;">${user.username }</span>&emsp;找回密码<br>
									<span style="font-size: 20px">请输入密码&emsp;</span><input type="password" name="password" id="p1" style="height: 40px;font-size: 20px"/><br>
									<span style="font-size: 20px">请确认密码&emsp;</span><input type="password" id="p2" style="height: 40px;font-size: 20px"/><br>
									<input type="submit" value="确认修改" id="yes" style="padding:0px 10px;height: 40px;line-height: 40px;vertical-align: middle;letter-spacing: 2px">
								</form>
							</div>
						</div>
					</div>

				</section>
			</div>
		</main>
		<!-- /main -->


		<!-- footer -->
		<jsp:include page="../index/footer.jsp"></jsp:include>
		<!-- /footer -->