$(document).ready(function()
{
	$("#resetbtn").on("click", function (e) {
		$("input:checkbox").prop('checked', false);
		$("#category").val("all");
		$("#searchname").val("");
		$("#status").val("Active");
		getcategorylist();
		
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

	$("#edit").click(function(){
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
			$(".validateTips").text("Select Any One Category Type Details");
			return false;
		} 
		else
		{
			var catid = getData;
			window.location.href = "LibraryMenu.html?method=editCategoryTypeDetail&catid="+catid;
		}
	});
	
	$("#search").click(function(){
		SearchCategoryType();
		$("input:checkbox").prop('checked', false);
	
	});
	
	$("#inactive").click(function(){
	
		var count = 0;
		CatIdlist = [];
		$('input[type="checkbox"]:checked').each(function() {
			var list = $(this).attr("id");
			CatIdlist.push(list);
			count++;
		});

		if (count == 0) {
			$(".errormessagediv").show();
			$(".validateTips").text(
			"Select Category Type To Inactive");
			return false;

		} else {
			$("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p>Are You Sure To Inactive?</p>");
		}
	});
	
	$("#dialog").dialog({
		autoOpen: false,
		modal: true,					    
		title:'Category Type',
		buttons : {
			"Yes" : function() {
				datalist = {
						"CatIdlist": CatIdlist},
				$.ajax({
					type : "POST",
					url : "LibraryMenu.html?method=inactiveCategoryType&catid="+CatIdlist,
					data : datalist,
					async : false,
					success : function(data) {

						var result = $.parseJSON(data);
						$('.errormessagediv').hide();
						$(".successmessagediv").show();
						$(".successmessage").text(result.status);
						setTimeout(function() {
								window.location.href="LibraryMenu.html?method=categoryType";
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


			/*---------*/
			
	
	$("#category").change(function(){
		getcategorylist();
		$("input:checkbox").prop('checked', false);
		
	});
	$("#status").change(function(){
		
		if( ($("#status").val().trim()) == "Inactive"){		 
	        $("#inactive").hide();
	        $("#active").show();		  
		}else{		 
	        $("#inactive").show();
	        $("#active").hide();		  
		}
		getcategorylist();
		$("input:checkbox").prop('checked', false);
	});
	getcategorylist();
	
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
			$(".validateTips").text("Select Any One Category Type Details");
			return false;
		} else{
			$("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p>Are You Yure to Activate?</p>");
		}
		
		$("#dialog").dialog({
			autoOpen: false,
			modal: true,					    
			title:'Category Type',
			buttons : {
				"Yes" : function() {
				$.ajax({
					type : "POST",
					url : "LibraryMenu.html?method=activeCategoryType",
					data : {"Activelist":Activelist},
					async : false,
					success : function(data) {

						var result = $.parseJSON(data);
						$('.errormessagediv').hide();
						$(".successmessagediv").show();
						$(".successmessage").text(result.status);
						setTimeout(function() {
								window.location.href="LibraryMenu.html?method=categoryType";
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
 function getcategorylist(){
		datalist={
				"categoryid" : $("#category").val(),
                 "statusid"	 :$("#status").val(),			
		},
		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=getcategorylist",
			data : datalist,
			async : false,
			success : function(response) {

				var result = $.parseJSON(response);

				$('#allstudent tbody').empty();
			      if(result.catlist.length>0){
				for ( var i = 0; i < result.catlist.length; i++) {
               
					$("#allstudent tbody").append("<tr>" +
							"<td> <input type='checkbox' name='select' class='select' style='text-align:center' id='"+result.catlist[i].category_id+"'></td>"+
							"<td>"+result.catlist[i].categorytypecode+"</td>"+
							"<td>"+result.catlist[i].categorytypename+"</td>"+
							"<td>"+result.catlist[i].description+"</td>"+
							"<td>"+result.catlist[i].status+"</td>"+
							
							"</tr>"
					);
				}
			      }
			      else{
						$("#allstudent tbody").append("<tr><td colspan='5'><center> No Record Found</center></tr>");
			      }
			      
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.catlist.length);
				pagination(100);
			}
		});
 }
function SearchCategoryType(){
	datalist={
		"categoryid" : $("#category").val(),
		"statusid"	 :$("#status").val(),
		"searchname" :$("#searchname").val(),
	},
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getCategoryListBySearch",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#allstudent tbody').empty();
		    if(result.catlist.length>0){
			for ( var i = 0; i < result.catlist.length; i++) {
           
				$("#allstudent tbody").append("<tr>" +
						"<td> <input type='checkbox' name='select' class='select' style='text-align:center' id='"+result.catlist[i].category_id+"'></td>"+
						"<td>"+result.catlist[i].categorytypecode+"</td>"+
						"<td>"+result.catlist[i].categorytypename+"</td>"+
						"<td>"+result.catlist[i].description+"</td>"+
						"<td>"+result.catlist[i].status+"</td>"+
						
						"</tr>"
				);
			}
		    }
		    else{
		    	$("#allstudent tbody").append("<tr><td colspan='5'><center> No Record Found</center></tr>");
		    }
		    $(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.catlist.length);
			pagination(100);
		}
	});
}