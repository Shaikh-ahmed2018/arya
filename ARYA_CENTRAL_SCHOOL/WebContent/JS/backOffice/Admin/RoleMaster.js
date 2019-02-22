$(document)
		.ready(
				function() {
					
					$("#selectall").change(function(){
						$(".roleDetails_Checkbox_Class").prop("checked",$(this).prop("checked"));
				});
					
					$('.errormessagediv').hide();

					$('#save').click(
							function() {
								
								document.getElementById("rolename").style.border = "1px solid #ccc";
								
								var role_Code = $("#rolecode").val();
								
								var rolename = $("#rolename").val();
								var roleDescription = $("#description").val();
								var isadministrator = $('#isadministrator').is(':checked');
									
							
								
								
								if (role_Code=="") {
									
									
									if(rolename==""){
										
										$('.errormessagediv').show();
										$('.validateTips').text("Enter Role Name");
										$("#rolename").focus();
										document.getElementById("rolename").style.border = "1px solid #ff0000";
										
									}else if(rolename.match(/^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$/i)==null){
										
										$('.errormessagediv').show();
										$('.validateTips').text("Role name should be only alphanumeric");
										
									}
									
									else if(validateRoleName()==1){
										
										$('.errormessagediv').show();
										$('.validateTips').text("Role Already Exist");
									}
									else{
									
									var datalist={
											
											"roleCode" : role_Code,
											"roleName" : rolename,
											"roleDescription" : roleDescription,
											"isadministrator" : isadministrator
											
									};
									
									$.ajax({
										type : "GET",
										url : "role.html?parameter=Add",
										data :datalist,
										async : false,
										success : function(data) {
											var result = $.parseJSON(data);
											
											$('.errormessagediv').hide();
											$('.successmessagediv').show();
											$('.successmessage').text(result.status);
											
											
											 setTimeout(function(){
												   
												 window.location.href="adminMenu.html?method=roleList";
											 
											 },3000);
										
										}
									});
								}
									
								}else{
									
									if(rolename==""){
										
										$('.errormessagediv').show();
										$('.validateTips').text("Enter Role Name");
										
									}else if(validateRoleNameUpdate()==1){
										
										$('.errormessagediv').show();
										$('.validateTips').text("Role already exist");
									
									}else{
									
									var datalist={
											"roleCode" : role_Code,
											"roleName": rolename,
											"roleDescription": roleDescription,
											"isadministrator" : isadministrator
											
									};
											
										$.ajax({
												type : "GET",
												url : "role.html?parameter=Update",
												data :datalist,
												async : false,
												success : function(data) {
													var result = $.parseJSON(data);
													
													/*$("#rolename").val("");
													$("#description").val("");*/
													
													$('.errormessagediv').hide();
													
													$('.successmessagediv').show();
													$('.successmessage').text(result.status);
												
												}
											});
										
										
										 setTimeout(function(){
											 
											 window.location.href="adminMenu.html?method=roleList";
											
										 
										 },3000);
									
								}
								}
					
				});
					
					
					$("#delete").click(function(e){
						var count=0;
						roleCodeArray=[];//
						 $('input[name="rolename"]:checked').each(function() {
								count = count + 1;
								role_Code = this.id;
								roleCodeArray.push(role_Code);//

							});
						
						 if (count == 0) {
								$('.errormessagediv').show();
								$('.validateTips').text("Select Any Role");
								return false;
							}
						 else{
							
							  $("#dialog").dialog("open");
							  $("#dialog").empty();
							  $("#dialog").append("<p>Are you sure want to Delete?</p>");
						}
									
					});
					$("#dialog").dialog({
						
						
					     autoOpen: false,
					     modal: true,
					     title:'Role details',
					     buttons : {
					          "Yes" : function() {
					        	  var role = {
											"role_Code" : roleCodeArray.toString(),//
										};
									$.ajax({
												type : 'POST',
												url : "role.html?parameter=deleteRole",
												data : role,
												success : function(
														response) {
													var result = $.parseJSON(response);
													
													$('.errormessagediv').hide();
													
													if(result.status=="Delete Unmapped Roll Details Progressing..."){
														
													$(".successmessagediv").show();
													$(".successmessagediv").attr("style","width:150%;margin-left:-250px;");
													$(".successmessage").text(result.status);
													
													}else{
														
														$('.errormessagediv').show();
														$('.validateTips').text(result.status);
														
													}
													
													
													 setTimeout(function(){
														   
														 location.reload();
													 
													 },2000);
													
												
												}

											});
									 $(this).dialog("close");
								
							},
					          "No" : function() {
					            $(this).dialog("close");
					          }
					        }


			 });
					
					
					
					
				/*	$('#delete').click(function() {
						
						var count = 0;
						var role_Code = null;

						$('input[name="rolename"]:checked').each(function() {
							count = count + 1;
							role_Code = this.id;

						});

						if (count == 0 || count>1) {
							$('.errormessagediv').show();
							$('.validateTips').text("Select Any Role");

						} else {

							var x = confirm("Are you sure to Delete Role");
							if (x) {

								var role = {
									"role_Code" : role_Code
								};
								$	.ajax({
											type : "GET",
											url : "role.html?parameter=deleteRole",
											data : role,
											async : false,

											success : function(data) {
												var result = $.parseJSON(data);
												
												$('.errormessagediv').hide();
												
												if(result.status=="Delete Unmapped Roll Details Progressing..."){
													
												$(".successmessagediv").show();
												$(".successmessagediv").attr("style","width:150%;margin-left:-250px;");
												$(".successmessage").text(result.status);
												
												}else{
													
													$('.errormessagediv').show();
													$('.validateTips').text(result.status);
													
												}
												
												
												 setTimeout(function(){
													   
													 location.reload();
												 
												 },3000);
												
											
											}

										});

							}

						}

						
					});*/
					
							
				$('#edit').click(function() {
					
							
						var count = 0;
						var role_Code = null;

						$('input[name="rolename"]:checked').each(function() {
							count = count + 1;
							role_Code = this.id;
						});
						

						if (count > 1 || count == 0) {
							
							$('.errormessagediv').show();
							$('.validateTips').text("Select Any Role");
							return false;

						} else {
							
							window.location.href="role.html?parameter=editRole&ROleCode="+role_Code;
				}
	});
				
				$("#search").click(function(){
					
					var searchTerm=$("#searchterm").val().trim();
					window.location.href="adminMenu.html?method=roleList&searchTerm="+searchTerm;	
				});
				
				
				
				
				var isAdministrator = $("#hadministrator").val();
			
				if(isAdministrator=="Y")
					{
					document.getElementById("isadministrator").checked = true;
					}
				else
					{
					document.getElementById("isadministrator").checked = false;

					}

				
				
				$("#clear").click(function(){
					
					window.location.href="role.html?parameter=getRoleEntry";
					
				});
				
				$('#excelDownload')
				.click(
						function() {
							
							
							var searchTerm = $("#searchexamid").val().trim();
							
						
							
							window.location.href = 'role.html?parameter=downloadRoleDetailsXLS&searchTerm='+ searchTerm;
							
						});

				$("#pdfDownload").click(function(){
					
					var searchTerm = $("#searchexamid").val().trim();
					
					window.location.href = "role.html?parameter=RoleDetailsPDFReport&searchTerm="+ searchTerm;
						
				});	
			
				});



function validateRoleNameUpdate() {

	var roleName_validate_update=0;
	var roleName = $('#rolename').val();
	var roleid = $("#rolecode").val();

	var roleObject = {
		"roleName" : roleName,
		"roleid" : roleid
	};

	$.ajax({

		type : "GET",
		url : "role.html?parameter=validateRoleNameUpdate",
		data : roleObject,
		async : false,

		success : function(data) {

			var result = $.parseJSON(data);
			
			
			if (result) {
				$('.errormessagediv').show();
				$('.validateTips').text("Role Name Already Exists");
				roleName_validate_update = 1;

			} else {
				roleName_validate_update = 0;
			}

		}

	});

	return roleName_validate_update;

}

function validateRoleName() {
	var roleName_validate=0;
	var roleName = $('#rolename').val();
	var roleObject = {
		"roleName" : roleName
	};

	$.ajax({

		type : "GET",
		url : "role.html?parameter=validateRoleName",
		data : roleObject,
		async : false,

		success : function(data) {

			var result = $.parseJSON(data);
			
			if (result) {

				$('.errormessagediv').show();
				$('.validateTips').text("Role name already exist");
				
				roleName_validate = 1;

			} else {
				roleName_validate = 0;
			}

		}

	});


	return roleName_validate;
}

function HideError() 
{
	
document.getElementById("rolename").style.border = "1px solid #ccc";
document.getElementById("rolename").style.backgroundColor = "#fff";

}