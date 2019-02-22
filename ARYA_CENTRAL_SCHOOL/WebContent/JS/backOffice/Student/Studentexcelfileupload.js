$(document).ready(function(){
	
	$("#save").click(function(){
		$("input[name=photoUpload]").change(function() {
		    var photoUpload = [];
		    for (var i = 0; i < $(this).get(0).files.length; ++i) {
		    	photoUpload.push($(this).get(0).files[i].photoUpload);
		    }
		  
		});
	});
});