$(document).ready(function(){	
	
	$(".class").click(function(){
		$("#classOne").slideToggle();
	});
	
	var hacademicyear=$('#hiddenaccyear').val();
	$("#accyear").val(hacademicyear);
	
	var year =$("#hiddenlocationid").val();
	$("#location").val(year);
	generatehouseSettings($("#location"),$("#accyear"));
	
	$("#location").change(function(){
	generatehouseSettings($(this),$("#accyear"));
	
	$("#allstudent tr").click(function(){
	var accyearid = $(this).find(".accyearid").attr("id");
	var locid= $(this).find(".locname").attr("id");
	var selection = $("#selection").val();
		window.location.href="houseSettings.html?method=HouseSettingClassWise&accyear="+accyearid+"&locid="+locid;
});
});
	
	$("#accyear").change(function(){
		generatehouseSettings($("#location"),$(this));
		
		$("#allstudent tr").click(function(){
		var accyearid = $(this).find(".accyearid").attr("id");
		var locid= $(this).find(".locname").attr("id");
		var selection = $("#selection").val();
			window.location.href="houseSettings.html?method=HouseSettingClassWise&accyear="+accyearid+"&locid="+locid;
	});
	});
	
	$("#allstudent tr").click(function(){
		
		var accyearid = $(this).find(".accyearid").attr("id");
		var locid= $(this).find(".locname").attr("id");
		var selection = $("#selection").val();
			window.location.href="houseSettings.html?method=HouseSettingClassWise&accyear="+accyearid+"&locid="+locid;
		
		});
	
});

function generatehouseSettings(pointer,pointer1){

	$.ajax({
			
			type : "POST",
			url : "houseSettings.html?method=getgenratehouseSettings",
			data : {"location":pointer.val(),"accyear":pointer1.val()},
			async : false,
			success : function(data){
				var result = $.parseJSON(data);
				
				$("#markstable").empty();
				$("#markstable").append("<table class='table' id='allstudent' width='100%'>"+"<tr>"+
						"<th>Sl.No</th>" +
						"<th>Academic Year</th>" +
						"<th>Location</th>" +
						"<th>Status</th>"+
						"</tr>" +
						"</table>"
						);
				if(result.AccYearList.length>0){
					
				
				for(var i=0;i<result.AccYearList.length;i++){
					
					$("#markstable #allstudent").append(
							"<tr>"+
							"<td>"+result.AccYearList[i].sno1+"</td>"+
							"<td class='accyearid' id='"+result.AccYearList[i].accyearid+"'>"+result.AccYearList[i].accyear+"</td>"+
							"<td  class='locname' id='"+result.AccYearList[i].locationid+"'>"+result.AccYearList[i].locname+"</td>"+
							"<td><span class="+result.AccYearList[i].status+">"+result.AccYearList[i].status+"</span></td>"
							+"</tr>"
					);
					
				}
				}
				else{
					$("#markstable #allstudent").append("<tr><td colspan='4'>NO Records Found</td></tr>");
				}
				
				$("#allstudent").after("<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select><span class='numberOfItem'></span></div><div class='pagination pagelinks'></div>");
				
				$(".numberOfItem").empty();
				$(".numberOfItem").append("  No. of Records  "+result.AccYearList.length);
				pagination(20);
				$("#show_per_page").change(function(){
					pagination($(this).val());
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
