
$(document).ready(function() {
	$("#accyear").val($("#hacademicyaer").val());
	$('#excelDownload').click(function() {
				window.location.href = 'teacherLeaveDetailsReport.html?method=LeaveReportDownloadXLs';
			});
	
	$("#accyear").click(function(){
		$("#haccyear").val($("#accyear option:selected").text().trim());
	});
	
	$("#teachername").click(function(){
		$("#hteachername").val($("#teachername option:selected").text().trim());
	});
	$("#dropdown").click(function(){
		$("#hbox").slideToggle("slow");
	});
$("#teachertype").change(function(){
	getTeacherName();
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
		var departmet=$("#deptid").val();
		var teachingtype=$("#teachertype").val();
		var teachername=$("#teachername").val();
		
		if(accyear=="" && departmet=="" && teachingtype=="" && teachername==""){
			
			$("#txtstyle, #txtstyle").slideToggle();
			
			
		}
		
		if(accyear==""){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select Academic Year");
			
			return false;
			
		}if(departmet==""){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select Department");
			
			return false;
			
		}if(teachingtype==""){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select Teaching type");
			
			return false;
			
		}if(teachername==""){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select Teacher");
			
			return false;
			
		}else{
			
			return true;
		}
		
	});
	$("#print").click(function(){
		
		
		var a=$("#printing-css").val();
		var b = document.getElementById("printarea").innerHTML;

	    var abd='<style>' + a +'</style>' + b;
	
	   
	    
    var frame1 = $('<iframe />');
    frame1[0].name = "frame1";
    frame1.css({ "position": "absolute", "top": "-1000000px" });
    $("body").append(frame1);
    var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
    frameDoc.document.open();
    //Create a new HTML document.
    frameDoc.document.write('<html><head><title>DIV Contents</title>');
    //Append the external CSS file.
    frameDoc.document.write('<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />');
    frameDoc.document.write('<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">');
    frameDoc.document.write('<link href="CSS/newUI/modern-business.css" rel="stylesheet">');
    frameDoc.document.write('<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">');
    frameDoc.document.write('<link href="CSS/newUI/custome.css" rel="stylesheet">');
 
    frameDoc.document.write('</head><body>');
  
    
    //Append the DIV contents.
    frameDoc.document.write(abd);
    frameDoc.document.write('</body></html>');
    frameDoc.document.close();
    setTimeout(function () {
        window.frames["frame1"].focus();
        window.frames["frame1"].print();
        frame1.remove();
    }, 100);
	    
	});

});

function getTeacherName(){
	var teachertype=$("#teachertype").val();
		datalist={
				"teachertype" : teachertype,
				"department" : "all"
				
		},
		
		$.ajax({
			
			type : 'POST',
			url : "teacherLeaveDetailsReport.html?method=getTeachername",
			data : datalist,
			async : false,
			success : function(response) {
				var result = $.parseJSON(response);
				$('#teachername').html("");
				$('#teachername')
				.append('<option value="'
						+ ""
						+'">'
						+ ""
						+ '</option>');
				if(result.TeacherList.length!=0){
					$('#teachername').append(
							'<option value="'
									+ "all" + '">'
									+ "ALL"
									+ '</option>');
				}
		for ( var j = 0; j < result.TeacherList.length; j++) {
			$('#teachername')
					.append(
							'<option value="'
									+ result.TeacherList[j].teacherId
									+ '">'
									+ result.TeacherList[j].teacherName
									+ '</option>');
		}
				
			}
			
			
		});
		

}