var no=0;

$(document).ready(function(){
	var currentEletd;
	//onchange functions
	$("#eventName").change(function(){
		getcategoryName();
		getProgramName();
		getstageName();
		if($("#progName").val().length>0)
			getChestNoForMarksEntry();
	});
	$("#categoryName").change(function(){
		getProgramName();
		if($("#progName").val().length>0)
		getChestNoForMarksEntry();
		
	});
	$("#progName").change(function(){
		getChestNoForMarksEntry();
		
	});

	
	

$("#save").click(function(){
	
	$(".successmessagediv").show();
	$(".errormessagediv").hide();
	$(".validateTips").text("Record Validating please Wait...");
	$(this).hide();
	var accId = $("#hacademicyaer").val();
	var evId = $("#eventName").val();
	var catId = $("#categoryName").val();
	var progId = $("#progName").val(); 
	
	
	
	 var criteriaName=[];
	 var criteriaMarks =[];
	$("#judgemarks tbody").find(".abcrow").each(function(){
		criteriaName.push($(this).find("td").eq(1).text());
		//alert(criteriaName);
	});
	var chestNo =[];
	var marksObtained= [];
	$("#eventtotalmarks tbody tr").not(".saved,.notSaved").each(function(){
		chestNo.push($(this).attr('id'));
		
		criteriaMarks.push($(this).attr("class"));
		marksObtained.push($(this).find("td:nth-child(4)").text());
		
		//alert(chestNo);
	});
	//alert(criteriaMarks);
	datalist ={
			"accId":accId,
			"evId":evId,
			"catId":catId,
			"progId":progId,
			"chestNo":chestNo.toString(),
			"marksObtained":marksObtained.toString(),
			"criteriaName":criteriaName.toString(),
			"criteriaMarks":criteriaMarks.toString(),
	};
	if(chestNo.length>0){
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=saveMarksEntry",
		data:datalist,
		success:function(data){
			var result=$.parseJSON(data);
				if(result.status=="true"){
					$(".successmessagediv").show();
					$(".errormessagediv").hide();
					$(".validateTips").text("Adding Record Progressing...");
					$(".successmessagediv").delay(3000).fadeOut();
					setTimeout(function(){location.reload()},3000)
				}
				else{
					$("#save").show();
					$('.successmessagediv').hide();
					$(".errormessagediv").show();
					$(".validateTips").text("Fail to Add Record...");
					$(".errormessagediv").delay(3000).fadeOut();
			}//success
		
				getChestNoForMarksEntry();
		}
		});//ajax
	}
	else{
		$("#save").show();
		$('.successmessagediv').hide();
		$(".errormessagediv").show();
		$(".validateTips").text("No Record Found To Add...");
		$(".successmessagediv").delay(3000).fadeOut();
	}
});//end function	


$(".judge").keypress(function(e){
	if(e.which !=8 && e.which !=0 && (e.which < 48 || e.which > 57)){
		return false;
	}
});
});//end JQury

function addjudges(){
	for(var num=1;num<=$("#No").val();num++){
		$(".addjudges").append('<div class="form-group clearfix wrap"><label for="inputPassword" class="control-label col-xs-3 removeLabel'+num+'" style="text-align: right;">JUDGE '+num+'</label><div class="col-xs-7">'+
				'<input type="text" class="form-control Judge" id="judgeName'+num+'"/></div><span id="delete"  class="glyphicon glyphicon-trash" onclick="removeTrash('+num+')></div></span>');
	}
	/*num=$(".wrap").length;
	num++;
	if(num<=$("#No").val()){
		$(".addjudges ").append('<div class="form-group clearfix wrap"><label for="inputPassword" class="control-label col-xs-3 removeLabel'+num+'" style="text-align: right;">Judge'+num+'</label><div class="col-xs-7">'+
		'<input type="text" class="form-control" id="judgeName'+num+'"/></div><span id="delete" class="glyphicon glyphicon-trash" onclick="removeTrash('+num+')"></div></span>');
	}*/
}
/*function removeTrash(num){
	jQuery('#judgeName'+num).closest(".wrap").remove();
	num--;
}*/
function getcategoryName(){
	var id= $("#eventName").val();
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getCategoryName",
		data :{"id":id,
			"flag":"onLoad",
		},
		async:false,
		success:function(data){
			var result=$.parseJSON(data);

			$("#categoryName").empty();
			$("#categoryName").append("<option value=''>----------Select----------</option>");

			for(var i=0;i<result.data.length;i++){
				$("#categoryName").append("<option value='"+result.data[i].categoryId+"'>" 
						+result.data[i].categoryName+ "</option>");
			}
		}
	});
}

function getProgramName(){
	var evId = $("#eventName").val();
	var catId = $("#categoryName").val();
	datalist ={
			"catId":catId,
			"evId":evId,
			"flag":"onLoad",
	};
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getprogramName",
		async:false,
		data:datalist,
		success:function(data){
			var result = $.parseJSON(data);
			$("#progName").empty();
			$("#progName").append("<option value=''>----------Select----------</option>");
			for(var i=0;i<result.data.length;i++){
				$("#progName").append("<option value='"+result.data[i].progId+"'>" 
						+result.data[i].progName+"</option>");
			}
		}
	});
}

function getstageName(){
	var id = $("#eventName").val();
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getstageList",
		async:false,
		data:{"id":id,
		},
		success:function(data){
			var result = $.parseJSON(data);
			$("#stageName").empty();
			$("#stageName").append("<option value=''>----------Select----------</option>");
			for(var i=0;i<result.data.length;i++){
				$("#stageName").append("<option value='"+result.data[i].stageId+"'>" 
						+result.data[i].stageName+"</option>");
			}
		}
	});
}

function getChestNoForMarksEntry(){
	var evId = $("#eventName").val();
	var catId = $("#categoryName").val();
	var progId= $("#progName").val();

	datalist={
			"evId":evId,
			"catId":catId,
			"progId":progId,
	};
	$.ajax({
		type:"post",
		url:"EventsMenu.html?method=getChestNoForMarksEntry",
		data:datalist,
		async:false,
		success:function(data){
			var result=$.parseJSON(data);
			$("#eventtotalmarks tbody").empty();  
			if(result.data.length > 0){
				for(var i=0; i<result.data.length; i++){
					
					
					$("#eventtotalmarks tbody").append("<tr id='"+result.data[i].chestNumber+"'>" 
							+"<td>"+(i+1)+"</td>"
							+"<td>"+result.data[i].chestNumber+"</td>"
							+"<td>"+result.data[i].maxMarks+"</td>"
							+"<td>"+result.data[i].totNumber+"</td>"
							+"</tr>");
					if(result.data[i].totNumber>0){
						$("#"+result.data[i].chestNumber).addClass("saved");
					}
					else{
						$("#"+result.data[i].chestNumber).addClass("notSaved");
					}
				}
				getCriteriaForMarksEntry(result.data[0].judgeNos);
			}//end if{

			else{
				$("#eventtotalmarks tbody").append("<tr><td colspan='4'>No Record Found</td></tr>");
			}

			
		}
	});
}

function getCriteriaForMarksEntry(nuOfJudge){
	var evId = $("#eventName").val();
	var catId = $("#categoryName").val();
	var progId= $("#progName").val();

	datalist={
			"evId":evId,
			"catId":catId,
			"progId":progId,
	};
	$.ajax({
		type:"post",
		url:"EventsMenu.html?method=getCriteriaForMarksEntry",
		data:datalist,
		async:false,
		success:function(data){

			var result=$.parseJSON(data);
			len=result.data.length;
			$("#judgemarks thead").empty();
			$("#judgemarks thead").append("<tr><th>Sl no</th><th>Criteria</th></tr>");
			for(var l=1;l<=Number(nuOfJudge);l++){
				$("#judgemarks thead tr").append("<th>Judge"+l+"</th>");
			}
			$("#judgemarks tbody").empty();  
			if(result.data.length>0){
				for(var i=0; i<len; i++){	//calling the No of criteria dynamically by DB
					$("#judgemarks tbody").append("<tr id='"+result.data[i].criteriaId+"' class='abcrow abc"+i+"'>" 
							+"<td>"+(i+1)+"</td>"
							+"<td>"+result.data[i].criteria+"</td>"
							+"</tr>");
					                                                           
				}//for automatic appending textbox dynamically next to criteria class.
				
				
				
				$("#judgemarks tbody tr").each(function(){
					for(var l=1;l<=Number(nuOfJudge);l++){
						$(this).append("<td><input type='text' class='judge count"+l+"' readonly='readonly' /></td>");
					}
				});
				
				tfootClick();
			}
			
			else{
				$("#judgemarks tbody").append("<tr><td colspan='4'>No Record Found</td></tr>");
				}
			//to get the id n append to next table
			$("#eventtotalmarks tbody tr").click(function(){
				$("#judgemarks tfoot tr th:nth-child(1)").removeAttr("class");
				$("#judgemarks tfoot tr th:nth-child(1)").addClass($(this).attr("id"));
			$("#judgemarks tfoot tr th:nth-child(1)").text("Submit "+$(this).attr("id")+"'s Marks ");
			if($(this).attr("class")=="saved"){
				getCriteriaForMarksEntrySaved($(this).attr("id"));
				//$("#judgemarks tfoot").hide();
			}
			else{
				$(".judge").attr("readonly",false);
				$("#judgemarks tfoot").show();
			}
			
				
			});
		}
	});
}
function getCriteriaForMarksEntrySaved(chestno){
	var evId = $("#eventName").val();
	var catId = $("#categoryName").val();
	var progId= $("#progName").val();

	datalist={
			"evId":evId,
			"catId":catId,
			"progId":progId,
			"chestno":chestno,
	};
	$.ajax({
		type:"post",
		url:"EventsMenu.html?method=getCriteriaForMarksEntrySaved",
		data:datalist,
		async:false,
		success:function(data){

			var result=$.parseJSON(data);
			len=result.data.length;
			
				for(var j=0; j<len; j++){
					for(var k=0;k<result.data[j].eventRgoList.length;k++){
						$("#judgemarks tbody tr.abc"+j).find('.count'+(k+1)).val(result.data[j].eventRgoList[k].criteriaMarks);
					}
					
				}
				
		}
	});
}	

function tfootClick(){
	$("#submit").click(function(){
		totmark=0;
		var className ="";	
		$("#eventtotalmarks tbody tr[id='"+$("#judgemarks tfoot tr th:nth-child(1)").attr('class')+"']").removeAttr("class");
		$("#judgemarks tbody tr").each(function(){ 
			$(this).find(".judge").each(function(){
				if(this.value==""){
					this.value=0;
				}
				
				className =className+(this.value)+"-";
				totmark=totmark+Number(this.value);
			});
			className=className+":";
		});
		
		if(Number(totmark) > Number($("#eventtotalmarks tbody tr[id='"+$("#judgemarks tfoot tr th:nth-child(1)").attr('class')+"']").find("td:nth-child(3)").text())){
			
			$('.successmessagediv').hide();
			$(".errormessagediv").show();
			$(".validateTips").text("Not a Valid Input");
			$(".errormessagediv").delay(3000).fadeOut();
			return false;
		}
		else
		{
			$("#eventtotalmarks tbody tr[id='"+$("#judgemarks tfoot tr th:nth-child(1)").attr('class')+"']").addClass(className);
			$("#eventtotalmarks tbody tr[id='"+$("#judgemarks tfoot tr th:nth-child(1)").attr('class')+"']").find("td:nth-child(4)").empty();
			$("#eventtotalmarks tbody tr[id='"+$("#judgemarks tfoot tr th:nth-child(1)").attr('class')+"']").find("td:nth-child(4)").text(totmark);
			
			$("#judgemarks tfoot tr th:nth-child(1)").removeClass($("#judgemarks tfoot tr th:nth-child(1)").attr('class'));
			$('#judgemarks tbody .judge').attr('readonly',true).val("");
		}
		
		
	});
}


