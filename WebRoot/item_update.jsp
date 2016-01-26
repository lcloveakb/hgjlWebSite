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

<input type="hidden" value="${item.id }" id="id">
<input type="hidden" value="${item.parentId }" id="parentId">
<input type="hidden" value="${item.categoryId }" id="categoryId">
<input type="hidden" value="${item.clickCount }" id="clickCount">
<input type="hidden" value="${item.shareCount }" id="shareCount">
<input type="hidden" value="${item.createTime }" id="createTime">
<input type="hidden" value="${item.creator }" id="creator">
<input type="hidden" value="${item.articleId }" id="articleId">
<input type="hidden" value="${item.type }" id="type">
<input type="hidden" value="${item.indexImg }" id="indexImg">
<input type="hidden" value="${item.top }" id="top">
<form id="hiddenImgform" style="display: none;" enctype="multipart/form-data">
		<input id="fileupload" name="index" type="file">
</form>
<div id="container">
	开发者标题：<input type="text" value="${item.title }" id="title"><br><br>
	开发者简介：<br><br>
	<textarea rows="6" cols="80" id="brief">${item.brief }</textarea><br><br>
	首页展示：<select id="indexShown">
					<option value="false" <c:if test="${!item.shown }">selected="selected"</c:if>>否</option>
					<option value="true" <c:if test="${item.shown }">selected="selected"</c:if>>是</option>
				</select><br><br>
		<div id="divIndexImg" style='<c:if test="${!item.shown }">display: none</c:if>'>
			首页图片：
			<c:choose>
				<c:when test="${item.shown }">
					<img src="images/article/indeximg/${item.indexImg }" width="150" height="150"/>
					<img title="删除图片" width="15" height="15" src="images/delImg.png" id="delIndexImg"/>
				</c:when>
				<c:otherwise>
					<img title="添加一张首页图片" alt="" src="images/addImg.png" width="150" height="150" 
						id="addIndexImg" style="cursor: pointer;margin-left: 7px"/>
					<br>
				</c:otherwise>
			</c:choose>
		</div><br>
	开发者内容：<br><br><script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
	<button onclick="getContent()" style=" display: block; width: 100px; height: 30px;margin-top: 20px">修改</button>
</div>
	<!-- 文本编辑器 start -->
	<script type="text/javascript" src="lib/js/jquery-1.9.0.js"></script>
	<script type="text/javascript" src="lib/js/jquery.form.js"></script>
	<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"> </script>
	<script type="text/javascript">
		//实例化编辑器
		//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
		var ue = UE.getEditor('editor');
		function getContent() {
			var arr = [];
			arr.push(ue.getContent());
			var id = $("#id").val();
			var title = $.trim($("#title").val());
			if(title=="") {
				alert("标题不能为空");
				return;
			}
			var shown_=$("#indexShown").val()=="true"?0:1;
			$.ajax({
					url:"developer/developerAction_update.action",
					type:"post",
					data:{
						"id" : id,
						"categoryid" : $("#categoryId").val(),
						"parentid" : $("#parentId").val(),
						"content" : arr.join("\n"),
						"title" : title,
						"articleId" : $("#articleId").val(),
						"clickCount" : $("#clickCount").val(),
						"shareCount" : $("#shareCount").val(),
						"createtime" : $("#createTime").val(),
						"shown" : shown_,
						"creator" : $("#creator").val(),
						"type" : $("#type").val(),
						"brief" : $("#brief").val(),
						"top" : $("#top").val(),
						"indeximg" : $("#indexImg").val()
					},
					success:function(result){
						if(result.ajaxStatus){
							alert("更新成功");
							window.location.href = "developer/developerAction_toCatsList.action";
						}else{
							alert("更新失败");
						}
					},
					error:function(XmlHttpRequest, textStatus, errorThrown) {
						alert("更新失败"+errorThrown);
					}
			});
		}
		$(function() {
			var arr = '${item.content }';
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
								url : "news/newsAction_uploadIndexImg.action",
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
						url:"developer/developerAction_update.action",
						data:{
							"id":$("#id").val(),
							"categoryid":$("#category").val(),
							"parentid":$("#parentId").val(),
							"title":title,
							"articleId":$("#articleId").val(),
							"type":$("#type").val(),
							"clickCount":$("#clickCount").val(),
							"shareCount":$("#shareCount").val(),
							"createtime":$("#createTime").val(),
							"brief":brief,
							"shown":1,
							"creator":$("#creator").val(),
							"img":$("#img").val(),
							"indeximg":"",
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
	<!-- 文本编辑器 end -->
</body>
</html>
