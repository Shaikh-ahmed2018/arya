$(document).ready(function(){
	$("#academicYear").val($("#hacademicyaer").val());
	$("#locationName").change(function(){
		getClassByLoc();
		
		getTerm();
		getDefaulterFeeList();
	});
	$("#className").change(function(){
		getDivision();
		getDefaulterFeeList();
	});
	$("#divisionName").change(function(){
		getDefaulterFeeList();
	});
	$("#termName").change(function(){
		getDefaulterFeeList();
	});
	$("#academicYear").change(function(){
		getTerm();
		getDefaulterFeeList();
	});
	
	$("#download").click(function(){
		if($("#locationName").val()=="" || $("#locationName").val()==undefined){
			showError("#locationName","Select Location Name");
			return false;
		}
		if($("#className").val()=="" || $("#className").val()==undefined){
			showError("#className","Select Class Name");
			return false;
		}
		if($("#academicYear").val()=="" || $("#academicYear").val()==undefined){
			showError("#academicYear","Select academicYear");
			return false;
		}
		if($("#divisionName").val()=="" || $("#divisionName").val()==undefined){
			showError("#divisionName","Select Division Name");
			return false;
		}
		if($("#termName").val()=="" || $("#termName").val()==undefined){
			showError("#termName","Select term Name");
			return false;
		} 
	});
	
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
	
//DOWNLOAD PDF AND EXCEL
	$('#download').click(function() {
		fnExcelReport()
		});
	
	$("#pdfDownload").click(function(){
		var locname = $("#locationName :selected").text();
		var classname=$("#className :selected").text();
		var	divname=$("#divisionName :selected").text();
		var termname=$("#termName :selected").text();
		var accname=$("#academicYear :selected").text();
		var locId = $("#locationName").val();
		var classId=$("#className").val();
		var	divId=$("#divisionName").val();
		var termId=$("#termName").val();
		var accId=$("#academicYear").val();
		
		window.location.href = "addfee.html?method=FeeDetailsDefaulterPDF" +
				"&locId="+locId+"&accId="+accId+"&classId="+classId+"&divId="+divId+"&termId="+termId
			   +"&locname="+locname+"&classname="+classname+"&divname="+divname+"&termname="+termname+"&accname="+accname;
	});
		
	$("#print").click(function(){
		var location=$("#locationName option:selected").text(); 
		var reportType="Fee Defaulter List";
		var accyear=$("#academicYear option:selected").text();
		
		var classname=$("#className option:selected").text();
		var section=$("#divisionName option:selected").text();
		
		var termstatus=$("#termstatus option:selected").text();
		var term=$("#termName option:selected").text();
		
		
		 var b= document.getElementById("collapseOne").innerHTML;
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
		        frameDoc.document.write('<link href="CSS/newUI/custome.css" rel="stylesheet">');
		        frameDoc.document.write('<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>');
		        frameDoc.document.write('<script type="text/javascript" src="JS/backOffice/Reports/defaulterList.js"></script>');
		        frameDoc.document.write('<style>table td,table th{border:1px solid #000 !important;}</style>');
		        frameDoc.document.write('</head><body>');
		      
		        
		        frameDoc.document.write('<div style="text-align:center;"><h2>'+location+'</h2></div>');
		        frameDoc.document.write('<div class="col-xs-6"><div class="form-group clearfix"><label for="inputPassword" class="control-label col-xs-5" id="inputnames" style="text-align: right;">Report Type :</label><div class="col-xs-7">'+reportType+'</div></div></div>');
		        frameDoc.document.write('<div class="col-xs-6"><div class="form-group clearfix"><label for="inputPassword" class="control-label col-xs-5" id="inputnames" style="text-align: right;">Academic Year :</label><div class="col-xs-7">'+accyear+'</div></div></div>');
		       
		        	frameDoc.document.write('<div class="col-xs-6"><div class="form-group clearfix"><label for="inputPassword" class="control-label col-xs-5" id="inputnames" style="text-align: right;">Class :</label><div class="col-xs-7">'+classname+'</div></div></div>');
			        frameDoc.document.write('<div class="col-xs-6"><div class="form-group clearfix"><label for="inputPassword" class="control-label col-xs-5" id="inputnames" style="text-align: right;">Division :</label><div class="col-xs-7">'+section+'</div></div></div>');
			        
			        frameDoc.document.write('<div class="col-xs-6"><div class="form-group clearfix"><label for="inputPassword" class="control-label col-xs-5" id="inputnames" style="text-align: right;">Term :</label><div class="col-xs-7">'+term+'</div></div></div>');
			  
		        
		        frameDoc.document.write(abd);
		        frameDoc.document.write("<table><tbody><tr><td style='text-align:right'><b>Total Dues </b></td><td> : </td><td> "+totAmount+"</td></tr></tbody></table>");
		        frameDoc.document.write('</body></html>');
		        frameDoc.document.close();
		        setTimeout(function () {
		            window.frames["frame1"].focus();
		            window.frames["frame1"].print();
		            frame1.remove();
		        }, 100);
		
	});
	
});//end JQUERY

function getClassByLoc(){
		var locationid=$("#locationName").val();
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
				$('#className').html("");
				$('#className').append('<option value="all">ALL</option>');
				for ( var j = 0; j < result.ClassList.length; j++) {
					$('#className').append('<option value="'
							+ result.ClassList[j].classcode + '">'
							+ result.ClassList[j].classname
							+ '</option>');
				}
			}
		});
};

function getDivision(){
		datalist={
				"classidVal" : $("#className").val(),
				"locationId":$("#locationName").val()
		},
		$.ajax({
			type : 'POST',
			url : "studentRegistration.html?method=getClassSection",
			data : datalist,
			async : false,
			success : function(response) {
				var result = $.parseJSON(response);
				$('#divisionName').html("");
				$('#divisionName').append('<option value="all">ALL</option>');
				for ( var j = 0; j < result.sectionList.length; j++) {
					$('#divisionName').append('<option value="' + result.sectionList[j].sectioncode
							+ '">' + result.sectionList[j].sectionnaem
							+ '</option>');
				}
			}
		});
}

function getTerm(){
	datalist={
			"locId" : $("#locationName").val(),
			"accId" : $("#academicYear").val(), 
	},
	$.ajax({
		type : 'POST',
		url : "reportaction.html?method=getTerm",
		data : datalist,
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);
			var j;
			var len= result.data.length;
			$('#termName').html("");
			$('#termName').append('<option value="">' +"-----Select-----"+ '</option>');
			for ( j = 0; j <len; j++) {
				$('#termName').append('<option value="'
						+ result.data[j].termname+ '">'
						+ result.data[j].termId
						+ '</option>');
			}
		}
	});
}
function getDefaulterFeeList(){
		datalist ={"locId":$("#locationName").val(),
					"classId":$("#className").val(),
					"divId":$("#divisionName").val(),
					"termId":$("#termName").val(),
					"accId":$("#academicYear").val(),
			};
		$.ajax({
			type:"post",
			url:"feecollection.html?method=getDefaulterFeeList",
			data:datalist,
			async:false,
			success:function(data){
				var result = $.parseJSON(data);
				$("#allstudent tbody").empty();
				if(result.data.length>0){
					 totAmount=0.0;
					
				for(var i=0;i<result.data.length;i++){
					totAmount+=Number(result.data[i].dueAmt);
					
					$("#allstudent tbody").append("<tr>"
							+"<td>"+(i+1)+"</td>"
							+"<td>"+result.data[i].admissionNo+"</td>"
							+"<td>"+result.data[i].studentName+"</td>"
							+"<td>"+result.data[i].locationName+"</td>"
							+"<td>"+result.data[i].className+"</td>"
							+"<td>"+result.data[i].divisionName+"</td>"
							+"<td>"+result.data[i].termName+"</td>"
							+"<td>"+result.data[i].dueAmt+"</td>"
							+"<td>"+result.data[i].name+"</td>"
							+"</tr>");
					}
				
				
				}
				else{
					$("#allstudent tbody").append("<tr><td colspan='9'>No Record Found</td></tr>");
				}
			}
		});
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

function hideError(){
	document.getElementById("termName").style.backgroundColor = "#fff";
	document.getElementById("termName").style.border = "1px solid #ccc";
}

function fnExcelReport()
{
    var tab_text="<table border='2px'><tr bgcolor='#87AFC6'>";
    var textRange; var j=0;
    tab = document.getElementById('allstudent'); // id of table

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
        sa=txtArea1.document.execCommand("SaveAs",true,"Say Thanks to TransportReport.xls");
    }  
    else                 //other browser not tested on IE 11
        sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));  

    return (sa);
}