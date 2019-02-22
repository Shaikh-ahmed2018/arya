function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}
$(document)
		.ready(
				function() {
					
					
					setTimeout("removeMessage()", 3000);
					setInterval(function() {
						$(".errormessagediv").hide();
					}, 3000);

					$('.accBody').css('display', 'none');
					$('.accBody:first').css('display', 'block');
					$('.accordHead').click(function() {
						var displaypro = $(this).next('div').css('display');
						if (displaypro == 'none') {
							$(this).next('div').css({
								'display' : 'block'
							});
						} else {
							$(this).next('div').css({
								'display' : 'none'
							});
						}
						$('div .accordHead:last-child', this).hide();
					});

					$('.addExam')
							.click(
									function() {
										window.location.href = "examTimetablePath.html?method=getAllSubjects&classAndExamId="
												+ this.id;

									});

				});