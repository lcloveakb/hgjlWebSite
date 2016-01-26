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

<title>找回密码</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/r29/html5.min.js"></script>
    <![endif]-->
<link rel="stylesheet" type="text/css" href="lib/css/style.css" />
<script type="text/javascript" src="lib/js/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$('.btn_findPwd').on("click",function(){
		findPwd();
	});
});
function showError(inputid,error) {
	var $input = $("#"+inputid);
	var $parent = $input.parent();
	var $tip = $parent.find(".tip");
	if($tip.length>0) {
		$tip.addClass("inError");
		$tip.text(error);
	} else {
		$parent.append("<span class='tip inError'>"+error+"</span>");
	}
	if(!$input.hasClass("errorColor")) {
		$input.addClass("errorColor");
	}
}
function findPwd(){
	$.ajax({
		url:'user/userAction_confirmStatus.action',
		data:{"email":$("#mail").val()},
		datatype : 'json',
		type : 'post',
		success:function(result){
			var code = result.ajaxStatus;
			var mgs = result.msg;
			if(code==1) {
				$("#mail").removeClass("errorColor");
    			$("#mail").parent().find("span").remove();
				window.confirm(mgs);
				location.href = "/";
			} else if(code==-1) {
				showError("mail", mgs);
			} else {
				alert("未知结果："+code);
			}
		},
		error: function() {
			alert("认证失败");
		}
	});
}

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
							<div class="login">
								<h3>找回密码</h3>
								<form action="" class="form pwdform">
									<dl>
										<dt>
											<label for="mail">请输入您注册时使用的邮箱</label>
										</dt>
										<dd>
											<input type="text" value="" id="mail" maxlength="80"
												class="ipt" size="30">
										</dd>
										<dd class="right">
											<button type="button" class="btn btn_findPwd">找回密码</button>
										</dd>
										<dd>
											已有账户? <a href="user/userAction_toLogin.action">登录</a>
										</dd>
									</dl>
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