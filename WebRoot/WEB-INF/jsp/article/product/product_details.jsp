<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

<title>${product.title }</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/r29/html5.min.js"></script>
    <![endif]-->
<link rel="stylesheet" type="text/css" href="lib/css/style.css" />

<script type="text/javascript" src="lib/js/jquery.min.js"></script>
<script type="text/javascript" src="lib/js/comment.js"></script>

</head>

<body>
	<input type="hidden" value="${user.id }" id="uid">
	<input type="hidden" value="${product.parentId }" id="articleId">
	<div class="wrapper">
		<!-- header -->
		<div class="nav-banner" id="navBanber"></div>
		<jsp:include page="../../index/header.jsp"></jsp:include>

		<!-- main -->
		<main class="main">
			<div class="container row">
				<section class="content item">
					<!-- mod -->
					<div class="details">
						<h2 style="color: #E67E22;text-align: center">${product.title }</h2>
						<!-- <div style="margin-top: 30px;">
						</div> -->
						<div class="entry-content">
							<div style="color: #E67E22;font-size: 24px;line-height: 80px;margin-top: 25px">产品简介</div>
							<p style="color: #a0a0a0;letter-spacing: 1px;line-height: 30px;font-style: italic;font-size: 13px">&emsp;&emsp;${product.brief }</p>
							<div style="color: #E67E22;font-size: 24px;line-height: 80px;margin-top: 25px">产品图片</div>
							<c:if test="${imgList.size()>0 }">
								<div style="width: 960px">
									<c:forEach items="${imgList }" var="img">
										<img src="images/article/products/${img }" alt="" style="width: 300px;height: 200px;margin-left: 15px" title="${product.title }">
									</c:forEach>
								</div>
							</c:if>
							
							<div style="color: #E67E22;font-size: 24px;line-height: 80px;margin-top: 15px">产品详情</div>
							<c:forEach items="${params }" var="p" varStatus="s">
								<div style="line-height: 40px;font-size: 15px;color: #808080;font-weight: 600;margin-left: 15px">${p}</div>
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
							<div id="pagelet-comment">
								<div class="top clearfix">
								    <h2>评论</h2>
								    <span class="cc">0条评论</span>
								</div>
							    <div class="middle clearfix">
							        <div class="profile">
							            <img src="lib/images/user_default.jpg" data-node="profile">
							        </div>
							        <div class="input" style="width: 895px">
							            <textarea placeholder="写下您的评论" style="width: 895px;" id="ta_comment"></textarea>
							            <div class="bottom">
							                <span class="sns-label">分享到：</span>
							                <div class="sns-group" data-node="snsGroup">
							                    <a class="sns weibo" data-node="sina_weibo"></a>
							                    <!--a class="sns qqzone" data-node="qzone_sns"></a>
							                    <a class="sns renren" data-node="renren_sns"></a-->
							                    <a class="sns ttweibo" data-node="qq_weibo"></a>
							                </div>
							                <a href="javascript:;" class="publish-btn" data-node="publish"> 发表 </a>
							            </div>
							        </div>
							    </div>
							    <div class="comments-list" data-node="commentList">
							    	<ul id="ul_content">
										<%-- <li class="comment-item clearfix">
										    <div class="avatar">
										        <a href="javascript:;" target="_blank">
										            <img src="lib/images/firefox.png">
										        </a>
										    </div>
										    <div class="comment-content">
										        <div class="name">
										            <a href="javascript:;" target="_blank">${c.user.username }</a>
										        </div>
										        <div class="content">${c.content }</div>
										        <div class="comment_actions clearfix">
										            <span class="time"><fmt:formatDate value="${c.time }" pattern="yyyy-MM-dd HH:mm" /></span>
										            <div class="action">
										              <a href="javascript:;" class="comment_digg comment_digged">0</a>
										              <a class="c-comment" href="#" data-node="listcomment">
										                  <img src="lib/images/ic_comments.png">
										              </a>
										            </div>
										        </div>
										    </div>
										</li> --%>
									</ul>
									<div class="comment_paginator pager page_number page_page_number" id="pager">
									   	<!-- <a class="page-number-prev" data-node="pre">上一页</a>
									    <a class="page_number-next current">1</a>
										<a class="page-number-next">下一页</a> -->
									</div>
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