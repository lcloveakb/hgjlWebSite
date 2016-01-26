<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/r29/html5.min.js"></script>
    <![endif]-->
<link rel="stylesheet" type="text/css" href="lib/css/style.css" />
<script type="text/javascript" src="lib/js/jquery.min.js"></script>
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
					<!-- mod -->
					<div class="article">
						<div class="spy" data-offset-spy="10">
							<nav class="side-nav">
								<ul class="people-nav fixed">
									<c:forEach items="${cats }" var="c" varStatus="status">
										<li><a href="#${c.name }" class="category">${c.name }</a></li>
									</c:forEach>

								</ul>
							</nav>
						</div>
						<article class="post">
							<header class="entry-header">
								<h1 class="entry-title">开发者</h1>
							</header>
							<section class="entry-content">
								<c:forEach items="${cats }" var="c">
									<h3 id="${c.name }">${c.name }</h3>
									<div class="mod">
										<ul class="mod-list">
											<c:forEach items="${items }" var="i">
												<c:if test="${c.id==i.categoryId }">
													<li><a href="developer/developerAction_detail.action?id=${i.id }"> 
													<i class="photo-img">
														<img src="${i.path }/${i.name }" width="250" height="187" alt="PROTO" data-bd-imgshare-binded="1">
													</i>
															<div class="photo-info">
																<p>
																	<span class="blue">${i.title }</span>
																</p>
															</div>
													</a></li>
												</c:if>
											</c:forEach>
										</ul>
									</div>
								</c:forEach>
							</section>
						</article>
					</div>
					<div class="share">
						<h3>分享至</h3>
						<div class="share_list">
							<div class="bdsharebuttonbox">
								<a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a> 
								<a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
								<a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a>
								<a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a>
								<a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
							</div>
						</div>
					</div>
				</section>
			</div>
		</main>
		<!-- /main -->
		
		<script type="text/javascript" src="../lib/js/jquery.min.js"></script>
		<script type="text/javascript">
			window.onload=function (){
				$(function(){
					$(".category").click(function(){
						var name=$(this).attr("href");
						window.location="developer/developerAction_toIndex.action"+name;
						return false;
					});
				});
			};
		</script>

		<!-- footer -->
		<jsp:include page="../index/footer.jsp"></jsp:include>
		<!-- /footer -->