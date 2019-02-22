$(document).ready(function(){
	
	$(".class").click(function(){
		$("#classOne").slideToggle();
	});
	
	flag = false;
	flag1 =false;
	flag2 = false;
	var accyear = $("#hiddenaccyear").val();
	var locid = $("#hiddenlocid").val();
	
	checkselection(accyear,locid);
	if(flag1 == true){
		if($("#filter2").val()!="" && $("#filter2").val()!== null){
			$("#selections").show();
			$("#selection option[value=" +$("#filter2").val()+ "]").attr('selected','true');
		}
	}
	
	$(".studenttable tbody tr").click(function(){
		
		var accyear = $("#hiddenaccyear").val();
		var locid = $("#hiddenlocid").val();
		var total = $(this).find(".total").text();
		var allocated = $(this).find(".allocated").text();
		var classId = $( this ).find(".classId").attr("id");
		var status = $(this).find(".status").text();
		var selection = null;
		if(flag2 == true){
			if($("#selection").val() == "" || $("#selection").val() == null){
				$(".errormessagediv").show();
				$(".validateTips").text("Field Required - Filter");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
			}else{

				
				if($("#filter").val() != "" && $("#filter").val() != null){
					selection = $("#filter").val();
					selection1 = $("#filter").val();
				}
				
				else if($("#selection").val() != "" && $("#selection").val() != null){
					selection =$('#selection :selected').text();
					selection1 =  $("#selection").val();
				}

				if(status == "Set"){
					
					generatehouseid = $(this).find(".genehouid").attr("class").split[0];
					alert(generatehouseid);
					
					if(selection == "Name Wise"){
					window.location.href="houseSettings.html?method=getHouseSettingStudentWise&accyear="+accyear+
					"&locid="+locid+"&classId="+classId+"&total="+total+"&allocated="+allocated;
					}
					else if(selection == "Name Wise Desc"){
						window.location.href="houseSettings.html?method=displayHouseStuNameWise&accyear="+accyear+
						"&locid="+locid+"&classId="+classId+"&total="+total+"&allocated="+allocated;
					}else if(selection == "By Admission No."){
						window.location.href="houseSettings.html?method=displayHouseSettingAdminoWise&accyear="+accyear+
						"&locid="+locid+"&classId="+classId+"&total="+total+"&allocated="+allocated+"&selection1="+selection1;
					}else if(selection == "By Admission No. Desc"){
						window.location.href="houseSettings.html?method=displayHouseSettingAdminodescWise&accyear="+accyear+
						"&locid="+locid+"&classId="+classId+"&total="+total+"&allocated="+allocated+"&selection1="+selection1;
					}else if(selection == "By Admission No. Even"){
						window.location.href="houseSettings.html?method=displayHouseSettingAdminoevenWise&accyear="+accyear+
						"&locid="+locid+"&classId="+classId+"&total="+total+"&allocated="+allocated+"&selection1="+selection1;
					}else if(selection == "By Admission No. Odd"){
						window.location.href="houseSettings.html?method=dispalyHouseSettingAdminooddWise&accyear="+accyear+
						"&locid="+locid+"&classId="+classId+"&total="+total+"&allocated="+allocated+"&selection1="+selection1;
					}else if(selection == "Name Wise Desc"){
						window.location.href="houseSettings.html?method=displayHouseSettingnamedescWise&accyear="+accyear+
						"&locid="+locid+"&classId="+classId+"&total="+total+"&allocated="+allocated+"&selection1="+selection1;
					}
				}
				else{
						if(selection == "Name Wise"){
						window.location.href="houseSettings.html?method=HouseSettingStudentWise&accyear="+accyear+
						"&locid="+locid+"&classId="+classId+"&total="+total+"&allocated="+allocated+"&selection1="+selection1;
						}
						else if(selection == "By Admission No."){
							window.location.href="houseSettings.html?method=getHouseSettingAdminoWise&accyear="+accyear+
							"&locid="+locid+"&classId="+classId+"&total="+total+"&allocated="+allocated+"&selection1="+selection1;
						}else if(selection == "By Admission No. Desc"){
							window.location.href="houseSettings.html?method=getHouseSettingAdminodescWise&accyear="+accyear+
							"&locid="+locid+"&classId="+classId+"&total="+total+"&allocated="+allocated+"&selection1="+selection1;
						}else if(selection == "By Admission No. Even"){
							window.location.href="houseSettings.html?method=getHouseSettingAdminoevenWise&accyear="+accyear+
							"&locid="+locid+"&classId="+classId+"&total="+total+"&allocated="+allocated+"&selection1="+selection1;
						}else if(selection == "By Admission No. Odd"){
							window.location.href="houseSettings.html?method=getHouseSettingAdminooddWise&accyear="+accyear+
							"&locid="+locid+"&classId="+classId+"&total="+total+"&allocated="+allocated+"&selection1="+selection1;
						}else if(selection == "Name Wise Desc"){
							window.location.href="houseSettings.html?method=getHouseSettingnamedescWise&accyear="+accyear+
							"&locid="+locid+"&classId="+classId+"&total="+total+"&allocated="+allocated+"&selection1="+selection1;
						}
				}
			
			}
		}
		else{
	
		if($("#filter").val() != "" && $("#filter").val() != null){
			selection = $("#filter").val();
			selection1 = $("#filter").val();
		}
		
		else if($("#selection").val() != "" && $("#selection").val() != null){
			selection =$('#selection :selected').text();
			selection1 =  $("#selection").val();
		}
		
		if(status == "Set"){
			
			generatehouseid = $(this).find(".genehouid").attr("id");
			
			if(selection == "Name Wise"){
				window.location.href="houseSettings.html?method=getHouseSettingStudentWise&accyear="+accyear+
				"&locid="+locid+"&classId="+classId+"&total="+total+"&allocated="+allocated+"&generatehouseid="+generatehouseid;
			}
				else if(selection == "Name Wise Desc"){
					window.location.href="houseSettings.html?method=displayHouseStuNameWise&accyear="+accyear+
					"&locid="+locid+"&classId="+classId+"&total="+total+"&allocated="+allocated;
				}else if(selection == "By Admission No."){
					window.location.href="houseSettings.html?method=displayHouseSettingAdminoWise&accyear="+accyear+
					"&locid="+locid+"&classId="+classId+"&total="+total+"&allocated="+allocated+"&selection1="+selection1;
				}else if(selection == "By Admission No. Desc"){
					window.location.href="houseSettings.html?method=displayHouseSettingAdminodescWise&accyear="+accyear+
					"&locid="+locid+"&classId="+classId+"&total="+total+"&allocated="+allocated+"&selection1="+selection1;
				}else if(selection == "By Admission No. Even"){
					window.location.href="houseSettings.html?method=displayHouseSettingAdminoevenWise&accyear="+accyear+
					"&locid="+locid+"&classId="+classId+"&total="+total+"&allocated="+allocated+"&selection1="+selection1;
				}else if(selection == "By Admission No. Odd"){
					window.location.href="houseSettings.html?method=dispalyHouseSettingAdminooddWise&accyear="+accyear+
					"&locid="+locid+"&classId="+classId+"&total="+total+"&allocated="+allocated+"&selection1="+selection1;
				}else if(selection == "Name Wise Desc"){
					window.location.href="houseSettings.html?method=displayHouseSettingnamedescWise&accyear="+accyear+
					"&locid="+locid+"&classId="+classId+"&total="+total+"&allocated="+allocated+"&selection1="+selection1;
				}
			}
		else{
				if(selection == "Name Wise"){
				window.location.href="houseSettings.html?method=HouseSettingStudentWise&accyear="+accyear+
				"&locid="+locid+"&classId="+classId+"&total="+total+"&allocated="+allocated+"&selection1="+selection1;
				}
				else if(selection == "By Admission No."){
					window.location.href="houseSettings.html?method=getHouseSettingAdminoWise&accyear="+accyear+
					"&locid="+locid+"&classId="+classId+"&total="+total+"&allocated="+allocated+"&selection1="+selection1;
				}else if(selection == "By Admission No. Desc"){
					window.location.href="houseSettings.html?method=getHouseSettingAdminodescWise&accyear="+accyear+
					"&locid="+locid+"&classId="+classId+"&total="+total+"&allocated="+allocated+"&selection1="+selection1;
				}else if(selection == "By Admission No. Even"){
					window.location.href="houseSettings.html?method=getHouseSettingAdminoevenWise&accyear="+accyear+
					"&locid="+locid+"&classId="+classId+"&total="+total+"&allocated="+allocated+"&selection1="+selection1;
				}else if(selection == "By Admission No. Odd"){
					window.location.href="houseSettings.html?method=getHouseSettingAdminooddWise&accyear="+accyear+
					"&locid="+locid+"&classId="+classId+"&total="+total+"&allocated="+allocated+"&selection1="+selection1;
				}else if(selection == "Name Wise Desc"){
					window.location.href="houseSettings.html?method=getHouseSettingnamedescWise&accyear="+accyear+
					"&locid="+locid+"&classId="+classId+"&total="+total+"&allocated="+allocated+"&selection1="+selection1;
				}
		}
	}
	});

	$("#back").click(function(){
		var accyear = $("#hiddenaccyear").val();
		var locid = $("#hiddenlocid").val();
		
		var selection = null;
		var selection1 = null;
		if($("#filter").val() != "" && $("#filter").val() != null){
			selection = $("#filter").val();
			selection1 =  $("#filter1").val();
		}
		else if($("#selection").val() != "" && $("#selection").val() != null){
			selection =$('#selection :selected').text();
		    selection1 =$("#filter1").val();
		}

		if(selection == "Name Wise"){
			window.location.href="houseSettings.html?method=HouseSettingClassWise&accyear="+accyear+"&locid="+locid+"&selection1="+selection1;
		}else if(selection == "Name Wise Desc"){
			window.location.href="houseSettings.html?method=ordernameDescWise&accyear="+accyear+"&locid="+locid+"&selection1="+selection1;
		}else if(selection == "By Admission No. Odd"){
			window.location.href="houseSettings.html?method=orderadmiOddWise&accyear="+accyear+"&locid="+locid+"&selection1="+selection1;
		}else if(selection == "By Admission No."){
			window.location.href="houseSettings.html?method=houseadmiWise&accyear="+accyear+"&locid="+locid+"&selection1="+selection1;
		}else if(selection == "By Admission No. Desc"){
			window.location.href="houseSettings.html?method=orderadmiDescWise&accyear="+accyear+"&locid="+locid+"&selection1="+selection1;
		}else if(selection == "By Admission No. Even"){
			window.location.href="houseSettings.html?method=orderadmiEvenWise&accyear="+accyear+"&locid="+locid+"&selection1="+selection1;
		}
	});
	
	$("#house").click(function(){
		
		var selection = null;
		if($("#filter").val() != "" && $("#filter").val() != null)
			selection = $("#filter").val();
		else if($("#selection").val() != "" && $("#selection").val() != null){
			selection =$('#selection :selected').text();
		}
		
		var noofstudents = $("#noofstudents").val();
		
		if(noofstudents == 0){
			flag = false;
			 $(".errormessagediv").show();
				$(".validateTips").text("No Students Found");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
		}
		else{
		datalist = {
				"accyear" :$("#hiddenaccyear").val(),
				"locid" : $("#hiddenlocid").val(),
				"noofstudents" : $("#noofstudents").val(),
				"hiddenclassname" : $("#hiddenclassname").val(),
				"selection" : selection
		};
		
		$.ajax({
			type : "POST",
			url : "houseSettings.html?method=checkHousing",
			data : datalist,
			async:false,
			success : function(data){
				var result = $.parseJSON(data);
				if(result.status == "true"){
					flag = false;
					 $(".errormessagediv").show();
						$(".validateTips").text("Houses are not configured for the selected Academic Year and Location");
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
						return false;
				}else{
					flag = true;
					$.ajax({
						type : "POST",
						url : "houseSettings.html?method=generateHousing",
						data : datalist,
						async:false,
						success : function(data){
							var result = $.parseJSON(data);
								$(".housename").each(function(e){
									$(this).find("span").text(result.HousesList[e].housename);
									$(this).find(".houseid").val(result.HousesList[e].houseid);
								});
						}
					});
				}
				
			}
		});
	}
	});
	
	$("#save").click(function(){
		
	      studid=[];
		  classid=[];
		  sectionid=[];
		  houseid=[];
		  
		  if(flag){
		 

       $("#dialog").dialog("open");
       
	     
	}else{
		 $(".errormessagediv").show();
			$(".validateTips").text("Generate House");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
	}
	});
	
	$("#dialog").dialog({
		autoOpen  : false,
	    modal     : true,
	    title     : "Add House Details",
	    buttons   : {
	    	"Save": {
	    		text: 'Yes',
	    		
	    		 click: function() {
	    			 $('.houseid').each(function() {
	    					var getData = $(this).val();
	    					houseid.push(getData);
	    			  });
	    			  $('.stuId').each(function(){
	    					var getData = $(this).attr("id");
	    					studid.push(getData);
	    			  });
	    			  
	    			  $('.classId').each(function()  {
	    					var getData = $(this).val();
	    					classid.push(getData);
	    					
	    			  });
	    			  $(".sectionid").each(function(){
	    					var getData=$(this).attr("id");
	    					sectionid.push(getData);
	    			  });
	    			
	    			var hiddenlocid = $("#hiddenlocid").val();
	    			var hiddenaccyear = $("#hiddenaccyear").val();
	    			var hiddenclassname = $("#hiddenclassname").val();
	    			var filter1 = $("#filter").val();
	    			
	    	       datalist = {
	    					  "hiddenlocid":hiddenlocid,
	    					  "hiddenaccyear":hiddenaccyear,
	    					  "hiddenclassid":hiddenclassname,
	    					  "houseid" :houseid.toString(),
	    					  "studid" : studid.toString(),
	    					  "classid" : classid,
	    					  "sectionid" :sectionid.toString(),
	    					  "noofstudents" : $("#noofstudents").val(),
	    					  "filter1" :filter1
	    			  };
	    	       	savegenerateHouseDetails();
	    			 $(this).dialog('close');
	    		 }
	    	},
	    	'No' : function() {
                $(this).dialog('close');
	    	}
	    }
	});
});


function checkselection(accyear,locid){
	
	$.ajax({
		
		type : "POST",
		url : "houseSettings.html?method=checkselection",
		data : {"accyear":accyear,"locid":locid},
		async : false,
		success : function(data){
			var result = $.parseJSON(data);
			if(result.status == "false"){
				$(".selections").show();
				flag1 = true;
				flag2 = true;
			}else{
				$(".filters").show();
				$("#filter").val(result.status);
			}
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

function savegenerateHouseDetails(){
	
	 $.ajax({
			type : "POST",
			url : "houseSettings.html?method=savegenerateHouseDetails",
			data : datalist,
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);
				if(result.status =="success" ) {
					 
					$(".successmessagediv").show();
					$(".validateTips").text("Adding Record Progressing... ");
				
					setTimeout(function(){
						$(".successmessagediv").show();
						window.location.href="houseSettings.html?method=HouseSettingClassWise&accyear="+$("#hiddenaccyear").val()+"&locid="+$("#hiddenlocid").val(); 
               },3000);
				}
			}
		});
	
}
