$(document).ready(function() {
	
		$("#back").click(function(){
			
			window.location.href="adminMenu.html?method=studentConfidentialReport";

		});
		
		
		
		
		$('.edit').click(function() {
			$("#admissionDialog").dialog("open");
		});
		
		$('#addappraisal').click(function() {
			$("#admissionDialog").dialog("open");
		});
		
		$("#admissionDialog").dialog({
		    autoOpen  : false,
		    maxWidth  :	750,
	        maxHeight : 500,
	        width     : 750,
	        height    : 500,
		    modal     : true,
		    title     : "Add Appraisal",
		    buttons   : {
		    	'Save'  : function() {
		    		
		    		
		    	},
		    	'Close' : function() {
	                 $(this).dialog('close');
	             }
		    }
		});
		
});