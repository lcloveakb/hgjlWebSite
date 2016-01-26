<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册</title>
    
	<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/r29/html5.min.js"></script>
    <![endif]-->
<link rel="stylesheet" type="text/css" href="lib/css/style.css" />
  <script type="text/javascript" src="lib/js/jquery.min.js"></script>
        <script type="text/javascript">
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
        	var username,password,email;
        	var usernameReg = /[A-Za-z0-9_]{6,19}/;
        	var passwordReg = /^[a-zA-Z]\w{7,19}$/;
        	var emailReg = /^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
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
        			showError("password", "密码必须以字母开头，长度8-20个数字、字母和下划线组合");
        			return false;
        		} else {
        			$("#password").removeClass("errorColor");
        			$("#password").parent().find("span").remove();
        		}
        		if($("#password2").val()!=password) {
        			showError("password2", "两次密码输入不一致");
        			return false;
        		} else {
        			$("#password2").removeClass("errorColor");
        			$("#password2").parent().find("span").remove();
        		}
        		email = $("#email").val();
        		if(!emailReg.test(email)){
        			showError("email", "邮箱格式不正确");
        			return false;
        		} else {
        			$("#email").removeClass("errorColor");
        			$("#email").parent().find("span").remove();
        		}
        		return true;
        	}
        	$(window).ready(function(){
        		$('.btn_register').bind("click",function(){
        			if(!check())
            			return false;
        			register();
        		});
        	});
        	function register() {
        		$.ajax({
    				url:'user/userAction_register.action',
    				data:{"username":username,"password":password,"email":email },
    				dataType:'json',
    				type:'post',
    				beforeSend:function(){
    					$('#beforeSend').show();
    				},
    				success:function(result){
    					$('#beforeSend').hide();
    					var code = result.ajaxStatus;
    					if(code==1) {
    						window.confirm("请登录邮箱激活账号！");
    						location.href = "/";
    					} else if(code==0) {
    						alert("注册失败，请稍后重试！");
    					} else if(code==-1) {
    						showError(result.errorInput,result.msg);
    					}
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
                          <h3>注册</h3>
                          <form action="" class="form">
                            <dl>
                              <dt><label for="username">用户名</label></dt>
                              <dd><input type="text" name="username" value="" id="username" class="ipt username" size="30"></dd>
                              <dt><label for="password">密码</label></dt>
                              <dd><input type="password" name="password" value="" id="password" class="ipt" size="30"></dd>
                              <dt><label for="passwords">确认密码</label></dt>
                              <dd><input type="password" value="" id="password2" class="ipt" size="30"></dd>
                              <dt><label for="mail">邮箱</label></dt>
                              <dd><input name="email" value="" id="email" maxlength="80" class="ipt" size="30"></dd>
                              <dd class="right"><button type="button" class="btn btn_register">注册</button></dd>
                              <dd>已有账户? <a href="user/userAction_toLogin.action">登录</a></dd>
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
       