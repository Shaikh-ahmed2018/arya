 flag="anywhere";
$(document).ready(function(){

	var hacademicyear=$('#hacademicyaer').val();
	$("#Acyearid").val(hacademicyear);
	
	$("#classname").prop('disabled', 'disabled');
	$("#section").prop('disabled', 'disabled');
	
	$("#dates").datepicker({
		dateFormat : "dd-mm-yy",
		yearRange : 'c-65:c+65',
		maxDate : 0,
		changeMonth : "true",
		changeYear : "true",
	
	});

	 $("#locationName").change(function(){
		 
		 if($(this).val() == "all"){
			 $(".navbar-right").hide();
			 $("#allstudent").empty();
			 $(".numberOfItem").empty("");
			 $(".numberOfItem").append("No. of Records  0");
			 $("#scrollbars").hide();
			 return false;
		 }
		 else{
			 getAllOverDueListDetails();
				var option = $("input[name='requested_by']:checked").val();
				if(option == "Student"){
					getClassList();
				}else if(option == "staff"){
					getdeptlist();
					getdesgList(dept);
				}
		 }
		
	 });
		
	 
	 $("input[name='requested_by']").change(function(){
		 if($(this).val()=="all"){
			 
			 if( $("#locationName").val()== "all"){
				 $(".navbar-right").hide();
				 showErrormasg();
				 return false;
			 }else{
				 $(".class").show();
					$(".divission").show();
				 	$("#classname").prop('disabled', 'disabled');
					$("#section").prop('disabled', 'disabled');
					$("#department").hide();
					$(".designation").hide();
					getAllOverDueListDetails();
			 }
			
		}
		else if($(this).val()=="Student"){
			
			if( $("#locationName").val()== "all"){
				 $(".navbar-right").hide();
				 showErrormasg();
				 return false;
			 }else{
			
					getClassList();
					$("#classname").prop('disabled', false);
					$("#section").prop('disabled',false);
					$(".class").show();
					$(".divission").show();
					$("#department").hide();
					$(".designation").hide();
					getAllOverDueListDetails();
			
			 }
		}
		else if($(this).val()=="staff"){
			
			if( $("#locationName").val()== "all"){
				 $(".navbar-right").hide();
				 showErrormasg();
				 return false;
			 }else{
						getdeptlist();
						getdesgList(dept);
						$(".class").hide();
						$(".divission").hide();
						$("#department").show();
						$(".designation").show();
						getAllOverDueListDetails();
			  }
		}
		else if($(this).val()=="Others"){
			if( $("#locationName").val()== "all"){
				 $(".navbar-right").hide();
				 showErrormasg();
				 return false;
			 }else{
				$(".class").hide();
				$(".divission").hide();
				$("#department").hide();
				$(".designation").hide();
				getAllOverDueListDetails();
			 }
	}
			
	 });
	 
	 $("input[name='order_by']").change(function(){
		 if( $("#locationName").val()== "all"){
			 $(".navbar-right").hide();
			 showErrormasg();
			 return false;
		 }else{
		 getAllOverDueListDetails();
		 }
	 });
	 

$('#days').on('keyup keypress blur change', function(e) {
	if( $("#locationName").val()== "all"){
		 $(".navbar-right").hide();
		 showErrormasg();
		 return false;
	 }else{
	  getAllOverDueListDetails();
	 }
});

$("#dates").change(function(){
	if( $("#locationName").val()== "all"){
		 $(".navbar-right").hide();
		 showErrormasg();
		 return false;
	 }else{
	 getAllOverDueListDetails();
	 }
});
	 
	 $("#classname").change(function(){
			$("#searchvalue").val("");
			var locationid=$("#locationName").val();
			var classname=$("#classname").val();
			getAllOverDueListDetails();
			if($('#classname').val() == "all"){
				 $(".navbar-right").hide();
				$('#section').empty("");
				$('#section').append('<option value="all">' +"ALL"+ '</option>');
			}else{
				getSectionList(classname);
			}
		});
		
	 $("#section").change(function(){
		 getAllOverDueListDetails();
	});
	
	 $("#dept").change(function(){
		 getAllOverDueListDetails();
	 });
	 
	 $("#desg").change(function(){
		 getAllOverDueListDetails();
	 });
	
	$("#excelDownload").click(function(){
		window.location.href ='LibraryMenu.html?method=overdueReportExcel';
	});
	$("#pdfDownload").click(function(){
			window.location.href = 'LibraryMenu.html?method=overdueReportPDF';
	  });
	$("#print").click(function(){
		$.ajax({
			type: "POST",
			url:"LibraryMenu.html?method=printoverdueReport",
			success : function(data){
				
			}
		});
	});
});

function getClassList(){
	var locationid=$("#locationName").val();
	datalist={
			"locationid" : locationid
	};
	
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassByLocation",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#classname').empty("");
			$('#classname').append('<option value="all">' +"ALL"+ '</option>');
			var j=0;
			var len=result.ClassList.length;
			for ( j = 0; j < len; j++) {
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
			"locationId":$("#locationName").val()
	},
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getLibraryClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#section').empty("");
			$('#section').append('<option value="all">' + "ALL"+ '</option>');
			for ( var j = 0; j < result.sectionList.length; j++) {
				$('#section').append('<option value="' + result.sectionList[j].sectioncode+'">'+result.sectionList[j].sectionnaem+'</option>');
			}
		}
	});
}
 
 function getAllOverDueListDetails(){
		datalist={
				"locationid" : $("#locationName").val(),
				"Acyearid" :$("#Acyearid").val(),
				"classname" : $("#classname").val(),
				"section" :$("#section").val(),
				"dept" : $("#dept").val(),
				"desg" :$("#desg").val(),
				"select" :$("input[name='requested_by']:checked").val(),
	            "orderby" :$("input[name='order_by']:checked").val(),
	            "searchby" :$("input[name='seach_by']:checked").val(),
	            "searchterm" :$("#searchvalue").val(),
	            "days" : $("#days").val(),
	            "dates" : $("#dates").val()
		},
		$("#scrollbars").show();
		$("#mytable").empty();
		$("#mytable").append("<table class='table' id='allstudent'><thead><tr>" +
				"<th>Sl.No</th>" +
				"<th>Subscriber No.</th>" +
				"<th>Name</th>" +
				"<th>Subscriber Type</th>"+
				 "<th>Std/Dept/Phone</th>"+
			       "<th>Div/Desig/Email</th>"+
				   "<th>Accession No.</th>"+
					"<th>Title</th>"+
					"<th>Author</th>"+
					"<th>Issue Date</th>"+
					"<th>Due Date</th>"+
					"<th>Return Date</th>"+
					"<th>Status</th>"+
					"</tr></thead><tbody></tbody></table>");
		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=getAllOverDueListDetails",
			data : datalist,
			async : false,
			success : function(response) {
				var result = $.parseJSON(response);
			    var len=result.reservationList.length;
				if(len>0){
					 $(".navbar-right").show();
					 
				for(i=0;i<len;i++){
					$("#mytable #allstudent").append("<tr>"+
							"<td>"+(i+1)+"</td>"+
							"<td> "+result.reservationList[i].subscriberno+"</td>"+
							"<td>"+result.reservationList[i].subname+"</td>"+
							"<td>"+result.reservationList[i].usertype+"</td>"+
							"<td> "+result.reservationList[i].department+" </td>"+
							"<td> "+result.reservationList[i].desigantion+" </td>"+
							"<td>"+result.reservationList[i].accessionNo+"</td>"+
							"<td>"+result.reservationList[i].bookname+"</td>"+
							"<td>"+result.reservationList[i].bookauthor+"</td>"+
							"<td>"+result.reservationList[i].fromdate+"</td>"+
							"<td>"+result.reservationList[i].todate+"</td>"+
							"<td>"+result.reservationList[i].returneddate+"</td>"+
							"<td>"+result.reservationList[i].status+"</td>"+
							"</tr>"
					);
				}
			}
				else{
					$("#mytable #allstudent").append("<tr><td ColSpan='13'>No Records Found</td></tr>");
					 $(".navbar-right").hide();
				}
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+len);
			}
		});
			
	}
 
 function getdeptlist(){
		$.ajax({
		type : 'POST',
		url : 'teacherregistration.html?method=getDepartment',
		async : false,
		success : function(response) {
			var data = $.parseJSON(response);
			$("#dept").empty();
			$("#dept")
			.append('<option value="all">ALL</option>');
			var j=0;
			var len=data.DEPARTMENTLIST.length;
			for (j = 0; j < len; j++) {
				$("#dept")
				.append(
						'<option value="'
						+ data.DEPARTMENTLIST[j].depId
						+ '">'
						+ data.DEPARTMENTLIST[j].depName
						+ '</option>');
			}
			var hiddendept=$("#hiddendept").val();
			$("#dept option[value=" + hiddendept + "]").attr('selected', 'true');
		}
	  });
	}
	function getdesgList(dept){
	    $.ajax({
		type : 'POST',
		url : 'teacherregistration.html?method=getDesignation',
		async : false,
		success : function(response) {
			var data = $.parseJSON(response);
			$('#desg').empty();
			$("#desg").append('<option value="all">ALL</option>');
			var j=0;
			var len=data.DESIGNATIONLIST.length;
			for ( j = 0; j < len;j++) {
				$('#desg')
				.append(
						'<option value="'
						+ data.DESIGNATIONLIST[j].desgid
						+ '">'
						+ data.DESIGNATIONLIST[j].desgname
						+ '</option>');
			}
			var hiddendes=$("#hiddendes").val();
			$("#desg option[value=" + hiddendes + "]").attr('selected', 'true');
		}
	  });
	} 
 
 function showError(id){
		
		$(id).css({
			"border":"1px solid #AF2C2C",
			"background-color":"#FFF7F7"
		});
		$(".form-control").not(id).css({
			"border":"1px solid #ccc",
			"background-color":"#fff"
		});
		
	}

 function showErrormasg(){
	 $(".errormessagediv").show();
	 $(".validateTips").text("School Name - Required");
	 setTimeout(function(){
		 $(".errormessagediv").hide();
	 },2000);
 }