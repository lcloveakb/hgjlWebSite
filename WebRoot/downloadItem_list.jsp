<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<link rel="stylesheet" type="text/css" href="lib/css/admin/list_style.css">

</head>

<body>
	<select name="item.categoryId" id="cats">
		<option value="-1">--请选择资源类别--</option>
		<c:forEach items="${catsList }" var="cat">
			<option value="${cat.id }" <c:if test="${category eq cat.id}"> selected="selected" </c:if>>${cat.name }</option>
		</c:forEach>
	</select>
	
	  <table class="data_list" cellpadding="10" align="center">
    		<tr><td>资源名称</td><td>操作</td></tr>
    	<c:forEach items="${itemList }" var="item">
			<tr><td>${item.title }</td><td><a href="<%=path%>/download/downloadAction_itemDelete.action?id=${item.id }" class="del_href">删除</a></td></tr>
		</c:forEach>
    </table>
    
<script type="text/javascript" src="lib/js/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		
		$("#cats").change(function(){
			var categoryid=$("#cats").val();
			window.location.href="download/downloadAction_itemList.action?category="+categoryid;
		});
		
		$(".del_href").each(function(){
			var $a = $(this);
			$a.unbind("click");
			$a.bind("click",function(){
				return window.confirm("确认删除\n"+$a.parent().prev().text());
			});
		});
		
	});
</script>   	
	
	
	
</body>
</html>
