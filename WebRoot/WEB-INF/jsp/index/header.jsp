<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<header class="header">
            <div class="row">
                <div class="head">
                    <div class="logo">
	                    <img src="lib/images/mylogo.png" class="title" style="width: 168px;height:77px" data-bd-imgshare-binded="1"/>
                    </div>
                    <div class="search">
                        <div class="form">
                            <form action="search/searchAction_detailSearch.action">
                                <input type="text" placeholder="站内搜索" name="keyword" id="" class="ipt">
                                <input type="submit" value="search" class="btn">
                                <i class="icon-search-2"></i>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="nav">
                    <ul class="menu left">
                        <li><a href="index/indexAction_forward.action">首页</a></li>
                        <li class="dropdown">
                            <a href="developer/developerAction_toIndex.action">开发者</a>
                            <ul class="sub-menu">
                            	<c:forEach items="${cats }" var="c">
                            		<li><a href="developer/developerAction_toIndex.action#${c.name }">${c.name }</a></li>
                            	</c:forEach>
                                <li><a href="product/productAction_toIndex.action">最终产品</a></li>
                            </ul>
                        </li>
                        <li><a href="download/downloadAction_toIndex.action">下载</a></li>
                        <li class="dropdown">
                            <a>支持</a>
                            <ul class="sub-menu">
                                <li><a href="support/supportAction_toFaq.action">常见问答</a></li>
                                <li><a href="support/supportAction_toAbout.action">关于我们</a></li>
                            </ul>
                        </li>
                        <li><a href="http://www.sense-id.net/monitor/login/LoginServlet?method=loginUI">云平台</a></li>
                        <li><a href="forum/forumAction_toForum.action">论坛</a></li>
                    </ul>
                     <ul class="menu right">
                    <c:choose>
                    	<c:when test="${sessionScope.user.username!=null }">
                    		<li><a href="user/userAction_info.action" style="font-weight: 700;">${sessionScope.user.username }</a></li>
	                        <li><a href="user/userAction_logout.action">注销</a></li>
                    	</c:when>
                    	<c:otherwise>
	                        <li><a href="user/userAction_toLogin.action">登录</a></li>
	                        <li><a href="user/userAction_toRegister.action">注册</a></li>
                    	</c:otherwise>
                    </c:choose>
                     </ul>
                </div>
            </div>
        </header><!-- /header -->
        
        