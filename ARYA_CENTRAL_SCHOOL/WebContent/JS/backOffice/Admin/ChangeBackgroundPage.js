function readURL(input) {

	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$('#imagePreview').attr('src', e.target.result);
		};

		reader.readAsDataURL(input.files[0]);
		
	}

}

$(document).ready(function(){

	if($("#hFile").val()!=""){
		
		$('.download').show();
	
		$('#deleteProfile').show();
		$('#file').hide();
		
	}else{
		
		
		$('#file').show();
		
		$('#deleteProfile').hide();
		$('.download').hide();
	}
	
	
	
	$("#imagePreview").hide();

	$("#studentstatuslable").hide();


	$("#studentImageId1").change(function() {
		var fileval = $("#studentImageId1").val();

		if (fileval != undefined && fileval != ''){
			var extval = fileval.substring(fileval.lastIndexOf('.') + 1);
			
			if(extval != "jpeg" && extval != "jpg" && extval != "png"){
				$('.errormessagediv').show();
				$('.validateTips').text("Background Image accepts only .jpg & .png format");
				document.getElementById("studentImageId1").style.border = "1px solid #AF2C2C";
				document.getElementById("studentImageId1").style.backgroundColor = "#FFF7F7";
				setTimeout(	function() {
					$('#errorhover').fadeOut();
					document.getElementById("studentImageId1").style.border = "1px solid #ccc";
					document.getElementById("studentImageId1").style.backgroundColor = "#fff";
				}, 500);
				$('.errormessagediv').fontSize = "25px";
				$("#studentImageId1").val("");
				$("#imagePreview").hide();
				return false;
			}else{
				$("#imagePreview").show();
				readURL(this);
			}
		}
		
	});
	
	
	
	
	$('#save').click(function() {
		
		var formdata;
		formdata = new FormData();
		var file=$("#studentImageId1")[0].files[0];
		if(file != "" && file != undefined){

			formdata.append("inputfile",file); 

			$.ajax({
				url:'adminMenu.html?method=saveChangeImage',
				cache:false,
				type: 'POST',
				data:formdata,
				contentType: false,
				processData: false,

				beforeSend: function() {
					$('#loader').show();
				},

				success:function(data){


					$(".successmessagediv").show();
					$(".successmessagediv").attr("style","width:150%;margin-left:-270px;");
					$(".validateTips").text("Please wait! Change Background Progressing...");

				

					setTimeout(	function() {
						//location.reload();				
						window.location.href="adminMenu.html?method=changeBackground";
					}, 3000);
				}
			});
		}else{
			$('.errormessagediv').show();
			$('.validateTips').text("Please Add Background Image...");
			document.getElementById("studentImageId1").style.border = "1px solid #AF2C2C";
			document.getElementById("studentImageId1").style.backgroundColor = "#FFF7F7";
			setTimeout(	function() {
				$('#errorhover').fadeOut();
				document.getElementById("studentImageId1").style.border = "1px solid #ccc";
				document.getElementById("studentImageId1").style.backgroundColor = "#fff";
			}, 1000);
			
		}


});

    
    var path=$("#hFile").val();
    
	if(path !== "" && path!=undefined){
		
		$("#document1btn").attr('name',path);
		$("#file").hide();
		
		$(".download").show();
		$("#deleteProfile").show();
		
	}
	
	
/*	$('#imageInput').change(function(){
	    var frm = new FormData();
	    frm.append('imageInput', input.files[0]);
	    $.ajax({
	        method: 'POST',
	        address: 'url/to/save/image',
	        data: frm,
	        contentType: false,
	        processData: false,
	        cache: false
	    });
	});*/
	
	
	
	
	
	
	
});







