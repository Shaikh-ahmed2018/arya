$(document).ready(function(){	
	
	$(".tolalListField ul li").click(function(){
		if($("#selected"+$(this).attr("id")).length >0){
			$("input[name='"+$(this).attr("id")+"']").remove();
			$("#selected"+$(this).attr("id")).remove();
			$(this).css({
				"background-color":"#FEFEFE"
			});
		}
		else{
			
			$(".SelectedListField ul").append("<li id=selected"+$(this).attr('id')+">"+$(this).text()+"</li>");
		if($(this).attr('id')=="student_rollno"){
			$(".SelectedListField").append("<input type='hidden' name='"+$(this).attr('id')+"' value='cs."+$(this).attr('id')+"' />");
		}
		else if($(this).attr('id')=="casteCategory"){
			$(".SelectedListField").append("<input type='hidden' name='"+$(this).attr('id')+"' value='cct."+$(this).attr('id')+"' />");
		}
		else if($(this).attr('id')=="subjectName_s"){
			$(".SelectedListField").append("<input type='hidden' name='"+$(this).attr('id')+"' value='s."+$(this).attr('id').split("_")[0]+"' />");
		}
		else if($(this).attr('id')=="subjectName_t"){
			$(".SelectedListField").append("<input type='hidden' name='"+$(this).attr('id')+"' value='t."+$(this).attr('id').split("_")[0]+"' />");
		}
		else{
			$(".SelectedListField").append("<input type='hidden' name='"+$(this).attr('id')+"' value='"+$(this).attr('id')+"' />");
		}
			
			$(this).css({
				"background-color":"#C7C7C7"
			});
		}
		
		
	});
	$("#orderbycheck").change(function(){
		if($("#orderbycheck").is(":checked")==true){
			if($(".SelectedListField ul li").length==0){
				if($("#abcd1").length>0)
					$("#abcd1").remove();
				$("#orderbycheck").attr("checked",false);
				$("#orderby").append("<p id='abcd1'>First Select Item to download</p>");
			}
			else{
				$("#abcd1").remove();
				$("#orderby").append("<p id='abcd'>Select Item From Selected Field</p>");
				orderby();	
			}
			
		}
		else{
			$("#abcd").remove();
		}
		
	});
	$("#accyear").val($("#hacademicyaer").val());
	$("#allstudent").hide();
	$(".selecteditems").hide();
	$("#genderName").hide();
	$("#gender").hide();

	if($("#hideenId").val()!="" && $("#hideenId").val()!=undefined ){

		$(".selecteditems").show(1000);
		$("#allstudent").show(1000);
		$("#txtstyle, #txtstyle").slideToggle();
	}
	
	$("#location").change(function(){
		getClassList();
		
	});
	
	$("#selection").change(function() {
		if($("#selection").val() == 'stulistgenwise'){
			$("#genderName").show();
			$("#gender").show();
		}else{
			$("#genderName").hide();
			$("#gender").hide();
		}
		

	});
	
	

	

	$("#class").change(function(){

		var classId=$("#class").val();
		var locationId=$("#location").val();
		$.ajax({
			type : "GET",
			url : "reportaction.html?method=getSectionByClass",
			data : {"classId":classId,
					"location":locationId},
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);

				$("#section").html("");
				$("#section").append('<option value="all">' + "ALL"	+ '</option>');

				for (var j = 0; j < result.SectionList.length; j++) {

					$("#section").append(
							'<option value="'
							+ result.SectionList[j].sectionId
							+ '">'
							+ result.SectionList[j].sectionname
							+ '</option>');

				}
			}
		});
	});
	
	$("#downloadexcell").click(function(){
		var formValueArray=[];
		var labelValueArray=[];
		var orderbyArray=[];
		$(".SelectedListField input").each(function(){
			formValueArray.push($(this).val());
		});
		$(".SelectedListField li").each(function(){
			labelValueArray.push($(this).text());
		});
		
		if($(".orderby span").length>0){
			$(".orderby span").each(function(){
				orderbyArray.push($(this).attr("class"));
			});
		}
		else{
			orderbyArray.push("student_fname_var");
		}
		if($(".SelectedListField ul").length > 0){
			$.ajax({

				type : 'POST',
				url : "reportaction.html?method=CustomizableStudentReportsExcell",
				data : {"formValueArray":formValueArray.toString(),
						"labelValueArray":labelValueArray.toString(),
						"location":$("#location").val(),
						"accyear":$("#accyear").val(),
						"class":$("#class").val(),
						"section":$("#section").val(),
						"orderbyArray":orderbyArray.toString()
				},
				
				success : function(response) {
					
					window.location.href="reportaction.html?method=CustomizableStudentReportsExcellDownload";
				}
			});
		}
		else{
			$(".errormessagediv").show();
			$(".validateTips").text("Select Any Field to Generate Report");
			$(".errormessagediv").delay(2000).fadeOut("slow");
			
		}

	});


});

function getClassList(){
	var locationid=$("#location").val();
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

			$('#class').html("");

			$('#class').append('<option value="all">' + "ALL"	+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#class').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
}




function getStudentsStrength(accyear,selection,classId,section,location){

	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getStudentsStrength",
		data : datalist,

		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+"<tr><th>Class</th>"+
					"<th>Div</th>" +
					"<th>Girls</th>"+
					"<th>Boys</th>" +
					"<th>Total</th>" +
					"<th></th>" +
					"</tr>" +
					"</table>"
			);
			if(result.studentsStrength.length>0){
			for(var i=0;i<result.studentsStrength.length;i++){

				$("#studentlisttable #allstudent").append(
						"<tr>"+
						"<td>"+result.studentsStrength[i].classname+"</td>"+
						"<td>"+result.studentsStrength[i].sectionname+"</td>"+
						"<td>"+result.studentsStrength[i].girls+"</td>"+
						"<td>"+result.studentsStrength[i].boys+"</td>"+
						"<td>"+result.studentsStrength[i].totalStudentsInDiv+"</td>"+
						"<td>"+result.studentsStrength[i].totalStudentsInCls+"</td>"+
						+"</tr>"

				);

			}
		}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='6'>NO Records Found</td></tr>");
			}
			
		}

	});
}
function orderby(){
	
	
	
	
	$(".SelectedListField ul li").click(function(){
		if($("#abcd").length>0)
			$("#abcd").remove();
		
		
		if($("#orderby"+$(this).attr("id")).length >0){
	
			$("#orderby"+$(this).attr("id")).remove();
			$(this).css({
				"background-color":"#FEFEFE"
			});
		}
		else{
			if($(this).attr('id')=="selectedstudent_rollno"){
				$(".orderby").append("<span id=orderby"+$(this).attr('id')+" class="+$("input[name="+$(this).attr('id').split("elected")[1]).val()+">"+$(this).text()+",</span>");
			}
			else if($(this).attr('id')=="selectedcasteCategory"){
				$(".orderby").append("<span id=orderby"+$(this).attr('id')+" class="+$("input[name="+$(this).attr('id').split("elected")[1]).val()+">"+$(this).text()+",</span>");
			}
			else if($(this).attr('id')=="selectedsubjectName_s"){
				$(".orderby").append("<span id=orderby"+$(this).attr('id')+" class="+$("input[name="+$(this).attr('id').split("elected")[1]).val()+">"+$(this).text()+",</span>");
			}
			else if($(this).attr('id')=="selectedsubjectName_t"){
				
				$(".orderby").append("<span id=orderby"+$(this).attr('id')+" class="+$("input[name="+$(this).attr('id').split("elected")[1]).val()+">"+$(this).text()+",</span>");
			}
			else{
				$(".orderby").append("<span id=orderby"+$(this).attr('id')+" class="+$("input[name="+$(this).attr('id').split("elected")[1]).val()+">"+$(this).text()+",</span>");
			}
			$(this).css({
				"background-color":"#C7C7C7"
			});
		}
	});
}
