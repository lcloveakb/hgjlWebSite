<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<style type="text/css">
a:HOVER {
	text-decoration: none;
}
</style>
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
					<div class="article-l clearfix">
						<div class="posts left">
							<c:forEach items="${articleList }" var="result">
								<div class="post">
									<div class="entry-title">
										<a href="search/searchAction_itemSearch.action?id=${result.articleId }&type=${result.type }">${result.title }</a>
									</div>
									<div style="position: relative;height: 100px;margin-top: 10px">
										<c:choose>
											<c:when test="${result.indexImg.length()>0 }">
												<a href="search/searchAction_itemSearch.action?id=${result.articleId }&type=${result.type }">
													<img class="alignnone size-full wp-image-12216"
													 	style="float: left; margin-right: 20px;border: 1px solid #e0e0e0; width: 150px;height: 100px"
														src="images/article/indeximg/${result.indexImg }" data-bd-imgshare-binded="1">
												</a>
											</c:when>
											<c:otherwise>
												<img class="alignnone size-full wp-image-12216"
													style="float: left; margin-right: 20px;border: 1px solid #e0e0e0; width: 150px;height: 100px"
													src="lib/images/not_found.jpg" data-bd-imgshare-binded="1">
											</c:otherwise>
										</c:choose>
										<div style="float: left;width: 400px;font-size: 12px">
											<div style="height: 90px;line-height: 20px">
												&emsp;&emsp;${fn:substring(result.brief,0,140) } ......
											</div>
											<div style="height: 10px;">
												<div style="float: left;width: 300px;color: #b1b1b1;font-style: italic">
													&emsp;&emsp;Sense-ID，<fmt:formatDate value="${result.createTime }" type="both" pattern="yyyy-MM-dd" />
												</div>
												<div style="float: left;width: 100px;text-align: right">
													<a href="search/searchAction_itemSearch.action?id=${result.articleId }&type=${result.type }" class="more-link">
														查看详情 »</a>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
							<div class="share">
								<h3>分享至</h3>
								<div class="share_list">
									<div class="bdsharebuttonbox">
										<a href="#" class="bds_more" data-cmd="more"></a>
										<a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a> 
										<a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
										<a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a>
										<a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a>
										<a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
									</div>
								</div>
							</div>
							
						</div>
						<div class="sidebar right">
							<div class="module">
								<div class="bar-tit">
									<h2>最新文章</h2>
								</div>
								<ul class="bar-list">
									<c:forEach items="${lately }" var="l">
										<li><a
											href="search/searchAction_itemSearch.action?type=0&id=${l.articleId }">${l.title }</a>
										</li>
									</c:forEach>
								</ul>
							</div>
							<div class="module">
								<div class="bar-tit">
									<h2>最热文章</h2>
								</div>
								<ul class="bar-list">
									<c:forEach items="${hot }" var="h">
										<li><a
											href="search/searchAction_itemSearch.action?type=0&id=${h.articleId }">${h.title }</a>
										</li>
									</c:forEach>
								</ul>
							</div>
							<div class="module">
								<div class="bar-tit">
									<h2>友情链接</h2>
								</div>
								<ul class="bar-list">
									<li><a href="http://www.sense-id.com">慧感嘉联</a></li>
								</ul>
							</div>
						</div>
					</div>

				</section>
			</div>
		</main>
		<!-- /main -->


		<!-- footer -->
		<jsp:include page="../index/footer.jsp"></jsp:include>
		<!-- /footer -->