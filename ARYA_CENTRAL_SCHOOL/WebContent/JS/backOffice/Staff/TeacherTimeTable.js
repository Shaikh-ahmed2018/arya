$(document)
		.ready(
				function() {
					
					$("#print").click(function(){

				      	  var a=$("#printing-css").val();
				      	  
				      	   var b= document.getElementById("scrolid").innerHTML;
				      	   var abd='<style>' + a +'</style>' + b;
				      	    var frame1 = $('<iframe />');
				  	        frame1[0].name = "frame1";
				  	        frame1.css({ "position": "absolute", "top": "-1000000px" });
				  	        $("body").append(frame1);
				  	        var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
				  	        frameDoc.document.open();
				  	        //Create a new HTML document.
				  	        frameDoc.document.write('<html><head><title>DIV Contents</title>');
				  	        //Append the external CSS file.
				  	        frameDoc.document.write('<link href="CSS/newUI/modern-business.css" rel="stylesheet">');
				  	        frameDoc.document.write('<link href="CSS/newUI/custome.css" rel="stylesheet">');
				  	        frameDoc.document.write('<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">');
				  	        frameDoc.document.write('<link href="CSS/newUI/custome.css" rel="stylesheet">');
				  	        frameDoc.document.write('<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />');
				  	        frameDoc.document.write('</head><body>');
				  	      
				  	        
				  	        //Append the DIV contents.
				  	        frameDoc.document.write(abd);
				  	        frameDoc.document.write('</body></html>');
				  	        frameDoc.document.close();
				  	        setTimeout(function () {
				  	            window.frames["frame1"].focus();
				  	            window.frames["frame1"].print();
				  	            frame1.remove();
				  	        }, 100);
							
						});
					var userId=window.location.href.substring(window.location.href.lastIndexOf("=")+1);
					
					$.ajax({
						type:"POST",
						url:"teachermenuaction.html?method=viewTeacherTimeTableBYJs",
						data:{"userId":userId},
						async:false,
						success:function(response){
							var result=$.parseJSON(response);
							$("#allstudent tbody").empty();
							$("#allstudent tbody").append("<tr id='DAY1'><td>Monday</td></tr>");
							$("#allstudent tbody").append("<tr id='DAY2'><td>Tuesday</td></tr>");
							$("#allstudent tbody").append("<tr id='DAY3'><td>Wednesday</td></tr>");
							$("#allstudent tbody").append("<tr id='DAY4'><td>Thursday</td></tr>");
							$("#allstudent tbody").append("<tr id='DAY5'><td>Friday</td></tr>");
							$("#allstudent tbody").append("<tr id='DAY6'><td>Saturday</td></tr>");
							for(var i=1;i<10;i++){
								$("#allstudent tbody tr").append("<td class='period"+i+"'></td>");
							}
							for(var i=0;i<result.timeTableDetails.length;i++){
								$("#allstudent tr#"+result.timeTableDetails[i].dayid+" td.period1").text(result.timeTableDetails[i].period1);
								$("#allstudent tr#"+result.timeTableDetails[i].dayid+" td.period2").text(result.timeTableDetails[i].period2);
								$("#allstudent tr#"+result.timeTableDetails[i].dayid+" td.period3").text(result.timeTableDetails[i].period3);
								$("#allstudent tr#"+result.timeTableDetails[i].dayid+" td.period4").text(result.timeTableDetails[i].period4);
								$("#allstudent tr#"+result.timeTableDetails[i].dayid+" td.period5").text(result.timeTableDetails[i].period5);
								$("#allstudent tr#"+result.timeTableDetails[i].dayid+" td.period6").text(result.timeTableDetails[i].period6);
								$("#allstudent tr#"+result.timeTableDetails[i].dayid+" td.period7").text(result.timeTableDetails[i].period7);
								$("#allstudent tr#"+result.timeTableDetails[i].dayid+" td.period8").text(result.timeTableDetails[i].period8);
								$("#allstudent tr#"+result.timeTableDetails[i].dayid+" td.period9").text(result.timeTableDetails[i].period9);
							}
							
							
						}
					});
					$.ajax({
						type:"POST",
						url:"teachermenuaction.html?method=viewTodayTeacherTimeTableBYJs",
						data:{"userId":userId},
						async:false,
						success:function(response){
							var result=$.parseJSON(response);
							$("#todayTimeTable tbody").empty();
							$("#todayTimeTable tbody").append("<tr id='"+result.todaytimeTableDetails[0].dayid+"'></tr>");
							$("#todayTimeTable tbody tr").append("<td class='dateColumn'>"+result.todaytimeTableDetails[0].dayid+"</td>");
							$("#todayTimeTable tbody tr").append("<td id='dayname'>"+result.todaytimeTableDetails[0].dayname+"</td>");
							for(var i=1;i<10;i++){
								$("#todayTimeTable tbody tr").append("<td class='period"+i+"'></td>");
							}
							for(var i=0;i<result.todaytimeTableDetails.length;i++){
								$("#todayTimeTable tr#"+result.todaytimeTableDetails[i].dayid+" td.period1").text(result.todaytimeTableDetails[i].period1);
								$("#todayTimeTable tr#"+result.todaytimeTableDetails[i].dayid+" td.period2").text(result.todaytimeTableDetails[i].period2);
								$("#todayTimeTable tr#"+result.todaytimeTableDetails[i].dayid+" td.period3").text(result.todaytimeTableDetails[i].period3);
								$("#todayTimeTable tr#"+result.todaytimeTableDetails[i].dayid+" td.period4").text(result.todaytimeTableDetails[i].period4);
								$("#todayTimeTable tr#"+result.todaytimeTableDetails[i].dayid+" td.period5").text(result.todaytimeTableDetails[i].period5);
								$("#todayTimeTable tr#"+result.todaytimeTableDetails[i].dayid+" td.period6").text(result.todaytimeTableDetails[i].period6);
								$("#todayTimeTable tr#"+result.todaytimeTableDetails[i].dayid+" td.period7").text(result.todaytimeTableDetails[i].period7);
								$("#todayTimeTable tr#"+result.todaytimeTableDetails[i].dayid+" td.period8").text(result.todaytimeTableDetails[i].period8);
								$("#todayTimeTable tr#"+result.todaytimeTableDetails[i].dayid+" td.period9").text(result.todaytimeTableDetails[i].period9);
							}
							
							
						}
					});
					
					
				});


function MergeCommonRows(table) {
    var firstColumnBrakes = [];
    // iterate through the columns instead of passing each column as function parameter:
    for(var i=1; i<=table.find('th').length; i++){
        var previous = null, cellToExtend = null, rowspan = 1;
        table.find("td:nth-child(" + i + ")").each(function(index, e){
            var jthis = $(this), content = jthis.text();
            // check if current row "break" exist in the array. If not, then extend rowspan:
            if (previous == content && content !== "" && $.inArray(index, firstColumnBrakes) === -1) {
                // hide the row instead of remove(), so the DOM index won't "move" inside loop.
                jthis.addClass('hidden');
                cellToExtend.attr("rowspan", (rowspan = rowspan+1));
            }else{
                // store row breaks only for the first column:
                if(i === 1) firstColumnBrakes.push(index);
                rowspan = 1;
                previous = content;
                cellToExtend = jthis;
            }
        });
    }
    // now remove hidden td's (or leave them hidden if you wish):
    $('td.hidden').remove();
}
