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
						smsNo = result.studentSearchList[j].fatherMobileNo;
						if(smsNo.length > 0 ){
							$.ajax({
							    type: "POST",
							    url: "http://sms.textidea.com/app/smsapi/index.php?key=25BB891406C392&campaign=5043&routeid=100233&type=text&contacts="+smsNo+"&senderid=ARYACS&msg="+$('#Message').val()+"&company="+$('#locationname').val(),
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
