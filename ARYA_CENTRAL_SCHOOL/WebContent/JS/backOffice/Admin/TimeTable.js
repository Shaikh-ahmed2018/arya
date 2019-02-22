$(document).ready(function() {
	var ClassId="ClassId";
	
	$("#SchoolID").change(function(){
		getClasses(ClassId);
		getTimeTableList("ALL");
	});
	getTeacherList();
	$("#classname").change(function(){

		var viewBy=$("#classname").val().trim();

		window.location.href = "adminMenu.html?method=gettimetable&viewBy="+viewBy;

	});

	var hviewBy=$("#hviewBy").val();
	if(hviewBy!=undefined && hviewBy!=''){

		$("#classname option[value=" +hviewBy+ "]").attr('selected', 'true');
	}


	$("#edit").click(function(){
		var count = 0;
		var TimeTableDetails = null;
		var locationId=$("#SchoolID").val();
		$('input[name="selectBox"]:checked').each(function() {
			count = count + 1;
			TimeTableDetails = this.value;
		});

		if (count > 1 || count == 0) {

			$('.errormessagediv').show();
			$('.validateTips').text("Select any one record");

		} else {
			window.location.href = "TimeTableActionPath.html?method=getTimeTableDetails&TimeTableDetails="
				+ TimeTableDetails+"&locationId="+locationId;


			/*window.location.href = "TimeTableActionPath.html?method=getTeacherTimeTableDetails&TimeTableDetails="
					+ TimeTableDetails;*/
		}
	});


	var viewBy=$("#hviewBy").val();


	if(viewBy=='class'){



		$.ajax({
			type : 'POST',
			url : 'teacherregistration.html?method=getSubjects',
			data:{"classId":$("#hiddenClassId").val()},
			async : false,
			success : function(response) {

				var data = $.parseJSON(response);

				for (var j = 0; j < data.SubjectList.length; j++) {

					$(".subject").append(
							'<option value="' + data.SubjectList[j].subjectid + '">'
							+ data.SubjectList[j].subjectname
							+ '</option>');
				}

			}

		});

	}else{

		$.ajax({
			type : 'POST',
			url : 'TimeTableActionPath.html?method=getClassSectionList',
			async : false,
			success : function(response) {

				var data = $.parseJSON(response);

				for (var j = 0; j < data.ClassSectionList.length; j++) {

					$(".subject")
					.append(
							'<option value="'
							+ data.ClassSectionList[j].classid
							+ '">'
							+ data.ClassSectionList[j].classname
							+ '</option>');



				}

			}

		});

	}


	$('#UpdateTimeTable').click(function() {
		var dayidArray = [];
		var period1Array = [];
		var period2Array = [];
		var period3Array = [];
		var period4Array = [];
		var period5Array = [];
		var period6Array = [];
		var period7Array = [];
		var period8Array = [];
		var period9Array = [];

		var tperiod1Array = [];
		var tperiod2Array = [];
		var tperiod3Array = [];
		var tperiod4Array = [];
		var tperiod5Array = [];
		var tperiod6Array = [];
		var tperiod7Array = [];
		var tperiod8Array = [];
		var tperiod9Array = [];

		$('table#allstudent tr:gt(0)').each(function() {

			var day = $(this).find('[name=dayid]').val().trim();
			var period11 = $(this).find('[name=period11]').val().trim();
			var period21 = $(this).find('[name=period21]').val().trim();
			var period31 = $(this).find('[name=period31]').val().trim();
			var period41 = $(this).find('[name=period41]').val().trim();
			var period51 = $(this).find('[name=period51]').val().trim();
			var period61 = $(this).find('[name=period61]').val().trim();
			var period71 = $(this).find('[name=period71]').val().trim();
			var period81 = $(this).find('[name=period81]').val().trim();
			var period91 = $(this).find('[name=period91]').val().trim();
			
			var tperiod11 = $(this).find('[name=tperiod11]').val().trim();
			var tperiod21 = $(this).find('[name=tperiod21]').val().trim();
			var tperiod31 = $(this).find('[name=tperiod31]').val().trim();
			var tperiod41 = $(this).find('[name=tperiod41]').val().trim();
			var tperiod51 = $(this).find('[name=tperiod51]').val().trim();
			var tperiod61 = $(this).find('[name=tperiod61]').val().trim();
			var tperiod71 = $(this).find('[name=tperiod71]').val().trim();
			var tperiod81 = $(this).find('[name=tperiod81]').val().trim();
			var tperiod91 = $(this).find('[name=tperiod91]').val().trim();
			
			
			
			
			
			
			var period12 = $(this).find('[name=period12]').val().trim();
			var period22 = $(this).find('[name=period22]').val().trim();
			var period32 = $(this).find('[name=period32]').val().trim();
			var period42 = $(this).find('[name=period42]').val().trim();
			var period52 = $(this).find('[name=period52]').val().trim();
			var period62 = $(this).find('[name=period62]').val().trim();
			var period72 = $(this).find('[name=period72]').val().trim();
			var period82 = $(this).find('[name=period82]').val().trim();
			var period92 = $(this).find('[name=period92]').val().trim();
			
			var tperiod12 = $(this).find('[name=tperiod12]').val().trim();
			var tperiod22 = $(this).find('[name=tperiod22]').val().trim();
			var tperiod32 = $(this).find('[name=tperiod32]').val().trim();
			var tperiod42 = $(this).find('[name=tperiod42]').val().trim();
			var tperiod52 = $(this).find('[name=tperiod52]').val().trim();
			var tperiod62 = $(this).find('[name=tperiod62]').val().trim();
			var tperiod72 = $(this).find('[name=tperiod72]').val().trim();
			var tperiod82 = $(this).find('[name=tperiod82]').val().trim();
			var tperiod92 = $(this).find('[name=tperiod92]').val().trim();
			
			
			
			var period13 = $(this).find('[name=period13]').val().trim();
			var period23 = $(this).find('[name=period23]').val().trim();
			var period33 = $(this).find('[name=period33]').val().trim();
			var period43 = $(this).find('[name=period43]').val().trim();
			var period53 = $(this).find('[name=period53]').val().trim();
			var period63 = $(this).find('[name=period63]').val().trim();
			var period73 = $(this).find('[name=period73]').val().trim();
			var period83 = $(this).find('[name=period83]').val().trim();
			var period93 = $(this).find('[name=period93]').val().trim();
			
			var tperiod13 = $(this).find('[name=tperiod13]').val().trim();
			var tperiod23 = $(this).find('[name=tperiod23]').val().trim();
			var tperiod33 = $(this).find('[name=tperiod33]').val().trim();
			var tperiod43 = $(this).find('[name=tperiod43]').val().trim();
			var tperiod53 = $(this).find('[name=tperiod53]').val().trim();
			var tperiod63 = $(this).find('[name=tperiod63]').val().trim();
			var tperiod73 = $(this).find('[name=tperiod73]').val().trim();
			var tperiod83 = $(this).find('[name=tperiod83]').val().trim();
			var tperiod93 = $(this).find('[name=tperiod93]').val().trim();
			
			
			
			var period14 = $(this).find('[name=period14]').val().trim();
			var period24 = $(this).find('[name=period24]').val().trim();
			var period34 = $(this).find('[name=period34]').val().trim();
			var period44 = $(this).find('[name=period44]').val().trim();
			var period54 = $(this).find('[name=period54]').val().trim();
			var period64 = $(this).find('[name=period64]').val().trim();
			var period74 = $(this).find('[name=period74]').val().trim();
			var period84 = $(this).find('[name=period84]').val().trim();
			var period94 = $(this).find('[name=period94]').val().trim();
			
			var tperiod14 = $(this).find('[name=tperiod14]').val().trim();
			var tperiod24 = $(this).find('[name=tperiod24]').val().trim();
			var tperiod34 = $(this).find('[name=tperiod34]').val().trim();
			var tperiod44 = $(this).find('[name=tperiod44]').val().trim();
			var tperiod54 = $(this).find('[name=tperiod54]').val().trim();
			var tperiod64 = $(this).find('[name=tperiod64]').val().trim();
			var tperiod74 = $(this).find('[name=tperiod74]').val().trim();
			var tperiod84 = $(this).find('[name=tperiod84]').val().trim();
			var tperiod94 = $(this).find('[name=tperiod94]').val().trim();
			
			
			
			
		
			

			if (day == '') {
				dayidArray.push('-');
			} else {
				dayidArray.push(day);
			}

			
			if (period11 == '') {
				period1Array.push('-');
			} else {
				period1Array.push(period11);
			}

			if (period21 == '') {
				period2Array.push('-');
			} else {
				period2Array.push(period21);
			}

			if (period31 == '') {
				period3Array.push('-');
			} else {
				period3Array.push(period31);
			}

			if (period41 == '') {
				period4Array.push('-');
			} else {
				period4Array.push(period41);
			}

			if (period51 == '') {
				period5Array.push('-');
			} else {
				period5Array.push(period51);
			}

			if (period61 == '') {
				period6Array.push('-');
			} else {
				period6Array.push(period61);
			}

			if (period71 == '') {
				period7Array.push('-');
			} else {
				period7Array.push(period71);
			}

			if (period81 == '') {
				period8Array.push('-');
			} else {
				period8Array.push(period81);
			}


			if (period91 == '') {
				period9Array.push('-');
			} else {
				period9Array.push(period91);
			}
			
			
			if (tperiod11 == '') {
				tperiod1Array.push('-');
			} else {
				tperiod1Array.push(tperiod11);
			}

			if (tperiod21 == '') {
				tperiod2Array.push('-');
			} else {
				tperiod2Array.push(tperiod21);
			}

			if (tperiod31 == '') {
				tperiod3Array.push('-');
			} else {
				tperiod3Array.push(tperiod31);
			}

			if (tperiod41 == '') {
				tperiod4Array.push('-');
			} else {
				tperiod4Array.push(tperiod41);
			}

			if (tperiod51 == '') {
				tperiod5Array.push('-');
			} else {
				tperiod5Array.push(tperiod51);
			}

			if (tperiod61 == '') {
				tperiod6Array.push('-');
			} else {
				tperiod6Array.push(tperiod61);
			}

			if (tperiod71 == '') {
				tperiod7Array.push('-');
			} else {
				tperiod7Array.push(tperiod71);
			}

			if (tperiod81 == '') {
				tperiod8Array.push('-');
			} else {
				tperiod8Array.push(tperiod81);
			}


			if (tperiod91 == '') {
				tperiod9Array.push('-');
			} else {
				tperiod9Array.push(tperiod91);
			}
			
			
			
			
			
			
			
			
			if (period12 == '') {
				period1Array.push('-');
			} else {
				period1Array.push(period12);
			}

			if (period22 == '') {
				period2Array.push('-');
			} else {
				period2Array.push(period22);
			}

			if (period32 == '') {
				period3Array.push('-');
			} else {
				period3Array.push(period32);
			}

			if (period42 == '') {
				period4Array.push('-');
			} else {
				period4Array.push(period42);
			}

			if (period52 == '') {
				period5Array.push('-');
			} else {
				period5Array.push(period52);
			}

			if (period62 == '') {
				period6Array.push('-');
			} else {
				period6Array.push(period62);
			}

			if (period72 == '') {
				period7Array.push('-');
			} else {
				period7Array.push(period72);
			}

			if (period82 == '') {
				period8Array.push('-');
			} else {
				period8Array.push(period82);
			}


			if (period92 == '') {
				period9Array.push('-');
			} else {
				period9Array.push(period92);
			}
			
			
			if (tperiod12 == '') {
				tperiod1Array.push('-');
			} else {
				tperiod1Array.push(tperiod12);
			}

			if (tperiod22 == '') {
				tperiod2Array.push('-');
			} else {
				tperiod2Array.push(tperiod22);
			}

			if (tperiod32 == '') {
				tperiod3Array.push('-');
			} else {
				tperiod3Array.push(tperiod32);
			}

			if (tperiod42 == '') {
				tperiod4Array.push('-');
			} else {
				tperiod4Array.push(tperiod42);
			}

			if (tperiod52 == '') {
				tperiod5Array.push('-');
			} else {
				tperiod5Array.push(tperiod52);
			}

			if (tperiod62 == '') {
				tperiod6Array.push('-');
			} else {
				tperiod6Array.push(tperiod62);
			}

			if (tperiod72 == '') {
				tperiod7Array.push('-');
			} else {
				tperiod7Array.push(tperiod72);
			}

			if (tperiod82 == '') {
				tperiod8Array.push('-');
			} else {
				tperiod8Array.push(tperiod82);
			}


			if (tperiod92 == '') {
				tperiod9Array.push('-');
			} else {
				tperiod9Array.push(tperiod92);
			}
			
			
			
			
			if (period13 == '') {
				period1Array.push('-');
			} else {
				period1Array.push(period13);
			}

			if (period23 == '') {
				period2Array.push('-');
			} else {
				period2Array.push(period23);
			}

			if (period33 == '') {
				period3Array.push('-');
			} else {
				period3Array.push(period33);
			}

			if (period43 == '') {
				period4Array.push('-');
			} else {
				period4Array.push(period43);
			}

			if (period53 == '') {
				period5Array.push('-');
			} else {
				period5Array.push(period53);
			}

			if (period63 == '') {
				period6Array.push('-');
			} else {
				period6Array.push(period63);
			}

			if (period73 == '') {
				period7Array.push('-');
			} else {
				period7Array.push(period73);
			}

			if (period83 == '') {
				period8Array.push('-');
			} else {
				period8Array.push(period83);
			}


			if (period93 == '') {
				period9Array.push('-');
			} else {
				period9Array.push(period93);
			}
			
		
			if (tperiod13 == '') {
				tperiod1Array.push('-');
			} else {
				tperiod1Array.push(tperiod13);
			}

			if (tperiod23 == '') {
				tperiod2Array.push('-');
			} else {
				tperiod2Array.push(tperiod23);
			}

			if (tperiod33 == '') {
				tperiod3Array.push('-');
			} else {
				tperiod3Array.push(tperiod33);
			}

			if (tperiod43 == '') {
				tperiod4Array.push('-');
			} else {
				tperiod4Array.push(tperiod43);
			}

			if (tperiod53 == '') {
				tperiod5Array.push('-');
			} else {
				tperiod5Array.push(tperiod53);
			}

			if (tperiod63 == '') {
				tperiod6Array.push('-');
			} else {
				tperiod6Array.push(tperiod63);
			}

			if (tperiod73 == '') {
				tperiod7Array.push('-');
			} else {
				tperiod7Array.push(tperiod73);
			}

			if (tperiod83 == '') {
				tperiod8Array.push('-');
			} else {
				tperiod8Array.push(tperiod83);
			}


			if (tperiod93 == '') {
				tperiod9Array.push('-');
			} else {
				tperiod9Array.push(tperiod93);
			}
			
			
			
			
			
			if (period14 == '') {
				period1Array.push('-');
			} else {
				period1Array.push(period14);
			}

			if (period24 == '') {
				period2Array.push('-');
			} else {
				period2Array.push(period24);
			}

			if (period34 == '') {
				period3Array.push('-');
			} else {
				period3Array.push(period34);
			}

			if (period44 == '') {
				period4Array.push('-');
			} else {
				period4Array.push(period44);
			}

			if (period54 == '') {
				period5Array.push('-');
			} else {
				period5Array.push(period54);
			}

			if (period64 == '') {
				period6Array.push('-');
			} else {
				period6Array.push(period64);
			}

			if (period74 == '') {
				period7Array.push('-');
			} else {
				period7Array.push(period74);
			}

			if (period84 == '') {
				period8Array.push('-');
			} else {
				period8Array.push(period84);
			}


			if (period94 == '') {
				period9Array.push('-');
			} else {
				period9Array.push(period94);
			}
			
			
			if (tperiod14 == '') {
				tperiod1Array.push('-');
			} else {
				tperiod1Array.push(tperiod14);
			}

			if (tperiod24 == '') {
				tperiod2Array.push('-');
			} else {
				tperiod2Array.push(tperiod24);
			}

			if (tperiod34 == '') {
				tperiod3Array.push('-');
			} else {
				tperiod3Array.push(tperiod34);
			}

			if (tperiod44 == '') {
				tperiod4Array.push('-');
			} else {
				tperiod4Array.push(tperiod44);
			}

			if (tperiod54 == '') {
				tperiod5Array.push('-');
			} else {
				tperiod5Array.push(tperiod54);
			}

			if (tperiod64 == '') {
				tperiod6Array.push('-');
			} else {
				tperiod6Array.push(tperiod64);
			}

			if (tperiod74 == '') {
				tperiod7Array.push('-');
			} else {
				tperiod7Array.push(tperiod74);
			}

			if (tperiod84 == '') {
				tperiod8Array.push('-');
			} else {
				tperiod8Array.push(tperiod84);
			}


			if (tperiod94 == '') {
				tperiod9Array.push('-');
			} else {
				tperiod9Array.push(tperiod94);
			}
			
			period1Array.push(':');
			period2Array.push(':');
			period3Array.push(':');
			period4Array.push(':');
			period5Array.push(':');
			period6Array.push(':');
			period7Array.push(':');
			period8Array.push(':');
			period9Array.push(':');
			
			tperiod1Array.push(':');
			tperiod2Array.push(':');
			tperiod3Array.push(':');
			tperiod4Array.push(':');
			tperiod5Array.push(':');
			tperiod6Array.push(':');
			tperiod7Array.push(':');
			tperiod8Array.push(':');
			tperiod9Array.push(':');
			
			
		});
	
		var ClassId = $("#hiddenClass").val();
		var SectionId= $("#hiddenSection").val();
		var timetableID=$("#hiddenId").val();

		var className=$("#classid").val().trim();
		
		var jsonObject = {
				'period1Array' : period1Array.toString(),
				'period2Array' : period2Array.toString(),
				'period3Array' : period3Array.toString(),
				'period4Array' : period4Array.toString(),
				'period5Array' : period5Array.toString(),
				'period6Array' : period6Array.toString(),
				'period7Array' : period7Array.toString(),
				'period8Array' : period8Array.toString(),
				'period9Array' : period9Array.toString(),
				
				'tperiod1Array' : tperiod1Array.toString(),
				'tperiod2Array' : tperiod2Array.toString(),
				'tperiod3Array' : tperiod3Array.toString(),
				'tperiod4Array' : tperiod4Array.toString(),
				'tperiod5Array' : tperiod5Array.toString(),
				'tperiod6Array' : tperiod6Array.toString(),
				'tperiod7Array' : tperiod7Array.toString(),
				'tperiod8Array' : tperiod8Array.toString(),
				'tperiod9Array' : tperiod9Array.toString(),
				
				'timetableID' : timetableID,
				'ClassId' : ClassId,
				'SectionId' : SectionId,
				'viewBy' : viewBy,
				'dayidArray' : dayidArray.join(","),
				'locationId':$("#locationId").val()
		};
	showPopup(jsonObject);
		
	});
	
	
	$("#excelDownload").click(function(){
			var viewBy=$("#hviewBy").val().trim();
			window.location.href = "TimeTableActionPath.html?method=classTimeTableDownloadXLS&viewBy="+viewBy;
		
			
	});

	
	
	
	$("#pdfDownload").click(function()
			{
		var viewBy=$("#hviewBy").val().trim();
		window.location.href = "TimeTableActionPath.html?method=classTimeTableDownloadPDF&viewBy="+viewBy;
	});

	/*$("#pdfDownload").click(function(){
		var viewBy=$("#classname").val().trim();

		if(viewBy=="class")
		{
			var viewBy=$("#classname").val().trim();
			window.location.href = "TimeTableActionPath.html?method=classTimeTableDownloadPDF&viewBy="+viewBy;
		}
		else
		{
			var viewBy=$("#classname").val().trim();
			window.location.href = "TimeTableActionPath.html?method=teacherTimeTableDownloadPDF&viewBy="+viewBy;
		}
	});*/
	
	
/*	$("#pdfDownload").click(function()
			{
			var searchTerm = $("#searchexamid").val().trim();
			window.location.href = "careerview.html?method=InternaljobPDFReport&searchTerm="+ searchTerm;
			});
	*/
	

	
	
	
	$('#ClassId').change(function() {
		getClassSection($('#ClassId').val());
		getTimeTableList($('#ClassId').val());
	});
	
	$('#sectionid').change(function() {
		if($('#sectionid').val() == "All"){
			getTimeTableList($('#ClassId').val());
		}else{
			getTimeTableListBySection($('#ClassId').val(),$('#sectionid').val());
		}
		
	});
});

function showPopup(jsonObject){
	$("#dialog").dialog({
		 autoOpen: true,
	     modal: true,
	     position: {my: "center middle",
            at: "center middle",
           },
	     title:'Save Time Table Details',
	    
	     buttons : {
	    	 "Yes" : function() {
	    		 $.ajax({
	    			 method : 'POST',
	    			 url : 'TimeTableActionPath.html?method=updateTimeTableDetails',
	    			 data : jsonObject,
	    			 async : false,
	    			 success : function(data) {
	    				 var result = $.parseJSON(data);

	    				 if (result.timetable_Result == "success") {

	    					 /*$('.successmessagediv').show();*/
	    					 $(".successmessagediv").attr("style","width:150%;margin-left:-250px;");
	    					 $('.successmessage').text("Update Time table Details Progressing...");

	    					 setInterval(function() {

	    						 window.location.href = "adminMenu.html?method=gettimetable";

	    					 }, 3000);
	    				 } else {
	    					 $('.successmessagediv').show();
	    					 $('.successmessage').text("TimeTable Details not Saved, Please try again");
	    				 }	
	    			 }
	    		 });
	    		 $(this).dialog("close");
	    	 },
	    	 "No" : function() {
	    		 $(this).dialog("close");
	    	 }
	     }
	});
}

function getClasses(classId) {
	
	var ClassId = "#" + classId;
	var location =$("#SchoolID").val();
	
	$.ajax({
		type : 'GET',
		url : "studentRegistration.html?method=getClassDetailWithOutStream",
		data:{"location":location},
		
		success : function(response) {
			$(ClassId).html("");
			$(ClassId).append('<option value="ALL">' + "ALL" + '</option>');
			var result = $.parseJSON(response);

			for ( var j = 0; j < result.ClassList.length; j++) {

				$(ClassId).append(
						'<option value="' + result.ClassList[j].classcode
						+ '">' + result.ClassList[j].classname
						+ '</option>');
			}
		}
	});
}

function getTimeTableList(classId) {
	datalist = {
		"classId" : classId,
		"locationId":$("#SchoolID").val(),
	}, $.ajax({
		type : 'POST',
		url : "adminMenu.html?method=gettimetablelist",
		data : datalist,
		async : false,
		
		success : function(response) {
			 
			var result = $.parseJSON(response);
				$("#allstudent tbody").empty();
				
				var len=result.ClassTimeTableList.length;
				if(len>0){
				for(var i=0;i<result.ClassTimeTableList.length;i++){
				$("#allstudent tbody").append("<tr><td><input type='checkbox' name='selectBox' id='selectBox'  value='"+result.ClassTimeTableList[i].timetableId+","+result.ClassTimeTableList[i].classid+","+result.ClassTimeTableList[i].sectionid+"'></td>"
						+"<td>"+result.ClassTimeTableList[i].classname+"</td>" 
						+"<td> "+result.ClassTimeTableList[i].sectionname+" </td>"
						+"<td> "+result.ClassTimeTableList[i].teachername+"</td>"
						+"<td> "+result.ClassTimeTableList[i].timetableStatus+" </td>"
						+"<td> "+result.ClassTimeTableList[i].createdby+" </td>"
						+"<td> "+result.ClassTimeTableList[i].createddate+" </td>"
						+"<td> "+result.ClassTimeTableList[i].lastupdatedby+" </td>"
						+"<td> "+result.ClassTimeTableList[i].lastupdated+"</td>"
						+"</tr>");
				  }	
				}
				else{
					$("#allstudent tbody").append("<tr><td ColSpan='9'>No Records Found</td></tr>");
				}
				pagination(100);
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+len);
		}
	});

}

function getClassSection(sectionid) {
	datalist = {
		"classidVal" : sectionid
	}, $.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		
		success : function(response) {
			 
			var result = $.parseJSON(response);
			$("#sectionid").html("");
			$("#sectionid").append('<option value= "All">' + "--Select--" + '</option>');
			for ( var j = 0; j < result.sectionList.length; j++) {
				$("#sectionid").append(
						'<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}

		}
	});

}
function getTimeTableListBySection(classId,sectionId){
	datalist = {
			"classId" : classId,
			"sectionId": sectionId,
		}, $.ajax({
			type : 'POST',
			url : "adminMenu.html?method=gettimetablelistbysection",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
			
					$("#allstudent tbody").empty();
					var len=result.ClassTimeTableList.length;
					if(len>0){
					
					for(var i=0;i<len;i++){
					$("#allstudent tbody").append("<tr><td><input type='checkbox' name='selectBox' id='selectBox'  value='"+result.ClassTimeTableList[i].timetableId+","+result.ClassTimeTableList[i].classid+","+result.ClassTimeTableList[i].sectionid+"'></td>"
							+"<td>"+result.ClassTimeTableList[i].classname+"</td>" 
							+"<td> "+result.ClassTimeTableList[i].sectionname+" </td>"
							+"<td> "+result.ClassTimeTableList[i].teachername+"</td>"
							+"<td> "+result.ClassTimeTableList[i].timetableStatus+" </td>"
							+"<td> "+result.ClassTimeTableList[i].createdby+" </td>"
							+"<td> "+result.ClassTimeTableList[i].createddate+" </td>"
							+"<td> "+result.ClassTimeTableList[i].lastupdatedby+" </td>"
							+"<td> "+result.ClassTimeTableList[i].lastupdated+"</td>"
							+"</tr>");
					  }	
					}else{
						$("#allstudent tbody").append("<tr><td ColSpan='9'>No Records Found</td></tr>");
					}
					pagination(100);
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+len);
			}
		});

}

function getTeacherList(){
	 $.ajax({
			type : 'POST',
			url : "adminMenu.html?method=getTeacherList",
		
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
			
					$("select.teacher").empty();
					$("select.teacher").append("<option value=''>-----Select----</option>");
					var len=result.teacherList.length;
					if(len>0){
					
					for(var i=0;i<len;i++){
					$("select.teacher").append("<option value='"+result.teacherList[i].teacherId+"'>"+result.teacherList[i].teacherName+"</option>");
					  }	
					}
					$("select.teacher").each(function(){
						$(this).val($(this).next(".tperiod").val());
					});	
			}
		});

}



