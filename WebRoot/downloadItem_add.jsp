
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
	<link rel="stylesheet" type="text/css" href="lib/css/admin/editor_style.css">
  </head>
  <body>
  	<div id="container">
	<form action="download/downloadAction_itemAdd.action" method="post" enctype="multipart/form-data">
		资源标题：<input type="text" name="title"><br>
	   	资源类别：<select name="category" style="height: 30px">
				   	<option>--请选择--</option>
				   	<c:forEach items="${catsList }" var="cat">
				   		<option value="${cat.id }">${cat.name }</option>
				   	</c:forEach>
				   </select><br>
		资源文件：<input type="file" name="fileUp"><br>
	   <input type="submit" value="添加" style="width: 50px;margin: 0">
	</form>
	</div>
  </body>
</html>
