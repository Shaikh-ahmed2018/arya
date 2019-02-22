$(document).ready(function(){
	
	
	$("#employee, #other").click(function(){
		if($("#employee").val() == "employee"||$("#other").val() == "other"){	
			$("#sectionidname").hide();
			$("#sectionid").hide();
			$("#classnamess").hide();
			$("#classname").hide();
			$("#classrow").hide();
			$("#divrow").hide();
		}else{
			$("#sectionidname").show();
			$("#sectionid").show();
			$("#classnamess").show();
			$("#classname").show();
		}
	});
	
	
	$("#student").click(function() {
				$("#sectionidname").show();
				$("#sectionid").show();
				$("#classnamess").show();
				$("#classname").show();
				$("#classrow").show();
				$("#divrow").show();
			});
	
	getClassList();
	

	$("#locationname").change(function(){
		getClassList();
	});
	
	$("#classname").change(function(){
		var classname=$('#classname').val();
		getSectionList(classname);
	});
		
});
	
	
	
function getClassList(){

	var locationid=$("#locationname").val();
	datalist={
			"locationid" : locationid
	},

	$.ajax({

		type : 'POST',
		url : "LibraryMenu.html?method=getClassByLocation",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#classname').html("");

			$('#classname').append('<option value="all">' +"All"+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#classname').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
}

function getSectionList(classname){
	datalist={
			"classidVal" : classname,
			"locationId":$("#locationname").val()
	},
	
	$.ajax({
		
		type : 'POST',
		url : "LibraryMenu.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#sectionid').html("");
			
			$('#sectionid').append('<option value="">' + "All"	+ '</option>');
			
			for ( var j = 0; j < result.sectionList.length; j++) {

				$('#sectionid').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});
}