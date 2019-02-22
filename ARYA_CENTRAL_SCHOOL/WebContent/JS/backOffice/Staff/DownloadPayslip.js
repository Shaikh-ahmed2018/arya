
$(document)
		.ready(
				function() {
					
					// fuctions to get values

					/*$.ajax({
						type : 'POST',
						url : "studentRegistration.html?method=getAcademicYear",
						async : false,
						success : function(response) {
							var result = $.parseJSON(response);
							$("#accyear").html("");
							$("#accyear").append(
									'<option value="' + "" + '">' + "" + '</option>');
							
							alert(JSON.stringify(result.jsonResponse));

							for ( var j = 0; j < result.jsonResponse.length; j++) {
								$("#accyear").append(
										'<option value="'
												+ result.jsonResponse[j].academicYearId + '">'
												+ result.jsonResponse[j].academicYear
												+ '</option>');
							}

						}
					});*/
					
					var hmonth=$("#hmonth").val();
					if(hmonth!=undefined && hmonth.trim()!=''){
	
						$("#monthid option[value='" + hmonth + "']").attr('selected', 'true');
						$("#accyear option[value='" + $("#hyear").val() + "']").attr('selected', 'true');
			 
					}
			 
			 
			 
			 
			$("#download").click(function(){
				var month=$("#monthid").val();
				var accyear=$("#accyear").val();
				
				if(month==""){
					
					$(".errormessagediv").show();
					$('.validateTips').text("Select month");
					
				}else if(accyear==""){
					
					$(".errormessagediv").show();
					$('.validateTips').text("Select year");
				}else{
					
					$(".errormessagediv").hide();
					window.location.href = "teachermenuaction.html?method=downloadPayslipDocument&month="
						+month
						+"&accyear="
						+accyear;
				}
				
			});


});