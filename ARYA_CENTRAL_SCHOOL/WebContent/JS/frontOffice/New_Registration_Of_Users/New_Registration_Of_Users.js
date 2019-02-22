$(document).ready(function() {
	
	var pageUrl1=window.location.href.substring(window.location.href.lastIndexOf("&")+1);
	var approveUrl=window.location.href.substring(window.location.href.lastIndexOf("?")+1);
	var rejectUrl=window.location.href.substring(window.location.href.lastIndexOf("?")+1);
	
	if(approveUrl.split("=")[1]=="searchapprove&searchname"){
		$("#approvedlink").attr("style","display:block");
		$(".tab-pane").not("#approvedlink").attr("style","display:none");
		$("li").removeClass("active");
		$("li").find("a[href='#approvedlink']").parent("li").addClass("active");
	}
	if(rejectUrl.split("=")[1]=="searchreject&searchname"){
		$("#rejected").attr("style","display:block");
		$(".tab-pane").not("#rejected").attr("style","display:none");
		$("li").removeClass("active");
		$("li").find("a[href='#rejected']").parent("li").addClass("active");
	}
	if(rejectUrl.split("=")[1]=="searchcancel&searchname"){
		$("#cancelled").attr("style","display:block");
		$(".tab-pane").not("#cancelled").attr("style","display:none");
		$("li").removeClass("active");
		$("li").find("a[href='#cancelled']").parent("li").addClass("active");
	}
	
	
	
	if(rejectUrl.split("=")[1]=="searchsubmit&searchname"){
		$("#submitted").attr("style","display:block");
		$(".tab-pane").not("#submitted").attr("style","display:none");
		$("li").removeClass("active");
		$("li").find("a[href='#submitted']").parent("li").addClass("active");
	
	}
	
	
	if(rejectUrl.split("=")[1]=="searchprocess&searchname"){
		$("#processed").attr("style","display:block");
		$(".tab-pane").not("#processed").attr("style","display:none");
		$("li").removeClass("active");
		$("li").find("a[href='#processed']").parent("li").addClass("active");
	}
	if(pageUrl1=="Approved"){
		$("#approvedlink").attr("style","display:block");
		$(".tab-pane").not("#approvedlink").attr("style","display:none");
		$("li").removeClass("active");
		$("li").find("a[href='#approvedlink']").parent("li").addClass("active");
	}
	if(pageUrl1=="Rejected"){
		$("#rejected").attr("style","display:block");
		$(".tab-pane").not("#rejected").attr("style","display:none");
		$("li").removeClass("active");
		$("li").find("a[href='#rejected']").parent("li").addClass("active");
	}
     if(pageUrl1=="Cancelled"){
		$("#cancelled").attr("style","display:block");
		$(".tab-pane").not("#cancelled").attr("style","display:none");
		$("li").removeClass("active");
		$("li").find("a[href='#cancelled']").parent("li").addClass("active");
	  }
     if(pageUrl1=="Submitted"){
 		$("#submitted").attr("style","display:block");
 		$(".tab-pane").not("#submitted").attr("style","display:none");
 		$("li").removeClass("active");
 		$("li").find("a[href='#submitted']").parent("li").addClass("active");
 		location.reload();
 	  }
	
	$(".issued").click(function(){
		$("#issuedOne").slideToggle();
	});
	
	$(".class").click(function(){
		$("#classOne").slideToggle();
	});
	$(".reject").click(function(){
		$("#rejectedOne").slideToggle();
	});
	$(".cancelled").click(function(){
		$("#cancelledOne").slideToggle();
	});
	
	$(".submitted").click(function(){
		$("#submittedOne").slideToggle();
	});
	
	$(".processed").click(function(){
		$("#processedOne").slideToggle();
	});
	
	

	$("#stream").val($("#hiddenStreamId").val());
	$("#class").val($("#updateClassCode").val());
	var hstream = $("#hiddenStreamId").val();
	$("#stu_parrelation option[value='"+$("#hiddenrelationname").val()+"']").attr('selected','true');


	if(hstream!=undefined && hstream!=""){

		$("#stream option[value='"+hstream+"']").attr('selected','true');

		var streamId=$("#stream").val();


		$.ajax({

			type : "GET",
			url : "registration.html?method=getClassesByStream",
			data : {"streamId":streamId},
			async : false,
			success : function(data) {

				var result = $.parseJSON(data);

				$("#class").append(
						'<option value="' + "" + '">' + "----------select----------"
						+ '</option>');
				for (var j = 0; j < result.ClassesList.length; j++) {

					$("#class").append(
							'<option value="'
							+ result.ClassesList[j].classId
							+ '">'
							+ result.ClassesList[j].classname
							+ '</option>');
				}

			}

		});

		$("#class option[value='"+$("#updateClassCode").val()+"']").attr('selected','true');


	}
	$("#location").change(function(){
		getStream($(this));
	});
	$("#stream").change(function(){

		var streamId=$("#stream").val();

		$.ajax({

			type : "GET",
			url : "registration.html?method=getClassesByStream",
			data : {"streamId":streamId},
			async : false,
			success : function(data) {

				var result = $.parseJSON(data);
				$("#class").html("");
				$("#class").append(
						'<option value="' + "" + '">' + "----------Select----------"
						+ '</option>');
				for (var j = 0; j < result.ClassesList.length; j++) {

					$("#class").append(
							'<option value="'
							+ result.ClassesList[j].classId
							+ '">'
							+ result.ClassesList[j].classname
							+ '</option>');
				}

			}

		});

	});

	$("#selectall").change(function(){

		$(".select").prop('checked', $(this).prop("checked"));


	});
	
	$("#searchprocess").keydown(function(event) {
		var searchname=$("#searchprocess").val();

		if (event.keyCode == 13) {
			window.location.href = "adminMenu.html?method=searchprocess&searchname="+searchname;
		}

	});
	$('#searchpess')
	.click(
			function() {
				var searchname = $("#searchprocess").val().trim();
				window.location.href = "adminMenu.html?method=searchprocess&searchname="+searchname;
			});
	

	$("#save").click(function()  
			{
		var enq_code = $("#updateenquiryid").val().trim();
		var parentfirstName=$("#parentfirstName").val().trim();
		var parent_LastName=$("#parent_LastName").val().trim();

		var parents_name=$("#parents_name").val().trim();

		var parentEmailId=$("#parentEmailId").val().trim();
		var address=$("#address").val().trim();
		var stu_parrelation=$("#stu_parrelation").val().trim();
		var mobile_number=$("#mobile_number").val().trim();
		var stream=$("#stream").val().trim();
		var classid=$("#class").val();
		
		if(parentfirstName==""){

			$('.errormessagediv').show();
			$('.validateTips').text("Field  Required First Name");
		}
		else  if(parent_LastName=="")
		{
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required Last Name");
		}
		else if(parentEmailId=="")
		{
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required Email");
		}

		else if (!parentEmailId.match(/^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/)) {

			$('.errormessagediv').show();
			$('.validateTips').text("Enter Valid Email");
		}

		else if(stream=="")
		{
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required Stream");

		}
		else if(classid=="")
		{
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required Class");
		}

		else if(mobile_number.length<10)
		{
			$(".errormessagediv").show();

			$(".validateTips").text("Enter 10 digit Mobile no");
			$("#mobile_number").focus();


			document.getElementById("mobile_number").style.border = "1px solid #AF2C2C";
			document.getElementById("mobile_number").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			}, 3000);

		}

		else if (!mobile_number.match(/^(?!0{6})([0-9])+$/i)) {
			$(".errormessagediv").show();

			$(".validateTips").text("Enter Valid Mobile");
			$("#mobile_number").focus();

            
			document.getElementById("mobile_number").style.border = "1px solid #AF2C2C";
			document.getElementById("mobile_number").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			}, 3000);

		}
		else if(stu_parrelation == "" || stu_parrelation == null){
			$(".errormessagediv").show();

			$(".validateTips").text("Select the Relationship");
			$("#stu_parrelation").focus();
			document.getElementById("stu_parrelation").style.border = "1px solid #AF2C2C";
			document.getElementById("stu_parrelation").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			},100);
			
			
		}
		
	

		else
		{
			var datalist = {
					"parentfirstName" : parentfirstName,
					"parent_LastName" : parent_LastName,
					"parents_name" : parents_name,
					"parentEmailId" : parentEmailId,
					"address" : address,
					"stu_parrelation" : stu_parrelation,
					"mobile_number" : mobile_number,
					"stream" : stream,
					"classid" : classid,
					"enq_code" : enq_code,
				
			};


			$.ajax({

				type : "POST",

				url : "adminMenu.html?method=updateissuelist",

				data : datalist,

				success : function(data) {
					var result = $.parseJSON(data);
					$(".errormessagediv").hide();
					if(result.status == "updated successfully"){
				        $(".successmessagediv").show();
						$(".validateTips").text("Updating Record Progressing...");
						setTimeout(function(){
							window.location.href="adminMenu.html?method=tempadmissionMenu";
                           },3000);
					}
				}



			});
		}

			});

	
	$("#back2").click(function()
			{
		window.location.href="adminMenu.html?method=tempadmissionMenu";
			}); 

	$("#cancelled").click(function()
			{
        $("#dialog3").dialog("open");
		$("#dialog3").empty();
		$("#dialog3").append("<p>Are you sure to Cancel?</p>");
		$("#dialog3").append('<label for="">Reason:</label>');
		$("#dialog3").append('<select name="canreason" id="canreason">'
				+ '<option value="">' + "----------select----------"
				+ '</option>'
				+ '<option value="submitted delay">' + "Submitted delay"
				+ '</option>'
				+ '<option value="date closed">' + "Date closed" 
				+ '</option>'
				+ '<option value="others">' + "Others" 
				+ '</option>'+
		'</select>');
		  $("#dialog3").append('<div id="othreason"+ <label for="">OtherReason:</label>'+'<input type="text" name=other id="otherreason"/></div>');
     
  		  $("#othreason").hide();
  		  $('#canreason').change(function(){
  			$(".errormessagediv").hide();
  			var othersres=$('#canreason').val();
  			if(othersres == 'others'){
  				$("#othreason").show();
  			}else{
  				$("#otherreason").val("");
  				$("#othreason").hide();
  				$("#canreason").show();
  			}
  		});
  	  reason = $("#canreason").val();
	});


	$("#dialog3").dialog({

		autoOpen: false,
		modal: true,
		title:'Cancel Details',
		buttons : {
			"Yes" : function() {
                  var cancelreason=$("#canreason").val();
                  if(cancelreason== "" || cancelreason==null)
                	  {
                	    $(".errormessagediv").show();
		     		    $(".validateTips").text("Select Any One Reason");
		     		    setTimeout(function() {
		  				$('#errormessagediv').fadeOut();
		  			    },10);
                	  }
                  else
                	  {
                	  
                	  
				      datalist ={
						"enquiryIdlist" :enquiryIdlist,
						"resn":$("#canreason").val(),
						"cancelreasn":$("#otherreason").val(),
						
				};

				$.ajax({

					type : "POST",

					url :"parentrequiresappointment.html?method=insertcancelledlist",

					data : datalist,

					success : function(data)

					{ 
						var result = $.parseJSON(data);
						$(".errormessagediv").hide();
						if(result.status == "Cancelled"){
							$(".successmessagediv").show();
							$(".validateTips").text("Cancelling Record Progressing...");
							setTimeout(function(){
								window.location.href="adminMenu.html?method=tempadmissionMenu&"+result.status;

							},4000);


						}


					}


				});
				$(this).dialog("close");
                	  }
			},
			"No" : function() {
				$(this).dialog("close");
			}

		}

	});
	$(".buttonstyle").click(function()
			{
		var currentid= this.id;
		var curenquiryid =currentid;
		window.location.href="adminMenu.html?method=issuedformEdit&curenquiryid="+curenquiryid;

			});

	$(".apprbuttonstyle").click(function()
			{
		var currentid= this.id;
		var curenquiryid =currentid;
		window.location.href="adminMenu.html?method=ApprformDetails&curenquiryid="+curenquiryid;

			});
	$(".rjtbuttonstyle").click(function()
			{
		var currentid= this.id;
		var curenquiryid =currentid;
		window.location.href="adminMenu.html?method=RejectformDetails&curenquiryid="+curenquiryid;

			});

   $(".cancelbuttonstyle").click(function()
			{
		var currentid= this.id;
		var curenquiryid =currentid;
		window.location.href="adminMenu.html?method=CancelformDetails&curenquiryid="+curenquiryid;

			});
   $(".submitbuttonstyle").click(function()
			{
	var currentid= this.id;
	var curenquiryid =currentid;
	window.location.href="adminMenu.html?method=submitformDetails&curenquiryid="+curenquiryid;
			});
   
   $(".processbuttonstyle").click(function()
			{
	var currentid= this.id;
	var curenquiryid =currentid;
	window.location.href="adminMenu.html?method=processformDetails&curenquiryid="+curenquiryid;
			});

	$("#edit").click(function()
			{
		$(".successmessagediv").hide();

		var cnt = 0;


		$('input[type="checkbox"]:checked')
		.map(
				function() {



					getData = $(this).attr("id");
					cnt++;
				});


		if (cnt == 0 || cnt > 1) {

			$(".errormessagediv").show();
			$(".validateTips").text("Select One Record");

			return false;
		} 

		else
		{

			var enquiryid = getData;
			window.location.href ="adminMenu.html?method=EditissuedList&enquiryid="+enquiryid;
		}
			});

	$("#save3").click(function()
			{
		window.location.href="adminMenu.html?method=updateissuelist";
			});
	$("#Back").click(function()
			{
		window.location.href="adminMenu.html?method=tempadmissionMenu";	
			});

	
	var enquiryIdlist=$("#updateenquiryid").val();

	$("#approve").click(function(){
		$("#dialog").dialog("open");
		$("#dialog").empty();
		$("#dialog").append("<p>Are you sure to Approve?</p>");
		$("#dialog").append('<label for="">Reason:</label>');
		$("#dialog").append('<select name="reason" id="reason">'
                + '<option value="">' + "----------select----------"
        		+ '</option>'
				+ '<option value="selectedcandidate">' + "Selected Candidate" 
				+ '</option>'
				+ '<option value="ready for next approval">' + "Ready for next approval" 
				+ '</option>'
				+ '<option value="others">' + "Others" 
				+ '</option>'+
		'</select>') ;
		+'<br/><br/><br/>';
		$("#dialog").append('<div id="othreason"+ <label for="">OtherReason:</label>'+'<input type="text" name=other id="otherreason"/></div>');
		
		$("#othreason").hide();
		$('#reason').change(function(){
			$(".errormessagediv").hide();
			var othersres=$('#reason').val();
			if(othersres == 'others'){
				$("#othreason").show();
			}else{
				$("#otherreason").val("");
				$("#othreason").hide();
				$("#reason").show();
			}
		});
	    reason = $("#reason").val();
	});


	$("#dialog").dialog({

		autoOpen: false,
		modal: true,
		title:'Approve Details',
		buttons : {
			"Yes" : function() {
				var reasonname=$('#reason').val();
				
			  if(reasonname =="" || reasonname == null)
          	   {
          	   $(".errormessagediv").show();
     		   $(".validateTips").text("Select Any One Reason");
     		    setTimeout(function() {
  				$('#errormessagediv').fadeOut();
  			    }, 100);
          	   }
               else
            	       {
				        datalist ={
						"enquiryIdlist" :enquiryIdlist,
						"resn":$("#reason").val(),
						"othereason":$("#otherreason").val(),
						"mobile_number":$("#mobile_number").val(),
						 
				       };
            	       

				$.ajax({

					type : "POST",

					url :"parentrequiresappointment.html?method=insertapprovedlist",

					data : datalist,
					beforeSend: function() {
						$('#loader').show();
					},
					success : function(data)

					{ 
						$('#loader').hide();
						var result = $.parseJSON(data);
						$(".errormessagediv").hide();
						if(result.status == "Approved"){
							$(".successmessagediv").show();
							$(".validateTips").text("Approving Record Progressing...");
							setTimeout(function(){
								window.location.href="adminMenu.html?method=tempadmissionMenu&"+result.status;

							},4000);


						}


					}


				});
				$(this).dialog("close");
            	       } 
			},
			"No" : function() {
				$(this).dialog("close");
			}

		}

	});
	$("#searchvalue").keydown(function(event) {
		var searchname=$("#searchvalue").val();

		if (event.keyCode == 13) {
			window.location.href = "adminMenu.html?method=tempadmissionMenu&searchname="+searchname;
		}

	});
	$('#search')
	.click(
			function() {
				var searchname = $("#searchvalue").val().trim();
				window.location.href = "adminMenu.html?method=tempadmissionMenu&searchname="+searchname;
			});
	
	
	
	
	$("#searchapprove").keydown(function(event) {
		var searchname=$("#searchapprove").val().trim();

		if (event.keyCode == 13) {
			window.location.href = "adminMenu.html?method=searchapprove&searchname="+searchname;
		}

	});
	$("#apprsearch")
	.click(
			function() {

				var searchname = $("#searchapprove").val().trim();
                window.location.href = "adminMenu.html?method=searchapprove&searchname="+searchname;

			});

	var enquiryIdlist=$("#updateenquiryid").val();

	$(document).ready(function() {
		$("#reject").click(function(){
			$("#dialog1").dialog("open");
			$("#dialog1").empty();
			$("#dialog1").append("<p>Are you sure to Reject?</p>");
			$("#dialog1").append('<label for="">Reason:</label>');
            $("#dialog1").append('<select name="reason" id="rejectreason">'
            		+ '<option value="">' + "----------select----------"
    				+ '</option>'
					+ '<option value="poorperformance">' + "Poor Performance" 
					+ '</option>'
					+ '<option value="admission closed">' + "Admission Closed" 
					+ '</option>'
					+ '<option value="others">' + "Others" 
					+ '</option>'+
			'</select>');
            $("#dialog1").append('<div id="othreason1"+ <label for="">OtherReason:</label>'+'<input type="text" name=other id="otherreason1"/></div>');
            $("#othreason1").hide();
    		$('#rejectreason').change(function(){
    			$(".errormessagediv").hide();
    			var othersres=$('#rejectreason').val();
    			if(othersres == 'others'){
    				$("#othreason1").show();
    			}else{
    				$("#otherreason1").val("");
    				$("#othreason1").hide();
    				$("#rejectreason").show();
    			}
    		});
    	    reason = $("#rejectreason").val();
    	});

	
		$("#dialog1").dialog({		
			autoOpen: false,
			modal: true,
			title:'Reject Details',
			buttons : {
				"Yes" : function() {
				var rejreason=	$("#rejectreason").val();
					if(rejreason=="" || rejreason==null)
                        {
	                     if(rejreason =="" || rejreason == null)
			          	   {
			          	   $(".errormessagediv").show();
			     		   $(".validateTips").text("Select Any One Reason");
			     		    setTimeout(function() {
			  				$('#errormessagediv').fadeOut();
			  			    }, 100);
			          	   }
						}
					else
						{
						
				
					datalist ={
							"enquiryIdlist" :enquiryIdlist,
							"resn":$("#rejectreason").val(),
							"othereason":$("#otherreason1").val()
					};


					$.ajax({

						type : "POST",

						url :"parentrequiresappointment.html?method=insertrejectedlist",

						data :datalist,
						beforeSend: function() {
							$('#loader').show();
						},

						success : function(data){

							$('#loader').hide();
							var result = $.parseJSON(data);
							$(".errormessagediv").hide();
							if(result.status == "Rejected"){
								$(".successmessagediv").show();
								$(".validateTips").text("Rejecting Record Progressing...");
								setTimeout(function(){
									window.location.href="adminMenu.html?method=tempadmissionMenu&"+result.status;

								},4000);


							}


						}
					});
					$(this).dialog("close");
						}
				},
				"No" : function() {
					$(this).dialog("close");
				}

			}

		});
	});

	$("#searchreject").keydown(function(event) {
		var searchname=$("#searchreject").val().trim();

		if (event.keyCode == 13) {
			window.location.href = "adminMenu.html?method=searchreject&searchname="+searchname;
		}

	});
	$("#searchrjct").click(
			function() {
				var searchname = $("#searchreject ").val().trim();

				window.location.href = "adminMenu.html?method=searchreject&searchname="+searchname;



			});
	
	
	$("#searchcancelled").keydown(function(event) {
		var searchname=$("#searchcancelled").val().trim();

		if (event.keyCode == 13) {
			window.location.href = "adminMenu.html?method=searchcancel&searchname="+searchname;
		}

	});
	$("#searchcacl").click(
			function() {
				var searchname = $("#searchcancelled").val().trim();

				window.location.href = "adminMenu.html?method=searchcancel&searchname="+searchname;
        });
	
	
	
	
	
	$("#searchdsbmit").keydown(function(event) {
		var searchname=$("#searchdsbmit").val().trim();

		if (event.keyCode == 13) {
			window.location.href = "adminMenu.html?method=searchsubmit&searchname="+searchname;
		}

	});
	$("#searchsubmit").click(
			function() {
				var searchname = $("#searchdsbmit").val().trim();

				window.location.href = "adminMenu.html?method=searchsubmit&searchname="+searchname;
        });
	
	
	
	
	$("#stream").change(function(){

		var streamId=$("#stream").val();

		$.ajax({

			type : "GET",
			url : "registration.html?method=getClassesByStream",
			data : {"streamId":streamId},
			async : false,
			success : function(data) {

				var result = $.parseJSON(data);
				$("#class").html("");
				$("#class").append(
						'<option value="' + "" + '">' + "----------Select----------"
						+ '</option>');
				for (var j = 0; j < result.ClassesList.length; j++) {

					$("#class").append(
							'<option value="'
							+ result.ClassesList[j].classId
							+ '">'
							+ result.ClassesList[j].classname
							+ '</option>');
				}

			}

		});

	});
	$(".nav-tabs li a").click(function(){

		$(".tab-pane").attr("style","display:none");
		$($(this).attr("href")).attr("style","display:block");

	});
	$(".successmessagediv").hide();
	$(".errormessagediv1").hide();
	$(".successmessagediv").hide();


	$('#save2').click (function()
			{
	
		$('#save2').attr("style","display:none");
		var parentfirstName = $('#parentfirstName').val();
		var parent_LastName = $('#parent_LastName').val(); 
		var mobile_number = $('#mobile_number').val(); 
		var address = $('#address').val();
		var parentEmailId = $('#parentEmailId').val();
		var stream=$('#stream').val();
		var classid=$('#class').val();
		var parents_name=$('#parents_name').val();
		var stu_par_relation=$('#stu_parrelation').val();
		var schoolId=$('#location').val();
		var AccyearId=$("#AccyearId").val();
		
		
		if (parentfirstName == "" || parentfirstName == null) {
			$('#save2').attr("style","display:inline-block;top:4px;right:8px");
			$(".errormessagediv").show();

			$(".validateTips").text("Enter  First Name");
			$("#parentfirstName").focus();
			document.getElementById("parentfirstName").style.border = "1px solid #AF2C2C";
			document.getElementById("parentfirstName").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			}, 3000);


		}

		else if (!parentfirstName.match(/[A-Za-z]+$/i)) {
			$('#save2').attr("style","display:inline-block;top:4px;right:8px");
			$('.errormessagediv').show();
			$('.validateTips').text("Enter a Valid Student First Name");
			$("#parentfirstName").focus();
			document.getElementById("parentfirstName").style.border = "1px solid #AF2C2C";
			document.getElementById("parentfirstName").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			}, 3000);



		}

		/*else if (parent_LastName == "" || parent_LastName == null) {
			$('#save2').attr("style","display:inline-block;top:4px;right:8px");
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Last Name");
			$("#parent_LastName").focus();
			document.getElementById("parent_LastName").style.border = "1px solid #AF2C2C";
			document.getElementById("parent_LastName").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			}, 3000);



		}

		else if (!parent_LastName.match(/[A-Za-z]+$/i)) {
			$('#save2').attr("style","display:inline-block;top:4px;right:8px");
			$('.errormessagediv').show();
			$('.validateTips').text("Enter a Valid Student Last Name");
			$("#parent_LastName").focus();
			document.getElementById("parent_LastName").style.border = "1px solid #AF2C2C";
			document.getElementById("parent_LastName").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			}, 3000);



		}*/

		else if(parents_name == "" || parents_name == null)
		{
			$('#save2').attr("style","display:inline-block;top:4px;right:8px");
			$(".errormessagediv").show();

			$(".validateTips").text("Field Required Parents Name");
			$("#parents_name").focus();


			document.getElementById("parents_name").style.border = "1px solid #AF2C2C";
			document.getElementById("parents_name").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			}, 3000);

		}

		else if(stu_par_relation == "")
		{
			$('#save2').attr("style","display:inline-block;top:4px;right:8px");
			$(".errormessagediv").show();

			$(".validateTips").text("Please Select Anyone Relation");
			$("#stu_parrelation").focus();
			document.getElementById("stu_parrelation").style.border = "1px solid #AF2C2C";
			document.getElementById("stu_parrelation").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
				document.getElementById("stu_parrelation").style.border = "1px solid #ccc";
				document.getElementById("stu_parrelation").style.backgroundColor = "#fff";
			}, 3000);

		}
		
		
		

		else if (mobile_number == "" || mobile_number == null) {
			$('#save2').attr("style","display:inline-block;top:4px;right:8px");
			$(".errormessagediv").show();

			$(".validateTips").text("Field Required Mobile Number");
			$("#mobile_number").focus();


			document.getElementById("mobile_number").style.border = "1px solid #AF2C2C";
			document.getElementById("mobile_number").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			}, 3000);

		}
		else if(mobile_number.length<10)
		{
			
			$('#save2').attr("style","display:inline-block;top:4px;right:8px");
			$(".errormessagediv").show();

			$(".validateTips").text("Enter 10 digit mobile no");
			$("#mobile_number").focus();


			document.getElementById("mobile_number").style.border = "1px solid #AF2C2C";
			document.getElementById("mobile_number").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			}, 3000);

		}

		else if (!mobile_number.match(/^(?!0{6})([0-9])+$/i)) {
			$('#save2').attr("style","display:inline-block;top:4px;right:8px");
			$(".errormessagediv").show();

			$(".validateTips").text("Enter Valid Mobile");
			$("#mobile_number").focus();


			document.getElementById("mobile_number").style.border = "1px solid #AF2C2C";
			document.getElementById("mobile_number").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			}, 3000);

		}

		
		
        
		else if(parentEmailId == "" || parentEmailId == null)
		{
			$('#save2').attr("style","display:inline-block;top:4px;right:8px");
			$(".errormessagediv").show();

			$(".validateTips").text("Field Required Email");
			$("#parentEmailId").focus();


			document.getElementById("parentEmailId").style.border = "1px solid #AF2C2C";
			document.getElementById("parentEmailId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			}, 3000);

		}
		else if (!parentEmailId.match(/^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/)) {
			$('#save2').attr("style","display:inline-block;top:4px;right:8px");
			$('.errormessagediv').show();
			$('.validateTips').text("Enter Valid Email.");
		}
		else if(schoolId == "" || schoolId == null)
		{
			$('#save2').attr("style","display:inline-block;top:4px;right:8px");
			$(".errormessagediv").show();

			$(".validateTips").text("Field Required School");
			document.getElementById("location").style.border = "1px solid #AF2C2C";
			document.getElementById("location").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			}, 3000);

		}
		else if(stream == "" || stream == null)
		{
			$('#save2').attr("style","display:inline-block;top:4px;right:8px");
			$(".errormessagediv").show();

			$(".validateTips").text("Field Required Stream");
			document.getElementById("stream").style.border = "1px solid #AF2C2C";
			document.getElementById("stream").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			}, 3000);

		}
		else if(address == "" || address == null)
		{
			$('#save2').attr("style","display:inline-block;top:4px;right:8px");
			$(".errormessagediv").show();

			$(".validateTips").text("Field Required  Address");
			$("#address").focus();


			document.getElementById("address").style.border = "1px solid #AF2C2C";
			document.getElementById("address").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			}, 3000);

		}
            
		


		else if(classid == "" || classid == null)
		{
			$('#save2').attr("style","display:inline-block;top:4px;right:8px");
			$(".errormessagediv").show();

			$(".validateTips").text("Field Required Class");
			$("#classid").focus();
            document.getElementById("classid").style.border = "1px solid #AF2C2C";
			document.getElementById("classid").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			}, 3000);

		}
		else if(AccyearId == "" || AccyearId == undefined){
			$(".errormessagediv").show();

			$(".validateTips").text("Select Academic Year");
			$("#stu_parrelation").focus();
			document.getElementById("AccyearId").style.border = "1px solid #AF2C2C";
			document.getElementById("AccyearId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			},100);
			
			
		}

		else
		{
			
			var datalist = {
					"parentfirstName" : parentfirstName,
					"parent_LastName" : parent_LastName,
					"parents_name" : parents_name,
					"parentEmailId" : parentEmailId,
					"address" : address,
					"stu_par_relation" : stu_par_relation,
					"mobile_number" : mobile_number,
					"stream" : stream,
					"classid" : classid,
					"schoolId":schoolId,
					"accyearId":AccyearId

			};


			$.ajax({

				type : "POST",

				url : "registration.html?method=InsertNewRegistrationUser",

				data : datalist,

				success : function(data) {
					var result = $.parseJSON(data);
					if(result.status == "true"){
						$(".successmessagediv").show();
						$(".validateTips").text("Adding Record Progressing...");
						setTimeout(function(){
							window.location.href="adminMenu.html?method=tempadmissionMenu";

						},4000);
					}
				}



			});
		}

			});
	var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("?")+1);

	if(pageUrl.split("&")[0]=="method=InsertNewRegistrationUser"){

		$(".successmessagediv").show();
		setTimeout(function(){
			window.location.href="adminMenu.html?method=tempadmissionMenu";
		},2500);
	}

	//getReload();
	var reg=window.location.href.substr(window.location.href.lastIndexOf('&')+1);
	var splitr=reg.split('=');
	var checker=splitr[0];
	if(checker=='re_enter_password'){
		$(".successmessagediv").show();
		$(".successmessagediv").show();
		$(".successmessagediv").delay(2000).fadeOut("slow");


	}
	
	$("#print").click(function(){
		printApplication();
	});
	
	
});

function printApplication(){

	
	 var a=$("#printing-css-a4").val();
		var b = document.getElementById("container").innerHTML;

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
 frameDoc.document.write('<link href="CSS/IdCard/'+$("#templatename").val()+'.css" rel="stylesheet" type="text/css" />');
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
function getStream(pointer){
	$.ajax({
		url : "classPath.html?method=getStreamDetailAction",
		async : false,
		data:{'school':pointer.val().trim()},
		success : function(data) {
			
			var result = $.parseJSON(data);
			$('#stream').empty();
			$('#stream').append('<option value="">-------Select-------</option>');
			for ( var j = 0; j < result.streamList.length; j++) {

				$('#stream').append('<option value="'+ result.streamList[j].streamId+ '">'
						+ result.streamList[j].streamName+ '</option>');

			}

		}
	});
}
function HideError() {

	document.getElementById("parentfirstName").style.border = "1px solid #ccc";
	document.getElementById("parentfirstName").style.backgroundColor = "#fff";

	document.getElementById("parent_LastName").style.border = "1px solid #ccc";
	document.getElementById("parent_LastName").style.backgroundColor = "#fff";

	document.getElementById("mobile_number").style.border = "1px solid #ccc";
	document.getElementById("mobile_number").style.backgroundColor = "#fff";

	document.getElementById("parents_name").style.border = "1px solid #ccc";
	document.getElementById("parents_name").style.backgroundColor = "#fff";

	document.getElementById("parentEmailId").style.border = "1px solid #ccc";
	document.getElementById("parentEmailId").style.backgroundColor = "#fff";
	
    document.getElementById("address").style.border = "1px solid #ccc";
	document.getElementById("address").style.backgroundColor = "#fff";
	
	document.getElementById("stu_parrelation").style.border = "1px solid #ccc";
	document.getElementById("stu_parrelation").style.backgroundColor = "#fff";

	document.getElementById("stream").style.border = "1px solid #ccc";
	document.getElementById("stream").style.backgroundColor = "#fff";

	document.getElementById("class").style.border = "1px solid #ccc";
	document.getElementById("class").style.backgroundColor = "#fff";

}

