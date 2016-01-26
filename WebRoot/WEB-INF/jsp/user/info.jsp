<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set value="${sessionScope.user }" var="u"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>云云创客</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="lib/css/style.css" />
<style type="text/css">
input[type=text] {
width: 300px;
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
                	<div class="login-mod">
                		<div class="mod" style="padding-left: 45px;padding-top: 40px">
		                   <p style="font-size: 24px; font-weight: normal; color: #0078d7;">个人信息</p>
		                   <br>
		                   <fieldset class="fieldset" data-id="1">
		                   		<legend style="font-style: italic;letter-spacing: 2px;padding-left: 5px;padding-right: 5px;">基本信息</legend>
		                   <!-- <a href="javascript:void(0);">
			                   <div class="edit" style="height: 35px;line-height: 35px;vertical-align: middle">
			                   		<i></i>
			                   		<span>点击修改</span>
			                   </div>
		                   </a> -->
			                   	<div class="info-mod" style="line-height: 30px;font-size: 13px;color:#808080">
				                   <p>账&emsp;&emsp;号：${u.username }</p>
				                   <p>邮&emsp;&emsp;箱：${u.email }</p>
			                   </div>
		                   </fieldset>
		                   <br><br>
		                   <fieldset class="fieldset" data-id="2">
		                   		<legend style="font-style: italic;letter-spacing: 2px;padding-left: 5px;padding-right: 5px;">其他信息</legend>
			                   <div class="info-mod" style="line-height: 30px;font-size: 13px;color:#808080">
			                   		<p>积&emsp;&emsp;分：${u.credits }</p>
			                   		<p>等&emsp;&emsp;级：${u.level }</p>
			                   		<p>注册时间：<fmt:formatDate value="${u.createTime }" type="both" pattern="yyy-MM-dd HH:mm"/></p>
			                   </div>
			                </fieldset>
							<p style="color: grey;margin-top: 20px;font-size: 12px;font-style: italic">上次登录：<fmt:formatDate value="${sessionScope.user.loginTime }" type="both" pattern="yyyy-MM-dd HH:mm"/> </p>
	                   </div>
                   </div>
                </section>
        		<div id="beforeSend"
					style="display: none; width: 1000px; height: auto;  position: fixed; top: 350px; text-align: center;">
					<img alt="loading" src="images/admin/refresh.gif">
				</div>
            </div>
        </main>
        <!-- /main -->
        
        

        
        <!-- footer -->
        <jsp:include page="../index/footer.jsp"></jsp:include>
        <!-- /footer -->
  </body>
</html>
