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
    <script type="text/javascript" src="lib/js/jquery-1.9.0.js"></script>
	<script type="text/javascript">
		window.onload = function(){
			$("a.del_href").each(function(){
				$(this).unbind("click");
				$(this).bind("click",function(){
					return window.confirm("此操作会删除该类目下所有的子项");
				});
			});
		};
	</script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="lib/css/admin/list_style.css">

  </head>
  
  <body>
  <table class="data_list" cellpadding="10" align="center">
    	<tr><td>资源类别名称</td><td colspan="2">操作</td></tr>
    	 <c:forEach items="${catsList }" var="cat">
    	<tr><td>${cat.name }</td><td><a href="download/downloadAction_toCatUpdate.action?cat.id=${cat.id }">修改</a></td><td><a href="<%=path%>/download/downloadAction_catDelete.action?cat.id=${cat.id }" class="del_href">删除</a></td></tr>
    </c:forEach>
    </table>
  </body>
</html>
