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
		    getMostWantedStudentListDetails();
		    $("#Acyearid").change(function(){
			 getMostWantedStudentListDetails();
			 getClassList();
			 $("#sectionid").html("");
			 $("#sectionid").append("<option value='all'>ALL</option>");
		 });
		 $("#locationname").change(function(){
			 $("#searchvalue").val("");
			 var classname=$("#classname").val();
			 getMostWantedStudentListDetails();
			 
			 if($("#locationname").val()!="all"){
				 getClassList();
				 getSectionList(classname); 
			 }
			 else{
				 $("#classname").html("");
				 $("#classname").append("<option value='all'>ALL</option>");
				 
				 $("#sectionid").html("");
				 $("#sectionid").append("<option value='all'>ALL</option>");
			 }
		 });
			$("#classname").change(function(){
				$("#searchvalue").val("");
				var locationid=$("#locationname").val();
				var accyear=$("#Acyearid").val();
				var classname=$("#classname").val();
				if($("#classname").val()=="all" || $("#classname").val()==""){
					$("#sectionid").html("");
					 $("#sectionid").append("<option value='all'>ALL</option>");
				}
				else{
				getMostWantedStudentListByClassName(locationid,accyear,classname);
				getSectionList(classname);
				}
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
			
			getMostWantedStaffListDetails();
			
			$("#locationname").change(function(){
				  $("#searchvalue").val("");
				  var department=$("#department").val();
				    getMostWantedStaffListDetails();
	         		
	         if($("#locationname").val()!="all"){
	        	      getdepartmentlist();
	        	      getDesignationList(department); 
	   			 }
	   			 else{
	   				 $("#department").html("");
	   				 $("#department").append("<option value='all'>ALL</option>");
	   				 
	   				 $("#designation").html("");
	   				 $("#designation").append("<option value='all'>ALL</option>");
	   			 }
	 			});
			$("#department").change(function(){
				$("#searchvalue").val("");
				var locationid = $("#locationname").val();
				var accyear = $("#Acyearid").val();
				var department=$("#department").val();
				var designation=$("#designation").val();
				getDesignationList(department);
				getMostWantedStaffdetailsByDepartment(locationid,accyear,department);
			});
		      $("#designation").change(function(){
		    	    var locationid = $("#locationname").val();
					var accyear = $("#Acyearid").val();
					var department=$("#department").val();
					var designation=$("#designation").val();
				    getMostWantedStaffdetailsByDesignation(locationid,accyear,department,designation);
			});
			
			
	 }
	 else if($("input[name='requested_by']:checked").val()=="others"){
		 $(".otherstable").show();
			$(".stafftable").hide();
			$(".staffstand").hide();
			$(".stand").hide();
			$(".allstudenttable").hide();
			getMostWantedOthersListDetails();
	 }
	 $("input[name='subscriber_by']").change(function(){
			if($(this).val()=="subscriberno")
				{
				getMostWantedStudentListDetails();
				}
			else if($(this).val()=="subscribername")
			     {
				getMostWantedStudentListDetails();
				}
		});

	 $("#searchValue").keypress(function(e){
		 var searchname = $("#searchValue").val().trim();
		    if(e.keyCode == 13){
		        e.preventDefault();
		       if(flag=="anywhere") 
		    	   {
		    	   SearchMostWantedStudentDetailsByAnyWhere();
		    	   
		    	   $("#locationname").change(function(){
			         	  if(searchname!="" || searchname!=null){
			         		 SearchMostWantedStudentDetailsByAnyWhere();
			         	  }else{
			         		  getMostWantedStudentListDetails();
			         		 $("#sectionid").html("");
			    			 $("#sectionid").append("<option value='all'>ALL</option>");
			         		 }
			 			});
		    	   $("input[name='subscriber_by']").change(function(){
		    			if($(this).val()=="subscriberno")
		    				{
		    				SearchMostWantedStudentDetailsByAnyWhere();
		    				}
		    			else if($(this).val()=="subscribername")
		    			     {
		    				SearchMostWantedStudentDetailsByAnyWhere();
		    				}
		    		});
 		    }
		       else if (flag=="startwith") 
	    	   {
		    	   SearchMostWantedStudentDetailsByAnyWhere();
		    	   $("#locationname").change(function(){
			         	  if(searchname!="" || searchname!=null){
			         		 SearchMostWantedStudentDetailsByAnyWhere();
			         	  }else{
			         		  getMostWantedStudentListDetails();
			         		 $("#sectionid").html("");
			    			 $("#sectionid").append("<option value='all'>ALL</option>");
			         		 }
			 			});
		    	   $("input[name='subscriber_by']").change(function(){
		    			if($(this).val()=="subscriberno")
		    				{
		    				SearchMostWantedStudentDetailsByAnyWhere();
		    				}
		    			else if($(this).val()=="subscribername")
		    			     {
		    				SearchMostWantedStudentDetailsByAnyWhere();
		    				}
		    		});
			   }
			   else if (flag=="endswith") 
	    	   {
				   SearchMostWantedStudentDetailsByAnyWhere();
				   $("#locationname").change(function(){
			         	  if(searchname!="" || searchname!=null){
			         		 SearchMostWantedStudentDetailsByAnyWhere();
			         	  }else{
			         		  getMostWantedStudentListDetails();
			         		 $("#sectionid").html("");
			    			 $("#sectionid").append("<option value='all'>ALL</option>");
			         		 }
			 			});
				   $("input[name='subscriber_by']").change(function(){
						if($(this).val()=="subscriberno")
							{
							SearchMostWantedStudentDetailsByAnyWhere();
							}
						else if($(this).val()=="subscribername")
						     {
							SearchMostWantedStudentDetailsByAnyWhere();
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
			getMostWantedStudentListDetails();
			 $("#Acyearid").change(function(){
				 getMostWantedStudentListDetails();
				 getClassList();
				 $("#sectionid").html("");
				 $("#sectionid").append("<option value='all'>ALL</option>");
			 });
			 $("#locationname").change(function(){
				 $("#searchvalue").val("");
				 getMostWantedStudentListDetails();
				 
				 if($("#locationname").val()!="all"){
					 getClassList();
					 getSectionList(classname); 
				 }
				 else{
					 $("#classname").html("");
					 $("#classname").append("<option value='all'>ALL</option>");
					 
					 $("#sectionid").html("");
					 $("#sectionid").append("<option value='all'>ALL</option>");
				 }
				 
			 });
				$("#classname").change(function(){
					$("#searchvalue").val("");
					var locationid=$("#locationname").val();
					var accyear=$("#Acyearid").val();
					var classname=$("#classname").val();
					getSectionList(classname);
					getMostWantedStudentListByClassName(locationid,accyear,classname);
				});
				
				$("#sectionid").change(function(){
					var locationid=$("#locationname").val();
					var accyear=$("#Acyearid").val();
					var classname=$("#classname").val();
					var sectionid=$("#sectionid").val();
					getStudentListBySection(locationid,accyear,classname,sectionid);
				});
				
				$("input[name='subscriber_by']").change(function(){
					if($(this).val()=="subscriberno")
						{
						 getMostWantedStudentListDetails();
						}
					else if($(this).val()=="subscribername")
					     {
						 getMostWantedStudentListDetails();
						}
				  });
				
				
			$("#searchValue").keypress(function(e){
				var searchname = $("#searchValue").val().trim();
			    if(e.keyCode == 13){
			        e.preventDefault();
			       if(flag=="anywhere") 
			    	   {
			    	   SearchMostWantedStudentDetailsByAnyWhere();
			    	   $("#locationname").change(function(){
			         	  if(searchname!="" || searchname!=null){
			         		 SearchMostWantedStudentDetailsByAnyWhere();
			         	  }else{
			         		  getMostWantedStudentListDetails();
			         		 $("#sectionid").html("");
							 $("#sectionid").appendl("<option value='all'>ALL</option>");
							 }
			 			});
			        
			    	   $("input[name='subscriber_by']").change(function(){
							if($(this).val()=="subscriberno")
								{
								SearchMostWantedStudentDetailsByAnyWhere();
								}
							else if($(this).val()=="subscribername")
							     {
								SearchMostWantedStudentDetailsByAnyWhere();
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
				         		  getMostWantedStudentListDetails();
				         		 $("#sectionid").val('');}
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
					   SearchMostWantedStudentDetailsByAnyWhere();
					   $("#locationname").change(function(){
				         	  if(searchname!="" || searchname!=null){
				         		 SearchMostWantedStudentDetailsByAnyWhere();
				         	  }else{
				         		  getMostWantedStudentListDetails();
				         		 $("#sectionid").val("");}
				 			});
					   $("input[name='subscriber_by']").change(function(){
							if($(this).val()=="subscriberno")
								{
								SearchMostWantedStudentDetailsByAnyWhere();
								}
							else if($(this).val()=="subscribername")
							     {
								SearchMostWantedStudentDetailsByAnyWhere();
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
			getMostWantedStaffListDetails();
			
			$("#Acyearid").change(function(){
				    getMostWantedStaffListDetails();
	         		getdepartmentlist();
	         		getDesignationList(department);
			 });
			$("#locationname").change(function(){
				  $("#searchvalue").val("");
				  var department=$("#department").val();
				    getMostWantedStaffListDetails();
	         		
				    if($("#locationname").val()!="all"){
		        	      getdepartmentlist();
		        	      getDesignationList(department); 
		   			 }
		   			 else{
		   				 $("#department").html("");
		   				 $("#department").append("<option value='all'>ALL</option>");
		   				 
		   				 $("#designation").html("");
		   				 $("#designation").append("<option value='all'>ALL</option>");
		   			 }
	 			});
			$("#department").change(function(){
				$("#searchvalue").val("");
				var locationid = $("#locationname").val();
				var accyear = $("#Acyearid").val();
				var department=$("#department").val();
				var designation=$("#designation").val();
				getDesignationList(department);
				getMostWantedStaffdetailsByDepartment(locationid,accyear,department);
			});
		      $("#designation").change(function(){
		    	    var locationid = $("#locationname").val();
					var accyear = $("#Acyearid").val();
					var department=$("#department").val();
					var designation=$("#designation").val();
				    getMostWantedStaffdetailsByDesignation(locationid,accyear,department,designation);
			});
			$("input[name='subscriber_by']").change(function(){
				if($(this).val()=="subscriberno")
					{
					getMostWantedStaffListDetails();
					}
				else if($(this).val()=="subscribername")
				     {
					getMostWantedStaffListDetails();
					}
			});
			$("#searchValue").keypress(function(e){
				var searchname = $("#searchValue").val().trim();
			    if(e.keyCode == 13){
			        e.preventDefault();
			        
			       if(flag=="anywhere") 
			    	   {
			    	   SearchMostWantedStaffDetailsByAnyWhere();
			        $("#locationname").change(function(){
			         	  if(searchname!="" || searchname!=null){
			         		 SearchMostWantedStaffDetailsByAnyWhere();
			         	  }else{
			         		 getMostWantedStaffListDetails();
			         		}
			 			});
			        $("input[name='subscriber_by']").change(function(){
			    		if($(this).val()=="subscriberno")
			    			{
			    			SearchMostWantedStaffDetailsByAnyWhere();
			    			}
			    		else if($(this).val()=="subscribername")
			    		     {
			    			SearchMostWantedStaffDetailsByAnyWhere();
			    			}
			    	});
			    	   }
			       else if (flag=="startwith") 
		    	    {
			    	   SearchMostWantedStaffDetailsByAnyWhere();
			    	   $("#locationname").change(function(){
				         	  if(searchname!="" || searchname!=null){
				         		 SearchMostWantedStaffDetailsByAnyWhere();
				         	  }else{
				         		 getMostWantedStaffListDetails();}
				 			});
			    	   $("input[name='subscriber_by']").change(function(){
			    			if($(this).val()=="subscriberno")
			    				{
			    				SearchMostWantedStaffDetailsByAnyWhere();
			    				}
			    			else if($(this).val()=="subscribername")
			    			     {
			    				SearchMostWantedStaffDetailsByAnyWhere();
			    				}
			    		});
				      }
				   else if (flag=="endswith") 
		    	   {
					   SearchMostWantedStaffDetailsByAnyWhere();
					   $("#locationname").change(function(){
				         	  if(searchname!="" || searchname!=null){
				         		 SearchMostWantedStaffDetailsByAnyWhere();
				         	  }else{
				         		 getMostWantedStaffListDetails();}
				 			});
					   $("input[name='subscriber_by']").change(function(){
							if($(this).val()=="subscriberno")
								{
								SearchMostWantedStaffDetailsByAnyWhere();
								}
							else if($(this).val()=="subscribername")
							     {
								SearchMostWantedStaffDetailsByAnyWhere();
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
			getMostWantedOthersListDetails();
			$("#Acyearid").change(function(){
				 getMostWantedOthersListDetails();	
			 });
			 $("#locationname").change(function(){
				 getMostWantedOthersListDetails();	
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
			         		 getMostWantedOthersListDetails();}
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
				         		 getMostWantedOthersListDetails();}
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
				         		 getMostWantedOthersListDetails();}
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
					getMostWantedOthersListDetails();
					}
				else if($(this).val()=="subscribername")
				     {
					getMostWantedOthersListDetails();
					}	
			  });
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
function getMostWantedStudentListDetails(){
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var subscriberId =$("#subscriberId").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	var select=$("input[name='subscriber_by']:checked").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getMostWantedStudentListDetails",
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
						+"<td>"+(i+1)+"</td>" 
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
function getMostWantedStudentListByClassName(){
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	var select=$("input[name='subscriber_by']:checked").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getMostWantedStudentListByClassName",
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
						+"<td>"+(i+1)+"</td>" 
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
		url : "LibraryMenu.html?method=getMostWantedStudentListBySection",
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
						+"<td>"+(i+1)+"</td>" 
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
function SearchMostWantedStudentDetailsByAnyWhere(){
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	var searchname = $("#searchValue").val();
	var select=$("input[name='subscriber_by']:checked").val();
	var startwith=$("input[name='started_by']:checked").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=SearchMostWantedStudentDetailsByAnyWhere",
		data : {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"searchname":searchname,
			"select":select,
			"startwith":startwith
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
						+"<td>"+(i+1)+"</td>" 
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

function getMostWantedStaffListDetails(){
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var department=$("#department").val();
	var designation=$("#designation").val();
	var select=$("input[name='subscriber_by']:checked").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getMostWantedStaffListDetails",
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
						+"<td>"+(i+1)+"</td>" 
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].staffName+"</td>"
						+"<td> "+result.studentData[i].staffDepartment+"</td>"
						+"<td> "+result.studentData[i].staffDesignation+"</td>"
						+"<td> "+result.studentData[i].status+"</td>"
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
function getMostWantedStaffdetailsByDepartment(){
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var department=$("#department").val();
	var designation=$("#designation").val();
	var select=$("input[name='subscriber_by']:checked").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getMostWantedStaffdetailsByDepartment",
		data : {
			"location" :locationid,
			"accyear" :accyear,
			"department" :department,
			"designation":designation,
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
						+"<td>"+(i+1)+"</td>" 
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
function getMostWantedStaffdetailsByDesignation(){
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var department=$("#department").val();
	var designation=$("#designation").val();
	var select=$("input[name='subscriber_by']:checked").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getMostWantedStaffdetailsByDesignation",
		data : {
			"location" :locationid,
			"accyear" :accyear,
			"department" :department,
			"designation" :designation,
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
						+"<td>"+(i+1)+"</td>" 
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
function SearchMostWantedStaffDetailsByAnyWhere(){
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var department=$("#department").val();
	var designation=$("#designation").val();
	var searchname = $("#searchValue").val();
	var select=$("input[name='subscriber_by']:checked").val();
	var startwith=$("input[name='started_by']:checked").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=SearchMostWantedStaffDetailsByAnyWhere",
		data : {
			"location" :locationid,
			"accyear" :accyear,
			"department" :department,
			"designation" :designation,
			"searchname":searchname,
			"select":select,
			"startwith":startwith
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
						+"<td>"+(i+1)+"</td>" 
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

   /*--------------------------------Others Details---------------------------*/
function getMostWantedOthersListDetails(){
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var select=$("input[name='subscriber_by']:checked").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getMostWantedOthersListDetails",
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
						+"<td class='"+result.studentData[i].studentId+" "+result.studentData[i].academicYearId+" "+result.studentData[i].locationId+" "+"studentid"+"'>"+(i+1)+"</td>" 
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
/*--------------------------------Others Details search Any Where---------------------------*/
function SearchOthersDetailsByAnyWhere(){
	var locationid = $("#locationname").val();
	var select=$("input[name='subscriber_by']:checked").val();
	var searchname = $("#searchValue").val();
	var startwith=$("input[name='started_by']:checked").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=SearchMostWantedOthersDetailsByAnyWhere",
		data : {
			"location" :locationid,
			"select":select,
			"searchname":searchname,
			"startwith":startwith
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
						+"<td class='"+result.studentData[i].studentId+" "+result.studentData[i].academicYearId+" "+result.studentData[i].locationId+" "+"studentid"+"'>"+(i+1)+"</td>" 
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
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=SearchOthersDetailsByStartWith",
		data : {
			"location" :locationid,
			"select":select,
			"searchname":searchname
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
						+"<td class='"+result.studentData[i].studentId+" "+result.studentData[i].academicYearId+" "+result.studentData[i].locationId+" "+"studentid"+"'>"+(i+1)+"</td>" 
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
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=SearchOthersDetailsByEndsWith",
		data : {
			"location" :locationid,
			"select":select,
			"searchname":searchname
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
						+"<td class='"+result.studentData[i].studentId+" "+result.studentData[i].academicYearId+" "+result.studentData[i].locationId+" "+"studentid"+"'><input type='radio' name='subid' value='"+result.studentData[i].subscriberId+"' class='"+result.studentData[i].location+"'/>"+(i+1)+"</td>" 
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
			getMostWantedOthersListDetails();
			}
		else if($(this).val()=="subscribername")
		     {
			getMostWantedOthersListDetails();
			}	
	});
}