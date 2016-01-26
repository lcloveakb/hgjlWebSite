<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>404</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css"> 

body{ 

width: 100%;

height: 100%;

FILTER: progid:DXImageTransform.Microsoft.Gradient(gradientType=0,startColorStr=#AEB4B8,endColorStr=#f6f6f8); /*IE 6 7 8*/ 

background: -ms-linear-gradient( #AEB4B8,  #f6f6f8);        /* IE 10 */

background:-moz-linear-gradient(top,#AEB4B8,#f6f6f8);/*火狐*/ 

background:-webkit-gradient(linear, 0% 0%, 0% 100%,from(#AEB4B8), to(#f6f6f8));/*谷歌*/ 

background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#AEB4B8), to(#f6f6f8));      /* Safari 4-5, Chrome 1-9*/

background: -webkit-linear-gradient(top, #AEB4B8, #f6f6f8);   /*Safari5.1 Chrome 10+*/

background: -o-linear-gradient(top, #AEB4B8, #f6f6f8);  /*Opera 11.10+*/


} 

</style> 

<link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="Bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="Bootstrap/css/style.css" rel="stylesheet">

  </head>
  
  <body style="overflow-x: hidden ">
   <center>
   		<img alt="" src="images/404.png">
   </center>
   <nav>
   	<ul class="pager">
   		<li><a href="javascript:history.go(-1);" style="padding: 20px 150px; font-size: 28px; line-height: 20px; border-radius: 35px;">点击返回</a>
   	</ul>
   </nav>
  </body>
</html>
