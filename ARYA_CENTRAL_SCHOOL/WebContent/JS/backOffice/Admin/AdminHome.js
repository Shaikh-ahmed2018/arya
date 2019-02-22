$(document).ready(function() {

	$('.pagebanner').hide();
	$('.pagelinks').hide();
});

function moreEvents() {
	window.location.href="adminMenu.html?method=viewMoreEvents";
}

function moreMeetings() {
	window.location.href = "adminMenu.html?method=viewMoreMeeting";
}

function moreRemainders() {
	window.location.href = "adminMenu.html?method=viewMoreReminder";
}