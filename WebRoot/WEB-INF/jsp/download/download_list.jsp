<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>下载</title>

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


		<!-- main start -->
		
		<%-- <div style="margin-left: auto; margin-right: auto; text-align: center;">
			<b style="font-size: 39px;">下载资源</b>
			<hr>
			<section style="margin: 60px;">
				<nav>
					<ul style="font-size: 25px;">
						<s:iterator value="itemList" var="item">
							<li><a style=" color: #565656;" href="download/downloadAction_downloadFile.action?fileName=<s:property value="#item.title"/>&url=<s:property value="#item.url"/>"><s:property value="#item.title"/></a></li>
							<hr>
						</s:iterator>
					</ul>
				</nav>
			</section>
		</div> --%>
		<main class="main">
            <div class="container row">
                <section class="content item">
                    <!-- mod -->
                    <div class="doubt">
                    
                    	<%-- <s:iterator value="itemList" var="item">
							<li><a style=" color: #565656;" href="download/downloadAction_downloadFile.action?fileName=<s:property value="#item.title"/>&url=<s:property value="#item.url"/>"><s:property value="#item.title"/></a></li>
							<div class="doubt-mod">
	                            <div class="mod">
	                                <h2>这里放置下载类别</h2>
	                                <a href="download/downloadAction_downloadFile.action?fileName=<s:property value="#item.title"/>&url=<s:property value="#item.url"/>" style="color: blue;font-size: 16px;cursor: pointer;"><s:property value="#item.title"/></a>
	                            </div>
                        	</div>
						</s:iterator> --%>
						
                        <div class="doubt-mod">
                        	<s:if test="catsList.size==0">
                        		<div class="mod">
	                                <h2>暂无下载资源</h2>
	                            </div>	
                        	</s:if>
                        	<s:else>
		                        <s:iterator value="catsList" var="cat" status="st">
		                            <div class="mod" style="margin-top: 30px">
		                                <div style="font-size: 15px;font-family: '黑体';font-style: italic;margin-bottom: 10px"><s:property value="#st.count"/>、<s:property value="#cat.name"/></div>
		                                <s:iterator value="#cat.items" var="item" status="s">
			                                <a href="download/downloadAction_downloadFile.action?id=<s:property value="#item.id"/>" 
			                                	style="color: blue;font-size: 13px;font-style:italic;cursor: pointer;line-height:40px;margin-left: 20px">
			                                	<s:property value="#st.count"/>.<s:property value="#s.count"/>、<s:property value="#item.title"/>
			                                </a><br>
		                                </s:iterator>
		                            </div>
		                        </s:iterator>
                        	</s:else>
                        </div>
                    </div>
                </section>
            </div>
        </main>
		
		<!-- main end -->


		<!-- footer -->
		<jsp:include page="../index/footer.jsp"></jsp:include>
		<!-- /footer -->