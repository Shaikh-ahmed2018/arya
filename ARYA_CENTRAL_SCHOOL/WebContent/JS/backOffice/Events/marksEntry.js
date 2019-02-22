var no=0;
$(document).ready(function(){
	var currentEletd;
	//onchange functions
	$("#eventName").change(function(){
		getcategoryName();
		getProgramName();
		getstageName();
		getChestNoForMarksEntry();
		getCriteriaForMarksEntry();
	});
	$("#categoryName").change(function(){
		getProgramName();
		getChestNoForMarksEntry();
		getCriteriaForMarksEntry();
	});
	$("#progName").change(function(){
		getChestNoForMarksEntry();
		getCriteriaForMarksEntry();
	});

	$(".judge").change(function(){
		alert();
	});




	//for judge Lables 
	$("#No").change(function(){
		no = $("#No").val();
		$("#judgemarks thead tr").empty();
		$("#judgemarks thead tr").append("<th>Sl no</th><th>Criteria</th>");

		for(var k=1;k <=(Number(no));k++){
			$("#judgemarks thead tr").append("<th>JUDGE "+k+"</th>");
		}
		$("#judgemarks tbody").empty();  
		$('#judgemarks tfoot').empty();
		$('#judgemarks tfoot').append("<tr><td colspan='2'>Total</td></tr>");
		if(len>0){
			for(var i=0; i<len; i++){
				$("#judgemarks tbody").append("<tr id='"+criteriaIDarry[i]+"' class='abcrow abc"+i+"'>" 
						+"<td>"+(i+1)+"</td>"
						+"<td>"+criteriaArray[i]+"</td>"
						+"</tr>");

				for(var j=0; j<(Number(no)); j++){
					$("#judgemarks tbody tr.abc"+i).append("<td><input type='text'   class='judge'/></td>");

				}
			}


			//for appending textbox at end dynamically
			for(var k=0;k<(Number(no));k++){
				$('#judgemarks tfoot tr').append('<td><input type="text" class="judgeT"></td>');
			}


			findTotalResult();
			//gotomarksEntry();
			
			$("#eventtotalmarks tbody tr").change(function(){
				var totmark=0;
				$("#judgemarks tfoot tr .judgeT").each(function(){
					totmark=totmark+Number(this.value);
					alert("totmarks"+totmark);
				});
				$("#eventtotalmarks tbody tr").find("td:nth-child(4)").empty();
				$(this).find("td:nth-child(4)").text(totmark);
			});
			
			
			rowClickable();
		}
	});

$("#save").click(function(){
	var chestNo = $("#chestNo").val();
	var MarksObtained = $("#eventtotalmarks tbody tr").find("td:nth-child(4)").text();
	var accId = $("#hacademicyaer").val();
	var evId = $("#eventName").val();
	var catId = $("#categoryName").val();
	var progId = $("#progName").val();
	
	var lhs = $("#eventtotalmarks tbody tr").find("td:nth-child(2)").text();
	if(lhs == chestNo){
		
		
	//}
	
	
	
	datalist = {
			"chestNo":chestNo,
			"MarksObtained":MarksObtained,
			"accId":accId,
			"evId":evId,
			"catId":catId,
			"progId":progId,
			};
 	
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
				}
				else{
					$('.successmessagediv').hide();
					$(".errormessagediv").show();
					$(".validateTips").text("Fail to Add Record...");
					$(".errormessagediv").delay(3000).fadeOut();
			}//success
		}
		});//ajax
	
	}
	else{
		alert("not matching criteria");
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
			$("#categoryName").append("<option value=''>---select---</option>");

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
			$("#progName").append("<option value=''>---select---</option>");
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
		url:"EventsMenu.html?" +
		"method=getstageList",
		async:false,
		data:{"id":id,
		},
		success:function(data){
			var result = $.parseJSON(data);
			$("#stageName").empty();
			$("#stageName").append("<option value=''>---select---</option>");
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
			if(result.data.length>0){
				for(var i=0; i<result.data.length; i++){
					$("#eventtotalmarks tbody").append("<tr id='"+result.data[i].chestNumber+"'>" 
							+"<td>"+(i+1)+"</td>"
							+"<td>"+result.data[i].chestNumber+"</td>"
							+"<td>"+result.data[i].maxMarks+"</td>"
							+"<td></td>"
							+"</tr>");
				}
			}//end if

			else{
				$("#eventtotalmarks tbody").append("<tr><td colspan='4'>No Record Found</td></tr>");
			}

			//alert($("#judgemarks").closest("tr").attr("td"));
			rowClickable();
			$(".numberOfItems").empty();
			$(".numberOfItems").append("No of records "+result.data.length+ ".");
			pagination(50);
		}
	});
}

function getCriteriaForMarksEntry(){
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
			criteriaIDarry=[];
			criteriaArray=[];
			$("#judgemarks tbody").empty();  
			$('#judgemarks tfoot').empty();
			$('#judgemarks tfoot').append("<tr><td colspan='2'>Total</td></tr>");
			if(result.data.length>0){
				for(var i=0; i<len; i++){
					criteriaIDarry.push(result.data[i].criteriaId);
					criteriaArray.push(result.data[i].criteria);
					$("#judgemarks tbody").append("<tr id='"+result.data[i].criteriaId+"' class='abcrow abc"+i+"'>" 
							+"<td>"+(i+1)+"</td>"
							+"<td>"+result.data[i].criteria+"</td>"
							+"</tr>");

					for(var j=0; j<(Number(no)); j++){
						$("#judgemarks tbody tr.abc"+i).append("<td><input type='text'   class='judge'/></td>");
					}
				}


				//for appending textbox at end dynamically
				for(var k=0;k<(Number(no));k++){
					$('#judgemarks tfoot tr').append('<td><input type="text" class="judgeT"></td>');
				}


				findTotalResult();
				
				
				//-----
				$("#eventtotalmarks tbody tr").click(function(){
					var totmark=0;
					$("#judgemarks tfoot tr .judgeT").each(function(){
						totmark=totmark+Number(this.value);
					});
					$("#eventtotalmarks tbody tr").find("td:nth-child(4)").empty();
					//alert("check"+$("#eventtotalmarks tbody tr").find("td:nth-child(2)").text());
					$(this).find("td:nth-child(4)").text(totmark);
					$(this).prop("readonly",true);
				});
	
				
				
				rowClickable();
			}
			else{
				$("#judgemarks tbody").append("<tr><td colspan='4'>No Record Found</td></tr>");
			}



			$(".numberOfItems").empty();
			$(".numberOfItems").append("No of records "+result.data.length+ ".");
			pagination(50);
			var sum=0;
			$(".judge").change(function(){
				$(".abcrow td .judge").each(function(){
					sum=sum+Number(this.value);
					//alert(sum);
				});
			});
		}

	});
}	

function rowClickable(){

	$("#eventtotalmarks tbody tr").click(function(){
		var chestNo=$(this).attr("id");
		//alert(chestNo);
		$("#chestNo").val(chestNo);
		//$("#chestNo").val('116');
		$('#chestNo').attr('readonly', 'true'); 
	});
}

function findTotalResult(){

	$(".judge").change(function(){
		var sum=0;
		var indx=Number($(this).closest("td").index())+1;
		$('#judgemarks tr td:nth-child('+indx+')').find(".judge").each(function(){
			sum=sum+Number(this.value);

		});
		$("#judgemarks tfoot tr td:nth-child("+$(this).closest("td").index()+")").find(".judgeT").val(sum);
	});


}
/*function gotomarksEntry(){
	$("#eventtotalmarks tbody tr").change(function(){
		alert("ur here");
		var totmark=0;
		$("#judgemarks tfoot tr .judgeT").each(function(){
			totmark=totmark+Number(this.value);
			alert("totmarks"+totmark);
		});
		$("#eventtotalmarks tbody tr").find("td:nth-child(4)").empty();
		alert("Totmarks"+totmark);
		$(this).find("td:nth-child(4)").text(totmark);
	});
}*/