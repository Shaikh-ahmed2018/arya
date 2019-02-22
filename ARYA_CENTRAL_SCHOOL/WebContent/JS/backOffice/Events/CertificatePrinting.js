$(document).ready(function(){
	
	$("#eventname").change(function(){
		getCategoryDropDown();
		getStageDropDown();
	});
	$("#categoryName").change(function(){
		getProgramDropDown();
	});
	
	$("#certificateon").change(function(){
		getTableOnCertificate();
	});
	$("#programName").change(function(){
		$("#certificateon").val("");
	});
	$("#printOn").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : true,
		changeYear : true,
	});
	
	$("#print").click(function(){
		var name=$(":radio.student:checked").val().split(",")[7];
		var className=$(":radio.student:checked").val().split(",")[6];
		var postion=$(":radio.student:checked").val().split(",")[1];
		var progName=$("#programName option:selected").text();
		var printOn=$("#printOn").val().split("-")[2];
		var pintDate=$("#printOn").val();
		var houseName=$(":radio.student:checked").val().split(",")[8];
		 var b= document.getElementById($("#eventType").val()).innerHTML;
	  	   var abd= b;
	  	    var frame1 = $('<iframe />');
		        frame1[0].name = "frame1";
		        frame1.css({ "position": "absolute", "top": "-1000000px" });
		        $("body").append(frame1);
		        var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
		        frameDoc.document.open();
		        //Create a new HTML document.
		        frameDoc.document.write('<html><head><title>DIV Contents</title>');
		        //Append the external CSS file.
		        frameDoc.document.write('<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>');
		        frameDoc.document.write('<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">');
		        frameDoc.document.write('<link href="CSS/newUI/modern-business.css" rel="stylesheet">');
		        
		        
		        frameDoc.document.write('<link href="CSS/newUI/custome.css" rel="stylesheet">');
		        frameDoc.document.write('<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>');
		        frameDoc.document.write('<script type="text/javascript" src="JS/backOffice/Events/Resultprinting.js"></script>');
		        frameDoc.document.write('<style>label{font-size:24px;}.background-template,.houseNamePrint,.studentPrintName,.classNamePrint,.postionPrint,.progNamePrint,.printOnPrint,.printDatePrint{position:absolute;}.studentPrintName{top:370px;left:350px;}.houseNamePrint{top:410px;left:350px;}.classNamePrint{top:460px;left:250px;}.postionPrint{top:510px;left:210px;}.progNamePrint{top:510px;left:570px;}.printOnPrint{top:560px;left:450px;}.printDatePrint{top:660px;left:120px;}</style>');
		        frameDoc.document.write('</head><body>');
		      
		        
		        //Append the DIV contents.
		        frameDoc.document.write(abd);
		        frameDoc.document.write("<label class='studentPrintName'>"+name+"</label>" +
		        		"<label class='houseNamePrint'>("+houseName+")</label>" +
		        		"<label class='classNamePrint'>"+className+"</label>" +
		        		"<label class='postionPrint'>"+postion+"</label>" +
		        		"<label class='progNamePrint'>"+progName+"</label>" +
		        		"<label class='printOnPrint'>"+$('#globalAcademicYear option:selected').text()+"</label>" +
		        		"<label class='printDatePrint'>"+pintDate+"</label>");
		        frameDoc.document.write('</body></html>');
		        frameDoc.document.close();
		        setTimeout(function () {
		            window.frames["frame1"].focus();
		            window.frames["frame1"].print();
		            frame1.remove();
		        }, 100);
		
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
		$("#programName").append("<option value=''>-----------Select----------</option>");
		for(var i=0;i<result.data.length;i++){
		$("#programName").append("<option value='"+result.data[i].progId+"'>" 
				+result.data[i].progName+"</option>");
		}
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
			$("#stageName").append("<option value=''>-----------Select----------</option>");
			for(var i=0;i<result.data.length;i++){
			$("#stageName").append("<option value='"+result.data[i].stageId+"'>" 
					+result.data[i].stageName+"</option>");
			}
		}
	});
}
function getTableOnCertificate(){
	var catId = $("#categoryName").val();
	var evId = $("#eventname").val();
	var progId= $("#programName").val();
	var accId = $("#hacademicyaer").val();
	var certificateon=$("#certificateon").val();
	
	if(certificateon!="merit"){
		
		$(".place").hide();
		$(".sno").show();
	$.ajax({
		type:'POST',
		url:"EventsMenu.html?method=getTableOnCertificate",
		data:{	
		    "evId":evId,
			"catId":catId,
			"progId":progId,
			"accId":accId,
			"certificateon":certificateon,
		},
		async:false,
		success:function(data){
			var result= $.parseJSON(data);
			$("#individualPart tbody").empty();
			
			if(result.data.length>0){
				for(var i=0;i<result.data.length;i++){	
					$("#individualPart tbody").append("<tr>" +
									"<td><input type='radio' name='certificate' class='student' value='"+evId+","+result.data[i].place+","+catId+","+progId+","+accId+","+result.data[i].admissionNo+","+result.data[i].classSec+","+result.data[i].studentName+","+result.data[i].houseName+"'></td>" +
									"<td>"+result.data[i].admissionNo+"</td>"+
									"<td>"+result.data[i].studentName+"</td>"+
									"<td>"+result.data[i].classSec+"</td>"+
									"<td>"+result.data[i].chestNoId+"</td>"+
									"</tr>");
					}
			}else{
				$("#individualPart tbody").append('<tr><td colspan="5">No records Found</td></tr>');
			}
			
			}
		});
	}else{
		$(".place").show();
		$(".sno").hide();
		$.ajax({
			type:'POST',
			url:"EventsMenu.html?method=getTableOnCertificate",
			data:{	
			    "evId":evId,
				"catId":catId,
				"progId":progId,
				"accId":accId,
				"certificateon":certificateon,
			},
			async:false,
			success:function(data){
				var result= $.parseJSON(data);
				$("#individualPart tbody").empty();
				
				if(result.data.length>0){
					for(var i=0;i<result.data.length;i++){	
						$("#individualPart tbody").append("<tr>" +
										"<td><input type='radio' name='certificate' class='student' value='"+evId+","+result.data[i].place+","+catId+","+progId+","+accId+","+result.data[i].admissionNo+","+result.data[i].classSec+","+result.data[i].studentName.trim()+","+result.data[i].houseName+"'></td>" +
										"<td>"+result.data[i].admissionNo+"</td>"+
										"<td>"+result.data[i].studentName+"</td>"+
										"<td>"+result.data[i].classSec+"</td>"+
										"<td>"+result.data[i].chestNoId+"</td>"+
										"<td>"+result.data[i].place+"</td>"+
										"</tr>");
						}
				}else{
					$("#individualPart tbody").append('<tr><td colspan="6">No records Found</td></tr>');
				}
				
				}
			});
		
		
	}
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