 flag="anywhere";

$(document).ready(function(){
	$("#Acyearid").val($("#hidenaccyear").val());
	$("input[name='requested_by']").change(function(){
		 $("#searchValue").val('');
	});
	 if($("input[name='requested_by']:checked").val()=="Student"){
		 $(".stand").show();
			$(".allstudenttable").show();
			$(".staffstand").hide();
			$(".stafftable").hide();
			$(".otherstable").hide();
		    getStudentListDetails();
		    $("#Acyearid").change(function(){
		    	$("#searchvalue").val("");
			 getStudentListDetails();
			 getClassList();
			 $("#sectionid").html("");
			 $("#sectionid").append("<option value='all'>ALL</option>");
		 });
		 $("#locationname").change(function(){
			
			 $("#searchvalue").val("");
			 var classname=$("#classname").val();
			 getStudentListDetails();
			 getClassList();
			 getSectionList(classname);
			 $("#sectionid").html("");
			 $("#sectionid").append("<option value='all'>ALL</option>");
			
		 });
			$("#classname").change(function(){
				$("#searchvalue").val("");
				var locationid=$("#locationname").val();
				var accyear=$("#Acyearid").val();
				var classname=$("#classname").val();
				getStudentListByClassName(locationid,accyear,classname);
				getSectionList(classname);
			});
			
			$("#sectionid").change(function(){
				
				var locationid=$("#locationname").val();
				var accyear=$("#Acyearid").val();
				var classname=$("#classname").val();
				var sectionid=$("#sectionid").val();
				getStudentListBySection(locationid,accyear,classname,sectionid);
			});
	 }
	 else if($("input[name='requested_by']:checked").val()=="staff"){
			$(".staffstand").show();
			$(".stafftable").show();
			$(".stand").hide();
			$(".allstudenttable").hide();
			$(".otherstable").hide();
			
			getStaffListDetails();
			
	 }
	 else if($("input[name='requested_by']:checked").val()=="others"){
		 $(".otherstable").show();
			$(".stafftable").hide();
			$(".staffstand").hide();
			$(".stand").hide();
			$(".allstudenttable").hide();
			$("#searchvalue").val(""); 
			 $("#hidensearch").show();
			getOthersListDetails();
	 }
	 $("input[name='subscriber_by']").change(function(){
			if($(this).val()=="subscriberno")
				{
				getStudentListDetails();
				}
			else if($(this).val()=="subscribername")
			     {
				getStudentListDetails();
				}
		});
	 $("#searchValue").keypress(function(e){
		 var searchname = $("#searchValue").val().trim();
		    if(e.keyCode == 13){
		        e.preventDefault();
		       if(flag=="anywhere") 
		    	   {
		    	   SearchSubscriberDetailsByAnyWhere();
		    	   
		    	   $("#locationname").change(function(){
			         	  if(searchname!="" || searchname!=null){
			         		 SearchSubscriberDetailsByAnyWhere();
			         	  }else{
			         		  getStudentListDetails();
			         		 
			         		 $("#sectionid").html("");
			    			 $("#sectionid").append("<option value='all'>ALL</option>");
			    			 }
			 			});
		    	   $("input[name='subscriber_by']").change(function(){
		    			if($(this).val()=="subscriberno")
		    				{
		    				SearchSubscriberDetailsByAnyWhere();
		    				}
		    			else if($(this).val()=="subscribername")
		    			     {
		    				SearchSubscriberDetailsByAnyWhere();
		    				}
		    		});
 		    }
		       else if (flag=="startwith") 
	    	   {
		    	   SearchSubscriberDetailsByStartWith();
		    	   $("#locationname").change(function(){
			         	  if(searchname!="" || searchname!=null){
			         		 SearchSubscriberDetailsByStartWith();
			         	  }else{
			         		  getStudentListDetails();
			         		
			         		 $("#sectionid").html("");
			    			 $("#sectionid").append("<option value='all'>ALL</option>"); 
			         	  }
			 			});
		    	   $("input[name='subscriber_by']").change(function(){
		    			if($(this).val()=="subscriberno")
		    				{
		    				SearchSubscriberDetailsByStartWith();
		    				}
		    			else if($(this).val()=="subscribername")
		    			     {
		    				SearchSubscriberDetailsByStartWith();
		    				}
		    		});
			   }
			   else if (flag=="endswith") 
	    	   {
				   SearchSubscriberDetailsByEndsWith();
				   $("#locationname").change(function(){
			         	  if(searchname!="" || searchname!=null){
			         		 SearchSubscriberDetailsByEndsWith();
			         	  }else{
			         		  getStudentListDetails();
			         		
			         		 $("#sectionid").html("");
			    			 $("#sectionid").append("<option value='all'>ALL</option>");
			         		 }
			 			});
				   $("input[name='subscriber_by']").change(function(){
						if($(this).val()=="subscriberno")
							{
							SearchSubscriberDetailsByEndsWith();
							}
						else if($(this).val()=="subscribername")
						     {
							SearchSubscriberDetailsByEndsWith();
							}
					});
			   }
		    }
		});	
	 /*----------------Subscriber Type (Student,staff,other)---------------*/
	 
	$("input[name='requested_by']").change(function(){
		
       /*--------Subscriber Type (Student)--------*/
		if($(this).val()=="Student"){
			$(".stand").show();
			$(".allstudenttable").show();
			$(".staffstand").hide();
			$(".stafftable").hide();
			$(".otherstable").hide();
			getStudentListDetails();
			 
			 $("#Acyearid").change(function(){
				 getStudentListDetails();
				 getClassList();
				 $("#sectionid").html("");
				 $("#sectionid").append("<option value='all'>ALL</option>");
			 });
			 $("#locationname").change(function(){
				
				 getStudentListDetails();
				 getClassList();
				 $("#sectionid").html("");
				 $("#sectionid").append("<option value='all'>ALL</option>");
				 
			 });
				$("#classname").change(function(){
					
					var locationid=$("#locationname").val();
					var accyear=$("#Acyearid").val();
					var classname=$("#classname").val();
					getSectionList(classname);
					getStudentListByClassName(locationid,accyear,classname);
				});
				
				$("#sectionid").change(function(){
					var locationid=$("#locationname").val();
					var accyear=$("#Acyearid").val();
					var classname=$("#classname").val();
					var sectionid=$("#sectionid").val();
					getStudentListBySection(locationid,accyear,classname,sectionid);
				});
			$("#searchValue").keypress(function(e){
				var searchname = $("#searchValue").val().trim();
			    if(e.keyCode == 13){
			        e.preventDefault();
			       if(flag=="anywhere") 
			    	   {
			    	   SearchSubscriberDetailsByAnyWhere();
			    	   $("#locationname").change(function(){
			         	  if(searchname!="" || searchname!=null){
			         		 SearchSubscriberDetailsByAnyWhere();
			         	  }else{
			         		  getStudentListDetails();
			         		 
			         		 $("#sectionid").html("");
							 $("#sectionid").append("<option value='all'>ALL</option>");
							 }
			 			});
			        
			    	   $("input[name='subscriber_by']").change(function(){
							if($(this).val()=="subscriberno")
								{
								SearchSubscriberDetailsByAnyWhere();
								}
							else if($(this).val()=="subscribername")
							     {
								SearchSubscriberDetailsByAnyWhere();
								}
						});
			    }
			       else if (flag=="startwith") 
		    	   {
			    	   SearchSubscriberDetailsByStartWith();
			    	   $("#locationname").change(function(){
				         	  if(searchname!="" || searchname!=null){
				         		 SearchSubscriberDetailsByStartWith();
				         	  }else{
				         		  getStudentListDetails();
				         		
				         		 $("#sectionid").html("");
								 $("#sectionid").append("<option value='all'>ALL</option>");
								 }
				 			});
			    	   $("input[name='subscriber_by']").change(function(){
							if($(this).val()=="subscriberno")
								{
								SearchSubscriberDetailsByStartWith();
								}
							else if($(this).val()=="subscribername")
							     {
								SearchSubscriberDetailsByStartWith();
								}
						});
			    	   
				   }
				   else if (flag=="endswith") 
		    	   {
					   SearchSubscriberDetailsByEndsWith();
					   $("#locationname").change(function(){
				         	  if(searchname!="" || searchname!=null){
				         		 SearchSubscriberDetailsByEndsWith();
				         	  }else{
				         		  getStudentListDetails();
				         		
				         		 $("#sectionid").html("");
								 $("#sectionid").append("<option value='all'>ALL</option>");
				         		 }
				 			});
					   $("input[name='subscriber_by']").change(function(){
							if($(this).val()=="subscriberno")
								{
								SearchSubscriberDetailsByEndsWith();
								}
							else if($(this).val()=="subscribername")
							     {
								SearchSubscriberDetailsByEndsWith();
								}
						});
				   }
			    }
			});
		}
/*--------------------------------Subscriber Type (staff)------------------------------------*/
		
		else if($(this).val()=="staff"){
			
			$(".staffstand").show();
			$(".stafftable").show();
			$(".stand").hide();
			$(".allstudenttable").hide();
			$(".otherstable").hide();
			getStaffListDetails();
			getdepartmentlist();
     		getDesignationList(department);
			$("#Acyearid").change(function(){
				    getStaffListDetails();
	         		getdepartmentlist();
	         		getDesignationList(department);
			 });
			$("#locationname").change(function(){
				  $("#searchvalue").val("");
				  var department=$("#department").val();
				    getStaffListDetails();
	         		getdepartmentlist();
	         		getDesignationList(department);
	         		$("#designation").val("");
	 			});
			$("#department").change(function(){
				$("#searchvalue").val("");
				var locationid = $("#locationname").val();
				var accyear = $("#Acyearid").val();
				var department=$("#department").val();
				var designation=$("#designation").val();
				getDesignationList(department);
				getStaffdetailsByDepartment(locationid,accyear,department);
			});
		      $("#designation").change(function(){
		    	   
		    	    var locationid = $("#locationname").val();
					var accyear = $("#Acyearid").val();
					var department=$("#department").val();
					var designation=$("#designation").val();
				    getStaffdetailsByDesignation(locationid,accyear,department,designation);
			});
			$("input[name='subscriber_by']").change(function(){
				if($(this).val()=="subscriberno")
					{
					getStaffListDetails();
					}
				else if($(this).val()=="subscribername")
				     {
					getStaffListDetails();
					}
			});
			$("#searchValue").keypress(function(e){
				var searchname = $("#searchValue").val().trim();
			    if(e.keyCode == 13){
			        e.preventDefault();
			        
			       if(flag=="anywhere") 
			    	   {
			    	   SearchStaffDetailsByAnyWhere();
			        $("#locationname").change(function(){
			         	  if(searchname!="" || searchname!=null){
			         		 SearchStaffDetailsByAnyWhere();
			         	  }else{
			         		 getStaffListDetails();
			         		}
			 			});
			        $("input[name='subscriber_by']").change(function(){
			    		if($(this).val()=="subscriberno")
			    			{
			    			SearchStaffDetailsByAnyWhere();
			    			}
			    		else if($(this).val()=="subscribername")
			    		     {
			    			SearchStaffDetailsByAnyWhere();
			    			}
			    	});
			    	   }
			       else if (flag=="startwith") 
		    	    {
			    	   SearchStaffDetailsByStartWith();
			    	   $("#locationname").change(function(){
				         	  if(searchname!="" || searchname!=null){
				         		 SearchStaffDetailsByStartWith();
				         	  }else{
				         		 getStaffListDetails();}
				 			});
			    	   $("input[name='subscriber_by']").change(function(){
			    			if($(this).val()=="subscriberno")
			    				{
			    				SearchStaffDetailsByStartWith();
			    				}
			    			else if($(this).val()=="subscribername")
			    			     {
			    				SearchStaffDetailsByStartWith();
			    				}
			    		});
				      }
				   else if (flag=="endswith") 
		    	   {
					   SearchStaffDetailsByEndsWith();
					   $("#locationname").change(function(){
				         	  if(searchname!="" || searchname!=null){
				         		 SearchStaffDetailsByEndsWith();
				         	  }else{
				         		 getStaffListDetails();}
				 			});
					   $("input[name='subscriber_by']").change(function(){
							if($(this).val()=="subscriberno")
								{
								SearchStaffDetailsByEndsWith();
								}
							else if($(this).val()=="subscribername")
							     {
								SearchStaffDetailsByEndsWith();
								}
						});
				   }
			    }
			});
		}
    /*-----------Subscriber Type (other)-------------*/
		else if($(this).val()=="others"){
			$(".otherstable").show();
			$(".stafftable").hide();
			$(".staffstand").hide();
			$(".stand").hide();
			$(".allstudenttable").hide();
			getOthersListDetails();
			$("#Acyearid").change(function(){
				 getOthersListDetails();	
			 });
			 $("#locationname").change(function(){
				 getOthersListDetails();	
				});
			
			$("#searchValue").keypress(function(e){
				var searchname = $("#searchValue").val().trim();
			    if(e.keyCode == 13){
			        e.preventDefault();
			        
			       if(flag=="anywhere") 
			    	   {
			        SearchOthersDetailsByAnyWhere();
			        $("#locationname").change(function(){
			         	  if(searchname!="" || searchname!=null){
			         		 SearchOthersDetailsByAnyWhere();
			         	  }else{
			         		 getOthersListDetails();}
			 			});
			        $("input[name='subscriber_by']").change(function(){
			    		if($(this).val()=="subscriberno")
			    			{
			    			SearchOthersDetailsByAnyWhere();
			    			}
			    		else if($(this).val()=="subscribername")
			    		     {
			    			SearchOthersDetailsByAnyWhere();
			    			}	
			    	});
			    	   }
			       else if (flag=="startwith") 
		    	   {
			    	   SearchOthersDetailsByStartWith();
			    	   $("#locationname").change(function(){
				         	  if(searchname!="" || searchname!=null){
				         		 SearchOthersDetailsByStartWith();
				         	  }else{
				         		 getOthersListDetails();}
				 			});
			    	   $("input[name='subscriber_by']").change(function(){
			    			if($(this).val()=="subscriberno")
			    				{
			    				SearchOthersDetailsByStartWith();
			    				}
			    			else if($(this).val()=="subscribername")
			    			     {
			    				SearchOthersDetailsByStartWith();
			    				}	
			    		});
				   }
				   else if (flag=="endswith") 
		    	   {
					   SearchOthersDetailsByEndsWith();
					   $("#locationname").change(function(){
				         	  if(searchname!="" || searchname!=null){
				         		 SearchOthersDetailsByEndsWith();
				         	  }else{
				         		 getOthersListDetails();}
				 			});
					   $("input[name='subscriber_by']").change(function(){
							if($(this).val()=="subscriberno")
								{
								   SearchOthersDetailsByEndsWith();
								}
							else if($(this).val()=="subscribername")
							     {
								   SearchOthersDetailsByEndsWith();
								}	
						});
				   }
			    }
			});
			$("input[name='subscriber_by']").change(function(){
				if($(this).val()=="subscriberno")
					{
					getOthersListDetails();
					}
				else if($(this).val()=="subscribername")
				     {
					getOthersListDetails();
					}	
			});
		}
	});
	
	  /*..........................GoTo Click..........................*/
	$("#gobutton").click(function(){
		var selection=$("#goto").val();
		var subscriberType=$("input[name='requested_by']:checked").val();
		var subscriberId=$("input[name='subid']:checked").val();
		var accyear = $('#Acyearid').val();
		var locid = $('#locationname').val();
		var editid=$("input[name='subid']:checked").attr("class");
		var location=editid;
		  	
		 if((subscriberId==undefined || subscriberId==" ")){
			$(".errormessagediv").show();
			$(".validateTips").text("Select Any One Record");
			
			$(".errormessagediv").fadeOut(3000);
		}
		else if((selection==undefined||selection=="----GoTo----")){
			$(".errormessagediv").show();
			$(".validateTips").text("Select Any One Details");
			document.getElementById('goto').style.border = "1px solid #AF2C2C";
			$(".errormessagediv").fadeOut(3000);
		}
		
		else if(selection=="subdetail"){
		 window.location.href ="LibraryMenu.html?method=gotosubscribersDetails&subId="+subscriberId+"&location="+location+"&accyear="+accyear+"&subscriberType="+subscriberType;
		}
		else if(selection=="issuestate"){
		 window.location.href ="LibraryMenu.html?method=IssueStatementBySubScriberType&subId="+subscriberId+"&location="+locid+"&accyear="+accyear+"&subscriberType="+subscriberType;
				}
		else if(selection=="issuereturn"){
			 window.location.href ="LibraryMenu.html?method=IssueReturnBySubScriberType&subId="+subscriberId+"&location="+locid+"&accyear="+accyear+"&subscriberType="+subscriberType;
		 //window.location.href ="LibraryMenu.html?method=GOtOIssueReturns&subId="+subscriberId+"&subscriberType="+subscriberType;
		 }
		 else {
			 $(".errormessagediv").show();
				$(".validateTips").text("No Issue And Returns");
				$(".errormessagediv").fadeOut(3000);
		 }
	});
	// validate subscriber Type

	 $("input[name='started_by']").change(function(){
     	if($(this).val()=="startwith"){
     		flag="startwith";	
     	}
     	else if($(this).val()=="endswith"){
     		flag="endswith";
     	}
         else {
        	 flag="anywhere";
         }
     }); 
});
function getClassList(){
	var locationid=$("#locationname").val();
	datalist={
			"locationid" : locationid
	},
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassByLocation",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#classname').html("");
			$('#classname').append('<option value="all">' +"ALL"+ '</option>');
			var j=0;
			var len=result.ClassList.length;
			for ( j = 0; j < len; j++) {
				$('#classname').append('<option value="'
						+ result.ClassList[j].classcode + '">'
						+ result.ClassList[j].classname
						+ '</option>');
			}
		}
	});
}
function getSectionList(classname){
	datalist={
			"classidVal" : classname,
			"locationId":$("#locationname").val()
	},
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#sectionid').html("");
			$('#sectionid').append('<option value="">' + "ALL"	+ '</option>');
			var j=0;
			var len=result.sectionList.length;
			for ( j = 0; j < len; j++) {
				$('#sectionid').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});
}

function getdepartmentlist(){
	$.ajax({
	type : 'POST',
	url : 'teacherregistration.html?method=getDepartment',
	async : false,
	success : function(response) {
		var data = $.parseJSON(response);
		$("#department").empty();
		$("#department")
		.append('<option value="all">ALL</option>');
		var j=0;
		var len=data.DEPARTMENTLIST.length;
		for (j = 0; j < len; j++) {
			$("#department")
			.append(
					'<option value="'
					+ data.DEPARTMENTLIST[j].depId
					+ '">'
					+ data.DEPARTMENTLIST[j].depName
					+ '</option>');
		}
		var hiddendept=$("#hiddendept").val();
		$("#department option[value=" + hiddendept + "]").attr('selected', 'true');
	}
  });
}
function getDesignationList(department){
    $.ajax({
	type : 'POST',
	url : 'teacherregistration.html?method=getDesignation',
	async : false,
	success : function(response) {
		var data = $.parseJSON(response);
		$('#designation').empty();
		$("#designation").append('<option value="all">ALL</option>');
		var j=0;
		var len=data.DESIGNATIONLIST.length;
		for ( j = 0; j < len;j++) {
			$('#designation')
			.append(
					'<option value="'
					+ data.DESIGNATIONLIST[j].desgid
					+ '">'
					+ data.DESIGNATIONLIST[j].desgname
					+ '</option>');
		}
		var hiddendes=$("#hiddendes").val();
		$("#designation option[value=" + hiddendes + "]").attr('selected', 'true');
	}
  });
} 
function getStudentListDetails(){
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var subscriberId =$("#subscriberId").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	var select=$("input[name='subscriber_by']:checked").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getStudentListDetails",
		data : {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"select":select,
			"subscriberId":subscriberId,
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
					$("#allstaff tbody").empty();
					$("#others tbody").empty();
				$("#allstudent tbody").append("<tr>"
						+"<td class='"+result.studentData[i].location+" "+"studentid1"+"'><input type='radio' name='subid' value='"+result.studentData[i].subscriberId+"' class='"+result.studentData[i].location+"'/></td>" 
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].studentName+"</td>"
						+"<td> "+result.studentData[i].studentAdmissionNo+"</td>"
						+"<td> "+result.studentData[i].rollNO+" </td>"
						+"<td> "+result.studentData[i].classid+" </td>"
						+"<td> "+result.studentData[i].division+" </td>"
						+"<td> "+result.studentData[i].status+" </td>"
						+"</tr>");
				}	
			}
			else{
				$("#allstudent tbody").append("<tr><td ColSpan='8'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
		}
	});
}
function getStudentListByClassName(){
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	var select=$("input[name='subscriber_by']:checked").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getStudentListByClassName",
		data : {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"select":select
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
					$("#allstaff tbody").empty();
					$("#others tbody").empty();
				$("#allstudent tbody").append("<tr>"
						+"<td class='"+result.studentData[i].studentId+" "+result.studentData[i].academicYearId+" "+result.studentData[i].locationId+" "+"studentid"+"'><input type='radio' name='subid' value='"+result.studentData[i].subscriberId+"' class='"+result.studentData[i].location+"'/></td>" 
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].studentName+"</td>"
						+"<td> "+result.studentData[i].studentAdmissionNo+"</td>"
						+"<td> "+result.studentData[i].rollNO+" </td>"
						+"<td> "+result.studentData[i].classid+" </td>"
						+"<td> "+result.studentData[i].division+" </td>"
						+"<td> "+result.studentData[i].status+" </td>"
						+"</tr>");
				}	
			}
				else{
					$("#allstudent tbody").append("<tr><td ColSpan='8'>No Records Found</td></tr>");
				}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
			pagination(100);
		}
	});
} 
function getStudentListBySection(){
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	var select=$("input[name='subscriber_by']:checked").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getStudentListBySection",
		data : {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"select":select
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
					$("#allstaff tbody").empty();
					$("#others tbody").empty();
				$("#allstudent tbody").append("<tr>"
						+"<td class='"+result.studentData[i].studentId+" "+result.studentData[i].academicYearId+" "+result.studentData[i].locationId+" "+"studentid"+"'><input type='radio' name='subid' value='"+result.studentData[i].subscriberId+"' class='"+result.studentData[i].location+"'/></td>" 
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].studentName+"</td>"
						+"<td> "+result.studentData[i].studentAdmissionNo+"</td>"
						+"<td> "+result.studentData[i].rollNO+" </td>"
						+"<td> "+result.studentData[i].classid+" </td>"
						+"<td> "+result.studentData[i].division+" </td>"
						+"<td> "+result.studentData[i].status+" </td>"
						+"</tr>");
				}	
			}
			else{
				$("#allstudent tbody").append("<tr><td ColSpan='8'>No Records Found</td></tr>");
			}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
			pagination(100);
		}
	});
}
function SearchSubscriberDetailsByAnyWhere(){
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	var searchname = $("#searchValue").val();
	var select=$("input[name='subscriber_by']:checked").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=SearchSubscriberDetailsByAnyWhere",
		data : {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"searchname":searchname,
			"select":select
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
					$("#allstaff tbody").empty();
					$("#others tbody").empty();
				$("#allstudent tbody").append("<tr>"
						+"<td class='"+result.studentData[i].studentId+" "+result.studentData[i].academicYearId+" "+result.studentData[i].locationId+" "+"studentid"+"'><input type='radio' name='subid' value='"+result.studentData[i].subscriberId+"' class='"+result.studentData[i].location+"'/></td>" 
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].studentName+"</td>"
						+"<td> "+result.studentData[i].studentAdmissionNo+"</td>"
						+"<td> "+result.studentData[i].rollNO+" </td>"
						+"<td> "+result.studentData[i].classid+" </td>"
						+"<td> "+result.studentData[i].division+" </td>"
						+"<td> "+result.studentData[i].status+" </td>"
						+"</tr>");
				}	
			}
			else{
				$("#allstudent tbody").append("<tr><td ColSpan='8'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
		}
	});
}
function SearchSubscriberDetailsByStartWith(){
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	var searchname = $("#searchValue").val();
	var select=$("input[name='subscriber_by']:checked").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=SearchSubscriberDetailsByStartWith",
		data : {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"searchname":searchname,
			"select":select
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
					$("#allstaff tbody").empty();
					$("#others tbody").empty();
				$("#allstudent tbody").append("<tr>"
						+"<td class='"+result.studentData[i].studentId+" "+result.studentData[i].academicYearId+" "+result.studentData[i].locationId+" "+"studentid"+"'><input type='radio' name='subid' value='"+result.studentData[i].subscriberId+"' class='"+result.studentData[i].location+"'/></td>" 
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].studentName+"</td>"
						+"<td> "+result.studentData[i].studentAdmissionNo+"</td>"
						+"<td> "+result.studentData[i].rollNO+" </td>"
						+"<td> "+result.studentData[i].classid+" </td>"
						+"<td> "+result.studentData[i].division+" </td>"
						+"<td> "+result.studentData[i].status+" </td>"
						+"</tr>");
				}	
			}
			else{
				$("#allstudent tbody").append("<tr><td ColSpan='8'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
		}
	});
}
function SearchSubscriberDetailsByEndsWith(){
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	var searchname = $("#searchValue").val();
	var select=$("input[name='subscriber_by']:checked").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=SearchSubscriberDetailsByEndsWith",
		data : {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"searchname":searchname,
			"select":select
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
					$("#allstaff tbody").empty();
					$("#others tbody").empty();
				$("#allstudent tbody").append("<tr>"
						+"<td class='"+result.studentData[i].studentId+" "+result.studentData[i].academicYearId+" "+result.studentData[i].locationId+" "+"studentid"+"'><input type='radio' name='subid' value='"+result.studentData[i].subscriberId+"' class='"+result.studentData[i].location+"'/></td>" 
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].studentName+"</td>"
						+"<td> "+result.studentData[i].studentAdmissionNo+"</td>"
						+"<td> "+result.studentData[i].rollNO+" </td>"
						+"<td> "+result.studentData[i].classid+" </td>"
						+"<td> "+result.studentData[i].division+" </td>"
						+"<td> "+result.studentData[i].status+" </td>"
						+"</tr>");
				}	
			}
			else{
				$("#allstudent tbody").append("<tr><td ColSpan='8'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
		}
	});
}
          /*-------Staff----*/
function getStaffListDetails(){
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var department=$("#department").val();
	var designation=$("#designation").val();
	var select=$("input[name='subscriber_by']:checked").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getStaffListDetails",
		data : {
			"location" :locationid,
			"accyear" :accyear,
			"department":department,
			"designation":designation,
			"select":select
		},
		async : false,
		success : function(response) { 
			var result = $.parseJSON(response);
			$("#allstaff tbody").empty();
			var i=0;
			var len=result.studentData.length;
			if(len> 0){
				for(i=0;i<len;i++){
					$("#allstudent tbody").empty();
					$("#others tbody").empty();
				$("#allstaff tbody").append("<tr>"
						+"<td class='"+result.studentData[i].studentId+" "+result.studentData[i].academicYearId+" "+result.studentData[i].locationId+" "+"studentid"+"'><input type='radio' name='subid' value='"+result.studentData[i].subscriberId+"' class='"+result.studentData[i].location+"'/></td>" 
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].staffName+"</td>"
						+"<td> "+result.studentData[i].staffDepartment+"</td>"
						+"<td> "+result.studentData[i].staffDesignation+" </td>"
						+"<td> "+result.studentData[i].status+" </td>"
						+"</tr>");
				}	
			}
			else{
				$("#allstaff tbody").append("<tr><td ColSpan='6'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
		}
	});
}
function getStaffdetailsByDepartment(){
	
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var department=$("#department").val();
	var designation=$("#designation").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getStaffdetailsByDepartment",
		data : {
			"location" :locationid,
			"accyear" :accyear,
			"department" :department,
			"designation":designation
		},
		async : false,
		success : function(response) { 
			var result = $.parseJSON(response);
			$("#allstaff tbody").empty();
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
					$("#allstudent tbody").empty();
					$("#others tbody").empty();
				$("#allstaff tbody").append("<tr>"
						+"<td class='"+result.studentData[i].studentId+" "+result.studentData[i].academicYearId+" "+result.studentData[i].locationId+" "+"studentid"+"'><input type='radio' name='subid' value='"+result.studentData[i].subscriberId+"' class='"+result.studentData[i].location+"'/></td>" 
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].staffName+"</td>"
						+"<td> "+result.studentData[i].staffDepartment+"</td>"
						+"<td> "+result.studentData[i].staffDesignation+" </td>"
						+"<td> "+result.studentData[i].status+" </td>"
						+"</tr>");
				}	
			}
			else{
				$("#allstaff tbody").append("<tr><td ColSpan='6'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
		}
	});
}
function getStaffdetailsByDesignation(){
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var department=$("#department").val();
	var designation=$("#designation").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getStaffdetailsByDesignation",
		data : {
			"location" :locationid,
			"accyear" :accyear,
			"department" :department,
			"designation" :designation
		},
		async : false,
		success : function(response) { 
			var result = $.parseJSON(response);
			$("#allstaff tbody").empty();
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
					$("#allstudent tbody").empty();
					$("#others tbody").empty();
				$("#allstaff tbody").append("<tr>"
						+"<td class='"+result.studentData[i].studentId+" "+result.studentData[i].academicYearId+" "+result.studentData[i].locationId+" "+"studentid"+"'><input type='radio' name='subid' value='"+result.studentData[i].subscriberId+"' class='"+result.studentData[i].location+"'/></td>" 
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].staffName+"</td>"
						+"<td> "+result.studentData[i].staffDepartment+"</td>"
						+"<td> "+result.studentData[i].staffDesignation+" </td>"
						+"<td> "+result.studentData[i].status+" </td>"
						+"</tr>");
				}	
			}
			else{
				$("#allstaff tbody").append("<tr><td ColSpan='6'>No Records Found</td></tr>");
				
			}
			pagintion(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
		}
	});
}
  /*-------------Staff search Details---------------*/
     /*------Search Staff Details By AnyWhere------*/
function SearchStaffDetailsByAnyWhere(){
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var department=$("#department").val();
	var designation=$("#designation").val();
	var searchname = $("#searchValue").val();
	var select=$("input[name='subscriber_by']:checked").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=SearchStaffDetailsByAnyWhere",
		data : {
			"location" :locationid,
			"accyear" :accyear,
			"department" :department,
			"designation" :designation,
			"searchname":searchname,
			"select":select
		},
		async : false,
		success : function(response) { 
			var result = $.parseJSON(response);
			$("#allstaff tbody").empty();
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
					$("#allstudent tbody").empty();
					$("#others tbody").empty();
				$("#allstaff tbody").append("<tr>"
						+"<td class='"+result.studentData[i].studentId+" "+result.studentData[i].academicYearId+" "+result.studentData[i].locationId+" "+"studentid"+"'><input type='radio' name='subid' value='"+result.studentData[i].subscriberId+"' class='"+result.studentData[i].location+"'/></td>" 
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].staffName+"</td>"
						+"<td> "+result.studentData[i].staffDepartment+"</td>"
						+"<td> "+result.studentData[i].staffDesignation+" </td>"
						+"<td> "+result.studentData[i].status+" </td>"
						+"</tr>");
				}	
			}
			else{
				$("#allstaff tbody").append("<tr><td ColSpan='6'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
		}
	});
}
/*------Search Staff Details By StartWith------*/
function SearchStaffDetailsByStartWith(){
	
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var department=$("#department").val();
	var designation=$("#designation").val();
	var searchname = $("#searchValue").val();
	var select=$("input[name='subscriber_by']:checked").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=SearchStaffDetailsByStartWith",
		data : {
			"location" :locationid,
			"accyear" :accyear,
			"department" :department,
			"designation" :designation,
			"searchname":searchname,
			"select":select
		},
		async : false,
		success : function(response) { 
			var result = $.parseJSON(response);
			$("#allstaff tbody").empty();
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
					$("#allstudent tbody").empty();
					$("#others tbody").empty();
				$("#allstaff tbody").append("<tr>"
						+"<td class='"+result.studentData[i].studentId+" "+result.studentData[i].academicYearId+" "+result.studentData[i].locationId+" "+"studentid"+"'><input type='radio' name='subid' value='"+result.studentData[i].subscriberId+"' class='"+result.studentData[i].location+"'/></td>" 
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].staffName+"</td>"
						+"<td> "+result.studentData[i].staffDepartment+"</td>"
						+"<td> "+result.studentData[i].staffDesignation+" </td>"
						+"<td> "+result.studentData[i].status+" </td>"
						+"</tr>");
				}	
			}
			else{
				$("#allstaff tbody").append("<tr><td ColSpan='6'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
		}
	});
}
/*------Search Staff Details By EndsWith------*/
function SearchStaffDetailsByEndsWith(){
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var department=$("#department").val();
	var designation=$("#designation").val();
	var searchname = $("#searchValue").val();
	var select=$("input[name='subscriber_by']:checked").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=SearchStaffDetailsByEndsWith",
		data : {
			"location" :locationid,
			"accyear" :accyear,
			"department" :department,
			"designation" :designation,
			"searchname":searchname,
			"select":select
		},
		async : false,
		success : function(response) { 
			var result = $.parseJSON(response);
			$("#allstaff tbody").empty();
			var i=0;
			var len=result.studentData.length;
			
			if(len > 0){
				for(i=0;i<len;i++){
					$("#allstudent tbody").empty();
					$("#others tbody").empty();
				$("#allstaff tbody").append("<tr>"
						+"<td class='"+result.studentData[i].studentId+" "+result.studentData[i].academicYearId+" "+result.studentData[i].locationId+" "+"studentid"+"'><input type='radio' name='subid' value='"+result.studentData[i].subscriberId+"' class='"+result.studentData[i].location+"'/></td>" 
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].staffName+"</td>"
						+"<td> "+result.studentData[i].staffDepartment+"</td>"
						+"<td> "+result.studentData[i].staffDesignation+" </td>"
						+"<td> "+result.studentData[i].status+" </td>"
						+"</tr>");
				}	
			}
			else{
				$("#allstaff tbody").append("<tr><td ColSpan='6'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
		}
	});
}
/*-----Staff List By Accyear and Location------*/
function getStaffListFilterByLocationAndAcyearid(){
	
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var department=$("#department").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getStaffListFilterByLocationAndAcyearid",
		data : {
			"location" :locationid,
			"accyear" :accyear,
			"department" :department,
		},
		async : false,
		success : function(response) { 
			var result = $.parseJSON(response);
			$("#allstaff tbody").empty();
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
					$("#allstudent tbody").empty();
					$("#others tbody").empty();
				$("#allstaff tbody").append("<tr>"
						+"<td class='"+result.studentData[i].studentId+" "+result.studentData[i].academicYearId+" "+result.studentData[i].locationId+" "+"studentid"+"'><input type='radio' name='subid' value='"+result.studentData[i].subscriberId+"' class='"+result.studentData[i].location+"'/></td>" 
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].staffName+"</td>"
						+"<td> "+result.studentData[i].staffDepartment+"</td>"
						+"<td> "+result.studentData[i].staffDesignation+" </td>"
						+"<td> "+result.studentData[i].status+" </td>"
						+"</tr>");
				}	
			}
			else{
				$("#allstaff tbody").append("<tr><td ColSpan='6'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
		}
	});
}
   /*--------------------------------Others Details---------------------------*/
function getOthersListDetails(){
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var select=$("input[name='subscriber_by']:checked").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getOthersListDetails",
		data : {
			
			"location" :locationid,
			"accyear" :accyear,
			"select":select
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#others tbody").empty();
			var i=0;
			var len=result.studentData.length;
			
				if(len > 0){
				for(i=0;i<len;i++){
					$("#allstudent tbody").empty();
					$("#allstaff tbody").empty();
				$("#others tbody").append("<tr>"
						+"<td class='"+result.studentData[i].studentId+" "+result.studentData[i].academicYearId+" "+result.studentData[i].locationId+" "+"studentid"+"'><input type='radio' name='subid' value='"+result.studentData[i].subscriberId+"' class='"+result.studentData[i].location+"'/></td>" 
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].othereName+"</td>"
						+"<td> "+result.studentData[i].othereContactNumber+"</td>"
						+"<td> "+result.studentData[i].othereMail_Id+" </td>"
						+"<td> "+result.studentData[i].othereAddress+" </td>"
						+"<td> "+result.studentData[i].status+" </td>"
						+"</tr>");
				}	
			}
				else{
					$("#others tbody").append("<tr><td ColSpan='7'>No Records Found</td></tr>");
				}
				pagination(100);
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.studentData.length);
		}
	});
}   
function SearchOthersDetailsByAnyWhere(){
	var locationid = $("#locationname").val();
	var select=$("input[name='subscriber_by']:checked").val();
	var searchname = $("#searchValue").val();
	var accyear = $("#Acyearid").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=SearchOthersDetailsByAnyWhere",
		data : {
			"location" :locationid,
			"select":select,
			"searchname":searchname,
			"accyear":accyear
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#others tbody").empty();
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
					$("#allstudent tbody").empty();
					$("#allstaff tbody").empty();
				$("#others tbody").append("<tr>"
						+"<td class='"+result.studentData[i].studentId+" "+result.studentData[i].academicYearId+" "+result.studentData[i].locationId+" "+"studentid"+"'><input type='radio' name='subid' value='"+result.studentData[i].subscriberId+"' class='"+result.studentData[i].location+"'/></td>" 
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].othereName+"</td>"
						+"<td> "+result.studentData[i].othereContactNumber+"</td>"
						+"<td> "+result.studentData[i].othereMail_Id+" </td>"
						+"<td> "+result.studentData[i].othereAddress+" </td>"
						+"<td> "+result.studentData[i].status+" </td>"
						+"</tr>");
				}	
			}
			else{
				$("#others tbody").append("<tr><td ColSpan='7'>Nothing found to display</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
		}
	});
}
  /*--------------------------------Others Details search Start With---------------------------*/
function SearchOthersDetailsByStartWith(){
	var locationid = $("#locationname").val();
	var select=$("input[name='subscriber_by']:checked").val();
	var searchname = $("#searchValue").val();
	var accyear = $("#Acyearid").val();
	
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=SearchOthersDetailsByStartWith",
		data : {
			"location" :locationid,
			"select":select,
			"searchname":searchname,
			"accyear":accyear
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#others tbody").empty();
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
					$("#allstudent tbody").empty();
					$("#allstaff tbody").empty();
				$("#others tbody").append("<tr>"
						+"<td class='"+result.studentData[i].studentId+" "+result.studentData[i].academicYearId+" "+result.studentData[i].locationId+" "+"studentid"+"'><input type='radio' name='subid' value='"+result.studentData[i].subscriberId+"' /></td>" 
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].othereName+"</td>"
						+"<td> "+result.studentData[i].othereContactNumber+"</td>"
						+"<td> "+result.studentData[i].othereMail_Id+" </td>"
						+"<td> "+result.studentData[i].othereAddress+" </td>"
						+"<td> "+result.studentData[i].status+" </td>"
						+"</tr>");
				}
			}
				else{
					$("#others tbody").append("<tr><td ColSpan='7'>No Records Found</td></tr>");
				}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
		}
	});
}
/*--------------------------------Others Details search Ends With---------------------------*/
function SearchOthersDetailsByEndsWith(){
	var locationid = $("#locationname").val();
	var select=$("input[name='subscriber_by']:checked").val();
	var searchname = $("#searchValue").val();
	var accyear = $("#Acyearid").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=SearchOthersDetailsByEndsWith",
		data : {
			"location" :locationid,
			"select":select,
			"searchname":searchname,
			"accyear":accyear
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#others tbody").empty();
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
					$("#allstudent tbody").empty();
					$("#allstaff tbody").empty();
				$("#others tbody").append("<tr>"
						+"<td class='"+result.studentData[i].studentId+" "+result.studentData[i].academicYearId+" "+result.studentData[i].locationId+" "+"studentid"+"'><input type='radio' name='subid' value='"+result.studentData[i].subscriberId+"' class='"+result.studentData[i].location+"'/></td>" 
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].othereName+"</td>"
						+"<td> "+result.studentData[i].othereContactNumber+"</td>"
						+"<td> "+result.studentData[i].othereMail_Id+" </td>"
						+"<td> "+result.studentData[i].othereAddress+" </td>"
						+"<td> "+result.studentData[i].status+" </td>"
						+"</tr>");
				}	
			}
			else{
				$("#others tbody").append("<tr><td ColSpan='7'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
		}
	});
}
function OthersBySubNoANDSubName(){
	$("input[name='subscriber_by']").change(function(){
		if($(this).val()=="subscriberno")
			{
			getOthersListDetails();
			}
		else if($(this).val()=="subscribername")
		     {
			getOthersListDetails();
			}	
	});
}