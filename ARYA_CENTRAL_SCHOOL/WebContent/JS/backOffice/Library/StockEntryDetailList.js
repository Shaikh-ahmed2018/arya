$(document).ready(function(){
	
	
	$("#upload").change(function(){
		window.location.href="LibraryMenu.html?method=stockEntryExcelUpload";
	});
	
	getStockEntryBookList();

	$("#selectall").change(function(){
		$(".select").prop('checked', $(this).prop("checked"));
	});
	
	
	
	$(".select").change(function(){
		if($(".select").length==$(".select:checked").length){
			$("#selectall").prop("checked",true);
		}
		else{
			$("#selectall").prop("checked",false);
		}
		
	});
	
	
	
	$("#editId").click(function(){
			$(".successmessagediv1").hide();
			var cnt = 0;
			

			$('input[type="checkbox"]:checked').map(function() {
				getData = $(this).val();
				cnt++;
				
			});
			
			

		 if ($("#allstudent tbody tr").length !=1 &&  (cnt == 0 || cnt > 1)) {
				$(".errormessagediv").show();
				$(".validateTips").text("Select Any One Accession Number");
				return false;
			} 
			else
			{
				window.location.href = "LibraryMenu.html?method=editStockEntryDetail&stockid="+getData;
			}
		});
	pagination(100);
	
});

function getStockEntryBookList(){
		datalist={
				
		},
		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=getStockEntryBookList",
			data : datalist,
			async : false,
			success : function(response) {

				var result = $.parseJSON(response);
				$("#allstudent tbody").empty();
			
				for(var i=0;i<result.StockList.length;i++){
					$("#allstudent tbody").append("<tr>" +
							"<td><input type='checkbox' name='select' class='select'  value='"+result.StockList[i].enteryid+"'/></td>"+
							"<td>"+result.StockList[i].accessionNo+"</td>"+
							"<td>"+result.StockList[i].itemType+"</td>"+
							"<td>"+result.StockList[i].regdate+"</td>"+
							"<td>"+result.StockList[i].bookTitle+"</td>"+
							"<td>"+result.StockList[i].author+"</td>"+
							"<td>"+result.StockList[i].ddc+"</td>"+
							"<td>"+result.StockList[i].no_of_Copies+"</td>"+
							"<td>"+result.StockList[i].location +"</td>"+
							"<td>"+result.StockList[i].currentStatus+"</td>"+
							"</tr>"
					
					);
				}
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.StockList.length);
				pagination(100);
			}
		});
			
	}

	
	
