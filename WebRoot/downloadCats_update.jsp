<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="lib/css/admin/type_style.css">

  </head>
  
  <body>
   <form action="<%=path %>/download/downloadAction_catUpdate.action" method="post">
<pre>
	   <input type="hidden" name="cat.id" value="${cat.id }"><br>
         类型名称：<input type="text" name="cat.name" value="${cat.name }"><br>
   		    <input type="submit" value="提交">
</pre>
   </form>
  </body>
</html>
