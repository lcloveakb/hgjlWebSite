<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新闻添加页</title>
<link rel="stylesheet" type="text/css" href="lib/css/admin/editor_style.css">
</head>
<body>
	<form id="hiddenImgform" style="display: none;" enctype="multipart/form-data">
		<input id="fileupload" name="index" type="file">
	</form><br>
	<div id="container">
		新闻标题：<input type="text" id="title" style="width:250px"><br>
		新闻简介：<br><br><textarea rows=6" cols="80" id="brief"></textarea><br>
		新闻类别：<select id="category">
					<option value="3">--请选择--</option>
					<option value="0">公司新闻</option>
					<option value="1">外部新闻</option>
				</select><br>
		首页显示：<select id="shown">
					<option value="0">是</option>
					<option value="1" selected="selected">否</option>
				</select><br>
		<div id="divIndexImg" style='<c:if test="${!news.shown }">display: none</c:if>'>
			<input type="hidden" id="imgPath" value="">
			首页图片：
			<c:choose>
				<c:when test="${news.shown }">
					<img src="images/article/indeximg/${news.indexImg }" width="150" height="150"/>
					<img title="删除图片" width="15" height="15" src="images/delImg.png" id="delIndexImg"/>
				</c:when>
				<c:otherwise>
					<img title="添加一张首页图片" alt="添加一张首页图片" src="images/addImg.png" width="150" height="150" 
						id="addIndexImg" style="cursor: pointer;margin-left: 7px"/>
					<%
					//TODO 添加“删除首页图片”按钮
					%>
				</c:otherwise>
			</c:choose>
		</div><br>
		新闻内容：<br><br><div class="u_editor"><script id="editor" type="text/plain"></script>
				</div><button onclick="Content()" style=" display: block; width: 100px; height: 30px;margin-top: 20px;">提交</button>
	</div>
	
	<script type="text/javascript" src="lib/js/jquery.min.js"></script>
	<script type="text/javascript" src="lib/js/jquery.form.js"></script>
	<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript">
		//实例化编辑器
		//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
		var ue = UE.getEditor('editor');
		function Content() {
			var arr = [];
			arr.push(UE.getEditor('editor').getContent());
			//禁止标题为空
			if($("#title").val().length<=0){
				alert("【新闻标题】禁止为空");
				return false;
			}
			//禁止类别为空 start
			if($("#category").val()==3){
				alert("【新闻类别】禁止为空");
				return false;
			}
			//禁止shown为空 start
			if($("#shown").val()==0 && $("#imgPath").val()==""){
				alert("请选择首页图片");	
				return false;
			}
			//截取图片start
			var imgStr="",arr_={};
			var prefix = "images/article/news/";
			var prefix_len = prefix.length;
			var fileName_len = 25;	//包含后缀名
			if(arr[0].indexOf("<img src=")<=0){	//新闻没有添加图片
				arr_="";
			}else{							// 获取各个图片的路径，并以";"隔开
				var p=arr[0].split("</p>");
				for(var i=0;i<p.length;i++){
					var srcValueIndex = p[i].indexOf(prefix);
					if(srcValueIndex>0){
						imgStr+=p[i].substring(srcValueIndex,srcValueIndex+8+prefix_len+fileName_len)+";";
					}
				}
			    arr_=imgStr.split(";");
			}
			//添加请求
			$.ajax({
				type: "post",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "news/newsAction_add.action",
				data : {
					"content" : arr.join("\n"),
					"category" : $("#category").val(),
					"title" : $("#title").val(),
					"brief" : $("#brief").val(),
					"indeximg" : $("#imgPath").val(),
					"imgs" : arr_[0],
					"shown":$("#shown").val()
				},
				datatype : "json",
				success : function(result) {
					if (result.ajaxStatus == 0) {
						alert("添加失败");
					} else {
						window.open("news/newsAction_detail.action?id="+ result.id);
						window.location.reload();
					}
				}
			});
		}
		$(function(){
			//首页显示图片
			$("#shown").click(function() {
				if ($(this).val() == 0) {
					$("#divIndexImg").show();
				} else {
					$("#divIndexImg").hide();
				}
			});
			
			$("#fileupload").bind("change",function(){
				var val = $(this).val();
				if(val!="") {
					var sufIndex = val.lastIndexOf(".");
					if(sufIndex>0 && sufIndex<val.length-1) {
						var suffix = val.substring(sufIndex+1);
						if(suffix=="png"||suffix=="jpg"||suffix=="jpeg"||suffix=="bmp"||suffix=="gif") {
							$("#hiddenImgform").ajaxSubmit({
								url : 'product/productAction_uploadIndexImg.action',
								dataType : "text",
								success : function(json) {
									var result = eval('(' + json + ')');
									var fileName = result.indexFileName;
									$("#imgPath").val(fileName);
									$("#addIndexImg").attr("src","images/article/indeximg/"+fileName);
								},
								error : function(XmlHttpRequest, textStatus, errorThrown) {
									alert(textStatus + "\n" + errorThrown);
								}
							});
						} else {
							alert("仅支持png jpg jpeg bmp gif格式");
							$(this).val("");
						}
					} else {
						alert("错误类型");
					}
				}
			});
			
			//添加产品首页轮换图
			$("#addIndexImg").unbind("click");
			$("#addIndexImg").bind("click",function(){
				$("#fileupload").click();
			});
			
		});
	</script>
</body>
</html>