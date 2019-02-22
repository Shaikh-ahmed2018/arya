$(document).ready(function() {
			
			$('#popUp').click(function() {
				$("#admissionDialog").dialog("open");
				assignMachine();
			});
			
			$("#admissionDialog").dialog({
			    autoOpen  : false,
			    maxWidth  :	750,
		        maxHeight : 500,
		        width     : 550,
		        height    : 200,
			    modal     : true,
			    title     : "Machine List",
			    buttons   : {
			    	'Save'  : function() {
			    		
			    		
			    	},
			    	'Close' : function() {
		                 $(this).dialog('close');
		             }
			    }
			});
});

function assignMachine(){
alert($("#yearIdHidden").val());
	$.ajax({
		type:'POST',
		url :"ElectionMenu.html?method=getVotingMachine",
		data:{
			"accid": $("#yearIdHidden").val(),
			},
		async :false,
		success :function(data){
			var result = $.parseJSON(data);
		
			$('#stream').empty();
			$('#stream').append('<option value="all">----Select----</option>');
			for( var j=0;j<result.machine.length; j++) {
				$('#stream').append('<option value="'+result.machine[j].pollingMachineId+ '">'
						+result.machine[j].pollingMachineName+ '</option>'
				);
			}
		
			}

		});//end

	
}