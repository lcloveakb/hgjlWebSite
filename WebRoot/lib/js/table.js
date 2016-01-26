$(function() {

	$(".table tr td").mouseover(function() {
		
		$(this).parent().children().css("background-color","#CFCFCF");
	}).mouseout(function(){
		
		$(this).parent().children().css("background-color","");
	});
});