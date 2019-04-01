$(document).ready(function(){	
	
	$("#allstudent tbody tr").click(function(e) {
		e.preventDefault();
		$('html, body').animate({
			scrollTop : 0
		}, 800);
		return false;
	});
	
	getissuedList();
	
	$("#reset").hide();
	$("#reset").click(function(){
		window.location.href ="studentcertificate.html?method=bonafideCertificate&locationid="+ $("#hiddenlocationid").val()
		+"&accyear="+$("#hiddenaccyearid").val()+"&classid="+$("#hiddenclassid").val()+"&sectionid="+$("#hiddensectionid").val()+"&stuid="+$("#hiddenstuid").val();
	});
		 $("#print").click(function () {
			 singleprint();
	     });
		 
		 $("#allstudent tbody tr").click(function(){
			
			 agecetiid = $(this).find(".ageid").attr("id");
				displaytabledata(agecetiid);
			});
		 

	$("#print").hide();
	$("#save").click(function(){

		datalist ={
				"accyear" :$("#hiddenaccyearid").val(),
				"locid" : $("#hiddenlocationid").val(),
				"classid" :$("#hiddenclassid").val(),
				"sectionid" :$("#hiddensectionid").val(),
				"stuname":$("#hiddenstuid").val(),
				"admissionno" :$("#admissionno").val(),
				"fathername" :$("#fathername").val(),
				"dob" : $("#dob").val(),
				"purpose":$("#purpose").val(),
				"otherinfo":$("#otherinfo").val(),
				"studentstatus":$("#studentstatus").val(),
				"motherName":$("#motherName").val(),
				"dobwords" :$("#dobwords").val()
		};
		
		if($("#purpose").val() == "" || $("#purpose").val() == undefined){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Purpose");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
		}
		else{
			 $("#dialog").dialog("open");
		}
	});
		$("#dialog").dialog({
			autoOpen  : false,
		    modal     : true,
		    title     : "Bonafied Certificate Details",
		    buttons   : {
		    	"Save": {
		    		text: 'Yes',
		    		 click: function() {
		    			 	$("#save").hide();
		    				$("#print").show();
		    				$('input[type=text]').attr('readonly','true');
		    				document.getElementById("purpose").style.backgroundColor = "#FFF";
		    				document.getElementById("otherinfo").style.backgroundColor = "#FFF";
		    			 datalist ={
		    						"accyear" :$("#hiddenaccyearid").val(),
		    						"locid" : $("#hiddenlocationid").val(),
		    						"classid" :$("#hiddenclassid").val(),
		    						"sectionid" :$("#hiddensectionid").val(),
		    						"stuname":$("#hiddenstuid").val(),
		    						"admissionno" :$("#admissionno").val(),
		    						"fathername" :$("#fathername").val(),
		    						"dob" : $("#dob").val(),
		    						"purpose":$("#purpose").val(),
		    						"otherinfo":$("#otherinfo").val(),
		    						"studentstatus":$("#studentstatus").val(),
		    						"motherName":$("#motherName").val(),
		    						"dobwords" :$("#dobwords").val()
		    				};
		    			 $.ajax({
		    					type : "POST",
		    					url : "studentcertificate.html?method=saveBonafiedCertificateData",
		    					data : datalist,
		    					async : false,
		    					success : function(data){
		    						var result = $.parseJSON(data);
		    						if(result.status == "true"){
		    							$(".successmessagediv").show();
		    							$(".validateTips").text("Record Saved Successfully...");
		    							setTimeout(function() {
		    								$('.successmessagediv').fadeOut();
		    								$.ajax({
		    									type : "POST",
		    									url : "studentcertificate.html?method=getbonafiedissueddetails",
		    									data : datalist,
		    									async : false,
		    									success : function(data){
		    										var result = $.parseJSON(data);
		    										$("#issued").show();
		    										$("#heading").show();
		    										$("#certificatetable").empty();
		    										$("#certificatetable").append("<table class='table' id='allstudent' width='100%'" +">"
		    												+"<center><tr><th>SI No</th>"+
		    												"<th>Entry Date</th>" +
		    												"<th>Purpose</th>" +
		    												"</center></tr>" +
		    												"</table>"
		    										);
		    										for(var i=0;i<result.stuList.length;i++){
		    											$("#allstudent tbody").append("<tr>"
		    													+"<td class='ageid' id='"+result.stuList[i].agecertid+"'>"+result.stuList[i].slno+"</td>" 
		    													+"<td>"+result.stuList[i].entryDate+"</td>"
		    													+"<td>"+result.stuList[i].purpose+"</td>"
		    													+"</tr>");
		    											}	
		    										pagination(50);
		    										$("#allstudent").after("<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select></div><div class='pagination pagelinks'></div>")
		    										$("#show_per_page").change(function(){
		    											pagination($(this).val());
		    										});
		    									}
		    								});
		    								 $("#allstudent tbody tr").click(function(){
		    									  agecetiid = $(this).find(".ageid").attr("id");
		    										displaytabledata(agecetiid)
		    									});
		    								 $("#allstudent tbody tr").click(function(e) {
		    										e.preventDefault();
		    										$('html, body').animate({
		    											scrollTop : 0
		    										}, 800);
		    										return false;
		    									});
		    							}, 2500);
		    						}
		    					}
		    				});
		    			 $(this).dialog('close');
		    		 }
		    	},
		    	'No' : function() {
	                $(this).dialog('close');
		    	}
		    	}
		    });
});

function getissuedList(){
	
	datalist ={
			"accyear" :$("#hiddenaccyearid").val(),
			"locid" : $("#hiddenlocationid").val(),
			"classid" :$("#hiddenclassid").val(),
			"sectionid" :$("#hiddensectionid").val(),
			"stuname":$("#hiddenstuid").val()
	}
	$.ajax({
		type : "POST",
		url : "studentcertificate.html?method=getbonafiedissueddetails",
		data : datalist,
		async : false,
		success : function(data){
			var result = $.parseJSON(data);
			$("#certificatetable").empty();
			
			if(result.stuList.length == 0){
				$("#issued").hide();
				$("#heading").hide();
			}
			else{
				
			$("#certificatetable").append("<table class='table' id='allstudent' width='100%'" +">"
					+"<center><tr><th>SI No</th>"+
					"<th>Entry Date</th>" +
					"<th>Purpose</th>" +
					"</center></tr>" +
					"</table>"
			);
			
			for(var i=0;i<result.stuList.length;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td class = 'ageid' id='"+result.stuList[i].agecertid+"'>"+result.stuList[i].slno+"</td>" 
						+"<td>"+result.stuList[i].entryDate+"</td>"
						+"<td>"+result.stuList[i].purpose+"</td>"
						+"</tr>");
				}	
			$("#allstudent tbody tr").click(function(e) {
				e.preventDefault();
				$('html, body').animate({
					scrollTop : 0
				}, 800);
				return false;
			});
			pagination(50);
			$("#allstudent").after("<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select></div><div class='pagination pagelinks'></div>")
			$("#show_per_page").change(function(){
				pagination($(this).val());
			});
			
		}
		}
	});
}

function displaytabledata(agecetiid){

	var stuid = $("#hiddenstuid").val();
	var selection = "bonafied";
	$("#save").hide();
	$("#print").show();
	$("#reset").show();
	datalist = {
			"stuid" : $("#hiddenstuid").val(),
			"agecetiid" : agecetiid,
			"selection":selection
	};
	$('input[type=text]').attr('readonly','true');
	document.getElementById("purpose").style.backgroundColor = "#FFF";
	document.getElementById("otherinfo").style.backgroundColor = "#FFF";
	$.ajax({
		
			type : "POST",
			url : "studentcertificate.html?method=displayageissueddetails",
			data : datalist,
			async : false,
			success : function(data){
				var result = $.parseJSON(data);
					
				
				for(i=0;i<result.stuList.length;i++){
					$("#admissionno").val(result.stuList[i].admissionno);
					$("#accyearname").val(result.stuList[i].accyear);
					$("#schoolName").val(result.stuList[i].locname);
					$("#stuname").val(result.stuList[i].stuname);
					$("#purpose").val(result.stuList[i].purpose);
					$("#classname").val(result.stuList[i].classname);
					$("#fathername").val(result.stuList[i].fathername);
					$("#dob").val(result.stuList[i].dob);
					$("#otherinfo").val(result.stuList[i].otherinfo);
					$("#studentstatus").val(result.stuList[i].studentstatus);
					$("#sectionname").val(result.stuList[i].sectionname);		
					$("#motherName").val(result.stuList[i].motherName);		
					$("#dobwords").val();		
				}
				$("#allstudent tbody tr").click(function(e) {
					e.preventDefault();
					$('html, body').animate({
						scrollTop : 0
					}, 800);
					return false;
				});
			}
	});

}


function pagination(list) {

	
	
	
	var show_per_page = list;
    var number_of_items = $('#allstudent tbody tr,.allstudent tbody tr').length;
   
    var number_of_pages = Math.ceil(number_of_items / show_per_page);
    
    $('.pagination').empty();
    $('.pagination').append('<div class=controls></div><input id=current_page type=hidden><input id=show_per_page type=hidden>');
    $('#current_page').val(0);
    $('#show_per_page').val(show_per_page);

    var navigation_html = '<a class="prev" onclick="previous()">Prev</a>';
    var current_link = 0;
    while (number_of_pages > current_link) {
        navigation_html += '<a class="page" onclick="go_to_page(' + current_link + ')" longdesc="' + current_link + '">' + (current_link + 1) + '</a>';
    	 current_link++;
    }
    navigation_html += '&nbsp;&nbsp;&nbsp;<a class="next" onclick="next()">Next</a>';

    $('.controls').html(navigation_html);
    $('.controls .page:first').addClass('active');
    $(".controls").find(".page").hide();
    $(".controls").find(".active").show();
    $(".controls").find(".active").prev().prev().show();	
    $(".controls").find(".active").prev().show();	
    $(".controls").find(".active").next().show();	
    $(".controls").find(".active").next().next().show();
   
    $('#allstudent tbody tr,.allstudent tbody tr').css('display', 'none');
    $('#allstudent tbody tr,.allstudent tbody tr').slice(0, show_per_page).css('display', 'table-row');
}

function go_to_page(page_num) {
    var show_per_page = parseInt($('#show_per_page').val(), 0);

    start_from = page_num * show_per_page;

    end_on = start_from + show_per_page;
    $(".controls").find(".page").hide();
    $(".controls").find(".active").show();
    $(".controls").find(".active").prev().prev().show();	
    $(".controls").find(".active").prev().show();	
    $(".controls").find(".active").next().show();	
    $(".controls").find(".active").next().next().show();	
    $('#allstudent tbody tr,.allstudent tbody tr').css('display', 'none').slice(start_from, end_on).css('display', 'table-row');

    $('.page[longdesc=' + page_num + ']').addClass('active').siblings('.active').removeClass('active');

    $('#current_page').val(page_num);
}



function previous() {

    new_page = parseInt($('#current_page').val(), 0) - 1;
    //if there is an item before the current active link run the function
    if ($('.active').prev('.page').length == true) {
        go_to_page(new_page);
    }

}

function next() {
    new_page = parseInt($('#current_page').val(), 0) + 1;
    //if there is an item after the current active link run the function
    if ($('.active').next('.page').length == true) {
        go_to_page(new_page);
    }
}
function singleprint(){			
	
	var stuname = $("#stuname").val();
	var fathername = $("#fathername").val();
	var mothername = $("#motherName").val();
	var accyear = $("#accyearname").val();
	var school=$("#schoolName").val();
	var addmission = $("#admissionno").val();
	var classname= $("#classname").val()+" - "+$("#sectionname").val();
var gender= $("#hiddenstugender").val();
	
	if(gender == "Male"){
		heading = "Kumar";
		rel="son"
	}else{
		heading = "Kumari";
		rel="daughter"
	}
	
	
var frame1 = $('<iframe />');
frame1[0].name = "frame1";
frame1.css({ "position": "absolute", "top": "-1000000px" });
$("body").append(frame1);
var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
frameDoc.document.open();
//Create a new HTML document.
frameDoc.document.write('<html><head><title>DIV Contents</title>');
//Append the external CSS file.
frameDoc.document.write('<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />');
frameDoc.document.write('<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">');
frameDoc.document.write('<link href="CSS/newUI/modern-business.css" rel="stylesheet">');
frameDoc.document.write('<link href="CSS/Certificates/ageCertificate.css" rel="stylesheet">');
frameDoc.document.write('</head><body>');

//Append the DIV contents.

frameDoc.document.write('<div id="wrap">'+'<table align="center" style="margin-top:100px;"><tr><td><h3 class="age">BONAFIED CERTIFICATE</h3></td></tr>'+
		'<tr><td><p>This is to certify that&nbsp'+heading+'&nbsp&nbsp<span style="border-bottom:1px solid black">&nbsp&nbsp'+stuname+'&nbsp(AdmnNo - '+addmission+')&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span></p></td></tr>'+
		'<tr><td><p>'+rel+'&nbspof&nbsp&nbsp<span style="border-bottom:1px solid black">&nbsp&nbsp'+fathername+'&nbspand&nbsp'+mothername+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span></p></td></tr>'+
		'<tr><td><p>is a bonafied student of this school studying in std&nbsp&nbsp<span style="border-bottom:1px solid black">&nbsp&nbsp'+classname+'&nbsp&nbsp</span></p></td></tr><tr><td><p>during the academic year&nbsp&nbsp<span style="border-bottom:1px solid black">&nbsp&nbsp'+accyear +'&nbsp&nbsp</span>&nbsp.</p></td></tr></table>'+
		'<table width="100%" style="margin-top:100px;"><tr><td align="left"><h3>Date</h3></td><td><h3 align="center">Seal</h3></td><td  align="right"><h3>Principal</h3></td></tr></table>'+
'</div>');
frameDoc.document.write('</body></html>');
frameDoc.document.close();
setTimeout(function () {
  window.frames["frame1"].focus();
  window.frames["frame1"].print();
  frame1.remove();
}, 100);
}