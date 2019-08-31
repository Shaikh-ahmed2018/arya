function myFunction() {
	    
			         document.getElementById("myForm").submit();   
				  }


	$(document).ready(function() {
		
		$("#Acyearid").val($("#hacademicyaer").val());
		$("#locationname").change(function(){
			getClassByLoc();
			getDivision();
			getStudentcontactDetails();
			
		});
		
		
		$("#classname").change(function(){
			getDivision();
			getStudentcontactDetails();
			
		});
		
	
		
		$("#sectionid").change(function(){
			getStudentcontactDetails();
		});
		
	
	
	});
	
	function getClassByLoc(){
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
				$('#classname').append('<option value="all">ALL</option>');
				for ( var j = 0; j < result.ClassList.length; j++) {
					$('#classname').append('<option value="'
							+ result.ClassList[j].classcode + '">'
							+ result.ClassList[j].classname
							+ '</option>');
				}
			}
		});
};

function getDivision(){
	datalist={
			"classidVal" : $("#classname").val(),
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
			$('#sectionid').append('<option value="all">ALL</option>');
			for ( var j = 0; j < result.sectionList.length; j++) {
				$('#sectionid').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});
}
	
	
	function getStudentcontactDetails()
	{
		dataList={				
				
				"accYear" : $('#Acyearid').val(),
				"locationId":$('#locationname').val(),
				"classId":$('#classname').val(),
				"divisionId":$('#sectionid').val(),
 };
		
		console.log(dataList);
 $.ajax({
	 type:"POST",
		url:"studentRegistration.html?method=getStudentcontactDetails",
		data:dataList,
		async:false,
		success:function(data){	
			//alert("hiii")
			var result = $.parseJSON(data);
			$("#allstudent tbody").empty();
			if(result.data.length>0){
			
				for(var i=0;i<result.data.length;i++){
					
					$("#allstudent tbody").append("<tr>"
							+"<td>"+(i+1)+"</td>"
							+"<td>"+result.data[i].admissionNo+"</td>"
							+"<td>"+result.data[i].studentFirstName+"</td>"
							+"<td>"+result.data[i].classname+"</td>"
							+"<td>"+result.data[i].sectionnaem+"</td>"
							+"<td>"+result.data[i].smsnumber+"</td>"
							//+"<td>"+result.data[i].termName+"</td>"
							//+"<td>"+result.data[i].dueAmt+"</td>"
							//+"<td>"+result.data[i].name+"</td>"
							+"</tr>");
				}
				
			}
			
		}
	 
 });
 
	}

	
	function validate(){
		  
		var location=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classId=$("#classname").val();
		var section=$("#sectionid").val();
		var message=$("#Message").val();

		if(location=="" && accyear=="" && classId=="" && section=="" && message==""){

			$("#txtstyle, #txtstyle").slideToggle();
		}

		if(location==""){
			$('.errormessagediv').show();
			$('.validateTips').text("Select Location");
			return false;
		}
		else if(accyear==""){
			$('.errormessagediv').show();
			$('.validateTips').text("Select Academic Year");
			return false;
		}
		else if(classId==""){
			$('.errormessagediv').show();
			$('.validateTips').text("Select Class");
			return false;
		}
		else if(section==""){
			$('.errormessagediv').show();
			$('.validateTips').text("Select Section");
			return false;
		}
		else if(message==""){
			$('.errormessagediv').show();
			$('.validateTips').text("Please Enter Your Message");
			return false;
		}
		else{
			return true;
		}
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	