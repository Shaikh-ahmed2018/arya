$(document).ready(function()
{
	
	$("#resetbtn").on("click", function (e) {
		
		$("#cattype,#subcatname").val("all");
		$("#searchname").val("");
		$("#classname").val("Active");
		getcattype("all","Active");
		subcatname("all","Active");
	});
	
	
	$("#selectall").change(function() {
		$(".select").prop('checked', $(this).prop("checked"));
	});
	getbystatus("all","all",$("#classname").val());
	$("#cattype").change(function(){
		var catcode=$("#cattype").val();
		subcatname(catcode);
		var status = $("#classname").val();
		var subcatcode = $("#subcatname").val();
		var searchname = $("#searchname").val().trim();
		SearchCategoryTypeList(catcode,subcatcode,status,searchname);	
	
		$("input:checkbox").prop('checked', false);
	});
	
	$("#subcatname").change(function(){
		var catcode = $("#cattype").val();
		var subcatcode = $("#subcatname").val();
		var status = $("#classname").val();
		var searchname = $("#searchname").val().trim();
		SearchCategoryTypeList(catcode,subcatcode,status,searchname);	
		$("input:checkbox").prop('checked', false);
	});
	
	$("#classname").change(function(){
		
		if( ($("#classname").val().trim()) == "Inactive"){		 
	        $("#inactive").hide();
	        $("#active").show();		  
		}else{		 
	        $("#inactive").show();
	        $("#active").hide();		  
		}
		var catcode = $("#cattype").val();
		var subcatcode = $("#subcatname").val();
		var status = $("#classname").val();
		var searchname = $("#searchname").val().trim();
		SearchCategoryTypeList(catcode,subcatcode,status,searchname);		
		$("input:checkbox").prop('checked', false);
	});
	
	$("#search").click(function(){
		var catcode = $("#cattype").val();
		var subcatcode = $("#subcatname").val();
		var status = $("#classname").val();
		var searchname = $("#searchname").val().trim();	
		SearchCategoryTypeList(catcode,subcatcode,status,searchname);		
		$("input:checkbox").prop('checked', false);
	});
	
	$("#edit").click(function(){
		$(".successmessagediv").hide();
		var cnt =0;
		admissionId=[];
		
		$(".select:checked").each(function(){
			var list=$(this).attr("id").split(",");
			admissionId.push(list);
			cnt ++;
		});	
		
		
		if (cnt == 0 || cnt > 1) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select Any One  Sub Category Type Details");
			return false;
		}
		else{
			var catid = admissionId;
			window.location.href = "LibraryMenu.html?method=editSubCategoryTypeDetail&catid="+catid;
		}
	});
	
	$("#inactive").click(function(){
		Inactivelist=[];
		var cnt = 0;
		$('input[type="checkbox"]:checked').map(function() {
			var getData = $(this).attr("id");
			Inactivelist.push(getData);
			cnt++;
		});
	
		if (cnt == 0) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select any one Sub Category Type Details");
			return false;
		}else{
			$("#dialog").dialog("open");
			$(".errormessagediv").hide();
			$("#dialog").empty();
			$("#dialog").append("<p>Are you sure to Inactive?</p>");
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
				url : "LibraryMenu.html?method=inactiveSubCategoryType",
				data : {"Inactivelist":Inactivelist},
				async : false,
				success : function(data) {

					var result = $.parseJSON(data);
					$('.errormessagediv').hide();
					$(".successmessagediv").show();
					$(".successmessage").text(result.status);
					setTimeout(function() {
							window.location.href="LibraryMenu.html?method=subCategoryType";
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
	
	
	//-------------Activating Record----------------
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
				$(".validateTips").text("Select any one Sub Category Type Details");
				return false;
			} else{
				$("#dialog").dialog("open");
				$("#dialog").empty();
				$("#dialog").append("<p>Are you sure to Activate?</p>");
			}
			
			$("#dialog").dialog({
				autoOpen: false,
				modal: true,					    
				title:'Sub Category Type',
				buttons : {
					"Yes" : function() {
					$.ajax({
						type : "POST",
						url : "LibraryMenu.html?method=activeSubCategoryType",
						data : {"Activelist":Activelist},
						async : false,
						success : function(data) {

							var result = $.parseJSON(data);
							$('.errormessagediv').hide();
							$(".successmessagediv").show();
							$(".successmessage").text(result.status);
							setTimeout(function() {
									window.location.href="LibraryMenu.html?method=subCategoryType";
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
		
	//-------------Activating Record End----------------
	
	
});
	


function subcatname(pointer){
	$.ajax({
		url : "LibraryMenu.html?method=getSubCategoryTypeName",
		async : false,
		data:{
			'categoryName':pointer
		},
		success : function(data) {

			var result = $.parseJSON(data);
			$('#subcatname').empty();
			$('#subcatname').append('<option value="all">ALL</option>');
			for ( var j = 0; j < result.subcategorynamelist.length; j++) {

				$('#subcatname').append('<option value="'+ result.subcategorynamelist[j].subcategorycode+ '">'
						+ result.subcategorynamelist[j].subcategoryname+ '</option>');

			}

		}
	});
}


function getcattype(cattype,status){
	var datalist = {			
			"cattype" :cattype,	
			"status"  :status,
		}; 
		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=getCategoryType",
			data : datalist,
			async:false,
			success : function(data) {
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					if(result.list.length>0){
						for(var i=0;i<result.list.length;i++){
							$("#allstudent tbody").append("<tr>"
									+"<td><input type='checkbox' class='select' id='"+result.list[i].subcategoryid+"' /></td>"
									+"<td> "+result.list[i].categorytypecode+" </td>"
									+"<td> "+result.list[i].categoryname+" </td>"
									+"<td> "+result.list[i].categorytypename+" </td>"
									+"<td> "+result.list[i].description+" </td>"
									+"<td> "+result.list[i].status+" </td>"
									+"</tr>");
						}	
					}else{
						$("#allstudent tbody").append("<tr>"+"<td colspan='6'>No Records Founds</td>" +"</tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.list.length);
					
					$(".select").change(function() {
						if($(".select").length == $(".select:checked").length){
							$("#selectall").prop("checked",true);
						}
						else{
							$("#selectall").prop("checked",false);
						}
						});
					
					pagination(100);
			}
		});

}


function getsubcatname(catcode,subcatcode,status){

	var datalist = {			
			"catcode" :catcode,	
			"subcatcode":subcatcode,
			"status":status,
		}; 
		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=getSubCategoryType",
			data : datalist,
			async:false,
			
			success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					if(result.list.length>0){
						for(var i=0;i<result.list.length;i++){
							$("#allstudent tbody").append("<tr>"
									+"<td><input type='checkbox' class='select' id='"+result.list[i].subcategoryid+"' /></td>"
									+"<td> "+result.list[i].categorytypecode+" </td>"
									+"<td> "+result.list[i].categoryname+" </td>"
									+"<td> "+result.list[i].categorytypename+" </td>"
									+"<td> "+result.list[i].description+" </td>"
									+"<td> "+result.list[i].status+" </td>"
									+"</tr>");
						}	
					}else{
						$("#allstudent tbody").append("<tr>"+"<td colspan='6'>No Records Founds</td>" +"</tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.list.length);
					
					$(".select").change(function() {
						if($(".select").length == $(".select:checked").length){
							$("#selectall").prop("checked",true);
						}
						else{
							$("#selectall").prop("checked",false);
						}
						});
					pagination(100);
			}
		});

}

function getbystatus(catcode,subcatcode,status){
	

	var datalist = {			
			"catcode" :catcode,	
			"subcatcode":subcatcode,
			"status":status,
		}; 
		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=getbystatus",
			data : datalist,
			async:false,
			
			success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					if(result.list.length>0){
						for(var i=0;i<result.list.length;i++){
							$("#allstudent tbody").append("<tr>"
									+"<td><input type='checkbox' class='select' id='"+result.list[i].subcategoryid+"' /></td>"
									+"<td> "+result.list[i].categorytypecode+" </td>"
									+"<td> "+result.list[i].categoryname+" </td>"
									+"<td> "+result.list[i].categorytypename+" </td>"
									+"<td> "+result.list[i].description+" </td>"
									+"<td> "+result.list[i].status+" </td>"
									+"</tr>");
						}	
					}else{
						$("#allstudent tbody").append("<tr>"+"<td colspan='6'>No Records Founds</td>" +"</tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.list.length);
					
					$(".select").change(function() {
						
						if($(".select").length == $(".select:checked").length){
							$("#selectall").prop("checked",true);
						}
						else{
							$("#selectall").prop("checked",false);
						}
						});	
					pagination(100);
				
			}
		});

}

function SearchCategoryTypeList(catcode,subcatcode,status,searchname){
	
	var datalist = {			
			"catcode" :catcode,	
			"subcatcode":subcatcode,
			"status":status,
			"searchname":searchname,
		}; 
		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=SearchCategoryTypeList",
			data : datalist,
			async:false,
			
			success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					if(result.list.length>0){
						for(var i=0;i<result.list.length;i++){
							$("#allstudent tbody").append("<tr>"
									+"<td><input type='checkbox' class='select' id='"+result.list[i].subcategoryid+"' /></td>"
									+"<td> "+result.list[i].categorytypecode+" </td>"
									+"<td> "+result.list[i].categoryname+" </td>"
									+"<td> "+result.list[i].categorytypename+" </td>"
									+"<td> "+result.list[i].description+" </td>"
									+"<td> "+result.list[i].status+" </td>"
									+"</tr>");
						}	
					}else{
						$("#allstudent tbody").append("<tr>"+"<td colspan='6'>No Records Founds</td>" +"</tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.list.length);
					
					$(".select").change(function() {
						
						if($(".select").length == $(".select:checked").length){
							$("#selectall").prop("checked",true);
						}
						else{
							$("#selectall").prop("checked",false);
						}
						});
					pagination(100);
			}
		});

}





