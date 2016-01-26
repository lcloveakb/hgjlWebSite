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
	<title>新闻更新页</title>
<link rel="stylesheet" type="text/css" href="lib/css/admin/editor_style.css">
</head>
<body>
	<input type="hidden" id="id" value="${news.id }">
	<input type="hidden" value="${news.parentId }" id="parentId">
	<input type="hidden" value="${news.category }" id="category">
	<input type="hidden" value="${news.source }" id="source">
	<input type="hidden" value="${news.clickCount }" id="clickCount">
	<input type="hidden" value="${news.shareCount }" id="shareCount">
	<input type="hidden" value="${news.createTime }" id="createTime">
	<input type="hidden" value="${news.creator }" id="creator">
	<input type="hidden" value="${news.shown }" id="shown">
	<input type="hidden" value="${news.articleId }" id="articleId">
	<input type="hidden" value="${news.type }" id="type">
	<input type="hidden" value="${news.imgs }" id="imgs">
	<input type="hidden" value="${news.indexImg }" id="indexImg">
	<input type="hidden" value="${news.top }" id="top">
	<form id="hiddenImgform" style="display: none;" enctype="multipart/form-data">
		<input id="fileupload" name="index" type="file">
	</form>
	<div id="container">
		新闻标题:<input type="text" id="title" value="${news.title }"> <br>
		新闻简介: <br><br>
		<textarea rows="6" cols="80" id="brief">${news.brief }</textarea><br>
		首页展示：<select id="indexShown">
					<option value="false" <c:if test="${!news.shown }">selected="selected"</c:if>>否</option>
					<option value="true" <c:if test="${news.shown }">selected="selected"</c:if>>是</option>
				</select><br><br>
		<div id="divIndexImg" style='<c:if test="${!news.shown }">display: none</c:if>'>
			首页图片：
			<c:choose>
				<c:when test="${news.shown }">
					<img src="images/article/indeximg/${news.indexImg }" width="150" height="150"/>
					<img title="删除图片" width="15" height="15" src="images/delImg.png" id="delIndexImg"/>
				</c:when>
				<c:otherwise>
					<img title="添加一张首页图片" alt="" src="images/addImg.png" width="150" height="150" 
						id="addIndexImg" style="cursor: pointer;margin-left: 7px"/>
					<br>
				</c:otherwise>
			</c:choose>
		</div><br>
		新闻内容:<br><br>
		<script id="editor" type="text/plain"
				style="width:1024px;height:500px;"></script> <br>
		<button onclick="Content();" style=" display: block; width: 100px; height: 30px;">修改</button>
	</div>

	<script type="text/javascript" charset="utf-8" src="lib/js/jquery-1.9.0.js"></script>
	<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript" src="lib/js/jquery.form.js"></script>
	<script type="text/javascript">
		//实例化编辑器
		//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
		var ue = UE.getEditor('editor'); 
		function Content() {
			var title = $.trim($("#title").val());
			if(title=="") {
				alert("标题不能为空");
				return;
			}
			var brief = $.trim($("#brief").val());
			if(brief=="") {
				alert("简介不能为空");
				return;
			}
			var shown = $("#shown").val();
			var indexImg = $("#indexImg").val();
			if(shown=="true") {
				if($.trim(indexImg)=="") {
					alert("请添加一张首页图片");
					return;
				}
			}
			var arr = [];
			arr.push(ue.getContent());
			$.ajax({ 
				url: "news/newsAction_update.action",
				data:{
					"id" : $("#id").val(),
					"category" : $("#category").val(),
					"parentId" : $("#parentId").val(),
					"title" : $("#title").val(),
					"articleId" : $("#articleId").val(),
					"type" : $("#type").val(),
					"clickCount" : $("#clickCount").val(),
					"shareCount" : $("#shareCount").val(),
					"createTime" : $("#createTime").val(),
					"brief" : $("#brief").val(),
					"shown_" : $("#indexShown").val(),
					"creator" : $("#creator").val(),
					"source" : $("#source").val(),
					"imgs" : $("#imgs").val(),
					"indeximg" : $("#indexImg").val(),
					"top" : $("#top").val(),
					"content" : arr.join("\n")
				},
				type:"post",
				contentType :"application/x-www-form-urlencoded;charset=utf-8",
				dataType:"json",
				success:function(result){
					if(result.ajaxStatus==1){
						alert("修改成功");
						window.location.reload();
					}else{
						alert("修改失败");
					}
				}
			});
		}
		$(function() {
			var arr = '${news.content }';
				ue.addListener("ready", function() {
				ue.setContent(arr);
			});
			$("#indexShown").bind("change",function() {
				if($(this).val()=="true"){
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
								url : 'news/newsAction_uploadIndexImg.action',
								dataType : "text",
								success : function(json) {
									var result = eval('(' + json + ')');
									var indexImg = result.indexFileName;
									$("#indexImg").val(indexImg);
									$("#addIndexImg").attr("src","images/article/indeximg/"+indexImg);
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
			//添加产品首页轮换图按钮
			$("#addIndexImg").unbind("click");
			$("#addIndexImg").bind("click",function(){
				$("#fileupload").click();
			});
			
			//删除产品首页轮换图按钮
			$("#delIndexImg").unbind("click");
			$("#delIndexImg").bind("click",function(){
				if(window.confirm("确认删除产品首页图片？")) {
					var title = $.trim($("#title").val());
					var brief = $.trim($("#brief").val());
					var indexImg = $("#indexImg").val();
					var arr = [];
					arr.push(ue.getContent());
					$.ajax({
						url:"news/newsAction_update.action",
						data:{
							"id":$("#id").val(),
							"category":$("#category").val(),
							"parentId":$("#parentId").val(),
							"title":title,
							"articleId":$("#articleId").val(),
							"type":$("#type").val(),
							"clickCount":$("#clickCount").val(),
							"shareCount":$("#shareCount").val(),
							"createTime":$("#createTime").val(),
							"brief":brief,
							"shown_":"false",
							"creator":$("#creator").val(),
							"img":$("#img").val(),
							"indexImg":"",
							"top": $("#top").val(),
							"content":arr.join("\n")
						},
						type:"post",
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						dataType:"json",
						success:function(result){
							if(result.ajaxStatus==1){
								$.ajax({
									url:"news/newsAction_delIndexImg.action",
									data:{
										"indeximg" : indexImg
									},
									type:"post",
									contentType : "application/x-www-form-urlencoded;charset=utf-8",
									dataType:"json",
									success:function(result){
										window.location.reload();
									}
								});
							}else{
								alert("删除图片失败");
							}
						}
					});
				}
			});
		});
	</script>
  </body>
</html>
