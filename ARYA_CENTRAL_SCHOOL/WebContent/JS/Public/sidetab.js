
$(document).ready(function(){
	
//	   $("#Noticeboard").text("hiiiR");
			 
			 $.ajax({
					type : "POST",
					url : "publicMenu.html?method=loadNoticeBoardFirstRecords",
					success : function(data) {
						var obj = $.parseJSON(data);
						var nb = obj["first"];
						 $("#Noticeboard").text(nb[0]["title"]);
					 
					}
					});
			 
//          $("#Noticeboard").text("hiiiR");
			 
			 $.ajax({
					type : "POST",
					url : "publicMenu.html?method=loadEventsFirstRecords",
					success : function(data) {
						var obj = $.parseJSON(data);
						var event = obj["firstEvent"];
						 $("#Event").text(event[0]["eventName"]);
					 
					}
					});	 
			 
});
