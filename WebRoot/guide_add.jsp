<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="lib/css/admin/editor_style.css">
</head>

<body>
<form id="hiddenImgform" style="display: none;" enctype="multipart/form-data">
		<input id="fileupload" name="index" type="file">
	</form><br>
	<div id="container">
	<div class="top">
		开发者标题：<input type="text" id="title"><br><br>
		开发者简介：<br><br><textarea rows="6" cols="80" id="brief"></textarea><br><br>
		开发者类别：<select id="categoryid">
				<c:forEach items="${cats }" var="cat">
					<option value="${cat.id }">${cat.name }</option>
				</c:forEach>
			</select>
	</div>
	首页显示：<select id="shown">
					<option value="0">是</option>
					<option value="1" selected="selected">否</option>
				</select><br>
		<div id="divIndexImg" style='<c:if test="${!item.shown }">display: none</c:if>'>
			<input type="hidden" id="imgPath" value="">
			首页图片：
			<c:choose>
				<c:when test="${item.shown }">
					<img src="images/article/indeximg/${item.indexImg }" width="150" height="150"/>
					<img title="删除图片" width="15" height="15" src="images/delImg.png" id="delIndexImg"/>
				</c:when>
				<c:otherwise>
					<img title="添加一张首页图片" alt="添加一张首页图片" src="images/addImg.png" width="150" height="150" 
						id="addIndexImg" style="cursor: pointer;margin-left: 7px"/>
				</c:otherwise>
			</c:choose>
		</div><br>
		
	<!-- 文本编辑器 start -->
	<div>
		<script id="editor" type="text/plain"></script>
	</div>
	<button onclick="Content()" style=" display: block; width: 100px; height: 30px;margin-top: 20px">提交</button>
	</div>
	<script type="text/javascript" src="lib/js/jquery.min.js"></script>
	<script type="text/javascript" src="lib/js/jquery.form.js"></script>
	<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"> </script>
	<script type="text/javascript">
		//实例化编辑器
		//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
		var ue = UE.getEditor('editor');
		function Content() {
			var arr = [];
			arr.push(UE.getEditor('editor').getContent());
			$.ajax({
					type : "post",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "developer/developerAction_add.action",
					data : {
						"content" : arr.join("\n"),
						"categoryid" : $("#categoryid").val(),
						"brief" : $("#brief").val(),
						"shown" : $("#shown").val(),
						"indeximg" : $("#imgPath").val(),
						"title" : $("#title").val()
					},
					datatype : "json",
					success : function(result) {
						if (result.ajaxStatus) {
							alert("添加成功");
							window.location.reload();
						} else {
							alert("添加失败");
						}
					}
			});
		}
		
		$(function(){
			//首页显示图片
			$("#shown").click(function() {
				if ($(this).val()==0) {
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
	<!-- 文本编辑器 end -->
</body>
</html>
