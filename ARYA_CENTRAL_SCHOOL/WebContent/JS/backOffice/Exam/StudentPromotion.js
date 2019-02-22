$(document).ready(function()
		{
		$("#save").click(function() {

			
				var studentidvalues = [];
				var toclassvalues = [];
				var tosectionvalues = [];
				var toacadamicyearvalues = [];
				var pramotionstatus = [];
				var fromSpecialization=[];
				var toSpecialization=[];
				var classCount=0;
				
				var fromclass = $("#hclassid").val();
				var fromacadamicyear = $("#haccyaear").val();
				var fromsection = $("#hsectionid").val();
				
										// getting the grid values
				$('#allstudent tbody tr').each(function()
					{
					
					var row = $(this);
					if(row.find(".promotion").val()=="Promoted"){
						classCount=(parseInt(fromclass.split("D")[1])+1);
						}
						else{
							classCount=parseInt(fromclass.split("D")[1]);
						}
						var toclass="CCD"+classCount;
					studentidvalues.push(row.find('td:nth-child(1)').attr("class"));
					toclassvalues.push(toclass);
					tosectionvalues.push(fromsection);
					toacadamicyearvalues.push($('#nhaccyaear').val());
					pramotionstatus.push(row.find('.promotion').val());
					fromSpecialization.push($("#hspecializationid").val());
					toSpecialization.push($("#hspecializationid").val());

							
						});

										
														datalist = {

															"studentidvalues" : studentidvalues,
															"fromclass" : fromclass,
															"fromacadamicyear" : fromacadamicyear,
															"fromsection" : fromsection,
															"toclassvalues" : toclassvalues,
															"tosectionvalues" : tosectionvalues,
															"toacadamicyearvalues" : toacadamicyearvalues,
															"pramotionstatus" : pramotionstatus,
															"fromSpecialization":fromSpecialization,
															"toSpecialization":toSpecialization

														},

														$
																.ajax({
																	type : 'GET',
																	url : 'studentPromote.html?method=insertStudentPromotion',
																	data : datalist,
																	success : function(
																			response) {
																		var result = $.parseJSON(response);


																		window.location.href = "adminMenu.html?method=studPromotion&notptomotedStudentList="
																				+ result.RESULT;

																	}

																});
										
									
									});
	
					
				});
