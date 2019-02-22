$(document).ready(function(){
	
	$("input,select").on({
		keypress: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	change: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	});
	
	
	
    
	$("#eventname").change(function(){
		categoryDropDown=getCategoryDropDown();
		allreport=getOverAllReport();
		cathouse=getOverAllReportMarksHouseAndCategory();
		housmark=getHouseForParticipantList();
		getEventStudentOverallResult();
		
	});
	
	$("#print").click(function(){
		
		 var b= document.getElementById("printable").innerHTML;
	  	   var abd= b;
	  	    var frame1 = $('<iframe />');
		        frame1[0].name = "frame1";
		        frame1.css({ "position": "absolute", "top": "-1000000px" });
		        $("body").append(frame1);
		        var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
		        frameDoc.document.open();
		        //Create a new HTML document.
		        frameDoc.document.write('<html><head><title>DIV Contents</title>');
		        //Append the external CSS file.
		        frameDoc.document.write('<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>');
		        frameDoc.document.write('<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">');
		        frameDoc.document.write('<link href="CSS/newUI/modern-business.css" rel="stylesheet">');
		        frameDoc.document.write('<style>.rotate {height: 300px;white-space: nowrap;}.rotate div{transform: translate(0px, -124px) rotate(90deg);width: 30px;text-align: center;}#second tbody td,#second thead th{border: 1px solid #000;}</style>');
		        
		        frameDoc.document.write('<link href="CSS/newUI/custome.css" rel="stylesheet">');
		        frameDoc.document.write('<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>');
		        frameDoc.document.write('<script type="text/javascript" src="JS/backOffice/Events/OverallResult.js"></script>');
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
	$("#excel").click(function(){
		fnExcelReport();
	});
	
});

function getEventName(){
	data={"flag":"Indiv"};
	$.ajax({
		type:'post',
		data:{"flag":"Indiv"},
		url:"EventsMenu.html?method=getEventNameList_Group",
		async:false,
		success:function(data){
			var result=$.parseJSON(data);
				$("#eventName").empty();
				$("#eventName").append("<option value=''>------------Select----------</option>");
				for(var i=0;i<result.data.length;i++){
				$("#eventNameListIndiv").append("<option value='"+result.data[i].eventId+"'>" 
						+result.data[i].eventName+ "</option>");
				}
		}
	});
}

function getCategoryDropDown(){
	
		var id = $("#eventname").val();
		
		$.ajax({
			type:'post',
			url:"EventsMenu.html?method=getCategoryName",
			data :{"id":id,
					"flag":"onLoad",
					},
			async:false,
			success:function(data){
				 category=$.parseJSON(data);
				
			}
		});
		return category.data;
	}
function getHouseForParticipantList(){
	var houseName=[];
	var evId = $("#eventname").val();
	datalist ={
			"eventID":evId,
	};
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getHouseForParticipantList",
		async:false,
		data:datalist,
		success:function(data){
			var result = $.parseJSON(data);
			
			for(var i=0;i<result.data.length;i++){
				houseName.push(result.data[i].houseName);
			}
		}
	});
	return houseName;
}

function getOverAllReport(){
	
	var evId = $("#eventname").val();
	datalist ={
			"eventID":evId,
	};
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getOverAllReport",
		async:false,
		data:datalist,
		success:function(data){
			  map = $.parseJSON(data);
			
			
			
			 
			
		}
	});
	
	return map.data;
}
function getOverAllReportMarksHouseAndCategory(){
	
	var evId = $("#eventname").val();
	datalist ={
			"eventID":evId,
	};
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getOverAllReportMarksHouseAndCategory",
		async:false,
		data:datalist,
		success:function(data){
			  housecatMarks = $.parseJSON(data);
			
			
			
			 
			
		}
	});
	
	return housecatMarks.data;
}
function getOverAllReportMarksHouse(){
	
	var evId = $("#eventname").val();
	datalist ={
			"eventID":evId,
	};
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getOverAllReportMarksHouse",
		async:false,
		data:datalist,
		success:function(data){
			  houseMarks = $.parseJSON(data);
			
			
			
			 
			
		}
	});
	
	return houseMarks.data;
}

function getEventStudentOverallResult(){
	var evId= $("#eventname").val();
	
	
	
	$.ajax({
		type:'POST',
		url:"EventsMenu.html?method=getEventStudentOverallResult",
		data:{	
			    "evId":evId,
				
			},
		async:false,
		success:function(data){
			var result= $.parseJSON(data);
			
			
			
			
			
			if(result.data.length>0){
				$("#second  thead").empty();
				$("#second  thead").append("<tr >" +
						"<th class='rotate'><div>SL.NO.</div></th>" +
						"<th class='rotate'><div  style='width: 100px;transform: translate(0);'>HOUSE</div></th>" +
						"<th class='rotate'><div>POSITION</div></th>" +	
						"</tr>");
				
				for(var i=0;i<result.data.length;i++){	
					$("#second > thead tr").append("<th class='rotate'><div>"+result.data[i].programmeName+"</div></th>");
					}
				
				$("#second  thead tr").append("<th class='rotate'><div style='width: 100px;transform: translate(0);'>SUB.TOTAL</div></th>");
				$("#second  thead tr").append("<th class='rotate'><div>TOTAL</div></th>");
				$("#second  thead tr").append("<th class='rotate'><div>POSITION</div></th>");
				
				
				for(var j=0;j<getHouseForParticipantList().length;j++){
					$("#second  tbody").append("<tr class='"+getHouseForParticipantList()[j]+" house'>" +
							"<td></td>" +
							"<td style='text-align:left;' colspan='"+(result.data.length+3)+"'>"+getHouseForParticipantList()[j]+"</td>" +
							"</tr>");
					
					
					for(var k=0;k<categoryDropDown.length;k++){
						$("#second  tbody").append("<tr class='"+getHouseForParticipantList()[j]+"_"+categoryDropDown[k].categoryId+"_first first programme'>" +
								"<td>"+(j+1)+"</td>" +
								"<td>"+categoryDropDown[k].categoryName+"</td>" +
								"<td>I</td>" +
								"</tr>");
						$("#second  tbody").append("<tr class='"+getHouseForParticipantList()[j]+"_"+categoryDropDown[k].categoryId+"_second second programme'>" +
								"<td></td>" +
								"<td></td>" +
								"<td>II</td>" +
								"</tr>");
						$("#second  tbody").append("<tr class='"+getHouseForParticipantList()[j]+"_"+categoryDropDown[k].categoryId+"_third third programme'>" +
								"<td></td>" +
								"<td></td>" +
								"<td>III</td>" +
								"</tr>");
						
						
					}
				}
				 
				for(var i=0;i<result.data.length;i++){	
					$("#second  tbody tr.programme").append("<td class='"+result.data[i].programmeName+"'></td>");
				}
					
					$("#second  tbody tr.first").append("<td class='sub-total' rowspan='3'></td>");
				
			
					$("#second  tbody tr.house").append("<td class='total' rowspan='"+(getCategoryDropDown().length*3+1)+"'></td><td class='rank' rowspan='"+(getCategoryDropDown().length*3+1)+"'></td>");
		
					for(var l=0;l<allreport.length;l++){
						$("#second tbody tr."+allreport[l].categoryId.split(",")[0]).find("[class='"+allreport[l].categoryId.split(",")[1]+"']").text(allreport[l].categoryId.split(",")[2]);
					}
					
					for(var l=0;l<cathouse.length;l++){
						
						var sub_t=0.0;
						$("tr[class^="+cathouse[l].categoryId+"]").each(function(){
							$(this).find("td[class]").not("td.sub-total").each(function(){
								sub_t=sub_t+Number($(this).text());
							});
						});
						$("#second tbody tr."+cathouse[l].categoryId+"_first td.sub-total").text(sub_t);
						
					}
					var rank=[]
					var chk=0.0;
					for(var l=0;l<housmark.length;l++){
						
						var sub_t=0.0;
						$("tr[class^="+housmark[l]+"_]").each(function(){
							$(this).find("td.sub-total").each(function(){
								sub_t=sub_t+Number($(this).text());
							});
						});
						
						$("#second tbody tr."+housmark[l]+" td.total").text(sub_t);
						if(chk!=sub_t){
							rank.push(sub_t);
						}
						chk=sub_t;
						
						
						
						//$("#second tbody tr."+housmark[l].houseName+" td.rank").text(housmark[l].points);
					}
					rank.sort(function(a, b){return b-a});
					
					for(var m=0;m<rank.length;m++){
						$(".total").each(function(){
							if(Number($(this).text())==rank[m] && m==0){
								$(this).closest("tr").find(".rank").text("First");
							}
							else if(Number($(this).text())==rank[m] && m==1){
								$(this).closest("tr").find(".rank").text("Second");
							}
							else if(Number($(this).text())==rank[m] && m==2){
								$(this).closest("tr").find(".rank").text("Third");
							}
							else if(Number($(this).text())==rank[m] && m==3){
								$(this).closest("tr").find(".rank").text("Fourth");
							}
						});
					}
					
				
			}else{
				$("#second  thead tr").append("<th class='rotate'><div>NO PROGRAM SET </div></th>");
			}
			
			
			}
		});
	
}
function write_headers_to_excel() 
{
  str="";

  var myTableHead = document.getElementById('second');
  var rowCount = myTableHead.rows.length;
  var colCount = myTableHead.getElementsByTagName("tr")[0].getElementsByTagName("th").length; 

var ExcelApp = new ActiveXObject("Excel.Application");
var ExcelSheet = new ActiveXObject("Excel.Sheet");
ExcelSheet.Application.Visible = true;

for(var i=0; i<rowCount; i++) 
{   
    for(var j=0; j<colCount; j++) 
    {           
        str= myTableHead.getElementsByTagName("tr")[i].getElementsByTagName("th")[j].innerHTML;
        ExcelSheet.ActiveSheet.Cells(i+1,j+1).Value = str;
    }
}

}
function fnExcelReport()
{
    var tab_text="<table border='2px'><tr bgcolor='#87AFC6'>";
    var textRange; var j=0;
    tab = document.getElementById('second'); // id of table

    for(j = 0 ; j < tab.rows.length ; j++) 
    {     
        tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
        //tab_text=tab_text+"</tr>";
    }

    tab_text=tab_text+"</table>";
    tab_text= tab_text.replace(/<A[^>]*>|<\/A>/g, "");//remove if u want links in your table
    tab_text= tab_text.replace(/<img[^>]*>/gi,""); // remove if u want images in your table
    tab_text= tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input params

    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE "); 

    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
    {
        txtArea1.document.open("txt/html","replace");
        txtArea1.document.write(tab_text);
        txtArea1.document.close();
        txtArea1.focus(); 
        sa=txtArea1.document.execCommand("SaveAs",true,"Say Thanks to overAllReport.xls");
    }  
    else                 //other browser not tested on IE 11
        sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));  

    return (sa);
}
function showError(id,errorMessage){
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
	$('.successmessagediv').hide();
	$(".errormessagediv").show();
	$(".validateTips").text(errorMessage);
	$(".errormessagediv").delay(3000).fadeOut();
}
function hideError(id){
	$(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
		});
}