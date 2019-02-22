$(document).ready(function() {
	
	getTableBycategorytype();
	
	$(".select").change(function(){
        if($(".select").length==$(".select:checked").length){
	         $("#selectall").prop("checked",true);
         }
       else{
	           $("#selectall").prop("checked",false);
           }
      });
	
	$("#resetbtn").on("click", function (e) {
		$("input:checkbox").prop('checked', false);
		$("#categorytype,#subcategorytype,#subcategorytype1,#subcategorytype2").val("all");
		$("#searchname").val("");
		$("#status").val("Active");
		getSubCategoryTypeList();
		getSubCategoryType1List();
		getSubCategoryType2List();
		getTableBycategorytype();
	});
	
	$("#categorytype").change(function() {
		getSubCategoryTypeList();
		getTableBycategorytype();
		$("input:checkbox").prop('checked', false);
	});
	
	$("#subcategorytype").change(function() {
		getSubCategoryType1List();
		getTableBysubcategorytype();
		$("input:checkbox").prop('checked', false);
	});
	
	$("#subcategorytype1").change(function() {
		 getSubCategoryType2List();
		 getTableBysubcategorytype1();
		 $("input:checkbox").prop('checked', false);
	});
	
	$("#subcategorytype2").change(function() {
		 getTableBysubcategorytype2();
		 $("input:checkbox").prop('checked', false);
	});
	
	$("#status").change(function() {
		if( ($("#status").val().trim()) == "Inactive"){		 
	        $("#inactive").hide();
	        $("#active").show();		  
		}else{		 
	        $("#inactive").show();
	        $("#active").hide();		  
		}
		getTableBySub2Status();
		$("input:checkbox").prop('checked', false);
	});
	
	$("#search").click(function(){
		
		SearchCategoryType2List();	
		$("input:checkbox").prop('checked', false);
	});
	
	$("#selectall").change(function() {
		$(".select").prop('checked', $(this).prop("checked"));
	});
	$(".select").change(function(){
        if($(".select").length==$(".select:checked").length){
	         $("#selectall").prop("checked",true);
         }
       else{
	           $("#selectall").prop("checked",false);
           }
     });

	$("#edit").click(function() {
		$(".successmessagediv").hide();
		var cnt = 0;
		getData=[];
/*		$('input[type="checkbox"]:checked').map(function() {*/
			$(".select:checked").each(function(){
			/*getData = $(this).attr("id");
			cnt++;*/
			var list=$(this).attr("id").split(",");
			getData.push(list);
			cnt ++;
		});


		if (cnt == 0 || cnt > 1) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select Any One Subcategory Type 2 Details");
			return false;
		} else {
			var catid = getData;
			window.location.href = "LibraryMenu.html?method=editSubCategoryType2Detail&catid="+ catid;
		}
	});

	$("#inactive").click(function() {
		Inactivelist=[];
		var cnt = 0;
	$('input[type="checkbox"]:checked').map(function() {
		getData = $(this).attr("id");
		Inactivelist.push(getData);
		cnt++;
	});

	if (cnt == 0) {
		$(".errormessagediv").show();
		$(".validateTips").text("Select Any One Sub Category Type 2");
		return false;
	} else{

		$("#dialog").dialog("open");
		$("#dialog").empty();
		$("#dialog").append("<p>Are You Sure to Inactive?</p>");
	}
});
	
	$("#dialog").dialog({
		autoOpen: false,
		modal: true,					    
		title:'Sub Category Type 2',
		buttons : {
			"Yes" : function() {
				$.ajax({
					type : "POST",
					url : "LibraryMenu.html?method=inactiveSubCategoryType2",
					data : {"Inactivelist":Inactivelist},
					async : false,
					success : function(data) {

						var result = $.parseJSON(data);
						$('.errormessagediv').hide();
						$(".successmessagediv").show();
						$(".successmessage").text(result.status);
						setTimeout(function() {
								window.location.href="LibraryMenu.html?method=subCategoryType2";
						}, 2000);
					}

				});  

				$(this).dialog("close");

			},

			"No" : function() {
				$(this).dialog("close");
			}

		}
	});
	$("#active").click(function(){
		Activelist=[];
		var cnt = 0;
		$('input[type="checkbox"]:checked').map(function() {
			var getData = $(this).attr("id");
			Activelist.push(getData);
			cnt++;
		});
	
		if (cnt == 0) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select Any One Sub Category Type 2 Details");
			return false;
		} else{
			$("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p>Are You Sure to Activate?</p>");
		}
		
		$("#dialog").dialog({
			autoOpen: false,
			modal: true,					    
			title:'Sub Category Type2',
			buttons : {
				"Yes" : function() {
				$.ajax({
					type : "POST",
					url : "LibraryMenu.html?method=activeSubCategoryType2",
					data : {"Activelist":Activelist},
					async : false,
					success : function(data) {

						var result = $.parseJSON(data);
						$('.errormessagediv').hide();
						$(".successmessagediv").show();
						$(".successmessage").text(result.status);
						setTimeout(function() {
								window.location.href="LibraryMenu.html?method=subCategoryType2";
						}, 2000);
					}
				});
				
				$(this).dialog("close");
			},
			"No" : function() {
				$(this).dialog("close");
			}
			}
		});	
		
		
});
});

function getSubCategoryTypeList() {

	var categorycode = $("#categorytype").val();
	datalist = {
		"categoryid" : categorycode
	},

	$.ajax({

		type : 'POST',
		url : "LibraryMenu.html?method=getSubCategoryByCategory",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#subcategorytype').html("");

			$('#subcategorytype').append(
					'<option value="all">' + "ALL"
							+ '</option>');

			for ( var j = 0; j < result.SubCategoryList.length; j++) {

				$('#subcategorytype').append('<option value="'

				+ result.SubCategoryList[j].subcategorytypecode + '">'

				+ result.SubCategoryList[j].subcategorytypename

				+ '</option>');
			}
		}
	});

}

function getSubCategoryType1List() {

	var subcategorycode = $("#subcategorytype").val();
			datalist = {
				"subcategorycode" : subcategorycode

			},

			$.ajax({
                       type : 'POST',
						url : "LibraryMenu.html?method=getSubCategory1ByCategoryAndSubCategory",
						data : datalist,
						async : false,
						success : function(response) {

							var result = $.parseJSON(response);

							$('#subcategorytype1').html("");

							$('#subcategorytype1').append(
									'<option value="all">'
											+ "ALL"
											+ '</option>');

							for ( var j = 0; j < result.SubCategoryType1List.length; j++) {

								$('#subcategorytype1')
										.append(
												'<option value="'

														+ result.SubCategoryType1List[j].subcategorytype1code
														+ '">'
														+ result.SubCategoryType1List[j].subcategorytype1name

														+ '</option>');
							}
						}
					});

}

function getTableBycategorytype(){
	var categorycode=$("#categorytype").val();
	     var status=$("#status").val();
	var subcategory2code=$("#subcategorytype2").val();
	var subcategory1code=$("#subcategorytype1").val();
	var subcategorycode=$("#subcategorytype").val();
	var datalist = {			
			"categorytype" :categorycode,
			"subcategorycode":subcategorycode,
			"subcategory1code":subcategory1code,
			"subcategory2code":subcategory2code,
			"status" : status
		}; 
		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=getTabBySub2CategoryType",
			data : datalist,
			async:false,
			
			success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					if(result.list.length>0){
						for(var i=0;i<result.list.length;i++){
							$("#allstudent tbody").append("<tr>"
									+"<td><input type='checkbox' class='select' id='"+result.list[i].subcategorytype2_id+"' /></td>"
									+"<td> "+result.list[i].subcategorytype2code+" </td>"
									+"<td> "+result.list[i].categorytypename+" </td>"
									+"<td> "+result.list[i].subcategorytypename+" </td>"
									+"<td> "+result.list[i].subcategorytype1name+" </td>"
									+"<td> "+result.list[i].subcategorytype2name+" </td>"
									+"<td> "+result.list[i].description+" </td>"
									+"<td> "+result.list[i].status+" </td>"
									+"</tr>");
						}	
					}else{
						$("#allstudent tbody").append("<tr>"+"<td colspan='8'>No Records Founds</td>" +"</tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.list.length);
				
					pagination(100);
					}
		});

}

function getTableBysubcategorytype(){
	var categorycode=$("#categorytype").val();
    var status=$("#status").val();
var subcategory2code=$("#subcategorytype2").val();
var subcategory1code=$("#subcategorytype1").val();
var subcategorycode=$("#subcategorytype").val();
var datalist = {			
		"categorytype" :categorycode,
		"subcategorycode":subcategorycode,
		"subcategory1code":subcategory1code,
		"subcategory2code":subcategory2code,
		"status" : status
};
		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=getTabBySub2subCategoryType",
			data : datalist,
			async:false,
			
			success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					if(result.list.length>0){
						for(var i=0;i<result.list.length;i++){
							$("#allstudent tbody").append("<tr>"
									+"<td><input type='checkbox' class='select' id='"+result.list[i].subcategorytype2_id+"' /></td>"
									+"<td> "+result.list[i].subcategorytype2code+" </td>"
									+"<td> "+result.list[i].categorytypename+" </td>"
									+"<td> "+result.list[i].subcategorytypename+" </td>"
									+"<td> "+result.list[i].subcategorytype1name+" </td>"
									+"<td> "+result.list[i].subcategorytype2name+" </td>"
									+"<td> "+result.list[i].description+" </td>"
									+"<td> "+result.list[i].status+" </td>"
									+"</tr>");
						}	
					}else{
						$("#allstudent tbody").append("<tr>"+"<td colspan='8'>No Records Founds</td>" +"</tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.list.length);
				
					pagination(100);
					}
		});

}

function getTableBysubcategorytype1(){
	var categorycode=$("#categorytype").val();
    var status=$("#status").val();
    var subcategory2code=$("#subcategorytype2").val();
    var subcategory1code=$("#subcategorytype1").val();
    var subcategorycode=$("#subcategorytype").val();
    var datalist = {			
		"categorytype" :categorycode,
		"subcategorycode":subcategorycode,
		"subcategory1code":subcategory1code,
		"subcategory2code":subcategory2code,
		"status" : status
}; 
		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=getTabBySub2subCategory1Type",
			data : datalist,
			async:false,
			
			success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					if(result.list.length>0){
						for(var i=0;i<result.list.length;i++){
							$("#allstudent tbody").append("<tr>"
									+"<td><input type='checkbox' class='select' id='"+result.list[i].subcategorytype2_id+"' /></td>"
									+"<td> "+result.list[i].subcategorytype2code+" </td>"
									+"<td> "+result.list[i].categorytypename+" </td>"
									+"<td> "+result.list[i].subcategorytypename+" </td>"
									+"<td> "+result.list[i].subcategorytype1name+" </td>"
									+"<td> "+result.list[i].subcategorytype2name+" </td>"
									+"<td> "+result.list[i].description+" </td>"
									+"<td> "+result.list[i].status+" </td>"
									+"</tr>");
						}	
					}else{
						$("#allstudent tbody").append("<tr>"+"<td colspan='8'>No Records Founds</td>" +"</tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.list.length);
					pagination(100);
			}
		});

}

function getTableBySub2Status(){
	var categorycode=$("#categorytype").val();
    var status=$("#status").val();
    var subcategory2code=$("#subcategorytype2").val();
    var subcategory1code=$("#subcategorytype1").val();
    var subcategorycode=$("#subcategorytype").val();
    var datalist = {			
		"categorytype" :categorycode,
		"subcategorycode":subcategorycode,
		"subcategory1code":subcategory1code,
		"subcategory2code":subcategory2code,
		"status" : status
}; 
		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=getTableBySub2Status",
			data : datalist,
			async:false,
			
			success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					if(result.list.length>0){
						for(var i=0;i<result.list.length;i++){
							$("#allstudent tbody").append("<tr>"
									+"<td><input type='checkbox' class='select' id='"+result.list[i].subcategorytype2_id+"' /></td>"
									+"<td> "+result.list[i].subcategorytype2code+" </td>"
									+"<td> "+result.list[i].categorytypename+" </td>"
									+"<td> "+result.list[i].subcategorytypename+" </td>"
									+"<td> "+result.list[i].subcategorytype1name+" </td>"
									+"<td> "+result.list[i].subcategorytype2name+" </td>"
									+"<td> "+result.list[i].description+" </td>"
									+"<td> "+result.list[i].status+" </td>"
									+"</tr>");
						}	
					}else{
						$("#allstudent tbody").append("<tr>"+"<td colspan='8'>No Records Founds</td>" +"</tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.list.length);
					pagination(100);
			}
		});

}

function getSubCategoryType2List(){
	
	var subcategorycode = $("#subcategorytype1").val();
	
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

			$('#subcategorytype2').html("");

			$('#subcategorytype2').append('<option value="all">' +"ALL"+ '</option>');

			for ( var j = 0; j < result.SubCategoryType2List.length; j++) {

				$('#subcategorytype2').append('<option value="'

						+ result.SubCategoryType2List[j].subcategorytype1code + '">'

						+ result.SubCategoryType2List[j].subcategorytype1name

						+ '</option>');
			}
		}
	});

}

function getTableBysubcategorytype2(){
	var categorycode=$("#categorytype").val();
    var status=$("#status").val();
    var subcategory2code=$("#subcategorytype2").val();
    var subcategory1code=$("#subcategorytype1").val();
    var subcategorycode=$("#subcategorytype").val();
    var datalist = {			
		"categorytype" :categorycode,
		"subcategorycode":subcategorycode,
		"subcategory1code":subcategory1code,
		"subcategory2code":subcategory2code,
		"status" : status
}; 
	
		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=getTabBySub2subCategory2Type",
			data : datalist,
			async:false,
			
			success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					if(result.list.length>0){
						for(var i=0;i<result.list.length;i++){
							$("#allstudent tbody").append("<tr>"
									+"<td><input type='checkbox' class='select' id='"+result.list[i].subcategorytype2_id+"' /></td>"
									+"<td> "+result.list[i].subcategorytype2code+" </td>"
									+"<td> "+result.list[i].categorytypename+" </td>"
									+"<td> "+result.list[i].subcategorytypename+" </td>"
									+"<td> "+result.list[i].subcategorytype1name+" </td>"
									+"<td> "+result.list[i].subcategorytype2name+" </td>"
									+"<td> "+result.list[i].description+" </td>"
									+"<td> "+result.list[i].status+" </td>"
									+"</tr>");
						}	
					}else{
						$("#allstudent tbody").append("<tr>"+"<td colspan='8'>No Records Founds</td>" +"</tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.list.length);
					pagination(100);
			}
		});

}

function SearchCategoryType2List(){
	var categorycode=$("#categorytype").val();
          var status=$("#status").val();
    var subcategory2code=$("#subcategorytype2").val();
    var subcategory1code=$("#subcategorytype1").val();
    var subcategorycode=$("#subcategorytype").val();
	var searchname = $("#searchname").val().trim();
	var datalist = {			
			"searchname":searchname,
			"categorytype":categorycode,
			"subcategorycode":subcategorycode,
			"subcategory1code":subcategory1code,
			"subcategory2code":subcategory2code,
			"status" : status
		}; 
		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=searchSubCatType2",
			data : datalist,
			async:false,
			
			success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					if(result.list.length>0){
						for(var i=0;i<result.list.length;i++){
							$("#allstudent tbody").append("<tr>"
									+"<td><input type='checkbox' class='select' id='"+result.list[i].subcategorytype2_id+"' /></td>"
									+"<td> "+result.list[i].subcategorytype2code+" </td>"
									+"<td> "+result.list[i].categorytypename+" </td>"
									+"<td> "+result.list[i].subcategorytypename+" </td>"
									+"<td> "+result.list[i].subcategorytype1name+" </td>"
									+"<td> "+result.list[i].subcategorytype2name+" </td>"
									+"<td> "+result.list[i].description+" </td>"
									+"<td> "+result.list[i].status+" </td>"
								+"</tr>");
						  }
						}else{
						$("#allstudent tbody").append("<tr>"+"<td colspan='8'>No Records Founds</td>" +"</tr>");
			}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.list.length);
					pagination(100);
					}
		});

}