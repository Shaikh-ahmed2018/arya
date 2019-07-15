$(document).ready(function(){
	
	
	getRouteNameList();
	$("#route").change(function()
	{		
		var routeid = $(this).val();
		//alert(routeid)var stop=$("#stopname").val();
		if(stop == "%%"){
			stop = "all"
		 }
		getstoplist(routeid);
		 
		
		//getBusWiseStudentDetail();
		
				
});
	$("#stopname").change(function()
			{
		
		getBusWiseStudentDetail();
			});
	
	$("#busSend").click(function(){
		var location=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var routeNo=$("#route").val();
		var stop=$("#stopname").val();
		datalist ={
				"location":location,
				"accyear" :accyear,
				"routeNo":routeNo,
				"stop":stop
				
		};
		
		$.ajax({

			type : "POST",
			url :"transportfeereceipt.html?method=getContactBusStudentDetail",
			data : datalist,
			async:false,
			success:function(data){
				var result = $.parseJSON(data);
				for ( var j = 0; j < result.studentSearchList.length; j++)//Here termlist is the name given on key as JSONObject.
				{
					smsNo = result.studentSearchList[j].mobileNo;
					//console.log("hello"+smsNo);
					
					if(smsNo.length > 0 ){
						$.ajax({
						    type: "POST",
						    url:"http://sms.bbsltvm.in/vendorsms/pushsms.aspx?apikey= 16ba8674-c0ac-4c97-a47e-1376114848ec&clientId=fc9ad79d-2110-41af-a82f-277406ac283a&msisdn="+smsNo+"&sid=ARYACS&msg="+encodeURI($('#BusMessage').val())+"&company="+$('#locationname').val()+"&fl=0&gwid=2",
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
			        $('#BusMessage').append("Tick");
			      });
			}
		})
		 alert('successfully send');
	});
	
	$('#busSend').click(function () {
		  //$('#MobileNO').val('');
		$('#Acyearid').val('');
		$('#locationname').val('');
		$('#route').val('');
		 $('#stopname').val('');
		  $('#BusMessage').val('');
		});
	
	
	
		
});
function getRouteNameList(){
	
	var locationid=$("#locationname").val();

	   datalist = {
	"locationid" : locationid
		},
		//console.log(datalist)
		$.ajax({
			type : 'POST',
			url : "transport.html?method=getRouteNameList",
			data : datalist,
			async : false,
			
			success : function(response) {
				//alert("kii")
				 
				var result = $.parseJSON(response);
				//alert(result)
					$('#route').empty();
					$('#route').append('<option value="ALL">' + "----------Select----------"	+ '</option>');
					for ( var j = 0; j < result.routelist.length; j++) {
                    
						$('#route').append('<option value="'

								+ result.routelist[j].routeCode + '">'

								+ result.routelist[j].routeName

								+ '</option>');
					}
			}
		});
}

function getstoplist(routeid){

	   datalist = {
				"routeid" :routeid,
				"accyear":$("#Acyearid").val()
			}, $.ajax({
				type : 'POST',
				url : "transport.html?method=getstoplist",
				data : datalist,
	 			async : false,
				
				success : function(response) {
					
					var result = $.parseJSON(response);
						//$("#stopname").empty();
						$('#stopname').html("");
						$('#stopname').append('<option value="ALL">'  + "ALL"	+ '</option>');
						for ( var j = 0; j < result.stoplist.length; j++) {
	                       
							$('#stopname').append('<option value="'

									+ result.stoplist[j].stage_id + '">'

									+ result.stoplist[j].stopname

									+ '</option>');
							
						
							
							
							
							
						}
						
				}
			});
	}
function getBusWiseStudentDetail(){
	
	var location=$("#locationname").val();
	var accyear=$("#Acyearid").val();
	var routeNo=$("#route").val();
	var stop=$("#stopname").val();
	
	datalist ={
			"location":location,
			"accyear" :accyear,
			"routeNo":routeNo,
			"stop":stop
			
	};
	console.log(datalist);
	$.ajax({

		type : "POST",
		url :"transportfeereceipt.html?method=getBusWiseStudentDetail",
		data : datalist,
		async:false,
		success:function(data){
			var result = $.parseJSON(data);

			$("#studenttable").empty();
			$("#studenttable").append("<table class='table' id='allstudent' width='100%'" +">"
					+"<tr><th>SI No</th>"+
					"<th>Admission No.</th>" +
					"<th>Name</th>" +
					"<th>Class Div.</th>"+
					"<th>Route</th>"+
					"<th>Buspoint</th>"+
					"<th>Amount</th>"+
					"<th>Contact Person</th>"+
					"<th>Contact No.</th>"+
					"</tr>" +
					"</table>"
			);
			if(result.studentList.length>0)
				{
		
				for(var i=0;i<result.studentList.length;i++){
					

					$("#allstudent").append(
							"<tr>"+
							"<td>"+(i+1)+"</td>"+
							"<td>"+result.studentList[i].admisssion_no+"</td>"+
							"<td>"+result.studentList[i].student_name+"</td>"+
							"<td>"+result.studentList[i].classname+"</td>"+
							"<td>"+result.studentList[i].description+"</td>"+
							"<td>"+result.studentList[i].stage_name+"</td>"+
							"<td>"+result.studentList[i].distance+"</td>"+
							"<td>"+result.studentList[i].costPerPerson+"</td>"+
							"<td>"+result.studentList[i].mobileNo+"</td>"+
							+"</tr>"
					);
				}
				}else{
				$("#allstudent tbody").append("<tr><td colspan=9'>No Record Found</td></tr>");
				}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentList.length);
			
		}
	})
	
}
