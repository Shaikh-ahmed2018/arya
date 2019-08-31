$(document).ready(function() {
	 $("#send").click(function(){
		 
	dataList={
					"accYear" : $('#Acyearid').val(),
					"locationId":$('#locationname').val(),
					"classId":$('#classname').val(),
					"divisionId":$('#sectionid').val(),
	 },
		 
	 $.ajax({
				type:"POST",
				url:"studentRegistration.html?method=getStudentcontact",
				data:dataList,
				async:false,
				success:function(response){	
					var result = $.parseJSON(response);
					alert($('#Message').val());
					for ( var j = 0; j < result.studentSearchList.length; j++)//Here termlist is the name given on key as JSONObject.
					{
						smsNo = result.studentSearchList[j].smsnumber;
						if(smsNo.length > 0 ){
							$.ajax({
							    type: "POST",
							    url: "http://sms.bbsltvm.in/vendorsms/pushsms.aspx?apikey= 16ba8674-c0ac-4c97-a47e-1376114848ec&clientId=fc9ad79d-2110-41af-a82f-277406ac283a&msisdn="+smsNo+"&sid=ARYACS&msg="+encodeURI($('#Message').val())+"&company="+$('#locationname').val()+"&fl=0&gwid=2",
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
				        $('#message').append("Tick");
				      });
				}
				});
	 alert('successfully send');
	 });
	 $('#send').click(function () {
		  //$('#MobileNO').val('');
		$('#Acyearid').val('');
		$('#locationname').val('');
		$('#classname').val('');
		 $('#sectionid').val('');
		  $('#Message').val('');
		});

	//$(function () { $("input,select,textarea").not("[type=submit]").jqBootstrapValidation(); } );
});

//validation
