var monthCount=0;

$(document).ready(function(){
	
	
	getRouteNameList();
	
	 mappedMonth=getMappedMonth($("#hiddenstuid").val(),$("#hiddenaccyid").val());
	
	
	
	$(".routename").change(function()
	{		
			$("#waivedOff").hide();
			document.getElementById("routename").style.border = "1px solid rgb(169, 169, 169)";
			document.getElementById("routename").style.backgroundColor = "#FFF";
			$(".amount").val("");
			var routeid = $(this).val();
			getstoplist(routeid);
			getAllstoplist();
			
	});
	$(".stopname").change(function(){
		$("#waivedOff").hide();
		document.getElementById("stopname").style.border = "1px solid rgb(169, 169, 169)";
		document.getElementById("stopname").style.backgroundColor = "#FFF";
		var stageid= $(this).val();
		var pointer=$(this);

		getamountandstatus(stageid,pointer);
	});
	
	$("#waivedOff").click(function(){
		
		var datalist = {
				"stuid" : $("#hiddenstuid").val(),
				"locid" : $("#hiddenlocid").val(),
				"accyear" : $("#hiddenaccyid").val(),
				"routeid" : $(".routename").val(),
				"stageid" : $(".stopname").val()
		};
		
		$.ajax({
			type : 'POST',
			url : "transport.html?method=waivedOfftransportrequest",
			data : datalist,
			async : false,
			success : function(response) {
				var result = $.parseJSON(response);
				if(result.status == "success"){
					$(".successmessagediv").show();
					$(".validateTips").text("Transport WaivedOff Successfully");
					setTimeout(function(){
					window.location.href = "transport.html?method=transportFeeSearch";
				},3000);
				}else if(result.status == "fail"){
					$(".errormessagediv").show();
					$(".validateTips").text("Transport WaivedOff Failed");
					setTimeout(function(){
					window.location.href = "transport.html?method=transportFeeSearch";
				},3000);
				}
			}
		});
	});
	
	$("#month").click(function(){
		getSelectedMonth();
		$("#dialog").dialog("open");
	});
	
	$(".selectAll").change(function(){
		$(".select").prop('checked', $(this).prop("checked"));
	});
	
	$(".select").change(function(){
		if($(".select").length==$(".select:checked").length){
			$(".selectAll").prop("checked",true);
		}
		else{
			$(".selectAll").prop("checked",false);
		}
		
			if($(".select:checked").length > 1){
					if($(this).closest("tr").prev("tr").find(".select").prop("checked")==true){
						$(this).prop("checked",$(this).prop("checked"));
					}
					else{
						$(this).prop("checked",false);
					}
				}
				
		
			
			
		
	});
	
	 $( "#dialog" ).dialog({
	       autoOpen: false,
           minHeight: 400,
           minWidth: 300,
           height: 400,
           width: 300,
           closeOnEscape: true,
           modal: true,
           title:"Add Month Details",
           buttons: {
        	   "Add" : function() {
        		   count = 0;
        			monthList=[];
        			monthname =[];
        			$(".select:checked").each(function(){
        				var list=$(this).val();
        				monthList.push(list);
        				monthname.push($(this).closest("tr").find(".monthname").text());
        				count ++;
        			});	
        			if(count == 0){
        				$('.errormessagediv').show();
        				$('.validateTips').text("Select the Months");
        				$('.errormessagediv').delay(3000).slideUp();
        				return false;
        			}else{
        				if(monthList.length == 1){
        					$("#month").val(monthname[0]);
        				}else{
        					$("#month").val(monthname[0]+" - "+monthname[monthList.length-1]);
        				}
        				$("#startmonth").val(monthList);
        				$("#endmonth").val(monthname);
        			}
        			
        			monthCount=count;
        		   $(this).dialog("close");
        	   },
  
               "Close": function () {
            	   $(".select").prop("checked",false);
            	   $(".selectAll").prop("checked",false);
                   $(this).dialog("close");
               }
           }
       });
	
	$("#save").click(function(){
		
		var datalist = {
				"stuid" : $("#hiddenstuid").val(),
				"locid" : $("#hiddenlocid").val(),
				"accyear" : $("#hiddenaccyid").val(),
				"routeid" : $(".routename").val(),
				"stageid" : $(".stopname").val(),
				"droppoint":$(".drop-point").val(),
				"stmonths" : $("#startmonth").val(),
				"endmonth" :$("#endmonth").val(),
				"monthCount":monthCount,
		};
		
		if($(".routename").val() == "" || $(".routename").val() == undefined){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Route");
			document.getElementById("routename").style.border = "1px solid #AF2C2C";
			document.getElementById("routename").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}else if($(".stopname").val() == "" || $(".stopname").val() == undefined){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Stop");
			document.getElementById("stopname").style.border = "1px solid #AF2C2C";
			document.getElementById("stopname").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}else if($(".drop-point").val() == "" || $(".drop-point").val() == undefined){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Drop Point");
			document.getElementById("droppoint").style.border = "1px solid #AF2C2C";
			document.getElementById("droppoint").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if($("#month").val() == "" || $("#month").val() == undefined){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Month");
			document.getElementById("month").style.border = "1px solid #AF2C2C";
			document.getElementById("month").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else{
			$.ajax({
				type : 'POST',
				url : "transport.html?method=savetransportrequest",
				data : datalist,
				async : false,
				success : function(response) {
					var result = $.parseJSON(response);
					if(result.status == "success"){
						$(".successmessagediv").show();
						$(".validateTips").text("Transport Request Updating Progressing...");
						setTimeout(function(){
						window.location.href = "transport.html?method=transportFeeSearch";
					},3000);
					}else if(result.status == "fail"){
						$(".errormessagediv").show();
						$(".validateTips").text("Transport Request Updation Failed...");
						setTimeout(function(){
						window.location.href = "transport.html?method=transportFeeSearch";
					},3000);
					}
				}
			});
		}
	});
	
	
	$(".delete").click(function(){
		var id=$(this).closest("tr").attr("id");
		getMappedMonthDelete(id);
	});
	
});

   function getRouteNameList(){

	   var locationid=$("#hiddenlocid").val();
	   datalist = {
			   "locationid" :locationid
		},
		$.ajax({
			type : 'POST',
			url : "transport.html?method=getRouteNameList",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$(".routename").empty();
					$('.routename').append('<option value="">' + "----------Select----------"	+ '</option>');
					for ( var j = 0; j < result.routelist.length; j++) {
                       
						$('.routename').append('<option value="'

								+ result.routelist[j].routeCode + '">'

								+ result.routelist[j].routeName

								+ '</option>');
					}
			}
		});
}
   
   function getstoplist(routeid){

		   datalist = {
					"routeid" :routeid,
					"accyear":$("#hiddenaccyid").val()
				}, $.ajax({
					type : 'POST',
					url : "transport.html?method=getstoplist",
					data : datalist,
		 			async : false,
					
					success : function(response) {
						
						var result = $.parseJSON(response);
							$(".stopname").empty();
							$('.stopname').append('<option value="">' + "----------Select----------"	+ '</option>');
							for ( var j = 0; j < result.stoplist.length; j++) {
		                       
								$('.stopname').append('<option value="'

										+ result.stoplist[j].stage_id + '">'

										+ result.stoplist[j].stopname

										+ '</option>');
							}
							
					}
				});
		}
   function getAllstoplist(){

	   datalist = {
			
				"accyear":$("#hiddenaccyid").val()
			}, $.ajax({
				type : 'POST',
				url : "transport.html?method=getAllstoplist",
				data : datalist,
	 			async : false,
				
				success : function(response) {
					
					var result = $.parseJSON(response);
						
						
						$(".drop-point").empty();
						$('.drop-point').append('<option value="">' + "----------Select----------"	+ '</option>');
						for ( var j = 0; j < result.stoplist.length; j++) {
	                       
							$('.drop-point').append('<option value="'

									+ result.stoplist[j].stage_id + '">'

									+ result.stoplist[j].stopname

									+ '</option>');
						}
				}
			});
	}
   
   function getamountandstatus(stageid,pointer)
   {

	   datalist = {
				"stageid" :stageid
			}, $.ajax({
				type : 'POST',
				url : "transport.html?method=getamount",
				data : datalist,
	 			async : false,
				success : function(response) {
				var result = $.parseJSON(response);
				var amount=result.getamount.split(",")[0];
	            var status =result.getamount.split(",")[1];  

	            pointer.closest('tr').find('.amount').val(amount);
	            pointer.closest('tr').find('.tranportstatus').val(status);
	           }
			});
	}
function getSelectedMonth(){
	if($("#istransport").val() == 'Y'){
		
		for(var i=0;i<mappedMonth.jsonResponse.length;i++){
			$("table.month td."+mappedMonth.jsonResponse[i].months).closest("tr").remove();
		}
		
		
	
	}
}

function getMappedMonthDelete(id){
	datalist = {
			"id" :id,
		}, $.ajax({
			type : 'POST',
			url : "transport.html?method=getMappedMonthDelete",
			data : datalist,
 			async : false,
			success : function(response) {
			 var result = $.parseJSON(response);
			 if(result.jsonResponse=="true"){
				 $("tr#"+id).remove();
				 
				 $(".errormessagediv").hide();
				 $(".successmessagediv .validateTips").text("Record Deleted Successfully.");
				 $(".successmessagediv").show();
				 setTimeout(function(){
					 $(".successmessagediv").hide();
					 $(".successmessagediv .validateTips").text("");
					 location.reload();
				 },3000);
				 
				
			 
			 }
			 else{
				 $(".successmessagediv").hide();
				 $(".errormessagediv .validateTips").text("Record Not Deleted");
				 $(".errormessagediv").show();
				 setTimeout(function(){
					 $(".errormessagediv").hide();
					 $(".errormessagediv .validateTips").text("");
				 },3000);
			 }
			
           }
		});
	
	
}
function getMappedMonth(studentid,accyearid){
	
	datalist = {
			"studentid" :studentid,
			"accyearid" :accyearid
		}, $.ajax({
			type : 'POST',
			url : "transport.html?method=getMappedMonth",
			data : datalist,
 			async : false,
			success : function(response) {
			 mappmonth = $.parseJSON(response);
			
           }
		});
	return mappmonth;
}
