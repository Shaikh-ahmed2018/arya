
$(document).ready(function() {
	$("#allstudent tbody").empty();
	$("#allstaffs tbody").empty();
	$("#allother tbody").empty();
	$("#accyear").val($("#hiddenaccyear").val());

	$("#locationname").change(function(){
		if($("#locationname").val()!=""){
			getClassList();
			//getOtherName();
			getSubscriberDetailsListPage();
		}
		
	});
	
	
	$(".class").show();
	$(".division").show();
	$(".allstudenttable").hide();
	$(".stafftable").hide();
	$(".stafdetail").hide();
	$(".othersTable").hide();
	$("#pagebanner").hide();

	
	$("input[name='requested_by']").change(function(){
		
		
		
		if($(this).val()=="studentwise"){
			$(".class").show();
			$(".division").show();
			$(".allstudenttable").show();
			$(".stafftable").hide();
			$(".stafdetail").hide();
			$(".othersTable").hide();
			getSubscriberDetailsListPage();
		}
		
		else if($(this).val()=="staffwise"){
			$(".stafdetail").show();
			$(".stafftable").show();
			$(".class").hide();
			$(".division").hide();
			$(".allstudenttable").hide();
			$(".other").hide();
			$(".othersTable").hide();
			getSubscriberDetailsListPage();
		}
		else if($(this).val()=="other"){
			$(".other").show();
			$(".stafdetail").hide();
			$(".stafftable").hide();
			$(".class").hide();
			$(".division").hide();
			$(".allstudenttable").hide();
			$(".othersTable").show();
			getSubscriberDetailsListPage();
		}
	
	});
		
		$("#excelDownload").click(function(){
			var accyear = $("#accyear option:selected").text().trim();
			window.location.href = 'LibraryMenu.html?method=getSubscriberDetailExcelReport&type=excel'+"&accyear="+accyear;
		});

		$("#pdfDownload").click(function(){
			var accyear = $("#accyear option:selected").text().trim();
			window.location.href = 'LibraryMenu.html?method=getSubscriberDetailExcelReport&type=pdf'+"&accyear="+accyear;
	
		});
		
		$("#print").click(function(){
			var accyear = $("#accyear option:selected").text().trim();
			$.ajax({
				type:"post",
				url: 'LibraryMenu.html?method=getSubscriberDetailExcelReport&type=print'+"&accyear="+accyear,
				success:function(data){
					
				}
			});
		});
		

	
	$("#classname").change(function(){
		var classname=$('#classname').val();
		getSectionList(classname);
		getSubscriberDetailsListPage();
	});
	$("#designation").change(function(){
		getSubscriberDetailsListPage();
	});
	$("#department").change(function(){
		getSubscriberDetailsListPage();
	});
	$("#otherName").change(function(){
		getSubscriberDetailsListPage();
	});
	
	$("#section").change(function(){
		getSubscriberDetailsListPage();
	});
});


function getOtherName(){
	var accyear =$("#accyear").val();
	var locId =$("#locationname").val();
	var classId =$("#classname").val();
	var sectionName =$("#section").val();
	var suscriberType=$("input[name='requested_by']:checked").val();
	var designation = $("#designation").val();
	var department = $("#department").val();

	datalist={
			"accyear":accyear,
			"locId":locId,
			"classId":classId,
			"sectionName":sectionName,
			"suscriberType":suscriberType,
			"designation" :designation,
			"department" :department,
			"otherName":$("#otherName").val(),
	};

	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getSubscriberDetailsListPage",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			
			
		
			$('#otherName').empty();
			$('#otherName').append('<option value="all">' +"ALL"+ '</option>');
			for ( var j = 0; j < result.listInfo.length; j++) {
				$('#otherName').append('<option value="'+ result.listInfo[j].otherId + '">'
						+ result.listInfo[j].otherUserName +  '</option>');
			}
		}
	});
}
	


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
				$('#classname').append('<option value="'+ result.ClassList[j].classcode + '">'+ result.ClassList[j].classname +  '</option>');
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
		success : function(response){
			var result = $.parseJSON(response);
			$('#section').html("");
			$('#section').append('<option value="">' + "ALL"+ '</option>');
			for ( var j = 0; j < result.sectionList.length; j++) {
				$('#section').append('<option value="' + result.sectionList[j].sectioncode+ '">'
						+ result.sectionList[j].sectionnaem+ '</option>');
			}
		}
	});
	getSubscriberDetailsListPage();
}

function getSubscriberDetailsListPage(){
	var accyear =$("#accyear").val();
	var locId =$("#locationname").val();
	var classId =$("#classname").val();
	var sectionName =$("#section").val();
	var suscriberType=$("input[name='requested_by']:checked").val();
	var designation = $("#designation").val();
	var department = $("#department").val();
	//var otherName = $("#otherName").val();

	datalist={
			"accyear":accyear,
			"locId":locId,
			"classId":classId,
			"sectionName":sectionName,
			"suscriberType":suscriberType,
			"designation" :designation,
			"department" :department,
			"otherName":$("#otherName").val(),
	};		
$.ajax({
			type:'POST',
			url :"LibraryMenu.html?method=getSubscriberDetailsListPage",
			data : datalist,
			async :false,
			success :function(data){
	
				var result = $.parseJSON(data);
				$("#allstudent tbody").empty();
				$("#allstaffs tbody").empty();
				$("#allother tbody").empty();
				$("#pagebanner").show();
				if(result.listInfo.length>0){
					$(".navbar-right").show();
					for(var i=0; i<result.listInfo.length; i++){
					if(suscriberType=="studentwise"){
						$(".allstudenttable").show();
						
						$("#allstudent tbody").append("<tr>" +
								"<td>"+result.listInfo[i].sno+"</td>"+
								"<td>"+result.listInfo[i].subscriberNo +"</td>"+
								"<td>"+result.listInfo[i].studentName+"</td>"+
								"<td>"+result.listInfo[i].adminssionNo+"</td>"+
								"<td>"+result.listInfo[i].rollNumber+"</td>"+
								"<td>"+result.listInfo[i].className+"</td>"+
								"<td>"+result.listInfo[i].sectionName+"</td>"+
								"<td>"+result.listInfo[i].status+"</td>"+
								"</tr>");
					}
					if(suscriberType=="staffwise"){
						$("#allstaffs tbody").append("<tr>" +
								"<td>"+result.listInfo[i].sno+"</td>"+
								"<td>"+result.listInfo[i].subscriberNo +"</td>"+
								"<td>"+result.listInfo[i].staffName+"</td>"+
								"<td>"+result.listInfo[i].department+"</td>"+
								"<td>"+result.listInfo[i].designation+"</td>"+
								"<td>"+result.listInfo[i].status+"</td>"+
								"</tr>");
					}
					else{
		
						$("#allother tbody").append("<tr>" +
							"<td>"+result.listInfo[i].sno+"</td>"+
							"<td>"+result.listInfo[i].subscriberNo +"</td>"+
							"<td>"+result.listInfo[i].otherUserName+"</td>"+
							"<td>"+result.listInfo[i].otherUserGender+"</td>"+
							"<td>"+result.listInfo[i].otherUserContact+"</td>"+
							"<td>"+result.listInfo[i].otherUserEmail+"</td>"+
							"<td>"+result.listInfo[i].otherUserAddr+"</td>"+
							"<td>"+result.listInfo[i].status+"</td>"+
							"</tr>");
					}
				}
				}
					else{
						$(".navbar-right").hide();
						$("#allstudent tbody").append("<tr><td colspan='8'><center> No Record Found</center></tr>");
						$("#allstaffs tbody").append("<tr><center><td colspan='7'><center> No Record Found</center></tr>");
						$("#allother tbody").append("<tr><td colspan='8'><center> No Record Found</center></tr>");
					}
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.listInfo.length);
				pagination(100);
			}
	});
}
