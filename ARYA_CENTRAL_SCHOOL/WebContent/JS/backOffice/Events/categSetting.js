$(document).ready(function(){
	
	$("#eventName").change(function(){
		getClassList();
	});
	getCategorySettingList();
	$("#eventNameList").change(function(){
		getcategoruName();
		var eventId = $("#eventNameList").val();
		getCategorySettingList();
	});
	$("#categoryNameList").change(function(){
		getCategorySettingList();
	});

	$("#addgroup").click(function(){
		$("#categoryHidden").val("");
		$("#eventCatSetting").dialog("open");
	});



	$("#eventCatSetting").dialog({
		autoOpen  : false,
		maxWidth  :	950,
		maxHeight : 500,
		width     : 700,
		height    : 400,
		modal     : true,
		title     : "Category Setting",
		buttons   : {
			'Save'  : function() {
				var pointer=$(this);
				saveCategory(pointer);//saving the data into database:define
			},
			'Close' : function() {
				$(".errormessagediv").hide();
				$('input:text').val("");
				$('select').val("");
				$("#eventIdHidden").val("");
				$(this).dialog('close');

				$(".form-control").css({
					"border":"1px solid #ccc",
					"background-color":"#fff"
				});
				$("#eventName option").show();
			}
		}
	});


	$("#deleteDialog").dialog({
		autoOpen: false,
		modal: true,					    
		title:'Category Details',
		buttons : {

			"Yes" : function() {
				$.ajax({
					type:'post',
					url:"EventsMenu.html?method=deleteCategory",
					data:{"id":id},
					success:function(data){
						var result=$.parseJSON(data);
						if(result.status=="true"){
							$(".successmessagediv").show();
							$(".validateTips").text("Delete Record Progressing...");
							$('.successmessagediv').delay(3000).slideUp();
							getCategorySettingList();
						}
						else if(result.status == "false"){
							$(".errormessagediv").show();
							$(".validateTips").text("Selected Category is Mapped Cannot be Deleted");
							$('.errormessagediv').delay(3000).slideUp();
							getCategorySettingList();
						}
					}
				});
				$(this).dialog('close');
			},

			"No" : function() {
				$(this).dialog('close');

			}
		}
	});

});//JQUERY ENDS


function saveCategory(pointer){
		var classList=[];
		var eventName = $("#eventName").val();
		var categoryName = $("#categoryName").val();
		classList.push($("#classList").val());
		var eventIdHidden =$("#eventIdHidden").val();
		var categoryHidden =$("#categoryHidden").val();
		var categoryNameHidden =$("#categoryNameHidden").val();
	
	if(eventName=="" || eventName==undefined){
		showError("#eventName","Enter Event Name");
		return false;
	}
	if(categoryName=="" || categoryName==undefined){
		showError("#categoryName","Enter Category Name");
		return false;
	}
	if(classList=="" || classList==undefined){
		showError("#classList","Select Class Name");
		return false;
	}

	datalist = {
			"eventName":eventName,
			"categoryName":categoryName,
			"classList":classList.toString(),
			"eventIdHidden":eventIdHidden,
			"categoryHidden":categoryHidden,
			"categoryNameHidden":categoryNameHidden,
	};
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=saveCategory",
		data:datalist,
		async:false,
		success:function(data){
			var result = $.parseJSON(data);
			if(result.status=="true"){
				$(".errormessagediv").hide();
				$(".successmessagediv").show();
				$(".validateTips").text("Add Record Progressing");
				$(".successmessagediv").delay(3000).fadeOut();
				pointer.dialog('close');
				$('input:text').val("");
				$('select').val("");

				$("#eventCatSetting").dialog('close');
				$("#eventName option").show();
				getCategorySettingList();
			}
			else if(result.status=="dupCategoryName"){
				$(".succesmessagediv").hide();
				$(".errormessagediv").show();
				showError("#categoryName","Category Name already Exists");
				$("#categoryName").val("");
				$("#eventIdHidden").val("");


			}
			else if(result.status=="UpdateTrue"){
				$('.errormessagediv').hide();
				$(".successmessagediv").show();
				$(".validateTips").text("Update Record Progressing...");
				$(".successmessagediv").delay(3000).fadeOut();
				$('input:text').val("");
				$('select').val("");
				$("#eventIdHidden").val("");
				$("#eventCatSetting").dialog('close');
				$("#eventName option").show();
				getCategorySettingList();
			}
			else{
				$(".succesmessagediv").hide();
				$(".errormessagediv").show();
				$(".validateTips").text("fail to add Record");
				$(".errormessagediv").delay(3000).fadeOut();
			}
		}
	});
}

function getcategoruName(){
	var id= $("#eventNameList").val();
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getCategoryName",
		data :{"id":id,
			"flag":"onLoad",
		},
		async:false,
		success:function(data){
			var result=$.parseJSON(data);

			$("#categoryNameList").empty();
			$("#categoryNameList").append("<option value=''>----------Select---------</option>");

			for(var i=0;i<result.data.length;i++){

				$("#categoryNameList").append("<option value='"+result.data[i].categoryId+"'>" 
						+result.data[i].categoryName+ "</option>");
			}
		}
	});
}

function getCategorySettingList(){

	var eventId=$("#eventNameList").val();
	var catId=$("#categoryNameList").val();
	if(catId==""){
		catId="all";
	}
	if(eventId==""){
		eventId="all";
	}
	datalist= {
			"eventId":eventId,
			"catId":catId,
	};
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getCategorySettingList",
		data:datalist,
		async:false,
		success:function(data){
			var result =$.parseJSON(data);

			$("#allstudent tbody").empty();
			if(result.data.length>0){
				for(var i=0; i<result.data.length; i++){
					$("#allstudent tbody").append("<tr id='"+result.data[i].categoryId+"'>" 
							+"<td>"+(i+1)+"</td>"
							+"<td>"+result.data[i].eventName+"</td>"
							+"<td>"+result.data[i].categoryName+"</td>"
							+"<td>"+result.data[i].classList+"</td>" 
							+"<td style='width:20px;'><span class='glyphicon glyphicon-trash deleteRow'></span></td>"
							+"</tr>");
				}
				deleteRow();
				rowClickable();
				$("#allstudent tr td").mouseenter(function(){
					$(this).parent("tr").find("td").not("td:last-child").css({
						"background":"#9CDDE3"
					});
				});
				$("#allstudent tr td").mouseleave(function(){
					$(this).parent("tr").find("td").not("td:last-child").css({
						"background":"transparent"
					});
				});
			}else{
				$("#allstudent tbody").append("<tr><td colspan='4'>No Record Found</td></tr>");
			}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No of Records "+result.data.length+".");
			pagination(50);
		}
	});
}


function deleteRow(){
	$(".deleteRow").click(function(){
		id = $(this).closest("tr").attr("id");
		$("#deleteDialog").dialog("open");
		$("#deleteDialog").empty();
		$("#deleteDialog").append("<p>Are you sure to delete?</p>");
	});
}

function rowClickable(){
	
	$("#allstudent tbody tr td").not("#allstudent tbody tr td:last-child").click(function(){
		$("#eventCatSetting").dialog("open");
		id = $(this).closest("tr").attr("id");
		$.ajax({
			type:'post',
			url:"EventsMenu.html?method=getDataForUpdateCategorySetting",
			data :{"id":id},
			async:false,
			success:function(data){
				var result=$.parseJSON(data);
				$("#eventName option").hide();
				$("#eventName").val(result.DataList[0].eventId);
				getClassList();
				$("#categoryName").val(result.DataList[0].categoryName);
				$("#categoryNameHidden").val(result.DataList[0].categoryName);
				$("#categoryHidden").val(result.DataList[0].categoryId);
				$("#classList").val(result.DataList[0].classList);
				$("#eventIdHidden").val(result.DataList[0].eventId);
			}
		});
	});
}

function getClassList(){
	var eventName= $("#eventName").val();
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getClassListforCategory",
		data :{"eventName":eventName},
		async:false,
		success:function(data){
			var result=$.parseJSON(data);
			$("#classList").empty();
			//$("#classList").append("<option value=''>----------Select----------</option>");
			for(var i=0;i<result.data.length;i++){
				$("#classList").append("<option value='"+result.data[i].classId+"'>"
						+result.data[i].className+ "</option>");
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

