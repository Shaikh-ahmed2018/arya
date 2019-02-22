$(document).ready(function(){
	
	
	$('#print').click(function(){
		var student_Id = $(".itcollection:checked").val().split(" ");
	    var StuId=student_Id[0];
	    var accyear=student_Id[1];
	    var locid=student_Id[2];
		
		$.ajax({
			
			type:"post",
			url:'reportaction.html?method=printITFeeCollectionReport&StuId='+StuId+'&accyear='+accyear+'&locid='+locid,
			success:function(data){
				
			}
		});
	});
	$("#Acyearid").val($("#hacademicyaer").val());
	
	

	$("#locationname").change(function(){
		getClassList();
		
		
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
		if(e.keyCode=="13"){
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

			$('#classname').append('<option value="All">' + "----------Select----------"	+ '</option>');

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
			
			for ( var j = 0; j < result.sectionList.length; j++) {

				$('#sectionid').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});
	}

function searchList(){

	var searchname = $("#searchvalue").val().trim();
	
	var locationid=$("#hschoolLocation").val();
	var accyear=$("#hacademicyaer").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	
   datalist = {
			
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"searchname" :searchname,
		}, $.ajax({
			type : 'POST',
			url : "studentRegistration.html?method=getStudentSearchByFeeCollection",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				    var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					for(var i=0;i<result.SearchList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td class='"+result.SearchList[i].studentId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].locationId+" "+"studentid"+"'>"+result.SearchList[i].sno+"</td>"
							+"<td> "+result.SearchList[i].academicYear+" </td>" 
							+"<td> "+result.SearchList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.SearchList[i].studentFullName+"</td>"
							+"<td class='classid' id='"+result.SearchList[i].classDetailId+"'> "+result.SearchList[i].classname+"-" +result.SearchList[i].sectionnaem+" </td>"
							+"</tr>");
					}	
			}
		});
}

function changeAccYear(){
	var locationId = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=searchAllAcademicYearFeeDetails",
		data : {
			"locationId" :locationId,
			"accyear" :accyear,				
		},
		async : false,
		
		success : function(response) {
			 
			var result = $.parseJSON(response);
				$("#allstudent tbody").empty();
				for(var i=0;i<result.SearchList.length;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td class='"+result.SearchList[i].academicYearId+" "+result.SearchList[i].locationId+" "+"studentid"+"'>"+result.SearchList[i].sno+"</td>" 
						+"<td> "+result.SearchList[i].academicYear+" </td>"
						+"<td> "+result.SearchList[i].studentAdmissionNo+" </td>"
						
						+"<td class='studid' id='"+result.SearchList[i].studentId+"'> "+result.SearchList[i].studentFullName+"</td>"
						+"<td class='classid' id='"+result.SearchList[i].classDetailId+"'> "+result.SearchList[i].classname+"-" +result.SearchList[i].sectionnaem+" </td>"
						+"</tr>");
				}	
		}
	});
}
function getStudentList(locationid,accyear,classname){
	
	datalist = {
			
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			
		}, $.ajax({
			type : 'POST',
			url : "adminMenu.html?method=getStudentDetailsLByFeeFilter",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					for(var i=0;i<result.getClassWiseList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td class='"+result.getClassWiseList[i].studentId+" "+result.getClassWiseList[i].academicYearId+"  "+result.getClassWiseList[i].locationId+" "+"studentid"+"'>"+result.getClassWiseList[i].count+"</td>" 
							+"<td> "+result.getClassWiseList[i].academicYear+" </td>"
							+"<td> "+result.getClassWiseList[i].studentAdmissionNo+"</td>"
							+"<td class='studid' id='"+result.getClassWiseList[i].studentId+"'> "+result.getClassWiseList[i].studentFullName+"</td>"
							+"<td class='classid' id='"+result.getClassWiseList[i].classDetailId+"'> "+result.getClassWiseList[i].classname+"-" +result.getClassWiseList[i].sectionnaem+" </td>"
							+"</tr>");
					}	
			}
		});
	}



function getStudentListBySection(locationid,accyear,classname,sectionid){
	
	datalist = {
			
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
		}, $.ajax({
			type : 'POST',
			url : "adminMenu.html?method=getStudentListByFeeSection",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					for(var i=0;i<result.getSectionWiseList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td class='"+result.getSectionWiseList[i].studentId+" "+result.getSectionWiseList[i].academicYearId+" "+result.getSectionWiseList[i].locationId+" "+"studentid"+"'>"+result.getSectionWiseList[i].count+"</td>" 
							+"<td> "+result.getSectionWiseList[i].academicYear+" </td>"
							+"<td> "+result.getSectionWiseList[i].studentAdmissionNo+"</td>"
							+"<td> "+result.getSectionWiseList[i].studentFullName+" </td>"
							+"<td class='classid' id='"+result.getSectionWiseList[i].classDetailId+"'> "+result.getSectionWiseList[i].classname+"-" +result.getSectionWiseList[i].sectionnaem+" </td>"
							+"</tr>");
					}	
			}
		});
	}



function pagination(list) {

	
	
	
	var show_per_page = list;
    var number_of_items = $('#allstudent tbody tr').length;
   
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
   
    $('#allstudent tbody tr').css('display', 'none');
    $('#allstudent tbody tr').slice(0, show_per_page).css('display', 'table-row');

	
	

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
    $('#allstudent tbody tr').css('display', 'none').slice(start_from, end_on).css('display', 'table-row');

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
function getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm){
	datalist={
			"locationId" : locationId,
			"academicYear" : academicYear,
			"classId" : classId,
			"divisionId" : divisionId,
			"searchTerm" : searchTerm
	},
	
	$.ajax({
		
		type : 'POST',
		url : "studentRegistration.html?method=studentListByJs",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			$('#allstudent tbody').empty();
			if(result.studentdetailslist.length>0){
				for(var i=0;i<result.studentdetailslist.length;i++){
					$('#allstudent tbody').append(
							"<tr>" +
							"<td>"+"<input type='radio' name='radio' class='itcollection' value='"+result.studentdetailslist[i].studentId+" "+result.studentdetailslist[i].academicYearId+" "+result.studentdetailslist[i].locationId+"'>"+"</td>" +
							"<td>"+result.studentdetailslist[i].academicYear+"</td>" +
							"<td>"+result.studentdetailslist[i].studentAdmissionNo+"</td>" +
							"<td>"+result.studentdetailslist[i].studentFullName+"</td>" +
							"<td>"+result.studentdetailslist[i].classSectionId+"</td>" +
							"</tr>");
				}
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.studentdetailslist.length);
				pagination(100);
				$("#ShowPerPage").change(function(){
					pagination($(this).val());	
				});
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='5'>NO Records Found</td></tr>");
			}
		}
	});
}

