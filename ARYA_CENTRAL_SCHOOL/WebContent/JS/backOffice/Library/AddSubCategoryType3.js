$(document).ready(function()
{
	$("input,select").on({
		keypress: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	change: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	});

	
	$("#status").val($("#hiddenstatus").val());
	var hiddenSubcategoryId3=$('#hiddenSubcategoryId3').val();
	
	$("#cattypeid").change(function(){
		var cateType=$('#cattypeid').val();
		getSubCategoryTypeList(cateType);
	});
	
	$("#subcatid").change(function(){
		var subcatType=$('#subcatid').val();
		getSubCategoryType1List(subcatType);
	});
	
	$("#subcatid1").change(function(){
		var subcatType1=$('#subcatid1').val();
		getSubCategoryType2List(subcatType1);
	});
	
	if(hiddenSubcategoryId3 !=""){
		$("#subcatcodeid3").prop("readonly",true);
	}
	
	if(hiddenSubcategoryId3 != undefined || hiddenSubcategoryId3 != "" || hiddenSubcategoryId3 != null){
		
		getSubCategoryTypeList($('#hiddenCategoryId').val().trim());
		getSubCategoryType1List($('#hiddenSubCategoryId').val().trim());
		getSubCategoryType2List($('#hiddenSubCategoryId1').val().trim());
		
		var hiddenCategoryId=$('#hiddenCategoryId').val().trim();
		
		if(hiddenCategoryId != "" || hiddenCategoryId != null){
			$("#cattypeid  option[value='"+hiddenCategoryId+"' ]").attr('selected', 'true');
		}
		
		var hiddenSubCategoryId=$('#hiddenSubCategoryId').val().trim();
		if(hiddenSubCategoryId != "" || hiddenSubCategoryId != null){
			$("#subcatid  option[value='"+hiddenSubCategoryId+"' ]").attr('selected', 'true');
		}
		
		var hiddenSubCategoryId1=$('#hiddenSubCategoryId1').val().trim();
		if(hiddenSubCategoryId1 != "" || hiddenSubCategoryId1 != null){
			$("#subcatid1  option[value='"+hiddenSubCategoryId1+"' ]").attr('selected', 'true');
		}
		
		var hiddenSubCategoryId2=$('#hiddenSubCategoryId2').val().trim();
		if(hiddenSubCategoryId2 != "" || hiddenSubCategoryId2 != null){
			$("#subcatid2  option[value='"+hiddenSubCategoryId2+"' ]").attr('selected', 'true');
		}
	}
	
	
		
	$('#save').click(function() {
		var id=$('#hiddenSubcategoryId3').val();
		var cattypeid = $("#cattypeid").val().trim();
		var subcatid = $("#subcatid").val().trim();
		var subcatid1 = $("#subcatid1").val().trim();
		var subcatid2 = $("#subcatid2").val().trim();
		var subcatcodeid3 = $("#subcatcodeid3").val().trim();
		var subcatnameid3 = $("#subcatnameid3").val().trim();
		var status = $("#status").val().trim();		
		var description = $("#description").val().trim();
		
	if (cattypeid == "" || cattypeid==null) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select Category Type Name");
			showError("#cattypeid");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
		}else if (subcatid == "" || subcatid==null || subcatid=="all" ) {
			
			$(".errormessagediv").show();
			$(".validateTips").text("Select Sub Category Type Name");
			showError("#subcatid");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
		}else if (subcatid1 == "" || subcatid1==null || subcatid1=="all") {
			
			$(".errormessagediv").show();
			$(".validateTips").text("Select Sub Category Type 1 Name");
			showError("#subcatid1");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
		}else if (subcatid2 == "" || subcatid2==null || subcatid2=="all") {
			
			$(".errormessagediv").show();
			
			$(".validateTips").text("Select Sub Category Type 2 Name");
			showError("#subcatid2");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			
			return false;
		}else if(subcatcodeid3=="" || subcatcodeid3 == null){
			
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Sub Category type 3 Code");
			showError("#subcatcodeid3");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
			
		}else if(!subcatcodeid3.match(/^[a-zA-Z0-9]*$/)){
			
			$(".errormessagediv").show();
			$(".validateTips").text("Subcategory type3 Code Should Contain Only Characters and Numbers With No Spaces");
			showError("#subcatcodeid3");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			
			return false;
			
		}else if(subcatnameid3=="" || subcatnameid3 == null){
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Sub Category type 3 Name");
			showError("#subcatnameid3");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}/*else if (subcatcodeid3.replace(/\s/g, '')) {
			$('.errormessagediv').show();
			$('.validateTips').text("Sub Category Type3 Code Already Exist");
			return false;			
		}*/else 
			datalist = {
				"categorytype" : cattypeid,
				"subcategorytype" : subcatid,
				"subcategorytype1" : subcatid1,
				"subcategorytype2" : subcatid2,
				"subcategorytype3code" : subcatcodeid3,
				"subcategorytype3name" : subcatnameid3,
				"status" : status,			
				"description" : description,
				"subcategory3_id": id,
			 },
			 
		$.ajax({
			type : "POST",
			url : "LibraryMenu.html?method=insertSubCategoryType3",
			data : datalist,
			async : false,
			success : function(data){
					
				var result = $.parseJSON(data);
				
				if(result.status == "Sub Category Type3 Code Already Exist"){
					$('.errormessagediv').show();
					$(".successmessagediv").hide();
					$(".validateTips").text(result.status);
					$("#subcategorytype3code").focus();
					showError("#subcatcodeid3");
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				}else{		
				$('.errormessagediv').hide();
				$(".successmessagediv").show();
				$(".successmessage").text(result.status);
				setTimeout(function() {
						window.location.href="LibraryMenu.html?method=subCategoryType3";
				},3000);
				}
			}
		});
		return false;
	});
});

function getSubCategoryTypeList(categorycode){

	datalist={
			"categoryid" : categorycode
	},

	$.ajax({

		type : 'POST',
		url : "LibraryMenu.html?method=getSubCategoryByCategory",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#subcatid').html("");

			$('#subcatid').append('<option value="all">' +"----------Select----------"+ '</option>');

			for ( var j = 0; j < result.SubCategoryList.length; j++) {

				$('#subcatid').append('<option value="'

						+ result.SubCategoryList[j].subcategorytypecode + '">'

						+ result.SubCategoryList[j].subcategorytypename

						+ '</option>');
			}
		}
	});

}
	

function getSubCategoryType1List(subcategorycode){
	
	datalist={
			"subcategorycode" : subcategorycode
	},

	$.ajax({

		type : 'POST',
		url : "LibraryMenu.html?method=getSubCategory1ByCategoryAndSubCategory",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#subcatid1').html("");

			$('#subcatid1').append('<option value="all">' +"----------Select----------"+ '</option>');

			for ( var j = 0; j < result.SubCategoryType1List.length; j++) {

				$('#subcatid1').append('<option value="'

						+ result.SubCategoryType1List[j].subcategorytype1code + '">'

						+ result.SubCategoryType1List[j].subcategorytype1name

						+ '</option>');
			}
		}
	});

}

function getSubCategoryType2List(subcategorycode){
	
	datalist={
			"subcategorycode" : subcategorycode			
	},

	$.ajax({

		type : 'POST',
		url : "LibraryMenu.html?method=getSubCategory2ByCategoryAndSubCategory",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#subcatid2').html("");

			$('#subcatid2').append('<option value="all">' +"----------Select----------"+ '</option>');

			for ( var j = 0; j < result.SubCategoryType2List.length; j++) {

				$('#subcatid2').append('<option value="'

						+ result.SubCategoryType2List[j].subcategorytype1code + '">'

						+ result.SubCategoryType2List[j].subcategorytype1name

						+ '</option>');
			}
		}
	});

}

function validateSubCat3Name() {

	var desname_validate="";
	
	var subname = $('#subcatcodeid3').val().trim();
	

		
		var subname_object = {
				"subname" : subname
		};

		$.ajax({

			type : "GET",
			url : "LibraryMenu.html?method=validatesubcat3name",
			data : subname_object,
			async : false,

			success : function(data) {

				var result = $.parseJSON(data);
				
				if (result == "false") {
					
					desname_validate = "false";
				
				} else {
					
					desname_validate = "true";
				}
				
				
			}
		
		});
	
	
	return desname_validate;
}


function showError(id){
	
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
	
}

function hideError(id){
	$(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
		});
}

