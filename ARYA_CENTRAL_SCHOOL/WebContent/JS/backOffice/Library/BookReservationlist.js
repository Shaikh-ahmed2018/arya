$(document).ready(function(){
	var checkedVal="Student";
	getFromDateList();
	getToDateList();
	getStudentSubName(checkedVal);	
	 getStudentSubNo(checkedVal);
		getStuAccessionNo(checkedVal);
		getStockEntryBookList();
	
	
	$("input[name='requested_by']").change(function(){
		 
	
	
	 if($("input[name='requested_by']:checked").val()=="Student"){
	
		 var checkedVal=$("input[name='requested_by']:checked").val();
	
			$(".allstudenttable").show();
			$(".stusubno").show();
			$(".teachsubno").hide();
			$(".othersubno").hide();
			$(".stusubname").show();
			$(".teachsubname").hide();
			$(".othersubname").hide();
			$(".otherstable").hide();
			$(".studAcc").show();
			$(".teachAcc").hide();
			$(".otherAcc").hide();
			 $(".stafftable").hide();
			 getStudentSubName(checkedVal);	
			 getStudentSubNo(checkedVal);
				getStuAccessionNo(checkedVal);
				getStockEntryBookList();

	 }
	 else if($("input[name='requested_by']:checked").val()=="Teacher")
		 {
	
		 var checkedVal=$("input[name='requested_by']:checked").val();
		 
		 $(".stafftable").show();
		 $(".stusubno").hide();
			$(".teachsubno").show();
			$(".othersubno").hide();
		 $(".stusubname").hide();
			$(".teachsubname").show();
			$(".othersubname").hide();
			$(".studAcc").hide();
			$(".teachAcc").show();
			$(".otherAcc").hide();
		 $(".allstudenttable").hide();
			$(".otherstable").hide();
			 getTeacherSubName(checkedVal);
			 getTeacherSubNo(checkedVal);
				getTeachAccessionNo(checkedVal);
			getStockEntryBookList();
			
		 }
	 else if($("input[name='requested_by']:checked").val()=="other")
	 {
		 var checkedVal=$("input[name='requested_by']:checked").val();
		 $(".stusubno").hide();
			$(".teachsubno").hide();
			$(".othersubno").show();
		 $(".stusubname").hide();
			$(".teachsubname").hide();
			$(".othersubname").show();
			$(".studAcc").hide();
			$(".teachAcc").hide();
			$(".otherAcc").show();
	
	 $(".allstudenttable").hide();
		$(".otherstable").show();
		 $(".stafftable").hide();
		 getOtherSubName(checkedVal);
		 getOtherSubNo(checkedVal);
			getOtherAccessionNo(checkedVal);
			getStockEntryBookList();
		
	 }
	});

	$("#selectall").change(function(){
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
	
	
	
	$("#editId").click(function(){
			$(".successmessagediv1").hide();
			var cnt = 0;
			

			$('input[type="checkbox"]:checked').map(function() {
				getData = $(this).val();
				cnt++;
				
			});
			
			

			if (cnt == 0 || cnt > 1) {
				$(".errormessagediv").show();
				$(".validateTips").text("Select any one Item Type");
				return false;
			} 
			else
			{
				window.location.href = "LibraryMenu.html?method=editStockEntryDetail&stockid="+getData;
			}
		});
	pagination(100);
	
});

function getStockEntryBookList(){
		datalist={
				
		},
		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=getReservationListDetails",
			data : datalist,
			async : false,
			success : function(response) {

				var result = $.parseJSON(response);
				$("#allstudent tbody").empty();
			
				for(var i=0;i<result.reservationList.length;i++){
					$("#allstudent tbody").append("<tr>" +
							"<td><input type='checkbox' name='select' class='select'  value='"+result.reservationList[i].reservationId+"'/></td>"+
							"<td>"+result.reservationList[i].bookTitle+"</td>"+
							"<td>"+result.reservationList[i].location+"</td>"+
							"<td>"+result.reservationList[i].currentStatus+"</td>"+
						/*	"<td>"+result.reservationList[i].issued_to+"</td>"+*/
							"<td>"+result.reservationList[i].from_date+"</td>"+
							"<td>"+result.reservationList[i].to_date+"</td>"+
							"<td>"+result.reservationList[i].location+"</td>"+
							"</tr>"
					
					);
					
				}
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.reservationList.length);
			}
		});
			
	}

function getTeacherSubName(checkedVal){
	
	$.ajax({
		type:"POST",
		url:"LibraryMenu.html?method=getTeachSubscriberName",
		data : {
			"checkedVal" : checkedVal,
			
		},
		async : false,
		success:function(data){
			var result = $.parseJSON(data);
			$("#stuSublist").empty();
			$("#stuSublist").append("<option value=''>All</option>");
			
			for(var i=0;i<result.data.length;i++){
				
			$("#stuSublist").append("<option value='"+result.data[i].subscriberId+"'>" 
					+result.data[i].subscriberName+"</option>");
			}
		}
	});
	
	return false;
}
function getStudentSubName(checkedVal){
	
	$.ajax({
		type:"POST",
		url:"LibraryMenu.html?method=getStudentSubName",
		data : {
			"checkedVal" : checkedVal,
			
		},
		async : false,
		success:function(data){
			var result = $.parseJSON(data);
			$("#teaSublist").empty();
			$("#teaSublist").append("<option value=''>All</option>");
			
			for(var i=0;i<result.data.length;i++){
				
			$("#teaSublist").append("<option value='"+result.data[i].subscriberId+"'>" 
					+result.data[i].subscriberName+"</option>");
			}
		}
	});
	
	return false;
}
function getOtherSubName(checkedVal){
	
	$.ajax({
		type:"POST",
		url:"LibraryMenu.html?method=getOtherSubName",
		data : {
			"checkedVal" : checkedVal,
			
		},
		async : false,
		success:function(data){
			var result = $.parseJSON(data);
			$("#otherSublist").empty();
			$("#otherSublist").append("<option value=''>All</option>");
			
			for(var i=0;i<result.data.length;i++){
				
			$("#otherSublist").append("<option value='"+result.data[i].subscriberId+"'>" 
					+result.data[i].subscriberName+"</option>");
			}
		}
	});
	
	return false;
}


function getStudentSubNo(checkedVal){
	
	$.ajax({
		type:"POST",
		url:"LibraryMenu.html?method=getStudentSubNo",
		data : {
			"checkedVal" : checkedVal,
			
		},
		async : false,
		success:function(data){
			var result = $.parseJSON(data);
			$("#studsubName").empty();
			$("#studsubName").append("<option value=''>All</option>");
			
			for(var i=0;i<result.data.length;i++){
				
			$("#studsubName").append("<option value='"+result.data[i].subscriberId+"'>" 
					+result.data[i].subscriberName+"</option>");
			}
		}
	});
	
	return false;
}

function getTeacherSubNo(checkedVal){
	
	$.ajax({
		type:"POST",
		url:"LibraryMenu.html?method=getTeacherSubNo",
		data : {
			"checkedVal" : checkedVal,
			
		},
		async : false,
		success:function(data){
			var result = $.parseJSON(data);
			$("#teachsubName").empty();
			$("#teachsubName").append("<option value=''>All</option>");
			
			for(var i=0;i<result.data.length;i++){
				
			$("#teachsubName").append("<option value='"+result.data[i].subscriberId+"'>" 
					+result.data[i].subscriberName+"</option>");
			}
		}
	});
	
	return false;
}

function getOtherSubNo(checkedVal){
	
	$.ajax({
		type:"POST",
		url:"LibraryMenu.html?method=getOtherSubNo",
		data : {
			"checkedVal" : checkedVal,
			
		},
		async : false,
		success:function(data){
			var result = $.parseJSON(data);
			$("#othersubName").empty();
			$("#othersubName").append("<option value=''>All</option>");
			
			for(var i=0;i<result.data.length;i++){
				
			$("#othersubName").append("<option value='"+result.data[i].subscriberId+"'>" 
					+result.data[i].subscriberName+"</option>");
			}
		}
	});
	
	return false;
}

function getStuAccessionNo(checkedVal){
	
	$.ajax({
		type:"POST",
		url:"LibraryMenu.html?method=getStuAccessionNo",
		data : {
			"checkedVal" : checkedVal,
			
		},
		async : false,
		success:function(data){
			var result = $.parseJSON(data);
			$("#studAccNo").empty();
			$("#studAccNo").append("<option value=''>All</option>");
			
			for(var i=0;i<result.data.length;i++){
				
			$("#studAccNo").append("<option value='"+result.data[i].accessionNoId+"'>" 
					+result.data[i].accessionNo+"</option>");
			}
		}
	});
	
	return false;
}

function getTeachAccessionNo(checkedVal){
	
	$.ajax({
		type:"POST",
		url:"LibraryMenu.html?method=getTeachAccessionNo",
		data : {
			"checkedVal" : checkedVal,
			
		},
		async : false,
		success:function(data){
			var result = $.parseJSON(data);
			$("#teachAccNo").empty();
			$("#teachAccNo").append("<option value=''>All</option>");
			
			for(var i=0;i<result.data.length;i++){
				
			$("#teachAccNo").append("<option value='"+result.data[i].accessionNoId+"'>" 
					+result.data[i].accessionNo+"</option>");
			}
		}
	});
	
	return false;
}

function getOtherAccessionNo(checkedVal){
	
	$.ajax({
		type:"POST",
		url:"LibraryMenu.html?method=getOtherAccessionNo",
		data : {
			"checkedVal" : checkedVal,
			
		},
		async : false,
		success:function(data){
			var result = $.parseJSON(data);
			$("#otherAccNo").empty();
			$("#otherAccNo").append("<option value=''>All</option>");
			
			for(var i=0;i<result.data.length;i++){
				
			$("#otherAccNo").append("<option value='"+result.data[i].accessionNoId+"'>" 
					+result.data[i].accessionNo+"</option>");
			}
		}
	});
	
	return false;
}

function getFromDateList(checkedVal){
	
	$.ajax({
		type:"POST",
		url:"LibraryMenu.html?method=getFromDateList",
		data : {
			"checkedVal" : checkedVal,
			
		},
		async : false,
		success:function(data){
			var result = $.parseJSON(data);
			$("#fromdate").empty();
			$("#fromdate").append("<option value=''>All</option>");
			
			for(var i=0;i<result.data.length;i++){
				
			$("#fromdate").append("<option value='"+result.data[i].fromDate+"'>" 
					+result.data[i].fromDate+"</option>");
			}
		}
	});
	
	return false;
}

function getToDateList(checkedVal){
	
	$.ajax({
		type:"POST",
		url:"LibraryMenu.html?method=getToDateList",
		data : {
			"checkedVal" : checkedVal,
			
		},
		async : false,
		success:function(data){
			var result = $.parseJSON(data);
			$("#todate").empty();
			$("#todate").append("<option value=''>All</option>");
			
			for(var i=0;i<result.data.length;i++){
				
			$("#todate").append("<option value='"+result.data[i].toDate+"'>" 
					+result.data[i].toDate+"</option>");
			}
		}
	});
	
	return false;
}

	
	



	
	


	
	
