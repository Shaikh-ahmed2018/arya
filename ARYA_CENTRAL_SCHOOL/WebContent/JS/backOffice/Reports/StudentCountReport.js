$(document).ready(function(){
	$("#accyear").val($("#hacademicyaer").val());
	
	var accyear=$("#accyear").val();
	var location=$("#location").val();
	var selection=$("#selection").val();
	$("#search").click(function(){
		
		religionlist=getReligion();
		categorylist=getCasteCategory();
		classList=getClassList();
		$("#studentlisttable").empty();
		$("#studentlisttable").append("<table class='allstudent' id='studentcounttable'></table>");
		$("#studentcounttable").append("<thead><tr id='firstrow'>" +
				"<th></th>" +
				"</tr></thead>");
		for(var i=0;i<religionlist.length;i++){
			$("#studentcounttable thead tr#firstrow").append("<th colspan='2'>"+religionlist[i].religion+"</th>");
		}
		$("#studentcounttable thead tr#firstrow").append("<th rowspan='2'>Total</th>");
		for(var i=0;i<categorylist.length;i++){
			$("#studentcounttable thead tr#firstrow").append("<th colspan='2'>"+categorylist[i].casteCategory+"</th>");
		}
		$("#studentcounttable thead tr#firstrow").append("<th rowspan='2'>Total</th>");
		$("#studentcounttable thead").append("<tr id='secondrow'>" +
				"<th></th>" +
					"</tr>");
		for(var i=0;i<religionlist.length;i++){
			$("#studentcounttable thead tr#secondrow").append("<th>Boys</th><th>Girls</th>");
		}
		for(var i=0;i<categorylist.length;i++){
			$("#studentcounttable thead tr#secondrow").append("<th>Boys</th><th>Girls</th>");
		}
		
		$("#studentcounttable").append("<tbody></tbody>");
		for(var i=0;i<classList.length;i++){
			$("#studentcounttable tbody").append("<tr id='"+classList[i].classId+"'><td>"+classList[i].classname+"</td></tr>");
		}
		
		$("#studentcounttable tbody tr").each(function(){
			for(var i=0;i<religionlist.length;i++){
				$(this).append("<td class='rel "+religionlist[i].religion+"_Male'></td><td class='rel "+religionlist[i].religion+"_Female'></td>");
			}
			$(this).append("<td class='reltotal'></td>");
			for(var i=0;i<categorylist.length;i++){
				$(this).append("<td class='cast ct_"+categorylist[i].casteCategory+"_Male'></td><td class='cast ct_"+categorylist[i].casteCategory+"_Female'></td>");
			}
			$(this).append("<td class='casttotal'></td>");
		});
		$("#studentcounttable").append("<tfoot><tr><th>Total</th></tr><tfoot>");
		for(var i=0;i<religionlist.length;i++){
			$("#studentcounttable tfoot tr").append("<th class='rel "+religionlist[i].religion+"_Male'></th><th class='rel "+religionlist[i].religion+"_Female'></th>");
		}
		$("#studentcounttable tfoot tr").append("<th class='reltotal'></th>");
		for(var i=0;i<categorylist.length;i++){
			$("#studentcounttable tfoot tr").append("<th class='cast "+categorylist[i].casteCategory+"_Male'></th><th class='cast "+categorylist[i].casteCategory+"_Female'></th>");
		}
		$("#studentcounttable tfoot tr").append("<th class='casttotal'></th>");
		
		 if($("#selection").val() == 'classwise'){
			getstudentCountClassWise();
		}
		else if($("#selection").val() == "sectionwise"){
			getstudentCountSectionWise();
		}
		
	});
	
	
	$("#print").click(function(){
		
		 var b= document.getElementById("studentlisttable").innerHTML;
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
		        frameDoc.document.write('<style>td,th{border: 1px solid #000 !important;}</style>');
		        
		        frameDoc.document.write('<link href="CSS/newUI/custome.css" rel="stylesheet">');
		        frameDoc.document.write('<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>');
		        frameDoc.document.write('<script type="text/javascript" src="JS/backOffice/Events/OverallResult.js"></script>');
		        frameDoc.document.write('</head><body>');
		        frameDoc.document.write('<h2 style="text-align:center;">ARYA CENTRAL SCHOOL</h2>');
		        
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
function getstudentCountClassWise(){
	
	var dataList={
			"accyear":$("#accyear").val(),
			"locationid":$("#location").val(),
			"selection":$("#selection").val()
	};
	$.ajax({
		type:'POST',
		url:'reportaction.html?method=getStudentCountSelectionWise',
		data:dataList,
		async:false,
		
		success: function(response){
			var result=$.parseJSON(response);
			alert(result)
			if(result.data.length>0){
				for(var i=0;i<result.data.length;i++){
					
					$("tr#"+result.data[i].classId).find("td."+result.data[i].casteCategory).text(result.data[i].sno);
					
					
				}
			}
			var relgrandtotal=0;
			var castgrandtotal=0;
			$("#studentcounttable tbody tr").each(function(){
				var reltotal=0;
				$(this).find("td.rel").each(function(){
					reltotal+=Number($(this).text());
				});
				$(this).find("td.reltotal").text(reltotal);
				
				
				
				$(this).find("td.reltotal").each(function(){
					relgrandtotal+=Number($(this).text());
				});
				$("th.reltotal").text(relgrandtotal);
				
				var casttotal=0;
				$(this).find("td.cast").each(function(){
					casttotal+=Number($(this).text());
				});
				$(this).find("td.casttotal").text(casttotal);
				
				
				
				$(this).find("td.casttotal").each(function(){
					castgrandtotal+=Number($(this).text());
				});
				
				$("th.casttotal").text(castgrandtotal);
				
				
			});
			
			
			for(var i=0;i<religionlist.length;i++){
				var colTotal=0;
				$("td."+religionlist[i].religion+"_Male").each(function(){
					colTotal+=Number($(this).text());
				});
				$("th."+religionlist[i].religion+"_Male").text(colTotal);
				var fcoltotal=0;
				$("td."+religionlist[i].religion+"_Female").each(function(){
					fcoltotal+=Number($(this).text());
				});
				$("th."+religionlist[i].religion+"_Female").text(fcoltotal);
			}
			for(var i=0;i<categorylist.length;i++){
				var colTotal=0;
				$("td.ct_"+categorylist[i].casteCategory+"_Male").each(function(){
					colTotal+=Number($(this).text());
				});
				$("th."+categorylist[i].casteCategory+"_Male").text(colTotal);
				var fcoltotal=0;
				$("td.ct_"+categorylist[i].casteCategory+"_Female").each(function(){
					fcoltotal+=Number($(this).text());
				});
				$("th."+categorylist[i].casteCategory+"_Female").text(fcoltotal);
			}
			
			
			
			
		}
	});
}
function getstudentCountSectionWise(){
	var dataList={
			"accyear":$("#accyear").val(),
			"locationid":$("#location").val(),
			"selection":$("#selection").val()
	};
	$.ajax({
		type:'POST',
		url:'reportaction.html?method=getStudentCountSelectionWise',
		data:dataList,
		async:false,
		
		success: function(response){
			var result=$.parseJSON(response);
			alert(result)
			if(result.data.length>0){
				for(var i=0;i<result.data.length;i++){
					$("tr#"+result.data[i].classId).find("td."+result.data[i].casteCategory).text(result.data[i].sno);
				}
				
			}
			var relgrandtotal=0;
			var castgrandtotal=0;
			$("#studentcounttable tbody tr").each(function(){
				var reltotal=0;
				$(this).find("td.rel").each(function(){
					reltotal+=Number($(this).text());
				});
				$(this).find("td.reltotal").text(reltotal);
				
				$(this).find("td.reltotal").each(function(){
					relgrandtotal+=Number($(this).text());
				});
				$("th.reltotal").text(relgrandtotal);
				
				var casttotal=0;
				$(this).find("td.cast").each(function(){
					casttotal+=Number($(this).text());
				});
				$(this).find("td.casttotal").text(casttotal);
				
				$(this).find("td.casttotal").each(function(){
					castgrandtotal+=Number($(this).text());
				});
				
				$("th.casttotal").text(castgrandtotal);
				
				
			});
			
			for(var i=0;i<religionlist.length;i++){
				var colTotal=0;
				$("td."+religionlist[i].religion+"_Male").each(function(){
					colTotal+=Number($(this).text());
				});
				$("th."+religionlist[i].religion+"_Male").text(colTotal);
				var fcoltotal=0;
				$("td."+religionlist[i].religion+"_Female").each(function(){
					fcoltotal+=Number($(this).text());
				});
				$("th."+religionlist[i].religion+"_Female").text(fcoltotal);
			}
			for(var i=0;i<categorylist.length;i++){
				var colTotal=0;
				$("td.ct_"+categorylist[i].casteCategory+"_Male").each(function(){
					colTotal+=Number($(this).text());
				});
				$("th."+categorylist[i].casteCategory+"_Male").text(colTotal);
				var fcoltotal=0;
				$("td.ct_"+categorylist[i].casteCategory+"_Female").each(function(){
					fcoltotal+=Number($(this).text());
				});
				$("th."+categorylist[i].casteCategory+"_Female").text(fcoltotal);
			}
		}
	});
	
}


function getClassList(){
	var dataList={
			"accyear":$("#accyear").val(),
			"locationid":$("#location").val(),
			"selection":$("#selection").val()
	};
	$.ajax({
		type:'POST',
		url:'reportaction.html?method=getClassListSelectionWise',
		data:dataList,
		async:false,
		
		success: function(response){
			 classdetail=$.parseJSON(response);
		}
	});
	return classdetail.data;
}
function getReligion(){
	
	$.ajax({
		type:'POST',
		url:'reportaction.html?method=religionDetails',
		async:false,
		
		success: function(response){
			 religion=$.parseJSON(response);
		}
	});
	return religion.data;
}
function getCasteCategory(){
	
	$.ajax({
		type:'POST',
		url:'reportaction.html?method=categoryName',
		
		async:false,
		
		success: function(response){
			 category=$.parseJSON(response);
		}
	});
	return category.data;
}

function fnExcelReport()
{
    var tab_text="<table border='2px'><tr bgcolor='#87AFC6'>";
    var textRange; var j=0;
    tab = document.getElementById('studentcounttable'); // id of table

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