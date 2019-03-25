<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>eCampus Pro</title>

<script type="text/javascript" src="JS/common.js"></script>
<!-- <script type="text/javascript" src="JS/backOffice/SMS/homeWorkList.js"></script>-->

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<script>
$(document).ready(function() {
		 $("#send").click(function(){
		 $.ajax({
			    type: "POST",
			    url: "http://sms.textidea.com/app/smsapi/index.php?key=25BB891406C392&campaign=5043&routeid=100233&type=text&contacts="+$('#MobileNO').val()+"&senderid=ARYACS&msg="+$('#Smessage').val(),
			    success: function() {
			      $('#contact_form_message').html("<div id='message'></div>");
			      $('#message').html("<h2>Contact Form Submitted!</h2>")
			      .append("<p>We will be in touch soon.</p>")
			      .hide()
			      .fadeIn(1500, function() {
			        $('#message').append("Tick");
			      });
			    }
		 	});
		 alert('successfully send');
	
		 });
		 $('#send').click(function () {
			  $('#MobileNO').val('');
			  $('#Smessage').val('');
			});
});
//preview button
function renderHTML(){
	  var html = document.getElementById("Smessage").value
	  document.getElementById("preview_modal").innerHTML = html;
	}


</script>
<style>
.glyphicon:hover {
	cursor: pointer;
}
/* .modal-body {
	text-align: center;
} */
</style>

<style>
.download:hover{

cursor: pointer;
}
#excelDownload:hover {
	cursor: pointer;
}
#pdfDownload:hover {
	cursor: pointer;
}
</style>

</head>

<body>

		<div class="col-md-10 col-md-offset-2" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		
		<p><img src="images/addstu.png" /><span id="pageHeading">Single SMS</span></p>
				<div class="panel-body clearfix"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					
				</div>

			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676; vertical-align: text-top;"> 
							<h4 class="panel-title" style="vertical-align: super;"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Single SMS</h4></a>
						
					</div>
					<div id="contact_form_message"></div>

					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Mobile Number</label>
									<div class="col-xs-7">
										<input type="text"  id="MobileNO"name='MobileNO' class="form-control" required>
									</div>
								</div>
									
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Message</label>
									<div class="col-xs-7">
										<textarea  id="Smessage" name='StudentMessage' class="form-control" style=" width: 100%; height: 108px;"></textarea>
									
									</div>
								</div>
								
						</div>
							
							<div class="col-md-12">
							
								<p align="center">
									<button type="button" class="btn btn-info" id="previewbtn"  data-toggle="modal" data-target="#myModal" onclick="renderHTML()">Preview</button>
									<button type="button" class="btn btn-info" id="send" >Send</button>
									<!--button type="reset" class="btn btn-info" id="reset">Reset</button-->
								</p>
							
							
							
							</div>
							<!-- modal open -->
							<div id="myModal" class="modal fade" role="dialog">
  								<div class="modal-dialog">

    <!-- Modal content-->
    								<div class="modal-content">
      									<div class="modal-header">
        									<button type="button" class="close" data-dismiss="modal">&times;</button>
      											  <h4 class="modal-title">Preview</h4>
     									 </div>
     									 <div class="modal-body" id="preview_modal">
       										<p></p>
    								 </div>
     								 <div class="modal-footer">
        								<button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
    							  </div>
   								 </div>

  							</div>
					</div>
						<!--input type="hidden" name="Acyearid" id="Acyearid" value='<logic:present name="Acyearid"><bean:write name="Acyearid"/></logic:present>'></input-->
							
				<!-- div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">	
					
					
						<table class="table table-striped" id="allstudent">
							<thead>
							<tr>
							<th>S.No</th>
							<th>Admission No</th>
							<th>Student Name</th>
							<th>Roll No</th>
							<th>Class and Division</th>
							</tr>
							</thead>
							<tbody>
							
							</tbody>
						</table>
				
					
					
					</div>
        <div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
	    <span  class='numberOfItem'></span>	
	    </div><div class='pagination pagelinks' style='top:-9px'></div>
					</div-->
					
						</div>
					</div>
				</div>
			</div>
	</div>

	
</body>
<script>

</script>
</html>