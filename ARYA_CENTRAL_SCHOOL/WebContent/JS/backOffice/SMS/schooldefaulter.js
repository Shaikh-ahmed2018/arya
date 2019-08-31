function myFunction() {
	    
			         document.getElementById("myForm").submit();   
				  }


	$(document).ready(function() {
		$("#Acyearid").val($("#hacademicyaer").val());
		/*$("#Acyearid").change(function(){
			getClassList();
			getTerm();
			getSectionList(classname);
			getDefaulterFeeList();
			var classname=$("#classname").val();
			});*/
		
		$("#locationname").change(function(){

				//getClassList();
				//getSectionList(classname);
				getTerm();
				getDefaulterFeeList();
				var classname=$("#classname").val();
			
		});
		$("#classname").change(function(){
			
			
			var locationid=$("#locationname").val();
			var accyear=$("#Acyearid").val();
			var classname=$("#classname").val();
			var sectionid=$("#sectionid").val();
			getSectionList(classname);
			getDefaulterFeeList();
			
		});
		$("#sectionid").change(function(){
			$("#searchvalue").val("");
			var locationid=$("#locationname").val();
			var accyear=$("#Acyearid").val();
			var classname=$("#classname").val();
			var sectionid=$("#sectionid").val();
			getDefaulterFeeList();
			
		});
		$("#termName").change(function(){
			getDefaulterFeeList();
		});
		 $("#desend").click(function(){
			 dataList={
						"accYear" : $('#Acyearid').val(),
						"locationId":$('#locationname').val(),
						"classId":$('#classname').val(),
						"divisionId":$('#sectionid').val(),
						"termId":$('#termName').val(),
		 },
		 console.log(dataList);
		 $.ajax({
			 
				type:"POST",
				url:"studentRegistration.html?method=getdefaStudentcontact",
				data:dataList,
				async:false,
				success:function(response){	
					var result = $.parseJSON(response);
					//alert("hiii"+result)
					//alert($('#Message').val());
					for ( var j = 0; j < result.studentSearchList.length; j++)//Here termlist is the name given on key as JSONObject.
					{
						smsNo = result.studentSearchList[j].smsnumber;
						//console.log("hello"+smsNo);
						
						if(smsNo.length > 0 ){
							$.ajax({
							    type: "POST",
							    url:"http://sms.bbsltvm.in/vendorsms/pushsms.aspx?apikey= 16ba8674-c0ac-4c97-a47e-1376114848ec&clientId=fc9ad79d-2110-41af-a82f-277406ac283a&msisdn="+smsNo+"&sid=ARYACS&msg="+encodeURI($('#Message').val())+"&company="+$('#locationname').val()+"&fl=0&gwid=2",
							    success: function() {							     
							    }
						 	});
						}
					}
					
					 $('#contact_form_message').html("<div id='message'></div>");
				      $('#message').html("<h2>Contact Form Submitted!</h2>")
				      .append("<p>We will be in touch soon.</p>")
				      .hide()
				      .fadeIn(1500, function() {
				        $('#Message').append("Tick");
				      });
				}
		 });
			 alert('successfully send');
			 
		 });
		 $('#desend').click(function () {
			  //$('#MobileNO').val('');
			$('#Acyearid').val('');
			$('#locationname').val('');
			$('#classname').val('');
			 $('#sectionid').val('');
			 $('#termName').val('');
			  $('#Message').val('');
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

				$('#classname').append('<option value="">' + "ALL"	+ '</option>');

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
				
				$('#sectionid').append('<option value="">' + "ALL"	+ '</option>');
				
				for ( var j = 0; j < result.sectionList.length; j++) {

					$('#sectionid').append('<option value="' + result.sectionList[j].sectioncode
							+ '">' + result.sectionList[j].sectionnaem
							+ '</option>');
				}
			}
		});}

	function getDefaulterFeeList(){
		datalist ={"locId":$("#locationname").val(),
					"classId":$("#classname").val(),
					"divId":$("#sectionid").val(),
					"termId":$("#termName").val(),
					"accId":$("#Acyearid").val(),
			};
		console.log(datalist);
		$.ajax({
			type:"post",
			url:"feecollection.html?method=getDefaulterFeeList",
			data:datalist,
			async:false,
			success:function(data){
				var result = $.parseJSON(data);
				$("#allstudent tbody").empty();
				if(result.data.length>0){
					 totAmount=0.0;
					
				for(var i=0;i<result.data.length;i++){
					totAmount+=Number(result.data[i].dueAmt);
					
					$("#allstudent tbody").append("<tr>"
							+"<td>"+(i+1)+"</td>"
							+"<td>"+result.data[i].admissionNo+"</td>"
							+"<td>"+result.data[i].studentName+"</td>"
							+"<td>"+result.data[i].locationName+"</td>"
							+"<td>"+result.data[i].className+"</td>"
							+"<td>"+result.data[i].divisionName+"</td>"
							+"<td>"+result.data[i].termName+"</td>"
							+"<td>"+result.data[i].dueAmt+"</td>"
							+"<td>"+result.data[i].name+"</td>"
							
							+"</tr>");
					}
				
				
				}
				else{
					$("#allstudent tbody").append("<tr><td colspan='9'>No Record Found</td></tr>");
				}
			}
		});
	 }
	
	function getTerm(){
		datalist={
				"locId" : $("#locationname").val(),
				"accId" : $("#Acyearid").val(), 
		},
		console.log(datalist)
		$.ajax({
			type : 'POST',
			url : "reportaction.html?method=getTerm",
			data : datalist,
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);
				var j;
				var len= result.data.length;
				$('#termName').html("");
				$('#termName').append('<option value="">' +"-----Select-----"+ '</option>');
				for ( j = 0; j <len; j++) {
					$('#termName').append('<option value="'
							+ result.data[j].termname+ '">'
							+ result.data[j].termId
							+ '</option>');
				}
			}
		});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	