$(document).ready(function(){
	getJournalSubscriptioncodelist();
	
	
	
	
   $("#code").change(function(){
	getnamelist();
});
	

});

function getnamelist(){

	$.ajax({

		type : 'POST',
		url : "LibraryMenu.html?method=getnamelist",
		
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#name').html("");

			$('#name').append('<option value="ALL">' + "ALL"+'</option>');

			for ( var j = 0; j < result.response.length; j++) {
                  
				$('#name').append('<option value="'

						+ result.response[j].entryId + '">'

						+ result.response[j].name

						+ '</option>');
			}
		}
	});
}


function getJournalSubscriptioncodelist(){

	$.ajax({

		type : 'POST',
		url : "LibraryMenu.html?method=getJournalSubscriptioncodelist",
		
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#code').html("");

			$('#code').append('<option value="ALL">' + "ALL"+'</option>');

			for ( var j = 0; j < result.response.length; j++) {
                 
				$('#code').append('<option value="'

						+ result.response[j].entryId + '">'

						+ result.response[j].code

						+ '</option>');
			}
		}
	});
}













