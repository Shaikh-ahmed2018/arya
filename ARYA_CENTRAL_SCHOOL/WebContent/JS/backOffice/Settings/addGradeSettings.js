var rowHoliday=0;
var flag=false;
var saveFlag=true;
var errorMsg=null;
$(document).ready(function(){
	
	
	gradeSettingsList($("#hiddenaccyid"),$("#hiddenloc"));
	
	$(".grade").click(function(){
		$("#gradeOne").slideToggle();
	});	
	
	$("#addgrade").click(function(){
		
		$("#gradeName").val("");
		$("#comments").val("");
		$("#minmarks").val("");
		$("#maxmarks").val("");
		 $("#dialog").dialog("open");
		
	});
	
	$("#gradeName").keyup(function(){
		$(this).val($(this).val().toUpperCase());
	});
	$("#gradeName1").keyup(function(){
		$(this).val($(this).val().toUpperCase());
	});
	$("#comments").keyup(function(){
		$(this).val($(this).val().toUpperCase());
	});
	$("#comments1").keyup(function(){
		$(this).val($(this).val().toUpperCase());
	});
	
	$("#gradeName").change(function(){
		var gradename = $("#gradeName").val();
		$.ajax({
			
			type : "POST",
			url:"examCreationPath.html?method=checkduplicateGrade",
			data : {"gradename" :gradename,"accyear":$("#hiddenaccyid").val(),"loc":$("#hiddenloc").val()},
			async:false,
			success : function(data){
				var result = $.parseJSON(data);
				
				if(result.status == "true"){
					$(".errormessagediv").show();
					$("#gradeName").val("");
					document.getElementById("gradeName").style.border = "1px solid #AF2C2C";
					document.getElementById("gradeName").style.backgroundColor = "#FFF7F7";
					$(".validateTips").text("Grade Name Already configured for this Academic Year and Location");
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				}
			}
			
		});
	});
	
	 $("#minmarks").change(function(){
		 var minmarks = parseFloat($("#minmarks").val());
		 var maxmarks = parseFloat($("#maxmarks").val());
		 if(minmarks <= 0){
			 $(".errormessagediv").show();
				$("#minmarks").val("");
				document.getElementById("minmarks").style.border = "1px solid #AF2C2C";
				document.getElementById("minmarks").style.backgroundColor = "#FFF7F7";
				$(".validateTips").text("Min Marks Should be Greater than 0");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
		 }
		 if(minmarks !="" && maxmarks!=""){
			 if(minmarks > maxmarks){
					
				 $(".errormessagediv").show();
					$("#minmarks").val("");
					document.getElementById("minmarks").style.border = "1px solid #AF2C2C";
					document.getElementById("minmarks").style.backgroundColor = "#FFF7F7";
					$(".validateTips").text("Min Marks Should be Less than Max Marks");
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
			 }
		 }
	 });
	 
	 $("#minmarks1").change(function(){
		 var minmarks = parseFloat($("#minmarks1").val());
		 var maxmarks = parseFloat($("#maxmarks1").val());
		 if(minmarks <= 0){
			 $(".errormessagediv").show();
				$("#minmarks1").val("");
				document.getElementById("minmarks1").style.border = "1px solid #AF2C2C";
				document.getElementById("minmarks1").style.backgroundColor = "#FFF7F7";
				$(".validateTips").text("Min Marks Should be Greater than 0");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
		 }
		 if(minmarks !="" && maxmarks!=""){
			 if(minmarks > maxmarks){
					
				 $(".errormessagediv").show();
					$("#minmarks1").val("");
					document.getElementById("minmarks1").style.border = "1px solid #AF2C2C";
					document.getElementById("minmarks1").style.backgroundColor = "#FFF7F7";
					$(".validateTips").text("Min Marks Should be Less than Max Marks");
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
			 }
		 }
	 });
	 
	 $("#maxmarks1").change(function(){
		 var maxmarks = parseFloat($("#maxmarks1").val());
		 var minmarks = parseFloat($("#minmarks1").val());
		 if(maxmarks <= 0){
			 $(".errormessagediv").show();
				$("#maxmarks1").val("");
				document.getElementById("maxmarks1").style.border = "1px solid #AF2C2C";
				document.getElementById("maxmarks1").style.backgroundColor = "#FFF7F7";
				$(".validateTips").text("Max Marks Should be Greater than 0");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
		 }
		 
		 else if(minmarks !="" && maxmarks!=""){
			 if(minmarks > maxmarks){
					
				 $(".errormessagediv").show();
					$("#minmarks1").val("");
					document.getElementById("minmarks1").style.border = "1px solid #AF2C2C";
					document.getElementById("minmarks1").style.backgroundColor = "#FFF7F7";
					$(".validateTips").text("Min Marks Should be Less than Max Marks");
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
			 }
		 }
	 });

	 
	 
	 $("#maxmarks").change(function(){
		 var maxmarks = parseFloat($("#maxmarks").val());
		 var minmarks = parseFloat($("#minmarks").val());
		 if(maxmarks <= 0){
			 $(".errormessagediv").show();
				$("#maxmarks").val("");
				document.getElementById("maxmarks").style.border = "1px solid #AF2C2C";
				document.getElementById("maxmarks").style.backgroundColor = "#FFF7F7";
				$(".validateTips").text("Max Marks Should be Greater than 0");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
		 }
		 
		 else if(minmarks !="" && maxmarks!=""){
			 if(minmarks > maxmarks){
					
				 $(".errormessagediv").show();
					$("#minmarks").val("");
					document.getElementById("minmarks").style.border = "1px solid #AF2C2C";
					document.getElementById("minmarks").style.backgroundColor = "#FFF7F7";
					$(".validateTips").text("Min Marks Should be Less than Max Marks");
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
			 }
		 }
	 });
	 
	
	$("#dialog").dialog({
		
		 	autoOpen  : false,
		    modal     : true,
		    title     : "Add Grade Details",
		    buttons   : {
		
		    	"Save": {
		    			
		    		text: 'Save',
		    		
		    		 click: function() {
		    			 
		    			 var grade = $("#gradeName").val();
		    			 var comments = $("#comments").val();
		    			 var minmarks = $("#minmarks").val();
		    			 var maxmarks = $("#maxmarks").val();
		    			 var accyear = $("#hiddenaccyid").val();
		    			 var loc_id = $("#hiddenloc").val();
		    			 if(grade == "" || grade == null){
		    				 $(".errormessagediv").show();
		    					$(".validateTips").text("Field Required - Grade Name");
		    					document.getElementById("gradeName").style.border = "1px solid #AF2C2C";
		    					document.getElementById("gradeName").style.backgroundColor = "#FFF7F7";
		    					setTimeout(function() {
		    						$('.errormessagediv').fadeOut();
		    					}, 3000);
		    			 }
		    			 else if(comments == "" || comments == null){
		    				 $(".errormessagediv").show();
		    					$(".validateTips").text("Field Required - Comments");
		    					document.getElementById("comments").style.border = "1px solid #AF2C2C";
		    					document.getElementById("comments").style.backgroundColor = "#FFF7F7";
		    					setTimeout(function() {
		    						$('.errormessagediv').fadeOut();
		    					}, 3000);
		    			 }
		    			 else if(minmarks == "" || minmarks == null){
		    				 $(".errormessagediv").show();
		    					$(".validateTips").text("Field Required - Min Marks");
		    					document.getElementById("minmarks").style.border = "1px solid #AF2C2C";
		    					document.getElementById("minmarks").style.backgroundColor = "#FFF7F7";
		    					setTimeout(function() {
		    						$('.errormessagediv').fadeOut();
		    					}, 3000);
		    			 }
		    			 else if(maxmarks == "" || maxmarks == null){
		    				 $(".errormessagediv").show();
		    					$(".validateTips").text("Field Required - Min Marks");
		    					document.getElementById("maxmarks").style.border = "1px solid #AF2C2C";
		    					document.getElementById("maxmarks").style.backgroundColor = "#FFF7F7";
		    					setTimeout(function() {
		    						$('.errormessagediv').fadeOut();
		    					}, 3000);
		    			 }else{
		    			 
		    			 addGrade();
		    			 $(this).dialog('close');	}
		    		 }
		    	},
		    	'Close' : function() {
	                  $(this).dialog('close');
	                  $("#gradeName").val("");
	                  $("#comments").val("");
	                  $("#minmarks").val("");
	                  $("#maxmarks").val("");
	                  document.getElementById("gradeName").style.border = "1px solid #CCC";
					  document.getElementById("gradeName").style.backgroundColor = "#FFF";
					  document.getElementById("comments").style.border = "1px solid #CCC";
					  document.getElementById("comments").style.backgroundColor = "#FFF";
					  document.getElementById("minmarks").style.border = "1px solid #CCC";
					  document.getElementById("minmarks").style.backgroundColor = "#FFF";
					  document.getElementById("maxmarks").style.border = "1px solid #CCC";
					  document.getElementById("maxmarks").style.backgroundColor = "#FFF";
					  $(".errormessagediv").hide();
	            }
		 }
	});
	
	$(".aligning").click(function(){
		
		id=$(this).parent("td").parent("tr").find(".gradeid").attr("id");
		 $("#mydialog").dialog("open");
	});
	
	$(".editing").click(function(){
		editid=$(this).parent("td").parent("tr").find(".gradeid").attr("id");
		
		gradename = $(this).parent("td").parent("tr").find(".gradeid").text();
		comments = $(this).parent("td").parent("tr").find(".comments").text();
		min_marks =$(this).parent("td").parent("tr").find(".min_marks").text();
		max_marks =$(this).parent("td").parent("tr").find(".max_marks").text();
		
			 $("#gradeName1").val(gradename);
			 $("#comments1").val(comments);
			 $("#minmarks1").val(min_marks);
			 $("#maxmarks1").val(max_marks);
		
		 $("#mydialog1").dialog("open");
	});
	
	$("#mydialog1").dialog({
		
	 	autoOpen  : false,
	    modal     : true,
	    title     : "Modify Grade Details",
	    buttons   : {
	    	"Save": {
	    		text: 'Save',
	    		
	    			 click: function() {
	    				 
	    				 var grade = gradename;
	    				 var gradeName= $("#gradeName1").val();
	    				 var comments = $("#comments1").val();
	    				 var minmarks = $("#minmarks1").val();
	    				 var maxmarks = $("#maxmarks1").val();
	    				 
	    				 if(gradeName == "" || gradeName == null){
	    					 $(".errormessagediv").show();
		    					$(".validateTips").text("Field Required - Grade Name");
		    					document.getElementById("gradeName1").style.border = "1px solid #AF2C2C";
		    					document.getElementById("gradeName1").style.backgroundColor = "#FFF7F7";
		    					setTimeout(function() {
		    						$('.errormessagediv').fadeOut();
		    					}, 3000);
	    				 }else if(comments == "" || comments == null){
	    					 	$(".errormessagediv").show();
		    					$(".validateTips").text("Field Required - Comments");
		    					document.getElementById("comments1").style.border = "1px solid #AF2C2C";
		    					document.getElementById("comments1").style.backgroundColor = "#FFF7F7";
		    					setTimeout(function() {
		    						$('.errormessagediv').fadeOut();
		    					}, 3000);
	    				 }else if(minmarks == "" || minmarks == null){
	    					 $(".errormessagediv").show();
		    					$(".validateTips").text("Field Required - Min Marks");
		    					document.getElementById("minmarks1").style.border = "1px solid #AF2C2C";
		    					document.getElementById("minmarks1").style.backgroundColor = "#FFF7F7";
		    					setTimeout(function() {
		    						$('.errormessagediv').fadeOut();
		    					}, 3000);
	    				 }else if(maxmarks == "" || maxmarks == null){
	    					 	$(".errormessagediv").show();
		    					$(".validateTips").text("Field Required - Max Marks");
		    					document.getElementById("maxmarks1").style.border = "1px solid #AF2C2C";
		    					document.getElementById("maxmarks1").style.backgroundColor = "#FFF7F7";
		    					setTimeout(function() {
		    						$('.errormessagediv').fadeOut();
		    					}, 3000);
	    				 }else if(checkduplicate(gradeName,grade) == 1){
	    					 	$(".errormessagediv").show();
	    					 	$("#gradeName1").val("");
		    					$(".validateTips").text("Grade Name Already Configured with this Academic Year and Location");
		    					document.getElementById("gradeName1").style.border = "1px solid #AF2C2C";
		    					document.getElementById("gradeName1").style.backgroundColor = "#FFF7F7";
		    					setTimeout(function() {
		    						$('.errormessagediv').fadeOut();
		    					}, 3000);
	    				 }
	    				 else{
	    				 editgrade(editid);
	    				 $(this).dialog('close');	
	    				}
	    		 }
	    	},
	    	'Close' : function() {
                $(this).dialog('close');
          	  	  $(".errormessagediv").hide();
                  document.getElementById("gradeName1").style.border = "1px solid #CCC";
				  document.getElementById("gradeName1").style.backgroundColor = "#FFF";
				  document.getElementById("comments1").style.border = "1px solid #CCC";
				  document.getElementById("comments1").style.backgroundColor = "#FFF";
				  document.getElementById("minmarks1").style.border = "1px solid #CCC";
				  document.getElementById("minmarks1").style.backgroundColor = "#FFF";
				  document.getElementById("maxmarks1").style.border = "1px solid #CCC";
				  document.getElementById("maxmarks1").style.backgroundColor = "#FFF";
			
          }
	    }
	});
	
	
	$("#mydialog").dialog({
		
	 	autoOpen  : false,
	    maxWidth :100,
	    width: 100,
	    modal     : true,
	    title     : "Grade Details",
	    buttons   : {
	    	"Yes": {
	    		text: 'Yes',
	    			 click: function() {
	    			 deleteGrade(id);
	    			 $(this).dialog('close');	
	    		 }
	    	},
	    	'No' : function() {
                $(this).dialog('close');
          }
	    }
	});
	
});

function addGrade(){
	
	 var grade = $("#gradeName").val();
	 var comments = $("#comments").val();
	 var minmarks = $("#minmarks").val();
	 var maxmarks = $("#maxmarks").val();
	 var accyear = $("#hiddenaccyid").val();
	 var location = $("#hiddenloc").val();
	 datalist = {
				"grade" :grade,
				"comments" :comments,
				"minmarks" :minmarks,
				"maxmarks":maxmarks,
				"accyear":accyear,
				"location":location
		 },
		 $.ajax({
			 
			 type : "POST",
			 url : "examCreationPath.html?method=insertGradeSettings",
			 data : datalist,
			 async : false,
		     success: function(data) {
		    	 var result = $.parseJSON(data);
		    	 if(result.status == "success"){
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


function deleteGrade(pointer){
	
	var grade =pointer;
	var loaction = $("#hiddenloc").val();
	var accyear = $("#hiddenaccyid").val();
	datalist = {
			"gradeid" : grade,
			"loaction":loaction,
			"accyear":accyear
	}
	$.ajax({
		type : "POST",
		url : "examCreationPath.html?method=deleteGradeSettings",
		data : datalist,
		async:false,
		success : function(data){
			var result = $.parseJSON(data);
			if(result.status == "success"){
 				$(".successmessagediv").show();
				$(".validateTips").text("Record Deleting Progressing...");
				setTimeout(function() {
					$('.successmessagediv').fadeOut();
					//gradeSettingsList($("#hiddenaccyid"),$("#hiddenloc"));
					location.reload();
				}, 3000);
			}
			
		}
	});
}


function editgrade(editid){
	
	datalist = {
			"editid" : editid,
			"gradename" :$("#gradeName1").val(),
			"comments" : $("#comments1").val(),
			"min_marks" : $("#minmarks1").val(),
			"max_marks" : $("#maxmarks1").val(),
			"loaction" : $("#hiddenloc").val(),
			"accyear" : $("#hiddenaccyid").val()
	}
	$.ajax({
		
		type : "POST",
		url : "examCreationPath.html?method=editGradeSettings",
		data : datalist,
		 async:false,
		success : function(data){
			var result = $.parseJSON(data);
			if(result.status == "success"){
 				$(".successmessagediv").show();
				$(".validateTips").text("Record Updating Progressing...");
				setTimeout(function() {
					$('.successmessagediv').fadeOut();
					//gradeSettingsList($("#hiddenaccyid"),$("#hiddenloc"));
					location.reload();
				}, 3000);
			}
		}
	});
}

function checkduplicate(gradeName,grade) {
	
	var msg = 0;
	if(gradeName != grade){
		
		$.ajax({
			type : "POST",
			url:"examCreationPath.html?method=checkduplicateGrade",
			data : {"gradename" :gradeName,"accyear":$("#hiddenaccyid").val(),"loc":$("#hiddenloc").val()},
			async:false,
			success : function(data){
				var result = $.parseJSON(data);
			
				if(result.status == "true"){
					msg = 1;
				}else{
					msg = 0;
				}
			}
		});
	}else{
		msg = 0;
	}
	
	return msg;
}

function gradeSettingsList(pointer,pointer1){
	$.ajax({
		
		type : "POST",
		url:"examCreationPath.html?method=gradeSettingsList",
		data : {"accyear":pointer.val(),"location":pointer1.val()},
		async:false,
		success : function(data){
			var result = $.parseJSON(data);
			$("#markstable").empty();
			if(result.gradeSettingsList.length>0){
			$("#markstable").append("<table class='table' id='allstudent' width='100%'>"+"<thead><tr>"+
					"<th style='text-align: center;'>Grade Name</th>" +
					"<th style='text-align: center;'>Comments</th>" +
					"<th style='text-align: center;'>Min Marks</th>" +
					"<th style='text-align: center;'>Max Marks</th>"+
					"<th></th>"+
					"</tr></thead><tbody></tbody>" +
					"</table>"
					);
			}else{
				$("#markstable").append("<table class='table' id='allstudent' width='100%'>"+"<thead><tr>"+
						"<th style='text-align: center;'>Grade Name</th>" +
						"<th style='text-align: center;'>Comments</th>" +
						"<th style='text-align: center;'>Min Marks</th>" +
						"<th style='text-align: center;'>Max Marks</th>"+
						"</tr></thead><tbody></tbody>" +
						"</table>"
						);
			}
			if(result.gradeSettingsList.length>0){
			for(var i=0;i<result.gradeSettingsList.length;i++){
				
				$("#markstable #allstudent").append(
						"<tr>"+
						"<td class='gradeid' id='"+result.gradeSettingsList[i].gradeid+"'>"+result.gradeSettingsList[i].gradename+"</td>"+
						"<td class='comments'>"+result.gradeSettingsList[i].comments+"</td>"+
						"<td class='min_marks'>"+result.gradeSettingsList[i].min_marks+"</td>"
						+"<td class='max_marks'>"+result.gradeSettingsList[i].max_marks+"</td>"
						+"<td style='padding-bottom: 9px;padding-top: 9px;'><span class='buttons editing'>Edit</span><span class='buttons aligning' style='margin-left:10px;'>Delete</span></td>"
						+"</tr>"
				);
				 
			}
		}else{
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

