
$(document).ready(function(){
	
	$("div[id^='collapseECS'] .panel-body").each(function(){
		if($(this).find(".row").has("div").length==0){
		$(this).closest(".panel-group").remove();
		}
	});
		
	
	$("#startActivation").click(function(){
		
		$.ajax({
			type:'POST',
			url:'ElectionMenu.html?method=CheckMachineStatus',
			data:{"localIp":$("#LocalIp").text()},
			success:function(response){
				var result=$.parseJSON(response);

				if(result.status!="false"){

					window.location.href="adminMenu.html?method=showCategory&"+result.status.split(",")[0];
				}
				else{
					alert("Machine Not Activated");
				}
			}
		});
	
	
	});
	history.pushState(null, null, location.href);
	window.onpopstate = function(event) {
	    history.go(1);
	};
	$(".candidate").click(function(){
		voteCast($(this));
		$(this).closest(".panel-group").remove();
		if($(".panel-group").length>0){
		
			
		}
		else{
	setTimeout(function(){
		window.location.href="adminMenu.html?method=voterMachineStart&accyear="+$('#helectionaccyear').val()+"&group="+$('#helectiongroup').val()+"&titleID="+$('#helectiontitleID').val();
	},1000);
		}
		
	});
	setInterval(function(){
		if(refreshCheck()){
			refreshCheck();
		}
	},1000);
	
	
});

function voteCast(pointer){
	var voter=window.location.href.substring(window.location.href.lastIndexOf("&")+1);
	var admissionNo=pointer.find(".hadmissionNo").val();
	$.ajax({
		type:'POST',
		url:'ElectionMenu.html?method=voteCount',
		data:{"admissionNo":admissionNo,
			"localIp":$("#LocalIp").text(),
			"count":$(".panel-group").length,
			"voter":voter,
		},
		success:function(response){
			var result=$.parseJSON(response);

			if(result.status=="true"){
				$('.errormessagediv').hide();
				$('.successmessagediv').show();
				$(".validateTips").text("For Category "+pointer.closest(".panel-group").find("h4.panel-title").find("a").text()+" vote casted Thank you!");
				$(".successmessagediv").delay(2000).fadeOut();
				
				
			}
			else if(result.status=="illegal"){
				$('.successmessagediv').hide();
				$(".errormessagediv").show();
				$(".validateTips").text("illegal Action");
				$(".errormessagediv").delay(3000).fadeOut();
				setTimeout(function(){
					window.location.href="adminMenu.html?method=voterMachineStart&accyear="+$('#helectionaccyear').val()+"&group="+$('#helectiongroup').val()+"&titleID="+$('#helectiontitleID').val();
				},1000);
			}
			else{
				$('.successmessagediv').hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Fail to Cast Vote");
				$(".errormessagediv").delay(3000).fadeOut();
			}
		}
	});
}
function refreshCheck(){
	
	var status=true;
	
	$.ajax({
		type:'POST',
		url:'ElectionMenu.html?method=refreshCheck',
		data:{
			"localIp":$("#LocalIp").text(),
			"electionaccyear":$("#electionaccyear").val(),
			"electiongroup":$("#electiongroup").val(),
			"electiontitleID":$("#electiontitleID").val(),
		},
		success:function(response){
			var result=$.parseJSON(response);
			if(result.jsonResponse.length>0){
				$(".stuImage").empty();
				$(".stuImage").append("<img src='"+result.jsonResponse[0].studentimage+"' width='100%' height='100%' />");
				$(".stuImage").append("<div>"+result.jsonResponse[0].studentFirstName+"</div>");
				status=false;
			}
			

		}
	});
	return status
}



