$(document).ready(function () {

		var sPositions = localStorage.positions || "{}",
	    positions = JSON.parse(sPositions);
	$.each(positions, function (myclass, pos) {
	    $("." + myclass).css(pos);
	 
	});
	$(".main-div div").draggable({
	    containment: "#containment-wrapper",
	    scroll: false, 
	    stop: function (event, ui) {
	        positions[this.id] = ui.position;
	       
	       var myclass=$(this).attr("class").split(" ")[0];
	       positions[myclass]= ui.position;
	       localStorage.positions = JSON.stringify(positions);
	    }
	
	});
	
		$("#print").click(function(){
		var a=$("#printing-css").val();
		var b = document.getElementById("div").innerHTML;
		window.frames["print_frame"].document.title = document.title;
	    window.frames["print_frame"].document.body.innerHTML = '<style>' + a + '</style>' + b;
	    window.frames["print_frame"].window.focus();
	    window.frames["print_frame"].window.print();
		});
		$("#printA3").click(function(){
			var a=$("#printing-css-a3").val();
			var b = document.getElementById("div").innerHTML;
			window.frames["print_frame"].document.title = document.title;
		    window.frames["print_frame"].document.body.innerHTML = '<style>' + a + '</style>' + b;
		    window.frames["print_frame"].window.focus();
		    window.frames["print_frame"].window.print();
			});
		$("#saveChanges").click(function(){
			$(".successmessagediv").show();
			$(".successmessagediv span").text("Position Save Successfully");
			setTimeout(function(){
			location.reload();
			},2000);
			});
	});