$(document).ready(function(){
	
	$("#Acyearid").val($("#hiddenaccyear").val());
	$("#stafftable").hide();
	getStudentList();

	$("input[name='requested_by']").change(function(){
		
		if($(this).val() == "Student"){
			$(".class").show();
			//$(".division").show();
			$(".staffdetail").hide();
			$("#mytable").show();
			$("#stafftable").hide();
			$("#Otherstable").hide();
			getStudentList();
		}
		
		else if($(this).val()=="staff"){
			$(".staffdetail").show();
			$("#stafftable").show();
			getTeacherList();
		    $(".class").hide();
			$("#mytable").hide();
			$("#Otherstable").hide();
			
		
		}
		else if($(this).val()=="Others"){
			$("#Otherstable").show();
			 getOthersList();
			$(".class").hide();
			$("#mytable").hide();
			$(".staffdetail").hide();
			$("#stafftable").hide();
		    $("#stafftable").hide();
		   
				
		}
	
	});
	
	$("#locationname").change(function(){
						

			if($("input[name='requested_by']:checked").val() == "Student"){
				
				getIssueStudentClassList();
				getClassList();
			}
			else if($("input[name='requested_by']:checked").val()=="staff"){
				getTeacherDeptList();
				getTeacherList();
				
			}
			else if($("input[name='requested_by']:checked").val()=="Others"){
			
				getOthersList();
			}
    
	});

	$("#Acyearid").change(function(){
		if($("input[name='requested_by']:checked").val() == "Student"){
			
			getIssueStudentClassList();
			getClassList();
			getStudentList();
		}
		else if($("input[name='requested_by']:checked").val()=="staff"){
			getTeacherDeptList();
			getTeacherList();
			
		}
		else if($("input[name='requested_by']:checked").val()=="Others"){
			
			getOthersList();
		}
		
		
	});
	
	$("#classname").change(function(){
		var classname=$('#classname').val();
		getSectionList(classname);
		getIssueStudentClassList();
		
	});
	
	$("#section").change(function(){
		
		getIssueStudentSectionList();
		
	});
	
	
	
	$("#allstudent tbody tr").click(function(){
		//$( this ).find(".studentid").attr("class");
		window.location.href="LibraryMenu.html?method=issues";
	});
	
	
	
	$("input[name='seach_by']").change(function(){
		if($(this).val()=="startwith"){
			flag="startwith";	
			 if($(this).val()== "student"){
			getIssueDetailsByStartwith();
			 }
			 else if($(this).val()== "staff"){
				 getIssueByStartwith();
			 }
			 else if($(this).val()== "Others"){
				 getOthersList();
				 getIssueotherByStartwith();
			 }
			
          }
	/*	else if ($(this).val()=="anywhere"){
			flag="anywhere";
			getIssueDetailsByAnyWhere();
		}
		else if($(this).val()=="endwith"){
			flag="endwith";
			getIssueDetailsByEndwith();
		}
		*/
		
	});
	
	
	
	$("#dept").change(function()
		{ 
	
 		/* getTeacherList();*/
		 getTeacherDeptList();
	});
	$("#desg").change(function()
		{
		/* getTeacherList();*/
		 getTeacherDesgList();
	});
	


});





function getClassList(){

	datalist={
			"locationid" : $("#locationname").val(),
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
			
			$('#section').html("");
			
			$('#section').append('<option value="">' + "ALL"+ '</option>');
			
			for ( var j = 0; j < result.sectionList.length; j++) {

				$('#section').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});
}
function getStudentList(){
	
	
	datalist={
			"locationid" : $("#locationname").val(),
			"Acyearid"   :$("#Acyearid").val(),
	},
	$.ajax({

		type : 'POST',
		url : "LibraryMenu.html?method=getStudentIssuedList",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$("#mytable").empty();
			$("#mytable").append("<table class='table' style='margin-right: 250px;width='100%';margin-top:50px' id='allstudent' ><thead><tr>" +
					"<th>Sl.No</th><th>SubscriberNo.</th>" +
					"<th>Name</th>" +
					"<th>Roll.No</th>"+
					   "<th>Standard</th>"+
				       "<th>Division</th>"+
					   "<th>BookName</th>"+
						"<th>Author</th>"+
						"<th>IssueDate</th>" +
						"<th>Status</th>"+
						"</tr></thead><tbody></tbody></table>");
			if(result.stulist.length>0){
			for(var i=0;i<result.stulist.length;i++){
				$("#allstudent tbody").append("<tr>" +
						"<td>"+result.stulist[i].slno+"</td>"+
						"<td>"+result.stulist[i].stuSubNo+"</td>"+
						"<td>"+result.stulist[i].stuname+"</td>"+
						"<td>"+result.stulist[i].rollno+"</td>"+
						"<td>"+result.stulist[i].standard+"</td>"+
						"<td>"+result.stulist[i].division+"</td>"+
						"<td>"+result.stulist[i].bookname+"</td>"+
						"<td>"+result.stulist[i].bookauthor+"</td>"+
						"<td>"+result.stulist[i].issuedate+"</td>"+
						"<td>"+result.stulist[i].status+"</td>"+
						"</tr>"
				
				);
				
			}
			}
			else{
				$("#allstudent tbody").append("<tr><td ColSpan='10'>No Records Found</td></tr>");
			}
			$("#allstudent tbody tr").click(function(){
				//$( this ).find(".studentid").attr("class");
				window.location.href="LibraryMenu.html?method=issues";
			});
			
		}
		
		});
}
	
	
	function  getIssueStudentClassList(){
		
		datalist={
				"locationid" : $("#locationname").val(),
				"Acyearid"   :$("#Acyearid").val(),
		        "classid"    :$("#classname").val(),
		
		},
		$.ajax({

			type : 'POST',
			url : "LibraryMenu.html?method=getIssueStudentClassList",
			data : datalist,
			async : false,
			success : function(response) {
				var result = $.parseJSON(response);
				$("#mytable").empty();
				$("#mytable").append("<table class='table' style='margin-right: 250px;width='100%';margin-top:50px' id='allstudent' ><thead><tr>" +
						"<th>Sl.No</th><th>SubscriberNo.</th>" +
						"<th>Name</th>" +
						"<th>Roll.No</th>"+
						   "<th>Standard</th>"+
					       "<th>Division</th>"+
						   "<th>BookName</th>"+
							"<th>Author</th>"+
							"<th>IssueDate</th>" +
							   "<th>Status</th>"+
							"</tr></thead><tbody></tbody></table>");
				if(result.clslist.length>0){
				for(var j=0;j<result.clslist.length;j++){
									
					$("#allstudent tbody").append("<tr>" +
							"<td>"+result.clslist[j].slno+"</td>"+
							"<td>"+result.clslist[j].stuSubNo+"</td>"+
							"<td>"+result.clslist[j].stuname+"</td>"+
							"<td>"+result.clslist[j].rollno+"</td>"+
							"<td>"+result.clslist[j].standard+"</td>"+
							"<td>"+result.clslist[j].division+"</td>"+
							"<td>"+result.clslist[j].bookname+"</td>"+
							"<td>"+result.clslist[j].bookauthor+"</td>"+
							"<td>"+result.clslist[j].issuedate+"</td>"+
							"<td>"+result.clslist[j].status+"</td>"+
							"</tr>"
					);
				
				}
				}
				else{
					$("#allstudent tbody").append("<tr><td ColSpan='10'>No Records Found</td></tr>");
				}
			}
		});
		
		
	}
	
	function getIssueStudentSectionList(){

		datalist={
				"locationid" : $("#locationname").val(),
				"Acyearid"   :$("#Acyearid").val(),
		        "classid"    :$("#classname").val(),
		        "sectionid"  :$("#section").val(),
		},
		$.ajax({

			type : "POST",
			url : "LibraryMenu.html?method=getIssueStudentSectionList",
			data : datalist,
			async : false,
			success : function(response) {
				
				var result = $.parseJSON(response);
			
				$("#mytable").empty();
				$("#mytable").append("<table class='table' style='margin-right: 250px;margin-top:50px;' width='100%' id='allstudent' ><thead><tr>" +
						"<th>Sl.No</th><th>SubscriberNo.</th>" +
						"<th>Name</th>" +
						"<th>Roll.No</th>"+
						   "<th>Standard</th>"+
					       "<th>Division</th>"+
						   "<th>BookName</th>"+
							"<th>Author</th>"+
							"<th>IssueDate</th>" +
							  "<th>Status</th>"+
							"</tr></thead><tbody></tbody></table>");
				if(result.Seclist.length>0){
				for(var z=0;z<result.Seclist.length;z++){
									
					$("#allstudent tbody").append("<tr>" +
							"<td>"+result.Seclist[z].slno+"</td>"+
							"<td>"+result.Seclist[z].stuSubNo+"</td>"+
							"<td>"+result.Seclist[z].stuname+"</td>"+
							"<td>"+result.Seclist[z].rollno+"</td>"+
							"<td>"+result.Seclist[z].standard+"</td>"+
							"<td>"+result.Seclist[z].division+"</td>"+
							"<td>"+result.Seclist[z].bookname+"</td>"+
							"<td>"+result.Seclist[z].bookauthor+"</td>"+
							"<td>"+result.Seclist[z].issuedate+"</td>"+	
							"<td>"+result.Seclist[z].status+"</td>"+
							"</tr>");
				}
			}
				else{
					$("#allstudent tbody").append("<tr><td ColSpan='10'>No Records Found</td></tr>");
				}
			}
		
		
		});
	
}
	/*
	
	function  getIssueDetailsByAnyWhere(){
		
		datalist={
				"locationid" : $("#locationname").val(),
				"Acyearid"   :$("#Acyearid").val(),
		        "classid"    :$("#classname").val(),
		        "sectionid"  :$("#section").val(),
		        "searchid"   :$("#searchvalue").val(),
		      
		},
		
	
		$.ajax({

			type : 'POST',
			url : "LibraryMenu.html?method=getIssueDetailsByAnyWhere",
			data : datalist,
			async : false,
			success : function(response) {
				
				var result = $.parseJSON(response);
				$("#allstudent tbody").empty();
				
				for(var i=0;i<result.studentData.length;i++){
									
					$("#allstudent tbody").append("<tr>" +
							"<td>"+result.studentData[i].slno+"</td>"+
							"<td>"+result.studentData[i].stuname+"</td>"+
							"<td>"+result.studentData[i].stuSubNo+"</td>"+
							"<td>"+result.studentData[i].rollno+"</td>"+
							"<td>"+result.studentData[i].standard+"</td>"+
							"<td>"+result.studentData[i].division+"</td>"+
							"</tr>"
					);
				
				}
				
				
			}
		});
		
		
	}*/
	
function  getIssueDetailsByStartwith(){
		
		datalist={
				"locationid" : $("#locationname").val(),
				"Acyearid"   :$("#Acyearid").val(),
		        "classid"    :$("#classname").val(),
		        "sectionid"  :$("#section").val(),
		        "searchid"   :$("#searchvalue").val(),
		        "selection" : $("input[name='seach_by']:checked").val()
		},
	
		$.ajax({

			type : 'POST',
			url : "LibraryMenu.html?method=getIssueDetailsByStartwith",
			data : datalist,
			async : false,
			success : function(response) {
				
				var result = $.parseJSON(response);
			
				$("#mytable").empty();
				$("#mytable").append("<table class='table' style='margin-right: 250px;width='100%';margin-top:50px' id='allstudent' ><thead><tr>" +
						"<th>Sl.No</th><th>SubscriberNo.</th>" +
						"<th>Name</th>" +
						"<th>Roll.No</th>"+
						   "<th>Standard</th>"+
					       "<th>Division</th>"+
						   "<th>BookName</th>"+
							"<th>Author</th>"+
							"<th>IssueDate</th>" +
					       "<th>Status</th>"+
							"</tr></thead><tbody></tbody></table>");
				
				if(result.studentData.length>0){
					
				
				for(var i=0;i<result.studentData.length;i++){
									
					$("#allstudent tbody").append("<tr>" +
							"<td>"+result.studentData[i].slno+"</td>"+
							"<td>"+result.studentData[i].stuSubNo+"</td>"+
							"<td>"+result.studentData[i].stuname+"</td>"+
							"<td>"+result.studentData[i].rollno+"</td>"+
							"<td>"+result.studentData[i].standard+"</td>"+
							"<td>"+result.studentData[i].division+"</td>"+
							"<td>"+result.studentData[i].bookname+"</td>"+
							"<td>"+result.studentData[i].bookauthor+"</td>"+
							"<td>"+result.studentData[i].issuedate+"</td>"+	
							"<td>"+result.studentData[i].status+"</td>"+
							"</tr>"
					);
				
				}
				}
				else{
					$("#allstudent tbody").append("<tr><td ColSpan='10'>No Records Found</td></tr>");
				}
			
				
			}
	
		});
}	
function getTeacherList(){
		datalist={
				"locationid" : $("#locationname").val(),
				"Acyearid"   :$("#Acyearid").val(),
				/*"dept" :$("#dept").val(),
				"desg":$("#desg").val(),*/
		},
		
		
		$.ajax({

			type : 'POST',
			url : "LibraryMenu.html?method=getTeacherList",
			data : datalist,
			async : false,
			success : function(response) {
				var result = $.parseJSON(response);
				
				$("#stafftable").empty();
				$("#stafftable").append("<table class='table' style='margin-right: 250px; width=100%;' id='allstudent'><thead><tr>" +
						"<th>Sl.No</th>" +
						"<th>SubscriberNo.</th>" +
						"<th>TeacherName</th>" +
						"<th>Department</th>"+
						   "<th>Designation</th>"+
						   "<th>BookName</th>"+
							"<th>Author</th>"+
							"<th>IssueDate</th>" +
							"<th>Status</th>"+
							"</tr></thead><tbody></tbody></table>");
				
				if(result.stfflist.length>0){
				for(var i=0;i<result.stfflist.length;i++){
				
							$("#allstudent tbody").append("<tr>" +
							"<td>"+result.stfflist[i].slno+"</td>"+
							"<td>"+result.stfflist[i].stuSubNo+"</td>"+
							"<td>"+result.stfflist[i].staffName+"</td>"+
							"<td>"+result.stfflist[i].staffDepartment+"</td>"+
							"<td>"+result.stfflist[i].staffDesignation+"</td>"+
							"<td>"+result.stfflist[i].bookname+"</td>"+
							"<td>"+result.stfflist[i].bookauthor+"</td>"+
							"<td>"+result.stfflist[i].issuedate+"</td>"+	
							"<td>"+result.stfflist[i].status+"</td>"+
							"</tr>"
							);
				}
				}
				else{
					$("#allstudent tbody").append("<tr><td ColSpan='10'>No Records Found</td></tr>");
				}
			}
		
		
			});
	}
	
	function  getTeacherDeptList(){
		datalist={
				"locationid" : $("#locationname").val(),
				"Acyearid"   :$("#Acyearid").val(),
				"deptId"     :$("#dept").val(),
		},
		$.ajax({

			type : 'POST',
			url : "LibraryMenu.html?method=getTeacherDeptList",
			data : datalist,
			async : false,
			success : function(response) {
				
				var result = $.parseJSON(response);
				
				
				$("#stafftable").empty();
				$("#stafftable").append("<table class='table' style='margin-right: 250px; width=100%;' id='allstudent'><thead><tr>" +
						"<th>Sl.No</th>" +
						"<th>SubscriberNo.</th>" +
						"<th>TeacherName</th>" +
						"<th>Department</th>"+
						   "<th>Designation</th>"+
						   "<th>BookName</th>"+
							"<th>Author</th>"+
							"<th>IssueDate</th>" +
							 "<th>Status</th>"+
							"</tr></thead><tbody></tbody></table>");
				
				if(result.stffdept.length>0){
				for(var i=0;i<result.stffdept.length;i++){
				
							$("#allstudent tbody").append("<tr>" +
							"<td>"+result.stffdept[i].slno+"</td>"+
							"<td>"+result.stffdept[i].stuSubNo+"</td>"+
							"<td>"+result.stffdept[i].staffName+"</td>"+
							"<td>"+result.stffdept[i].staffDepartment+"</td>"+
							"<td>"+result.stffdept[i].staffDesignation+"</td>"+
							"<td>"+result.stffdept[i].bookname+"</td>"+
							"<td>"+result.stffdept[i].bookauthor+"</td>"+
							"<td>"+result.stffdept[i].issuedate+"</td>"+	
							"<td>"+result.stffdept[i].status+"</td>"+
							"</tr>"
							);
				}
				}
				else
					{
					$("#allstudent tbody").append("<tr><td ColSpan='10'>No Records Found</td></tr>");
					}
			}
			});
	}
function  getTeacherDesgList(){
	datalist={
			"locationid" : $("#locationname").val(),
			"Acyearid"   :$("#Acyearid").val(),
			"deptId"     :$("#dept").val(),
			"desgid"     :$("#desg").val(),
	},
	$.ajax({

		type : 'POST',
		url : "LibraryMenu.html?method=getTeacherDesgList",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			
			$("#stafftable").empty();
			$("#stafftable").append("<table class='table' style='margin-right: 250px; width=100%;' id='allstudent'><thead><tr>" +
					"<th>Sl.No</th>" +
					"<th>SubscriberNo.</th>" +
					"<th>TeacherName</th>" +
					"<th>Department</th>"+
					   "<th>Designation</th>"+
					   "<th>BookName</th>"+
						"<th>Author</th>"+
						"<th>IssueDate</th>" +
						 "<th>Status</th>"+
						"</tr></thead><tbody></tbody></table>");
			
			if(result.stffdesg.length>0){
			for(var i=0;i<result.stffdesg.length;i++){
			
						$("#allstudent tbody").append("<tr>" +
						"<td>"+result.stffdesg[i].slno+"</td>"+
						"<td>"+result.stffdesg[i].stuSubNo+"</td>"+
						"<td>"+result.stffdesg[i].staffName+"</td>"+
						"<td>"+result.stffdesg[i].staffDepartment+"</td>"+
						"<td>"+result.stffdesg[i].staffDesignation+"</td>"+
						"<td>"+result.stffdesg[i].bookname+"</td>"+
						"<td>"+result.stffdesg[i].bookauthor+"</td>"+
						"<td>"+result.stffdesg[i].issuedate+"</td>"+
						"<td>"+result.stffdesg[i].status+"</td>"+
						"</tr>"
						);
			}
			}
			else{
				$("#allstudent tbody").append("<tr><td ColSpan='10'>No Records Found</td></tr>");
			}
		}
		});
	
}
 function  getIssueByStartwith(){
	 datalist={
				"locationid" : $("#locationname").val(),
				"Acyearid"   :$("#Acyearid").val(),
				"deptId"     :$("#dept").val(),
				"desgid"     :$("#desg").val(),
				 "selection" : $("input[name='seach_by']:checked").val(),
				 "searchvalue" : $("#searchvalue").val(),
		},
		
		$.ajax({

			type : 'POST',
			url : "LibraryMenu.html?method=getIssueByStartwith",
			data : datalist,
			async : false,
			success : function(response) {
				
				var result = $.parseJSON(response);
				
				
				$("#stafftable").empty();
				$("#stafftable").append("<table class='table' style='margin-right: 250px; width=100%;' id='allstudent'><thead><tr>" +
						"<th>Sl.No</th>" +
						"<th>SubscriberNo.</th>" +
						"<th>TeacherName</th>" +
						"<th>Department</th>"+
					    "<th>Designation</th>"+
					    "<th>BookName</th>"+
						"<th>Author</th>"+
						"<th>IssueDate</th>" +
						"<th>Status</th>"+
						"</tr></thead><tbody></tbody></table>");
				
				if(result.StafData.length>0){
				for(var i=0;i<result.StafData.length;i++){
				
							$("#allstudent tbody").append("<tr>" +
							"<td>"+result.StafData[i].slno+"</td>"+
							"<td>"+result.StafData[i].stuSubNo+"</td>"+
							"<td>"+result.StafData[i].staffName+"</td>"+
							"<td>"+result.StafData[i].staffDepartment+"</td>"+
							"<td>"+result.StafData[i].staffDesignation+"</td>"+
							"<td>"+result.StafData[i].bookname+"</td>"+
							"<td>"+result.StafData[i].bookauthor+"</td>"+
							"<td>"+result.StafData[i].issuedate+"</td>"+
							"<td>"+result.StafData[i].status+"</td>"+
							"</tr>"
							);
				}
				}
				else{
					$("#allstudent tbody").append("<tr><td ColSpan='10'>No Records Found</td></tr>");
				}
			}
			});
 }
 

 function getOthersList(){
		
	 datalist={
				"locationid" : $("#locationname").val(),
				"Acyearid"   :$("#Acyearid").val(),
				
	 },
	 alert( $("#locationname").val());
	 $.ajax({
		 type : 'POST',
			url : "LibraryMenu.html?method=getOthersList",
			data : datalist,
			async : false,
			success : function(response) {	
			
				$("#Otherstable").empty();
				$("#Otherstable").append("<table class='table' style='margin-right: 250px; width=100%;' id='allstudent'><thead><tr>" +
						"<th>Sl.No</th>" +
						"<th>SubscriberNo.</th>" +
						"<th>Name</th>" +
						"<th>ContactNO</th>"+
						   "<th>EmailId</th>"+
						   "<th>BookName</th>"+
							"<th>Author</th>"+
							"<th>IssueDate</th>" +
							"<th>Status</th>"+
							"</tr></thead><tbody></tbody></table>");
				var result = $.parseJSON(response);
				 if(result.OtherData.length>0){
				for(var i=0;i<result.OtherData.length;i++){
					
					$("#allstudent tbody").append("<tr>" +
					"<td>"+result.OtherData[i].slno+"</td>"+
					"<td>"+result.OtherData[i].stuSubNo+"</td>"+
					"<td>"+result.OtherData[i].othersName+"</td>"+
					"<td>"+result.OtherData[i].contactNo+"</td>"+
					"<td>"+result.OtherData[i].emailId+"</td>"+
					"<td>"+result.OtherData[i].bookname+"</td>"+
					"<td>"+result.OtherData[i].bookauthor+"</td>"+
					"<td>"+result.OtherData[i].issuedate+"</td>"+
					"<td>"+result.OtherData[i].status+"</td>"+
					"</tr>"
					);
			}
				 }
				 else{
					 $("#allstudent tbody").append("<tr><td ColSpan='10'>No Records Found</td></tr>");
				 }
			}
		 
	 });
 }
 function  getIssueotherByStartwith(){
	 
	
	 
	 datalist={
				"locationid" : $("#locationname").val(),
				"Acyearid"   :$("#Acyearid").val(),
				 "selection" : $("input[name='seach_by']:checked").val(),
				"searchvalue" : $("#searchvalue").val(),
	 },
	 $.ajax({
		 type : 'POST',
			url : "LibraryMenu.html?method=getIssueotherByStartwith",
			data : datalist,
			async : false,
			success : function(response) {	
				var result = $.parseJSON(response);
				$("#Otherstable").empty();
				$("#Otherstable").append("<table class='table' style='margin-right: 250px; width=100%;' id='allstudent'><thead><tr>" +
						"<th>Sl.No</th>" +
						"<th>SubscriberNo.</th>" +
						"<th>Name</th>" +
						"<th>ContactNO</th>"+
						   "<th>EmailId</th>"+
						   "<th>BookName</th>"+
							"<th>Author</th>"+
							"<th>IssueDate</th>" +
							   "<th>Status</th>"+
							"</tr></thead><tbody></tbody></table>");
			   if(result.OtherData.length>0){
				
				for(var i=0;i<result.OtherData.length;i++){

					$("#allstudent tbody").append("<tr>" +
							"<td>"+result.OtherData[i].slno+"</td>"+
							"<td>"+result.OtherData[i].stuSubNo+"</td>"+
							"<td>"+result.OtherData[i].othersName+"</td>"+
							"<td>"+result.OtherData[i].contactNo+"</td>"+
							"<td>"+result.OtherData[i].emailId+"</td>"+
							"<td>"+result.OtherData[i].bookname+"</td>"+
							"<td>"+result.OtherData[i].bookauthor+"</td>"+
							"<td>"+result.OtherData[i].issuedate+"</td>"+
							"<td>"+result.OtherData[i].status+"</td>"+
							"</tr>"
					);
				}
			   }
			   else{
					 $("#allstudent tbody").append("<tr><td ColSpan='10'>No Records Found</td></tr>");
				 }
			}
		 
	 });
 }










