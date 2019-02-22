$(document).ready(function(){

	//getStudentListByJs("all",$("#globalAcademicYear").val(),"all","all","all");

	$("#resetbtn").click(function(){
		$("#locationname").val("");
		$("#Acyearid").val("");
		$("#classname").val("");
		$("#sectionid").val("");
		$("#searchvalue").val("");
	});
	
	
	$("#Acyearid").val($("#globalAcademicYear").val());
	getStudentListByJs("all",$("#globalAcademicYear").val(),"all","all","all");

	
	$("#show_per_page").change(function(){
		locationId=$("#locationname").val();
		if($("#locationname").val()==""){
			locationId="all";
		}
		academicYear=$("#Acyearid").val();
		if($("#Acyearid").val()==""){
			academicYear="all";
		}
		classId=$("#classname").val();
		if($("#classname").val()==""){
			classId="all";
		}
		divisionId=$("#sectionid").val();
		if($("#sectionid").val()==""){
			divisionId="all";
		}
		searchTerm=$("#searchvalue").val();
		if($("#searchvalue").val()==""){
			searchTerm="all";
		}
		getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm);
	});
		
		$("#locationname").change(function(){
			getClassList();
			locationId=$("#locationname").val();
			if($("#locationname").val()==""){
				locationId="all";
			}
			academicYear=$("#Acyearid").val();
			if($("#Acyearid").val()==""){
				academicYear="all";
			}
			classId=$("#classname").val();
			if($("#classname").val()==""){
				classId="all";
			}
			divisionId=$("#sectionid").val();
			if($("#sectionid").val()==""){
				divisionId="all";
			}
			searchTerm=$("#searchvalue").val();
			if($("#searchvalue").val()==""){
				searchTerm="all";
			}
			getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm);
		});
		$("#Acyearid").change(function(){
			
			locationId=$("#locationname").val();
			if($("#locationname").val()==""){
				locationId="all";
			}
			academicYear=$("#Acyearid").val();
			if($("#Acyearid").val()==""){
				academicYear="all";
			}
			classId=$("#classname").val();
			if($("#classname").val()==""){
				classId="all";
			}
			divisionId=$("#sectionid").val();
			if($("#sectionid").val()==""){
				divisionId="all";
			}
			searchTerm=$("#searchvalue").val();
			if($("#searchvalue").val()==""){
				searchTerm="all";
			}
			getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm);
		});
		$("#classname").change(function(){
			getSectionList($(this).val());
			locationId=$("#locationname").val();
			if($("#locationname").val()==""){
				locationId="all";
			}
			academicYear=$("#Acyearid").val();
			if($("#Acyearid").val()==""){
				academicYear="all";
			}
			classId=$("#classname").val();
			if($("#classname").val()==""){
				classId="all";
			}
			divisionId=$("#sectionid").val();
			if($("#sectionid").val()==""){
				divisionId="all";
			}
			searchTerm=$("#searchvalue").val();
			if($("#searchvalue").val()==""){
				searchTerm="all";
			}
			getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm);
		});
		$("#sectionid").change(function(){
			locationId=$("#locationname").val();
			if($("#locationname").val()==""){
				locationId="all";
			}
			academicYear=$("#Acyearid").val();
			if($("#Acyearid").val()==""){
				academicYear="all";
			}
			classId=$("#classname").val();
			if($("#classname").val()==""){
				classId="all";
			}
			divisionId=$("#sectionid").val();
			if($("#sectionid").val()==""){
				divisionId="all";
			}
			searchTerm=$("#searchvalue").val();
			if($("#searchvalue").val()==""){
				searchTerm="all";
			}
			getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm);
		});
		$("#search").click(function(){
			locationId=$("#locationname").val();
			if($("#locationname").val()==""){
				locationId="all";
			}
			academicYear=$("#Acyearid").val();
			if($("#Acyearid").val()==""){
				academicYear="all";
			}
			classId=$("#classname").val();
			if($("#classname").val()==""){
				classId="all";
			}
			divisionId=$("#sectionid").val();
			if($("#sectionid").val()==""){
				divisionId="all";
			}
			searchTerm=$("#searchvalue").val();
			if($("#searchvalue").val()==""){
				searchTerm="all";
			}
			getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm);
		});
		$("#searchvalue").keypress(function(e){
			if(e.keyCode==13){
			locationId=$("#locationname").val();
			if($("#locationname").val()==""){
				locationId="all";
			}
			academicYear=$("#Acyearid").val();
			if($("#Acyearid").val()==""){
				academicYear="all";
			}
			classId=$("#classname").val();
			if($("#classname").val()=="" || $("#classname").val()==undefined){
				classId="all";
			}
			divisionId=$("#sectionid").val();
			if($("#sectionid").val()==""){
				divisionId="all";
			}
			searchTerm=$("#searchvalue").val();
			if($("#searchvalue").val()==""){
				searchTerm="all";
			}
			getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm);
			}
		});
		
		
		$("#editStudent").click(function() {
			var stdId=$('input[name="selectBox"]:checked').val();

			if (stdId==undefined) {
				$('.errormessagediv').show();
				$('.validateTips').text("Select Any One Student");
			} else {
				$('#studentid').stdId;
				accyear=stdId.split(" ")[1];
				stdId=stdId.split(" ")[0];
				window.location.href = "studentRegistration.html?method=editStudent&searchTerm="+ stdId+","+accyear;
			}

		});

		// Delete Function

		$("#trash").click(function() {
			var stdId=$('input[name="selectBox"]:checked').val().split(" ")[0];

			if (stdId==undefined)  {
				$('.errormessagediv').show();
				$('.validateTips').text(
				"Select Any One Student");
			} else {
				$('#studentid').stdId;

				var Check = {
						"studentid" : stdId
				};
				
				$.ajax({
					type : "POST",
					url : "studentRegistration.html?method=deactivateStudent",
					data : Check,
					async : false,
					success : function(data) {
						var result = $.parseJSON(data);

						if (result.status == true) {
							$('.successmessagediv').show();
							$('.successmessage').text("Student Deleted Successfully");

							setTimeout(function() {	location.reload(); }, 1000);
						} else {
							$('.errormessagediv').show();
							$('.validateTips').text("Student already mapped can't Delete");

							setTimeout(function() { location.reload(); }, 1000);
						}

					}
				});
			}

		});
});
function getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm){
	var totalcount=0;
	var start=(Number($(".page.active").text())*Number($("#show_per_page").val())-Number($("#show_per_page").val()));
	if(start<0){
		start=0;
	}
	datalist={
			"locationId" : locationId,
			"academicYear" : academicYear,
			"classId" : classId,
			"divisionId" : divisionId,
			"searchTerm" : searchTerm,
			"show_per_page":$("#show_per_page").val(),
			"startPoint":start,
	},
	
	$.ajax({
		
		type : 'POST',
		url : "studentRegistration.html?method=getStudentDetailsByJsInRegistration",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			$('#allstudent tbody').empty();
			if(result.studentdetailslist.length>0){
				totalcount=result.studentdetailslist[0].totalCount;
		var len=Number($("#show_per_page").val());	
				if(Number(result.studentdetailslist.length) < Number($("#show_per_page").val())){
					len=Number(result.studentdetailslist.length);
				}
				
				for(var i=0;i<len;i++){
					$('#allstudent tbody').append("<tr>" +
							"<td><input type='radio' name='selectBox' id='selectBox' value='"+result.studentdetailslist[i].studentId+" "+result.studentdetailslist[i].academicYearId+"'></td>" +
							"<td>"+result.studentdetailslist[i].academicYear+"</td>" +
							"<td>"+result.studentdetailslist[i].studentAdmissionNo+"</td>" +
							"<td>"+result.studentdetailslist[i].studentFullName+"</td>" +
							"<td>"+result.studentdetailslist[i].classSectionId+"</td>" +
							"<td>"+result.studentdetailslist[i].dateofBirth+"</td>" +
							"<td>"+result.studentdetailslist[i].studentStatus+"</td>" +
							"</tr>");
				}
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='7'>NO Records Found</td></tr>");
			}
			
			paginationforstudent($("#show_per_page").val(),totalcount);
			$(".numberOfItem").text("   No. of Records "+totalcount+".");
		}
	});
}
function paginationforstudent(list,no_of_items) {

	
	
	
	var show_per_page = list;
    var number_of_items = no_of_items;
   
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
  

}

function go_to_page(page_num) {
    var show_per_page = parseInt($('#show_per_page').val(), 0);

    start_from = page_num * show_per_page;

    end_on = start_from + show_per_page;
   	
   

    
    locationId=$("#locationname").val();
	if($("#locationname").val()==""){
		locationId="all";
	}
	academicYear=$("#Acyearid").val();
	if($("#Acyearid").val()==""){
		academicYear="all";
	}
	classId=$("#classname").val();
	if($("#classname").val()==""){
		classId="all";
	}
	divisionId=$("#sectionid").val();
	if($("#sectionid").val()==""){
		divisionId="all";
	}
	searchTerm=$("#searchvalue").val();
	if($("#searchvalue").val()==""){
		searchTerm="all";
	}

	
	var start=(Number(page_num+1)*Number($("#show_per_page").val())-Number($("#show_per_page").val()));
	if(start<0){
		start=0;
	}
	datalist={
			"locationId" : locationId,
			"academicYear" : academicYear,
			"classId" : classId,
			"divisionId" : divisionId,
			"searchTerm" : searchTerm,
			"show_per_page":$("#show_per_page").val(),
			"startPoint":start,
	},
	
	$.ajax({
		
		type : 'POST',
		url : "studentRegistration.html?method=getStudentDetailsByJsInRegistration",
		data : datalist,
		async : false,
		success : function(response) {
			var len=$("#show_per_page").val();
			var result = $.parseJSON(response);
			$('#allstudent tbody').empty();
			
			
			if(result.studentdetailslist.length>0){
				
				if($("#show_per_page").val() > result.studentdetailslist.length){
					len=result.studentdetailslist.length;
				}
				
				for(var i=0;i<len;i++){
					$('#allstudent tbody').append("<tr>" +
							"<td><input type='radio' name='selectBox' id='selectBox' value='"+result.studentdetailslist[i].studentId+" "+result.studentdetailslist[i].academicYearId+"'></td>" +
							"<td>"+result.studentdetailslist[i].academicYear+"</td>" +
							"<td>"+result.studentdetailslist[i].studentAdmissionNo+"</td>" +
							"<td>"+result.studentdetailslist[i].studentFullName+"</td>" +
							"<td>"+result.studentdetailslist[i].classSectionId+"</td>" +
							"<td>"+result.studentdetailslist[i].dateofBirth+"</td>" +
							"<td>"+result.studentdetailslist[i].studentStatus+"</td>" +
							"</tr>");
				}
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='7'>NO Records Found</td></tr>");
			}
			
			
		
		}
	});
	$('.page[longdesc=' + page_num + ']').addClass('active').siblings('.active').removeClass('active');

    $('#current_page').val(page_num);
	 $(".controls").find(".page").hide();
	    $(".controls").find(".active").show();
	    $(".controls").find(".active").prev().prev().show();	
	    $(".controls").find(".active").prev().show();	
	    $(".controls").find(".active").next().show();	
	    $(".controls").find(".active").next().next().show();
	
}
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

			$('#classname').append('<option value="">' + "ALL"	+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

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
			"locationId":$('#locationname').val()
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
			
			for ( var j = 0; j < result.sectionList.length; j++) {

				$('#sectionid').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});
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