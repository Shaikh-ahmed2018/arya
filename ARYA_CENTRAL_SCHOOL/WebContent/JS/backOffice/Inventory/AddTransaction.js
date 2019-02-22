$(document).ready(function(){

$('#saveid').click(function() {
	alert("hello");
	$("#InventoryTransactionForm").submit();
	
	
});
$("#issued_date").datepicker({
	dateFormat : "dd-mm-yy",
	maxDate : 0,
	changeMonth : "true",
	changeYear : "true",
	buttonImage : "images/calendar.GIF",
	buttonImageOnly : true


});

$("#returned_date").datepicker({
	dateFormat : "dd-mm-yy",
	maxDate : 0,
	changeMonth : "true",
	changeYear : "true",
	buttonImage : "images/calendar.GIF",
	buttonImageOnly : true
});
$("input[name='time']").timeInput();

	
});
