$(document)
		.ready(
				function() {
					
					$("#idfront").click(function(){
						$(".main-div.idBack").hide();
						$(".main-div.idFront").show();
						$("#bprint").hide();
						$("#print").show();
					});
					$("#idback").click(function(){
						$(".main-div.idFront").hide();
						$(".main-div.idBack").show();
						$("#bprint").show();
						$("#print").hide();
						
					});
					$(".section").each(function(){
						var qrId=$(this).find(".qrcodes").attr("id");
						var qrcode = new QRCode(qrId);
						qrcode.makeCode("Name:"+$(this).find("#nametext").text()+
								" AdmissionNo:"+$(this).find("#admNotext").text()+
								" Class:"+$(this).find("#classtext").text()+
								" House:"+$(this).find("#houNotext").text()+
								" Valid Upto:"+$(this).find("#validUptotext").text()+
								" Phone No.:"+$(this).find("#phonetext").text()+
								
								" Emergency No.:"+$(this).find("#emergencyNo").text());
						
						
						
						var qrId1=$(this).find(".qrcodes-bck").attr("id");
						var qrcode1 = new QRCode(qrId1);
						
						
						qrcode1.makeCode("Name:"+$(this).find("#nametext").text()+
								" AdmissionNo:"+$(this).find("#admNotext").text()+
								" Class:"+$(this).find("#classtext").text()+
								" House:"+$(this).find("#houNotext").text()+
								" Valid Upto:"+$(this).find("#validUptotext").text()+
								" Phone No.:"+$(this).find("#phonetext").text()+
								
								" Emergency No.:"+$(this).find("#emergencyNo").text());
					});
				
				

					    	 $("#print").click(function(){
					    		 if($('input[name="print"]:checked').val()=="A4"){
					    	
					    			 A4();
					    		 }
					    		 else if($('input[name="print"]:checked').val()=="A3"){
					    			 A3();
					    		 }
					    		 else if($('input[name="print"]:checked').val()=="card"){
					    			 card();
					    		 }
					    		 else{
					    			 alert("select print format");

					    		 } 
					    	 });
					    	 
					    	 
					    	 
					    	 $("#bprint").click(function(){
					    		 if($('input[name="print"]:checked').val()=="A4"){
					    	
					    			 bA4();
					    		 }
					    		 else if($('input[name="print"]:checked').val()=="A3"){
					    			 bA3();
					    		 }
					    		 else if($('input[name="print"]:checked').val()=="card"){
					    			 bcard();
					    		 }
					    		 else{
					    			 alert("select print format");

					    		 } 
					    	 });
				});
function card(){
	 var a=$("#printing-css-card").val();
		var b = document.getElementById("studentpreview").innerHTML;

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
    frameDoc.document.write('<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />');
    frameDoc.document.write('<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">');
    frameDoc.document.write('<link href="CSS/newUI/modern-business.css" rel="stylesheet">');
    frameDoc.document.write('<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">');
    frameDoc.document.write('<link href="CSS/newUI/custome.css" rel="stylesheet">');
    frameDoc.document.write('<link href="CSS/IdCard/IdCard.css" rel="stylesheet" type="text/css" />');
    frameDoc.document.write('<link href="CSS/IdCard/'+$("#templatename").val()+'.css" rel="stylesheet" type="text/css" />');
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
	    


}

function A4(){
	 var a=$("#printing-css-a4").val();
		var b = document.getElementById("studentpreview").innerHTML;

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
     frameDoc.document.write('<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />');
     frameDoc.document.write('<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">');
     frameDoc.document.write('<link href="CSS/newUI/modern-business.css" rel="stylesheet">');
     frameDoc.document.write('<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">');
     frameDoc.document.write('<link href="CSS/newUI/custome.css" rel="stylesheet">');
     frameDoc.document.write('<link href="CSS/IdCard/IdCard.css" rel="stylesheet" type="text/css" />');
     frameDoc.document.write('<link href="CSS/IdCard/'+$("#templatename").val()+'.css" rel="stylesheet" type="text/css" />');
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
	    


}
function A3(){
	 var a=$("#printing-css-a3").val();
		var b = document.getElementById("studentpreview").innerHTML;

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
     frameDoc.document.write('<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />');
     frameDoc.document.write('<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">');
     frameDoc.document.write('<link href="CSS/newUI/modern-business.css" rel="stylesheet">');
     frameDoc.document.write('<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">');
     frameDoc.document.write('<link href="CSS/newUI/custome.css" rel="stylesheet">');
     frameDoc.document.write('<link href="CSS/IdCard/IdCard.css" rel="stylesheet" type="text/css" />');
     frameDoc.document.write('<link href="CSS/IdCard/'+$("#templatename").val()+'.css" rel="stylesheet" type="text/css" />');
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
}













function bcard(){
	 var a=$("#bprinting-css-card").val();
		var b = document.getElementById("studentpreview").innerHTML;

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
   frameDoc.document.write('<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />');
   frameDoc.document.write('<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">');
   frameDoc.document.write('<link href="CSS/newUI/modern-business.css" rel="stylesheet">');
   frameDoc.document.write('<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">');
   frameDoc.document.write('<link href="CSS/newUI/custome.css" rel="stylesheet">');
   frameDoc.document.write('<link href="CSS/IdCard/IdCard.css" rel="stylesheet" type="text/css" />');
   frameDoc.document.write('<link href="CSS/IdCard/'+$("#templatename").val()+'.css" rel="stylesheet" type="text/css" />');
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
	    


}

function bA4(){
	 var a=$("#bprinting-css-a4").val();
		var b = document.getElementById("studentpreview").innerHTML;

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
    frameDoc.document.write('<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />');
    frameDoc.document.write('<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">');
    frameDoc.document.write('<link href="CSS/newUI/modern-business.css" rel="stylesheet">');
    frameDoc.document.write('<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">');
    frameDoc.document.write('<link href="CSS/newUI/custome.css" rel="stylesheet">');
    frameDoc.document.write('<link href="CSS/IdCard/IdCard.css" rel="stylesheet" type="text/css" />');
    frameDoc.document.write('<link href="CSS/IdCard/'+$("#templatename").val()+'.css" rel="stylesheet" type="text/css" />');
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
	    


}
function bA3(){
	 var a=$("#bprinting-css-a3").val();
		var b = document.getElementById("studentpreview").innerHTML;

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
    frameDoc.document.write('<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />');
    frameDoc.document.write('<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">');
    frameDoc.document.write('<link href="CSS/newUI/modern-business.css" rel="stylesheet">');
    frameDoc.document.write('<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">');
    frameDoc.document.write('<link href="CSS/newUI/custome.css" rel="stylesheet">');
    frameDoc.document.write('<link href="CSS/IdCard/IdCard.css" rel="stylesheet" type="text/css" />');
    frameDoc.document.write('<link href="CSS/IdCard/'+$("#templatename").val()+'.css" rel="stylesheet" type="text/css" />');
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
}
