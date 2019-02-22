$(document).ready(function(){
	
	$("#accyear").val($("#hacademicyaer").val());
	$(".selectall").change(function(){
		$(".staffid").prop("checked",$(this).prop("checked"));
	});
	getIdCardStreamList();
	$("#locationname").change(function(){
		getSearchIdCardStreamList();
		$("#searchvalue").val("");
	});
	$("#designation").change(function(){
		getSearchIdCardStreamList();
		$("#searchvalue").val("");
	});
	$("#accyear").change(function(){
		getSearchIdCardStreamList();
		$("#searchvalue").val("");
	});
	$("#department").change(function(){
		getSearchIdCardStreamList();
		$("#searchvalue").val("");
	});

	$("#search").click(function(){
		searchList();
		
	});	
	$("#resetbtn").on("click", function (e) {
		$("#accyear,#locationname,#department,#designation").val("all");
		$("#searchvalue").val("");
	});
	
	getDesignation();
	getDeaprtment();

});

function getDeaprtment(){
	$.ajax({
		type : 'POST',
		url : 'teacherregistration.html?method=getDepartment',
		async : false,
		success : function(response) {
			var data = $.parseJSON(response);

			for ( var j = 0; j < data.DEPARTMENTLIST.length; j++) {

				$("#department")
						.append(
								'<option value="'
										+ data.DEPARTMENTLIST[j].depId
										+ '">'
										+ data.DEPARTMENTLIST[j].depName
										+ '</option>');
			}

		}

	});

}

function getDesignation(){
	$
	.ajax({
		type : 'POST',
		url : 'teacherregistration.html?method=getDesignation',
		async : false,
		success : function(response) {
			var data = $.parseJSON(response);
			$("#designation").empty();
			$("#designation").append("<option value=''>------Select--------</option>")
			for ( var j = 0; j < data.DESIGNATIONLIST.length; j++) {

				$("#designation").append(
								'<option value="'
										+ data.DESIGNATIONLIST[j].desgid
										+ '">'
										+ data.DESIGNATIONLIST[j].desgname
										+ '</option>');
			}

		}

	});
	
}
function getIdCardStreamList(){
	var academicYear=$("#globalAcademicYear").val();
	if(academicYear=""){
		academicYear="all";
	}
	var locationId=$("#school").val();
	if(locationId=""){
		locationId="all";
	}
	var dataList={"academicYear":academicYear,
					"locationId":locationId,
					"departmentId":"all",
					"designationId":"all",
	};
	$.ajax({
		type : 'POST',
	url : "IDCardMenu.html?method=StaffSingleIDCardDesignNew",
	//url :"IDCardMenu.html?method=NewstaffIdCardDesignList",
	//url : "adminMenu.html?method=StaffSingleIDCardDesign",
		data:dataList,
		async : false,
		success : function(data) {
			
			var result = $.parseJSON(data);
			$('#allstudent tbody').empty();
			
			for ( var j = 0; j < result.streamList.length; j++) {

				
				
				$('#allstudent tbody').append("<tr>" +
						"<td><input type='checkbox' class='staffid' value='"+result.streamList[j].teacherID+" "+result.streamList[j].academicYearCode+" "+result.streamList[j].locationId+"  "+result.streamList[j].departmentId+" "+result.streamList[j].status+"' /></td>" +
						"<td>"+ result.streamList[j].teacherID+ "</td>" +
						"<td>"+ result.streamList[j].teacherName+ "</td>" +
						"<td>"+ result.streamList[j].academicYear+ "</td>" +
						"<td>"+ result.streamList[j].locationName+ "</td>" +
						"<td>"+ result.streamList[j].designationName+ "</td>" +
						"<td>"+ result.streamList[j].departmentName+ "</td>" +
						"<td>"+ result.streamList[j].isActive+ "</td>" +
						"</tr>" +
						"");

			}
			
		
		}
	});
	
}
function getSearchIdCardStreamList(){
	
	
	var academicYear=$("#accyear").val();
	var locationId=$("#locationname").val();
	var designation=$("#designation").val();
	var department=$("#department").val();

	if(academicYear==""){
		academicYear="all";
	}

	if(locationId==""){
		locationId="all";
	}

	if(designation==""){
		designation="all";
	}

	if(department==""){
		department="all";
	}

	
 
	
	var dataList={	"academicYear":academicYear,
					"locationId":locationId,
					"designationId":designation,
					"departmentId": department,
	};

	$.ajax({
		type : 'POST',
	url : "IDCardMenu.html?method=StaffSingleIDCardDesignNew",
//url :"IDCardMenu.html?method=staffDesignSingle",
		data:dataList,
		async : false,
		success : function(data) {
			
			var result = $.parseJSON(data);
			$('#allstudent tbody').empty();
			
			for ( var j = 0; j < result.streamList.length; j++) {

				$('#allstudent tbody').append("<tr>" +
						"<td><input type='checkbox' class='staffid' value='"+result.streamList[j].teacherID+" "+result.streamList[j].locationId+" "+result.streamList[j].departmentId+" "+result.streamList[j].academicYearCode+"' /></td>" +
						"<td>"+result.streamList[j].teacherID+ "</td>" +
						"<td>"+result.streamList[j].teacherName+ "</td>" +
						"<td>"+result.streamList[j].academicYear+ "</td>" +
						"<td>"+result.streamList[j].locationName+ "</td>" +
						"<td>"+result.streamList[j].designationName+ "</td>" +
						"<td>"+result.streamList[j].departmentName+ "</td>" +
						"<td>"+result.streamList[j].isActive+ "</td>" +
						"</tr>" +
						"");

			}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.streamList.length);
			printpreview();
			
		}
	});
	
}


function printpreview(){
	$("#editStudent").click(function(){
		var staffid=[];
		var locationId=[];
		var designationId=[]; 
		var academicyear=[];
		var cnt = 0;
		$("#allstudent tbody tr").find(".staffid:checked").each(function(){
			staffid.push($(this).val().split(" ")[0]);
			locationId.push($(this).val().split(" ")[1]);
			designationId.push($(this).val().split(" ")[2]);
			academicyear.push($(this).val().split(" ")[3]);
			cnt++;
		});
		
		
		if (cnt == 0) {
			
				$(".errormessagediv").show();
				$(".validateTips").text("Please select atleast one record !");
				$(".errormessagediv").delay(2000).fadeOut();
				
				return false;
			} else{
				
			
		window.location.href="IDCardMenu.html?method=PrintPreviewStaffMultipleIDCard&staffid="+staffid+"&locationId="+locationId+"&designationId="+designationId+"&academicyear="+academicyear;
			}
			});
	
}

function searchList(){
	$("#allstudent tbody").empty();
	var academicYear=$("#accyear").val();
	var locationId=$("#locationname").val();
	var designation=$("#designation").val();
	var department=$("#department").val();
	var searchname = $("#searchvalue").val().trim();

	if(academicYear==""){
		academicYear="all";
	}

	if(locationId==""){
		locationId="all";
	}

	if(designation==""){
		designation="all";
	}

	if(department==""){
		department="all";
	}
	if(searchname==""){
		searchname="all";
	}

	
datalist = {
			
			"academicYear" :academicYear,
			"locationId":locationId,
			 "designation":designation,
		   	"department": department,
			"searchname":searchname,
		}, $.ajax({
	
	
			type : 'POST',
			url : "IDCardMenu.html?method=getStaffSearchByList",
			data : datalist,
			async : false,
			
			success : function(response) {
			 
				var result = $.parseJSON(response);
					
					for(var i=0;i<result.SearchList.length;i++){
						
					$("#allstudent tbody").append("<tr class='"+result.SearchList[i].teacherID+" "+result.SearchList[i].academicYear+" "+result.SearchList[i].locationId+" "+"studentid"+"'>"
							+"<td>"+(i+1)+ "</td>" 
							+"<td>"+result.SearchList[i].teacherID+ "</td>" 
							+"<td>"+result.SearchList[i].teacherName+ "</td>" 
							+"<td>"+result.SearchList[i].academicYear+ "</td>" 
							+"<td>"+result.SearchList[i].locationName+ "</td>" 
							+"<td>"+result.SearchList[i].designationName+ "</td>" 
							+"<td>"+result.SearchList[i].departmentName+ "</td>" 
							+"<td>"+result.SearchList[i].status+ "</td>" 
							+"</tr>");
					}	
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.SearchList.length);
	
					}
			});
}

