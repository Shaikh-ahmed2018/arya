$(document).ready(function() {
					$(".field").change(function(){
						pointer=$(this);
						$("#OrderingCondition tbody tr").find(".field").not(pointer).each(function(){
							if($(this).val()==$(pointer).val()){
								pointer.val("");
							}
						});
					});
					$("#Acyearid").val($("#hacademicyaer").val());
					$("#codition-collapse").click(function(){
						$("#coditiontable-block").slideToggle();
					});
					
					$("#locationname").change(function(){
						getClassName($("#locationname").val());
					if($("#classname").val()!="")
						getClassSpecilization($("#locationname").val(),$("#classname").val(),$(".specialization"));
					});
					
					
					$('#classname').change(function() {
						var classname=$("#classname").val();
						getClassSpecilization($("#classname").val(),$("#locationname").val(),$(".specialization"));
						
						getClassSection(classname);
					$('#studSectionId').change(function() {
						var sectionid = $("#studSectionId").val();
						getsstudentdetails($("#locationname").val(),$("#Acyearid").val(),classname,sectionid);
					});
					});
					
					$("#conditionAdd").click(function(){
						addConditionRows();
					});
					$(".specialization").change(function(){
						getLangauage($("#classname").val(),$("#locationname").val(),$(this).val(),$(this).closest("tr").find(".secondLanguage"));
						getLangauage($("#classname").val(),$("#locationname").val(),$(this).val(),$(this).closest("tr").find(".thirdLanguage"));
					});
				
					
					selectSortable();
					$(".secondLanguage").change(function(){
						if($(this).closest("tr").find(".thirdLanguage").val()==$(this).val()){
							$(this).val("");
						}
					});
					$(".thirdLanguage").change(function(){
						if($(this).closest("tr").find(".secondLanguage").val()==$(this).val()){
							$(this).val("");
						}
					});
				});





function getClassName(val) {
	
	
	
	$.ajax({
	url : "sectionPath.html?method=getCampusClassDetailsIDandClassDetailsNameAction",
	data:{"locationId":val},
	async:false,

	success : function(data) {

		

		var result = $.parseJSON(data);
		$(classname).html("");
		
	    $(classname).append('<option value="">' + "---------Select---------" + '</option>');

		for (var j = 0; j < result.classDetailsIDandClassDetailsNameList.length; j++) {

			$(classname).append(
					'<option value="' + result.classDetailsIDandClassDetailsNameList[j].secDetailsId + '">'
							+ result.classDetailsIDandClassDetailsNameList[j].secDetailsName + '</option>');
		}

	
	
	
	
	}
});
	}

function getClassSection(classname) {
    datalist = {
		"classidVal":classname,
		"locationId":$("#locationname").val()
	}, 
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#studSectionId").html("");
			$("#studSectionId").append('<option value="' + "" + '">' + "----------Select----------" + '</option>');

			for ( var j = 0; j < result.sectionList.length; j++) {
				$("#studSectionId").append(
						'<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}

		}
	});

}


function getsstudentdetails(locationid,accyear,classname,sectionid)
{
	$("#allstudent").show();
datalist = {
			
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
		}, $.ajax({
			type : 'POST',
			url : "adminMenu.html?method=getStudentListBySectionForROllNo",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					if(result.getSectionWiseList.length>0){
					for(var i=0;i<result.getSectionWiseList.length;i++){
					$("#allstudent tbody").append("<tr class='"+result.getSectionWiseList[i].studentId+" "+result.getSectionWiseList[i].academicYearId+" "+result.getSectionWiseList[i].locationId+" "+result.getSectionWiseList[i].gender+" "+result.getSectionWiseList[i].admissionno+" "+result.getSectionWiseList[i].secondLanguageName+" "+result.getSectionWiseList[i].thirdLanguageName+" "+result.getSectionWiseList[i].specilization+" "+"studentid"+"'>"
							+"<td>"+result.getSectionWiseList[i].count+"</td>" 
							+"<td> "+result.getSectionWiseList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.getSectionWiseList[i].studentnamelabel+"<input type='hidden' class='studentName' value='"+result.getSectionWiseList[i].studentnamelabel+"' /></td>"
							+"<td> "+result.getSectionWiseList[i].gender+" </td>"
							+"<td> "+result.getSectionWiseList[i].secondLanguageName+" </td>"
							+"<td> "+result.getSectionWiseList[i].thirdLanguageName+" </td>"
							+"<td> "+result.getSectionWiseList[i].studentrollno+" </td>"
							+"<td><input type='text' class='newRollNo' name='newRollNo' style='background-color:#fffae6;'> </td>"
							+"</tr>");
					}	
					}
					else{
						$("#allstudent tbody").append("<tr><td colspan='8'>NO Records Found</td></tr>");
					}
				$("#rollNoGenerator").click(function(){
					if($("#OrderingCondition #order1 .field").val()!=""){
						$("#OrderingCondition #order1 .field").css({'border-color':'#CCC'});
						rollNoGeneration(locationid,accyear,classname,sectionid);
					
					}
					else{
						$(".errormessagediv").show();
						$(".validateTips").text("Select Field in Condition Box.");
						$(".errormessagediv").delay(2000).fadeOut("slow");
						$("#OrderingCondition #order1 .field").css({'border-color':'#f00'});
						$("#coditiontable-block").show();
					}
				});	
				$("#save").click(function(){
					if($(".newRollNo").val()!=""){
					saveRollNo();
					}
					else{
						$(".errormessagediv").show();
						$(".validateTips").text("First Generate Roll No.");
						$(".errormessagediv").delay(2000).fadeOut("slow");
					}
				});	
			}
		});
	
	}
function addConditionRows(){
	var rowCount=$("#conditionTable tbody tr").length;
	rowCount++;
	$("#conditionTable tbody").append('<tr id="rowid'+rowCount+'">'
						+'<td>'+rowCount+'</td>'
						+'<td><select  name="specialization" class="form-control specialization" ><option value="">---Select---</option></select></td>'
						+'<td><select  name="secondLanguage" class="form-control secondLanguage" ><option value="">---Select---</option></select></td>'
						+'<td><select  name="thirdLanguage" class="form-control thirdLanguage" ><option value="">---Select---</option></select></td>'
						+'<td><span  class="glyphicon glyphicon-trash deleteCondition" onclick="deleteConditionRows('+rowCount+');"></span></td>'	
						+'</tr>');
	
	$("#conditionTable tbody tr input").change(function(){
		sortTable();
	});
		getClassSpecilization($("#classname").val(),$("#locationname").val(),$("#rowid"+rowCount+" .specialization"));
		$(".specialization").change(function(){
			getLangauage($("#classname").val(),$("#locationname").val(),$(this).val(),$(this).closest("tr").find(".secondLanguage"));
			getLangauage($("#classname").val(),$("#locationname").val(),$(this).val(),$(this).closest("tr").find(".thirdLanguage"));
		});
		
		selectSortable();
}
function deleteConditionRows(removeNum){
	var rowCount=$("#conditionTable tbody tr").length;
	var count=0;
	for(var del=removeNum;del<=rowCount;del++){
		jQuery('#rowid' + del).remove();
		count++;
	}
	rowCount=rowCount-count;
}
function getClassSpecilization(classId,location,pointer){
	var data = {
			"classId" : classId,
			"locationId":location,
	};
	$.ajax({
		type : 'POST',
		url : "specialization.html?method=getSpecializationOnClassBased",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			pointer.empty();
			pointer.append('<option value="">'+ "------select------" + '</option>');
			pointer.append('<option value="-">'+ "General" + '</option>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				pointer.append(
						'<option value="'
						+ result.jsonResponse[j].spec_Id
						+ '">'
						+ result.jsonResponse[j].spec_Name
						+ '</option>');
			}


		}
	});
}
function getLangauage(classId,location,specializationId,pointer){
	var data = {
			"classId" : classId,
			"locationId":location,
			"specializationId":specializationId,
	};
	$.ajax({
		type : 'POST',
		url : "subject.html?method=getLangauageOnClassBased",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$(pointer).empty();
			$(pointer).append('<option value="">'+ "------select------" + '</option>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$(pointer).append(
						'<option value="'
						+ result.jsonResponse[j].subjectCode
						+ '">'
						+ result.jsonResponse[j].subjectname
						+ '</option>');
			}


		}
	});
}

function rollNoGeneration(locationid,accyear,classname,sectionid){
	
	var oder2="NO";
	if($("#order2 .field").val()!=""){
		oder2=$("#order2 .field").val()+" "+$("#order2 .ordering").val();
	}
	else{
		oder2="NO";
	}
	var oder3="NO";
	if($("#order3 .field").val()!=""){
		oder3=$("#order3 .field").val()+" "+$("#order3 .ordering").val();
	}
	else{
		oder3="NO";
	}
	 var jsonOBj={
			 "order1":$("#order1 .field").val()+" "+$("#order1 .ordering").val(),
			 "order2":oder2,
			 "order3":oder2,
			 "location" :locationid,
			 "accyear" :accyear,
			 "classId" :classname,
			 "sectionid" :sectionid,
			
	 }
	 
	 $.ajax({
		 type:'POST',
		 url:'adminMenu.html?method=rollNoGeneration',
		data:jsonOBj,
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			$("#allstudent tbody").empty();
			if(result.getSectionWiseList.length>0){
				
				for(var i=0;i<result.getSectionWiseList.length;i++){
				$("#allstudent tbody").append("<tr class='"+result.getSectionWiseList[i].studentId+" "+result.getSectionWiseList[i].academicYearId+" "+result.getSectionWiseList[i].locationId+" "+result.getSectionWiseList[i].gender+" "+result.getSectionWiseList[i].admissionno+" "+result.getSectionWiseList[i].secondLanguageName+" "+result.getSectionWiseList[i].thirdLanguageName+" "+result.getSectionWiseList[i].specilization+" "+"studentid"+"'>"
						+"<td>"+result.getSectionWiseList[i].count+"</td>" 
						+"<td> "+result.getSectionWiseList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.getSectionWiseList[i].studentnamelabel+"<input type='hidden' class='studentName' value='"+result.getSectionWiseList[i].studentnamelabel+"' /></td>"
						+"<td> "+result.getSectionWiseList[i].gender+" </td>"
						+"<td> "+result.getSectionWiseList[i].secondLanguageName+" </td>"
						+"<td> "+result.getSectionWiseList[i].thirdLanguageName+" </td>"
						+"<td> "+result.getSectionWiseList[i].studentrollno+" </td>"
						+"<td><input type='text' class='newRollNo' name='newRollNo' style='background-color:#fffae6;' value='"+result.getSectionWiseList[i].count+"' /> </td>"
						+"</tr>");
				}	
				}
				else{
					$("#allstudent tbody").append("<tr><td colspan='8'>NO Records Found</td></tr>");
				}
		}
		 
		 
	 });
	 
	 

}

function saveRollNo(){
	var studentId=[];
	var accYear=[];
	var schoolCode=[];
	var rollNo=[];
	$("#allstudent tbody tr").each(function(){
		studentId.push($(this).attr("class").split(" ")[0]);
		accYear.push($(this).attr("class").split(" ")[1]);
		schoolCode.push($(this).attr("class").split(" ")[2]);
		rollNo.push($(this).find(".newRollNo").val());
	});
	
	var data = {
			"studentId" : studentId.toString(),
			"accYear":accYear.toString(),
			"schoolCode" : schoolCode.toString(),
			"rollNo":rollNo.toString(),
	};
	if($("#allstudent tbody tr").length==rollNo.length){
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=saveRollNo",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			if(result.status=="true"){
				$(".successmessagediv").show();
				$(".sucessmessage").text("Saving Roll No...");
				setTimeout(function(){
					window.location.href="adminMenu.html?method=generateRollNo";
				},2000);
			}
			else{
				$(".errormessagediv").show();
				$(".validateTips").text("Roll No. Not Saved.");
				$(".errormessagediv").delay(2000).fadeOut("slow");
			}


		}
	});
	}
	else{
		$(".errormessagediv").show();
		$(".validateTips").text("Add Langauage Group");
		$(".errormessagediv").delay(2000).fadeOut("slow");
	}
}
function selectSortable(){
	$('.field').multisortable({
	    stop: function(e, ui) {
	        var $group = $('.ui-multisort-grouped').not(ui.item);
	        $group.clone().insertAfter($(ui.item));
	        $group.each(function() {
	            $(this).removeClass('ui-multisort-grouped');
	        });
	        $group.remove();
	    }
	});
	$('.field').disableSelection();
}