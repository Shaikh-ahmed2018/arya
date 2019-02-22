$(document)
		.ready(
				function() {
					$(".section").each(function(){
						var qrId=$(this).find(".qrcodes").attr("id");
						var qrcode = new QRCode(qrId);
						qrcode.makeCode("Name:"+$(this).find("#nametext").text()+
								" Abbravative:"+$(this).find("#abbravative").text()+
								" Department:"+$(this).find("#registrationId").text()+
								" Designation:"+$(this).find("#designation").text()+
								" Valid Upto:"+$(this).find("#validUptotext").text()+
								" Phone No.:"+$(this).find("#phonetext").text());
					});
				
					$(".validUptotext").click(function(){
						 var z = prompt("valid UpTo");
						 if(z!=null)
						 $(this).text(z);
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
				});
function card(){
	 var a=$("#printing-css-card").val();
		var b = document.getElementById("staffpreview").innerHTML;

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
    frameDoc.document.write('<link href="CSS/IdCard/StaffId/StaffIdCard.css" rel="stylesheet" type="text/css" />');
    frameDoc.document.write('<link href="CSS/IdCard/StaffId/'+$("#templatename").val()+'.css" rel="stylesheet" type="text/css" />');
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
		var b = document.getElementById("staffpreview").innerHTML;

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
     frameDoc.document.write('<link href="CSS/IdCard/StaffId/StaffIdCard.css" rel="stylesheet" type="text/css" />');
     frameDoc.document.write('<link href="CSS/IdCard/StaffId/'+$("#templatename").val()+'.css" rel="stylesheet" type="text/css" />');
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
		var b = document.getElementById("staffpreview").innerHTML;

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
     frameDoc.document.write('<link href="CSS/IdCard/StaffId/StaffIdCard.css" rel="stylesheet" type="text/css" />');
     frameDoc.document.write('<link href="CSS/IdCard/StaffId/'+$("#templatename").val()+'.css" rel="stylesheet" type="text/css" />');
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
