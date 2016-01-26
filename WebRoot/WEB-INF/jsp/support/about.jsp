<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>关于我们</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/r29/html5.min.js"></script>
    <![endif]-->
    <!-- 
    	每个页面的link上面引入这段代码！
     -->
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
                    <div class="about-mod">
                        <div class="mod">
                            
                            <div class="comnetns">
                                <h2>关于我们</h2>
                                <p>
                                    慧感嘉联科技是致力于物联网和射频识别领域的新锐开发商,由国内外知名的物联网和射频识别专家以及拥有丰富运营经验的跨国企业管理精英组成，慧感嘉联目前拥有完整的物联网及射频识别产品线以及多项自主知识产权和专利，并在物联网和射频识别领域的关键技术上取得了重大突破。慧感嘉联的产品和系统已经广泛应用在医疗、电力、教育、仓储、航天军工等多个行业，获得了用户的一致好评和业内的广泛认可。
                                </p>
                                <p>
                                    慧感嘉联科技立志成为国内物联网和射频识别行业的引领者，为此，我们密切关注客户以及“客户之客户”的需求，在项目初期就会积极并有预见性的介入用户的需求中去，为用户提供专业的业务咨询、需求分析、方案设计和培训等服务，并在充分理解用户之需求后，为用户提供灵活完善的产品定制开发测试服务，从而为用户提供高性能的、符合用户实际需求的物联网及射频识别产品和应用。慧感嘉联可以根据客户要求研发设计各类RFID标签、高性能RFID读写器、物联网平台应用及整体解决方案等。公司目前拥有通过ISO9001认证的产品生产基地，可以为客户生产制造高品质，高可靠性的产品。
在物联网产业蓬勃发展的今天，热忱欢迎有识之士与我们进行交流流合作，让我们携手并进，共创辉煌！
                                </p>
<!--                                 <h3 class="hr"></h3> -->
<!--                                 <h2>团队</h2> -->
<!--                                 <p> -->
<!--                                     Sensoro 奉行精英策略，公司现有 30 名成员，其中 80% 为工程师团队；我们的工程师分别来自：清华大学、香港理工、网易核心架构组、微软亚洲研究院（MSRA）、中国航天研究院（CAOS）。我们在大规模并发处理、移动互联网、数据挖掘、数字电路、惯性导航、飞行器定位等技术领域具备强大的研发实力。 -->
<!--                                 </p> -->
<!--                                 <p> -->
<!--                                     <img src="http://www.sensoro.com/images/about/us.jpg" alt=""> -->
<!--                                 </p> -->
                                <h3 class="hr"></h3>
                                <h2>联系我们</h2>
                                <div class="us">
                                    <p>中国，北京</p>
                                    <p>邮箱：sense-id@sense-id.com</p>
                                    <p>电话：+86-010-56353116 ( 09:00 - 18:00 )</p>
                                    <p>地址：北京市海淀区清河嘉园东区甲1号楼(翠微大厦A座) 10层 1026</p>
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
       