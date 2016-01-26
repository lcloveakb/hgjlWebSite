<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>云云创客</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/r29/html5.min.js"></script>
    <![endif]-->

 <link rel="stylesheet" type="text/css" href="lib/css/style.css" />
<style type="text/css">
p {
margin: 15 5px;
}
</style>
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
	                            <div style="float: left; border: 1px solid #ccc; margin: 30px;">
	                            	<a href="${weiboUrl }">
	                            		<img style="margin: 10px;" alt="${username }" title="${username }" src="${imageUrl }">
	                            	</a>
	                            </div>
	                            <div style="margin-top: 30px; padding: 30px;">
		                            <p>昵称：&emsp;<a href="${weiboUrl }" id="username"></a></p><br>
		                            <p id="description"></p><br>
		                            <p id="location"></p><br>
		                            <button id="btn_bind">绑定已有账号</button>
	                            </div>
	                            
	                             <div class="login" style="margin-top: 40px;"> 
								<form action="" id="login" class="form" style="display: none;">
									<dl>
										<dt>
											<label for="username">用户名</label>
										</dt>
										<dd>
											<input type="text" name="username" value="" id="uname"
												class="ipt" size="30">
										</dd>
										<dt>
											<label for="password">密码</label>
										</dt>
										<dd>
											<input type="password" name="password" value="" id="password"
												class="ipt" size="30">
										</dd>
										<dd class="right">
											<button type="button"
												style=" display: inline-block; height: 36px; padding: 6px 14px; border: none; background-color: #0078e7; border-bottom: 3px solid #456789; color: #fff;"
												class="btn_Bind">Bind</button>
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
        <script type="text/javascript" src="lib/js/jquery.min.js"></script>
		<script type="text/javascript">
		var weiboid, username, password;
		function weiboBind() {
			console.log(username);
			console.log(password);
			console.log(weiboid);
			$.ajax({
				url : 'user/userAction_accountBind.action',
				data : {
					"weiboId" : weiboid,
					"username" : username,
					"password" : password
				},
				datatype : 'json',
				type : 'post',
				success : function(result) {
					if(result.ajaxStatus==1){
						location.href='index/indexAction_forward.action';
					}
				}
			});
		}
			function getParameter(){
				var weiboId = GetQueryString('weiboId');   //微博id
				var username = GetQueryString('username');	//昵称
				var description = GetQueryString('description'); //简介
				var location = GetQueryString('location'); //地址
				var weiboUrl = GetQueryString('weiboUrl'); //连接
				$("#username").html(username);
				$("#username").attr(weiboUrl);
				$("#weiboId").val(weiboId);
				$("#description").html('简介：&emsp;'+description);
				$("#location").html('地址：&emsp;'+location);
			}
			
			function GetQueryString(name){   //解码
				var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			     var r = window.location.search.substr(1).match(reg);
			     if(r!=null)return  decodeURI(r[2]); return null;
			}
		
			$(function(){
				
				getParameter();  
				weiboid = '${weiboId}';
				var no=0;  //计数器
				$('#btn_bind').click(function(){
					
					if(no%2==0){
						$("#login").show();
					no++;
					}else{
						$("#login").hide();
						no++;
					}
				});
				
				$('.btn_Bind').bind("click", function() {
					username = $("#uname").val();
					password = $("#password").val();
					console.log(username);
					console.log(password);
					if (username == "") {
						console.log('username is null');
						return false;
					} else if (password == "") {
						console.log('password is null');
						return false;
					} else if (weiboid == "") {
						console.log('weiboid is null');
						return false;
					} else {
						weiboBind();
					}
				});
			});
		</script>
        <!-- footer -->
        <jsp:include page="../index/footer.jsp"></jsp:include>
        <!-- /footer -->
       