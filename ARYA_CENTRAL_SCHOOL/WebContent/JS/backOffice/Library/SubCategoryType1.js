$(document).ready(function() {
	
	getTableBycategorytype();
	
	$("#resetbtn").on("click", function (e) {
		$("input:checkbox").prop('checked', false);
		$("#categorytype").val("all");
		$("#searchname").val("");
		$("#status").val("Active");
		getSubCategoryTypeList();
		getSubCategoryType1List();
		getTableBycategorytype();
		
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

	$("#categorytype").change(function(){
		getSubCategoryTypeList();
		getTableBycategorytype();
		$("input:checkbox").prop('checked', false);
	
	});

	$("#subcategory").change(function() {
		getSubCategoryType1List();
		getTableBycategorytypeandSub();
		$("input:checkbox").prop('checked', false);
		
	});
	
	$("#subcategorytype1").change(function() {
		
		getTableBycategorytypeandSub1();
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
		getTableByStatus();
		$("input:checkbox").prop('checked', false);
	
	});
	
	$("#search").click(function(){
		
		$("#selectall").prop("checked", false);
		SearchCategoryType1List();
		$("input:checkbox").prop('checked', false);
	
	});
	
	$("#edit").click(function()
			{
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
			$(".validateTips").text("Select Any One Subcategory Type 1 Details");
			return false;
		} else {
			var catid = getData;
			window.location.href = "LibraryMenu.html?method=editSubCategoryType1Detail&catid="+ catid;
		}
			});

	$("#inactive").click(function() {
		Inactivelist=[];
		var cnt = 0;
		$('input[type="checkbox"]:checked').map(function() {
			var getData = $(this).attr("id");
			Inactivelist.push(getData);
			cnt++;
		});
	
		if (cnt == 0) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select Any One Sub Category Type 1");
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
		title:'Sub Category Type',
		buttons : {
			"Yes" : function() {
				$.ajax({
					type : "POST",
					url : "LibraryMenu.html?method=inactiveSubCategoryType1",
					data : {"Inactivelist":Inactivelist},
					async : false,
					success : function(data) {

						var result = $.parseJSON(data);
						$('.errormessagediv').hide();
						$(".successmessagediv").show();
						$(".successmessage").text(result.status);
						setTimeout(function() {
								window.location.href="LibraryMenu.html?method=subCategoryType1";
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
			$(".validateTips").text("Select Any One Sub Category Type 1 Details");
			return false;
		} else{
			$("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p>Are You Sure to Activate?</p>");
		}
		
		$("#dialog").dialog({
			autoOpen: false,
			modal: true,					    
			title:'Sub Category Type1',
			buttons : {
				"Yes" : function() {
				$.ajax({
					type : "POST",
					url : "LibraryMenu.html?method=activeSubCategoryType1",
					data : {"Activelist":Activelist},
					async : false,
					success : function(data) {

						var result = $.parseJSON(data);
						$('.errormessagediv').hide();
						$(".successmessagediv").show();
						$(".successmessage").text(result.status);
						setTimeout(function() {
								window.location.href="LibraryMenu.html?method=subCategoryType1";
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


function getSubCategoryTypeList(){
	var categorycode=$("#categorytype").val();
	var status=$('#status').val();
	datalist={
			"categoryid" : categorycode,
			"status":status
	},

	$.ajax({

		type : 'POST',
		url : "LibraryMenu.html?method=getSubCategoryByCategory",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#subcategory').html("");

			$('#subcategory').append('<option value="all">' +"ALL"+ '</option>');

			for ( var j = 0; j < result.SubCategoryList.length; j++) {

				$('#subcategory').append('<option value="'

						+ result.SubCategoryList[j].subcategorytypecode + '">'

						+ result.SubCategoryList[j].subcategorytypename

						+ '</option>');
			}
		}
	});

}

function getSubCategoryType1List() {

	var subcategorycode = $("#subcategory").val();
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

					$('#subcategorytype1').append('<option value="all">'+ "ALL"+ '</option>');
					for ( var j = 0; j < result.SubCategoryType1List.length; j++) {
						$('#subcategorytype1')
						.append('<option value="'
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
	var subcategorycode=$("#subcategory").val();
	var subcategorytype1=$("#subcategorytype1").val();
	var status=$('#status').val();
	var datalist = {			
			"categorytype" :categorycode,
			"subcategorycode":subcategorycode,
			"subcategorytype1":subcategorytype1,
			"status":status
		}; 
		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=getTabByCategoryType",
			data : datalist,
			async:false,
			
			success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					if(result.list.length>0){
						for(var i=0;i<result.list.length;i++){
							$("#allstudent tbody").append("<tr>"
									+"<td><input type='checkbox' class='select' id='"+result.list[i].subcategorytype1_id+"' /></td>"
									+"<td> "+result.list[i].subcategorytype1code+" </td>"
									+"<td> "+result.list[i].categorytypename+" </td>"
									+"<td> "+result.list[i].subcategorytypename+" </td>"
									+"<td> "+result.list[i].subcategorytype1name+" </td>"
									+"<td> "+result.list[i].description+" </td>"
									+"<td> "+result.list[i].status+" </td>"
									+"</tr>");
						}	
					}else{
						$("#allstudent tbody").append("<tr>"+"<td colspan='7'>No Records Founds</td>" +"</tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.list.length);
					pagination(100);
					}
		});

}

function getTableBycategorytypeandSub(){
	var categorycode=$("#categorytype").val();
	var subcategorytype1=$("#subcategorytype1").val();
	var subcategorycode=$("#subcategory").val();
	var status=$('#status').val();
	var datalist = {			
			"subcategorytype" :subcategorycode,
			"categorytype" :categorycode,
			"subcategorytype1":subcategorytype1,
			"status":status
		}; 
		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=getTableBycategorytypeandSub",
			data : datalist,
			async:false,
			
			success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					if(result.list.length>0){
						for(var i=0;i<result.list.length;i++){
							$("#allstudent tbody").append("<tr>"
									+"<td><input type='checkbox' class='select' id='"+result.list[i].subcategorytype1_id+"' /></td>"
									+"<td> "+result.list[i].subcategorytype1code+" </td>"
									+"<td> "+result.list[i].categorytypename+" </td>"
									+"<td> "+result.list[i].subcategorytypename+" </td>"
									+"<td> "+result.list[i].subcategorytype1name+" </td>"
									+"<td> "+result.list[i].description+" </td>"
									+"<td> "+result.list[i].status+" </td>"
									+"</tr>");
						}	
					}else{
						$("#allstudent tbody").append("<tr>"+"<td colspan='7'>No Records Founds</td>" +"</tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.list.length);
					pagination(100);
			}
		});

}

function getTableBycategorytypeandSub1(){
	
	var categorycode=$("#categorytype").val();
	var subcategorycode=$("#subcategory").val();
	var subcategory1code=$("#subcategorytype1").val();
	var status=$('#status').val();
	var datalist = {			
			"categorytype" :categorycode,
			"subcategorycode":subcategorycode,
			"subcategorytype1" :subcategory1code,
			"status" :status
		}; 
		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=getTableBycategorytypeandSub1",
			data : datalist,
			async:false,
			
			success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					if(result.list.length>0){
						for(var i=0;i<result.list.length;i++){
							$("#allstudent tbody").append("<tr>"
									+"<td><input type='checkbox' class='select' id='"+result.list[i].subcategorytype1_id+"' /></td>"
									+"<td> "+result.list[i].subcategorytype1code+" </td>"
									+"<td> "+result.list[i].categorytypename+" </td>"
									+"<td> "+result.list[i].subcategorytypename+" </td>"
									+"<td> "+result.list[i].subcategorytype1name+" </td>"
									+"<td> "+result.list[i].description+" </td>"
									+"<td> "+result.list[i].status+" </td>"
									+"</tr>");
						}	
					}else{
						$("#allstudent tbody").append("<tr>"+"<td colspan='7'>No Records Founds</td>" +"</tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.list.length);
					pagination(100);
			}
		});

}

function getTableByStatus(){
	   var status=$("#status").val();
	   var categorycode=$("#categorytype").val();
	   var subcategorycode=$("#subcategory").val();
	   var subcategory1code=$("#subcategorytype1").val();
	
	var datalist = {			
			"status" :status,
			"categorycode":categorycode,
			"subcategorycode":subcategorycode,
			"subcategory1code":subcategory1code
			
		}; 
		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=getTableByStatus",
			data : datalist,
			async:false,
			
			success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					if(result.list.length>0){
						for(var i=0;i<result.list.length;i++){
							$("#allstudent tbody").append("<tr>"
									+"<td><input type='checkbox' class='select' id='"+result.list[i].subcategorytype1_id+"' /></td>"
									+"<td> "+result.list[i].subcategorytype1code+" </td>"
									+"<td> "+result.list[i].categorytypename+" </td>"
									+"<td> "+result.list[i].subcategorytypename+" </td>"
									+"<td> "+result.list[i].subcategorytype1name+" </td>"
									+"<td> "+result.list[i].description+" </td>"
									+"<td> "+result.list[i].status+" </td>"
									+"</tr>");
						}	
					}else{
						$("#allstudent tbody").append("<tr>"+"<td colspan='7'>No Records Founds</td>" +"</tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.list.length);
					pagination(100);
			}
		});

}


function SearchCategoryType1List(){
	
	   var status=$("#status").val();
	   var searchname = $("#searchname").val().trim();
	   var catcode=$("#categorytype").val();
	   var subcatcode=$("#subcategory").val();
	   var subcategorytype1=$("#subcategorytype1").val();
	   var datalist = {	
			"status":status,
			"searchname":searchname,	
			"catcode":catcode,
			"subcatcode":subcatcode,
			"subcategorytype1":subcategorytype1,
			
		}; 
		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=searchSubCatType1",
			data : datalist,
			async:false,
			
			success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					if(result.list.length>0){
						for(var i=0;i<result.list.length;i++){
							$("#allstudent tbody").append("<tr>"
									+"<td><input type='checkbox' class='select' id='"+result.list[i].subcategorytype1_id+"' /></td>"
									+"<td> "+result.list[i].subcategorytype1code+" </td>"
									+"<td> "+result.list[i].categorytypename+" </td>"
									+"<td> "+result.list[i].subcategorytypename+" </td>"
									+"<td> "+result.list[i].subcategorytype1name+" </td>"
									+"<td> "+result.list[i].description+" </td>"
									+"<td> "+result.list[i].status+" </td>"
									+"</tr>");
						}	
					}else{
						$("#allstudent tbody").append("<tr>"+"<td colspan='7'>No Records Founds</td>" +"</tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.list.length);
					pagination(100);
					}
		});

}