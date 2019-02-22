$(document).ready(function(){
	
	$("input,select").on({
		keypress: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	change: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	});
	
     $("#pdfDownload").click(function(){
    	    var catId = $("#categoryName").val();
    		var evId = $("#eventname").val();
    		var progId= $("#programName").val();
    		var houseId = $("#houseName").val();
    		var accId = $("#hacademicyaer").val();
    		var stage=$("#stageName").val();
    		
    		
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
    		}else if ($("#stageName").val() == "" || $("#stageName").val()==null) {
    			$(".errormessagediv").show();
    			$(".validateTips").text("Select Stage");
    			showError("#stageName");
    			setTimeout(function() {
    				$('.errormessagediv').fadeOut();
    			}, 3000);
    			
    			return false;
    		}
    		else{
		window.location.href = "EventsMenu.html?method=EventParticipentListPDF&catid="+catId+"&eventId="+evId+"&programmeId="+progId+"&houseId="+houseId+"&accId="+accId+"&stageId="+stage;
    		}
    		});	
      $("#print").click(function(){
    	  $(".eventName").text($("#eventname option:selected").text());
    	  $(".stagePrintName").text($("#stageName option:selected").text());
    	  $(".categoryPrintName").text($("#categoryName option:selected").text());
    	  $(".programePrintDate").text($("#event").val());
    	  
    	  A4();
      });
    /* $("#print").click(function(){
    	 var catId = $("#categoryName").val();
 		var evId = $("#eventname").val();
 		var progId= $("#programName").val();
 		var houseId = $("#houseName").val();
 		var accId = $("#hacademicyaer").val();
 		var stage=$("#stageName").val();

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
		window.location.href = "EventsMenu.html?method=EventParticipentListPrint&catid="+catId+"&eventId="+evId+"&programmeId="+progId+"&houseId="+houseId+"&accId="+accId+"&stageId="+stage;
		setTimeout(function() {
			window.location.href="EventsMenu.html?method=ParticipantsList";
		}, 2000);
	}
     });*/
     $('#excelDownload').click(function() {
			window.location.href = 'EventsMenu.html?method=EventParticipentListEXCEL';
			
		});
	$("#eventname").change(function(){
		getCategoryDropDown();
		getStageDropDown();
	});
	$("#categoryName").change(function(){
		getProgramDropDown();
	});
	$("#stageName").change(function(){
		getTableByData();
		getProgrammeDate();
	
		
	});
	$("#houseName").change(function(){
		getTableByHouse();
	});
	$("#categoryName").change(function(){
		getTableByData();
		getHouse($("#eventname").val());
	});
	$("#programName").change(function(){
		getTableByData();
	
		
	});
	
});

function getTableByHouse(){
	var catId = $("#categoryName").val();
	var evId = $("#eventname").val();
	var progId= $("#programName").val();
	var houseId = $("#houseName").val();
	var accId = $("#hacademicyaer").val();
	if(houseId==""){
		houseId="%%";
	}
	$.ajax({
		type:'POST',
		url:"EventsMenu.html?method=getTableByHouse",
		data:{	
			    "evId":evId,
				"catId":catId,
				"progId":progId,
				"houseId":houseId,
				"accId":accId,
			},
		async:false,
		success:function(data){
			var result= $.parseJSON(data);
			$("#individualPart tbody").empty();
			
			if(result.data.length>0){
				for(var i=0;i<result.data.length;i++){	
					$("#individualPart tbody").append("<tr>" +
									"<td>"+(i+1)+"</td>" +
									"<td>"+result.data[i].admissionNo+"</td>"+
									"<td>"+result.data[i].studentName+"</td>"+
									"<td>"+result.data[i].progName+"</td>"+
									"<td>"+result.data[i].classSec+"</td>"+
									"<td>"+result.data[i].chestNoId+"</td>"+
									"<td>"+result.data[i].houseName+"</td>"+
									"</tr>");
					}
			}else{
				$("#individualPart tbody").append('<tr><td colspan="7">No records Found</td></tr>');
			}
			pagination(300);
			$(".noOfRecords").empty();
			$(".noOfRecords").append("No of Records "+result.data.length+".");
			
			}
		});
   }
function getHouse(eventID){
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getHouseForParticipantList",
		data:{'eventID':eventID},
		async:false,
		
		success:function(data){
			var result=$.parseJSON(data);
			$("#houseName").empty();
			$("#houseName").append("<option value=''>ALL</option>");
			for(var i=0;i<result.data.length;i++){
			$("#houseName").append("<option value='"+result.data[i].houseId+"'>" 
					+result.data[i].houseName+ "</option>");
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

function getEventName(){
	data={"flag":"Indiv"};
	$.ajax({
		type:'post',
		data:{"flag":"Indiv"},
		url:"EventsMenu.html?method=getEventNameList_Group",
		async:false,
		success:function(data){
			var result=$.parseJSON(data);
				$("#eventName").empty();
				$("#eventName").append("<option value=''>------------Select----------</option>");
				for(var i=0;i<result.data.length;i++){
				$("#eventNameListIndiv").append("<option value='"+result.data[i].eventId+"'>" 
						+result.data[i].eventName+ "</option>");
				}
		}
	});
}

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
				$("#categoryName").append("<option value=''>----------Select----------</option>");
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
			$("#programName").append("<option value=''>ALL</option>");
			for(var i=0;i<result.data.length;i++){
			$("#programName").append("<option value='"+result.data[i].progId+"'>" 
					+result.data[i].progName+"</option>");
			}
		}
	});
}

function getTableByData(){
	var catId = $("#categoryName").val();
	var evId = $("#eventname").val();
	var progId= $("#programName").val();
	var houseId = $("#houseName").val();
	var accId = $("#hacademicyaer").val();
	if(houseId==""){
		houseId="%%";
	}
	$.ajax({
		type:'POST',
		url:"EventsMenu.html?method=getTableByData",
		data:{	
			    "evId":evId,
				"catId":catId,
				"progId":progId,
				"houseId":houseId,
				"accId":accId,
			},
		async:false,
		success:function(data){
			var result= $.parseJSON(data);
			$("#individualPart tbody").empty();
			
			if(result.data.length>0){
				for(var i=0;i<result.data.length;i++){	
					$("#individualPart tbody").append("<tr>" +
									"<td>"+(i+1)+"</td>" +
									"<td>"+result.data[i].admissionno+"</td>"+
									"<td>"+result.data[i].studentName+"</td>"+
									"<td>"+result.data[i].progName+"</td>"+
									"<td>"+result.data[i].classSec+"</td>"+
									"<td>"+result.data[i].chestNoId+"</td>"+
									"<td>"+result.data[i].houseName+"</td>"+
									"</tr>");
					}
			}else{
				$("#individualPart tbody").append('<tr><td colspan="7">No records Found</td></tr>');
			}
			pagination(300);
			$(".noOfRecords").empty();
			$(".noOfRecords").append("No of Records "+result.data.length+".");
			
			}
		});
   }


function getTableByDataHouse(){
	var catId = $("#categoryName").val();
	var evId = $("#eventname").val();
	var progId= $("#programName").val();
	var houseId = $("#houseName").val();
	var accId = $("#hacademicyaer").val();
	if(houseId==""){
		houseId="%%";
	}
	$.ajax({
		type:'POST',
		url:"EventsMenu.html?method=getTableByData",
		data:{	
			    "evId":evId,
				"catId":catId,
				"progId":progId,
				"houseId":houseId,
				"accId":accId,
			},
		async:false,
		success:function(data){
			var result= $.parseJSON(data);
			$("#individualPart tbody").empty();
			
			if(result.data.length>0){
				for(var i=0;i<result.data.length;i++){	
					$("#individualPart tbody").append("<tr>" +
									"<td>"+(i+1)+"</td>" +
									"<td>"+result.data[i].admissionno+"</td>"+
									"<td>"+result.data[i].rollNumber+"</td>"+
									"<td>"+result.data[i].studentName+"</td>"+
									"<td>"+result.data[i].classSec+"</td>"+
									"<td>"+result.data[i].chestNoId+"</td>"+
									"<td>"+result.data[i].houseName+"</td>"+
									"</tr>");
					}
			}else{
				$("#individualPart tbody").append('<tr><td colspan="8">No records Found</td></tr>');
			}
			$(".noOfRecords").empty();
			$(".noOfRecords").append("No of Records "+result.data.length+".");
			pagination(50);
			}
		});
   }
function getEventStudentRegList(){
	var evId= $("#eventNameListGroup").val();
	var catId= $("#CategoryNameListGroup").val();
	var progId= $("#progNameListGroup").val();
	var houseId= $("#houseNameListGroup").val();
	var accId =$("#hacademicyaer").val();
	
	if(evId=="" || evId==null){
		evId="%%";
	}
	if(catId=="" || catId==null){
		catId="%%";
	}
	if(progId=="" || progId==null){
		progId="%%";
	}
	if(houseId=="" || houseId==null){
		houseId="%%";
	}
	$.ajax({
		type:'POST',
		url:"EventsMenu.html?method=getEventStudentRegList",
		data:{	"evId":evId,
				"catId":catId,
				"progId":progId,
				"houseId":houseId,
				"accId":accId,
			},
		async:false,
		success:function(data){
			var result= $.parseJSON(data);
			$("#allstudent > tbody").empty();
			if(result.data.length>0){
				for(var i=0;i<result.data.length;i++){	
					$("#allstudent > tbody").append("<tr class='dispaly-table' id='"+result.data[i].registrationId+"'>" +
							"<td>"+(i+1)+"</td>" +
							"<td>"+result.data[i].registrationNo+"</td>" +
							"<td><table id='participants"+i+"'><thead><tr>" +
									"<th>Ad.No.</th><th>Name</th><th>Roll No.</th><th>Class</th><th>School</th><th>Image</th>" +
							"</tr></thead></table></td>"+
							"<td>"+result.data[i].houseName+"</td>"+
							"<td><span class='glyphicon glyphicon-trash deleteRow'></span></td>"+
							"</tr>");
		for(var j=0;j<result.data[i].participantsList.length;j++){
						$("#participants"+i).append("<tr class='"+result.data[i].participantsList[j].captainHighlight+"'>" +
								"<td>"+result.data[i].participantsList[j].admissionNo+"</td>"+
								"<td>"+result.data[i].participantsList[j].studentName+"</td>"+
								"<td>"+result.data[i].participantsList[j].rollNumber+"</td>" +
								"<td>"+result.data[i].participantsList[j].className+"</td>" +
								"<td>"+result.data[i].participantsList[j].location+"</td>" +
								"<td><img  class='fancybox'  src='"+result.data[i].participantsList[j].imageUrl+"' width='20' height='20' /></td>" +
								"</tr>");
				
					}
				}
				rowClickable();
				deleteRow();
			}else{
				$("#allstudent > tbody").append('<tr><td colspan="5">No records Found</td></tr>');
			}
			$(".noOfRecords").empty();
			$(".noOfRecords").append("No of Records "+result.data.length+".");
			pagination(50);
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
			$("#stageName").append("<option value=''>ALL</option>");
			for(var i=0;i<result.data.length;i++){
			$("#stageName").append("<option value='"+result.data[i].stageId+"'>" 
					+result.data[i].stageName+"</option>");
			}
		}
	});
}
function showError(id,errorMessage){
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
	$('.successmessagediv').hide();
	$(".errormessagediv").show();
	$(".validateTips").text(errorMessage);
	$(".errormessagediv").delay(3000).fadeOut();
}
function hideError(id){
	$(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
		});
}
function A4(){
	 var a=$("#printing-css-a4").val();
		var b = document.getElementById("printarea").innerHTML;

	    var abd='<style>' + a +'</style>' + b;

	   
	    
	var frame1 = $('<iframe />');
	frame1[0].name = "frame1";
	frame1.css({ "position": "absolute", "top": "-1000000px" });
	$("body").append(frame1);
	var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
	frameDoc.document.open();
	//Create a new HTML document.
	frameDoc.document.write('<html><head><title>DIV Contents</title>');
	//Append the external CSS file.
	frameDoc.document.write('<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />');
	frameDoc.document.write('<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">');
	frameDoc.document.write('<link href="CSS/newUI/modern-business.css" rel="stylesheet">');
	frameDoc.document.write('<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">');
	frameDoc.document.write('<link href="CSS/IdCard/'+$("#templatename").val()+'.css" rel="stylesheet" type="text/css" />');
	frameDoc.document.write('</head><body>');


	//Append the DIV contents.
	frameDoc.document.write(abd);
	frameDoc.document.write('</body></html>');
	frameDoc.document.close();
	setTimeout(function () {
	    window.frames["frame1"].focus();
	    window.frames["frame1"].print();
	    frame1.remove();
	}, 100);
	    


	}