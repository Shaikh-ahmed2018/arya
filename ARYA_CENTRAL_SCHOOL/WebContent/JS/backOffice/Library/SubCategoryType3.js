$(document).ready(function()
{
	
	$("#resetbtn").on("click", function (e) {
	
		$("#cattype,#subcatname,#subcatname1,#subcatname2,#subcatname3").val("all");
		$("#searchname").val("");
		$("#classname").val("Active");
		
		subcatname("all","Active");
		subcat1name("all","Active");
		getSubCategoryType3List("all","Active");
		getSubCategoryType2List("all","Active");
		getcattype("all","all","all","all","all",$("#classname").val());
	});
	
	
	$("#selectall").change(function() {
		$(".select").prop('checked', $(this).prop("checked"));
	});
	
	
	getcattype("all","all","all","all","all",$("#classname").val());
	
	$("#cattype").change(function(){
		
		var cattype=$("#cattype").val();
		if(cattype=="" || cattype==undefined || cattype=="all"){
			cattype="all";
		}
		
		var subcatcode=$("#subcatname").val().trim();
		
		
		if(subcatcode=="" || subcatcode==undefined){
			subcatcode="all";
		}
		var subcatcode1=$("#subcatname1").val().trim();
		if(subcatcode1=="" || subcatcode1==undefined){
			subcatcode1="all";
		}
		var subcatcode2=$("#subcatname2").val().trim();
		if(subcatcode2=="" || subcatcode2==undefined){
			subcatcode2="all";
		}
		var subcatcode3=$("#subcatname3").val().trim();
		if(subcatcode3=="" || subcatcode3==undefined){
			subcatcode3="all";
		}
		var status = $("#classname").val();
		
		var searchname = $("#searchname").val();	
		SearchSubCategoryType3List(cattype,subcatcode,subcatcode1,subcatcode2,subcatcode3,status,searchname);
		subcatname(cattype);
		
		$("input:checkbox").prop('checked', false);
		$('#subcatname1 option:first').prop('selected',true);
		$('#subcatname2 option:first').prop('selected',true);
		$('#subcatname3 option:first').prop('selected',true);
	});
	
	$("#subcatname").change(function(){
		
		var catcode = $("#cattype").val();
		var subcatcode=$("#subcatname").val();
		if(subcatcode=="" || subcatcode==undefined){
			subcatcode="all";
		}
		var subcatcode1=$("#subcatname1").val();
		if(subcatcode1=="" || subcatcode1==undefined){
			subcatcode1="all";
		}
		var subcatcode2=$("#subcatname2").val();
		if(subcatcode2=="" || subcatcode2==undefined){
			subcatcode2="all";
		}
		var subcatcode3=$("#subcatname3").val();
		if(subcatcode3=="" || subcatcode3==undefined){
			subcatcode3="all";
		}
		var status = $("#classname").val();
		var searchname = $("#searchname").val();
		if(searchname=="" || searchname==undefined){
			searchname="all";
		}
		SearchSubCategoryType3List(catcode,subcatcode,subcatcode1,subcatcode2,subcatcode3,status,searchname);
		subcat1name(subcatcode);
		
		$("input:checkbox").prop('checked', false);
		$('#subcatname1 option:first').prop('selected',true);
		$('#subcatname2 option:first').prop('selected',true);
		$('#subcatname3 option:first').prop('selected',true);
		
	});
	
	$("#subcatname1").change(function(){
		var catcode = $("#cattype").val();
		var subcatcode=$("#subcatname").val();
		if(subcatcode=="" || subcatcode==undefined){
			subcatcode="all";
		}
		var subcatcode1=$("#subcatname1").val();
		if(subcatcode1=="" || subcatcode1==undefined){
			subcatcode1="all";
		}
		var subcatcode2=$("#subcatname2").val();
		if(subcatcode2=="" || subcatcode2==undefined){
			subcatcode2="all";
		}
		var subcatcode3=$("#subcatname3").val();
		if(subcatcode3=="" || subcatcode3==undefined){
			subcatcode3="all";
		}
		var status = $("#classname").val();
		var searchname = $("#searchname").val();
		if(searchname=="" || searchname==undefined){
			searchname="all";
		}
		SearchSubCategoryType3List(catcode,subcatcode,subcatcode1,subcatcode2,subcatcode3,status,searchname);
		getSubCategoryType2List(subcatcode1);
		
		$("input:checkbox").prop('checked', false);
		$('#subcatname2 option:first').prop('selected',true);
		$('#subcatname3 option:first').prop('selected',true);
		
	});
	
	
	$("#subcatname2").change(function(){
		var catcode = $("#cattype").val();
		var subcatcode=$("#subcatname").val();
		if(subcatcode=="" || subcatcode==undefined){
			subcatcode="all";
		}
		var subcatcode1=$("#subcatname1").val();
		if(subcatcode1=="" || subcatcode1==undefined){
			subcatcode1="all";
		}
		var subcatcode2=$("#subcatname2").val();
		if(subcatcode2=="" || subcatcode2==undefined){
			subcatcode2="all";
		}
		var subcatcode3=$("#subcatname3").val();
		if(subcatcode3=="" || subcatcode3==undefined){
			subcatcode3="all";
		}
		var status = $("#classname").val();
		var searchname = $("#searchname").val();
		if(searchname=="" || searchname==undefined){
			searchname="all";
		}
		SearchSubCategoryType3List(catcode,subcatcode,subcatcode1,subcatcode2,subcatcode3,status,searchname);
		getSubCategoryType3List(subcatcode2);
		
		$("input:checkbox").prop('checked', false);
		$('#subcatname3 option:first').prop('selected',true);
	});
	
	
	$("#subcatname3").change(function(){
		var catcode = $("#cattype").val();
		var subcatcode=$("#subcatname").val();
		if(subcatcode=="" || subcatcode==undefined){
			subcatcode="all";
		}
		var subcatcode1=$("#subcatname1").val();
		if(subcatcode1=="" || subcatcode1==undefined){
			subcatcode1="all";
		}
		var subcatcode2=$("#subcatname2").val();
		if(subcatcode2=="" || subcatcode2==undefined){
			subcatcode2="all";
		}
		var subcatcode3=$("#subcatname3").val();
		if(subcatcode3=="" || subcatcode3==undefined){
			subcatcode3="all";
		}
		var status = $("#classname").val();
		var searchname = $("#searchname").val();	
		if(searchname=="" || searchname==undefined){
			searchname="all";
		}
		SearchSubCategoryType3List(catcode,subcatcode,subcatcode1,subcatcode2,subcatcode3,status,searchname);
		
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
		if(catcode=="" || catcode==undefined){
			catcode="all";
		}
		var subcatcode=$("#subcatname").val();
		if(subcatcode=="" || subcatcode==undefined){
			subcatcode="all";
		}
		var subcatcode1=$("#subcatname1").val();
		if(subcatcode1=="" || subcatcode1==undefined){
			subcatcode1="all";
		}
		var subcatcode2=$("#subcatname2").val();
		if(subcatcode2=="" || subcatcode2==undefined){
			subcatcode2="all";
		}
		var subcatcode3=$("#subcatname3").val();
		if(subcatcode3=="" || subcatcode3==undefined){
			subcatcode3="all";
		}
		var status = $("#classname").val().trim();
		
		var searchname = $("#searchname").val();	
		if(searchname=="" || searchname==undefined){
			searchname="all";
		}
		SearchSubCategoryType3List(catcode,subcatcode,subcatcode1,subcatcode2,subcatcode3,status,searchname);
		$("input:checkbox").prop('checked', false);

	});
	
	$("#search").click(function(){
		$("#selectall").prop("checked", false);
		var catcode = $("#cattype").val();
		var subcatcode=$("#subcatname").val();
		if(subcatcode=="" || subcatcode==undefined){
			subcatcode="all";
		}
		var subcatcode1=$("#subcatname1").val();
		if(subcatcode1=="" || subcatcode1==undefined){
			subcatcode1="all";
		}
		var subcatcode2=$("#subcatname2").val();
		if(subcatcode2=="" || subcatcode2==undefined){
			subcatcode2="all";
		}
		var subcatcode3=$("#subcatname3").val();
		if(subcatcode3=="" || subcatcode3==undefined){
			subcatcode3="all";
		}
		var status = $("#classname").val();
		var searchname = $("#searchname").val();	
		if(searchname=="" || searchname==undefined){
			searchname="all";
		}
		SearchSubCategoryType3List(catcode,subcatcode,subcatcode1,subcatcode2,subcatcode3,status,searchname);		
	});
	


	$("#edit").click(function(){
		$(".successmessagediv").hide();
		var cnt = 0;

		$('input.select:checkbox:checked').map(function() {
		
			getData = $(this).attr("id").split(",");
			cnt++;
		});

		if (cnt == 0 || cnt > 1) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select Any One  Sub Category Type 3 Details");
			return false;
		} 
		else
		{
			var catid = getData;
			window.location.href = "LibraryMenu.html?method=editSubCategoryTypeDetail3&catid="+catid;
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
			$(".validateTips").text("Select any one Sub Category Type 3 Details");
			return false;
		} else{
			$("#dialog").dialog("open");
			$(".errormessagediv").hide();
			$("#dialog").empty();
			$("#dialog").append("<p>Are you sure to Inactive?</p>");
		}
});
	
	$("#dialog").dialog({
		autoOpen: false,
		modal: true,					    
		title:'Sub Category Type3',
		buttons : {
			"Yes" : function() {
			$.ajax({
				type : "POST",
				url : "LibraryMenu.html?method=inactiveSubCategoryType3",
				data : {"Inactivelist":Inactivelist},
				async : false,
				success : function(data) {

					var result = $.parseJSON(data);
					$('.errormessagediv').hide();
					$(".successmessagediv").show();
					$(".successmessage").text(result.status);
					setTimeout(function() {
							window.location.href="LibraryMenu.html?method=subCategoryType3";
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
			$(".validateTips").text("Select any one Sub Category Type 3 Details");
			return false;
		} else{
			$("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p>Are you sure to Activate?</p>");
		}
		
		$("#dialog").dialog({
			autoOpen: false,
			modal: true,					    
			title:'Sub Category Type3',
			buttons : {
				"Yes" : function() {
				$.ajax({
					type : "POST",
					url : "LibraryMenu.html?method=activeSubCategoryType3",
					data : {"Activelist":Activelist},
					async : false,
					success : function(data) {

						var result = $.parseJSON(data);
						$('.errormessagediv').hide();
						$(".successmessagediv").show();
						$(".successmessage").text(result.status);
						setTimeout(function() {
								window.location.href="LibraryMenu.html?method=subCategoryType3";
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
			$('#subcatname').append('<option value="">ALL</option>');
			for ( var j = 0; j < result.subcategorynamelist.length; j++) {

				$('#subcatname').append('<option value="'+ result.subcategorynamelist[j].subcategorycode+ '">'
						+ result.subcategorynamelist[j].subcategoryname+ '</option>');
			}

		}
	});
}

function subcat1name(pointer){
	$.ajax({
		url : "LibraryMenu.html?method=getSubCategory1ByCategoryAndSubCategory",
		async : false,
		data:{
			"subcategorycode" : pointer
		},
		success : function(data) {

			var result = $.parseJSON(data);
			$('#subcatname1').empty();
			$('#subcatname1').append('<option value="">ALL</option>');
			for ( var j = 0; j < result.SubCategoryType1List.length; j++) {

				$('#subcatname1').append('<option value="'

						+ result.SubCategoryType1List[j].subcategorytype1code + '">'

						+ result.SubCategoryType1List[j].subcategorytype1name

						+ '</option>');
			}
		}
	});
}


function getcattype(cattype,subcatcode,subcatcode1,subcatcode2,subcatcode3,status){
	
	var datalist = {			
			"cattype" :cattype,	
			"subcatcode":subcatcode,
			"subcatcode1":subcatcode1,
			"subcatcode2":subcatcode2,
			"subcatcode3":subcatcode3,
			"status"  :status,
		}; 
		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=getCategoryType3",
			data : datalist,
			async:false,
			
			success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					if(result.list.length>0){
						for(var i=0;i<result.list.length;i++){
							$("#allstudent tbody").append("<tr>"
									+"<td><input type='checkbox' class='select' id='"+result.list[i].subcategoryid+"' /></td>"
									+"<td> "+result.list[i].subcategorycode3+" </td>"
									+"<td> "+result.list[i].categoryname+" </td>"
									+"<td> "+result.list[i].subcategoryname+" </td>"
									+"<td> "+result.list[i].subcategoryname1+" </td>"
									+"<td> "+result.list[i].subcategoryname2+" </td>"
									+"<td> "+result.list[i].subcategoryname3+" </td>"
									+"<td> "+result.list[i].description+" </td>"
									+"<td> "+result.list[i].status+" </td>"
									+"</tr>");
						}	
					}else{
						$("#allstudent tbody").append("<tr>"+"<td colspan='11'>No Records Founds</td>" +"</tr>");
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

function SearchSubCategoryType3List(catcode,subcatcode,subcatcode1,subcatcode2,subcatcode3,status,searchname){
	
	var datalist = {			
			"catcode" :catcode,	
			"subcatcode":subcatcode,
			"subcatcode1":subcatcode1,
			"subcatcode2":subcatcode2,
			"subcatcode3":subcatcode3,
			"status":status,
			"searchname":searchname,
		}; 
		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=SearchCategoryType3List",
			data : datalist,
			async:false,
			
			success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					if(result.list.length>0){
						for(var i=0;i<result.list.length;i++){
							$("#allstudent tbody").append("<tr>"
									+"<td><input type='checkbox' class='select' id='"+result.list[i].hiddencode+"' /></td>"
									+"<td> "+result.list[i].subcategorycode3+"</td>"
									+"<td> "+result.list[i].categoryname+"</td>"
									+"<td> "+result.list[i].subcategoryname+"</td>"
									+"<td> "+result.list[i].subcategoryname1+"</td>"
									+"<td> "+result.list[i].subcategoryname2+"</td>"
									+"<td> "+result.list[i].subcategoryname3+"</td>"
									+"<td> "+result.list[i].description+"</td>"
									+"<td> "+result.list[i].status+"</td>"
									+"</tr>");
						}	
					}else{
						$("#allstudent tbody").append("<tr>"+"<td colspan='12'>No Records Founds</td>" +"</tr>");
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
			$('#subcatname2').html("");
			$('#subcatname2').append('<option value="all">' +"ALL"+ '</option>');
			for ( var j = 0; j < result.SubCategoryType2List.length; j++) {

				$('#subcatname2').append('<option value="'

						+ result.SubCategoryType2List[j].subcategorytype1code + '">'

						+ result.SubCategoryType2List[j].subcategorytype1name

						+ '</option>');
			}
		}
	});

}

function getSubCategoryType3List(subcategorycode){
	
	datalist={
			"subcategorycode" : subcategorycode			
	},

	$.ajax({

		type : 'POST',
		url : "LibraryMenu.html?method=getSubCategory3ByCategoryAndSubCategory",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#subcatname3').html("");

			$('#subcatname3').append('<option value="all">' +"ALL"+ '</option>');

			for ( var j = 0; j < result.SubCategoryType2List.length; j++) {

				$('#subcatname3').append('<option value="'

						+ result.SubCategoryType2List[j].subcategorytype1code + '">'

						+ result.SubCategoryType2List[j].subcategorytype1name

						+ '</option>');
			}
		}
	});

}








