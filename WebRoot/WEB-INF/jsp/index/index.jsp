<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>云云创客-SenseMaker</title>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="<%=basePath%>/favicon.ico">
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/r29/html5.min.js"></script>
    <![endif]-->
<link rel="stylesheet" type="text/css" href="lib/css/style.css" />
</head>
<body>
	<div class="wrapper">
		<!-- header -->
		<div class="nav-banner" id="navBanber"></div>
		<jsp:include page="header.jsp"></jsp:include>
		<!-- main -->
		<main class="main">
			<div class="container row">
				<section class="content item">
					<!-- mod -->
					<div class="module"></div>
				</section>
				<div id="beforeSend"
					style="display: none; width: 1000px; height: auto;  position: fixed; top: 550px; text-align: center;">
					<img alt="loading" src="images/admin/refresh.gif">
				</div>
				<div id="arrive"
					style="display: none; color: #DBDBDB; width: 1000px; height: auto;  text-align: center; font-size: 24px; font-weight: 800; ">
					<i>没有数据啦</i>
				</div>
			</div>
		</main>
		<!-- /main -->
		<script type="text/javascript" src="lib/js/jquery.min.js"></script>
		<script type="text/javascript">
			var pageNo, pageCount, str, flag = 0, height = 0, target = 250;
			$(function() {
				//页面加载
				send(1);

				// 滚动条监听
				$(window).scroll(
						function() {
							if ($(document).scrollTop() > target
									&& $(document).scrollTop() > height) {
								if (flag == 0) {
									flag += 1;
									target += 250;
									send(pageNo + 1);
								} else if (pageNo > (pageCount / 6)) {
									// 						$("#arrive").show();
									return false;
								} else {
									return false;
								}
							} else {
								return false;
							}
						});
			});

			function send(param) {
				height = $(document).scrollTop();
				$
						.ajax({
							url : "index/indexAction_toIndex.action",
							data : {
								"pageno" : param
							},
							datatype : "json",
							type : "post",
							beforeSend : function() {
								$("#beforeSend").show();
							},
							success : function(result) {
								flag = 0;
								$("#beforeSend").hide();
								pageNo = result.pageno;
								pageCount = result.pagecount;
								str = "";
								$.each(
												result.articles,
												function(index, a) {
													str += "<div class='mod ";
													if (a.type == 0) {
														str += "' style='background-color: #f1f1f1'><a href='search/searchAction_itemSearch.action?type=" + a.type + "&id=" + a.articleId + "'>";
														str += "<div class='panel blog'><div class='pic'><img alt='"+a.indexImg+"' width='304px' height='224px' title='"+a.title+"' src='images/article/indeximg/"+a.indexImg+"'></div>";
														str += "<div class='tit'><span style='font-size: 20px; font-weight: 700;'>"
																+ a.title
																+ "<br>";
														/* if(a.brief.length<13){
															str+="<i style='font-size: 15px; font-weight: 200;'>"+a.brief+"</i></span></div>";
														}else{
															str+="<i style='font-size: 15px; font-weight: 200;'>"+a.brief.substring(0,13)+".........</i></span></div>";
														} */
														str += "</span></div>";
													}
													if (a.type == 2) {
														str += "pro' style='background-color: #fff'><a href='search/searchAction_itemSearch.action?type=" + a.type + "&id=" + a.articleId + "'>";
														str += "<div class='panel adv'><img alt='"+a.indexImg+"' width='304px' height='203px' title='"+a.title+"' style='position: absolute; bottom: 0;' src='images/article/indeximg/"+a.indexImg+"'>";
														if(a.articleId%3==0){
														str += "<div class='banner-head'><span>"
																+ a.title +"</span><br>";
														}
														if(a.articleId%3==1){
															str += "<div class='banner-head-1'><span>"
																	+ a.title +"</span><br>";
														}
														if(a.articleId%3==2){
															str += "<div class='banner-head-2'><span>"
																	+ a.title +"</span><br>";
															
														}
														if(a.brief.length<31){
															str+="<i style='font-size: 15px; font-weight: 200;'>"+a.brief+"</i></div>";
														}else{
															str+="<i style='font-size: 15px; font-weight: 200;'>"+a.brief.substring(0,31)+".......</i></div>";
														} 
														str += "</span></div>";
													}
													if (a.type == 1) {
														str += "pro' style='background-color: #fff'><a href='search/searchAction_itemSearch.action?type=" + a.type + "&id=" + a.articleId + "'>";
														str += "<div class='panel adv'><img alt='"+a.indexImg+"' width='304px' height='203px' title='"+a.title+"' style='position: absolute; bottom: 0;' src='images/article/indeximg/"+a.indexImg+"'>";
														str += "<div class='banner-head'><span>"
																+ a.title/* +"<br>" */;
														/* if(a.brief.length<31){
															str+="<i style='font-size: 15px; font-weight: 200;'>"+a.brief+"</i></span></div>";
														}else{
															str+="<i style='font-size: 15px; font-weight: 200;'>"+a.brief.substring(0,31)+".......</i></span></div>";
														} */
														str += "</span></div>";
													}
													str += "</div></a></div>";
												});
								$('.module').append(str);
							}
						});
					}
		</script>
		<!-- footer -->
		<jsp:include page="footer.jsp"></jsp:include>
		<!-- /footer -->