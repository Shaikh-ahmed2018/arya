	$(document).ready(function(){	
		
	// for settings color js
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
		
		$("#accyear").click(function(){
			
			$("#haccyear").val($("#accyear option:selected").text().trim());
		});
		
		$("#section").click(function(){
			
			$("#hsection").val($("#section option:selected").text().trim());
		});
		
		$("#term").click(function(){
			
			$("#hterm").val($("#term option:selected").text().trim());
		});
		
		
		$("#search").click(function(){
			
			var accyear=$("#accyear").val();
			var classid=$("#class").val();
			var section=$("#section").val();
			var term=$("#term").val();
			
			if(accyear=="" && classid=="" && section=="" && term==""){
				
				$("#txtstyle, #txtstyle").slideToggle();
				
				
			}
			
			if(accyear==""){
				
				$('.errormessagediv').show();
				$('.validateTips').text("Select Academic Year");
				
				return false;
				
			}if(classid==""){
				
				$('.errormessagediv').show();
				$('.validateTips').text("Select Class");
				
				return false;
				
			}if(section==""){
				
				$('.errormessagediv').show();
				$('.validateTips').text("Select Section");
				
				return false;
				
			}if(term==""){
				
				$('.errormessagediv').show();
				$('.validateTips').text("Select term");
				
				return false;
				
			}else{
				
				return true;
			}
			
		});
		
		
		$("#excelDownload").click(function(){
			
		window.location.href = 'feecollectionsummaryreport.html?method=feeCollectionExcelReport&AccId='
				+ $("#haccyearid").val()
				+ ' &ClassId='
				+ $("#hclassid").val()
				+ ' &SectionId='
				+ $("#hsectionid").val()
				+ ' &TermId='
				+ $("#htermid").val()
				+'&TermName='
				+$("#termname").text().trim();
			
		});
		
		$("#pdfDownload").click(function(){
			
			window.location.href = 'feecollectionsummaryreport.html?method=feeCollectionPdfReport&AccId='
				+ $("#haccyearid").val()
				+ ' &ClassId='
				+ $("#hclassid").val()
				+ ' &SectionId='
				+ $("#hsectionid").val()
				+ ' &TermId='
				+ $("#htermid").val()
				+'&TermName='
				+$("#termname").text().trim();
			
			
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
					
					$("#section").append(
							'<option value="' + "all" + '">' + "All"
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
	