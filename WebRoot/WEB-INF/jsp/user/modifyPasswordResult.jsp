<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<title>找回密码</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/r29/html5.min.js"></script>
    <![endif]-->
<link rel="stylesheet" type="text/css" href="lib/css/style.css" />

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
					<div class="login-mod">
						<div class="mod">
							<div class="login">
								<c:if test="${modified }">
								<h3>密码修改成功，3秒后自动跳转至首页</h3>
								<script type="text/javascript">
									setTimeout("location.href='/'", 3000);
								</script>
								</c:if>
								<c:if test="${!modified }">
								<h3>密码修改失败，请重新<a href="user/userAction_toConfirmStatus.action">找回密码</a></h3>
								</c:if>
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