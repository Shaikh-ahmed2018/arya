$(document).ready(function(){
	
	$("#eventname").change(function(){
		getCategoryDropDown();
	});
	$("#categoryName").change(function(){
		getProgramDropDown();
	});
	
	$("#programName").change(function(){
		getStageDropDown();
	});
	$("#stageName").change(function(){
		getProgrammeDate();
	});
	
	$("#printOn").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : true,
		changeYear : true,
	});
	
	$("#print").click(function(){

   	    var catId = $("#categoryName").val();
		var evId = $("#eventname").val();
		var progId= $("#programName").val();
		var accId = $("#hacademicyaer").val();
		

		 if (evId == "" || evId==null) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select Event");
			showError("#eventname");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
		}else if (catId == "" || catId==null) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select Category");
			showError("#categoryName");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
		}
		else if (progId == "" || progId==null) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select Programme");
			showError("#programName");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
		}
		else if ($("#stageName").val() == "" || $("#stageName").val()==null) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select Stage");
			showError("#stageName");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
		}
		else{
		window.location.href = "EventsMenu.html?method=judgeentSheetPrint&catid="+catId+"&eventId="+evId+"&programmeId="+progId+"&accId="+accId;
		}
    
	});

});
function getCategoryDropDown(){
	var id = $("#eventname").val();
	
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getCategoryName",
		data :{"id":id,
				"flag":"onLoad",
				},
		async:false,
		success:function(data){
			var result=$.parseJSON(data);
			$("#categoryName").empty();
			$("#categoryName").append("<option value=''>----------select----------</option>");
			for(var i=0;i<result.data.length;i++){
				
			$("#categoryName").append("<option value='"+result.data[i].categoryId+"'>" 
					+result.data[i].categoryName+ "</option>");
			}
		}
	});
}
function getProgramDropDown(){
var catId = $("#categoryName").val();
var evId = $("#eventname").val();
datalist ={
		"catId":catId,
		"evId":evId,
		"flag":"onLoad",
};
$.ajax({
	type:'post',
	url:"EventsMenu.html?method=getprogramName",
	async:false,
	data:datalist,
	success:function(data){
		var result = $.parseJSON(data);
		$("#programName").empty();
		$("#programName").append("<option value=''>-----------select----------</option>");
		for(var i=0;i<result.data.length;i++){
		$("#programName").append("<option value='"+result.data[i].progId+"'>" 
				+result.data[i].progName+"</option>");
		}
	}
});
}

function getProgrammeDate(){
	
	var evId = $("#eventname").val();
	var progId = $("#programName").val();
	var stage =$("#stageName").val();
	datalist ={
			"evId":evId,
			"progId":progId,
			"stage":stage
	};
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getProgrammeDate",
		async:false,
		data:datalist,
		success:function(data){
			var result = $.parseJSON(data);
			$("#event").empty();
			$("#event").val(result.data[0].progDate);
			}
	});
}
function getStageDropDown(){
	var evId = $("#eventname").val();
	datalist ={
			"evId":evId,
	};
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getStage",
		async:false,
		data:datalist,
		success:function(data){
			var result = $.parseJSON(data);
			$("#stageName").empty();
			$("#stageName").append("<option value=''>-----------select----------</option>");
			for(var i=0;i<result.data.length;i++){
			$("#stageName").append("<option value='"+result.data[i].stageId+"'>" 
					+result.data[i].stageName+"</option>");
			}
		}
	});
}

