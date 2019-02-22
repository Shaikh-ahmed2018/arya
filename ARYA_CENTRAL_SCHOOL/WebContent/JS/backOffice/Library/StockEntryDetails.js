$(document).ready(function() {
	
	$("input,select,textarea").on({
		keypress: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	change: function(){
		if(this.value.length>0 ){
		hideError("#"+$(this).attr("id"));
		
		$(".errormessagediv").hide();
		}
	},
	});
	
	if($("#test").val()=="test"){
		$("#backid").show();
		$("#back").hide();
		$("#save").hide();
		$("#pageHeading").hide();
		$("#pageHeading1").show();
		
		$("#accessionNo").prop("readonly",true);
		$("#itemType").prop("readonly",true);
		$("#Fromdate").prop("readonly",true);
		$("#booktitle").prop("readonly",true);
		$("#author").prop("readonly",true);
		$("#classdesc").prop("readonly",true);
		$("#publisher").prop("readonly",true);
		$("#nocopy").prop("readonly",true);
		$("#cost").prop("readonly",true);
		$("#edition").prop("readonly",true);
		$("#iSBNNo").prop("readonly",true);
		$("#billNo").prop("readonly",true);
		$("#size").prop("readonly",true);
		$("#generalinfo").prop("readonly",true);
		$("#pubyear").prop("readonly",true);
		$("#totalcost").prop("readonly",true);
		$("#editor").prop("readonly",true);
		$("#pages").prop("readonly",true);
		$("#contentSearch").prop("readonly",true);
		$("#selfno").prop("readonly",true);
	}
	
	
	var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	var url=pageUrl.split("&");
	var finalurl=url[0];
	
	if(finalurl=="LibraryStockEntry.html?method=saveStockEnteryDetails")
	{
		if($(".errormessagediv  .validateTips").text()==""){
			$(".successmessagediv").show();
			setTimeout(function(){
				window.location.href="LibraryMenu.html?method=LibraryStockEntryDetailsList";
			},2000);
		}
		
	}
	
	if(finalurl=="LibraryMenu.html?method=editStockEntryDetail")
		{
		$("#save").text("Update");
		}
	
	
	
	$("#Fromdate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : true,
		changeYear : true,
	});

	$("#billdate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : true,
		changeYear : true,
	});

	
	if($("#hiddenid").val() !=null)
		{
	/*$("#itemType").val($("#hiddenitem").val());
	$("#booktitle").val($("#hiddenbooktitle").val());
	$("#author").val($("#hiddenauthor").val());*/
	$("#category").val($("#hiddencategory").val());
	
	$("#language").val($("#hiddenlang").val());
	$("#publisher").val($("#hiddenpub").val());
	$("#suppliedby").val($("#hiddensup").val());
	$("#libloc").val($("#hiddenloc").val());
	$("#currentstatus").val($("#hiddenstatus").val());
	$('#imagePreview').attr('src',$("#hiddenimgurl").val());
	$('#imagePreview1').attr('src',$("#photohiddenid").val());
	
	
	//$("#removeSpanId").show();
	if($("#category").val()!="" || $("#category").val()!=undefined){
		getclassdescrlist();
		$("#classdesc").val($("#hiddenclassdescrip").val());
		getlibcategorysectionlist();
		$("#section").val($("#hiddensection").val());
		getlibcategorydivisionlist();
		$("#division").val($("#hiddendivision").val());
	}
	
		}

	if($("#entryhiddenid").val() !=null)
	{
       /* $("#itemType").val($("#hiddenitem").val());
        $("#booktitle").val($("#hiddenbooktitle").val());
        $("#author").val($("#hiddenauthor").val());*/
        $("#category").val($("#hiddencategory").val());

        $("#language").val($("#hiddenlang").val());
        $("#publisher").val($("#hiddenpub").val());
        $("#suppliedby").val($("#hiddensup").val());
        $("#libloc").val($("#hiddenloc").val());
        $("#currentstatus").val($("#hiddenstatus").val());
        $('#imagePreview').attr('src',$("#hiddenimgurl").val());
    	$('#imagePreview1').attr('src',$("#photohiddenid").val());
        //$("#removeSpanId").show();
    if($("#category").val()!="" || $("#category").val()!=undefined){
	    getclassdescrlist();
        $("#classdesc").val($("#hiddenclassdescrip").val());
	    getlibcategorysectionlist();
	    $("#section").val($("#hiddensection").val());
	    getlibcategorydivisionlist();
	    $("#division").val($("#hiddendivision").val());
  }

	}
		
			  
	
	$("#accessionNo").change(function(){
		validateaccessionno();
		});
	
	 
	
	$("#save").click(function(){
		
		  if ($("#accessionNo").val() == "" || $("#accessionNo").val()  == undefined) { 
				$(".errormessagediv").show();
				showError("#accessionNo","Field Required -Accession No");
					return false;
			     }	
		  
		  else if ($("#itemType").val() == "" || $("#itemType").val()  == undefined) {
					$(".errormessagediv").show();
					showError("#itemType","Field Required -Item Type");
					return false;
					
			  }
		  else if ($("#Fromdate").val() == "" || $("#Fromdate").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#Fromdate","Field Required -Reg Date");
				return false;
			
		  }
		  else if ($("#booktitle").val() == "" || $("#booktitle").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#booktitle","Field Required -Book Title");
				return false;
			
		  }
		  else if ($("#author").val() == "" || $("#author").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#author","Field Required -Author");
				return false;
				
		  }
		  else if ($("#category").val() == "" || $("#category").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#category","Field Required - Category");
				return false;
				
		  }
		  else if ($("#classdesc").val() == "" || $("#classdesc").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#classdesc","Field Required - Class Description");
				return false;
				
		  }
		  else if ($("#section").val() == "" || $("#section").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#section","Field Required - Section Description");
				return false;
				
		  }
		  else if ($("#language").val() == "" || $("#language").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#language","Field Required - Language");
				return false;
				
		  }
		  else if ($("#publisher").val() == "" || $("#publisher").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#publisher","Field Required - Publisher");
				return false;
				
		  }
		  else if ($("#nocopy").val() == "" || $("#nocopy").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#nocopy","Field Required - Number Of Copy");
				return false;
				
				
		  }
		  else if ($("#cost").val() == "" || $("#cost").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#cost","Field Required - Cost");
				return false;
				
		  }
		  else if ($("#edition").val() == "" || $("#edition").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#edition","Field Required - Edition");
				return false;
				
		  }
		  else if ($("#iSBNNo").val() == "" || $("#iSBNNo").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#iSBNNo","Field Required - ISBN NO");
				return false;
				
		  }
		  else if ($("#billNo").val() == "" || $("#billNo").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#billNo","Field Required - Bill No");
				return false;
				
		  }
		  else if ($("#size").val() == "" || $("#size").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#size","Field Required - Size");
				return false;
				
				
		  }
		  else if ($("#suppliedby").val() == "" || $("#suppliedby").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#suppliedby","Field Required - Supplied By");
				return false;
				
		  }
		  else if ($("#pubyear").val() == "" || $("#pubyear").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#pubyear","Field Required - Publication Year");
				return false;
				
		  }
		  else if ($("#editor").val() == "" || $("#editor").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#editor","Field Required - Editor");
				return false;
				
		  }
		  else if ($("#billdate").val() == "" || $("#billdate").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#billdate","Field Required - Bill Date");
				return false;
				
				
		  }
		  else if ($("#pages").val() == "" || $("#pages").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#pages","Field Required -  Number Of Pages");
				return false;
				
		  }
		  else if ($("#selfno").val() == "" || $("#selfno").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#selfno","Field Required - Shelf No");
				return false;
				
		  }
		  else if ($("#libloc").val() == "" || $("#libloc").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#libloc","Field Required -  Location");
				return false;
			
		  }
		  else if ($("#currentstatus").val() == "" || $("#currentstatus").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#libloc","Field Required - CurrentStatus");
				return false;
				
				
		  }
		
		  else{
		        
			  document.getElementById("StockEntryDetails").submit();
			 
			  /*if(result="addsuccess"){
				  $(".successmessagediv1").show();
					$(".validateTips").text("Adding Record in progress");
				
			    }
			  if(result="success"){
				  $(".successmessagediv1").show();
					$(".validateTips").text("Updating Record in progress");
			
			  }*/
				
				
			 
		  }
			 

           
		

	
	});		



$("#category").change(function(){
	getclassdescrlist();
	$("#section").val("");
	$("#division").val("");
	$("#ddl").val("");
	

});



$("#classdesc").change(function(){
	getlibcategorysectionlist();
});

$("#section").change(function(){
	getlibcategorydivisionlist();
	$("#ddl").val($("#category").val()+"."+ $("#classdesc").val()+"."+ $("#section").val());	
	
	});
$("#division").change(function(){

	$("#ddl").val($("#category").val()+"."+ $("#classdesc").val()+"."+ $("#section").val()+"."+ $("#division").val());	
	
	});


	$("#cost,#nocopy").change(function(){
		$("#totalcost").val("");
	
		if($("#nocopy").val() !="" && $("#cost").val()!=""){
			$("#totalcost").val(parseFloat($("#nocopy").val())*parseFloat($("#cost").val()));
		}
		
		
	});
	$("#bookPhoto").change(function() {
		
		$("span#removeSpanId").show();
		 
		if(checkFile1() == false){
			$("#imagePreview").hide();
			
		     }
			
	else{
			$("#imagePreview").show();
			readURL(this);
		}
		 
	});
	$("#removeSpanId").click(function(){
		$('.setImage').attr('src','images/noImage.png');
		$('#photohiddenid').val("");
		$("#removeSpanId").hide();
	});
	
	imageSetting();


	
});
function checkFile1(){
	var fileval = $("#bookPhoto").val();
	
	if (fileval != undefined && fileval != ''){
		var extval = fileval.substring(fileval.lastIndexOf('.') + 1);
		if(extval != "jpeg" && extval != "jpg" && extval != "png" && extval != "PNG" && extval != "JPG" && extval != "JPEG"){
			$('.errormessagediv').show();
			$('.validateTips').text("Stock Photo Should be a jpeg,jpg and png File");
			document.getElementById("bookPhoto").style.border = "1px solid #AF2C2C";
    		document.getElementById("bookPhoto").style.backgroundColor = "#FFF7F7";
			$('.errormessagediv').delay(3000).fadeOut("slow");
			$("#imagePreview").val("");
			return false;
		}
	}
}






function imageSetting(){

	var setImage=$('.setImage').attr("src");
	
	if(setImage == 'images/noImage.png'){
		$("#removeSpanId").show();
	}else if(setImage == 'images/noImage.png' || setImage == " " || setImage == ""){
		$("#removeSpanId").hide();
	}else{
		$("#removeSpanId").show();
	}
	
}
function getclassdescrlist(){
	datalist={
			"categoryid" : $("#category").val(),
			
	},
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getclassdescrlist",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$("#classdesc").empty();
			 $("#classdesc").append("<option value=''>----------Select----------</option>");
			for(var i=0;i<result.catlist.length;i++){
				 $("#classdesc").append("<option value='"+result.catlist[i].subcategorycode+"'>"+result.catlist[i].subcategoryname+"</option>");
			}
		}
	});
		
}

function getlibcategorysectionlist(){
	datalist={
			"categoryid" : $("#category").val(),
			  "classid"  : $("#classdesc").val(),
			
	},
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getlibcategorysectionlist",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$("#section").empty();
			 $("#section").append("<option value=''>----------Select----------</option>");
		            
			for(var i=0;i<result.seclist.length;i++){
				 $("#section").append("<option value='"+result.seclist[i].subcategorycode1 +"'>"+result.seclist[i].subcategoryname1+"</option>");
				}
			 
		}
	});
		
}

function getlibcategorydivisionlist(){
	datalist={
			"categoryid" : $("#category").val(),
			  "classid"  : $("#classdesc").val(),
			  "sectionid"  : $("#section").val(),
			
	},
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getlibcategorydivisionlist",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
	
			$("#division").empty();
			 $("#division").append("<option value=''>----------Select----------</option>");
			
			for(var i=0;i<result.divlist.length;i++){
				 $("#division").append("<option value='"+result.divlist[i].subcategorycode2 +"'>"+result.divlist[i].subcategoryname2+"</option>");
			}
		}
	});
		
}

function validateaccessionno(){
	 if($("#hiddenid").val().toLowerCase() !="" && ($("#haccessionNo").val().toLowerCase() !=$("#accessionNo").val().toLowerCase()))
	 {
		 
		 $.ajax({
				type : 'POST',
				url : "LibraryStockEntry.html?method=validateaccessionno",
				data : {"accession":$("#accessionNo").val()},
				async : false,
				success : function(response) {
				
				var result = $.parseJSON(response);
				if(result.result=="true"){
					$('.successmessagediv1').hide();
					$(".errormessagediv").show();
					$(".validateTips").text("Accession No Already Exists.");
					$("#accessionNo").val("");
					$(".errormessagediv").delay(5000).fadeOut();
					 }	
					
				}
			});
	 }
	 else{
		 
	
	 $.ajax({
			type : 'POST',
			url : "LibraryStockEntry.html?method=validateaccessionno",
			data : {"accession":$("#accessionNo").val()},
			async : false,
			success : function(response) {
			
			var result = $.parseJSON(response);
			if(result.result=="true"){
				$('.successmessagediv1').hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Accession No Already Exists.");
			
				$(".errormessagediv").delay(5000).fadeOut();
				 }	
				
			}
		});
	 }
}
function readURL(input) {

	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#imagePreview').attr('src', e.target.result);
		};

		reader.readAsDataURL(input.files[0]);
	}

}
function showError(id,errorMessage){
	$(id).focus();
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
	$('.successmessagediv').hide();
	$(".errormessagediv").show();
	$(".validateTips").text(errorMessage);
	$(".errormessagediv").delay(3000).fadeOut();
}
function hideError(id){
	$(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
		});
}

