<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>常见问题</title>
    
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
                                <h2>常见问题</h2>
                                <div class="box clearfix">
                                    <div class="ask">什么是RFID</div>
                                    <div class="reply">又称无线射频识别，是一种通信技术，可通过无线电讯号识别特定目标并读写相关数据，而无需识别系统与特定目标之间建立机械或光学接触。较常见的应用有无线射频识别（Radio Frequency Identification，RFID）,常称为感应式电子晶片或近接卡、感应卡、非接触卡、电子标签、电子条码等。其原理为由扫描器发射一特定频率之无线电波能量给接收器，用以驱动接收器电路将内部的代码送出，此时扫描器便接收此代码。接收器的特殊在于免用电池、免接触、免刷卡故不怕脏污，且晶片密码为世界唯一无法复制。</div>
                                    <div class="ask">RFID的用途</div>
                                    <div class="reply">RFID技术和条形码等其他识别技术相比，有以下几个特点：远距离读写，存储量大，安全性高，可重复使用，使用寿命长。可以同时识别多个标签。形状不受限制。耐环境变化，保养方便。数据安全。
                                    RFID的应用非常广泛，目前典型应用有动物晶片、汽车晶片防盗器、门禁管制、停车场管制、生产线自动化、物料管理。RFID标签有两种：有源标签和无源标签。</div>
                                </div>
                            </div>
                        </div>
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
                </section>
            </div>
        </main>
        <!-- /main -->

        
        <!-- footer -->
        <jsp:include page="../index/footer.jsp"></jsp:include>
        <!-- /footer -->
       