$(document).ready(function(){
	$("#accyear").val($("#hacademicyaer").val());
	getSearchStaffIdCardList();
$("#locationname").change(function(){
	getSearchStaffIdCardList();
});
$("#department").change(function(){
	getSearchStaffIdCardList();
});
$("#accyear").change(function(){
	getSearchStaffIdCardList();
});
getDepartment();
});



function getDepartment(){
	$
	.ajax({
		type : 'POST',
		url : 'teacherregistration.html?method=getDepartment',
		async : false,
		success : function(response) {
			var data = $.parseJSON(response);
			$("#department").empty();
			$("#department").append("<option value=''>------Select--------</option>")
			for ( var j = 0; j < data.DEPARTMENTLIST.length; j++) {

				$("#department").append(
								'<option value="'
						
						
										+ data.DEPARTMENTLIST[j].depId
										+ '">'
										+ data.DEPARTMENTLIST[j].depName
										+ '</option>');
			}

		}

	});
		
		

	
	
	
	
}
function getSearchStaffIdCardList(){// all filter
	var academicYear=$("#accyear").val();
	if($("#accyear").val()=="" || $("#accyear").val()==undefined){
		academicYear="all";
	}
	var locationId=$("#locationname").val();
	if($("#locationname").val()=="" || $("#locationname").val()==undefined){
		locationId="all";
	}
	var department=$("#department").val();
	if($("#department").val()=="" || $("#department").val()==undefined){
		department="all";
	}
	var dataList={"academicYear":academicYear,
					"locationId":locationId,
					"department":department,
	};
	$.ajax({
		url : "IDCardMenu.html?method=NewstaffIdCardDesignList",
		data:dataList,
		async : false,
		success : function(data) {
			
			var result = $.parseJSON(data);
			$('#allstudent tbody').empty();
			
			for ( var j = 0; j < result.departmentList.length; j++) {

				$('#allstudent tbody').append("<tr class='"+ result.departmentList[j].academicYearCode+" "+result.departmentList[j].locationId+" "+result.departmentList[j].departmentId+"'>" +
						"<td>"+ result.departmentList[j].sno+ "</td>" +
						"<td>"+ result.departmentList[j].academicYear+ "</td>" +
						"<td>"+ result.departmentList[j].locationName+ "</td>" +
						"<td>"+ result.departmentList[j].departmentName+ "</td>" +
						"<td>"+ result.departmentList[j].status+ "</td>" +
				
						"</tr>" +
						"");

			}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.departmentList.length);
			rowClickable();
		}
	});
	
}





function rowClickable(){
	$("#allstudent tbody tr").click(function(){
		
		window.location.href="adminMenu.html?method=PrintStaffIDCard&accyear="+$(this).attr("class").split(" ")[0]+"&schoolId="+$(this).attr("class").split(" ")[1]+"&departmentId="+$(this).attr("class").split(" ")[2];

	});
}


/*
$(document).ready(function(){

$("#allstudent tbody tr").click(function(){
			
			window.location.href="adminMenu.html?method=PrintStaffIDCard";

		});
});
	*/