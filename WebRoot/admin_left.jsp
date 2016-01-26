<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<link href="lib/css/left_style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.title {
	padding-left: 30px;
}
</style>
</head>

<body style="background:#f0f9fd;">
	<div class="lefttop">
		<span></span>全部功能
	</div>

	<dl class="leftmenu">



		<dd>
			<div class="title">
				<span></span>开发者
			</div>
			<ul class="menuson">
				<li><cite></cite><a target="rightFrame" href="developer/categoryAction_toCates.action">所有类别项</a><i></i></li>
				<li><cite></cite><a target="rightFrame" href="cats_add.jsp">增加类别项</a><i></i></li>
				
				<li><cite></cite><a target="rightFrame" target="rightFrame" href="developer/developerAction_toCatsList.action">所有子列表项</a><i></i></li>
				<li><cite></cite><a target="rightFrame" target="rightFrame" href="developer/developerAction_toAdd.action">增加子列表项</a><i></i></li>
			</ul>
		</dd>


		<dd>
			<div class="title">
				<span></span>下载
			</div>
			<ul class="menuson">
				<li><cite></cite><a href="download/downloadAction_catsList.action" target="rightFrame">查看资源类别</a><i></i></li>
				<li><cite></cite><a href="download/downloadAction_toCatAdd.action" target="rightFrame">增加资源类别</a><i></i></li>
				<li><cite></cite><a href="download/downloadAction_toAddItem.action" target="rightFrame">上传资源</a><i></i></li>
				<li><cite></cite><a href="download/downloadAction_itemList.action" target="rightFrame">所有资源查看</a><i></i></li>
			</ul>
		</dd>


		<dd>
			<div class="title">
				<span></span>产品
			</div>
			<ul class="menuson">
				<li><cite></cite><a target="rightFrame" href="product/productAction_listInCategoryBackend.action">查看产品</a><i></i></li>
				
				<li><cite></cite><a target="rightFrame" href="product/productAction_toAdd.action">增加产品</a><i></i></li>
			</ul>

		</dd>


		<dd>
			<div class="title">
				<span></span>新闻
			</div>
			<ul class="menuson">
				<li><cite></cite><a target="rightFrame" href="news/newsAction_listInCategoryBackend.action">查看新闻</a><i></i></li>
				<li><cite></cite><a href="news_add.jsp" target="rightFrame">增加新闻</a><i></i></li>
			</ul>


	</dl>

	<script language="JavaScript" src="lib/js/jquery.min.js"></script>
	<script type="text/javascript">
		$(function() {
			//导航切换
			$(".menuson li").click(function() {
				$(".menuson li.active").removeClass("active");
				$(this).addClass("active");
			});

			$('.title').click(function() {
				var $ul = $(this).next('ul');
				$('dd').find('ul').slideUp();
				if ($ul.is(':visible')) {
					$(this).next('ul').slideUp();
				} else {
					$(this).next('ul').slideDown();
				}
			});
		});
		
		
	</script>
	
	
	
	
</body>
</html>

