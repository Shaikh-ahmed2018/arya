var val_val1 = [];
var val_val2 = [];
var val_val3 = [];

$(document).ready(function(){	
		
	$("#accyear").val($("#hacademicyaer").val());
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

	
		
	// for settings color js
		
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
	
		/*$("#allstudent").hide();
		$(".selecteditems").hide();*/
		
		if($("#hideenId").val()!="" && $("#hideenId").val()!=undefined ){
			
			$(".selecteditems").show(1000);
			$("#allstudent").show(1000);
			$("#txtstyle, #txtstyle").slideToggle();
			
		}
		
		$("#accyear").click(function(){
			
			$("#haccyear").val($("#accyear option:selected").text().trim());
		});
		
		$("#section").click(function(){
			
			$("#hsection").val($("#section option:selected").text().trim());
		});
		
		$("#exam").click(function(){
			
			$("#hexam").val($("#exam option:selected").text().trim());
		});
		
		$("#student").click(function(){
			
			$("#hstudent").val($("#student option:selected").text().trim());
		});
		
		
		$("#search").click(function(){
			
			var accyear=$("#accyear").val();
			var classid=$("#class").val();
			var section=$("#section").val();
			var exam=$("#exam").val();
			var student=$("#student").val();
			
			if(accyear=="" && classid=="" && section=="" && exam==""){
				
				$("#txtstyle, #txtstyle").slideToggle();
				
			}
			
			if(accyear==""){
				
				$('.errormessagediv').show();
				$('.validateTips').text("Select Academic Year");
				
				return false;
				
			}else if(classid==""){
				
				$('.errormessagediv').show();
				$('.validateTips').text("Select Class");
				
				return false;
				
			}else if(section==""){
				
				$('.errormessagediv').show();
				$('.validateTips').text("Select Section");
				
				return false;
				
			}else if(exam==""){
				
				$('.errormessagediv').show();
				$('.validateTips').text("Select Exam");
				
				return false;
				
			}else if(student.trim()==""){
				
				$('.errormessagediv').show();
				$('.validateTips').text("Select Student");
				
				return false;
				
			}
				else{
				
				return true;
			}
			
		});
		
		

		
		$("#excelDownload").click(function(){
			
			
		window.location.href ='reportaction.html?method=studentMarksExcelReport&AccId='
			+ $("#haccyearid").val()
			+ ' &ClassId='
			+ $("#hclassid").val()
			+ '&SectionId='
			+ $("#hsectionid").val()
			+ '&Exam='
			+ $("#hexamid").val()
			+ '&Student='
			+ $("#hstudentid").val();
				
		});
	
		$("#pdfDownload").click(function(){
			
			window.location.href = 'reportaction.html?method=studentMarksPdfReport&AccId='
				+ $("#haccyearid").val()
				+ ' &ClassId='
				+ $("#hclassid").val()
				+ '&SectionId='
				+ $("#hsectionid").val()
				+ '&Exam='
				+ $("#hexamid").val()
				+ '&Student='
				+ $("#hstudentid").val();
			
			
		});

		
		$("#class").change(function(){
			
			var classId=$("#class").val();
			
			$("#hclass").val($("#class option:selected").text().trim());
			
			
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

		
		
	$("#section").change(function(){
			
			var classId=$("#class").val();
			var sectionId=$("#section").val();
			
			
			$.ajax({
				type : "GET",
				url : "reportaction.html?method=getStudentBySection",
				data : {"classId":classId,"sectionId":sectionId},
				async : false,
				success : function(data) {
					var result = $.parseJSON(data);
					
					$("#student").html("");
					$("#student").append(
							'<option value="' + "" + '">' + ""
									+ '</option>');
					
					for (var j = 0; j < result.studentList.length; j++) {
						

						$("#student").append(
										'<option value="'
												+ result.studentList[j].studentid
												+ '">'
												+ result.studentList[j].studentFnameVar
												+ '</option>');
					}
				
					
				
				}

			});
			
		});
	
	
	$.ajax({
		type : "GET",
		url : "reportaction.html?method=studentMarksGraph",
		data : {
			"AccId":$("#haccyearid").val(),
			"ClassId":$("#hclassid").val(),
			"SectionId":$("#hsectionid").val(),
			"Exam":$("#hexamid").val(),
			"Student":$("#hstudentid").val()
			},
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);
			
			
			
			for (var j = 0; j < result.MARKSDETAILS.length; j++) {
				
				val_val1.push(result.MARKSDETAILS[j].subjectname);
				
				val_val2.push(result.MARKSDETAILS[j].maxmarks);
				
				val_val3.push(result.MARKSDETAILS[j].scoredmarks);
			}
		
			
		
		}

	});
	
	
	
	
});
	
	
	function createChart() {
		
		
		$(".studentAttendance").kendoChart({
			title : {
				text : "Student Marks Report"
			},
			legend : {
				position : "top"
			},
			seriesDefaults : {
				type : "column",
				
			},
			series : [ {
				name : "Max Marks",
				data : val_val2
			}, {
				name : "Scored Marks",
				data : val_val3
			} ],
			valueAxis : [ {
				title : {
					text : "Total"
				},
				min : 0,
				max : 100,
				majorGridLines : {
					visible : true
				},
			} ],
			
			categoryAxis : {
				categories : val_val1,
				line : {
					visible : false
				},
				labels : {
					rotation : 320,
				}

			},
			
		});
	}

	$(document).ready(createChart);
	$(document).bind("kendo:skinChange", createChart);
	
	
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
	