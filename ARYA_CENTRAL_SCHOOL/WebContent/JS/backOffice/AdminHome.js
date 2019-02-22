$(document).ready(function(){
	 setcount=0;
	var monthName=["January","February","March","April","May","June","July","August","September","October","November","December"];
	var currentdate=new Date();
	var currMonthDay=currentdate.getDate();
	var currMonthVal=currentdate.getMonth() + 1;
	var date = new Date();
	date.setDate(date.getDate()+Number($("#startDate").val())+1);
	
	var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
	var currentDay = new Date(currentdate.getFullYear(), currentdate.getMonth() + 1, 0);
	setcount=currentDay.getMonth()-lastDay.getMonth();
	
	$(".year").text(currentDay.getFullYear());
	$(".monthName").text(monthName[currentDay.getMonth()]);
	var monthCount=currentDay.getDate();
	
	if(currMonthDay<10){
		currMonthDay="0"+currMonthDay;
	}
	if(currMonthVal<10){
		currMonthVal="0"+currMonthVal;
	}

	 var monthvalue=currentDay.getMonth()+1;
	 if(monthvalue<10){
		 monthvalue="0"+monthvalue;
	 }
	$("#diaryTable tbody").empty();
	for(var i=1;i<=Number(monthCount);i++){
		var dayscount=i;
		if(dayscount<10){
			dayscount="0"+dayscount;
		}
		$("#diaryTable tbody").append("<tr id='rowid"+dayscount+monthvalue+currentDay.getFullYear()+"' class='"+dayscount+"-"+monthvalue+"-"+currentDay.getFullYear()+"'>" +
				"<td>"+i+"</td>" +
				"<td id='"+dayscount+"-"+monthvalue+"-"+currentDay.getFullYear()+"'><input type='text' class='dairyInput' /></td>" +
				"</tr>" +
				"");
		
	}
	$(".dairyInput").each(function(){
		if($(".dairyInput").val().trim()==''){
			$(this).css({
				'border-bottom':'none'
			});
		}
	});
	getExam();
	getComments();
	saveComment();
	
	
	
	$("#next").click(function(){
		
		if(setcount<11){
			setcount++;
			currentdate.setMonth(currentdate.getMonth() +1);
			currentDay = new Date(currentdate.getFullYear(), currentdate.getMonth() + 1, 0);
		 $(".year").text(currentDay.getFullYear());
			$(".monthName").text(monthName[currentDay.getMonth()]);
			var monthCount=currentDay.getDate();
			var monthvalue=currentDay.getMonth()+1;
			 if(monthvalue<10){
				 monthvalue="0"+monthvalue;
			 }
			$("#diaryTable tbody").empty();
			for(var i=1;i<=Number(monthCount);i++){
				var dayscount=i;
				if(dayscount<10){
					dayscount="0"+dayscount;
				}
				$("#diaryTable tbody").append("<tr id='rowid"+dayscount+monthvalue+currentDay.getFullYear()+"' class='"+dayscount+"-"+monthvalue+"-"+currentDay.getFullYear()+"'>" +
						"<td>"+i+"</td>" +
						"<td id='"+dayscount+"-"+monthvalue+"-"+currentDay.getFullYear()+"'><input type='text' class='dairyInput' /></td>" +
						"</tr>" +
						"");
			}
			$(".dairyInput").each(function(){
				if($(".dairyInput").val().trim()==''){
					$(this).css({
						'border-bottom':'none'
					});
				}
			});
			getExam();
			getComments();
			saveComment();
			
		}
		else{
			
		}
		$("#rowid"+currMonthDay+currMonthVal+currentdate.getFullYear()).css({
			"background-color":"#cceefc",
			"position":"relative"
		});
		$("#rowid"+currMonthDay+currMonthVal+currentdate.getFullYear() +" td:nth-child(1)").append("<span class='todayHilight'>Today</span>");	
	});
	
	
		$("#prev").click(function(){
			
			if(setcount>0){
				setcount--;
				currentdate.setMonth(currentdate.getMonth() -1);
				currentDay = new Date(currentdate.getFullYear(), currentdate.getMonth() + 1, 0);
			 $(".year").text(currentDay.getFullYear());
			
				$(".monthName").text(monthName[currentDay.getMonth()]);
				var monthCount=currentDay.getDate();
				var monthvalue=currentDay.getMonth()+1;
				 if(monthvalue<10){
					 monthvalue="0"+monthvalue;
				 }
				$("#diaryTable tbody").empty();
				for(var i=1;i<=Number(monthCount);i++){
					var dayscount=i;
					if(dayscount<10){
						dayscount="0"+dayscount;
					}	
					$("#diaryTable tbody").append("<tr id='rowid"+dayscount+monthvalue+lastDay.getFullYear()+"' class='"+dayscount+"-"+monthvalue+"-"+currentDay.getFullYear()+"'>" +
							"<td>"+i+"</td>" +
							"<td id='"+dayscount+"-"+monthvalue+"-"+currentDay.getFullYear()+"'><input type='text' class='dairyInput' /></td>" +
							"</tr>" +
							"");
				}
				$(".dairyInput").each(function(){
					if($(".dairyInput").val().trim()==''){
						$(this).css({
							'border-bottom':'none'
						});
					}
				});
				getExam();
				getComments();
				saveComment();
				
			}
			else{
				
			}
			$("#rowid"+currMonthDay+currMonthVal+currentdate.getFullYear()).css({
				"background-color":"#cceefc",
				"position":"relative"
			});
			$("#rowid"+currMonthDay+currMonthVal+currentdate.getFullYear() +" td:nth-child(1)").append("<span class='todayHilight'>Today</span>");
		});
		
	
		$('#calendar').datepicker({
			minDate:Number($("#startDate").val())+1,
			maxDate:Number($("#endDate").val())-1,
			onSelect:function(){


				var dates = $(this).datepicker('getDate');
				var hilightDay=dates.getDate();
				if(hilightDay<10){
					hilightDay="0"+hilightDay;
				}
				var hilightMonth=dates.getMonth()+1;
				if(hilightMonth<10){
					hilightMonth="0"+hilightMonth;
				}
				
				
				currentdate.setMonth(dates.getMonth());
				currentDay = new Date(currentdate.getFullYear(), currentdate.getMonth() + 1, 0);
				$(".year").text(lastDay.getFullYear());
				setcount=currentDay.getMonth()-lastDay.getMonth();
				$(".monthName").text(monthName[currentDay.getMonth()]);
				var monthCount=currentDay.getDate();
				var monthvalue=currentDay.getMonth()+1;
				if(monthvalue<10){
					monthvalue="0"+monthvalue;
				}
				$("#diaryTable tbody").empty();
				for(var i=1;i<=Number(monthCount);i++){
					var dayscount=i;
					if(dayscount<10){
						dayscount="0"+dayscount;
					}	
					$("#diaryTable tbody").append("<tr id='rowid"+dayscount+monthvalue+currentDay.getFullYear()+"' class='"+dayscount+"-"+monthvalue+"-"+currentDay.getFullYear()+"'>" +
							"<td>"+i+"</td>" +
							"<td id='"+dayscount+"-"+monthvalue+"-"+currentDay.getFullYear()+"'><input type='text' class='dairyInput' /></td>" +
							"</tr>" +
					"");
				}
				$(".dairyInput").each(function(){
					if($(".dairyInput").val().trim()==''){
						$(this).css({
							'border-bottom':'none'
						});
					}
				});
				getExam();
				getComments();
				saveComment();
			
				$( "body" ).scrollTop( $("#rowid"+hilightDay+hilightMonth+dates.getFullYear()).position().top);
				$("#rowid"+hilightDay+hilightMonth+dates.getFullYear()).css({
					"background-color":"#9CDDE3"
				});
				$("#rowid"+currMonthDay+currMonthVal+currentdate.getFullYear()).css({
					"background-color":"#cceefc",
					"position":"relative"
				});
				$("#rowid"+currMonthDay+currMonthVal+currentdate.getFullYear() +" td:nth-child(1)").append("<span class='todayHilight'>Today</span>");
			},
		});
		$( "body" ).scrollTop( $("#rowid"+currMonthDay+currMonthVal+currentdate.getFullYear()).position().top);
		$("#rowid"+currMonthDay+currMonthVal+currentdate.getFullYear()).css({
			"background-color":"#cceefc",
			"position":"relative"
		});
		$("#rowid"+currMonthDay+currMonthVal+currentdate.getFullYear() +" td:nth-child(1)").append("<span class='todayHilight'>Today</span>");
});
function saveComment(){
	$(".dairyInput").change(function(){
		var content=$(this).val();
		var rowid=$(this).closest("tr").attr("id");
		var commentdate=$(this).closest("tr").attr("class");
		var dataList={
				"content":content,
				"rowid":rowid,
				"commentdate":commentdate,
		};
		$.ajax({
			type:'POST',
			url:'login.html?method=Dairy',
			data:dataList,
			async:false,
		});
	});		
}
function getExam(){
	$.ajax({
		type:'POST',
		url:'examTimetablePath.html?method=ExamDetails',
		async:false,
		
		success:function(response){
			var result=$.parseJSON(response);
			for(var i=0;i<result.examsettings.length;i++){
			$("td#"+result.examsettings[i].startDate).append("<div class='exam'> "+result.examsettings[i].examCode+" Exam Start</div>");
			$("td#"+result.examsettings[i].endDate).append("<div class='exam'> "+result.examsettings[i].examCode+" Exam End</div>");
			
			$("td#"+result.examsettings[i].startDate).closest("tr").find(".dairyInput").css({
				'border-bottom':'1px solid rgba(204, 204, 204, 0.30)'
			});
			$("td#"+result.examsettings[i].endDate).closest("tr").find(".dairyInput").css({
				'border-bottom':'1px solid rgba(204, 204, 204, 0.30)'
			});
			}
		}
	});
}
function getComments(){
	$.ajax({
		type:'POST',
		url:'adminMenu.html?method=getDairy',
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			for(var i=0;i<result.commentlist.length;i++){
			$("tr#"+result.commentlist[i].rowId).find(".dairyInput").val(result.commentlist[i].content);
			
			}
			
				
				
		}
	});
}
