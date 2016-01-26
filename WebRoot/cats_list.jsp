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
    
    <title>My JSP 'cats_list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="lib/css/admin/list_style.css">
	
	<script type="text/javascript" src="lib/js/jquery-1.9.0.js"></script>
	<script type="text/javascript">
		window.onload = function(){
			$("a.del_href").each(function(){
				$(this).unbind("click");
				$(this).bind("click",function(){
					if(window.confirm("此操作会删除该类目下所有的子项")) {
						$.ajax({
							url : "developer/developerAction_deleteCat.action",
							type : "post",
							data : {
								"categoryid" : a.attr("id")
							},
							datatype : "json",
							success : function(result) {
								if (result.ajaxStatus) {
									alert("删除成功");
									window.location.reload();
								} else {
									alert("删除失败");
								}
							}
						});
					}
				});
			});
		};
	</script>

  </head>
  
  <body>
    <table class="data_list" cellpadding="10" align="center">
    	<tr>
    		<td>开发者类别名称</td>
    		<td colspan="2">操作</td>
    	</tr>
    	<c:forEach items="${cats }" var="cat">
   			<tr>
   				<td>${cat.name }</td>
   				<td><a href="developer/categoryAction_toCatUpdate.action?cat.id=${cat.id }">修改</a></td>
   				<td><a id="${cat.id }" class="del_href">删除</a></td>
   			</tr>
   		</c:forEach>
    </table>
  </body>
</html>
