$(document).ready(function(){
	$("#Acyearid").val($("#hacademicyaer").val());
	
	$("#studentName").autocomplete({
		source : function(request, response) {

			$.ajax({
				url : "studentRegistration.html?method=studentSearchbySibling",
				data : {
					searchTerm : request.term
				},
				async : false,
				success : function(data) {
					var result = $.parseJSON(data);
					response($.map(	result.jsonResponse,function(item) {
						return {
							label : item.studentnamelabel,
							value : item.studentidlabel,
						}
					}));
				}
			});
		},
		select : function(event, ui) {

			var searchTerm = ui.item.value;

			studentDetails = {
					'searchTerm' : searchTerm
			};
		
			paidTermListForStudent(searchTerm);
			
			$("#studentName").val(ui.item.label);
			$("#studentIdIntId").val(searchTerm);

			return false;
		}
	});
	
	$("#dialog").dialog({
		autoOpen: false,
		modal: true,					    
		title:'Cancel Fee',
		buttons : {
			"Yes" : function() {

				$.ajax({
					type : 'POST',
					url : "feecollection.html?method=cancelFee",
					data : {"feecode":$("#feecode").val()},
					success : function(
							response) {
						var result = $
						.parseJSON(response);

						$('.errormessagediv').hide();

						if (result.status == "true") {

							$(".successmessagediv").show();
							
							$(".validateTips").text("Cancel Fee Progressing...");
							$('.successmessagediv').delay(3000).slideUp();
							setTimeout(function(){
								paidTermListForStudent($("#studentIdIntId").val());
							},3000);

						} 
						else{
							$(".errormessagediv").show();
							$(".validateTips").text("Selected Fee is Not Canceled");
							$('.errormessagediv').delay(3000).slideUp();
						}
						

					}

				});  

				$(this).dialog("close");

			},

			"No" : function() {
				$(this).dialog("close");
			}

		}
	});
	
	});
function paidTermListForStudent(studentId){
	$.ajax({
		type:'POST',
		url:'feecollection.html?method=feeCancellationList',
		data:{'studentId':studentId,
				'accYear':$("#Acyearid").val()
		},
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			$("#allstudent tbody").empty();
			var len=result.data.length;
		for(var i=0;i<len;i++){
			$("#allstudent tbody").append("<tr><td>"+(i+1)+"</td>" +
					"<td>"+result.data[i].admissionNo+"</td>" +
					"<td>"+result.data[i].studentName+"</td>" +
					"<td>"+result.data[i].className+"</td>" +
					"<td>"+result.data[i].termName+"</td>" +
					"<td>"+result.data[i].paidAmt+"</td>" +
					"<td><input class='buttons cancel' type='button' value='cancel' id='"+result.data[i].id+"' /></td></tr>");
		}
		$(".cancel").click(function(){
			$("#dialog").dialog("open");
			$("#feecode").val($(this).attr("id"));
			
		});
		
		
		}
	});
	
}