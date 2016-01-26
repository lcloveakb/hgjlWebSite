var bigAutocomplete = $("#bigAutocompleteContent");
var pathName = window.document.location.pathname;
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
function change() {
	var keyword = $("#tags").val();
	$.ajax({

		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		url : "search/searchAction_autoComplete.action",
		data : {
			'keyword' : keyword
		},
		datatype : "json",
		success : function(result) {
			makeContAndShow(result.jsonReturn);
		}
	});
}

// ��װ������html���ݲ���ʾ
function makeContAndShow(data_) {

	if (data_ == null || data_.length == 0) {
		 $("#bigAutocompleteContent").html("");
		 $("#bigAutocompleteContent").hide();
	}
	// alert(data_);
	 var cont = "<table><tbody>";
	var json_ = JSON.parse(data_);
	$.each(json_, function(i, element) {

			// alert(element[j]);
			cont += "<tr><td><div data-id=" + element.articleId + " data_id="
					+ element.type + ">" + element.title + "</div></td></tr>";
	});
	cont += "</tbody></table>";
	$("#bigAutocompleteContent").html(cont);
	$("#bigAutocompleteContent").show();

}

function news_huigan() {
	window.location.href = "index/indexAction_toError.action";
}

$(function() {

	
	/////////////////////////////////////////////////////////////����///////////////
	$(".caption p a.btn-primary").click(function() {

		 var id = $(this).attr("data-id");
		$("#myModal").toggle("fast", function() {
			// ����id���õ� ��������
			// alert("oooo="+id);
		});
	});
	$("#close_fx").click(function() {

		$("#myModal").hide("normal");
	});
	
	
	///////////////////////////////////////////////////////////////����//////////

	$("body")
			.append(
					"<div id='bigAutocompleteContent' class='bigautocomplete-layout'></div>");
	$(document).bind(
			'mousedown',
			function(event) {
				var $target = $(event.target);
				if ((!($target.parents().andSelf()
						.is('#bigAutocompleteContent')))
						&& (!$target.is(bigAutocomplete.currentInputText))) {
					$("#bigAutocomplete").hideAutocomplete();
				}
			});

	// �����ͣʱѡ�е�ǰ��
	$("#bigAutocompleteContent").delegate("tr", "mouseover", function() {
		$("#bigAutocompleteContent tr").removeClass("ct");
		$(this).addClass("ct");
	}).delegate("tr", "mouseout", function() {
		$("#bigAutocompleteContent tr").removeClass("ct");
	});

	// ����ѡ���к�ѡ�����������õ��������
	$("#bigAutocompleteContent").delegate(
			"tr",
			"click",
			function() {
				var jj = $(this).children().children().html();
				var parentId = $(this).children().children().attr("data-id");
				var type = $(this).children().children().attr("data_id");
				$("#tags").val(jj);
				$.trim(jj);
				$("#bigAutocompleteContent").hide();
				var url = "search/searchAction_itemSearch.action?keyword="
						+ jj + "&id=" + parentId + "&type=" + type;
				url = encodeURI(url);
				url = encodeURI(url);
				window.location.href = url;
			});

});
