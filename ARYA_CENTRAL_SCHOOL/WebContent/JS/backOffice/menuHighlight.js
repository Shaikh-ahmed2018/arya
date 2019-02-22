$(document).ready(function() {
	$("section.content").css({
		"margin-top":$("nav.navbar.navbar-inverse.navbar-fixed-top").height()
	});
	var windowSize=$(window).width();
	windowResize(windowSize);
	$("#"+$('#highlightName').val()).css("background-color", "#04666F");
	$("#"+$('#subModuleHighlightName').val()).css("background-color", "#eee");
	$("div[id^='menucollapse']").removeClass("in");
	$("#"+$('#subModuleHighlightName').val()).closest("div[id^='menucollapse']").addClass("in");
	
	$(window).resize(function(){
		var windowSize=$(this).width();
		windowResize(windowSize);
		$("section.content").css({
			"margin-top":$("nav.navbar.navbar-inverse.navbar-fixed-top").height()
		});
	});

	
		

	
	
});

function windowResize(windowSize){
	if(windowSize < 991 && windowSize > 767){
		$(".leftmenu").css({
			"position":"fixed",
				"width":"24%"
		});
		$("#div1,#div-main").removeClass("col-md-offset-2");
		$("#div1,#div-main").addClass("col-sm-offset-3");
		
	}
	else if(windowSize < 768 && windowSize > 239){
		$(".leftmenu").css({
			"position":"static",
			"width":"100%"
		});
		$("#div1,#div-main").removeClass("col-md-offset-2");
	}
	else{
		$(".leftmenu").css({
			"position":"fixed",
			"width":"16%"
		});
		$("#div1,#div-main").addClass("col-md-offset-2");
		$("#div1,#div-main").removeClass("col-sm-offset-3");
	}
}