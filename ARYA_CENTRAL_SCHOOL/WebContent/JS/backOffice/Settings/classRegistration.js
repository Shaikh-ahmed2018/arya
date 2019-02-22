
function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv1").hide();

}



$(document).ready(function() 
		{
	if($("#schoolId").val()!=""){
		$("#locationname").val($("#schoolId").val());
		getStream($("#schoolId"));
		}
			/*if($("select[name='class']").text()==$("input[name='hiddenclassname']").val())
						$("select[name='class'] option")*/

			var split_id =  $("input[name='updateClassCode']").val()
			.split('D');
			getData = split_id[1];


			$("select[name='class']").val(getData);
			$("#locationname").change(function(){
				getStream($(this));

			});
			
			setTimeout("removeMessage()", 3000);
			setInterval(function() {
				$(".errormessagediv").hide();
			}, 3000);	




			var hiddenStreamId=$("#hiddenStreamId").val().trim();
			if(hiddenStreamId!=undefined){

				$("#streamId option[value=" + hiddenStreamId + "]").attr('selected', 'true');

			}



			$('#classSave').click(function() {
				var stream = $('#streamId').val();
				var className = $('#classId').val();
				var status = $('#statusId').val();
				var updateClassCode = $('#updateClassCode').val();
				var updateclassName =$('#hiddenclassname').val();

				if($("#locationname").val()=="" || $("#locationname").val()==null){
					$(".errormessagediv").show();
					$(".validateTips").text("Select Location Name");
					$("#locationname").focus();
					document.getElementById("locationname").style.border = "1px solid #AF2C2C";
					document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				}
				else if (stream == "" || stream == null) {
					$(".errormessagediv").show();
					$(".validateTips").text(
					"Select Stream Name");
					$("#streamId").focus();
					document.getElementById("streamId").style.border = "1px solid #AF2C2C";
					document.getElementById("streamId").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;

				}else if(className == "" || className == null){
					$(".errormessagediv").show();
					$(".validateTips").text(
					"Select Class Name");
					$("#className").focus();
					document.getElementById("className").style.border = "1px solid #AF2C2C";
					document.getElementById("className").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				}else if(checkClassName()){
					$(".errormessagediv").show();
					$(".validateTips").text("Class & Stream is Already Mapped");
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				} else {

					var datalist = {
							"stream" : stream,
							"className" : className,
							"status" :status,
							"updateClassCode":updateClassCode,
							"updateclassName" :updateclassName,
							"locationId":$("#locationname").val().trim()
					};

					$.ajax({
						type : 'POST',
						url : "classPath.html?method=insertClassAction",
						data : datalist,
						success : function(response) {
							var result = $.parseJSON(response);

							if (result.status == "Class Added Successfully") {

								$(".successmessagediv").show();

								$(".validateTips").text("Adding Record Progressing...");

								setTimeout(function(){

									window.location.href = "adminMenu.html?method=classList";


								},3000);


							} else if(result.status == "Class Updated Successfully"){

								$(".successmessagediv").show();

								$(".validateTips").text("Updating Record Progressing...");

								setTimeout(function(){

									window.location.href = "adminMenu.html?method=classList";

								},3000);

							}

							else {
								$(".errormessagediv").show();
								$(".validateTips").text("Class & Stream is Already Mapped");

								
							}

						}


					});
				}

			});

			$('#editClass').click(function() {

				$(".errormessagediv1").hide();
				var cnt = 0;
				$('input.class_Checkbox_Class:checkbox:checked').map(function() {
					if (cnt > 0) {
						$(".errormessagediv").show();
						$('.validateTips').text("You can update only one Class Details at a time");
						cnt++;
						return false;
					} else {
						var check_id = $(this).attr("id");
						var split_id = check_id.split('_');
						getData = split_id[1].split(',');
						var classCode=getData[0];
						cnt++;
					}
				});
				if (cnt == 0) {

					$(".errormessagediv").show();
					$('.validateTips').text("Select Class to Update");
				}
				if (cnt == 1) {
					window.location.href = "classPath.html?method=editClass&classCode="	+ classCode;

				}

			});

		});

function checkClassName() {
	var className = $('#classId').val();
	var stream = $("#streamId").val(); 
	var school=$("#locationname").val();
	var updateClassCode = $('#updateClassCode').val();
	var checkClassName = {
			"className" : className,
			"stream" : stream,
			"updateClassCode":updateClassCode,
			"school":school,
	};

	var status = false;

	$.ajax({
		type : "POST",
		url : "classPath.html?method=classNameCheck",
		data : checkClassName,
		async : false,
		success : function(data) {

			var result = $.parseJSON(data);

			status = result.status;

		}
	});

	return status;

}



function getStream(pointer){
	$.ajax({
		url : "classPath.html?method=getStreamDetailAction",
		async : false,
		data:{'school':pointer.val().trim()},
		success : function(data) {
			
			var result = $.parseJSON(data);
			$('#streamId').empty();
			$('#streamId').append('<option value="">-------Select-------</option>');
			for ( var j = 0; j < result.streamList.length; j++) {

				$('#streamId').append('<option value="'+ result.streamList[j].streamId+ '">'
						+ result.streamList[j].streamName+ '</option>');

			}

		}
	});
}



function HideError() 
{
	document.getElementById("streamId").style.border = "1px solid #ccc";
	document.getElementById("streamId").style.backgroundColor = "#fff";

	document.getElementById("className").style.border = "1px solid #ccc";
	document.getElementById("className").style.backgroundColor = "#fff";

}
