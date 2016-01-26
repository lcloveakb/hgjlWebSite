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
<title>产品添加页</title>
<link rel="stylesheet" type="text/css" href="lib/css/admin/editor_style.css">
</head>
<body>
	<form id="hiddenImgform" style="display: none;" enctype="multipart/form-data">
						<input id="fileupload" name="index" type="file">
					</form>
	<div id="container">
		产品标题：<input type="text" name="pro_name" id="pro_name" value="" style="width:250px"><br>
		产品简介：<br><br>
		<textarea rows="6" cols="80"  name="pro_brief" id="pro_brief"></textarea><br>
		产品类别：<select name="pro_type" id="pro_type">
					<option value="">-请选择-</option>
					<option value="0">读写器</option>
					<option value="1">标签</option>
					<option value="2">其它</option>
				</select><br>
		首页展示：<select id="shown">
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
					<br>
				</c:otherwise>
			</c:choose>
		</div><br>
		产品图片（最多3张）：<div id="content">
								<div class="fieldset flash" id="fsUploadProgress">
									<span class="legend">上传文件及进度</span>
								</div>
								<input type="hidden" id="imgPath" value="" readonly="readonly">
								<div>
									<span id="spanButtonPlaceHolder"></span> 
									<input type="button" id="btnCancel" value="取消所有上传" onclick="swfu.cancelQueue();"
										style="font-size: 8pt; height: 29px; width: 100px;display: none">
								</div>
							</div>
		产品详情：<br><br>
		<div class="u_editor"><script id="editor" type="text/plain"></script></div>
		<button onclick="getContent()" style="display: block; width: 100px; height: 30px; margin-top: 20px">提交</button>
	</div>
	
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
		var indexImg_ = "";
		function getContent() {
			var category = $("#pro_type").val();
			var title = $("#pro_name").val();
			var brief = $("#pro_brief").val();
			var imgs = $("#imgPath").val();
			var shown = $("#shown").val();
			if ($.trim(category) == "") {
				alert("请选择类别");
				return;
			}
			if ($.trim(title) == "") {
				alert("请填写标题");
				return;
			}
			if ($.trim(brief) == "") {
				alert("请填写简介");
				return;
			}
			if ($.trim(imgs) == "") {
				alert("请添加图片");
				return;
			}
			//禁止shown为空 start
			if($("#shown").val()==0 && $("#imgPath").val()==""){
				alert("请选择首页图片");	
				return false;
			}
			var arr = [];
			arr.push(UE.getEditor('editor').getContent());
			if (arr.length == 0) {
				alert("参数不能为空");
				return;
			}
			$.ajax({
				url : "product/productAction_add.action",
				type : "post",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				data : {
					"content" : arr.join("\n"), //规格参数
					"category" : category, //产品类型
					"title" : title, //产品标题
					"brief" : brief, //产品简介
					"shown" : shown, //是否显示首页
					"indexImg" : indexImg_, //产品首页显示图片
					"img" : imgs
				//产品图片
				},
				datatype : "json",
				success : function(result) {
					if (result.ajaxStatus == 1) {
						alert("添加成功");
						window.open("product/productAction_detail.action?id="
										+ result.id);
					} else {
						alert("添加失败");
					}
					//window.location.reload();
				}
			});
		}
	
		$(function() {
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
									indexImg_ = fileName;
									//$("#imgPath").val(fileName);
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
			
			$("#btn_submit").on("click", function() {
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
						//alert(XmlHttpRequest);
						alert(textStatus + "\n" + errorThrown);
					}
				});
				return false;
			});
		});
	</script>

	<!-- 文件上传 start -->
	<script type="text/javascript" src="lib/js/swfupload.js"></script>
	<script type="text/javascript" src="lib/js/swfupload.queue.js"></script>
	<script type="text/javascript" src="lib/js/fileprogress.js"></script>
	<script type="text/javascript" src="lib/js/handlers.js"></script>
	<script type="text/javascript">
		var swfu;
		
		window.onload = function() {
			var settings = {
					flash_url : "lib/js/swfupload.swf",
					upload_url : "product/productAction_upload.action",
					file_size_limit : "2MB",
					file_types : "*.png;*.jpg;*.gif;*.bmp",
					file_types_description : "图片",
					file_upload_limit : 3,
					file_queue_limit : 0,
					file_post_name : "attachment",//action中File属性名称
					custom_settings : {
						progressTarget : "fsUploadProgress",
						cancelButtonId : "btnCancel"
					},
					debug : false, //是否显示调试窗口

					// Button settings
					button_width : "55",
					button_height : "40",
					button_placeholder_id : "spanButtonPlaceHolder",
					button_text : '<span class="button">选择图片</span>',
					button_text_style : ".button { font-size: 13;color: #FF0000;}",
					button_text_left_padding : 0,
					button_text_top_padding : 0,
					button_cursor: SWFUpload.CURSOR.HAND,

					// The event handler functions are defined in handlers.js
					file_queued_handler : fileQueued,
					file_queue_error_handler : fileQueueError,
					file_dialog_complete_handler : fileDialogComplete,
					upload_start_handler : uploadStart,
					upload_progress_handler : uploadProgress,
					upload_error_handler : uploadError,
					upload_success_handler : uploadSuccess,
					upload_complete_handler : uploadComplete,
					queue_complete_handler : queueComplete
				// Queue plugin event

				};

			//自己定义一个名字为uploadSuccess的函数即可被调用
			function uploadSuccess(file, serverData) {
				var progress = new FileProgress(file,
						this.customSettings.progressTarget);
				progress.setComplete();
				progress.setStatus(serverData + "<br>图片已上传完成.");
				//progress.setStatus(serverData);
				progress.toggleCancel(false);
				document.getElementById("imgPath").value += file.name + ";";
			}

			swfu = new SWFUpload(settings);
		};
	</script>
	<!-- 文件上传 end -->

</body>
</html>
