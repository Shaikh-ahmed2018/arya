$(document).ready(function(){
	
	$(".class").click(function(){
		$("#classOne").slideToggle();
	});
	
	$("#house").click(function(){
		
		 $("#dialog").dialog("open");
	
	});
	
	$("#dialog").dialog({
		autoOpen  : false,
	    modal     : true,
	    title     : "Re-Generate House Details",
	    buttons   : {
	    	"Save": {
	    		text: 'Yes',
	    		
	    		 click: function() {	 
	    			 
	    			 displaydet();
	    			 $(this).dialog('close');
	    		 }
	    	},
	    	'No' : function() {
                $(this).dialog('close');
	    	}
	    }
	});
	
	
	
	$("#back").click(function(){
		
		var selection = $("#filter").val();
		var accyear = $("#hiddenaccyear").val();
		var locid = $("#hiddenlocid").val();
		if(selection == "Name Wise"){
			window.location.href="houseSettings.html?method=HouseSettingClassWise&accyear="+accyear+"&locid="+locid;
		}else if(selection == "Name Wise Desc"){
			window.location.href="houseSettings.html?method=ordernameDescWise&accyear="+accyear+"&locid="+locid;
		}else if(selection == "By Admission No. Odd"){
			window.location.href="houseSettings.html?method=orderadmiOddWise&accyear="+accyear+"&locid="+locid;
		}else if(selection == "By Admission No."){
			window.location.href="houseSettings.html?method=houseadmiWise&accyear="+accyear+"&locid="+locid;
		}else if(selection == "By Admission No. Desc"){
			window.location.href="houseSettings.html?method=orderadmiDescWise&accyear="+accyear+"&locid="+locid;
		}else if(selection == "By Admission No. Even"){
			window.location.href="houseSettings.html?method=orderadmiEvenWise&accyear="+accyear+"&locid="+locid;
		}
		
		
	});
	
});


function  displaydet(){
	 $.ajax({
			type : "POST",
			url : "houseSettings.html?method=regenerateHousedetails",
			data : {"location":$("#hiddenlocid").val(),"accyear":$("#hiddenaccyear").val(),"genhouid":$("#hiddengen").val()},
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);
				if(result.status =="true" ) {
					$(".successmessagediv").show();
					$(".validateTips").text("Progressing...");
					setTimeout(function(){
						window.location.href="houseSettings.html?method=HouseSettingClassWise&accyear="+$("#hiddenaccyear").val()+"&locid="+$("#hiddenlocid").val(); 
               },3000);
				}
			}
		});
}