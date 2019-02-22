$(document).ready(function(){
$("#print").click(function(){

	 var a=".winner{font-size: 14px;font-family: Roboto Regular !important;color: #767676;}"+
"fieldset{ display: block;margin-bottom: 5px;padding-bottom: 0.625em;padding-left: 7px;padding-right: 0px;border: 0.5px solid #ccc;}"+
"legend {display: inline-block;width: auto;padding: 0; margin-bottom: 0px;font-size: 16px;line-height: inherit;color: #333;border: 0;}"+
  ".winnerbox{padding: 15px;border: 1px solid #DEDEDE; margin-bottom: 10px;}"+
  ".winnerbox h3{margin-top: 0px;}body{-webkit-print-color-adjust: exact;margin:0 auto;padding:0px;}"+
  ".col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9 {float: left;} .col-lg-1, .col-lg-10, .col-lg-11, .col-lg-12, .col-lg-2, .col-lg-3, .col-lg-4, .col-lg-5, .col-lg-6, .col-lg-7, .col-lg-8, .col-lg-9, .col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9, .col-sm-1, .col-sm-10, .col-sm-11, .col-sm-12, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8, .col-sm-9, .col-xs-1, .col-xs-10, .col-xs-11, .col-xs-12, .col-xs-2, .col-xs-3, .col-xs-4, .col-xs-5, .col-xs-6, .col-xs-7, .col-xs-8, .col-xs-9 {position: relative;min-height: 1px;}"+
  ".col-md-1 {width: 12.5%;} .col-md-2 {width: 16.66666667%;} .col-md-3 {width: 25%;} .col-md-4 {width: 33.33333333%;} .col-md-5 {width: 41.66666667%;} .col-md-6 {width: 50%;} .col-md-7 {width: 58.333333%;} .col-md-8 {width: 66.66666667%;} .col-md-9 {width: 75%;} .col-md-10 {width: 83.33333333%;} .col-md-11 {width: 91.66666667%;} .col-md-12 {width: 100%;}";
		var b = document.getElementById("winnerList").innerHTML;

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
      
       frameDoc.document.write('<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet" type="text/css" />');
       frameDoc.document.write('<link href="CSS/newUI/modern-business.css" rel="stylesheet" type="text/css" />');
       frameDoc.document.write('<link href="CSS/newUI/custome.css" rel="stylesheet" type="text/css" />');
       frameDoc.document.write('<link href="CSS/newUI/bootstrap.css" rel="stylesheet" type="text/css" />');
       frameDoc.document.write('<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />');
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
	    
	  

});
	$("#Winner").click(function(){
		location.reload();
	
	});
	$("#Candidate").click(function(){
	var academicYearId=$("#academicYearId").val();
	var groupId=$("#groupId").val();
	var electionId=$("#electionId").val();
	
	var jsonObj={
			"accyear":academicYearId,
			"group":groupId,
			"titleID":electionId,
	};
	$.ajax({
		type:"POST",
		url:"adminMenu.html?method=CandidateList",
		data:jsonObj,
		success: function(response){
			var result=$.parseJSON(response);
			$("#winnerList").empty();
			$("#winnerList").append("<table id='candidateListData' class='allstudent' style='width:100%;'>" +
					"<thead><tr>" +
					"<th>S.No</th>" +
					"<th>Name</th>" +
					"<th>Admission No.</th>" +
					"<th>Class</th>" +
					"<th>Category Name</th>" +
					"<th>Vote Coun</th></tr></thead>" +
					"<tbody></tbody></table>");
			if(result.DataList.length>0){
				$("#candidateListData tbody").empty();
				for(var i=0;i<result.DataList.length;i++){
					
					$("#candidateListData tbody").append("<tr>" +
							"<td>"+(i+1)+"</td>" +
							"<td>"+result.DataList[i].studentName+"</td>" +
							"<td>"+result.DataList[i].admissionNo+"</td>" +
							"<td>"+result.DataList[i].className+"</td>" +
							"<td>"+result.DataList[i].categoryName+"</td>" +
							"<td>"+result.DataList[i].voteCount+"</td>" +
							"</tr>");
					
				}
				
			}
			else{
				$("#candidateListData tbody").append("<tr><td colspan='5' style='text-align:center'> No Record Found </td></tr>");
			}
			
		}
		
	});
	
	});	
});