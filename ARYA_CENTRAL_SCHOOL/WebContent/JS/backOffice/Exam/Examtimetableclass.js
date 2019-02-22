$(document).ready(function(){
	
	
	getexamsbtselection();
	getclasslist($('#exam'));
	$('#exam').change(function(){
		getclasslist($('#exam'));
		$("#allstudent tbody tr").click(function(){
			
			var accyear = $("#hiddenaccyear").val();
			var locid = $("#hiddenloc").val();
			var classid  = $(this).find(".classid").attr("id");
			var classname = $(this).find(".classid").text();
			var examid = $("#exam").val();
			
			if(examid == "" || examid == undefined){
				$('.errormessagediv').show();
				$('.validateTips').text("Field Required - Exam");
				document.getElementById("exam").style.border = "1px solid #AF2C2C";
				document.getElementById("exam").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
			}else{
				window.location.href = "examTimetablePath.html?method=getexamtimetableclasssection&accyear="+accyear+
				"&locid="+locid+"&classid="+classid+"&classname="+classname+"&examid="+examid;
			}
			
		});
	});
	
	$(".class").click(function(){
		$("#classOne").slideToggle();
	});
	$("#allstudent tbody tr").click(function(){
		
		var accyear = $("#hiddenaccyear").val();
		var locid = $("#hiddenloc").val();
		var classid  = $(this).find(".classid").attr("id");
		var classname = $(this).find(".classid").text();
		var examid = $("#exam").val();
		
		if(examid == "" || examid == undefined){
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required - Exam");
			document.getElementById("exam").style.border = "1px solid #AF2C2C";
			document.getElementById("exam").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}else{
			window.location.href = "examTimetablePath.html?method=getexamtimetableclasssection&accyear="+accyear+
			"&locid="+locid+"&classid="+classid+"&classname="+classname+"&examid="+examid;
		}
		
	});
	
	$("#back").click(function(){
		window.location.href = "examTimetablePath.html?method=getEaxmTimeTableListYear";
	});
	
});

function getexamsbtselection(){
	
	$.ajax({
		 	type: "POST",
		 	url: "examTimetablePath.html?method=getexamsbtselection",
		 	data:{"location":$("#hiddenloc").val(),"accyear":$("#hiddenaccyear").val()},
		 	async:false,
		 	success: function(data) {
				var result = $.parseJSON(data);
				$('#exam').html("");

				/*$('#exam').append('<option value="">' + "----------Select----------"	+ '</option>');*/

				for ( var j = 0; j < result.examlist.length; j++) {

					$('#exam').append('<option value="'

							+ result.examlist[j].examid + '">'

							+ result.examlist[j].examName

							+ '</option>');
				}
			}
	});
}
function getclasslist(pointer){
	$.ajax({
		
		type : "POST",
		url : "examTimetablePath.html?method=getclasslist",
		data:{"location":$("#hiddenloc").val(),"accyear":$("#hiddenaccyear").val(),"examcode":pointer.val()},
		async : false,
		success : function(data){
			var result = $.parseJSON(data);
			
			$("#mytable").empty();
			$("#mytable").append("<table class='table' id='allstudent' width='100%'>"+"<thead><tr>"+
					"<th>Sl.No</th>" +
					"<th>Classname</th>" +
					"<th>Status</th>"+
					"</tr></thead><tbody></tbody>" +
					"</table>"
					);

			for(var i=0;i<result.ClassList.length;i++){
				
				$("#mytable #allstudent").append(
						"<tr>"+
						"<td>"+result.ClassList[i].sno1+"</td>"+
						"<td class='classid' id='"+result.ClassList[i].classId+"'>"+result.ClassList[i].classname+"</td>"+
						"<td><span class='"+result.ClassList[i].status1+"'>"+result.ClassList[i].status+"</span>&nbsp;&nbsp;<i>"+result.ClassList[i].setmsg+"</i></td>"
						+"</tr>"
				);
				
			}

		}
	});
}