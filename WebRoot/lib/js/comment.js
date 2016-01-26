var current_pageno = 1;
var articleId;
function formateTime(timestr) {
	var JsonDateValue = new Date(timestr);
	var month = JsonDateValue.getMonth()+1;
	var monthtr = month<10?"0"+month:""+month;
	var date = JsonDateValue.getDate();
	var datestr = date<10?"0"+date:""+date;
	var hour = JsonDateValue.getHours();
	var hourstr = hour<10?"0"+hour:""+hour;
	var minute = JsonDateValue.getMinutes();
	var minutestr = minute<10?"0"+minute:""+minute;
	var text = JsonDateValue.getFullYear() + "-" + monthtr
			+ "-" + datestr + " " + hourstr
			+ ":" + minutestr;
	return text;
}
/**
 * 获取指定article的指定pageno分页的评论，具体DOM结构见产品详情页对应部分注释
 * @param articleId article的ID
 * @param pageno 分页页码，从1开始
 */
function page(articleId,pageno) {
	$.ajax({
		url : "comment/commentAction_pageItem.action",
		data : {
			"articleId" : articleId,
			"pageno" : pageno
		},
		datatype : "json",
		type : "post",
		success : function(data) {
//			alert(JSON.stringify(data));
			$("#pagelet-comment .top .cc").text(data.commentcount+"条评论");
			current_pageno = data.pageno;	// 当前页码
			var pagecount = data.pagecount;		// 总页数
			/*显示评论*/
			$("#ul_content").children("li").remove();	// 删除当前分页的评论
			var items = data.items;
			var appendHTML = "";
			for(var i=0,c=items.length;i<c;i++) {
				var item = items[i];
				appendHTML += "<li class='comment-item clearfix'>";
				appendHTML += "<div class='avatar'><a href='javascript:;' target='_blank'><img src='lib/images/user_default.jpg'></a></div>";
				appendHTML += "<div class='comment-content'><div class='name'><a href='javascript:;' target='_blank'>"+item.user.username+"</a></div>";
				appendHTML += "<div class='content'>"+item.content+"</div>";
				appendHTML += "<div class='comment_actions clearfix'>";
				appendHTML += "<span class='time'>"+formateTime(item.time)+"</span>";
//				appendHTML += "<div class='action'><a href='javascript:;' class='comment_digg comment_digged'>0</a>";
//				appendHTML += "<a class='c-comment' href='#' data-node='listcomment'><img src='lib/images/ic_comments.png'></a></div></div></div></li>";
				appendHTML += "</div></div></li>";
			}
			$("#ul_content").append(appendHTML);
			
			/*显示分页链接*/
			$("#pager").children("a").remove();
			if(pagecount>0) {
				appendHTML = "";
				if(current_pageno>1) {
					appendHTML += "<a class='page-number-prev' id='page-number-prev'>上一页</a>";
				}
				appendHTML += "<a class='page_number-next current'>"+current_pageno+"</a>";
				if(current_pageno<pagecount) {
					appendHTML += "<a class='page-number-next' id='page-number-next'>下一页</a>";
				}
				$("#pager").append(appendHTML);
				$("#page-number-next").bind("click",function() {
					page(articleId, current_pageno+1);
				});
				$("#page-number-prev").bind("click",function() {
					page(articleId, current_pageno-1);
				});
			}
		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {
			alert("评论加载失败");
		}
	});
};
$(document).ready(function() {
	articleId = $("#articleId").val();
	$("#ta_comment").bind("keyup",function(){
		var content = $(this).val();
		if(content.length>0)
			$(".publish-btn").addClass("active");
		else
			$(".publish-btn").removeClass("active");
	});
	$(".publish-btn").bind("click",function() {
		if($(this).hasClass("active")) {
			var content = $("#ta_comment").val();
			if(content.length<6) {
				alert("评论内容不能少于6个字");
				return;
			}
			var uid = $("#uid").val();
			if(uid=="") {
				alert("您尚未登录");
				return;
			}
			$.ajax({
				url : "comment/commentAction_addItem.action",
				data : {
					"userId" : uid,
					"articleId" : articleId,
					"content" : content
				},
				datatype : "json",
				type : "post",
				success : function(data) {
					var addResult = data.addResult;
					if(addResult=="1") {
						$("#ta_comment").val("");
						$(".publish-btn").removeClass("active");
						page(articleId, 1);
					} else if (addResult=="0") {
						alert("添加失败");
					}
				},
				error : function(XmlHttpRequest, textStatus, errorThrown) {
					alert(textStatus + "\n" + errorThrown);
				}
			});
		}
	});
	page(articleId, 1);
});