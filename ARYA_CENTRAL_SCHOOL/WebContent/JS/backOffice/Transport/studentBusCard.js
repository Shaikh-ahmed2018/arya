function handle(e){
		
	    if(e.keyCode === 13){
	        e.preventDefault(); // Ensure it is only this code that rusn

	        searchList();
	    }
	}
$(document).ready(function(){
	
	
	var hacademicyear=$('#hacademicyaer').val();
	$("#Acyearid option[value="+hacademicyear+"]").attr('selected', 'true');
	
	getClassList();
	
	$("#resetbtn").on("click", function (e) {
		$("#locationname,#Acyearid,#classname,#sectionid").val("all");
		$("#searchvalue").val("");
	});
	
	$("#search").click(function(){
		searchList();
	});	
	
	$("#Acyearid").change(function(){
		$("#searchvalue").val("");
		
		getClassList();
	});
	$("#locationname").change(function(){
		$("#searchvalue").val("");
		
		getClassList();
		document.getElementById("locationname").style.border = "1px solid #ccc";
		document.getElementById("locationname").style.backgroundColor = "#FFF";
		getTermDetails();
	});
	
	$("#classname").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		
		getSectionList($("#classname"),$("#locationname"));
		getStudentList(locationid,accyear,classname);
	});
	
	$("#sectionid").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		
		getStudentListBySection(locationid,accyear,classname,sectionid);
	});
	
	$("#termid").click(function(){
		if($("#locationname").val() == "all"){
			$(".errormessagediv").show();
						$(".validateTips").text("Field Required Location");
						document.getElementById("locationname").style.border = "1px solid #AF2C2C";
						document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
						setTimeout(function(){
							$(".errormessagediv").hide();
						},3000)
						return false;
		}
	})
	
	$("#print").click(function(){
		
		var result = $("input[type='radio']:checked").val();
		if(result == undefined || result == " "){
			$(".errormessagediv").show();
			$(".validateTips").text("Select any One Student Record");
			setTimeout(function(){
				$(".errormessagediv").hide();
			},3000)
			return false;
		}else if($("#termid").val() == "" || $("#termid").val() == undefined){
			$(".errormessagediv").show();
			$(".validateTips").text("Select the Term");
			setTimeout(function(){
				$(".errormessagediv").hide();
			},3000)
			return false;
		} 
		else{
		
		value = $("input[type='radio']:checked").val().split(" ");
		$.ajax({
			type: "POST",
			url:"transport.html?method=printStudentBusCard&stuId="+value[0]+"&ClassId="+value[3]+"&sectionId="+value[4]+"&termId="+ $("#termid").val()+"&accyear="+ value[1]+"&locid="+value[2],
			success : function(data){
				
			}
		});
	}
	});
	
	$("#download").click(function() {

					var result = $("input[type='radio']:checked").val();
					if(result == undefined || result == " "){
						$(".errormessagediv").show();
						$(".validateTips").text("Select any One Student Record");
						setTimeout(function(){
							$(".errormessagediv").hide();
						},3000)
						return false;
					}else if($("#termid").val() == "" || $("#termid").val() == undefined){
						$(".errormessagediv").show();
						$(".validateTips").text("Select the Term");
						setTimeout(function(){
							$(".errormessagediv").hide();
						},3000)
						return false;
					} 
					else{
						value = $("input[type='radio']:checked").val().split(" ");
							window.location.href = 'transport.html?method=getStudentBusCard&stuId='
							+ value[0]
							+ '&ClassId='
							+ value[3]
							+ '&sectionId='
							+ value[4]
							+ "&termId="
							+ $("#termid").val()
							+ "&accyear="
							+ value[1]
							+"&locid="+value[2];
					}
	});
});

function changeAccYear(){
	var locationId = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	
	$.ajax({
		type : 'POST',
		url : "transport.html?method=getStudentListByLoc",
		data : {
			"location" :locationId,
			"accyear" :accyear,				
		},
		async : false,
		
		success : function(response) {
			 
			var result = $.parseJSON(response);
				$("#allstudent tbody").empty();
				if(result.SearchList.length>0){
				for(var i=0;i<result.SearchList.length;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td><input type='radio' name='select' value='"+result.SearchList[i].studentId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].locationId+" "+result.SearchList[i].classDetailId+" "+result.SearchList[i].section_id+"'></td>" 
						+"<td> "+result.SearchList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.SearchList[i].studentFullName+"</td>"
						+"<td> "+result.SearchList[i].studentrollno+"</td>"
						+"<td> "+result.SearchList[i].classname+"</td>"
						+"<td> "+result.SearchList[i].sectionnaem+"</td>"
						+"</tr>");
				}
				}
				else{
					$("#allstudent tbody").append("<tr><td colspan='6'>NO Records Found</td></tr>");
				}
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

			$('#classname').append('<option value="All">' + "ALL"	+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#classname').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
}

function getSectionList(classname,location){
	datalist={
			"classname" : classname.val(),
			"location" : location.val()
	},
	
	$.ajax({
		
		type : 'POST',
		url : "studentTransferReport.html?method=getSectionList",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#sectionid').html("");
			
			$('#sectionid').append('<option value="All">' + "ALL"	+ '</option>');
			
			for ( var j = 0; j < result.seclist.length; j++) {

				$('#sectionid').append('<option value="'

						+ result.seclist[j].sectionId + '">'

						+ result.seclist[j].sectionName

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
			url : "transport.html?method=getStudentListByClass",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					if(result.getClassWiseList.length>0){
					
					for(var i=0;i<result.getClassWiseList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td><input type='radio' name='select' value='"+result.getClassWiseList[i].studentId+" "+result.getClassWiseList[i].academicYearId+" "+result.getClassWiseList[i].locationId+" "+result.getClassWiseList[i].classDetailId+" "+result.getClassWiseList[i].sectioncode+"'></td>" 
							+"<td> "+result.getClassWiseList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.getClassWiseList[i].studentnamelabel+"</td>"
							+"<td> "+result.getClassWiseList[i].studentrollno+" </td>"
							+"<td> "+result.getClassWiseList[i].classname+" </td>"
							+"<td> "+result.getClassWiseList[i].sectionnaem+" </td>"
							+"</tr>");
					}	
					}
					else{
						$("#allstudent tbody").append("<tr><td colspan='6'>NO Records Found</td></tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.getClassWiseList.length);
			}
		});
	}

function getStudentListBySection(locationid,accyear,classname,sectionid){
	
	datalist = {
			
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
		}, $.ajax({
			type : 'POST',
			url : "transport.html?method=getStudentListBySection",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					if(result.getSectionWiseList.length>0){
					for(var i=0;i<result.getSectionWiseList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td><input type='radio' name='select' value='"+result.getSectionWiseList[i].studentId+" "+result.getSectionWiseList[i].academicYearId+" "+result.getSectionWiseList[i].locationId+" "+result.getSectionWiseList[i].classDetailId+" "+result.getSectionWiseList[i].sectioncode+"'></td>" 
							+"<td> "+result.getSectionWiseList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.getSectionWiseList[i].studentnamelabel+"</td>"
							+"<td> "+result.getSectionWiseList[i].studentrollno+" </td>"
							+"<td> "+result.getSectionWiseList[i].classname+" </td>"
							+"<td> "+result.getSectionWiseList[i].sectionnaem+" </td>"
							+"</tr>");
					}
					}
		
					else{
						$("#allstudent tbody").append("<tr><td colspan='6'>NO Records Found</td></tr>");
					}
					
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
			url : "transport.html?method=getStudentSearchByList",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					if(result.SearchList.length>0){
					for(var i=0;i<result.SearchList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td ><input type='radio' name='select' value='"+result.SearchList[i].studentId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].locationId+" "+result.SearchList[i].classDetailId+" "+result.SearchList[i].sectioncode+"'></td>" 
							+"<td> "+result.SearchList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.SearchList[i].studentFullName+"</td>"
							+"<td> "+result.SearchList[i].studentrollno+" </td>"
							+"<td> "+result.SearchList[i].classname+" </td>"
							+"<td> "+result.SearchList[i].sectionnaem+" </td>"
							+"</tr>");
					}
			}
					else{
						$("#allstudent tbody").append("<tr><td colspan='6'>NO Records Found</td></tr>");
					}
					
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.SearchList.length);
				
			}
		});
}

function getTermDetails() {

	$.ajax({

				type : 'POST',
				url : 'reportaction.html?method=getTermDetails',
				data : {"loc" :$("#locationname").val(),"accyear" :$("#Acyearid").val()},
				async : false,
				success : function(response) {
					var result = $.parseJSON(response);
					$("#termid").empty();
					$("#termid").append('<option value="">-------------Select-----------</option>');
					for ( var j = 0; j < result.termList.length; j++) {
						$("#termid").append(
										'<option value="'
												+ result.termList[j].termid
												+ '">'
												+ result.termList[j].termname
												+ '</option>');
					}
				}
			});
}
