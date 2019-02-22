function disableF5(e) { if ((e.which || e.keyCode) == 116 || (e.which || e.keyCode) == 82) e.preventDefault(); };
$(document).ready(function(){
	var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	if(pageUrl=="uploadLibraryXSL.html?method=insertCategoryTypeXSL"){
		if($(".errormessagediv span").text()==""){
			$(".successmessagediv").find("span").not(".validateTips").parents(".successmessagediv").attr("style","display:block");
			setTimeout(function(){
				window.location.href="LibraryMenu.html?method=categoryType";
			},3000);
		}
		if($(".errormessagediv span").text()!=""){
			 $(document).on("keydown", disableF5);
			setTimeout(function(){
				$(".errormessagediv").empty();
			},3000);
		}
	}
	$("#saveid").click(function(){
				var fileName=$("#studentfile").val().split('.').pop().toLowerCase();
				var fileNameCheck=$("#studentfile").val();
				if(fileNameCheck==""){
					 $(".validateTips").text("Select File");
						$(".errormessagediv").show();
					 $("#studentfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
					 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
					return false;
				}else if(fileName !="xlsx"){
					 $(".validateTips").text("Select Only Excel file with .xlsx extension.");
						$(".errormessagediv").show();
					 
					 $("#studentfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
					 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
					return false;
				}
				else{
					document.getElementById("excelfileupload").submit();
			 	}
			if(fileName=="xlsx" || fileName=="xls"){
				$("#Add").val("xls");
			 		$(".errormessagediv").hide();
			  		$("#studentfile").css({'backgroundColor' : 'transparent','border':'1px solid #ccc'});
					return true;
				}else{
					 $(".errormessagediv").show();
						$(".validateTips").text("Select Excel File Only");
						 $("#studentfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
						 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
					return false;
				}
         });
   });