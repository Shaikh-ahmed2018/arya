$(document).ready(function()
{

	$("#checkbox_id").change(function() {

		$(".select").prop('checked', $(this).prop("checked"));

	});
	$(".select").change(function(){
        if($(".select").length==$(".select:checked").length){
	         $("#checkbox_id").prop("checked",true);
         }
       else{
	           $("#checkbox_id").prop("checked",false);
           }
});
	
	$("#edit").click(function(){
		
		$(".successmessagediv").hide();
		var cnt = 0;

		$('input[type="checkbox"]:checked').map(function() {
			getData = $(this).val();
			cnt++;
		});
		
	
			

		if ($("#allstudent tbody tr").length!=1 && (cnt == 0 || cnt > 1)) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select Any One Library Location");
			return false;
		} 
		else 
		{
			var libid = getData;
			window.location.href = "LibraryMenu.html?method=editLibraryLocation&libid="+libid;
		}
	});
	
	$("#deletelib").click(function(){
	
		var count = 0;
		libIdlist = [];
		$(".select:checked").each(function() {
			var list = $(this).val();
			libIdlist.push(list);
			count++;
		});

		if (count == 0) {
			$(".errormessagediv").show();
			$(".validateTips").text(
			"Select library location to Delete");
			return false;

		} else {
			$("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p>Are you sure to delete?</p>");
		}

	});

$("#dialog").dialog(
	{
		autoOpen : false,
		modal : true,
		title : 'Library Location Details',
		buttons : {
			"Yes" : function() {

		$.ajax({
			type:"Post",
			url:"LibraryMenu.html?method=deleteLibraryLocations",
			data:{"librarylocid" : libIdlist},
			async:false,
			success:function(response){
				var result = $.parseJSON(response);
			
				if(result.status == "true"){
					$(".successmessagediv").show();
					$(".validateTips").text("Deleting Record Progrssing...");
					setTimeout(function() {
						window.location.href="LibraryMenu.html?method=libraryLocations";
					},3000);
				
			}else if(result.status == "false"){
				$(".errormessagediv").show();
				$(".validateTips").text("Deleting Record failed...");
				setTimeout(function() {
					window.location.href="LibraryMenu.html?method=libraryLocations";
			},3000);
		}
			else{
				$(".errormessagediv").show();

				$(".validateTips").text("Selected Location is Mapped Cannot Delete");
				$('.errormessagediv').delay(3000).slideUp();
			}
				setTimeout(function() {
					window.location.href="LibraryMenu.html?method=libraryLocations";
			},3000);

			  
		}
		});
		$(this).dialog("close");
	},
	"No" : function() {
		$(this).dialog("close");
	}
		}
	});
	
	$("#locationname").change(function(){
		locid=$("#locationname").val();
		$("#checkbox_id").prop("checked",false);
		
		$.ajax({
			type:"post",
			data:{"locid":locid},
			url:"LibraryMenu.html?method=getSchoolLocations",
			async:false,
			success:function(data){
				var result = $.parseJSON(data);
				$("#allstudent tbody").empty();
				 if (result.status.length>0){
				for(var i=0;i<result.status.length;i++)
					{
					$("#allstudent tbody").append("<tr>" 
							+"<td><input type='checkbox' name='checkbox_id' class='select' value='"+result.status[i].librarylocid+"' onclick='selectAll()'</td>" 
							+"<td> "+result.status[i].schoolName+"</td>"
							+"<td> "+result.status[i].libraryLocations+"</td>"
							+"<td> "+result.status[i].description+"</td>"
							+"</tr>");
					}
				 }
				 else{
					 $("#allstudent tbody").append("<tr><td colspan='4'><center> No Record Found</center></tr>");
				 }
				 $(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records "+result.status.length);
				$(".select").change(function(){
			        if($(".select").length==$(".select:checked").length){
				         $("#checkbox_id").prop("checked",true);
			         }
			       else{
				           $("#checkbox_id").prop("checked",false);
			           }
			});
				pagination(100);
			}
		});
		
	});
	
});