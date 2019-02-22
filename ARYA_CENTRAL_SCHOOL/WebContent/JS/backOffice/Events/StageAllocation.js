$(document).ready(function(){

	$("#event").change(function()
			{
	
	getstageName();
			});
	
	$("#stage").change(function() {
			
		getUnAllocatedProgramList();
			stageDetailMapping();
	});
	
	$("#excelDownload").click(function(){

		var event = $("#event").val();
		var stage=$("#stage").val();
		
		if (event =="") {
			showError("#event","Select Event Name");
			
			return false;
		}
		else if(stage=="")
			{
			showError("#stage","Select Stage Name");
			HideError();	 
			return false;
			}
		

			window.location.href = 'EventsMenu.html?method=getStageAllocatedExcelReport&event='+event+'&stage='+stage+'';
				
		
	});

	$("#pdfDownload").click(function(){
		
		var event = $("#event").val();
		var stage=$("#stage").val();
		
		if (event =="") {
			showError("#event","Select Event Name");
			
			return false;
		}
		else if(stage=="")
			{
			showError("#stage","Select Stage Name");
			HideError();	 
			return false;
			}
		
	
	
			window.location.href = 'EventsMenu.html?method=getStageAllocatedPdfReport&event='+event+'&stage='+stage+'';
		
	});
	$("#print").click(function(){
		
		var event = $("#event").val();
		var stage=$("#stage").val();
		
		if (event =="") {
			showError("#event","Select Event Name");
			
			return false;
		}
		else if(stage=="")
			{
			showError("#stage","Select Stage Name");
			HideError();	 
			return false;
			}
		
		
		$.ajax({
			type: "POST",
			url:"EventsMenu.html?method=printStageAllocatedList&event="+event+"&stage="+stage,
			success : function(data){
				
			}
		});
	});


});



function addIndividualParticipant() {
		
	
	var a = document.getElementById('original');
		var b = document.getElementById('copy');
		
		var concatValues;
		if($("#stage").val()==""){
			showError("#stage","Enter Stage Name");
		 	return false;
		}
		else
			{
		if (a != null) {

			for ( var count = 0; count < a.length; count++) {
				if (a[count].selected) {
					var option = document.createElement("option");
					option.setAttribute("value", a.value);
					//option.innerHTML = a.value;
					option.innerHTML = a[count].text;
					document.getElementById('copy').appendChild(option);

					concatValues = a[count].value;
					a[count].remove();
					
					break;
				}
			}
			assignright();
			
		}
			}
		if($("#copy option").length>0){
			$(".navbar-right").show();
		}
		else{
			$(".navbar-right").hide();
		}
		
	}
function deselectIndivivdualParticipant() {
	
	
  		var a = document.getElementById('copy');
		var b = document.getElementById('original');
		var concatValues;
		if (a != null) {
			for ( var count = 0; count < a.length; count++) {
				if (a[count].selected) {
					var option = document.createElement("option");
					option.setAttribute("value", a.value);
					//option.innerHTML = a.value;
					option.innerHTML=a[count].text;
					document.getElementById('original').appendChild(option);
					concatValues = a[count].value;
					
					
					break;
				}
			}
			assignleft();
			a[count].remove();
		}
		
		if($("#copy option").length>0){
			$(".navbar-right").show();
		}
		else{
			$(".navbar-right").hide();
		}

	}

	function deSelectParticipant() {
		
	

		var a = document.getElementById('copy');
		var b = document.getElementById('original');
		var selectedValues = new Array();
		var seelctedTexts = new Array();
		var concatValues = "";
		var concattext="";
		for ( var count = 0; count < a.length; count++) {
			if (a[count].selected) {
				concatValues = a[count].value + "@" + concatValues;
				concattext= a[count].text + "@" + concattext;
				// a[count].remove();
			}
		}

		var splitValues = concatValues.split("@");
		var splitsText=concattext.split("@");
		for ( var i = 0; i < splitValues.length; i++) {
			if (splitValues[i] != "") {
				selectedValues[i] = splitValues[i];
			}
		}
		for ( var i = 0; i < splitsText.length; i++) {
			if (splitsText[i] != "") {
				seelctedTexts[i] = splitsText[i];
			}
		}
		for ( var i = 0; i < selectedValues.length; i++) {
			var option = document.createElement("option");
			option.setAttribute("value", selectedValues[i]);
			option.innerHTML =seelctedTexts[i];
			document.getElementById('original').appendChild(option);
		}

		removeleft(concatValues, a);
		
		
	}
	 
	function addParticipant() {
				
	
		var a = document.getElementById('original');

		var b = document.getElementById('copy');
		var selectedValues = new Array();
		var seelctedTexts = new Array();
		var concatValues = "";
		var concattext="";
		var v = a.length;
		for ( var count = 0; count < a.length; count++) {

			if (a[count].selected) {
				concatValues = a[count].value + "@" + concatValues;
				concattext= a[count].text + "@" + concattext;
				//a[count].remove();
			}
		}

		var splitValues = concatValues.split("@");
        var splitsText=concattext.split("@");
		for ( var i = 0; i < splitValues.length; i++) {
			if (splitValues[i] != "") {
				selectedValues[i] = splitValues[i];
			}
		}
		for ( var i = 0; i < splitsText.length; i++) {
			if (splitsText[i] != "") {
				seelctedTexts[i] = splitsText[i];
			}
		}
		for ( var i = 0; i < selectedValues.length; i++) {
			var option = document.createElement("option");
			option.setAttribute("value", selectedValues[i]);
			option.innerHTML =seelctedTexts[i];
			//option.innerHTML = selectedValues[i];
			document.getElementById('copy').appendChild(option);
		}

		removeright(concatValues, a);
		
		
	}
	 function removeleft(concatValues, a) {
		
		
		var splitValues = concatValues.split("@");
		var selectedValues = new Array();
		for ( var i = 0; i < splitValues.length; i++) {
			if (splitValues[i] != "") {
				selectedValues[i] = splitValues[i];
			}
		}
		for ( var i = 0; i < selectedValues.length; i++) {
			for ( var j = 0; j < a.length; j++) {
				if (a[j].value == selectedValues[i]) {
				
					a[j].remove();
				
					
				}
			}
		}
		assignleft();
		
		if($("#copy option").length>0){
			$(".navbar-right").show();
		}
		else{
			$(".navbar-right").hide();
		}
	}
	 function removeright(concatValues, a) {
					
			var splitValues = concatValues.split("@");
			var selectedValues = new Array();
			for ( var i = 0; i < splitValues.length; i++) {
				if (splitValues[i] != "") {
					selectedValues[i] = splitValues[i];
				}
			}
			for ( var i = 0; i < selectedValues.length; i++) {
				for ( var j = 0; j < a.length; j++) {
					if (a[j].value == selectedValues[i]) {
					
						a[j].remove();
						
						
					}
				}
			}
			assignright();
			
			if($("#copy option").length>0){
				$(".navbar-right").show();
			}
			else{
				$(".navbar-right").hide();
			}
		}
		function getstageName(){
				
			var id = $("#event").val();
			$.ajax({
			type:'post',
			url:"EventsMenu.html?method=getstageList",
			async:false,
			data:{"id":id},
			success:function(data){
				var result = $.parseJSON(data);
				$("#stage").empty();
				$("#stage").append("<option value=''>-------select-------</option>");
				
				for(var i=0;i<result.data.length;i++){
				$("#stage").append("<option value='"+result.data[i].stageId+"'>" 
						+result.data[i].stageName+"</option>");
				}
			}
		});
		}
		
		function getUnAllocatedProgramList() {
						
			$("#errordiv").hide();
			var event= $("#event").val();
		
			$("#original").empty();
			$.ajax({
				      type : "POST",
						url : "EventsMenu.html?method=getUnAllocatedProgramList",
						data:{"event":event},
						success : function(response) {
							
							var result = $.parseJSON(response);
							var original = $("#original");
							
							for ( var j = 0; j < result.list.length; j++) {

								$(original)
										.append(
												'<option value="'
														+ result.list[j].progId
														+ '">'
														+ result.list[j].progName
														+ '</option>');
							}
						}
					});
		}
		
			
			
	
		function assignright() {
						var stage = $("#stage").val();
			var event=$("#event").val();
		
			if (event ==""||event==null) {
				showError("#event","Enter Event Name");
				return false;
			}else if(stage==""||stage==null)
				{
				showError("#stage","Enter Stage Name");
				return false;
				}
			
			else{
				
				$(".errormessagediv").hide();
				var b = document.getElementById('copy');	
			var updaterowsArray = new Array();
			
			for ( var i = 0; i <b.options.length; i++) {
				
				updaterowsArray.push(b.options[i].value);
				/*b.options[i].value = "";*/
			}

		
			$.ajax({

			   type: "POST",
				url : "EventsMenu.html?method=saveStageParticipantMapping",
				data : {
					"stageId" : stage,
					"event":event,
					"participantsDetails" : updaterowsArray.toString()
				},
				success : function(response) {
					var result = $.parseJSON(response);
			
				if (result.status=="true") {
					$('.errormessagediv').hide();
					$(".successmessagediv").show();
					$(".validateTips").text("Captain Allocation Done Successfully...");
					$(".successmessagediv").delay(2000).fadeOut();
					
					
				    	 
		    	}else{
		    		$('.errormessagediv').hide();
					$(".successmessagediv").show();
					$(".validateTips").text("Captain Allocation Done Successfully...");
					$(".successmessagediv").delay(2000).fadeOut();
					
				}
				}
			});

			}
		}

		function assignleft() {
				
			var stage = $("#stage").val();
			var event=$("#event").val();
		
			if (event ==""||event==null) {
				showError("#event","Enter Event Name");
				return false;
			}else if(stage==""||stage==null)
				{
				showError("#stage","Enter Stage Name");
				return false;
				}
			
			else{
				
				$(".errormessagediv").hide();
				var b = document.getElementById('copy');	
			var updaterowsArray = new Array();
					for ( var i = 0; i <b.options.length; i++) {
				
				updaterowsArray.push(b.options[i].value);
				/*b.options[i].value = "";*/
			}

		
			$.ajax({

			   type: "POST",
				url : "EventsMenu.html?method=saveStageParticipantMapping",
				data : {
					"stageId" : stage,
					"event":event,
					"participantsDetails" : updaterowsArray.toString()
				},
				success : function(response) {
					var result = $.parseJSON(response);
			
				if (result.status=="true") {
					$('.errormessagediv').hide();
					$(".successmessagediv").show();
					$(".validateTips").text("Captain UnAllocation   Done Successfully...");
					$(".successmessagediv").delay(2000).fadeOut();
				}else if(result.status=="false"){
		    		$('.successmessagediv').hide();
					$(".errormessagediv").show();
					$(".validateTips").text("Captain UnAllocation  Is Failed...");
					$(".errormessagediv").delay(2000).fadeOut();
					
				}
				else if(result.status=="failure"){
		    		$('.successmessagediv').hide();
					$(".errormessagediv").show();
					$(".validateTips").text("First delete this Students in Program Numbering...");
					$(".errormessagediv").delay(2000).fadeOut();
					
				}
				}
			});

			}
			
			
			
			
		}
		function stageDetailMapping() {
						

			
			var stageVal = $("#stage > option:selected").val();
			$("#copy").empty();
			if (stageVal != 0) {
				$.ajax({
					type : "POST",
					url :  "EventsMenu.html?method=getMappedParticipants",
					data : {"stageVal": stageVal},
					success : function(data) {
						var result= $.parseJSON(data);
					if( result.list.length==0)
						{
						$(".navbar-right").hide();
						
						}
					else
						{
						$(".navbar-right").show();
						}
				
						for ( var i = 0; i < result.list.length; i++) {
							$('#copy').append(
									'<option value="' + result.list[i].progId
											+ '">' +result.list[i].progName +'</option>');
						}
				
					}
				});
			}
			
		}
		function showError(id,errorMessage){
			
			$(id).css({
				"border":"1px solid #AF2C2C",
				"background-color":"#FFF7F7"
			});
			$(".form-control").not(id).css({
				"border":"1px solid #ccc",
				"background-color":"#fff"
			});
			$('.successmessagediv').hide();
			$(".errormessagediv").show();
			$(".validateTips").text(errorMessage);
			$(".errormessagediv").delay(2000).fadeOut();
		}	
		function HideError() 
		{
		document.getElementById("event").style.border = "1px solid #ccc";
		document.getElementById("event").style.backgroundColor = "#fff";
		}
		
	