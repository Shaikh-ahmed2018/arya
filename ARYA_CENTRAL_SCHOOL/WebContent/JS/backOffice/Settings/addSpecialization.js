
function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv1").hide();

}

$(document)
.ready(
		function() {
			if($("#schoolId").val()!=""){
				$("#locationname").val($("#schoolId").val());
				$("#locationname").find("option").not("option[value*=LOC]").remove();
				getStream($("#schoolId"));
			}
			$("#locationname").change(function(){
				getStream($(this));
				
			});

			var hiddenclassId = $("#updateClassCode").val();
			var classId = $("#classId").val();

			$("#classId").change(function() {

				checkSpecialization();

			});

			$("#specialization").change(function() {
				if (hiddenspecname != specialization) {
					checkSpecialization();
				}
			});

			$("#streamId")
			.change(
					function() {

						var streamId = $("#streamId").val();

						$
						.ajax({
							type : "GET",
							url : "reportaction.html?method=getClassesByStream",
							data : {
								"streamId" : streamId
							},
							async : false,
							success : function(data) {

								var result = $
								.parseJSON(data);
								$("#classId").html("");
								$("#classId")
								.append(
										'<option value="'
										+ ""
										+ '">'
										+ "-----------Select------------"
										+ '</option>');

								for ( var j = 0; j < result.ClassesList.length; j++) {

									$("#classId")
									.append(
											'<option value="'
											+ result.ClassesList[j].classId
											+ '">'
											+ result.ClassesList[j].classname
											+ '</option>');
								}

							}

						});

					});

			if ($("#updateClassCode").val() != ""
				|| $("#updateClassCode").val() != undefined) {
				
				

				var streamId = $("#hiddenStreamId").val();
				$("#streamId").val(streamId);
				$
				.ajax({
					type : "GET",
					url : "reportaction.html?method=getClassesByStream",
					data : {
						"streamId" : streamId
					},
					async : false,
					success : function(data) {

						var result = $.parseJSON(data);
						$("#classId").html("");
						$("#classId").append(
								'<option value="' + "" + '">'
								+ "" + '</option>');

						for ( var j = 0; j < result.ClassesList.length; j++) {

							$("#classId")
							.append(
									'<option value="'
									+ result.ClassesList[j].classId
									+ '">'
									+ result.ClassesList[j].classname
									+ '</option>');
						}
						$("#classId").val(
								$("#updateClassCode").val());
					}

				});

			}

			$('#saveid')
			.click(function() {
						var stream = $('#streamId').val();
						var className = $('#classId').val();
						var specialization = $("#specialization").val();
						var status = $('#statusId').val();
						var locationId=$('#locationname').val();
						var updateClassCode = $('#updateClassCode').val();
						var hiddenspecname = $("#hiddenspecname").val().trim();
						var hiddenclassId = $("#updateClassCode").val()
						.trim();
						
						if (locationId == "" || locationId == null) {
							$(".errormessagediv").show();
							$(".validateTips").text("Select School Name");
							$("#locationname").focus();
							document.getElementById("locationname").style.border = "1px solid #AF2C2C";
							document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
							setTimeout(
									function() {
										$('.errormessagediv')
										.fadeOut();
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
							setTimeout(
									function() {
										$('.errormessagediv')
										.fadeOut();
									}, 3000);
							return false;

						} 
						else if (specialization == "" || specialization == null) {
							$(".errormessagediv").show();
							$(".validateTips").text("Enter Specialization Name");
							$("#specialization").focus();
							document.getElementById("specialization").style.border = "1px solid #AF2C2C";
							document.getElementById("specialization").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
										$('.errormessagediv').fadeOut();
									}, 2000);
							return false;
						}
						
						else if (className == ""
							|| className == null) {
							$(".errormessagediv").show();
							$(".validateTips").text(
							"Select Class Name");
							$("#classId").focus();
							document.getElementById("classId").style.border = "1px solid #AF2C2C";
							document.getElementById("classId").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
										$('.errormessagediv').fadeOut();
									}, 3000);
							return false;
						} 
						else {

							 var datalist = {
									 "stream" : stream,
									 "className" : className,
									 "status" : status,
									 "updateClassCode" : updateClassCode,
									 "specialization" : specialization,
									 "locationId":locationId,
							 };

							 $
							 .ajax({
								 type : 'POST',
								 url : "specialization.html?method=insertSpecialization",
								 data : datalist,
								 success : function(
										 response) {
									 var result = $
									 .parseJSON(response);

									 if (result.status == "true") {

										 /*
										  * var msg =
										  * "Specialization
										  * details Added
										  * Successfully";
										  */
										 var msg = "Adding Record progressing..."
											 $(".successmessagediv").show();
										 $(".validateTips").text( msg);

										 setTimeout(function() {

													 window.location.href = "adminMenu.html?method=SpecializationList";

												 }, 2000);

									 }

									 else if (result.status == "Specialization Updated SuccessFully") {
										 $(".errormessagediv").hide();
										 var msg = "Updating Record progressing...";
										 $(".successmessagediv").show();
										 $(".validateTips").text( msg);

										 setTimeout(
												 function() {

													 window.location.href = "adminMenu.html?method=SpecializationList";
												 }, 3000);
									 }

									 else if (result.status == "Specialization not Deleted SuccessFully") {

										 var msg = "Selected Specialization not Deleted";
										 $( ".errormessagediv").show();
										 $( ".validateTips").text( msg);
										 setTimeout( function() {
													 window.location.href = "adminMenu.html?method=SpecializationList";

												 }, 3000);

									 }

									 else {
										 $(".errormessagediv").show();
										 $(".validateTips").text("Specialization Already Exist with this Class");

										 setTimeout(function() {

													 window.location.href = "adminMenu.html?method=addSpecialization";

												 }, 3000);
									 }

								 }

							 });
						 }

					});

		});

function checkSpecialization() {

	var status = false;
	var hiddenspecname = $("#hiddenspecname").val();
	var hiddenclassId = $("#updateClassCode").val();

	var classId = $("#classId").val().trim();
	var specialization = $("#specialization").val();
	var locationId=$("#locationname").val();
	var checkClassName = {
			"classId" : classId,
			"specialization" : specialization,
			"locationId":locationId

	};
	if (hiddenclassId != classId || hiddenspecname != specialization) {

		$.ajax({
			type : "POST",
			url : "specialization.html?method=validateSpecialization",
			data : checkClassName,
			async : false,
			success : function(data) {

				var result = $.parseJSON(data);

				if (result.status == "true") {

					$(".errormessagediv").show();
					$(".validateTips").text(
							"Specialization Name '"
							+ $("#specialization").val()
							+ "' Already Exists With this Class");
					$("#specialization").val("");
					$("#specialization").css({
						'border-color' : '#f00'
					});

					setTimeout(function() {
						$(".errormessagediv").hide();

					}, 3000);

				}

			}
		});
	}

}

function getStream(pointer) {
	$.ajax({
		url : "classPath.html?method=getStreamDetailAction",
		data:{"school":pointer.val()},
		async : false,

		success : function(data) {

			var result = $.parseJSON(data);
			$('#streamId').empty();
			$('#streamId').append(
					'<option value="">-------------Select-------------</option>');
			for ( var j = 0; j < result.streamList.length; j++) {

				$('#streamId').append(
						'<option value="' + result.streamList[j].streamId
						+ '">' + result.streamList[j].streamName
						+ '</option>');

			}
			if ($("#hiddenStreamId").val() != ""
				|| $("#hiddenStreamId").val() != undefined) {
				$("#streamId").val($("#hiddenStreamId").val());
			}
		}
	});
}

function HideError() {
	document.getElementById("streamId").style.border = "1px solid #ccc";
	document.getElementById("streamId").style.backgroundColor = "#fff";

	document.getElementById("className").style.border = "1px solid #ccc";
	document.getElementById("className").style.backgroundColor = "#fff";

}
