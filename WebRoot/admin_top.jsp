<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>后台管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="lib/css/left_style.css" rel="stylesheet" type="text/css" />

  </head>
  
  <body>
  
  
  <body style="background:url(images/admin/topbg.gif) repeat-x;">

    <div class="topleft">
    <a href="#" target="_parent"><img src="images/admin/logo.png" title="系统首页" /></a>
    </div>
        
    <ul class="nav">
	    <li><a href="" target="rightFrame" class="selected"><img src="images/admin/icon01.png" title="1" /><h2>1</h2></a></li>
	    <li><a href="" target="rightFrame"><img src="images/admin/icon02.png" title="2" /><h2>2</h2></a></li>
	    <li><a href="" target="rightFrame"><img src="images/admin/icon03.png" title="3" /><h2>3</h2></a></li>
	    <li><a href="" target="rightFrame"><img src="images/admin/icon04.png" title="4" /><h2>4</h2></a></li>
	    <li><a href="" target="rightFrame"><img src="images/admin/icon05.png" title="5" /><h2>5</h2></a></li>
	    <li><a href="" target="rightFrame"><img src="images/admin/icon06.png" title="6" /><h2>6</h2></a></li>
    </ul>
            
    <div class="topright">    
    <ul>
    <li><span><img src="images/admin/help.png" title="帮助"  class="helpimg"/></span><a href="" target="rightFrame">帮助</a></li>
    <li><a href="" target="rightFrame">关于</a></li>
    <li><a href="" target="rightFrame">退出</a></li>
    </ul>
     
    <div class="user">
    	<span><a href="" style="color: white;" target="rightFrame">admin</a></span>
    </div>    
    
    </div>
  
  
  
    <script language="JavaScript" src="lib/js/jquery.min.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected");
		$(this).addClass("selected");
	});	
});	
</script>
  </body>
</html>
