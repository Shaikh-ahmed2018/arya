$(document).ready(
		function() {
			
			getAcademicYear();
			var hPriveliges = $("#hPriveliges").val();
			
			if(hPriveliges=="Y")
				{
				$("#globalAcademicYear option[value="+$("#hacademicyaer").val()+"]").attr("selected",true);
				
				$("#globalAcademicYear").attr("disabled",true);
				
				}
			else
				{
				
				$("#globalAcademicYear option[value="+$("#hacademicyaer").val()+"]").attr("selected",true);
				$("#globalAcademicYear").attr("disabled",true);
				}

			$('table.displayTableClass th,.exportlinks').attr('class',
					'HometblHeader');
			
			setInterval(function() {
				$(".successmessagediv").hide();
			}, 7000);
			setInterval(function() {
				$(".errormessagediv").hide();
			}, 10000);
			
		/*	$('.pagebanner').hide();*/
			var listSize = $('.pagelinks').text();

			if (listSize <= 10) {
				$('.pagelinks').text("Page : " + listSize);
			} else {
			}
		});
function getAcademicYear() {
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getAcademicYear",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#globalAcademicYear").html("");
			

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$("#globalAcademicYear").append(
						'<option value="'
								+ result.jsonResponse[j].academicYearId + '">'
								+ result.jsonResponse[j].academicYear
								+ '</option>');
			}
			$("#globalAcademicYear option[value="+$("#hacademicyaer").val()+"]").attr("selected",true);
		}
	});

}
