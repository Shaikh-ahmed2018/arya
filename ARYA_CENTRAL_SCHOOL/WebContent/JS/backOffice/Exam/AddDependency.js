var exam_Code = null;
var examId = null;
    var start_date = null;
    var end_date = null;

$(document).ready(function(){
	rowDependency = $("#dependency tbody tr").length-1;
	getexamdetails($("#hiddenaccyear"),$("#hiddenloc"));
	rowindex = 0;
	
     $("#back").click(function()
	  {
		window.location.href="examTimetablePath.html?method=examdependency";
	  });
     newenddate = 0;
     newstddate = 0;
     newexamcode = 0;
     $("#enddate1").change(function(){
    	newenddate = $(this).val();
     });
     $("#startdate1").change(function(){
     	newstddate = $(this).val();
      });
 	$("#examname").keyup(function(){
		$(this).val($(this).val().toUpperCase());
	});
	$("#examname1").keyup(function(){
		$(this).val($(this).val().toUpperCase());
	});
	$("#examcode").keyup(function(){
		$(this).val($(this).val().toUpperCase());
	});
	$("#examcode1").keyup(function(){
		$(this).val($(this).val().toUpperCase());
	});
	
	$("#examcode1").change(function(){
	     newexamcode = $(this).val();
	});
	
     $("#examcode").change(function(){
 		var exmcode = $("#examcode").val();
 		
 		$.ajax({
 			
 			type : "POST",
 			url:"examTimetablePath.html?method=checkduplicateExamcode",
 			data : {"exmcode" :exmcode,"accyear":$("#hiddenaccyear").val(),"location":$("#hiddenloc").val()},
 			async:false,
 			success : function(data){
 				var result = $.parseJSON(data);
 				
 				if(result.status == "true"){
 					$(".errormessagediv").show();
 					$("#examcode").val("");
 					document.getElementById("examcode").style.border = "1px solid #AF2C2C";
 					document.getElementById("examcode").style.backgroundColor = "#FFF7F7";
 					$(".validateTips").text("Exam Code configured for this Academic Year");
 					setTimeout(function() {
 						$('.errormessagediv').fadeOut();
 					}, 3000);
 				}
 			}
 			
 		});
 	});
	
	$(".examdetails").click(function()
	 {
		$("#exmdetail").slideToggle();
	 });

	$("#addexam").click(function()
	{
		$("#dialog").dialog("open");
    });
	$("#dialog").dialog({
        autoOpen  : false,
		modal     : true,
		title     : "Add Exam",
		buttons   : {
        "Save": {
                    text: 'Save',
                    click: function() {
            	    
                    var examcode=$("#examcode").val();
					var examname=$("#examname").val();
					var startdate=$("#startdate").val();
					var enddate=$("#enddate").val(); 
					var academiyear = $("#hiddenaccyear").val();
					var location = $("#hiddenloc").val();

					if(examcode == "" || examcode == null){
						$(".errormessagediv").show();
	 					document.getElementById("examcode").style.border = "1px solid #AF2C2C";
	 					document.getElementById("examcode").style.backgroundColor = "#FFF7F7";
	 					$(".validateTips").text("Field Required Exam Code");
	 					setTimeout(function() {
	 						$('.errormessagediv').fadeOut();
	 					}, 3000);
	 				return false
					}else if(examname == "" || examname == null){
						$(".errormessagediv").show();
	 					document.getElementById("examname").style.border = "1px solid #AF2C2C";
	 					document.getElementById("examname").style.backgroundColor = "#FFF7F7";
	 					$(".validateTips").text("Field Required Exam Name");
	 					setTimeout(function() {
	 						$('.errormessagediv').fadeOut();
	 					}, 3000);
	 				return false
					}else if(startdate == "" || startdate == null){
						$(".errormessagediv").show();
	 					document.getElementById("startdate").style.border = "1px solid #AF2C2C";
	 					document.getElementById("startdate").style.backgroundColor = "#FFF7F7";
	 					$(".validateTips").text("Field Required Start Date");
	 					setTimeout(function() {
	 						$('.errormessagediv').fadeOut();
	 					}, 3000);
	 				return false
					}else if(enddate == "" || enddate == null){
						$(".errormessagediv").show();
	 					document.getElementById("enddate").style.border = "1px solid #AF2C2C";
	 					document.getElementById("enddate").style.backgroundColor = "#FFF7F7";
	 					$(".validateTips").text("Field Required End Date");
	 					setTimeout(function() {
	 						$('.errormessagediv').fadeOut();
	 					}, 3000);
	 				return false
					}
					else if(checkduplicatedate()==1){

	 					$(".errormessagediv").show();
	 					$("#startdate").val("");
	 					$("#enddate").val("");
	 					document.getElementById("startdate").style.border = "1px solid #AF2C2C";
	 					document.getElementById("startdate").style.backgroundColor = "#FFF7F7";
	 					document.getElementById("enddate").style.border = "1px solid #AF2C2C";
	 					document.getElementById("enddate").style.backgroundColor = "#FFF7F7";
	 					$(".validateTips").text("Exam is already configured for this Dates");
	 					setTimeout(function() {
	 						$('.errormessagediv').fadeOut();
	 					}, 3000);
	 				return false
					}
					else{
					datalist = {
							 
							"examcode" : examcode,
							"examname" : examname,
							"startdate" : startdate,
							"enddate" : enddate,
							"academiyear" : academiyear,
							"location" : location
					},
					
					
					$.ajax({

						type : "POST",
						url :"examTimetablePath.html?method=insertexamdetails",
						data : datalist,
						async : false,
						success: function(data) {
							var result = $.parseJSON(data);
							 if(result.status == "true"){
						    	 $(".successmessagediv").show();
									$(".validateTips").text("Record Adding Progressing...");
									
									setTimeout(function() {
										$('.successmessagediv').fadeOut();
										window.location.reload();
									}, 3000);
							 }
						}
					});
                    }
					$(this).dialog('close');	
					
				}
			},
			'Close' : function() {
				$(this).dialog('close');
				$(".errormessagediv").hide();
				
			}
		}
	
	});
	
	

	$("#startdate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:$("#hiddenendaccyear").val()-1,
        minDate :$("#hiddenstartaccyear").val(),
		changeMonth : true,
		changeYear : true,
		onClose : function(selectedDate) {
	        var max = $(this).datepicker('getDate');
			$("#enddate").datepicker("option","minDate", max || $("#hiddenendaccyear").val());
		}
	});	

	$("#enddate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:$("#hiddenendaccyear").val()-1,
        minDate :$("#hiddenstartaccyear").val(),
		changeMonth : true,
		changeYear : true,
		onClose : function(selectedDate) {
			var max = $(this).datepicker('getDate');
			$("#datepicker").datepicker("option","minDate",max || '+1Y');
		}
	});
	$("#startdate1").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:$("#hiddenendaccyear").val()-1,
        minDate :$("#hiddenstartaccyear").val(),
		changeMonth : true,
		changeYear : true,
		onClose : function(selectedDate) {
			var max = $(this).datepicker('getDate');
			$("#enddate1").datepicker("option","minDate", max || $("#hiddenendaccyear").val());
		}
	});	

    $("#enddate1").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:$("#hiddenendaccyear").val()-1,
        minDate :$("#hiddenstartaccyear").val(),
		changeMonth : true,
		changeYear : true,
		onClose : function(selectedDate) {
			var max = $(this).datepicker('getDate');
			$("#todate").datepicker("option","minDate",max || '+1Y');
		}
	});
    
    $('.depend').click(function()
    		{
    	examCode = $(this).parent("td").parent("tr").find(".examcode").text();
    	examid=$(this).parent("td").parent("tr").find(".examid").attr("id");
    	examname = $(this).parent("td").parent("tr").find(".examid").text();
    	startdate =$(this).parent("td").parent("tr").find(".startdate").text();
    	enddate =$(this).parent("td").parent("tr").find(".enddate").text();


    	exam_Code = examCode;
    	examId=examid;
    	start_date = startdate;
    	end_date = enddate;
    	oldstartdate = startdate;
    	oldenddate = enddate;
    	oldexamcode = examCode;

    	$("#examcode1").val(examCode);
    	$("#examname1").val(examname);
    	$("#startdate1").val(startdate);
    	$("#enddate1").val(enddate);
    	$("#examId").val(examId);
    	$("#dialog2").dialog("open");

    	getExamcode1(0);

    		});
    
	$("#dialog2").dialog({

		autoOpen  : false,
		modal     : true,
		title     :"Exam Dependency",
		buttons   : {
			"Save": {
				text: 'Save',
				click: function() {
					myselectArray = [];
				    myinputArray = [];
					$("select[id^='code']").each(function(){
						 var examDcode=$(this).val().trim();
						 
						 if(examDcode==""||examDcode==undefined){
							 $(".errormessagediv").show();
							 $(".validateTips").text("Select Dependency Exam");
							 return false;
						 }
						 myselectArray.push(examDcode); 
					});
					
					$("input[id^='perse']").each(function(){
						 var examDperse=$(this).val();
						 if(examDperse==""||examDperse==undefined){
							 $(".errormessagediv").show();
							 $(".validateTips").text("Enter Dependency Percentage");
							 return false;
						 }
						 myinputArray.push(examDperse); 
					});
				    if(myselectArray.length==0){
				    	$(".errormessagediv").show();
						$(".validateTips").text("Select Dependency Exam");
						return false;
				    }else if(myinputArray.length==0){
						$(".errormessagediv").show();
						$(".validateTips").text("Please Enter Dependency Percentage");
						return false;
					}else{
						var examcode=$("#examcode1").val();
				    	var examname=	$("#examname1").val();
				    	var examId=$("#examId").val();
				    	datalist = {
				    			 "examId":examId,
				    			"myselectArray":myselectArray,
				    			"myinputArray":myinputArray,
				    			"examcode":examcode,
				    			"examname":examname
						},
						$.ajax({
							type : "POST",
							url : "examTimetablePath.html?method=saveExamDependency",
							data : datalist,
							async:false,
							success : function(data){
								var result = $.parseJSON(data);
								if(result.status == "success"){
							    	 $(".successmessagediv").show();
										$(".validateTips").text("Dependency Adding in Progress...");
										$("#dialog2").dialog("close");
										location.reload();
										
								 }else if(result.status == "already"){
							    	 $(".errormessagediv").show();
										$(".validateTips").text("Already Dependency Created For This Exam");
										$("#dialog2").dialog("close");
										location.reload();
								 }
								else{
							    	 $(".errormessagediv").show();
										$(".validateTips").text("Error...");
										$("#dialog2").dialog("close");
										location.reload();
								 }
							}
						});
					}
				    	
				}
			},
			'No' : function() {
				$(this).dialog('close');
				location.reload();
			}
		}
	});
	function editExam(pointer){
		var exam =pointer;
		datalist = {
				
				"examcode" : $("#examcode1").val(),
				"examname" : $("#examname1").val(),
				"startdate": $("#startdate1").val(),
				"enddate":    $("#enddate1").val(),
				"examid" : exam.trim(),
				 "academiyear" : $("#hiddenaccyear").val(),
				 "location" : $("#hiddenloc").val()
		},
		$.ajax({

			type : "POST",
			url : "examTimetablePath.html?method=editExamSettings",
			data : datalist,
			async:false,
			success : function(data){
				var result = $.parseJSON(data);
				if(result.status == "true"){
			    	 $(".successmessagediv").show();
						$(".validateTips").text("Updating Adding Progressing...");
						
						setTimeout(function() {
							$('.successmessagediv').fadeOut();
							location.reload();
						}, 3000);
				 }
			}
		});

	}
    $(".aligning").click(function()
			{
		id=$(this).attr("id");
		$("#dialog1").dialog("open");

			});
	$("#dialog1").dialog({

		autoOpen  : false,
		modal     : true,
		title     : "Delete Exam",
		buttons   : {
			"Yes": {
				text: 'Yes',
				click: function() {
					deleteExam(id);
					$(this).dialog('close');	
				}
			},
			'No' : function() {
				$(this).dialog('close');
			}
		}
	});

});


function addMoreRow() {
	if(rowDependency< (noOfExam-1)){
		rowDependency++;
		$('#dependency tr').last().after(
		 '<tr id="rowid'+rowDependency+'">'
		+'<td ><label for="inputEmail"style="text-align: center; line-height: 35px; width: 88px">Exam Code</label></td>'
		+'<td ><select style="width: 150px;" id="code'+rowDependency+'">'
		+'</select></td>'
		+'<td ><input type="text" style="width: 50px;" id="perse'+rowDependency+'"/></td>'
		+'<td >%</td>'
		+'<td align="right"><a href="javascript:void(0);" class="glyphicon glyphicon-trash" onclick="removeDepenency('+rowDependency+')"></a></td></tr>');
		getExamcode1(rowDependency);
		}
}
function getExamcode1(rowDependency){
		var locid = $("#hiddenloc").val().trim();
		var academic_year = $("#hiddenaccyear").val();
		datalist = {
				"examId" : examId,
				 "locid" : locid,
				"accYear" : academic_year,
				"stDate" : start_date,
				"enDate" : end_date,
				"exCode" : exam_Code,
		},
	    $.ajax({
		type : 'POST',
		url : "examTimetablePath.html?method=getExamcodeForDependency",
		data : datalist,
		async : false,
		success : function(data) {
		var result = $.parseJSON(data);
	$("#code"+rowDependency).empty();
	$("#code"+rowDependency).append('<option value="">---Select---</option>');
		for ( var j = 0; j < result.exam_Lists.length; j++) {
				$("#code"+rowDependency).append('<option value="'+result.exam_Lists[j].examId+ '">'
					+result.exam_Lists[j].examcode+ '</option>');
		}	
		}
	});
}
function removeDepenency(rowDependency) {
	jQuery('#rowid'+rowDependency).remove();
	rowDependency--;
}
function deleteExam(pointer){
	var exam =pointer;
	datalist = {
			"examid" : exam,
			"location" : $("#hiddenloc").val(),
			"accyear" : $("#hiddenaccyear").val()
	},
	$.ajax({

		type : "POST",
		url : "examTimetablePath.html?method=deleteExamSettings",
		data : datalist,
		async:false,
		success : function(data){
			var result = $.parseJSON(data);
			if(result.status == "true"){
		    	 $(".successmessagediv").show();
					$(".validateTips").text("Deleting Adding Progressing...");
					
					setTimeout(function() {
						$('.successmessagediv').fadeOut();
						location.reload();
					}, 3000);
			 }else{
				 $(".errormessagediv").show();
				 $(".validateTips").text("Exam is Mapped Cannot be Deleted...");
					
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
						location.reload();
					}, 3000);
			 }
		}
	});

}

function checkduplicatedate(){
	
	flag = 0;
	
	datalist = {
			 "startdate" : $("#startdate").val(),
			  "enddate" : $("#enddate").val(), 
			  "location" : $("#hiddenloc").val(),
			 "accyear" : $("#hiddenaccyear").val()
	}
	
	$.ajax({

		type : "POST",
		url : "examTimetablePath.html?method=checkduplicatedate",
		data : datalist,
		async:false,
		success : function(data){
			var result = $.parseJSON(data);
			if(result.status == "true"){
				flag = 1;
			}
		}
	});
	
	return flag;
	
}
function checkduplicatedateedit(oldstartdate,oldenddate){
	
	flag = 0;

	if(oldstartdate != $("#startdate1").val() && oldenddate != $("#enddate1").val()){

		flag = 0;
	
		datalist = {
			 "startdate" : $("#startdate1").val(),
			  "enddate" : $("#enddate1").val(), 
			  "location" : $("#hiddenloc").val(),
			  "accyear" : $("#hiddenaccyear").val()
		}
	
		$.ajax({

			type : "POST",
			url : "examTimetablePath.html?method=checkduplicatedate",
			data : datalist,
			async:false,
			success : function(data){
				var result = $.parseJSON(data);
				if(result.status == "true"){
				flag = 1;
				}
			}
		});
	}else{
		flag = 0;
	}
	return flag;
	
}

function checkduplicateexamedit(oldexamcode){
 		
		rs= 0;
 
 		if(oldexamcode !=$("#examcode1").val()){

 		$.ajax({
 			type : "POST",
 			url:"examTimetablePath.html?method=checkduplicateExamcode",
 			data : {"exmcode" :$("#examcode1").val(),"accyear":$("#hiddenaccyear").val(),"location":$("#hiddenloc").val()},
 			async:false,
 			success : function(data){
 				var result = $.parseJSON(data);
 				
 				if(result.status == "true"){
 					rs = 1;
 				}
 			}
 		});
 		}
 		else{
 			rs=0;
 		}
 		return rs;
 }

function getexamdetails(pointer,pointer1){

	$.ajax({
			type : "POST",
			url:"examTimetablePath.html?method=getexamdetails",
			data : {"accyear":$("#hiddenaccyear").val(),"location":$("#hiddenloc").val()},
			async:false,
			success : function(data){
				var result = $.parseJSON(data);
				noOfExam=result.examsettings.length;
				$("#markstable").empty();
				if(result.examsettings.length>0){
				$("#markstable").append("<table class='table' id='allstudent' width='100%'>"+"<thead><tr>"+
						"<th>S.No</th>" +
						"<th>Exam Code</th>" +
						"<th>Exam Name</th>" +
						"<th>Start Date</th>" +
						"<th>End Date</th>"+
						"<th></th>"+
						"</tr></thead>" +
						"<tbody></tbody></table>"
						);
				}else{

					$("#markstable").append("<table class='table' id='allstudent' width='100%'>"+"<thead><tr>"+
							"<th>S.No</th>" +
							"<th>Exam Code</th>" +
							"<th>Exam Name</th>" +
							"<th>Start Date</th>" +
							"<th>End Date</th>"+
							"</tr></thead>" +
							"<tbody></tbody></table>"
							);
					
				}
				if(result.examsettings.length>0){
				for(var i=0;i<result.examsettings.length;i++){
					
					$("#markstable #allstudent tbody").append(
							"<tr>"+
							"<td class='sno'>"+result.examsettings[i].sno+"</td>"+
							"<td class='examcode'>"+result.examsettings[i].examCode+"</td>"+
							"<td class='examid' id='"+result.examsettings[i].examid+"'>"+result.examsettings[i].examName+"</td>"+
							"<td  class='startdate'>"+result.examsettings[i].startDate+"</td>"
							+"<td class='enddate'>"+result.examsettings[i].endDate+"</td>"
							+"<td style='text-align: center;'><span class='buttons depend'>Dependency</span></td>"
							+"</tr>"
					);
				}
				
				
				}
				else{
					$('#allstudent tbody').append("<tr><td colspan='5'>NO Records Found</td></tr>");
				}
				
				$("#allstudent").after("<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select></div><div class='pagination pagelinks'></div>")
				pagination(50);
				$("#show_per_page").change(function(){
					pagination($(this).val());
				});
			}
		});
	
	
}

function pagination(list) {

	var show_per_page = list;
    var number_of_items = $('#allstudent tbody tr,.allstudent tbody tr').length;
   
    var number_of_pages = Math.ceil(number_of_items / show_per_page);
    
    $('.pagination').empty();
    $('.pagination').append('<div class=controls></div><input id=current_page type=hidden><input id=show_per_page type=hidden>');
    $('#current_page').val(0);
    $('#show_per_page').val(show_per_page);

    var navigation_html = '<a class="prev" onclick="previous()">Prev</a>';
    var current_link = 0;
    while (number_of_pages > current_link) {
        navigation_html += '<a class="page" onclick="go_to_page(' + current_link + ')" longdesc="' + current_link + '">' + (current_link + 1) + '</a>';
    	 current_link++;
    }
    navigation_html += '&nbsp;&nbsp;&nbsp;<a class="next" onclick="next()">Next</a>';

    $('.controls').html(navigation_html);
    $('.controls .page:first').addClass('active');
    $(".controls").find(".page").hide();
    $(".controls").find(".active").show();
    $(".controls").find(".active").prev().prev().show();	
    $(".controls").find(".active").prev().show();	
    $(".controls").find(".active").next().show();	
    $(".controls").find(".active").next().next().show();
   
    $('#allstudent tbody tr,.allstudent tbody tr').css('display', 'none');
    $('#allstudent tbody tr,.allstudent tbody tr').slice(0, show_per_page).css('display', 'table-row');
}

function go_to_page(page_num) {
    var show_per_page = parseInt($('#show_per_page').val(), 0);

    start_from = page_num * show_per_page;

    end_on = start_from + show_per_page;
    $(".controls").find(".page").hide();
    $(".controls").find(".active").show();
    $(".controls").find(".active").prev().prev().show();	
    $(".controls").find(".active").prev().show();	
    $(".controls").find(".active").next().show();	
    $(".controls").find(".active").next().next().show();	
    $('#allstudent tbody tr,.allstudent tbody tr').css('display', 'none').slice(start_from, end_on).css('display', 'table-row');

    $('.page[longdesc=' + page_num + ']').addClass('active').siblings('.active').removeClass('active');

    $('#current_page').val(page_num);
}

function previous() {

    new_page = parseInt($('#current_page').val(), 0) - 1;
    //if there is an item before the current active link run the function
    if ($('.active').prev('.page').length == true) {
        go_to_page(new_page);
    }
}

function next() {
    new_page = parseInt($('#current_page').val(), 0) + 1;
    //if there is an item after the current active link run the function
    if ($('.active').next('.page').length == true) {
        go_to_page(new_page);
    }
}

