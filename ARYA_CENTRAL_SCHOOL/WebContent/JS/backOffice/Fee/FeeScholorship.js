$(document).ready(function(){
	
	$("#Acyearid").val($("#hacademicyaer").val());
	$("#AcademicYearFor").val($("#hacademicyaer").val());
	getStudentListByJs("all",$("#hacademicyaer").val(),"all","all","all");
	

	$("#locationname").change(function(){
		getClassList();
		locationId=$("#locationname").val();
		if($("#locationname").val()==""){
			locationId="all";
		}
		academicYear=$("#Acyearid").val();
		if($("#Acyearid").val()==""){
			academicYear="all";
		}
		classId=$("#classname").val();
		if($("#classname").val()==""){
			classId="all";
		}
		divisionId=$("#sectionid").val();
		if($("#sectionid").val()==""){
			divisionId="all";
		}
		searchTerm=$("#searchvalue").val();
		if($("#searchvalue").val()==""){
			searchTerm="all";
		}
		getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm);
	});
	$("#Acyearid").change(function(){
		
		locationId=$("#locationname").val();
		if($("#locationname").val()==""){
			locationId="all";
		}
		academicYear=$("#Acyearid").val();
		if($("#Acyearid").val()==""){
			academicYear="all";
		}
		classId=$("#classname").val();
		if($("#classname").val()==""){
			classId="all";
		}
		divisionId=$("#sectionid").val();
		if($("#sectionid").val()==""){
			divisionId="all";
		}
		searchTerm=$("#searchvalue").val();
		if($("#searchvalue").val()==""){
			searchTerm="all";
		}
		getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm);
	});
	$("#classname").change(function(){
		getSectionList($(this).val());
		locationId=$("#locationname").val();
		if($("#locationname").val()==""){
			locationId="all";
		}
		academicYear=$("#Acyearid").val();
		if($("#Acyearid").val()==""){
			academicYear="all";
		}
		classId=$("#classname").val();
		if($("#classname").val()==""){
			classId="all";
		}
		divisionId=$("#sectionid").val();
		if($("#sectionid").val()==""){
			divisionId="all";
		}
		searchTerm=$("#searchvalue").val();
		if($("#searchvalue").val()==""){
			searchTerm="all";
		}
		getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm);
	});
	$("#sectionid").change(function(){
		locationId=$("#locationname").val();
		if($("#locationname").val()==""){
			locationId="all";
		}
		academicYear=$("#Acyearid").val();
		if($("#Acyearid").val()==""){
			academicYear="all";
		}
		classId=$("#classname").val();
		if($("#classname").val()==""){
			classId="all";
		}
		divisionId=$("#sectionid").val();
		if($("#sectionid").val()==""){
			divisionId="all";
		}
		searchTerm=$("#searchvalue").val();
		if($("#searchvalue").val()==""){
			searchTerm="all";
		}
		getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm);
	});
	$("#search").click(function(){
		locationId=$("#locationname").val();
		if($("#locationname").val()==""){
			locationId="all";
		}
		academicYear=$("#Acyearid").val();
		if($("#Acyearid").val()==""){
			academicYear="all";
		}
		classId=$("#classname").val();
		if($("#classname").val()==""){
			classId="all";
		}
		divisionId=$("#sectionid").val();
		if($("#sectionid").val()==""){
			divisionId="all";
		}
		searchTerm=$("#searchvalue").val();
		if($("#searchvalue").val()==""){
			searchTerm="all";
		}
		getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm);
	});
	$("#searchvalue").keypress(function(e){
		if(e.keyCode=="13"){
		locationId=$("#locationname").val();
		if($("#locationname").val()==""){
			locationId="all";
		}
		academicYear=$("#Acyearid").val();
		if($("#Acyearid").val()==""){
			academicYear="all";
		}
		classId=$("#classname").val();
		if($("#classname").val()=="" || $("#classname").val()==undefined){
			classId="all";
		}
		divisionId=$("#sectionid").val();
		if($("#sectionid").val()==""){
			divisionId="all";
		}
		searchTerm=$("#searchvalue").val();
		if($("#searchvalue").val()==""){
			searchTerm="all";
		}
		getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm);
		}
	});
	
	$("#delete").click(function(){
		getDataArray=[];
		accYearArray=[];
		$("#dialog").dialog("open");
		$("#dialog").empty();
		$("#dialog").append("<p>Are You Sure to Delete?</p>");
		$(".select:checked").each(function(){
			getDataArray.push($(this).val());
			accYearArray.push($(this).closest("tr").attr("class").split(" ")[1]);
		});
	});
	$("#dialog").dialog({
	    autoOpen  : false,
	    modal     : true,
	    title     : "Delete Scholorship Student",
	    buttons   : {
	              'Yes' : function() {

	            	  var datalist = {'getDataArray':getDataArray.toString(),
	            			  			'accYearArray':accYearArray.toString()
	            	  };//create json data3
						$
								.ajax({
									type : "GET",
									url : "feecollection.html?method=deleteScholorDetails",
									data : datalist,
									async : false,

									success : function(
											response) {

										var result = $
												.parseJSON(response);

										if (result.status == "true") {
											$(".successmessagediv").show();
											$(".sucessmessage") .text("Deleting  Record Progressing...");
											
											$(".successmessagediv").delay(3000).slideUp("slow");
											setTimeout(function(){
												window.location.href = "feecollection.html?method=FeeScholorship";
												},2000);
											
										}
										else {
											$(".errormessagediv").show();
											$(".validateTips").text("Record Not Deleted");
											$(".errormessagediv").delay(3000).slideUp("slow");
										}

										
									}
									
								});
						$(this).dialog('close');

					},
	              'No' : function() {
	                 
	                  $(this).dialog('close');
	              }
	                }
	});
	$("#add").click(function(){
		$("#myDialog").dialog("open");
		
		
		
	});
	$("#myDialog").dialog({
	    autoOpen  : false,
	    modal     : true,
	    title     : "Add Fee Concession Student",
	    buttons   : {
	              'Yes' : function() {
	          

	            	 //create json data3
	            	  
	            	  if(($("#admissionNo").val()).length > 1){
	            	  	  if($("input[name='contype']:checked").val()=="partial"){
	            	  		term=[];
	            			feecode=[];
	            			consfeeamount=[];
	            		
	            			$("#termwiseconcession tbody tr").each(function(){
            					term.push($(this).find("td:first").attr("id"));
            					feecode.push($(this).find(".confee").attr("class").split(" ")[0]);
            					consfeeamount.push($(this).find(".termconsamount").val());
            				});
            						            			
	            	  		  
	            	  		 var datalist = {'admissionNo':$("#admissionNo").val(),
		            			  			'AcademicYearFor':$("#AcademicYearFor").val(),
		            			  			'classId':$("#hclassId").val(),
		            			  			'term':term.toString(),
		            			  			'feecode':feecode.toString(),
		            			  			'consfeeamount':consfeeamount.toString(),
		            			  			'conType':$("input[name='contype']:checked").val()
		            	  };
	            	  		  
						$.ajax({
									type : "GET",
									url : "feecollection.html?method=addScholorshipStudent",
									data : datalist,
									async : false,

									success : function(
											response) {

										var result = $
												.parseJSON(response);

										if (result.status =="true") {
											$(".successmessagediv").show();
											$(".sucessmessage") .text("Adding Record Progressing...");
											
											$(".successmessagediv").delay(3000).slideUp("slow");
											setTimeout(function(){
												window.location.href = "feecollection.html?method=FeeScholorship";
												},2000);
										}
										else {
											$(".errormessagediv").show();
											$(".validateTips").text("Alraedy Exist.");
											$(".errormessagediv").delay(3000).slideUp("slow");
										}

										

									}
									
								});
						
						
						
						
	            	  }
	            	  	  else if($("input[name='contype']:checked").val()=="equal"){

		            		            			
		            	  		  
		            	  		 var datalist = {'admissionNo':$("#admissionNo").val(),
			            			  			'AcademicYearFor':$("#AcademicYearFor").val(),
			            			  			'classId':$("#hclassId").val(),
			            			  			'term':'all',
			            			  			'feecode':'all',
			            			  			'consfeeamount':$("#scholorshipAmount").val(),
			            			  			'conType':$("input[name='contype']:checked").val()
			            	  };
		            	  		  
							$.ajax({
										type : "GET",
										url : "feecollection.html?method=addScholorshipStudentForEqual",
										data : datalist,
										async : false,

										success : function(
												response) {

											var result = $
													.parseJSON(response);

											if (result.status =="true") {
												$(".successmessagediv").show();
												$(".sucessmessage") .text("Adding Record Progressing...");
												
												$(".successmessagediv").delay(3000).slideUp("slow");
												setTimeout(function(){
													window.location.href = "feecollection.html?method=FeeScholorship";
													},2000);
											}
											else {
												$(".errormessagediv").show();
												$(".validateTips").text("Alraedy Exist.");
												$(".errormessagediv").delay(3000).slideUp("slow");
											}

											

										}
										
									});
							
							
							
							
		            	  
	            	  	  }	
	            	  	  
	            	  	  else if($("input[name='contype']:checked").val()=="full"){


		            			
	            	  		  
		            	  		 var datalist = {'admissionNo':$("#admissionNo").val(),
			            			  			'AcademicYearFor':$("#AcademicYearFor").val(),
			            			  			'classId':$("#hclassId").val(),
			            			  			'term':'all',
			            			  			'feecode':'all',
			            			  			'consfeeamount':'000',
			            			  			'conType':$("input[name='contype']:checked").val()
			            	  };
		            	  		  
							$.ajax({
										type : "GET",
										url : "feecollection.html?method=addScholorshipStudentForEqual",
										data : datalist,
										async : false,

										success : function(
												response) {

											var result = $
													.parseJSON(response);

											if (result.status =="true") {
												$(".successmessagediv").show();
												$(".sucessmessage") .text("Adding Record Progressing...");
												
												$(".successmessagediv").delay(3000).slideUp("slow");
												setTimeout(function(){
													window.location.href = "feecollection.html?method=FeeScholorship";
													},2000);
											}
											else {
												$(".errormessagediv").show();
												$(".validateTips").text("Alraedy Exist.");
												$(".errormessagediv").delay(3000).slideUp("slow");
											}

											

										}
										
									});
	            	  	  
	            	  	  }
	            	  	  else{
	            	  		$(".errormessagediv").show();
	            			$(".validateTips").text("Select concession Type !");
	            			$(".errormessagediv").delay(3000).slideUp("slow"); 
	            	  		  
	            	  	  }
						$(this).dialog('close');
						
						
						
	              }
	else{
		$(".errormessagediv").show();
		$(".validateTips").text("Enter Correct Admission No.");
		$(".errormessagediv").delay(3000).slideUp("slow");
	}
						

					},
	              'No' : function() {
	                 
	                  $(this).dialog('close');
	              }
	                }
	});
	
	$("#admissionNo").autocomplete({
		source : function(request, response) {
				
			$.ajax({

				url : "feecollection.html?method=studentSearchbyadmissionNo",
				data : {
					searchTerm : request.term,
					
				},
				async : false,
				success : function(data) {
					var result = $.parseJSON(data);
					response($.map(	result.jsonResponse,function(item) {
						return {
							label : item.admissionNo,
							value : item.admissionNo,
						}
					}));
				}
			});
		},
		select : function(event, ui) {

			var searchTerm = ui.item.value;

			studentDetails = {
					'searchTerm' : searchTerm
			};

			
			$("#admissionNo").val(ui.item.label);
			studentListbyAdmissionNo(ui.item.label,$("#AcademicYearFor").val())
			return false;
		}
	});
	$("#scholorshipPercentage").keypress(function(){
		if(Number($(this).val())<=100 && Number($(this).val()) > 0){
			$("#scholorshipPercentage").val($(this).val());
		}
		else{
			$("#scholorshipPercentage").val("");
		}
	});
	$("#scholorshipPercentage").change(function(){
		if(Number($(this).val())<=100 && Number($(this).val()) > 0){
			$("#scholorshipPercentage").val($(this).val());
		}
		else{
			$("#scholorshipPercentage").val("");
		}
	});
	
	$("input[name='contype']").change(function(){
		if($(this).val()=="equal"){
			$(".partialconcession").hide();
			if($("#hstudentId").val()!=undefined && $("#hstudentId").val()!=""){
			$(".equalconcession").show();
			}
			else{
				$(".errormessagediv").show();
				$(".validateTips").text("Admission Number Required ! ");
				$(".errormessagediv").delay(2000).fadeOut();
			}
		}
		else if($(this).val()=="partial"){
			$(".equalconcession").hide();
			if($("#hstudentId").val()!=undefined && $("#hstudentId").val()!=""){
				$(".partialconcession").show();
				getTermdetails($("#hclassId").val(),$("#AcademicYearFor").val(),$("#hspecialization").val());
			}
			else{
				$(".errormessagediv").show();
				$(".validateTips").text("Admission Number Required ! ");
				$(".errormessagediv").delay(2000).fadeOut();
			}
			
		
		}
		else{
			$(".equalconcession").hide();
			$(".partialconcession").hide();
		}
	});
	
	
});


function getClassList(){
	var locationid=$("#locationname").val();
	datalist={
			"locationid" : locationid
	},

	$.ajax({

		type : 'POST',
		url : "studentRegistration.html?method=getClassByLocation",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#classname').html("");

			$('#classname').append('<option value="All">' + "----------Select----------"	+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#classname').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
}


function getSectionList(classname){
	datalist={
			"classidVal" : classname,
			"locationId":$("#locationname").val()
	},
	
	$.ajax({
		
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#sectionid').html("");
			
			$('#sectionid').append('<option value="all">' + "ALL"	+ '</option>');
			
			for ( var j = 0; j < result.sectionList.length; j++) {

				$('#sectionid').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});
}



function pagination(list) {

	
	
	
	var show_per_page = list;
    var number_of_items = $('#allstudent tbody tr').length;
   
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
   
    $('#allstudent tbody tr').css('display', 'none');
    $('#allstudent tbody tr').slice(0, show_per_page).css('display', 'table-row');

	
	

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
    $('#allstudent tbody tr').css('display', 'none').slice(start_from, end_on).css('display', 'table-row');

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
function getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm){
	datalist={
			"locationId" : locationId,
			"academicYear" : academicYear,
			"classId" : classId,
			"divisionId" : divisionId,
			"searchTerm" : searchTerm
	},
	
	$.ajax({
		
		type : 'POST',
		url : "studentRegistration.html?method=studentListByJsForScholorship",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			$('#allstudent tbody').empty();
			if(result.studentdetailslist.length>0){
				for(var i=0;i<result.studentdetailslist.length;i++){
					$('#allstudent tbody').append("<tr class='"+result.studentdetailslist[i].studentId+" "+result.studentdetailslist[i].academicYearId+" "+result.studentdetailslist[i].locationId+"'>" +
							"<td><input type='checkbox' class='select' id='"+result.studentdetailslist[i].studentAdmissionNo+"' value='"+result.studentdetailslist[i].studentAdmissionNo+"' /></td>" +
							"<td>"+result.studentdetailslist[i].academicYear+"</td>" +
							"<td>"+result.studentdetailslist[i].studentAdmissionNo+"</td>" +
							"<td>"+result.studentdetailslist[i].studentFullName+"</td>" +
							"<td>"+result.studentdetailslist[i].classSectionId+"</td>" +
							"<td>"+result.studentdetailslist[i].concessionType+"</td>" +
							"<td><img src='"+result.studentdetailslist[i].image+"' width='40' height='40' /></td>" +
							"</tr>");
					
				}
				
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.studentdetailslist.length);
				pagination(100);
				$("#ShowPerPage").change(function(){
					pagination($(this).val());	
				});
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='7'>NO Records Found</td></tr>");
			}
			
		$("#selectAll").change(function(){
			$(".select").prop("checked",$(this).prop("checked"));
		});
		$(".select").change(function(){
			if($(".select:checked").length==$(".select").length){
				$("#selectAll").prop("checked",true);
			}
			else{
				$("#selectAll").prop("checked",false);
			}
		});
		}
	});
}
function studentListbyAdmissionNo(admissionNo,accyear){
$.ajax({
		
		type : 'POST',
		url : "studentRegistration.html?method=studentListbyAdmissionNo",
		data : {"admissionNo":admissionNo,
				"accyear":accyear
		},
		async : false,
		success : function(response) {
			var result=$.parseJSON(response);
			$("#hstudentId").val(result.stuList[0].studentId);
			$("#student").val(result.stuList[0].student);
			$("#class_section").val(result.stuList[0].class_section);
			$("#hclassId").val(result.stuList[0].classId);
			$("#hspecialization").val(result.stuList[0].specialization);
		
			$("input[value='equal']").attr("checked",false);
			$("input[value='partial']").attr("checked",false);
			$("input[value='full']").attr("checked",true);
			$(".equalconcession").hide();
			$(".partialconcession").hide();
		}
	});
}
function getTermdetails(classId,accyear,specialization){
$.ajax({
		
		type : 'POST',
		url : "studentRegistration.html?method=TermdeatilsForConcession",
		data : {"classId":classId,
				"accyear":accyear,
				"specialization":specialization
		},
		async : false,
		success : function(response) {
			var result=$.parseJSON(response);
			$('#termwiseconcession tbody').empty();
			for(var i=0;i<result.stuList.length;i++){
				$('#termwiseconcession tbody').append("<tr>" +
						"<td id='"+result.stuList[i].termcode+"'>"+result.stuList[i].term+"</td>" +
						"<td class='"+result.stuList[i].feecode+" confee'>"+result.stuList[i].termTuitionFeeAmount+"</td>" +
						"<td><input type='text' name='scholorshipAmount' class='termconsamount' value='0' /></td>" +
						"</tr>");
				
			}
			$(".termconsamount").keyup(function(){
				if(isNaN($(this).val())){
					$(this).val("0");
				}
				else if(Number($(this).val()) > Number($(this).closest("tr").find(".confee").text())){
					$(this).val($(this).closest("tr").find(".confee").text());
				}
			});
			$(".termconsamount").focus(function(){
				if($(this).val()=="0"){
					$(this).val("");
				}
				else if($(this).val()!="0" && $(this).val().length>0){
					$(this).val($(this).val());
				}
				else{
					$(this).val("0");
				}
			});
			$(".termconsamount").blur(function(){
				if($(this).val().length>0){
					$(this).val($(this).val());
				}
				else{
					$(this).val("0");
				}
			});
			
		}
	});
}

