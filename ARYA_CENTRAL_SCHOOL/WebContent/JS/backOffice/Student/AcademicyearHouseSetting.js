$(document).ready(function(){


	$(".grade").click(function(){
		$("#gradeOne").slideToggle();
	});	
	
	$("#housename").keyup(function(){
		$(this).val($(this).val().toUpperCase());
	});
	
	$("#housenameedit").keyup(function(){
		$(this).val($(this).val().toUpperCase());
	});
	
	
	$('.editing').click(function() {
		
		editid=$(this).attr("id");
		
		housename = $(this).parent("td").parent("tr").find(".housename").text();
		
			 $("#housenameedit").val(housename);
			 $("#hiddenhousename").val(housename);
		$("#editDialog").dialog("open");
	});
	
	
	$('.delete').click(function() {
		
		deleteid=$(this).attr("id");
		$("#deleteDialog").dialog("open");
	});
	
	$('#addgrade').click(function() {
		$("#addDialog").dialog("open");
	});
	
	
	$("#deleteDialog").dialog({
		autoOpen  : false,
		maxWidth  :	300,
		maxHeight : 200,
		width     : 300,
		height    : 200,
		modal     : true,
		title     : "Edit House",
		buttons   : {
			'Yes'  : function() {
				 deleteHouse(deleteid);
				$(this).dialog('close');
			},
			'Close' : function() {
				$(this).dialog('close');
				$(".errormessagediv").hide();
			}
		}
	});
	
	$("#editDialog").dialog({
		autoOpen  : false,
		maxWidth  :	500,
		maxHeight : 250,
		width     : 500,
		height    : 250,
		modal     : true,
		title     : "Edit House",
		buttons   : {
			'Save'  : function() {
				
				 var housename1 = $("#housenameedit").val();
				 var house = $("#hiddenhousename").val();
				 var locid = $("#hiddenlocid").val();
				 var accid = $("#hiddenaccyear").val();
				 
				 if(housename1 == "" || housename1 == null){
    				 $(".errormessagediv").show();
    					$(".validateTips").text("Field Required - House Name");
    					document.getElementById("housenameedit").style.border = "1px solid #AF2C2C";
    					document.getElementById("housenameedit").style.backgroundColor = "#FFF7F7";
    					setTimeout(function() {
    						$('.errormessagediv').fadeOut();
    					}, 3000);
    					return false;
    			 }
				 else if(house!=housename1){
					  if(checkduplicateHouse(housename1,locid,accid)==1){
	    				 $(".errormessagediv").show();
	    		    	 $("#housenameedit").val("");
	    					$(".validateTips").text("House Name Already Exists for Selected Accademic Year and Location");
	    					document.getElementById("housenameedit").style.border = "1px solid #AF2C2C";
	       					document.getElementById("housenameedit").style.backgroundColor = "#FFF7F7";
	    					setTimeout(function() {
	    						$('.errormessagediv').fadeOut();
	    					}, 3000);
	    					 return false;
	    			 }
					  else{
							 edit(housename1,locid,accid,editid);
			    			 $(this).dialog('close');
			    		 }
				 }
				 else{
					 
				 edit(housename1,locid,accid,editid);
    			 $(this).dialog('close');
    			 }
			},
			'Close' : function() {
				$(this).dialog('close');
				 $(".errormessagediv").hide();
				document.getElementById("housenameedit").style.border = "1px solid #CCC";
				document.getElementById("housenameedit").style.backgroundColor = "#FFF";
			}
		}
	});

	$("#addDialog").dialog({
		autoOpen  : false,
		maxWidth  :	500,
		maxHeight : 250,
		width     : 500,
		height    : 250,
		modal     : true,
		title     : "Add House",
		buttons   : {
			'Save'  : function() {
				
				 var housename = $("#housename").val();
				 var locid = $("#hiddenlocid").val();
				 if(locid == "%%"){
					 locid = "all"
				 }
				 var accid = $("#hiddenaccyear").val();
				 if(housename == "" || housename == null){
   				 $(".errormessagediv").show();
   					$(".validateTips").text("Field Required - House Name");
   					document.getElementById("housename").style.border = "1px solid #AF2C2C";
   					document.getElementById("housename").style.backgroundColor = "#FFF7F7";
   					setTimeout(function() {
   						$('.errormessagediv').fadeOut();
   					}, 3000);
   			 }else if(checkduplicateHouse(housename,locid,accid)==1){
   				$(".errormessagediv").show();
		    	 $("#housename").val("");
					$(".validateTips").text("House Name Already Exists for Selected Accademic Year and Location");
					document.getElementById("housename").style.border = "1px solid #AF2C2C";
   					document.getElementById("housename").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
   			 }
			else{
				 addHouse(housename,locid,accid);
   			 $(this).dialog('close');
			}
		},
			'Close' : function() {
				$(this).dialog('close');
				$(".errormessagediv").hide();
				document.getElementById("housename").style.border = "1px solid #CCC";
				document.getElementById("housename").style.backgroundColor = "#FFF";
			}
		}
	});
	pagination(100);
});

function addHouse(housename,locid,accid){
	
	datalist = {
			"housename" : housename,
			"locid" : locid,
			"accid" : accid
	};
	
	$.ajax({
		
		type : "POST",
		url : "houseSettings.html?method=savehouseSettings",
		data : datalist,
		async : false,
		success : function(data){
			var result = $.parseJSON(data);
			
			if(result.status == "true"){
		    	 $(".successmessagediv").show();
					$(".validateTips").text("Adding Record Progressing...");
					setTimeout(function() {
						$('.successmessagediv').fadeOut();
						location.reload();
					}, 3000);
		     }
		}
	});
}

function edit(housename,locid,accid,editid){

	datalist = {
			"housename" : housename,
			"locid" : locid,
			"accid" : accid,
			"editid" :editid
	};
	
	$.ajax({
		
		type : "POST",
		url : "houseSettings.html?method=edithouseSettings",
		data : datalist,
		async : false,
		success : function(data){
			var result = $.parseJSON(data);
			
			if(result.status == "true"){
		    	 $(".successmessagediv").show();
					$(".validateTips").text("Updating Record Progressing...");
					setTimeout(function() {
						$('.successmessagediv').fadeOut();
						location.reload();
					}, 3000);
		     }
		}
	});

}

function deleteHouse(deleteid){
	
	var locid = $("#hiddenlocid").val();
	 var accid = $("#hiddenaccyear").val();
	 
	 datalist = {
		"locid" : locid,
		"accid" : accid,
		"deleteid" :deleteid
	 };
	
	 $.ajax({
			type : "POST",
			url : "houseSettings.html?method=deletehouseSettings",
			data : datalist,
			async : false,
			success : function(data){
				var result = $.parseJSON(data);
				
				if(result.status == "true"){
			    	 $(".successmessagediv").show();
						$(".validateTips").text("Deleting Record Progressing...");
						setTimeout(function() {
							$('.successmessagediv').fadeOut();
							location.reload();
						}, 3000);
			     }else{
			    	 $(".errormessagediv").show();
						$(".validateTips").text("House is Mapped Cannot be Deleted");
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
			     }
			}
		});
}

function checkduplicateHouse(housename,locid,accid){
	 
	var count = 0;
	
	 datalist = {
			"housename" :housename,
			"locid" : locid,
			"accid" : accid
	 };
	 
	 
	 
	 $.ajax({
			
			type : "POST",
			url : "houseSettings.html?method=checkduplicateHouse",
			data : datalist,
			async : false,
			success : function(data){
				var result = $.parseJSON(data);
				
				if(result.status == "true"){
					count = 1;
			     }
				else{
					count = 0;
				}
			}
		});
	 return count;
}

