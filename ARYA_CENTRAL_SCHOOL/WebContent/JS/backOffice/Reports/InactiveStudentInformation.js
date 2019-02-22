	$(document).ready(function(){	
		
	// for settings color js
		$("#accyear").val($("#hacademicyaer").val());
		$("#location").change(function(){
			getClassList();
			
			
		});
		
		$("#class").change(function(){
			getSectionList();
		});
		
		
		$("#dropdown").click(function(){
			$("#hbox").slideToggle("slow");
			
		});
	
		$('.col-md-10, .vertical').click(function(){
			$("#hbox").hide();
		});
		
		// for showing search destails
	
		$("#allstudent").hide();
		$(".selecteditems").hide();
		
		if($("#hideenId").val()!="" && $("#hideenId").val()!=undefined ){
			
			$(".selecteditems").show(1000);
			$("#allstudent").show(1000);
			$("#txtstyle, #txtstyle").slideToggle();
			
		}
	
		
		$("#search").click(function(){
			
			var accyear=$("#accyear").val();
			var stream=$("#stream").val();
			var classId=$("#class").val();
			var section=$("#section").val();
			
			if(accyear=="" && stream=="" && classId=="" && section==""){
				
				$("#txtstyle, #txtstyle").slideToggle();
				
				
			}
			
			if(accyear==""){
				
				$('.errormessagediv').show();
				$('.validateTips').text("Select Academic Year");
				
				return false;
				
			}if(stream==""){
				
				$('.errormessagediv').show();
				$('.validateTips').text("Select Stream");
				
				return false;
				
			}if(classId==""){
				
				$('.errormessagediv').show();
				$('.validateTips').text("Select Class");
				
				return false;
				
			}if(section==""){
				
				$('.errormessagediv').show();
				$('.validateTips').text("Select Section");
				
				return false;
				
			}else{
				
				return true;
			}
			
		});
		
		
		$("#excelDownload").click(function(){
			
			var haccyear=$("#haccyear").val();
			var hstream=$("#hstream").val();
			var hclass=$("#hclass").val();
			var hsection=$("#hsection").val();
			
			if(haccyear=="" && hstream=="" && hclass=="" && hsection=="" ){
				
				
				$('.errormessagediv').show();
				$('.validateTips').text("First Search the Student Details");
				
			}
			else{
				
				window.location.href = 'reportaction.html?method=geInactivetStudentDetailExcelsReport&AccId='
					+ $("#haccyear").val()
					+ ' &Stream='
					+ $("#hstream").val()
					+ ' &Class='
					+ $("#hclass").val()
					+ ' &Section='
					+ $("#hsection").val();
				
				
			}
			
			
		});
		
		$("#pdfDownload").click(function(){
			
			var haccyear=$("#haccyear").val();
			var hstream=$("#hstream").val();
			var hclass=$("#hclass").val();
			var hsection=$("#hsection").val();
			
			if(haccyear=="" && hstream=="" && hclass=="" && hsection=="" ){
				
				
				$('.errormessagediv').show();
				$('.validateTips').text("First Search the Student Details");
				
			}
			else{
				
				window.location.href = 'reportaction.html?method=geInactivetStudentDetailPDFReport&AccId='
					+ $("#haccyear").val()
					+ ' &Stream='
					+ $("#hstream").val()
					+ ' &Class='
					+ $("#hclass").val()
					+ ' &Section='
					+ $("#hsection").val();
				
			}
			
		});

		
		$("#stream").change(function(){
			
			var streamId=$("#stream").val();
			
			$.ajax({
				type : "GET",
				url : "reportaction.html?method=getClassesByStream",
				data : {"streamId":streamId},
				async : false,
				success : function(data) {
					
					var result = $.parseJSON(data);
					$("#class").html("");
					$("#class").append(
							'<option value="' + "" + '">' + ""
									+ '</option>');

					for (var j = 0; j < result.ClassesList.length; j++) {
						
						$("#class").append(
										'<option value="'
												+ result.ClassesList[j].classId
												+ '">'
												+ result.ClassesList[j].classname
												+ '</option>');
					}

				}

			});
			
		});
		
		
		$("#class").change(function(){
			
			var classId=$("#class").val();
			
			
			$.ajax({
				type : "GET",
				url : "reportaction.html?method=getSectionByClass",
				data : {"classId":classId},
				async : false,
				success : function(data) {
					var result = $.parseJSON(data);
					
					$("#section").html("");
					$("#section").append(
							'<option value="' + "" + '">' + ""
									+ '</option>');
					
					for (var j = 0; j < result.SectionList.length; j++) {
						

						$("#section").append(
										'<option value="'
												+ result.SectionList[j].sectionId
												+ '">'
												+ result.SectionList[j].sectionname
												+ '</option>');
					}
				
					
				
				}

			});
			
		});
	
});
	
	function getClassList(){
		var locationid=$("#location").val();
		datalist={
				"locationid" : locationid
		},

		$.ajax({

			type : 'POST',
			url : "studentRegistration.html?method=getClassByLocation",
			data : datalist,
			async : false,
			success : function(response) {

				var result = $.parseJSON(response);

				$('#class').html("");

				$('#class').append('<option value="all">' + "----------Select----------"	+ '</option>');

				for ( var j = 0; j < result.ClassList.length; j++) {

					$('#class').append('<option value="'

							+ result.ClassList[j].classcode + '">'

							+ result.ClassList[j].classname

							+ '</option>');
				}
			}
		});
	}

	function getSectionList()
	 {
		

		var classId=$("#class").val();
	

		$.ajax({
			type : "GET",
			url : "reportaction.html?method=getSectionByClass",
			data : {"classId":classId},
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);

				$("#section").html("");
				$("#section").append('<option value="all">' + "----------Select----------"	+ '</option>');

				for (var j = 0; j < result.SectionList.length; j++) {

					$("#section").append(
							'<option value="'
							+ result.SectionList[j].sectionId
							+ '">'
							+ result.SectionList[j].sectionname
							+ '</option>');

				}
			}
		});

	}
	
	
	
	
