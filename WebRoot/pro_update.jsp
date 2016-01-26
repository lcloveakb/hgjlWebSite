<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>产品更新页</title>
<link rel="stylesheet" type="text/css" href="lib/css/admin/editor_style.css">
</head>

<body>
	<input type="hidden" id="id" value="${product.id }">
	<input type="hidden" id="category" value="${product.category }">
	<input type="hidden" id="parentId" value="${product.parentId }">
	<input type="hidden" id="articleId" value="${product.articleId }">
	<input type="hidden" id="type" value="${product.type }">
	<input type="hidden" id="clickCount" value="${product.clickCount }">
	<input type="hidden" id="shareCount" value="${product.shareCount }">
	<input type="hidden" id="createTime" value="${product.createTime }">
	<input type="hidden" id="creator" value="${product.creator }">
	<input type="hidden" id="indexImg" value="${product.indexImg }">
	<input type="hidden" id="top" value="${product.top }">
	<!-- 产品图片提交隐藏form -->
	<form id="hiddenImgform" style="display: none;" enctype="multipart/form-data">
		<input id="fileupload" type="file">
	</form>
	<c:set value="${product.imgs }" var="img"/>
	<input type="hidden" id="img" value="${img }">	
	<div id="container">
		产品标题：<input type="text" value="${product.title }" id="title"><br>
		产品简介：<br><br>
		<textarea rows="6" cols="80" id="brief">${product.brief }</textarea><br>
		产品图片：<br><br>
		<div class="pro_img">
			<c:set value="${fn:split(product.imgs, ';') }" var="urls" />
			<c:set value="0" var="length" />
			<c:if test="${not empty img }">
				<c:forEach items="${urls}" var="url">
					<c:set value="${length+1 }" var="length" />
					<img alt="" src="images/article/products/${url }" width="100" height="100" class="pImg"/>
					<img title="删除图片" alt="${url }" src="images/delImg.png" width="10" height="10" class="delImg"/>&nbsp;&nbsp;
				</c:forEach>
			</c:if>
			<c:forEach var="i" begin="${length }" end="2">
			    <img alt="" title="添加图片" src="images/addImg.png" width="100" height="100" class="addImg" style="cursor: pointer;"/>&nbsp;&nbsp;
			</c:forEach><br><br>
		</div>
		首页展示：<select id="shown">
					<option value="false" <c:if test="${!product.shown }">selected="selected"</c:if>>否</option>
					<option value="true" <c:if test="${product.shown }">selected="selected"</c:if>>是</option>
				</select><br><br>
		<div id="divIndexImg" style='<c:if test="${!product.shown }">display: none</c:if>'>
			首页图片：
			<c:choose>
				<c:when test="${product.shown }">
					<img src="images/article/indeximg/${product.indexImg }" width="150" height="150"/>
					<img title="删除图片" width="15" height="15" src="images/delImg.png" id="delIndexImg"/>
				</c:when>
				<c:otherwise>
					<img title="添加一张首页图片" alt="" src="images/addImg.png" width="150" height="150" 
						id="addIndexImg" style="cursor: pointer;margin-left: 7px"/>
					<br>
				</c:otherwise>
			</c:choose>
		</div><br>
		产品详情：<br><br>
		<script id="editor" type="text/plain" style="width:1024px;height:500px;"></script> <br>
		<button onclick="Content();" style="display: block; width: 100px; height: 30px;">提交</button>
	</div>
	<!-- 文本编辑器 start -->
	<script type="text/javascript" charset="utf-8" src="lib/js/jquery-1.9.0.js"></script>
	<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript" src="lib/js/jquery.form.js"></script>
	<script type="text/javascript">
		//实例化编辑器
		//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
		/* var ue = UE.getEditor(
			'editor', 
			{ toolbars : [ [ 'fullscreen', 'source', 'undo', 'redo', 'bold',
					'italic', 'underline', 'fontborder', 'backcolor',
					'fontsize', 'fontfamily', 'justifyleft', 'justifyright',
					'justifycenter', 'justifyjustify',
					'strikethro(www.111cn.net)ugh', 'superscript', 'subscript',
					'removeformat', 'formatmatch', 'autotypeset', 'blockquote',
					'pasteplain', '|', 'forecolor', 'backcolor',
					'insertorderedlist', 'insertunorderedlist', 'selectall',
					'cleardoc', 'link', 'unlink', 'emotion', 'help' ] ]}
		); */
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
				url:"product/productAction_update.action",
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
					"shown_":shown,
					"creator":$("#creator").val(),
					"img":$("#img").val(),
					"indexImg":indexImg,
					"top": $("#top").val(),
					"content":arr.join("\n")
				},
				type:"post",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
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
			var arr = '${product.params}';
			ue.addListener("ready", function() {
				ue.setContent(arr);
			});
			$("#shown").bind("change",function() {
				if($(this).val()=="true"){
					$("#divIndexImg").show();
				} else {
					$("#divIndexImg").hide();
				}
			});
			
			//添加产品图片
			$(".addImg").each(function(){
				var btn = $(this);
				btn.unbind("click");
				btn.bind("click",function() {
					var $fileInput = $("#fileupload");
					$fileInput.click();		// 自动触发文件域
					//获取表单数据
					$fileInput.unbind("change");
					$fileInput.bind("change", function() {
						if($.trim($fileInput.val())=="") {
							alert("图片不能为空");
							return;
						}
						$fileInput.attr("name","attachment");
						$fileInput.parent().ajaxSubmit({
							url : 'product/productAction_uploadProductImg.action',
							dataType : "text",
							success : function(json) {
								var result = eval('(' + json + ')');
								var updateImg = result.attachmentFileName;
								var img=$("#img").val();
								$("#img").val(img+updateImg+";");
								var title = $.trim($("#title").val());
								var brief = $.trim($("#brief").val());
								var shown = $("#shown").val();
								var indexImg = $("#indexImg").val();
								var arr = [];
								arr.push(ue.getContent());
								$.ajax({
									url:"product/productAction_update.action",
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
										"shown_":shown,
										"creator":$("#creator").val(),
										"img":$("#img").val(),
										"indexImg":indexImg,
										"top": $("#top").val(),
										"content":arr.join("\n")
									},
									type:"post",
									contentType : "application/x-www-form-urlencoded;charset=utf-8",
									dataType:"json",
									success:function(result){
										if(result.ajaxStatus==1){
											window.location.reload();
										}else{
											alert("添加图片失败");
										}
									}
								});
							},
							error : function(XmlHttpRequest, textStatus, errorThrown) {
								alert(textStatus + "\n" + errorThrown);
							}
						});
						return false;
					});
				});
			});
			
			//删除产品图片
			$(".delImg").each(function(){
				var btn = $(this);
				btn.unbind("click");
				btn.bind("click",function() {
					
					if(window.confirm("确认删除该产品图片？")) {
						var ori_imgs=$("#img").val();
						var del_img=$(this).attr("alt");
						var index=ori_imgs.indexOf(del_img);
						if(index==0){
							//如果是第一张图片
							ori_imgs=ori_imgs.substr(del_img.length+1,ori_imgs.length);
						}else{
							var pre,next;
							pre=ori_imgs.substr(0,index);
							next=ori_imgs.substr(index+del_img.length+1,ori_imgs.length);
							ori_imgs=pre+next;
						}
						$("#img").val(ori_imgs);
						var title = $.trim($("#title").val());
						var brief = $.trim($("#brief").val());
						var shown = $("#shown").val();
						var indexImg = $("#indexImg").val();
						var arr = [];
						arr.push(ue.getContent());
						$.ajax({
							url:"product/productAction_update.action",
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
								"shown_":shown,
								"creator":$("#creator").val(),
								"img":$("#img").val(),
								"indexImg":indexImg,
								"top": $("#top").val(),
								"content":arr.join("\n")
							},
							type:"post",
							contentType : "application/x-www-form-urlencoded;charset=utf-8",
							dataType:"json",
							success:function(result){
								if(result.ajaxStatus==1){
									$.ajax({
										url:"product/productAction_ftpImgDel.action",
										data:{
											"type" : 0,   //0 表示删除product文件夹下的图片
											"img" : del_img
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
					}   // if end 
				});
			});
			
			
			//添加产品首页轮换图
			$("#addIndexImg").unbind("click");
			$("#addIndexImg").bind("click",function(){
				$("#fileupload").click();
				// 须重新定义change事件
				$("#fileupload").unbind("change");
				$("#fileupload").bind("change",function(){
					var val = $(this).val();
					if(val!="") {
						var sufIndex = val.lastIndexOf(".");
						if(sufIndex>0 && sufIndex<val.length-1) {
							var suffix = val.substring(sufIndex+1);
							if(suffix=="png"||suffix=="jpg"||suffix=="jpeg"||suffix=="bmp"||suffix=="gif") {
								$(this).attr("name","index");
								$("#hiddenImgform").ajaxSubmit({
									url : 'product/productAction_uploadIndexImg.action',
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
			});
			
			//删除产品首页轮换图
			$("#delIndexImg").unbind("click");
			$("#delIndexImg").bind("click",function(){
				if(window.confirm("确认删除产品首页图片？")) {
					var title = $.trim($("#title").val());
					var brief = $.trim($("#brief").val());
					var indexImg = $("#indexImg").val();
					var arr = [];
					arr.push(ue.getContent());
					$.ajax({
						url:"product/productAction_update.action",
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
									url:"product/productAction_ftpImgDel.action",
									data:{
										"type" : 1,   //1 表示删除indeximg文件夹下的图片
										"img" : indexImg
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
			
			
			
			
			/* $(".pImg").each(function(){
				var $img = $(this);
				var $delImg = $img.next("img");
				$img.unbind("mouseenter");
				$img.bind("mouseenter",function(){
					var left = $img.offset().left+10;
					var top = $img.offset().top+10;
					$delImg.css({"left":left,"top":top});
					$delImg.show();
				});
				$img.unbind("mouseleave");
				$img.bind("mouseleave",function(){
					$delImg.hide();
				});
			}); */
		});
	</script>
	<!-- 文本编辑器 end -->
  </body>
</html>
