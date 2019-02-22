$(document).ready(function(){
	
	$("#Acyearid").val($("#globalAcademicYear").val());
	
	if($(".isApply").val() =="Y"){
		$("#IsApplicable").prop("checked",true);
	}
	$("#save").click(function(){
		var locationArray=[];
		var classArray=[];
		var dayArray=[];
		var amountArray=[];
		var accyearArray=[];
		var termArray=[];
		var isApplicable=null;
		$("#allstudent tbody tr").each(function(){
		
			if (jQuery.inArray($(this).find(".date").val(), dayArray)=='-1' || jQuery.inArray($(this).find(".school").val(), locationArray)=='-1' || jQuery.inArray($(this).find(".ClassList").val(), classArray)=='-1'){
				
				locationArray.push($(this).find(".school").val());
				classArray.push($(this).find(".ClassList").val());
				accyearArray.push($(this).find(".accyear").val());
				termArray.push($(this).find(".termlist").val());
				dayArray.push($(this).find(".date").val());
				amountArray.push($(this).find(".amount").val());
			}
			else{
				
			}
		});
		if($("#IsApplicable").prop("checked")==true){
			isApplicable="Y";
		}
		else{
			isApplicable="N";
		}
		var dataList={
				"locationArray":locationArray.toString(),
				"classArray":classArray.toString(),
				"dayArray":dayArray.toString(),
				"amountArray":amountArray.toString(),
				"isApplicable":isApplicable,
				"accyearArray":accyearArray.toString(),
				"termArray":termArray.toString(),
				"accyear":$("#Acyearid").val(),
		};
		$.ajax({
			type:"POST",
			url:"addfee.html?method=saveFineConfiguration",
			data:dataList,
			async:false,
			success:function(response){
			var	result=$.parseJSON(response);
			if(result.status="true"){
				$(".successmessagediv").show();
				$(".successmessage").text("Saving Records...");
				setTimeout(function(){
					location.reload();
				},2000);
				
			}
			else{
				$(".errormessagediv").show();
				$(".validatTips").text("Records not Saved");
				$(".errormessagediv").delay(2000).fadeOut("slow");
			}
			}
		});
	});
	
	$(".date").datepicker({
		dateFormat : "dd-mm-yy",
		changeMonth : true,
		changeYear : true,
	});
	getAccyearList();
	getAllSchool();
	$(".accyear").change(function(){
		getChangedTermList($(this).closest("tr").find(".school"),$(this))
	});
	$(".school").change(function(){
		getChangedClassList($(this));
		getChangedTermList($(this),$(this).closest("tr").find(".accyear"))
	});
	
	$(".ClassList").each(function(){
		getAllClassList($(this),$(this).closest("tr").find(".hlocationid").val());
	});
	$(".termlist").each(function(){
		getAllTermList($(this));
	});
	$("#Acyearid").change(function(){
		getFineList($(this).val());
	});
});
function getAllSchool(){
	$.ajax({
		type:'POST',
		url:'adminMenu.html?method=getAllSchool',
		async:false,
		success:function(data){
			var result=$.parseJSON(data);
			$(".school").empty();
			$(".school").append("<option value=''>------SELECT------</option>");
			for(var i=0;i<result.schoolName.length;i++){
				$(".school").append("<option value='"+result.schoolName[i].locationId+"'>"+result.schoolName[i].locationName+"</option>");
			}
			$(".school").each(function(){
				$(this).val($(this).closest("td").find(".hlocationid").val());
			});
		}
	});
}
function getAllClassList(pointer,location){
	$.ajax({
		type:'POST',
		url:'adminMenu.html?method=getClassList',
		data:{'location':location},
		
		success:function(data){

			var result=$.parseJSON(data);
			pointer.closest("tr").find("select.ClassList").empty();
			pointer.closest("tr").find("select.ClassList").append("<option value=''>------SELECT------</option>");
			for(var i=0;i<result.classList.length;i++){
				pointer.closest("tr").find("select.ClassList").append("<option value='"+result.classList[i].classId+"'>"+result.classList[i].className+"</option>");
			}
			
		
			$(".ClassList").each(function(){
				$(this).val($(this).closest("td").find(".hclassnameid").val());
			});
		}
	});
}
function getChangedClassList(pointer){
	$.ajax({
		type:'POST',
		url:'adminMenu.html?method=getClassList',
		data:{'location':pointer.val()},
		async:false,
		success:function(data){
			var result=$.parseJSON(data);
			pointer.closest("tr").find("select.ClassList").empty();
			pointer.closest("tr").find("select.ClassList").append("<option value=''>------SELECT------</option>");
			for(var i=0;i<result.classList.length;i++){
				pointer.closest("tr").find("select.ClassList").append("<option value='"+result.classList[i].classId+"'>"+result.classList[i].className+"</option>");
			}
			
		}
	});
}
function getChangedTermList(location,accyear){
	$.ajax({
		type:'POST',
		url:'adminMenu.html?method=getTermList',
		data:{'location':location.val(),
				'accyear':accyear.val(),
		},
		async:false,
		success:function(data){
			var result=$.parseJSON(data);
			location.closest("tr").find("select.termlist").empty();
			location.closest("tr").find("select.termlist").append("<option value=''>------SELECT------</option>");
			for(var i=0;i<result.TermList.length;i++){
				location.closest("tr").find("select.termlist").append("<option value='"+result.TermList[i].termId+"'>"+result.TermList[i].term+"</option>");
			}
			
		}
	});
}
function getAllTermList(pointer){
	$.ajax({
		type:'POST',
		url:'adminMenu.html?method=getTermList',
		data:{'location':pointer.closest("tr").find(".hlocationid").val(),
				'accyear':pointer.closest("tr").find(".haccyearid").val(),
		},
		
		success:function(data){
			var result=$.parseJSON(data);
			pointer.empty();
			pointer.append("<option value=''>------SELECT------</option>");
			for(var i=0;i<result.TermList.length;i++){
				pointer.append("<option value='"+result.TermList[i].termId+"'>"+result.TermList[i].term+"</option>");
			}
			$(".termlist").each(function(){
				$(this).val($(this).closest("td").find(".htermlist").val());
			});
			
		}
	});
}
function getAccyearList(){
	$.ajax({
		type:'POST',
		url:'adminMenu.html?method=getAccyearList',
	
		success:function(data){
			var result=$.parseJSON(data);
			$("select.accyear").empty();
			for(var i=0;i<result.Accyear.length;i++){
				$("select.accyear").append("<option value='"+result.Accyear[i].accId+"'>"+result.Accyear[i].accName+"</option>");
			}
			$(".accyear").each(function(){
				$(this).val($(this).closest("td").find(".haccyearid").val());
			});
			
		}
	});
}
function getFineList(accyear){
	$.ajax({
		type:"POST",
		url:"adminMenu.html?method=fineConfigurationByAccyear",
		data:{"accyear":accyear},
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			if(result.fineList.length>0){
				$("#allstudent tbody").empty();
				for(var i=0;i<result.fineList.length;i++){
					$("#allstudent tbody").append("<tr>" +
							"<td>&gt;=</td>" +
							"<td><select  class='accyear' ></select><input type='hidden' value='"+result.fineList[i].accyearid+"' class='haccyearid' /></td>" +
							"<td><select  class='school' ><option value=''>------SELECT------</option></select><input type='hidden' value='"+result.fineList[i].locationid+"' class='hlocationid' /></td>" +
							"<td><select class='ClassList' ><option value=''>------SELECT------</option></select><input type='hidden' value='"+result.fineList[i].classnameid+"' class='hclassnameid' /></td>"+
							"<td><select class='termlist' ><option value=''>------SELECT------</option></select><input type='hidden' value='"+result.fineList[i].termId+"' class='htermlist' /></td>"+
							"<td><input type='text' class='date' value='"+result.fineList[i].fineDate+"' readonly='readonly' /></td>"+
							"<td><input type='number' class='amount' value='"+result.fineList[i].fine+"' /></td>" +
							"</tr>");
				}
				
				
				
				if(result.fineList[0].isApplicable =="Y"){
					$("#IsApplicable").prop("checked",true);
				}
			}
			
			$("#allstudent tbody").append("<tr>" +
					"<td>&gt;=</td>" +
					"<td><select  class='accyear' ></select><input type='hidden' value='' class='haccyearid' /></td>" +
					"<td><select  class='school' ><option value=''>------SELECT------</option></select><input type='hidden' value='' class='hlocationid' /></td>" +
					"<td><select class='ClassList' ><option value=''>------SELECT------</option></select><input type='hidden' value='' class='hclassnameid' /></td>"+
					"<td><select class='termlist' ><option value=''>------SELECT------</option></select><input type='hidden' value='' class='htermlist' /></td>"+
					"<td><input type='text' class='date' value='' readonly='readonly' /></td>"+
					"<td><input type='number' class='amount' value='' /></td>" +
					"</tr>");
		
			$(".date").datepicker({
				dateFormat : "dd-mm-yy",
				changeMonth : true,
				changeYear : true,
			});
			getAccyearList();
			getAllSchool();
			$(".accyear").change(function(){
				getChangedTermList($(this).closest("tr").find(".school"),$(this))
			});
			$(".school").change(function(){
				getChangedClassList($(this));
				getChangedTermList($(this),$(this).closest("tr").find(".accyear"))
			});
			
			$(".ClassList").each(function(){
				getAllClassList($(this),$(this).closest("tr").find(".hlocationid").val());
			});
			$(".termlist").each(function(){
				getAllTermList($(this));
			});
			$("#Acyearid").change(function(){
				getFineList($(this).val());
			});
			
		}
	});
}