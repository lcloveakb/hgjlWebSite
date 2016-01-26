<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<link rel="stylesheet" type="text/css" href="lib/css/admin/editor_style.css">

  </head>
  
  <body>
   <form action="download/downloadAction_itemUpdate.action" method="post">
   	<input type="hidden" name="item.id" value="${item.id }">
   	<input type="hidden" name="item.categoryId" value="${item.categoryId }">
   	标题：<input type="text" name="item.title" value="${item.title }"><br>
   	路径:<input type="file" name="item.url" value="${item.url }"><br>
   	<input type="submit" style="margin-left: 0px;width: 40px" value="提交">
   </form>
  </body>
</html>
