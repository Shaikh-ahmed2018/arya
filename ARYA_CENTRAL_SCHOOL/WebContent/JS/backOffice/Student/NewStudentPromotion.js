var errorMsg=null;
$(document).ready(function() {
	$('#dialog').hide();
	$("#resetbtn").on("click", function (e) {
		$("#locationname,#Acyearid,#classname,#sectionid").val("all");
	});
	
	$('.status').css("background-color", "green");
	
	$('.status').change(function() {
	    var selectedItem = $(this).find("option:selected");
	    $(this).css('backgroundColor', selectedItem.css('backgroundColor'));
	});
	
	$("#addnew").click(function(){
		
		window.location.href="adminMenu.html?method=getStudentForPromotion";

	});
	
	
	$(".promoted").click(function(){
		
		window.location.href="adminMenu.html?method=studentPromotionPage";

	});
	
	$("#search").click(function(){
			var searchval=$('#searchBy').val().trim();
			
			var locationid=$("#locationname").val();
			var accyearid=$("#Acyearid").val();
			var classid=$("#classname").val();
			var sectionid=$("#sectionid").val();
			flag=true;
			
			if(locationid  == "" || locationid  == null || locationid == "all" || locationid == "All") {
				$(".errormessagediv").show();
				$(".validateTips").text("Field Required School Name.");
				$("#locationname").focus();
				document.getElementById("locationname").style.border = "1px solid #AF2C2C";
				document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('#errorhover').fadeOut();
					document.getElementById("locationname").style.border = "1px solid #ccc";
					document.getElementById("locationname").style.backgroundColor = "#fff";
				}, 300);

				flag= false;

			}else if(accyearid  == "" || accyearid  == null || accyearid == "all" || accyearid == "All"){
				$(".errormessagediv").show();
				$(".validateTips").text("Field Required Academic Year.");
				$("#Acyearid").focus();
				document.getElementById("Acyearid").style.border = "1px solid #AF2C2C";
				document.getElementById("Acyearid").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('#errorhover').fadeOut();
					document.getElementById("Acyearid").style.border = "1px solid #ccc";
					document.getElementById("Acyearid").style.backgroundColor = "#fff";
				}, 300);

				flag= false;
			}else if(classid == null || classid == "" || classid == "all" || classid == "All" ){
				$(".errormessagediv").show();
				$(".validateTips").text("Field Required Class.");
				$("#classname").focus();
				document.getElementById("classname").style.border = "1px solid #AF2C2C";
				document.getElementById("classname").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('#errorhover').fadeOut();
					document.getElementById("classname").style.border = "1px solid #ccc";
					document.getElementById("classname").style.backgroundColor = "#fff";
				}, 300);

				flag= false;
			}else if(sectionid == null || sectionid == "" || sectionid == "all" || sectionid == "All"){
				$(".errormessagediv").show();
				$(".validateTips").text("Field Required Division.");
				$("#sectionid").focus();
				document.getElementById("sectionid").style.border = "1px solid #AF2C2C";
				document.getElementById("sectionid").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('#errorhover').fadeOut();
					document.getElementById("sectionid").style.border = "1px solid #ccc";
					document.getElementById("sectionid").style.backgroundColor = "#fff";
				}, 300);

				flag= false;
			}else if(searchval == null || searchval == ""){
				$(".errormessagediv").show();
				$(".validateTips").text("Field Required Search By.");
				$("#searchBy").focus();
				document.getElementById("searchBy").style.border = "1px solid #AF2C2C";
				document.getElementById("searchBy").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('#errorhover').fadeOut();
					document.getElementById("searchBy").style.border = "1px solid #ccc";
					document.getElementById("searchBy").style.backgroundColor = "#fff";
				}, 300);
				flag= false;
			}else{
				StudentPromotingSearchList(locationid,accyearid,classid,sectionid,searchval);
				return flag;
			}
			
	});
	
	var locid=$("#locationname").val();
	
	if(locid == '' || locid == 'all'){
		$("#Acyearid option[value='all']").attr('selected', 'true');
		$("#classname option[value='All']").attr('selected', 'true');
		$("#sectionid option[value='all']").attr('selected', 'true');
	}else{
		var hacademicyear=$('#hacademicyaer').val();
		$("#Acyearid option[value="+hacademicyear+"]").attr('selected', 'true');
	}
	
	
	getClassList();
	
	$("#locationname").change(function(){
		var locationid=$("#locationname").val();
		getClassList();
		var classname=$("#classname").val();
	
		if(locationid == '' || locationid == 'all'){
			$("#Acyearid option[value='all']").attr('selected', 'true');
			$("#classname option[value='All']").attr('selected', 'true');
			$("#sectionid option[value='all']").attr('selected', 'true');
		}
		$('#divIdVal').hide();
	});
	
	$("#Acyearid").change(function(){
		var accyear=$("#Acyearid").val();
		if(accyear == '' || accyear == 'all'){
		
			var classname=$("#classname").val();
		
			$("#classname option[value='All']").attr('selected', 'true');
			$("#sectionid option[value='all']").attr('selected', 'true');
		}else{
		
			var classname=$("#classname").val();
			
		}
		$('#divIdVal').hide();
	});

	$("#classname").change(function(){
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		getSectionList(classname);
		$('#divIdVal').hide();
	});
	
	$("#sectionid").change(function(){
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		if(sectionid == "All" || sectionid == "all"){
			$('#divIdVal').hide();
		}else{
			getStudentClassDivisionWisePromotionList(locationid,accyear,classname,sectionid);
		}
		
	});

	$("#save").click(function(){
		var saveFlag=true;
		
		if(validateFunction()){
			var studentIdArray = [];
			var academicyear_fromArray = [];
			var locationIdArray = [];
			var admissionNoArray = [];
			var rollNoArray = [];
			var class_fromArray = [];
			var section_fromArray = [];
			var specilization_fromArray = [];
			var class_toArray = [];
			var section_toArray = [];
			var specilization_toArray = [];
			var academicyear_toArray = [];
			var statusArray = [];

			var tablecount=1;

			$('table#allstudent tbody tr:gt(0)').each(function() {
				var status=$(this).find('.status').val();
			
				var count = $(this).find('td:nth-child(1) span').attr("class");
				var splitingval=count.split(",");
				var studentId=splitingval[0];
				var academicyear_fromid=splitingval[1];
				var locationId=splitingval[2];
				var academicyear_toid=splitingval[3];
				var admissionno = $(this).find('td:nth-child(3) span').attr("class");
				var rollno = $(this).find('td:nth-child(4) span').attr("class");
				var classId = $(this).find('td:nth-child(5) span').attr("class");
				splitvalue=parseInt(classId.split("D")[1]);
				splitvalue=splitvalue+1;
				var toClass="CCD"+splitvalue;
				
				//toCheckNextClassAvailable(toClass,locationId);
				var sectionId = $(this).find('td:nth-child(6) span').attr("class");
				var specilizationId = $(this).find('td:nth-child(7) span').attr("class");
				
				
				var newsection = $(this).find('.sectionlist').val();
				newspec = $(this).find('.specilizationlist').val();
				if(newspec == ""){
					newspec="-";
				}else{
					newspec;
				}
				studentIdArray.push(studentId);
				academicyear_fromArray.push(academicyear_fromid);
				locationIdArray.push(locationId);
				admissionNoArray.push(admissionno);
				rollNoArray.push(rollno);
				section_fromArray.push(sectionId);
				specilization_fromArray.push(specilizationId);
				class_fromArray.push(classId);
				if(status == "promoted"){
					class_toArray.push(toClass);
					section_toArray.push(newsection);
					specilization_toArray.push(newspec);
				}else{
					class_toArray.push(classId);
					section_toArray.push(sectionId);
					specilization_toArray.push(specilizationId);
				}
				statusArray.push(status);
				academicyear_toArray.push(academicyear_toid);
				
				if(status == 'promoted'){
					if(newsection == '' || newsection == null || newsection == "all"){
						$(".errormessagediv").show();
						$(".errormessagediv .validateTips").text("Field Required New Division.");
						errorMsg="Select the New Division in Row "+tablecount;
						$(this).find(".sectionlist").css({'border-color':'#B70606'});
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
							$(this).find(".sectionlist").fadeOut();
						}, 3000);
						saveFlag=false;
						return false;
					}
				}else{
					saveFlag=true;
					return true;
				}
			
				tablecount++;
				
			});
			var datalist = {
					"studentIdArray" : studentIdArray.toString(),
					"academicyear_fromArray": academicyear_fromArray.toString(),
					"locationIdArray" : locationIdArray.toString(),
					"admissionNoArray": admissionNoArray.toString(),
					"rollNoArray" : rollNoArray.toString(),
					"class_fromArray": class_fromArray.toString(),
					"section_fromArray" : section_fromArray.toString(),
					"specilization_fromArray": specilization_fromArray.toString(),
					"class_toArray" : class_toArray.toString(),
					"statusArray": statusArray.toString(),
					"section_toArray" : section_toArray.toString(),
					"specilization_toArray": specilization_toArray.toString(),
					"academicyear_toArray": academicyear_toArray.toString(),
			};

			if(saveFlag && toCheckNextYearAvailable()){
				showPopup(datalist);
			}else{
				$(".errormessagediv").show();
				$(".validateTips").text(errorMsg);
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
			}
		}
		
	});
	

	$("#back").click(function(){
		
		window.location.href="adminMenu.html?method=NewstudentPromotionList";

	});
});

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

			$('#classname').append('<option value="all">' + "----------Select----------"	+ '</option>');

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
			
			$('#sectionid').append('<option value="All">' + "----------Select----------"	+ '</option>');
			
			for ( var j = 0; j < result.sectionList.length; j++) {

				$('#sectionid').append('<option value="'

						+ result.sectionList[j].sectioncode + '">'

						+ result.sectionList[j].sectionnaem

						+ '</option>');
			}
			
		}
		
		
	});
}

function getClassSection(columnValue, locationId,sectionname) {
	
	var datalist = {
		"classidVal" : columnValue,
		"locationId":locationId,
	}; 
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('.sectionlist').empty();
			$('.sectionlist').append('<option value="all">' + "-----Select-----" + '</option>');

			for ( var j = 0; j < result.sectionList.length; j++) {
				$('.sectionlist').append(
						'<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
			
			$('.sectionlist option').map(function () {
			    if ($(this).text() == sectionname) return this;
			}).attr('selected', 'selected');
		}
	});

}

function getClassSpecilization(columnValue,locationId){
	var data = {
			"classId" : columnValue,
			"locationId":locationId,
	};
	$.ajax({
		type : 'POST',
		url : "specialization.html?method=getSpecializationOnClassBased",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('.specilizationlist').empty();
			$('.specilizationlist').append('<option value="' + "" + '">'+ "----------Select----------" + '</option>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('.specilizationlist').append(
						'<option value="'
						+ result.jsonResponse[j].spec_Id
						+ '">'
						+ result.jsonResponse[j].spec_Name
						+ '</option>');
			}


		}
	});
}

function getStudentClassDivisionWisePromotionList(locationid,accyear,classname,sectionid){
	
	var data = {
			"locationId":locationid,
			"accyear" : accyear,
			"classId" : classname,
			"sectionid" : sectionid,
	};
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getStudentClassDivisionWisePromotionList",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#divIdVal').show();
			$('#allstudent').empty();
			$("#allstudent").append("<tr>"
					+"<th>Sl No</th>"
					+"<th>Student Name</th>"
					+"<th>Admission No</th>"
					+"<th>Roll No.</th>"
					+"<th>Class</th>"
					+"<th>Division</th>"
					+"<th>Specilization</th>"
					+"<th>Status</th>"
					+"<th>New Division</th>"
					+"<th>New Specilization</th>"
					+"</tr>");
			
			for(var i=0;i<result.studentdetailslist.length;i++){
				$("#allstudent").append("<tr>"
						+"<td><span class='"+result.studentdetailslist[i].studentId+","+result.studentdetailslist[i].currentAccyearId+","+result.studentdetailslist[i].locationId+","+result.studentdetailslist[i].academicYearId+"'></span>"+result.studentdetailslist[i].count+"</td>" 
						+"<td>"+result.studentdetailslist[i].studentFullName+"</td>"
						+"<td><span class='"+result.studentdetailslist[i].studentAdmissionNo+"'></span>"+result.studentdetailslist[i].studentAdmissionNo+"</td>"
						+"<td><span class='"+result.studentdetailslist[i].studentrollno+"'></span>"+result.studentdetailslist[i].studentrollno+"</td>"
						+"<td><span class='"+result.studentdetailslist[i].classcode+"'></span>"+result.studentdetailslist[i].classname+"</td>"
						+"<td><span class='"+result.studentdetailslist[i].sectioncode+"'></span>"+result.studentdetailslist[i].sectionnaem+"</td>"
						+"<td><span class='"+result.studentdetailslist[i].specilization+"'></span>"+result.studentdetailslist[i].specilizationname+"</td>"
						+"<td><select class='status' style='color: white; font-weight: 700; background-color: rgb(0, 128, 0);'><option value='promoted' style='background-color:green;'>Promoted</option><option value='demoted' style='background-color:red;'>Demoted</option></select></td>"
						+"<td><select class='sectionlist'><option value='all'>----------Select----------</option></select></td>"
						+"<td><select class='specilizationlist'><option value='all'>----------Select----------</option></select><span class='settingpromotion'><img src='./images/right arrow.png'></span></td>"
						+"</tr>");
				
				classid =result.studentdetailslist[i].classcode;
				sectionname=result.studentdetailslist[i].sectionnaem;
				//alert(sectionname);
				splitvalue=parseInt(classid.split("D")[1]);
				splitvalue=splitvalue+1;
				classid="CCD"+splitvalue;
				var locationId=$('#hschoolLocation').val();
				
				getClassSection(classid, locationId,sectionname);
				 
				getClassSpecilization(classid,locationid);
				//getTableChangeData();
				$(".status").change(function(){
					if($(this).val()=="demoted"){
						$(this).closest("tr").find(".sectionlist").prop("disabled",true);
						$(this).closest("tr").find(".sectionlist").hide();
						$(this).closest("tr").find(".specilizationlist").prop("disabled",true);
						$(this).closest("tr").find(".specilizationlist").css({'opacity':'0'});
						$(this).css({'background-color':'#f00'});
					}
					else{
						$(this).css({'background-color':'rgb(0, 128, 0)'});
						$(this).closest("tr").find(".sectionlist").prop("disabled",false);
						$(this).closest("tr").find(".sectionlist").show();
						$(this).closest("tr").find(".specilizationlist").prop("disabled",false);
						$(this).closest("tr").find(".specilizationlist").css({'opacity':'1'});
					}
				});
			}
			getEachRowRecord();
			if ($(this).text() == sectionname) {
		        $(this).attr('selected', 'selected');
		        return false;
		    }
			
		}
	
	});
}

function toCheckNextClassAvailable(toClass,locationId){
	var flag = true;
	var data = {
			"toClass" : toClass,
			"locationId":locationId,
	};
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=toCheckNextClassAvailable",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			if(result.checkClass == "notpresent"){
				$(".errormessagediv").show();
				$(".validateTips").text("The Promotion of Next Class doesn't Exits In Current Location.");
				setTimeout(function() {
					$('#errorhover').fadeOut();
				}, 1000);
				
				flag=false; 
			}else{
				flag=true;
			}
		}
	});
	return flag;
}

/*function getTableChangeData(){
	$('table#allstudent tbody tr:gt(0)').each(function() {
		var splitvalue=0;
		pointersec=$(this).find(".sectionlist");
		pointer=$(this).find(".specilizationlist");
		
		var columnValue = $(this).find('td:nth-child(4) span').attr("class");
		splitvalue=parseInt(columnValue.split("D")[1]);
		splitvalue=splitvalue+1;
		columnValue="CCD"+splitvalue;
		var locationId=$('#hschoolLocation').val();
		//toCheckNextClassAvailable(columnValue,locationId);
		getClassSection(columnValue, locationId,pointersec);
		getClassSpecilization(columnValue,locationId,pointer);
	});
}*/

function getEachRowRecord(){
	$(".settingpromotion").click(function(){
		var count = $(this).closest('tr').find('td:nth-child(1) span').attr("class");
		var splitingval=count.split(",");
		var studentId=splitingval[0];
		var academicyearid=splitingval[1];
		var locationId=splitingval[2];
		
		window.location.href="adminMenu.html?method=studentPromotionPage&studentId="+studentId+"&accyear="+academicyearid+"&locationId="+locationId;
	
	});
}

function StudentPromotingSearchList(locationid,accyear,classname,sectionid,searchval){
	
	var data = {
			"locationId":locationid,
			"accyear" : accyear,
			"classId" : classname,
			"sectionid" : sectionid,
			"searchname" : searchval,
	};
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getStudentPromotingSearchList",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#divIdVal').show();
			$('#allstudent').empty();
			$("#allstudent").append("<tr>"
					+"<th>Sl No</th>"
					+"<th>Student Name</th>"
					+"<th>Admission No</th>"
					+"<th>Roll No.</th>"
					+"<th>Class</th>"
					+"<th>Division</th>"
					+"<th>Specilization</th>"
					+"<th>Status</th>"
					+"<th>New Division</th>"
					+"<th>New Specilization</th>"
					+"</tr>");
			
			for(var i=0;i<result.StudentPromotingSearchList.length;i++){
				$("#allstudent").append("<tr>"
						+"<td><span class='"+result.StudentPromotingSearchList[i].studentId+","+result.StudentPromotingSearchList[i].currentAccyearId+","+result.StudentPromotingSearchList[i].locationId+","+result.StudentPromotingSearchList[i].academicYearId+"'></span>"+result.StudentPromotingSearchList[i].count+"</td>" 
						+"<td>"+result.StudentPromotingSearchList[i].studentFullName+"</td>"
						+"<td><span class='"+result.StudentPromotingSearchList[i].studentAdmissionNo+"'></span>"+result.StudentPromotingSearchList[i].studentAdmissionNo+"</td>"
						+"<td><span class='"+result.StudentPromotingSearchList[i].studentrollno+"'></span>"+result.StudentPromotingSearchList[i].studentrollno+"</td>"
						+"<td><span class='"+result.StudentPromotingSearchList[i].classcode+"'></span>"+result.StudentPromotingSearchList[i].classname+"</td>"
						+"<td><span class='"+result.StudentPromotingSearchList[i].sectioncode+"'></span>"+result.StudentPromotingSearchList[i].sectionnaem+"</td>"
						+"<td><span class='"+result.StudentPromotingSearchList[i].specilization+"'></span>"+result.StudentPromotingSearchList[i].specilizationname+"</td>"
						+"<td><select class='status' style='color: white; font-weight: 700; background-color: rgb(0, 128, 0);'><option value='promoted' style='background-color:green;'>Promoted</option><option value='demoted' style='background-color:red;'>Demoted</option></select></td>"
						+"<td><select class='sectionlist'><option value='all'>----------Select----------</option></select></td>"
						+"<td><select class='specilizationlist'><option value='all'>----------Select----------</option></select><span class='settingpromotion'><img src='./images/right arrow.png'></span></td>"
						+"</tr>");
				
				classid =result.StudentPromotingSearchList[i].classcode;
				sectionname =result.StudentPromotingSearchList[i].sectionnaem;
				splitvalue=parseInt(classid.split("D")[1]);
				splitvalue=splitvalue+1;
				classid="CCD"+splitvalue;
				var locationId=$('#hschoolLocation').val();
				
				getClassSection(classid, locationId,sectionname);
				
				getClassSpecilization(classid,locationId);
				//getTableChangeData();
			}
			getEachRowRecord();
		}
	
	});
}
function validateFunction(){
	var locationid=$('#locationname').val();
	var accyearid=$('#Acyearid').val();
	var classid=$('#classname').val();
	var sectionid=$('#sectionid').val();
	var flag=true;
	if(locationid  == "" || locationid  == null || locationid == "all" || locationid == "All") {
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required School Name.");
		$("#locationname").focus();
		document.getElementById("locationname").style.border = "1px solid #AF2C2C";
		document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("locationname").style.border = "1px solid #ccc";
			document.getElementById("locationname").style.backgroundColor = "#fff";
		}, 300);

		flag= false;

	}else if(accyearid  == "" || accyearid  == null || accyearid == "all" || accyearid == "All"){
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Academic Year.");
		$("#Acyearid").focus();
		document.getElementById("Acyearid").style.border = "1px solid #AF2C2C";
		document.getElementById("Acyearid").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("Acyearid").style.border = "1px solid #ccc";
			document.getElementById("Acyearid").style.backgroundColor = "#fff";
		}, 300);

		flag= false;
	}else if(classid == null || classid == "" || classid == "all" || classid == "All" ){
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Class.");
		$("#classname").focus();
		document.getElementById("classname").style.border = "1px solid #AF2C2C";
		document.getElementById("classname").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("classname").style.border = "1px solid #ccc";
			document.getElementById("classname").style.backgroundColor = "#fff";
		}, 300);

		flag= false;
	}else if(sectionid == null || sectionid == "" || sectionid == "all" || sectionid == "All"){
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Division.");
		$("#sectionid").focus();
		document.getElementById("sectionid").style.border = "1px solid #AF2C2C";
		document.getElementById("sectionid").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("sectionid").style.border = "1px solid #ccc";
			document.getElementById("sectionid").style.backgroundColor = "#fff";
		}, 300);

		flag= false;
	}else{
		return flag;
	}
}

function showPopup(datalist){
	$("#dialog").dialog({
		 autoOpen: true,
	     modal: true,
	     position: {my: "center middle",
            at: "center middle",
           },
	     title:'Save Bulk Promotion Details',
	    
	     buttons : {
	    	 "Yes" : function() {
	    		 $.ajax({

	    			 type : 'POST',
	    			 url : 'studentRegistration.html?method=saveStudentPromotion',
	    			 data : datalist,

	    			 beforeSend: function() {

	    				 $('#loader').show();

	    			 },
	    			 success:function(response){
	    				 var result = $.parseJSON(response);

	    				 $('#loader').hide();

	    				 if(result.expense_Result == "success"){
	    					 $('.successmessagediv').show();
	    					 $('.validateTips').css({
	    						 'visibility' : 'visible',
	    					 });
	    					 $('.validateTips').text("Adding Record Progressing...");


	    					 setTimeout(function () {

	    						 window.location.href ="adminMenu.html?method=NewstudentPromotionList";
	    					 }, 3000);


	    				 }
	    				 else{
	    					 $('.errormessagediv').show();
	    					 $('.validateTips').css({
	    						 'visibility' : 'visible'
	    					 });
	    					 $('.validateTips').text("Please try again, after some time");
	    					 $('.errormessagediv').delay(3000).slideUp("slow");
	    				 }	
	    			 }
	    		 });
	    		 $(this).dialog("close");
	    	 },
	    	 "No" : function() {
	    		 $(this).dialog("close");
	    	 }
	     }
	});
}

function toCheckNextYearAvailable(){
	var accyearid=$('#Acyearid').val();
	var flag = true;
	var data = {
			"AccYear" : accyearid,
	};
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=toCheckNextYearAvailable",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
		
			if(result.checkNextYear.trim() == "Next Year Not Avaliable"){
				
				errorMsg="Next Academic Year Is Not Found, Please Create.";
								
				flag=false; 
			}else{
				flag=true;
			}
		}
	});
	return flag;
}

