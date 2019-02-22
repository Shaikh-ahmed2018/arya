$(document).ready(function(){
	
	var accYear=$("#accYear").val();
	var locationId = $("#location").val();
	var classId = $("#classId").val();
	var SectionId = $("#SectionId").val();
	var ExamCode = $("#ExamCode").val();
	var studentId=$("#studentId").val();
	$.ajax({
		type : 'POST',
		url : "reportaction.html?method=getChartDetail",
		data : {
			"accYear":accYear,
			"locationId" :locationId,
			"classId" :classId,	
			"SectionId":SectionId,
			"ExamCode":ExamCode,
			"studentId":studentId
		},
		async : false,
		
		success : function(response) {
			var result = $.parseJSON(response);
		             getChart(result);
		            return false;
		}
	});


	
	
});


function getChart(result){
	for(var i=0; i<result.detail.length; i++){
		var options = {
				title: {
					text: "Class Performance",
				//	color:"white"
				},
			            animationEnabled: true,
                       axisY: {
            	            title: "Students--->",
            	        	
			            },
			            axisX:{
			            	title: "Student Percentage---->",
			            		stripLines: [{
			            			 color:"black",
			            	         value: result.detail[i].mainCount,
			            	         showOnTop: true
			            	       }]
			            	
			            },
			            legend:{
			            	//dockInsidePlotArea: true,
			                horizontalAlign: "right", 
			                verticalAlign: "center",
			                fontSize: 20,
			                fontFamily: "tamoha",
			                fontColor: "black"      
			              },
				data: [
				{
					 color:"#07AAB9",
					 showInLegend: "true",
					 legendMarkerColor: "black",
					 legendText: result.detail[i].mainName,
					 type: "column",
					 dataPoints: [
						{ x: 10, y: result.detail[i].count },
						{ x: 20, y: result.detail[i].count1 },
						{ x: 30, y: result.detail[i].count2 },
						{ x: 40, y: result.detail[i].count3 },
						{ x: 50, y: result.detail[i].count4 },
						{ x: 60, y: result.detail[i].count5},
						{ x: 70, y: result.detail[i].count6 },
						{ x: 80, y: result.detail[i].count7 },
						{ x: 90, y: result.detail[i].count8 },
						{ x: 100, y: result.detail[i].count9 },
					]
				
				},{
					color:"#07AAB9",
					 showInLegend: "true",
					 legendMarkerColor: "black",
					 legendText:result.detail[i].rank,
					 type: "column",
					 dataPoints: [
									{ x: 10, y: result.detail[i].count },
									{ x: 20, y: result.detail[i].count1 },
									{ x: 30, y: result.detail[i].count2 },
									{ x: 40, y: result.detail[i].count3 },
									{ x: 50, y: result.detail[i].count4 },
									{ x: 60, y: result.detail[i].count5},
									{ x: 70, y: result.detail[i].count6 },
									{ x: 80, y: result.detail[i].count7 },
									{ x: 90, y: result.detail[i].count8 },
									{ x: 100, y: result.detail[i].count9 },
								]
				}
				]
			};
	}
			$("#chartContainer").CanvasJSChart(options);
		}
	
