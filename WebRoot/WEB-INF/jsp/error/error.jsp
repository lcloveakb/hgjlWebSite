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
		<jsp:include page="/WEB-INF/jsp/index/header.jsp"></jsp:include>
		<!-- main -->
		<main class="main">
			<div class="container row">
				<section class="content item">
                    <!-- mod -->
                    <div class="doubt">
                        <div class="doubt-mod">
                            <div class="mod" style="color: #f47a20;font-size: 20px;font-style:italic;font-weight:600;letter-spacing:2px">
                                <span>出错啦！</span>
                                <br><br>
                                <span>将在3秒后跳转至首页</span>
                                <script type="text/javascript">
                                	window.setTimeout("location.href='/'", 3000);
                                </script>
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