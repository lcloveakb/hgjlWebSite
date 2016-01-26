<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.opensymphony.xwork2.ActionContext" %> 
<%@ page import="org.apache.struts2.ServletActionContext" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html xmlns:wb="http://open.weibo.com/wb">
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>

<meta property="wb:webmaster" content="27a4029bb0d49d8b" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/r29/html5.min.js"></script>
    <![endif]-->
	 <link rel="stylesheet" type="text/css" href="lib/css/style.css" />
<script type="text/javascript" src="lib/js/jquery.min.js"></script>
<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=3482834530&debug=true" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">

$(window).ready(function() {
	$('.btn_login').on("click",function(){
		if(!check()) {
			return false;
		}
		sense_login();
	});
	weiboid = '${weiboId}'; //如果用微博登陆 并选择绑定
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
var username,password;
var usernameReg = /[A-Za-z0-9_]{6,19}/;
var passwordReg = /^[a-zA-Z]\w{7,19}$/;
function check() {
	username = $("#username").val();
	if(!usernameReg.test(username)){
		showError("username", "用户名只能包含数字、字母下划线，长度6-19个字符");
		return false;
	} else {
		$("#username").removeClass("errorColor");
		$("#username").parent().find("span").remove();
	}
	password = $("#password").val();
	if(!passwordReg.test(password)){
		showError("password", "密码必须以字母开头，8-20个数字、字母和下划线组合");
		return false;
	} else {
		$("#password").removeClass("errorColor");
		$("#password").parent().find("span").remove();
	}
	return true;
}
function sense_login(){
	$.ajax({
		url:'user/userAction_login.action',
		data:{"username":username,"password":password,"remember":$('#remember').val(),"weiboId" : weiboid },
		datatype:'json',
		type:'post',
		beforeSend:function(){
			$("#beforeSend").show();
		},
		success:function(result){
			$("#beforeSend").hide();
			var code = result.ajaxStatus;
			if(code==1){
				location.href= "/";
			} else  {
				showError(result.errorInput, result.msg);
			}
		}
	});
}

	function login(o) {
		console.log(o);
		$
				.ajax({
					url : 'user/userAction_checkWeiBo.action',
					data : {
						'weiboId' : o.idstr
					},
					datatype : 'json',
					type : 'post',
					success : function(result) {
						if (result.ajaxStatus == 0) { //该微博用户已有记录 直接登陆
							$
									.ajax({
										url : 'user/userAction_loginByAccount.action',
										data : {
											"weiboId" : o.idstr
										},
										datatype : 'json',
										type : 'post',
										beforeSend : function() {
											$("#beforeSend").show();
										},
										success : function(result) {
											$("#beforeSend").hide();
											var code = result.ajaxStatus;
											if (code == 1) {
												location.href = "index/indexAction_forward.action";
											} else {
												showError(result.errorInput,
														result.msg);
											}
										}
									});
						} else { //跳转至 微博信息页
							var weiboID = o.idstr; //微博id
							var name = o.screen_name; //微博昵称
							var addr = o.location; //微博介绍地址
							var description = o.description; //微博简介
							var imageUrl = o.avatar_large; //微博头像
							var weibo_url = "http://weibo.com/" + o.profile_url; //跳转到微博的url 
							location.href = "user/userAction_toWeiboInfo.action?weiboId="
									+ weiboID
									+ "&username="
									+ name
									+ "&location="
									+ addr
									+ "&description="
									+ description
									+ "&imageUrl="
									+ imageUrl
									+ "&weiboUrl=" + weibo_url;
						}
					}
				});

	}
</script>
  </head>
  
  
 <body>
 
 <%
 String pwd="",name="";
 HttpServletRequest reuqest = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	Cookie[] cookies = reuqest.getCookies();
	if(cookies!=null){
		for(int i=0;i<cookies.length;i++){
			if(cookies[i].getName().equals("num")){
				pwd=cookies[i].getValue();
			}
			if(cookies[i].getName().equals("num1")){
				name=cookies[i].getValue();
			}
		}
	}
 %>
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
                          <h3>登录</h3>
                          <form action="" class="form">
                            <dl>
                              <dt><label for="username">用户名</label></dt>
                              <dd><input type="text" name="username" value="<%=name %>" id="username" class="ipt" size="30"></dd>
                              <dt><label for="password">密码</label></dt>
                              <dd><input type="password" name="password" value="<%=pwd %>" id="password" class="ipt" size="30"></dd>
                              <dd class="check"><input type="checkbox" checked="checked" name="remember" value="1" id="remember">
                              	<label for="remember" style="display:inline">记住密码</label>
                              	<wb:login-button type="3,2" onlogin="login" onlogout="logout"></wb:login-button>
                              </dd>
                              <dd class="right"><button type="button" style=" display: inline-block; height: 36px; padding: 6px 14px; border: none; background-color: #0078e7; border-bottom: 3px solid #456789; color: #fff;" class="btn_login">Login</button></dd>
                              <dd><a href="user/userAction_toConfirmStatus.action">找回密码</a> 或 <a href="user/userAction_toRegister.action">注册</a></dd>
                            </dl>
                          </form>
                        </div>
                      </div>
                    </div>

                </section>
             <div id="beforeSend"
					style="display: none; width: 1000px; height: auto;  position: fixed; top: 350px; text-align: center;">
					<img alt="loading" src="<%=basePath %>images/admin/refresh.gif">
				</div>
            </div>
            </div>
        </main>
        <!-- /main -->
        
        
        <!-- footer -->
       <jsp:include page="../index/footer.jsp"></jsp:include>
        <!-- /footer -->
       