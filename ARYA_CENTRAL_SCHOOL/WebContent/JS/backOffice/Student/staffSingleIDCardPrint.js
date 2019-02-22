	$(document).ready(function(){

$("#printsingle").click(function(){
		singleprint();
	});	
	});
	function singleprint(){
		
		var b = document.getElementById("studentpreview").innerHTML;

	    var abd=b;
	
	   
	    
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