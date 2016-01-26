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
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="lib/css/admin/list_style.css">

  </head>
  
  <body>
   <select id="cats">
   <option value="-1">--请选择类型--</option>
   	<option value="0" <c:if test="${category==0 }">selected="selected"</c:if>>慧感嘉联新闻</option>
   	<option value="1" <c:if test="${category==1 }">selected="selected"</c:if>>外部新闻</option>
   </select>
   
     <table class="data_list" cellpadding="10" align="center">
    	<tr><td>新闻标题</td><td colspan="3">操作</td></tr>
    	 <c:forEach items="${newsList }" var="n">
		   	<tr><td>${n.title }</td><td><a href="news/newsAction_toUpdate.action?id=${n.id }">修改</a></td>
		   		<td><a id="${n.id }" class="del_href">删除</a></td>
		   		<td><a id="${n.parentId }" class="top_href">置顶</a></td>
		   	</tr>
		  </c:forEach>
    </table>
   
   
   <input type="hidden" id="pageno" value="${pageno }"/>  
   	<!-- 分页 start -->
	<div class="pagenav" id="pager_bottom">
		<div class="mod_pagenav">
			<!-- 上下页跳转 start -->
			<p class="mod_pagenav_main">
				<c:choose>
					<c:when test="${pageno>1}">
						<a class="c_tx " href="news/newsAction_listInCategoryBackend.action?pageno=${pageno-1 }">上一页</a>
					</c:when>
					<c:otherwise>
						<b style="font-weight: bold;">上一页</b>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${pageno<pagecount}">
						<a class="c_tx " href="news/newsAction_listInCategoryBackend.action?pageno=${pageno+1 }">下一页</a>
					</c:when>
					<c:otherwise>
						<b style="font-weight: bold;">下一页</b>
					</c:otherwise>
				</c:choose>
			</p>
			<!-- 上下页跳转 end -->

			<!-- 转页start -->
			<p class="mod_pagenav_option">
				<span class="mod_pagenav_turn">转到 <input class="textinput"
					id="pageIndex_input2" value="1" type="text"> 页 每页显示<input
					class="textinput" id="pageSize_input3" value="${pagesize }"
					type="text"> 条
					<button onclick="javascript:return 	go_to();" type="button"
						class="bt_tx2">确 定</button><br>
						 当前第${pageno }页，总共${pagecount }页
				</span>
			</p>
			<script type="text/javascript">
				function go_to() {
					var no = document.getElementById("pageIndex_input2").value;
					var size = document.getElementById("pageSize_input3").value;
					if (!isNaN(no)) {
						var pagecount = document.getElementById("pagecount").value;
						if(no>pagecount) {
							no = pagecount;
						}
						if(no<1) {
							no = 1;
						}
						window.location.href = 'news/newsAction_listInCategoryBackend.action?pageno='
								+ no + '&pagesize=' + size;
					} else {
						alert("只允许输入数字");
						return false;
					}
				}
			</script>
			<!-- 转页 end -->
		</div>
	</div>
	<!-- 分页 end -->
   <script type="text/javascript" src="lib/js/jquery.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#cats").change(function() {
				var categoryid = $("#cats").val();
				window.location.href = "news/newsAction_listInCategoryBackend.action?category="
						+ categoryid;
			});
			$(".del_href").each(function(){
				var a = $(this);
				a.unbind("click");
				a.bind("click",function() {
					if(window.confirm("确认删除该新闻？")) {
						$.ajax({
							url : "news/newsAction_delete.action",
							type : "post",
							data : {
								"id" : a.attr("id")
							},
							datatype : "json",
							success : function(result) {
								if (result.ajaxStatus == 1) {
									alert("删除成功");
									window.location.reload();
								} else {
									alert("添加失败");
								}
							}
						});
					}
				});
			});
			$('.top_href').each(function(){
				var a=$(this);
				a.unbind("click");
				a.bind("click",function(){
					if(window.confirm("该条新闻将显示在首页第一条")){
						$.ajax({
							url: "index/indexAction_toTop.action",
							type: "post",
							data: {
								"id": a.attr("id")
							},
							datatype: "json",
							success: function(result){
								if(result.ajaxStatus==1){
									alert("置顶成功");
								}else{
									alert("置顶失败");
								}
							}
						})
					}
				});
			});
		});
	</script>
  </body>
</html>
