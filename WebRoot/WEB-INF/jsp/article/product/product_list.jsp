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

<title>产品</title>

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
		<jsp:include page="../../index/header.jsp"></jsp:include>

		<!-- main -->
		<main class="main">
			<div class="container row">
				<section class="content item">
					<!-- mod -->
					<div class="article">
						<article class="post">
							<header class="entry-header">
								<h1 class="entry-title">产品</h1>
							</header>
							<section class="entry-content">
							<h3 id="reader">读写器</h3>
									<div class="mod">
										<ul class="mod-list">
											<s:iterator value="readers" var="r">
												<c:choose>
													<c:when test="${r.imgs.length()==0}">
													<li>
														<a href="product/productAction_detail.action?id=${r.id }"> 
															<i class="photo-img">
																<img alt="300x200" src='images/admin/default_b.jpg' width="300" height="200" />
															</i>
															<div class="photo-info">
																<p>
																	<span class="blue">${r.title }</span>
																</p>
															</div>
														</a>
													</li>
													</c:when>
													
													<c:otherwise>
														<li><a href="product/productAction_detail.action?id=${r.id }"> 
													<i class="photo-img">
														<s:set name="endIndex" value='#r.imgs.indexOf(";")'></s:set>
														<img width="300" height="200"  src='images/article/products/<s:property value="#r.imgs.substring(0,#endIndex)"/>' />
													</i>
															<div class="photo-info">
																<p>
																	<span class="blue">${r.title }</span>
																</p>
															</div>
														</a></li>
													</c:otherwise>
												</c:choose>
											</s:iterator>
										</ul>
									</div>
									
							<h3 id="tag">标签</h3>
									<div class="mod">
										<ul class="mod-list">
										<s:iterator value="tags" var="t">
										<c:choose>
											<c:when test="${t.imgs.length()==0}">
												<li>
														<a href="product/productAction_detail.action?id=${t.id }"> 
															<i class="photo-img">
																<img alt="300x200" src='images/admin/default_b.jpg' width="300" height="200" />
															</i>
															<div class="photo-info">
																<p>
																	<span class="blue">${t.title }</span>
																</p>
															</div>
														</a>
													</li>
											</c:when>
											<c:otherwise>
											<li><a href="product/productAction_detail.action?id=${t.id }"> 
													<i class="photo-img">
														<s:set name="endIndex" value='#t.imgs.indexOf(";")'></s:set>
														<img width="300" height="200"  src='images/article/products/<s:property value="#t.imgs.substring(0,#endIndex)"/>' />
													</i>
															<div class="photo-info">
																<p>
																	<span class="blue">${t.title }</span>
																</p>
															</div>
														</a></li>
											</c:otherwise>
										</c:choose>
											</s:iterator>
										</ul>
									</div>
									
							<h3 id="others">其他</h3>
									<div class="mod">
										<ul class="mod-list">
										<s:iterator value="others" var="o">
											<c:choose>
												<c:when test="${o.imgs.length()==0}">
												<li>
														<a href="product/productAction_detail.action?id=${o.id }"> 
															<i class="photo-img">
																<img alt="300x200" src='images/admin/default_b.jpg' width="300" height="200" />
															</i>
															<div class="photo-info">
																<p>
																	<span class="blue">${o.title }</span>
																</p>
															</div>
														</a>
													</li>
											</c:when>
											<c:otherwise>
											<li><a href="product/productAction_detail.action?id=${o.id }"> 
													<i class="photo-img">
														<s:set name="endIndex" value='#o.imgs.indexOf(";")'></s:set>
														<img width="300" height="200"  src='images/article/products/<s:property value="#o.imgs.substring(0,#endIndex)"/>' />
													</i>
															<div class="photo-info">
																<p>
																	<span class="blue">${o.title }</span>
																</p>
															</div>
														</a></li>
											</c:otherwise>
											</c:choose>
										</s:iterator>
										</ul>
									</div>
									
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






		<!-- footer -->
		<jsp:include page="../../index/footer.jsp"></jsp:include>
		<!-- /footer -->