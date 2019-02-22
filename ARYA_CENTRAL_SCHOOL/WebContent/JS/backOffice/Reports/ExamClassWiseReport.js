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
		
		$("#class").click(function(){
			
			$("#hclass").val($("#class option:selected").text().trim());
		});
		
	
		
		
		$("#search").click(function(){
			
			var accyear=$("#accyear").val();
			var classid=$("#class").val();
		
			
			if(accyear=="" && classid==""){
				
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
				
			}else{
				
				return true;
			}
			
		});
		
		

		
		$("#excelDownload").click(function(){
			
			
		window.location.href ='reportaction.html?method=classWiseExamExcelReport&AccId='
			+ $("#haccyearid").val()
			+ ' &ClassId='
			+ $("#hclassid").val()
			+ '&ClassName='
			+ $("#classnameid").text();
			
				
		});
	
		$("#pdfDownload").click(function(){
			
			window.location.href = 'reportaction.html?method=classWiseExamPdfReport&AccId='
				+ $("#haccyearid").val()
				+ ' &ClassId='
				+ $("#hclassid").val()
				+ '&ClassName='
				+ $("#classnameid").text();
			
		});

	
});
	
	
	
	