$(document).ready(function(){
	
	
	$('#Generated').click(function(){
		
			$("#selectall").prop("checked",false);
			$(".select").prop("checked",false);
		
	});
	


	$("#selectall").change(function() {
		$(".select").prop('checked', $(this).prop("checked"));
	});
	
	$("#selectall1").change(function() {
		$(".select1").prop('checked', $(this).prop("checked"));
	});
	
	studentDemotedList();
	
	$('#download').click(function(){
		 if($('input[type=checkbox]:checked').length == 0) {
			  $(".errormessagediv").show();
			  $(".validateTips").text("Select Any One Record");
			  setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
			 return false;
		 }
	});
	
	$(".select").change(function() {
		
		if($(".select").length == $(".select:checked").length){
			$("#selectall").prop("checked",true);
		}
		else{
			$("#selectall").prop("checked",false);
		}
	});
	
$(".select1").change(function() {
		
		if($(".select1").length == $(".select1:checked").length){
			$("#selectall1").prop("checked",true);
		}
		else{
			$("#selectall1").prop("checked",false);
		}
	});
	
	$("#generatetc").click(function(){
		 list=[];
		 list1=[];
		 list2=[];
		 list3=[];
		 list4=[];
		var cnt = 0;
		
		$('#allstudent tbody input[type="checkbox"]:checked').map(function() {
			var schnm =$(this).attr("class").split(" ")[0];
			var acy=$(this).attr("class").split(" ")[1];
			var stuid =$(this).attr("class").split(" ")[2];
			var adm =$(this).attr("class").split(" ")[3];
			var cls =$(this).attr("class").split(" ")[4];
			list.push(schnm);
			list1.push(acy);
			list2.push(stuid);
			list3.push(adm);
			list4.push(cls);
			cnt++;
		});
		
		if (cnt == 0) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select any one record for TC Generation");
			return false;
		} else{
			
			$("#dialog").dialog("open");
			
			$("#dialog").empty();
			$("#dialog").append('<label for="">School/Board Annual Examination :</label>');
			
			$("#dialog").append("<input type='text' class='examdetails' id='examdetails' style='float:right' required/><br>");
			
			$("#dialog").append('<label for="">Result :</label>');
			$("#dialog").append("<input type='text' id='result' name='result' class='result' required style='float:right' /><br>");
			
			$("#dialog").append('<label for="">Application Date :</label>');
			$("#dialog").append("<input type='text' class='appdate' id='appdate' style='float:right' readonly / ><br>");
			
			
			$("#dialog").append('<label for="">Last Attendance Date :</label>');
			$("#dialog").append("<input type='text' class='ladate' id='ladate' style='float:right' readonly / ><br>");
			
			$("#dialog").append('<label for="">Compulsory Subject :</label>');
			$("#dialog").append("<input type='text' class='csub' id='csub' style='float:right'/ ><br>");
			
			$("#dialog").append('<label for="">Elective Subject :</label>');
			$("#dialog").append("<input type='text' class='esub' id='esub' style='float:right'/ ><br>");
			
			$("#dialog").append('<label for="">Reason for leaving :</label>');
			$("#dialog").append("<input type='text' class='reason' id='reason' style='float:right'/ ><br>");
			
			$("#dialog").append('<label for="">Admission Class :</label>');
			$("#dialog").append("<input type='text' class='admclass' id='admclass' style='float:right'/><br>");
			
			$("#dialog").append('<label for="">Any other Remarks :</label>');
			$("#dialog").append("<input type='text' class='remarks' id='remarks' style='float:right'/><br>");
			
			$("#appdate").datepicker({

				dateFormat : "dd-mm-yy",
				yearRange : 'c-65:c+65',
				
				changeMonth : "true",
				changeYear : "true",
				
				onClose : function(selectedDate) {
					if ((selectedDate != "") && (selectedDate != undefined)) {
						var date2 = $('#appdate').datepicker('getDate');
						date2.setDate(date2.getDate() + 1);
						
					}
				}
			
			});
			$("#ladate").datepicker({

				dateFormat : "dd-mm-yy",
				yearRange : 'c-65:c+65',
				
				changeMonth : "true",
				changeYear : "true",
				
				onClose : function(selectedDate) {
					if ((selectedDate != "") && (selectedDate != undefined)) {
						var date2 = $('#ladate').datepicker('getDate');
						date2.setDate(date2.getDate() + 1);
						
					}
				}
			
			});
			
		}
		
	});
	
	$("#dialog").dialog({
		autoOpen: false,
		modal: true,
		
	        width     : 600,
	        height    : 300,
		title:'Details for TC Generation',
		buttons : {
			"YES" : function(){
				var examdetails = $("#examdetails").val();
				var reason = $("#reason").val();
				var remarks = $("#remarks").val();
				var result = $("#result").val();
				var appdate=$("#appdate").val();
				var ladate=$("#ladate").val();
				var csub=$("#csub").val();
				var esub=$("#esub").val();
				var admclass=$("#admclass").val();
				if(examdetails == null || examdetails == undefined || examdetails ==""){
					$(".errormessagediv").show();
					$(".validateTips").text("Please Enter The School/Board Annual Examination");
				}
				else if(appdate == null || appdate == undefined || appdate ==""){
					$(".errormessagediv").show();
					$(".validateTips").text("Select Application Date");
				}
				else if(ladate == null || ladate == undefined || ladate ==""){
					$(".errormessagediv").show();
					$(".validateTips").text("Select Last Attendance Date");
				}
				else if(csub == null || csub == undefined || csub ==""){
					$(".errormessagediv").show();
					$(".validateTips").text("Fill Compulsory Subject");
				}
				else if(esub == null || esub == undefined || esub ==""){
					$(".errormessagediv").show();
					$(".validateTips").text("Fill Elective Subject");
				}
				else{
					generateTranferCertificate(list,list1,list2,list3,list4,examdetails,reason,remarks,result,appdate,ladate,csub,esub,admclass);
				}
			},
			"No" : function(){
				$(this).dialog("close");
			}
			
		}
	});
	var hacademicyear=$('#hacademicyaer').val();
	$("#Acyearid option[value="+hacademicyear+"]").attr('selected', 'true');

	
	$("#Generated").click(function(){
		 $("#generatetc").hide();
		 $("#download").show();
		 $("#TCCancel").show();
		 $("#course").show();
	});
	
	$("#notGenerated").click(function(){
		 $("#download").hide();
		 $("#TCCancel").hide();
		 $("#course").hide();
		 $("#generatetc").show();
	});
	
	$("#resetbtn").on("click", function (e) {
		$("#locationname,#classname,#sectionid").val("all");
		$("#Acyearid").val($('#hacademicyaer').val());
		$("#sortingby").val("Alphabetical");
		
		orderby=$("input[name='sorting1']:checked").val();
		$("#Male").hide();	
		$("p3").hide();
        $("#Female").hide();
        $("p4").hide();
        
		 $("#ASC").show();
		 $("p1").show();
		 $("#DESC").show();
		 $("p2").show();  
		
		$("#searchvalue").val("");
		getClassList();
		getSectionList();
		searchList();
	});
	
	$("#resetbtn1").on("click", function (e) {
		$("#locationname1,#classname1,#sectionid1").val("all");
		$("#Acyearid").val($('#hacademicyaer').val());
		$("#sortingby1").val("Alphabetical");
		
		orderby=$("input[name='sorting3']:checked").val();
		$("#Male").hide();	
		$("p3").hide();
        $("#Female").hide();
        $("p4").hide();
        
		 $("#ASC").show();
		 $("p1").show();
		 $("#DESC").show();
		 $("p2").show();  
		
		$("#searchvalue").val("");
		getClassList1();
		getSectionList1();
		searchList();
	});
	
	$("#search").click(function(){
		searchList();
	});	
	
	$("#search1").click(function(){
		studentDemotedListSearch();
	});	
	
	
	
	
	$("#Acyearid").change(function(){
		var classname=$("#classname").val();
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var sectionid=$("#sectionid").val();
		var sortingby=$("#sortingby").val();
		var orderby=$("input[name='sorting']:checked").val();
		if(classname=='all'){
			
		}
		else{
			getStudentListBySection(locationid,accyear,classname,sectionid,sortingby,orderby);
		}
		
	
	});
	
	$("#locationname").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		var sortingby=$("#sortingby").val();
		var orderby=$("input[name='sorting']:checked").val();
		getClassList();
		getSectionList();
		
if(classname=='all'){
			
		}
		else{
			getStudentListBySection(locationid,accyear,classname,sectionid,sortingby,orderby);
		}
		
	});
	$("#locationname1").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname1").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname1").val();
		var sectionid=$("#sectionid1").val();
		var sortingby=$("#sortingby1").val();
		var orderby=$("input[name='sorting2']:checked").val();
		
		if(sortingby == "Gender"){
			orderby=$("input[name='sorting3']:checked").val();
		}
		
		getClassList1();
		getSectionList1();
		
if(classname=='all'){
			
		}
		else{
			studentDemotedListFilter(locationid,accyear,classname,sectionid,sortingby,orderby);
		}
		
		
	});
	
	$("#classname").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		var sortingby=$("#sortingby").val();
		var orderby=$("input[name='sorting']:checked").val();
		getSectionList(classname);
		getStudentListBySection(locationid,accyear,classname,sectionid,sortingby,orderby);
	});
	
	$("#classname1").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname1").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname1").val();
		var sectionid=$("#sectionid1").val();
		var sortingby=$("#sortingby1").val();
		var orderby=$("input[name='sorting2']:checked").val();
		
		if(sortingby == "Gender"){
			orderby=$("input[name='sorting3']:checked").val();
		}
		
		getSectionList1(classname);
		studentDemotedListFilter(locationid,accyear,classname,sectionid,sortingby,orderby);
	});
	
	$("#sectionid").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		var sortingby=$("#sortingby").val();
		var orderby=$("input[name='sorting']:checked").val();
		
		if(sortingby == "Gender"){
			orderby=$("input[name='sorting1']:checked").val();
		}
		
		getStudentListBySection(locationid,accyear,classname,sectionid,sortingby,orderby);
	});
	
	$("#sectionid1").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname1").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname1").val();
		var sectionid=$("#sectionid1").val();
		var sortingby=$("#sortingby1").val();
		var orderby=$("input[name='sorting2']:checked").val();
		
		if(sortingby == "Gender"){
			orderby=$("input[name='sorting3']:checked").val();
		}

		studentDemotedListFilter(locationid,accyear,classname,sectionid,sortingby,orderby);
	});
	
	$("#sortingby").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		var sortingby=$("#sortingby").val();
		var orderby=$("input[name='sorting']:checked").val();
		
		
		if($("#sortingby").val()=="Gender"){
			orderby=$("input[name='sorting1']:checked").val();
			 $("#ASC").hide();
			 $("p1").hide();
			 $("#DESC").hide();
			 $("p2").hide();
			 
			 	$("#Female").show();
		        $("p3").show();
		        $("#Male").show();
		        $("p4").show();
			}else{	
				orderby=$("input[name='sorting1']:checked").val();
				$("#Male").hide();	
				$("p3").hide();
		        $("#Female").hide();
		        $("p4").hide();
		        
				 $("#ASC").show();
				 $("p1").show();
				 $("#DESC").show();
				 $("p2").show();   
		}
		
		getStudentListBySection(locationid,accyear,classname,sectionid,sortingby,orderby);
	});
	
	$("#sortingby1").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname1").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname1").val();
		var sectionid=$("#sectionid1").val();
		var sortingby=$("#sortingby1").val();
		var orderby=$("input[name='sorting2']:checked").val();
		
		if($("#sortingby1").val()=="Alphabetical"){
			orderby=$("input[name='sorting3']:checked").val();
			$("#Male1").hide();	
			$("p7").hide();
	        $("#Female1").hide();
	        $("p8").hide();
	        
	        $("#ASC1").show();
		    $("p5").show();
		    $("#DESC1").show();
		    $("p6").show();   
			
			}else{	
				orderby=$("input[name='sorting3']:checked").val();
				$("#Male1").hide();	
				$("p7").hide();
		        $("#Female1").hide();
		        $("p8").hide();
		        
			    $("#ASC1").show();
			    $("p5").show();
			    $("#DESC1").show();
			    $("p6").show();   
		} 
		
		if($("#sortingby1").val()=="Admission"){
			orderby=$("input[name='sorting3']:checked").val();
			$("#Male1").hide();	
			$("p7").hide();
	        $("#Female1").hide();
	        $("p8").hide();
	        
	        $("#ASC1").show();
		    $("p5").show();
		    $("#DESC1").show();
		    $("p6").show();   
			}else{	
				orderby=$("input[name='sorting3']:checked").val();
				$("#Male1").hide();	
				$("p7").hide();
		        $("#Female1").hide();
		        $("p8").hide();
		        
		        $("#ASC1").show();
			    $("p5").show();
			    $("#DESC1").show();
			    $("p6").show();   
		} 
		
		
		if($("#sortingby1").val()=="Gender"){
			orderby=$("input[name='sorting3']:checked").val();
			 $("#ASC1").hide();
			 $("p5").hide();
			 $("#DESC1").hide();
			 $("p6").hide();
			 
			 	$("#Female1").show();
		        $("p7").show();
		        $("#Male1").show();
		        $("p8").show();
			}else{	
				orderby=$("input[name='sorting3']:checked").val();
				$("#Male1").hide();	
				$("p7").hide();
		        $("#Female1").hide();
		        $("p8").hide();
		        
			    $("#ASC1").show();
			    $("p5").show();
			    $("#DESC1").show();
			    $("p6").show();   
		} 
		
		studentDemotedListFilter(locationid,accyear,classname,sectionid,sortingby,orderby);
	});
	

$("input[name=sorting2]:radio,input[name=sorting3]:radio").click(function (){
	alert()
	$("#searchvalue1").val("");
	
	var accyear=$("#Acyearid").val();

	var locationid1=$("#locationname1").val();
	var classname1=$("#classname1").val();
	var sectionid1=$("#sectionid1").val();
	var sortingby1=$("#sortingby1").val();
	
	
	alert(locationid1)
	alert(classname1)
	alert(sectionid1)
	alert(sortingby1)
	
	
	if($("#sortingby1").val()=="Gender"){
		
		orderby1=$("input[name='sorting3']:checked").val();
		alert("vvvv"+orderby1)
		studentDemotedListFilter(locationid1,accyear,classname1,sectionid1,sortingby1,orderby1);
	}else if($("#sortingby1").val()=="Alphabetical" || $("#sortingby1").val()=="Admission"){
		alert("ssss"+sortingby1)
		var orderby1=$("input[name='sorting2']:checked").val();
		alert("ssss"+orderby1)
		studentDemotedListFilter(locationid1,accyear,classname1,sectionid1,sortingby1,orderby1);
	}
});

	
$("input[name=sorting]:radio").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		var sortingby=$("#sortingby").val();
		var orderby=$("input[name='sorting']:checked").val();
		
		if($("#sortingby").val()=="Gender"){
			orderby=$("input[name='sorting1']:checked").val();
		}
		
		var locationid1=$("#locationname1").val();
		var classname1=$("#classname1").val();
		var sectionid1=$("#sectionid1").val();
		var sortingby1=$("#sortingby1").val();
		
		
		if($("#sortingby1").val()=="Gender"){
			orderby1=$("input[name='sorting3']:checked").val();
			alert(orderby1)
			studentDemotedListFilter(locationid1,accyear,classname1,sectionid1,sortingby1,orderby1);
		}else{
			var orderby1=$("input[name='sorting3']:checked").val();
			studentDemotedListFilter(locationid1,accyear,classname1,sectionid1,sortingby1,orderby1);
		}
		
		getStudentListBySection(locationid,accyear,classname,sectionid,sortingby,orderby);
	});
	pagination(100);
});

function changeAccYear(){
	var locationId = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=searchAllAcademicYearDetails",
		data : {
			"locationId" :locationId,
			"accyear" :accyear,				
		},
		async : false,
		
		success : function(response) {
			 
			var result = $.parseJSON(response);
				$("#allstudent tbody").empty();
				if(result.SearchList.length>0){
				for(var i=0;i<result.SearchList.length;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td class='"+result.SearchList[i].studentId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].locationId+" "+"studentid"+"'>"+result.SearchList[i].sno+"</td>" 
						+"<td> "+result.SearchList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.SearchList[i].studentFullName+"</td>"
						+"<td> "+result.SearchList[i].studentrollno+" </td>"
						+"<td> "+result.SearchList[i].classname+" </td>"
						+"<td> "+result.SearchList[i].sectionnaem+" </td>"
						+"</tr>");
				}
				}
				else{
					$("#allstudent tbody").append("<tr><td colspan='5'>NO Records Found</td></tr>");
				}
				$(".select").change(function() {
					
					if($(".select").length == $(".select:checked").length){
						$("#selectall").prop("checked",true);
					}
					else{
						$("#selectall").prop("checked",false);
					}
				});
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.SearchList.length);
				
			
		}
	});
}
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

function getClassList1(){
	var locationid=$("#locationname1").val();
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

			$('#classname1').html("");

			$('#classname1').append('<option value="all">' +"ALL"+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#classname1').append('<option value="'

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

function getSectionList1(classname){
	datalist={
			"classidVal" : classname,
			"locationId":$("#locationname1").val()
	},
	
	$.ajax({
		
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#sectionid1').html("");
			
			$('#sectionid1').append('<option value="all">' + "ALL"	+ '</option>');
			
			for ( var j = 0; j < result.sectionList.length; j++) {

				$('#sectionid1').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});
}

function getStudentList(locationid,accyear,classname){
	
	datalist = {
			
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			
		}, $.ajax({
			type : 'POST',
			url : "adminMenu.html?method=getStudentDetailsLByFilter",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					if(result.getClassWiseList.length>0){
					for(var i=0;i<result.getClassWiseList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td class='"+result.getClassWiseList[i].studentId+" "+result.getClassWiseList[i].academicYearId+" "+result.getClassWiseList[i].locationId+" "+"studentid"+"'>"+result.getClassWiseList[i].count+"</td>" 
							+"<td> "+result.getClassWiseList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.getClassWiseList[i].studentnamelabel+"</td>"
							+"<td> "+result.getClassWiseList[i].studentrollno+" </td>"
							+"<td> "+result.getClassWiseList[i].classname+" </td>"
							+"<td> "+result.getClassWiseList[i].sectionnaem+" </td>"
							+"</tr>");
					}
					}
					else{
						$("#allstudent tbody").append("<tr><td colspan='5'>NO Records Found</td></tr>");
					}
					$(".select").change(function() {
						
						if($(".select").length == $(".select:checked").length){
							$("#selectall").prop("checked",true);
						}
						else{
							$("#selectall").prop("checked",false);
						}
					});
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.getClassWiseList.length);
				
			}
		});
	}

function getStudentListBySection(locationid,accyear,classname,sectionid,sortingby,orderby){
	
	datalist = {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"sortingby" :sortingby,
			"orderby" :orderby,
			
		}, $.ajax({
			type : 'POST',
			url : "adminMenu.html?method=getStudentListByTC",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					if(result.getSectionWiseList.length>0){
					for(var i=0;i<result.getSectionWiseList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' id='select' class='"+result.getSectionWiseList[i].locationId+" "+result.getSectionWiseList[i].academicYearId+" "+result.getSectionWiseList[i].studentId+" "+result.getSectionWiseList[i].studentAdmissionNo+" "+result.getSectionWiseList[i].classDetailId+" select'/></td>"  
							+"<td> "+result.getSectionWiseList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.getSectionWiseList[i].studentnamelabel+"</td>"
							+"<td> "+result.getSectionWiseList[i].studentrollno+" </td>"
							+"<td> "+result.getSectionWiseList[i].classsection+" </td>"
							+"</tr>");
					};
					}else{
						$("#allstudent tbody").append("<tr>"+"<td colspan='6'>No Records Founds</td>" +"</tr>");
					}
					
					$(".select").change(function() {
						
						if($(".select").length == $(".select:checked").length){
							$("#selectall").prop("checked",true);
						}
						else{
							$("#selectall").prop("checked",false);
						}
					});
						
						
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.getSectionWiseList.length);
					
			}
		});
	}

function searchList(){

	var searchname = $("#searchvalue").val().trim();
	var locationid=$("#locationname").val();
	var accyear=$("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	
datalist = {
			
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"searchname" :searchname,
		}, $.ajax({
			type : 'POST',
			url : "studentRegistration.html?method=getStudentSearchByList",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					if(result.SearchList.length>0){
					for(var i=0;i<result.SearchList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' id='select' class='"+result.SearchList[i].locationId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].studentId+" "+result.SearchList[i].studentAdmissionNo+" "+result.SearchList[i].classDetailId+" select'/></td>"  
							+"<td> "+result.SearchList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.SearchList[i].studentFullName+"</td>"
							+"<td> "+result.SearchList[i].studentrollno+" </td>"
							+"<td> "+result.SearchList[i].classsection+" </td>"
							+"</tr>");
					}	
					}
					else{
						$("#allstudent tbody").append("<tr>"+"<td colspan='6'>No Records Founds</td>" +"</tr>");
					}
					
					$(".select").change(function() {
						
						if($(".select").length == $(".select:checked").length){
							$("#selectall").prop("checked",true);
						}
						else{
							$("#selectall").prop("checked",false);
						}
					});
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.SearchList.length);
					
					
					
			}
		});
}


function cancelTranferCertificate(list,list1,list2){
	datalist = {			
		"location" :list.toString(),
		"accyear" :list1.toString(),
		"studentid" :list2.toString(),
		
		
	}, $.ajax({
		type : 'POST',
		url : "adminMenu.html?method=NewCancelStudentTC",
		data :  datalist,
		async : false,
		
		success : function(response) {
			 
			var result = $.parseJSON(response);
			
			if(result.status=="TC Cancelled Successfully...")
				
				{
				$('.errormessagediv').hide();
				$(".successmessagediv").show();
				$(".successmessage").text("TC Cancelled Successfully");
				}
			else{
				$(".successmessagediv").hide();
				$('.errormessagediv').show();
				$('.validateTips').text(result.status);
			}
			setTimeout( function(){
				window.location.href="adminMenu.html?method=tcgeneration";
			},2000);
			
				
				
				
		}
	});
}


function generateTranferCertificate(list,list1,list2,list3,list4,examdetails,reason,remarks,result,appdate,ladate,csub,esub,admclass){
		datalist = {			
			"location" :list.toString(),
			"accyear" :list1.toString(),
			"studentid" :list2.toString(),
			"admid" :list3.toString(),
			"classid" :list4.toString(),
			"examdetails" :examdetails,
			"reason" :reason,
			"remarks" :remarks,
			"result" :result,
			"appdate":appdate,
			"ladate":ladate,
			"csub":csub,
			"esub":esub,
			"admclass":admclass,
			
		}, $.ajax({
			type : 'POST',
			url : "adminMenu.html?method=newTcGeneration",
			data :  datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
				$('.errormessagediv').hide();
				$("#dialog").dialog("close");
				
					
					$(".select").change(function() {
						
						if($(".select").length == $(".select:checked").length){
							$("#selectall").prop("checked",true);
						}
						else{
							$("#selectall").prop("checked",false);
						}
						
					});
					
					
					location.reload();
					
					
			}
		});
}

function studentDemotedList(){
	$("#demotedTable").empty();
	$("#demotedTable").append("<table class='table allstudent' id='allstudentd' width='100%'" +">"
			+"<thead><tr><th style='width: 70px;'><input type='checkbox' id='selectall1' class='selectall1'/></th>"+
			"<th>Admission No</th>" +
			"<th style='width: 200px;'>Student Name</th>" +
			"<th>Roll No</th>" +
			"<th>Class and Division</th>" +
			"</tr></thead>" +
			"<tbody></tbody></table>"
	);
	
	$.ajax({
		
		type : "POST",
		url : "adminMenu.html?method=notGenTClist",
		async : false,
		
		success : function(response) {

			var result = $.parseJSON(response);

			
			

			if(result.SearchList.length>0){

			for (var i = 0; i < result.SearchList.length; i++) {

				$("#allstudentd tbody").append("<tr>"
						+"<td><input type='checkbox'  class='"+result.SearchList[i].locationId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].studentId+" "+result.SearchList[i].studentAdmissionNo+" "+result.SearchList[i].classDetailId+" select1'/></td>"  
						+"<td> "+result.SearchList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.SearchList[i].studentFullName+"</td>"
						+"<td> "+result.SearchList[i].studentrollno+" </td>"
						+"<td> "+result.SearchList[i].classsection+" </td>"
						+"</tr>");

			}	
			$("#selectall1").change(function() {
				$(".select1").prop('checked', $(this).prop("checked"));
			});
						
			$(".select1").change(function() {
				
				if($(".select").length == $(".select1:checked").length){
					$("#selectall1").prop("checked",true);
				}
				else{
					$("#selectall1").prop("checked",false);
				}
			});	
			}
	      else{
				$("#allstudentd tbody").append("<tr>"+"<td colspan='6'>No Records Founds</td>" +"</tr>");
			}
			
			
			$("#show_per_page").val(100);
			$(".numberOfItem1").empty();
			$(".numberOfItem1").append("No. of Records  "+result.SearchList.length);
		}
		
	});
	$("#TCCancel").click(function(){
		var list=[];
		var list1=[];
		var list2=[];
		var cnt = 0;
		$('#allstudentd tbody input[type="checkbox"]:checked').map(function() {
			var schnm =$(this).attr("class").split(" ")[0];
			var acy=$(this).attr("class").split(" ")[1];
			var stuid =$(this).attr("class").split(" ")[2];
			
			list.push(schnm);
			list1.push(acy);
			list2.push(stuid);
		
			cnt++;
		});
		if (cnt == 0) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select any one record for TC Cancellation");
			return false;
		} else{
			cancelTranferCertificate(list,list1,list2);
		}
	});
	$("#course").click(function(){
		var list=[];
		var list1=[];
		var list2=[];
		var list3=[];
		var list4=[];
		var cnt = 0;
		$('#allstudentd tbody input[type="checkbox"]:checked').map(function(){
			var schnm =$(this).attr("class").split(" ")[0];
			var acy=$(this).attr("class").split(" ")[1];
			var stuid =$(this).attr("class").split(" ")[2];
			var adm =$(this).attr("class").split(" ")[3];
			var cls =$(this).attr("class").split(" ")[4];
			list.push(schnm);
			list1.push(acy);
			list2.push(stuid);
			list3.push(adm);
			list4.push(cls);
			cnt++;
		});
		if (cnt == 0) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select any one record for TC Generation");
			return false;
		} else{
			
			window.location.href="studentcertificate.html?method=printCCCertificate&locationId="+list.toString()+"&accyear="+list1.toString()+"&studentid="+list2.toString()+"&admid="+list3.toString()+"&classid="+list4.toString();
		}
		
		
		 
		});
	
	
		
		$("#download").click(function(){
			var list=[];
			var list1=[];
			var list2=[];
			var list3=[];
			var list4=[];
			var cnt = 0;
			$('#allstudentd tbody input[type="checkbox"]:checked').map(function(){
				var schnm =$(this).attr("class").split(" ")[0];
				var acy=$(this).attr("class").split(" ")[1];
				var stuid =$(this).attr("class").split(" ")[2];
				var adm =$(this).attr("class").split(" ")[3];
				var cls =$(this).attr("class").split(" ")[4];
				list.push(schnm);
				list1.push(acy);
				list2.push(stuid);
				list3.push(adm);
				list4.push(cls);
				cnt++;
			});
			if (cnt == 0) {
				$(".errormessagediv").show();
				$(".validateTips").text("Select any one record for TC Generation");
				return false;
			} else{
				
				window.location.href="adminMenu.html?method=TransferCertificateDownload&locationId="+list.toString()+"&accyear="+list1.toString()+"&studentid="+list2.toString()+"&admid="+list3.toString()+"&classid="+list4.toString();
			}
			
			
			 
			});
}


function studentDemotedListFilter(locationid,accyear,classname,sectionid,sortingby,orderby){
	$("#demotedTable").empty();
	$("#demotedTable").append("<table class='table allstudent' id='allstudentd' width='100%'" +">"
			+"<tr><th style='width: 70px;'><input type='checkbox' id='selectall1' class='selectall1'/></th>"+
			"<th>Admission No</th>" +
			"<th style='width: 200px;'>Student Name</th>" +
			"<th>Roll No</th>" +
			"<th>Class and Division</th>" +
			"</tr>" +
			"</table>"
	);
	datalist = {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"sortingby" :sortingby,
			"orderby" :orderby,
		},
	
	$.ajax({
		
		type : "POST",
		url : "adminMenu.html?method=GenTCListFilter",
		data : datalist,
		async : false,
		
		success : function(response) {

			var result = $.parseJSON(response);
			if(result.SearchList.length>0){
			for (var i = 0; i < result.SearchList.length; i++) {

				$("#allstudentd").append("<tr>"
						+"<td><input type='checkbox' id='select1' class='"+result.SearchList[i].locationId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].studentId+" "+result.SearchList[i].studentAdmissionNo+" "+result.SearchList[i].classDetailId+" select1'/></td>"  
						+"<td> "+result.SearchList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.SearchList[i].studentFullName+"</td>"
						+"<td> "+result.SearchList[i].studentrollno+" </td>"
						+"<td> "+result.SearchList[i].classsection+" </td>"
						+"</tr>");
			}
			
			}
			else{
				$("#allstudent tbody").append("<tr>"+"<td colspan='6'>No Records Founds</td>" +"</tr>");
			}
			
			$("#selectall1").change(function() {
				$(".select1").prop('checked', $(this).prop("checked"));
			});
						
			$(".select1").change(function() {
				
				if($(".select").length == $(".select1:checked").length){
					$("#selectall1").prop("checked",true);
				}
				else{
					$("#selectall1").prop("checked",false);
				}
			});
			$("#show_per_page").val(50);
			$(".numberOfItem1").empty();
			$(".numberOfItem1").append("No. of Records  "+result.SearchList.length);
		}
		
	});
}

function studentDemotedListSearch(){
	$("#demotedTable").empty();
	$("#demotedTable").append("<table class='table allstudent' id='allstudentd' width='100%'" +">"
			+"<tr><th style='width: 70px;'><input type='checkbox' id='selectall1' class='selectall1'/></th>"+
			"<th>Admission No</th>" +
			"<th style='width: 200px;'>Student Name</th>" +
			"<th>Roll No</th>" +
			"<th>Class and Division</th>" +
			"</tr>" +
			"</table>"
	);
	
	var searchname = $("#searchvalue1").val().trim();
	var locationid=$("#locationname1").val();
	var accyear=$("#Acyearid").val();
	var classname=$("#classname1").val();
	var sectionid=$("#sectionid1").val();
	
	datalist = {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"searchname" :searchname,
		},
	
	$.ajax({
		
		type : "POST",
		url : "adminMenu.html?method=studentDemotedListSearch",
		data : datalist,
		async : false,
		
		success : function(response) {

			var result = $.parseJSON(response);
			if(result.SearchList.length>0){
			for (var i = 0; i < result.SearchList.length; i++) {

				$("#allstudentd").append("<tr>"
						+"<td><input type='checkbox' id='select1' class='"+result.SearchList[i].locationId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].studentId+" "+result.SearchList[i].studentAdmissionNo+" "+result.SearchList[i].classDetailId+" select1'/></td>"  
						+"<td> "+result.SearchList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.SearchList[i].studentFullName+"</td>"
						+"<td> "+result.SearchList[i].studentrollno+" </td>"
						+"<td> "+result.SearchList[i].classsection+" </td>"
						+"</tr>");
			}	
     	}
			else{
				$("#allstudentd tbody").append("<tr>"+"<td colspan='6'>No Records Founds</td>" +"</tr>");
			}
			

			$("#selectall1").change(function() {
				$(".select1").prop('checked', $(this).prop("checked"));
			});
						
			$(".select1").change(function() {
				
				if($(".select").length == $(".select1:checked").length){
					$("#selectall1").prop("checked",true);
				}
				else{
					$("#selectall1").prop("checked",false);
				}
			});
			$("#show_per_page").val(50);
			$(".numberOfItem1").empty();
			$(".numberOfItem1").append("No. of Records  "+result.SearchList.length);
		}
		
	});
}





