<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>云云创客-SenseMaker</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/r29/html5.min.js"></script>
    <![endif]-->
 <link rel="stylesheet" type="text/css" href="lib/css/style.css" />
<script type="text/javascript" src="lib/js/jquery.min.js"></script>
<script type="text/javascript" src="lib/js/comment.js"></script>
  </head>
  
  <body>
  	<input type="hidden" value="${user.id }" id="uid">
	<input type="hidden" value="${news.parentId }" id="articleId">
    <div class="wrapper">        
    	<!-- header -->
        <div class="nav-banner" id="navBanber"></div>
        <jsp:include page="/WEB-INF/jsp/index/header.jsp"></jsp:include>

        <!-- main -->
        <main class="main">
            <div class="container row">
                <section class="content item">
                    <!-- mod -->
                    <div class="article-l clearfix">
                       <div class="posts left">
                            <article class="post">
                                <header class="entry-header">
                                    <h1 class="entry-title">
                                        ${news.title }
                                    </h1>
                                </header>
                                <section class="entry-info">
                                    <div class="info" style="font-size: 12px;font-style: italic;color: #ADADAD;margin-left: 10px">
                                      <span class="author">${news.creator }</span>&nbsp;&nbsp;发布于&nbsp;&nbsp;
                                      <span class="date" style="margin-left: 20px;">
                                      	<fmt:formatDate value="${news.createTime }" type="both" pattern="yyyy-MM-dd" />
                                      </span>
                                    </div>
                                </section><br><br>
                                <section class="entry-content">${news.content}</section>
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
									    <span class="cc">0 条评论</span>
									</div>
								    <div class="middle clearfix">
								        <div class="profile">
								            <img src="lib/images/user_default.jpg" data-node="profile">
								        </div>
								        <div class="input">
								            <textarea placeholder="写下您的评论" style="width: 545px;" id="ta_comment"></textarea>
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
								    	<ul id="ul_content"></ul>
										<div class="comment_paginator pager page_number page_page_number" id="pager"></div>
									</div>
								</div>
                            </article>
                        </div>
                        <div class="sidebar right">
						    <div class="module">
						<!--         <a href="" class="btn">上一侧</a> -->
						<!--         <a href="" class="btn">button2</a> -->
						    </div>
						    <div class="module">
						        <div class="bar-tit">
						            <h2>最新文章</h2>
						        </div>
						        <ul class="bar-list">
						        	<c:forEach items="${lately }" var="l">
							            <li><a href="search/searchAction_itemSearch.action?type=0&id=${l.articleId }">${l.title }</a> 
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
							            <li><a href="search/searchAction_itemSearch.action?type=0&id=${h.articleId }">${h.title }</a> 
							            </li>
						        	</c:forEach>
						        </ul>
						    </div>
						    <div class="module">
						        <div class="bar-tit">
						            <h2>友情链接</h2>
						        </div>
						        <ul class="bar-list">
						            <li><a href="http://www.sense-id.com">慧感嘉联官网</a>
						            </li>
						        </ul>
						    </div>
						</div>
                    </div>
                </section>
            </div>
        </main>
        <!-- /main -->

        
        <!-- footer -->
        <jsp:include page="/WEB-INF/jsp/index/footer.jsp"></jsp:include>
        <!-- /footer -->
       