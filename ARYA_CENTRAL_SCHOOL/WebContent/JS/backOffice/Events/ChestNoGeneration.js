$(document).ready(function(){
	 $(".studentchestno").hide();
		
		
		$("#print").click(function(){
		
      	  
     	   var b= document.getElementById("printArea").innerHTML;
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
		});
		
		
		$("#pdfDownload").click(function(){
		   			window.location.href ="EventsMenu.html?method=printChestNopdf&eventId="+$("#eventNameList").val()+"&catid="+$("#categoryNameList").val()+"&programmeId="+$("#programNameList").val()
					+"&programmeName="+$("#programNameList option:selected").text()+"&eventName="+$("#eventNameList option:selected").text()+"&categoryName="+$("#categoryNameList option:selected").text();
		});
		
	       $("#excelDownload").click(function(){
	    	   window.location.href ="EventsMenu.html?method=printChestNoexcel&eventId="+$("#eventNameList").val()+"&catid="+$("#categoryNameList").val()+"&programmeId="+$("#programNameList").val()
				+"&programmeName="+$("#programNameList option:selected").text()+"&eventName="+$("#eventNameList option:selected").text()+"&categoryName="+$("#categoryNameList option:selected").text();
			});
		
		
		$("#eventNameList").change(function(){
			getCategoryName();
			getProgramNameByCategory();
			
		});
		$("#categoryNameList").change(function(){
			getProgramNameByCategory();
			getEventStudentRegChestNoList();
		});
		
		$("#programNameList").change(function(){
			getEventStudentRegChestNoList();
		});

		$("#progDate").datepicker({
			dateFormat : "dd-mm-yy",
			minDate : 0,
			changeMonth : true,
			changeYear : true,
			onClose : function(selectedDate) {
			}
		});	
		$("#progCreation").dialog({
		    autoOpen  : false,
		    maxWidth  :	850,
	        maxHeight : 350,
	        width     : 750,
	        height    :	300,
		    modal     : true,
		    title     : "Chest No Registation Setting",
		    buttons   : {
		    	'Save'  : function() {
		    		var pointer=$(this);
		    		saveProgram(pointer);
		    		
		    	},
		    	'Close' : function() {
		    		getEventStudentRegChestNoList();
		    		$(this).dialog('close');
		    		$("#eventName option").show();
		    		$("#categoryName option").show();
		    		
		    		$(".errormessagediv").hide();
		    		$(".form-control").css({
		    			"border":"1px solid #ccc",
		    			"background-color":"#fff"
		    		});
	             }
		    }
		});
		
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
		
		$("#chestnogeneration").click(function(){
        	   $(".studentchestno").show();
        	   $("#save").show();
        	   $("#back1").show();
        	   $("#allstudent").hide();
        	   $("#chestnogeneration").hide();
        	   $("#iconsimg").show();
        	   ChestNoGeneration();
        	   saveGeneratedchestNo();
		});

});//jquery ends
function hideError(id){
	$(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
		});
}
function getCategoryNameList(){
	var id = $("#eventNameList").val();
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getCategoryName",
		async:false,
		data:{"id":id},
		success:function(data){
			var result = $.parseJSON(data);
			$("#categoryNameList").empty();
			$("#categoryNameList").append("<option value=''>ALL</option>");
			for(var i=0;i<result.data.length;i++){
			$("#categoryNameList").append("<option value='"+result.data[i].categoryId+"'>" 
					+result.data[i].categoryName+"</option>");
			}
		}
	});	
}

function getCategoryName(){
	var id = $("#eventNameList").val();
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getCategoryNameByEvent",
		async:false,
		data:{"id":id},
		success:function(data){
			var result = $.parseJSON(data);
			$("#categoryNameList").empty();
			$("#categoryNameList").append("<option value=''>ALL</option>");
			for(var i=0;i<result.data.length;i++){
			$("#categoryNameList").append("<option value='"+result.data[i].categoryId+"'>" 
					+result.data[i].categoryName+"</option>");
			}
		}
	});
}
function getProgramNameByCategory(){
	var eventid = $("#eventNameList").val();
	var categoryid = $("#categoryNameList").val();
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getProgramNameByCategory",
		async:false,
		data:{"eventid":eventid,
			  "categoryid":categoryid
			},
		success:function(data){
			var result = $.parseJSON(data);
			$("#programNameList").empty();
			$("#programNameList").append("<option value=''>ALL</option>");
			for(var i=0;i<result.data.length;i++){
			$("#programNameList").append("<option value='"+result.data[i].progId+"'>" 
					+result.data[i].progName+"</option>");
			}
		}
	});	
}

function progNameNameList(){
	var catId = $("#eventNameListGroup").val();
	var evId = $("#CategoryNameListGroup").val();
	datalist ={
			"catId":catId,
			"evId":evId,
			"flag":"GroupS", // for differncite in daoimpl queries
	};
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getprogramName",
		async:false,
		data:datalist,
		success:function(data){
			var result = $.parseJSON(data);
			$("#progNameListGroup").empty();
			$("#progNameListGroup").append("<option value=''>ALL</option>");
			for(var i=0;i<result.data.length;i++){
				$("#progNameListGroup").append("<option value='"+result.data[i].progId+"'>" 
						+result.data[i].progName+"</option>");
			}
		}
	});
}
function getEventStudentRegChestNoList(){
	var evId= $("#eventNameList").val();
	var catId = $("#categoryNameList").val();
	var progId = $("#programNameList").val();
	$.ajax({
		type:'POST',
		url:"EventsMenu.html?method=getEventStudentRegChestNoList",
		data:{	"evId":evId,
				"catId":catId,
				"progId":progId,
			},
		async:false,
		success:function(data){
			var result= $.parseJSON(data);
			$("#allstudent  tbody").empty();
			$("#printArea").empty();
			if(result.data.length>0){
				for(var i=0;i<result.data.length;i++){	
						$("#allstudent tbody").append("<tr class='student' value='"+result.data[i].admissionNo+"'>" 
								+"<td>"+(i+1)+"</td>"
								+"<td>"+result.data[i].admissionNo+"</td>"
								+"<td>"+result.data[i].studentName+"</td>"
								+"<td>"+result.data[i].location+"</td>"
								+"<td><img src='"+result.data[i].imageUrl+"' width='20' height='20'/></td>"
								+"<td>"+result.data[i].chestNoId+"</td>"
								+"</tr>");
						
						$("#printArea").append("<h1 style='font-size:250px;text-align:center;padding:50px;border:5px solid #000;'>"+result.data[i].chestNoId+"</h1>");
				}
			}else{
				$("#allstudent tbody").append('<td colspan="6">No records Found</td>');
			}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No of Records "+result.data.length+".");
			pagination(50);
			}
		});
}
function getDataforUpdateProgram(id){
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getDataforUpdateChestNoGeneration",
		data:{"id":id},
		asunc:false,
		success:function(data){
			var result =$.parseJSON(data);
			$("#eventName").val(result.data[0].eventId);
			getCategoryName();
			getProgramNameByCategory();
			$("#categoryName").val(result.data[0].categoryId);
			$("#programName").val(result.data[0].progId);
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


function ChestNoGeneration(){
	var catId = $("#categoryNameList").val();
	var evId = $("#eventNameList").val();
	var progId = $("#programNameList").val();

	if(catId=="" || catId==null){
		catId="all";
	}
	if(evId=="" || evId==null){
		evId="all";
	}
	if(progId=="" || progId==null){
		progId="all";
	}
	datalist ={
			"catId":catId,
			"evId":evId,
			"progId":progId
	};
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getChestNoList",
		data:datalist,
		async:false,
		success:function(data){
			var result= $.parseJSON(data);
			$("#allstudent  tbody").empty();
			if(result.data.length>0){
				for(var i=0;i<result.data.length;i++){	
						$("#allstudent").append("<tr class='"+result.data[i].admissionNo+"'>" +
								"<td>"+(i+1)+"</td>"+
								"<td class='admisionNoId'>"+result.data[i].admissionNo+"</td>"+
								"<td>"+result.data[i].studentName+"</td>"+
								"<td>"+result.data[i].location+"</td>" +
								"<td><img src='"+result.data[i].imageUrl+"' width='20' height='20' /></td>" +
								"<td class='chestNoId'>"+result.data[i].chestNoId+"</td>" +
								"</tr>");
				}
			}else{
				$("#allstudent tbody").append('<td colspan="6">No records Found</td>');
			}
			$(".noOfRecords").empty();
			$(".noOfRecords").append("No of Records "+result.data.length+".");
			pagination(50);
		}
		});
}

function saveGeneratedchestNo(){
	var catId = $("#categoryNameList").val();
	var evId = $("#eventNameList").val();
	var progId = $("#programNameList").val();
	admisionNoId=[];
	chestNoId=[];
	$('.admisionNoId').each(function() {
		var getData = $(this).text();
		admisionNoId.push(getData);
  });
  $('.chestNoId').each(function(){
		var getData = $(this).text();
		chestNoId.push(getData);
  });
	$.ajax({
		type:'POST',
		url:"EventsMenu.html?method=saveGeneratedchestNo",
		data:{ "admisionNoId":admisionNoId.toString(),
		      "chestNoId":chestNoId.toString(),
		      "evId":evId,
		      "catId":catId,
		      "progId":progId
		},
		async:false,
		success:function(data){
			var result=$.parseJSON(data);
			
			if(result.status=="true"){
				$('.errormessagediv').hide();
				$(".successmessagediv").show();
				$(".validateTips").text("Saving Record successfully...");
				$('input:text').val("");
				$("#progCreation").dialog('close');
				
				setTimeout(function(){
					window.location.href="EventsMenu.html?method=ChestNoGeneration"; 
           },0000);
		}
		else if(result.status=="false"){
	 		$('.successmessagediv').hide();
	 		setTimeout(function(){
				window.location.href="EventsMenu.html?method=ChestNoGeneration"; 
       },0000);
	 		 }
			
    		}
	});
}
function deleteRow(){
	$(".deleteRow").click(function(){
		id=	$(this).closest("tr").attr("id");
		$("#deleteDialog").dialog("open");
		$("#deleteDialog").empty();
		$("#deleteDialog").append("<p>Are you sure to delete?</p>");
 });
	}
