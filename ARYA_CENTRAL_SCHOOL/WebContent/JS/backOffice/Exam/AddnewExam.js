$(document).ready(function(){
	getMainClassList();
	getExamTypeList();
	getexamdetails($("#hiddenaccyear"),$("#hiddenloc"));
	
	$("#back").click(function(){
		window.location.href="examTimetablePath.html?method=getEaxmListYear";
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
	
    /* $("#examcode").change(function(){
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
 	});*/
	
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
					var classid = $("#classname").val();
					var examtype = $("#examtype").val();
					var isapplicable=$('input:radio[name=isapplicale]:checked').val();
					//var toClassId = $("#toclass").val();
					
					if(examtype == "" || examtype == null){
						$(".errormessagediv").show();
						document.getElementById("examtype").style.border = "1px solid #AF2C2C";
						document.getElementById("examtype").style.backgroundColor = "#FFF7F7";
						$(".validateTips").text("Field Required From Exam Type");
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
						return false
					}else if(classid == "" || classid == null){
						$(".errormessagediv").show();
						document.getElementById("classname").style.border = "1px solid #AF2C2C";
						document.getElementById("classname").style.backgroundColor = "#FFF7F7";
						$(".validateTips").text("Field Required From Class");
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
						return false
					}/*else if(toClassId == "" || toClassId == null){
						$(".errormessagediv").show();
	 					document.getElementById("toclass").style.border = "1px solid #AF2C2C";
	 					document.getElementById("toclass").style.backgroundColor = "#FFF7F7";
	 					$(".validateTips").text("Field Required To Class");
	 					setTimeout(function() {
	 						$('.errormessagediv').fadeOut();
	 					}, 3000);
	 				return false
					}*/else if(examcode == "" || examcode == null){
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
					else if(checkduplicateexamcode()==1){
						$(".errormessagediv").show();
						$("#examcode").val("");
						document.getElementById("examcode").style.border = "1px solid #AF2C2C";
						document.getElementById("examcode").style.backgroundColor = "#FFF7F7";
						$(".validateTips").text("The Exam Code is Already Configured For The Selected Class.");
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
						return false
					}
					else if(checkduplicatedate()==1){

						$(".errormessagediv").show();
						document.getElementById("startdate").style.border = "1px solid #AF2C2C";
						document.getElementById("startdate").style.backgroundColor = "#FFF7F7";
						document.getElementById("enddate").style.border = "1px solid #AF2C2C";
						document.getElementById("enddate").style.backgroundColor = "#FFF7F7";
						$(".validateTips").text("The Exam Selected Dates Are Already Configured For The Selected Class.");
						$("#startdate").val("");
						$("#enddate").val("");
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
								"location" : location,
								"fromclassid" : classid,
								"examtype" : examtype,
								"isapplicable" : isapplicable,
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
    $('.editing').click(function(){
    	
    	editid=$(this).parent("td").parent("tr").find(".examid").attr("id");

    	examtypeid = $(this).parent("td").parent("tr").find(".examtypeid").attr("id");
    	classid = $(this).parent("td").parent("tr").find(".classid").attr("id");
    	examCode = $(this).parent("td").parent("tr").find(".examcode").text();
    	examname = $(this).parent("td").parent("tr").find(".examid").text();
    	startdate =$(this).parent("td").parent("tr").find(".startdate").text();
    	enddate =$(this).parent("td").parent("tr").find(".enddate").text();
    	isapplicable =$(this).parent("td").parent("tr").find(".isapplicable").text();

    	oldstartdate = startdate;
    	oldenddate = enddate;
    	oldexamcode = examCode;
    	
    	$("#hiddenisapplicable").val(isapplicable);
    	$("#hiddenfromclassid").val(classid);
    	$("#hiddenexamtypeid").val(examtypeid);
    	
    	if($("#hiddenfromclassid").val() != null){
    		getHiddenClassList();
    		$("#fromclassid option[value='"+ $("#hiddenfromclassid").val() +"']").attr('selected', 'true');
    	}
    	if($("#hiddenexamtypeid").val() != null){
    		getHiddenExamList();
    		$("#examtype1 option[value='"+ $("#hiddenexamtypeid").val() +"']").attr('selected', 'true');
    	}
    	if($("#hiddenisapplicable").val().trim() != null){
    		if($("#hiddenisapplicable").val() == "N"){
    			$(".hisapplicableno").attr("checked",true);
    		}else{
    			$(".hisapplicableyes").attr("checked",true);
    		}
    		
    	}
    	
    	
    	
    	$("#examcode1").val(examCode);
    	$("#examname1").val(examname);
    	$("#startdate1").val(startdate);
    	$("#enddate1").val(enddate);
    	$("#dialog2").dialog("open");
    });
	$("#dialog2").dialog({
		
		autoOpen  : false,
		modal     : true,
		title     : "Modify Exam ",
		buttons   : {
			"Save": {
				text: 'Save',
				click: function() {
					var examcode=$("#examcode1").val();
					var examname=$("#examname1").val();
					var startdate=$("#startdate1").val();
					var enddate=$("#enddate1").val(); 
					var academiyear = $("#hiddenaccyear").val();
					var location = $("#hiddenloc").val();
					var fromclassid = $("#fromclassid").val();
					var examtype = $("#examtype1").val();
					var isapplicable=$('input:radio[name=hisapplicale]:checked').val();

					if(examtype == "" || examtype == null){
						$(".errormessagediv").show();
						document.getElementById("examtype").style.border = "1px solid #AF2C2C";
						document.getElementById("examtype").style.backgroundColor = "#FFF7F7";
						$(".validateTips").text("Field Required From Exam Type");
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
						return false
					}else if(fromclassid == "all" || examcode == null){
						$(".errormessagediv").show();
						document.getElementById("examcode1").style.border = "1px solid #AF2C2C";
						document.getElementById("examcode1").style.backgroundColor = "#FFF7F7";
						$(".validateTips").text("Field Required Exam Code");
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
						return false
					}else if(examcode == "" || examcode == null){
						$(".errormessagediv").show();
						document.getElementById("examcode1").style.border = "1px solid #AF2C2C";
						document.getElementById("examcode1").style.backgroundColor = "#FFF7F7";
						$(".validateTips").text("Field Required Exam Code");
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
						return false
					}else if(examname == "" || examname == null){
						$(".errormessagediv").show();
						document.getElementById("examname1").style.border = "1px solid #AF2C2C";
						document.getElementById("examname1").style.backgroundColor = "#FFF7F7";
						$(".validateTips").text("Field Required Exam Name");
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
						return false
					}else if(startdate == "" || startdate == null){
						$(".errormessagediv").show();
						document.getElementById("startdate1").style.border = "1px solid #AF2C2C";
						document.getElementById("startdate1").style.backgroundColor = "#FFF7F7";
						$(".validateTips").text("Field Required Start Date");
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
						return false
					}else if(enddate == "" || enddate == null){
						$(".errormessagediv").show();
						document.getElementById("enddate1").style.border = "1px solid #AF2C2C";
						document.getElementById("enddate1").style.backgroundColor = "#FFF7F7";
						$(".validateTips").text("Field Required End Date");
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
						return false
					}
					else if(checkduplicateexamedit(oldexamcode)==1){
						$(".errormessagediv").show();
						$("#examcode1").val("");
						document.getElementById("examcode1").style.border = "1px solid #AF2C2C";
						document.getElementById("examcode1").style.backgroundColor = "#FFF7F7";
						$(".validateTips").text("Exam Code is already exists for this Academic Year");
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
					}
					else if(checkduplicatedateedit(oldstartdate,oldenddate)==1){

						$(".errormessagediv").show();
						$("#startdate1").val("");
						$("#enddate1").val("");
						document.getElementById("startdate1").style.border = "1px solid #AF2C2C";
						document.getElementById("startdate1").style.backgroundColor = "#FFF7F7";
						document.getElementById("enddate1").style.border = "1px solid #AF2C2C";
						document.getElementById("enddate1").style.backgroundColor = "#FFF7F7";
						$(".validateTips").text("The Exam Selected Dates Are Already Configured For The Selected Class.");
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
						return false
					}
					else{
						editExam(editid);
						$(this).dialog('close');	
					}
				}
			},
			'No' : function() {
				$(this).dialog('close');
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
				"location" : $("#hiddenloc").val(),
				"fromclassid" : $("#fromclassid").val(),
				"examtype" : $("#examtype1").val(),
				"isapplicable" : $('input:radio[name=hisapplicale]:checked').val(),
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
	
	getClassList();
	
	/*$("#classname").change(function(){
 		var classId = $("#classname").val();
 		
 		$.ajax({
 			
 			type : "POST",
 			url:"examTimetablePath.html?method=getToClassDetails",
 			data : {"classId" :classId,"location":$("#hiddenloc").val()},
 			async:false,
 			success : function(data){
 				var result = $.parseJSON(data);
 				
 				$('#toclass').html("");

 				$('#toclass').append('<option value="all">' +"ALL"+ '</option>');

 				for ( var j = 0; j < result.ClassList.length; j++) {

 					$('#toclass').append('<option value="'

 							+ result.ClassList[j].classcode + '">'

 							+ result.ClassList[j].classname

 							+ '</option>');
 				}
 				
 			}
 			
 		});
 	});*/
	
	$("#examtype").change(function(){
		if($(this).val()==""){
			$(".isapplicableno").attr("checked",false);
			$(".isapplicableyes").attr("checked",false);
			/*$(".errormessagediv").show();
			document.getElementById("examtype").style.border = "1px solid #AF2C2C";
			document.getElementById("examtype").style.backgroundColor = "#FFF7F7";
			$(".validateTips").text("Field Required Exam Type");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false*/
		}else if($(this).val()=="EMT1"){
			$(".isapplicableno").attr("checked",true);
		}
		else{
			$(".isapplicableyes").attr("checked",true);
		}
	});
	$("input[name=isapplicale]").change(function(){
		if($("#examtype").val()==""){
			$(".isapplicableno").attr("checked",false);
			$(".isapplicableyes").attr("checked",false);
			
		}else if($("#examtype").val()=="EMT1"){
			$(".isapplicableno").attr("checked",true);
		}
		else{
			$(".isapplicableyes").attr("checked",true);
		}
	});
	$("#examtype1").change(function(){
		if($(this).val()==""){
			$(".hisapplicableno").attr("checked",false);
			$(".hisapplicableyes").attr("checked",false);
		}else if($(this).val()=="EMT1"){
			$(".hisapplicableno").attr("checked",true);
		}
		else{
			
			$(".hisapplicableyes").attr("checked",true);
		}
	});
	$("input[name=hisapplicale]").change(function(){
		if($("#examtype1").val()==""){
			$(".hisapplicableno").attr("checked",false);
			$(".hisapplicableyes").attr("checked",false);
		}else if($("#examtype1").val()=="EMT1"){
			$(".hisapplicableno").attr("checked",true);
		}
		else{
			$(".hisapplicableyes").attr("checked",true);
		}
	});

});


function getClassList(){
	var locationid=$("#hiddenloc").val();
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

			$('#classname').append('<option value="all">' +"ALL"+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#classname').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
}


function getHiddenClassList(){
	var locationid=$("#hiddenloc").val();
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

			$('#fromclassid').html("");

			$('#fromclassid').append('<option value="all">' +"ALL"+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#fromclassid').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
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
			"accyear" : $("#hiddenaccyear").val(),
			"classid" : $("#classname").val(),
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
	
		datalist = {
			 "startdate" : $("#startdate1").val(),
			  "enddate" : $("#enddate1").val(), 
			  "location" : $("#hiddenloc").val(),
			  "accyear" : $("#hiddenaccyear").val(),
			  "classid" : $("#fromclassid").val(),
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

function checkduplicateexamcode(){

	rs= 0;

	$.ajax({
		type : "POST",
		url:"examTimetablePath.html?method=checkduplicateExamcode",
		data : {"exmcode" :$("#examcode").val(),"accyear":$("#hiddenaccyear").val(),"location":$("#hiddenloc").val(),"classid":$("#classname").val()},
		async:false,
		success : function(data){
			var result = $.parseJSON(data);

			if(result.status == "true"){
				rs = 1;
			}
		}
	});
	return rs;
}

function checkduplicateexamedit(oldexamcode){
 		
		rs= 0;
 
		if(oldexamcode !=$("#examcode1").val()){
			$.ajax({
				type : "POST",
				url:"examTimetablePath.html?method=checkduplicateExamcode",
				data : {"exmcode" :$("#examcode1").val(),"accyear":$("#hiddenaccyear").val(),"location":$("#hiddenloc").val(),"classid":$("#fromclassid").val()},
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
				
				$("#markstable").empty();
				if(result.examsettings.length>0){
				$("#markstable").append("<table class='table' id='allstudent' width='100%'>"+"<tr>"+
						"<th>Exam Type</th>" +
						"<th>Class Name</th>" +
						"<th>Exam Code</th>" +
						"<th>Exam Name</th>" +
						"<th>Start Date</th>" +
						"<th>End Date</th>"+
						"<th>Is Appli.Periodic</th>"+
						"<th></th>"+
						"</tr>" +
						"</table>"
						);
				}else{

					$("#markstable").append("<table class='table' id='allstudent' width='100%'>"+"<tr>"+
							"<th>Exam Type</th>" +
							"<th>Class Name</th>" +
							"<th>Exam Code</th>" +
							"<th>Exam Name</th>" +
							"<th>Start Date</th>" +
							"<th>End Date</th>"+
							"<th>Is Appli.Periodic</th>"+
							"</tr>" +
							"</table>"
							);
					
				}
				if(result.examsettings.length>0){
				for(var i=0;i<result.examsettings.length;i++){
					
					$("#markstable #allstudent").append(
							"<tr>"+
							"<td class='examtypeid' id='"+result.examsettings[i].examtypeid+"'>"+result.examsettings[i].examtypename+"</td>"+
							"<td class='classid' id='"+result.examsettings[i].classid+"'>"+result.examsettings[i].classname+"</td>"+
							"<td class='examcode'>"+result.examsettings[i].examCode+"</td>"+
							"<td class='examid' id='"+result.examsettings[i].examid+"'>"+result.examsettings[i].examName+"</td>"+
							"<td  class='startdate'>"+result.examsettings[i].startDate+"</td>"
							+"<td class='enddate'>"+result.examsettings[i].endDate+"</td>"
							+"<td class='isapplicable'>"+result.examsettings[i].isapplicableperiodic+"</td>"
							+"<td style='text-align: center;'><span class='buttons editing'>Edit</span><span class='buttons aligning' style='margin-left:10px;'>Delete</span></td>"
							+"</tr>"
					);
				}
				}
				else{
					$('#allstudent tbody').append("<tr><td colspan='7'>No Records Found</td></tr>");
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

function getExamTypeList(){
	$.ajax({

		type : 'POST',
		url : "examTimetablePath.html?method=getExamTypeList",
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#examtype').html("");

			$('#examtype').append('<option value="">' +"-----Select-----"+ '</option>');

			for ( var j = 0; j < result.ExamList.length; j++) {

				$('#examtype').append('<option value="'

						+ result.ExamList[j].examtypeid + '">'

						+ result.ExamList[j].examtypename

						+ '</option>');
			}
		}
	});
}
function getHiddenExamList(){
	$.ajax({
		
		type : 'POST',
		url : "examTimetablePath.html?method=getExamTypeList",
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#examtype1').html("");
			
			$('#examtype1').append('<option value="">' +"-----Select-----"+ '</option>');
			
			for ( var j = 0; j < result.ExamList.length; j++) {
				
				$('#examtype1').append('<option value="'
						
						+ result.ExamList[j].examtypeid + '">'
						
						+ result.ExamList[j].examtypename
						
						+ '</option>');
			}
		}
	});
}

function getMainClassList(){
	var locationid=$("#hiddenloc").val();
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
			$('#mainclass').html("");
			$('#mainclass').append('<option value="all">' +"ALL"+ '</option>');
			for ( var j = 0; j < result.ClassList.length; j++) {
				$('#mainclass').append('<option value="' + result.ClassList[j].classcode + '">' + result.ClassList[j].classname + '</option>');
			}
		}
	});
}