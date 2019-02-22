$(document).ready(function(){  
	getbarcodeList();
	
	
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
	
	
	$("#deleteTeacher").click(function(){
		
		
		$(".successmessagediv").hide();
		var cnt = 0;
		

		$('input[type="checkbox"]:checked').map(function() {
			getData = $(this).val();
			cnt++;
			
		});
		
		

		if (cnt == 0 || cnt > 1) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select any one Accession No");
			return false;
		} 
		else
		{
			  $("#dialog").dialog("open");
		/*	  $("#dialog").empty();*/
				$("#dialog").show();
				 $('#barcode').html('');
			        $('#barcode_num').html('');
			        createBarCode();
			        /* $("#dialog").append("<p>Are you sure to Print  ?</p>");*/
		}
			
			
			
			
		
	
	});

	
    //var line = $('#barcode .bc_line').length;
   



$("#dialog").dialog({
		
	 autoOpen: false,
    modal: true,					    
    title:'Barcod Genration',
    buttons : {
   	 "Print" : function() {
   		 
				
     	  var a=$("#cssPrint").val();
    	  
   	   var b= document.getElementById("dialog").innerHTML;
   	   var abd='<style>' + a +'</style>' + b;
   	    var frame1 = $('<iframe />');
	        frame1[0].name = "frame1";
	        frame1.css({ "position": "absolute", "top": "-1000000px" });
	        $("body").append(frame1);
	        var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
	        frameDoc.document.open();
	        //Create a new HTML document.
	        frameDoc.document.write('<html><head><title>DIV Contents</title>');
	        //Append the external CSS file.
	      
	        frameDoc.document.write('</head><body>');
	      
	        
	        //Append the DIV contents.
	        frameDoc.document.write(abd);
	        frameDoc.document.write('</body></html>');
	        frameDoc.document.close();
	        setTimeout(function () {
	            window.frames["frame1"].focus();
	            window.frames["frame1"].print();
	            frame1.remove();
	        }, 100);
				
		 		 $(this).dialog("close");
			 	  
         },
	
         "No" : function() {
	            $(this).dialog("close");
	          }
	
    }
});

pagination(100);
});
function createBarCode(){
    var no_lines = 20;
    for(var i=0; i<no_lines; i++){
        $('#barcode').append('<span class="bc_line"></span>');       
    }
    
    for(var i=0; i<no_lines; i++){
        var number = 1+Math.floor(Math.random() * 10);
        $('#barcode .bc_line').eq(i).css('width',number+'px');
        var space = 1 + Math.floor(Math.random() * 6);
        $('#barcode .bc_line').eq(i).css('margin-right',space+'px');
        if(i<10)
            $('#barcode_num').append(number);
    }    
    $('#barcode_num').css('width',$('#barcode').width());
}

function getbarcodeList(){
	datalist={
			
	},
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getbarcodeList",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
		  if(result.stocklist.length>0){
			for(var i=0;i<result.stocklist.length;i++){
				$("#allstudent tbody").append("<tr>" +
						"<td><input type='checkbox' name='select' class='select' id='selectall' value='"+result.stocklist[i].accessionNo+"'/></td>"+
						"<td>"+result.stocklist[i].accessionNo+"</td>"+
						"<td>"+result.stocklist[i].itemType+"</td>"+
						"<td>"+result.stocklist[i].regDate+"</td>"+
						"<td>"+result.stocklist[i].bookTitle+"</td>"+
						"<td>"+result.stocklist[i].author+"</td>"+
						"<td>"+result.stocklist[i].ddc+"</td>"+
						"<td>"+result.stocklist[i].no_of_Copies+"</td>"+
						"<td>"+result.stocklist[i].location +"</td>"+
						"<td>"+result.stocklist[i].currentStatus+"</td>"+
						"</tr>"
				
				);
				
			}
		  }
			 else{
			    	$("#allstudent tbody").append("<tr><td ColSpan='10'>No Records Found</td></tr>");
			    }
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.stocklist.length);
		}
	});
		
}
