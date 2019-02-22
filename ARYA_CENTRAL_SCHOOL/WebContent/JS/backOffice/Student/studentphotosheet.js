$(document).ready(function()
{
	$("#allstudent tbody").append("<tr><td colspan='5'>No Records Found</td></tr>");
		
	$("#Acyearid").val($("#globalAcademicYear").val());
	
	$("#Acyearid").change(function(){
		getClassList();
		var classname=$("#classname").val();
		getSectionList(classname);
		
		$("#allstudent tbody").empty();
		$("#allstudent tbody").append("<tr><td colspan='5'>No Records Found</td></tr>");
		
	});
	$("#locationname").change(function(){
		getClassList();
		var classname=$("#classname").val();
		getSectionList(classname);
		
		$("#allstudent tbody").empty();
		$("#allstudent tbody").append("<tr><td colspan='5'>No Records Found</td></tr>");
	});
	
	$("#classname").change(function(){
		var classname=$("#classname").val();
		getSectionList(classname);
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var sectionid=$("#sectionid").val();
		
		if($("#sectionid").val() == ""){
			 sectionid="ALL";
		}
		getStudentListBySection(locationid,accyear,classname,sectionid);
		
		
	});
	
	$("#sectionid").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		
		if($("#sectionid").val() == ""){
			 sectionid="ALL";
		}
		
		getStudentListBySection(locationid,accyear,classname,sectionid);
	});
	
	
	 $("#print").click(function(){
			 A4();
	 });
	$("#admissionno").keyup(function(e){
		
		if(e.keyCode==13){
			getStudentListBySearch($(this).val(),$("#Acyearid").val());
		}
	});
	
});


function A4(){
	 var a=$("#printing-css-a4").val();
		var b = document.getElementById("studentpreview").innerHTML;

	    var abd='<style>' + a +'</style>' + b;
	
	   
	    
    var frame1 = $('<iframe />');
    frame1[0].name = "frame1";
    frame1.css({ "position": "absolute", "top": "-1000000px" });
    $("body").append(frame1);
    var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
    frameDoc.document.open();
    //Create a new HTML document.
    frameDoc.document.write('<html><head><title>DIV Contents</title>');
    //Append the external CSS file.
    frameDoc.document.write('<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />');
    frameDoc.document.write('<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">');
    frameDoc.document.write('<link href="CSS/newUI/modern-business.css" rel="stylesheet">');
    frameDoc.document.write('<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">');
    frameDoc.document.write('<link href="CSS/newUI/custome.css" rel="stylesheet">');
    frameDoc.document.write('<link href="CSS/IdCard/IdCard.css" rel="stylesheet" type="text/css" />');
    frameDoc.document.write('</head><body>');
  
    
    //Append the DIV contents.
    frameDoc.document.write(abd);
    frameDoc.document.write('</body></html>');
    frameDoc.document.close();
    setTimeout(function () {
        window.frames["frame1"].focus();
        window.frames["frame1"].print();
        frame1.remove();
    }, 100);
	    


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

			$('#classname').append('<option value="all">' +"-----Select-----"+ '</option>');

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
			
			$('#sectionid').html("");
			
			$('#sectionid').append('<option value="ALL">' + "ALL"	+ '</option>');
			
			for ( var j = 0; j < result.sectionList.length; j++) {

				$('#sectionid').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});
}




function getStudentListBySection(locationid,accyear,classname,sectionid){
	var sectionName=$("#sectionid option:selected").text();
	datalist = {
			
			"locationId" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionId" :sectionid,
		}, 
		$.ajax({
			type : 'POST',
			url : "adminMenu.html?method=studentPhotosheetAccList",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
				
					$("#allstudent tbody").empty();
					if(result.studentSearchList.length > 0){
					
					
								$("#allstudent thead").empty();
								$("#allstudent thead").append("<tr>"
										+"<th class='"+result.studentSearchList.studentId+"' hidden></td>" 
										+"<th colspan='5' style='text-align:center'>"+""+result.studentSearchList[0].location+"  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "+result.studentSearchList[0].classname+" - "+sectionName+"</th>"
										
										+"</tr>");
						
					
					
					if(result.studentSearchList.length == 1){
						for(var i=0;i<result.studentSearchList.length;i++){
							$("#allstudent tbody").append("<tr>"
									+"<td class='"+result.studentSearchList[i].studentId+"' hidden></td>"
									+"<td style='width:160px'> <img src='"+result.studentSearchList[i].studentPhoto+"' width='150px' height='150px' />"+"<br><b>Ad. No</b>"+result.studentSearchList[i].studentAdmissionNo+"<br>"+result.studentSearchList[i].studentFullName+" </td>"
									
									+"</tr>");
							}
						
					}else if(result.studentSearchList.length == 2){
						for(var i=0;i<result.studentSearchList.length;i++){
							$("#allstudent tbody").append("<tr>"
									+"<td class='"+result.studentSearchList[i].studentId+"' hidden></td>"
									+"<td style='width:160px'> <img src='"+result.studentSearchList[i].studentPhoto+"' width='150px' height='150px' />"+"<br><b>Ad. No</b>"+result.studentSearchList[i].studentAdmissionNo+"<br>"+result.studentSearchList[i].studentFullName+" </td>"
									
									+"<td class='"+result.studentSearchList[++i].studentId+"' hidden></td>"
									+"<td style='width:160px'> <img src='"+result.studentSearchList[i].studentPhoto+"' width='150px' height='150px' />"+"<br><b>Ad. No</b>"+result.studentSearchList[i].studentAdmissionNo+"<br>"+result.studentSearchList[i].studentFullName+" </td>"
									
									+"</tr>");
							}
					}else if(result.studentSearchList.length == 3){
						for(var i=0;i<result.studentSearchList.length;i++){
							$("#allstudent tbody").append("<tr>"
									+"<td class='"+result.studentSearchList[i].studentId+"' hidden></td>"
									+"<td style='width:160px'> <img src='"+result.studentSearchList[i].studentPhoto+"' width='150px' height='150px' />"+"<br><b>Ad. No</b>"+result.studentSearchList[i].studentAdmissionNo+"<br>"+result.studentSearchList[i].studentFullName+" </td>"
									
									+"<td class='"+result.studentSearchList[++i].studentId+"' hidden></td>"
									+"<td style='width:160px'> <img src='"+result.studentSearchList[i].studentPhoto+"' width='150px' height='150px' />"+"<br><b>Ad. No</b>"+result.studentSearchList[i].studentAdmissionNo+"<br>"+result.studentSearchList[i].studentFullName+" </td>"
									
									+"<td class='"+result.studentSearchList[++i].studentId+"' hidden></td>"
									+"<td style='width:160px'> <img src='"+result.studentSearchList[i].studentPhoto+"' width='150px' height='150px' />"+"<br><b>Ad. No</b>"+result.studentSearchList[i].studentAdmissionNo+"<br>"+result.studentSearchList[i].studentFullName+" </td>"
									
									+"</tr>");
							}
					}
					
					else if(result.studentSearchList.length == 4){
						for(var i=0;i<result.studentSearchList.length;i++){
							$("#allstudent tbody").append("<tr>"
									+"<td class='"+result.studentSearchList[i].studentId+"' hidden></td>"
									+"<td style='width:160px'> <img src='"+result.studentSearchList[i].studentPhoto+"' width='150px' height='150px' />"+"<br><b>Ad. No</b>"+result.studentSearchList[i].studentAdmissionNo+"<br>"+result.studentSearchList[i].studentFullName+" </td>"
									
									+"<td class='"+result.studentSearchList[++i].studentId+"' hidden></td>"
									+"<td style='width:160px'> <img src='"+result.studentSearchList[i].studentPhoto+"' width='150px' height='150px' />"+"<br><b>Ad. No</b>"+result.studentSearchList[i].studentAdmissionNo+"<br>"+result.studentSearchList[i].studentFullName+" </td>"
									
									+"<td class='"+result.studentSearchList[++i].studentId+"' hidden></td>"
									+"<td style='width:160px'> <img src='"+result.studentSearchList[i].studentPhoto+"' width='150px' height='150px' />"+"<br><b>Ad. No</b>"+result.studentSearchList[i].studentAdmissionNo+"<br>"+result.studentSearchList[i].studentFullName+" </td>"
									
									+"<td class='"+result.studentSearchList[++i].studentId+"' hidden></td>"
									+"<td style='width:160px'> <img src='"+result.studentSearchList[i].studentPhoto+"' width='150px' height='150px' />"+"<br><b>Ad. No</b>"+result.studentSearchList[i].studentAdmissionNo+"<br>"+result.studentSearchList[i].studentFullName+" </td>"
									
									+"</tr>");
							}
						
					}
					
					else{
					
						 var j=0;
							for(var i=0;i<result.studentSearchList.length-1;i++){
								var count=0;
							
								$("#allstudent tbody").append("<tr id='rowid"+j+"'></tr>");
								for(count=0;count<5;count++){
									if(i==result.studentSearchList.length-1){
										return false;
									}
									$("#allstudent tbody #rowid"+j+"").append("" +
											+"<td class='"+result.studentSearchList[i].studentId+"' hidden></td>" 
											+"<td style='width:160px'> <img src='"+result.studentSearchList[i].studentPhoto+"' width='150px' height='150px' />"+"<br><b>Ad. No</b>"+result.studentSearchList[i].studentAdmissionNo+"<br>"+result.studentSearchList[i].studentFullName+" </td>"
											);
									
									i++;
								}
								i=i-1;
								j++;
								}
							
						
						
					}
					
					
					
			}
					else{
						$("#allstudent tbody").append("<tr><td colspan='5'>No Records Found</td></tr>");
					}
					
					
			}
		});
	}


function getStudentListBySearch(admission,accyear){
	var sectionName=$("#sectionid option:selected").text();
	datalist = {
			
			"admission" :admission,
			"accyear" :accyear,
			
		}, 
		$.ajax({
			type : 'POST',
			url : "adminMenu.html?method=studentPhotosheetSearchList",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
				
					$("#allstudent tbody").empty();
					if(result.studentSearchList.length > 0){
					
					
								$("#allstudent thead").empty();
								$("#allstudent thead").append("<tr>"
										+"<th class='"+result.studentSearchList.studentId+"' hidden></td>" 
										+"<th colspan='5' style='text-align:center'>Student List Admission Number Wise</th>"
										
										+"</tr>");
						
					
					
					if(result.studentSearchList.length == 1){
						for(var i=0;i<result.studentSearchList.length;i++){
							$("#allstudent tbody").append("<tr>"
									+"<td class='"+result.studentSearchList[i].studentId+"' hidden></td>"
									+"<td style='width:160px'> <img src='"+result.studentSearchList[i].studentPhoto+"' width='150px' height='150px' />"+"<br><b>Ad. No</b>"+result.studentSearchList[i].studentAdmissionNo+"<br>"+result.studentSearchList[i].studentFullName+" </td>"
									
									+"</tr>");
							}
						
					}else if(result.studentSearchList.length == 2){
						for(var i=0;i<result.studentSearchList.length;i++){
							$("#allstudent tbody").append("<tr>"
									+"<td class='"+result.studentSearchList[i].studentId+"' hidden></td>"
									+"<td style='width:160px'> <img src='"+result.studentSearchList[i].studentPhoto+"' width='150px' height='150px' />"+"<br><b>Ad. No</b>"+result.studentSearchList[i].studentAdmissionNo+"<br>"+result.studentSearchList[i].studentFullName+" </td>"
									
									+"<td class='"+result.studentSearchList[++i].studentId+"' hidden></td>"
									+"<td style='width:160px'> <img src='"+result.studentSearchList[i].studentPhoto+"' width='150px' height='150px' />"+"<br><b>Ad. No</b>"+result.studentSearchList[i].studentAdmissionNo+"<br>"+result.studentSearchList[i].studentFullName+" </td>"
									
									+"</tr>");
							}
					}else if(result.studentSearchList.length == 3){
						for(var i=0;i<result.studentSearchList.length;i++){
							$("#allstudent tbody").append("<tr>"
									+"<td class='"+result.studentSearchList[i].studentId+"' hidden></td>"
									+"<td style='width:160px'> <img src='"+result.studentSearchList[i].studentPhoto+"' width='150px' height='150px' />"+"<br><b>Ad. No</b>"+result.studentSearchList[i].studentAdmissionNo+"<br>"+result.studentSearchList[i].studentFullName+" </td>"
									
									+"<td class='"+result.studentSearchList[++i].studentId+"' hidden></td>"
									+"<td style='width:160px'> <img src='"+result.studentSearchList[i].studentPhoto+"' width='150px' height='150px' />"+"<br><b>Ad. No</b>"+result.studentSearchList[i].studentAdmissionNo+"<br>"+result.studentSearchList[i].studentFullName+" </td>"
									
									+"<td class='"+result.studentSearchList[++i].studentId+"' hidden></td>"
									+"<td style='width:160px'> <img src='"+result.studentSearchList[i].studentPhoto+"' width='150px' height='150px' />"+"<br><b>Ad. No</b>"+result.studentSearchList[i].studentAdmissionNo+"<br>"+result.studentSearchList[i].studentFullName+" </td>"
									
									+"</tr>");
							}
						
					}
					else if(result.studentSearchList.length == 4){
						for(var i=0;i<result.studentSearchList.length;i++){
							$("#allstudent tbody").append("<tr>"
									+"<td class='"+result.studentSearchList[i].studentId+"' hidden></td>"
									+"<td style='width:160px'> <img src='"+result.studentSearchList[i].studentPhoto+"' width='150px' height='150px' />"+"<br><b>Ad. No</b>"+result.studentSearchList[i].studentAdmissionNo+"<br>"+result.studentSearchList[i].studentFullName+" </td>"
									
									+"<td class='"+result.studentSearchList[++i].studentId+"' hidden></td>"
									+"<td style='width:160px'> <img src='"+result.studentSearchList[i].studentPhoto+"' width='150px' height='150px' />"+"<br><b>Ad. No</b>"+result.studentSearchList[i].studentAdmissionNo+"<br>"+result.studentSearchList[i].studentFullName+" </td>"
									
									+"<td class='"+result.studentSearchList[++i].studentId+"' hidden></td>"
									+"<td style='width:160px'> <img src='"+result.studentSearchList[i].studentPhoto+"' width='150px' height='150px' />"+"<br><b>Ad. No</b>"+result.studentSearchList[i].studentAdmissionNo+"<br>"+result.studentSearchList[i].studentFullName+" </td>"
									
									+"<td class='"+result.studentSearchList[++i].studentId+"' hidden></td>"
									+"<td style='width:160px'> <img src='"+result.studentSearchList[i].studentPhoto+"' width='150px' height='150px' />"+"<br><b>Ad. No</b>"+result.studentSearchList[i].studentAdmissionNo+"<br>"+result.studentSearchList[i].studentFullName+" </td>"
									
									+"</tr>");
							}
						
					}
					
					else{
						 var j=0;
							var count=0;
							for(var i=0;i<result.studentSearchList.length-1;i++){
							
							
								$("#allstudent tbody").append("<tr id='rowid"+j+"'></tr>");
								for(count=0;count<5;count++){
								
									if(i==result.studentSearchList.length){
									
										return false;
									}
									$("#allstudent tbody #rowid"+j+"").append("" +
											+"<td class='"+result.studentSearchList[i].studentId+"' hidden></td>" 
											+"<td style='width:160px'> <img src='"+result.studentSearchList[i].studentPhoto+"' width='150px' height='150px' />"+"<br><b>Ad. No</b>"+result.studentSearchList[i].studentAdmissionNo+"<br>"+result.studentSearchList[i].studentFullName+" </td>"
											);
									
									i++;
									
								}
								i=i-1;
								j++;
								count=0;
								
								}
						
						
						
					}
					
					
					
			}
					else{
						$("#allstudent tbody").append("<tr><td colspan='5'>No Records Found</td></tr>");
					}
					
					
			}
		});
	}