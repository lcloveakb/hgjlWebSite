<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>论坛</title>
    
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
                    <div class="doubt">
                        <div class="doubt-mod">
                            <div class="mod">
                                <h2>论坛正在建设中...</h2>
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
       