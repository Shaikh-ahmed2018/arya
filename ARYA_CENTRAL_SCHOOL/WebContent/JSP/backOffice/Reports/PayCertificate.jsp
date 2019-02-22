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

<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>


<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
	

 <script type="text/javascript" src="JS/backOffice/Reports/PayCertificate.js"></script> 

<script type="text/javascript">
	
	$(document).scroll(function() {
		var y = $(this).scrollTop();
		if (y > 100) {
			$('.topimg').fadeIn();
		} else {
			$('.topimg').fadeOut();
		}
	});
</script>
<style>
.glyphicon:hover{

cursor: pointer;
}
#classSave:hover {
	cursor: pointer;
}

</style>
<style>
.buttons{

vertical-align: -28px;

}
</style>

</head>

<body>
<div>
	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading" style="font-size: 16pt;">PayCertificate</span>
		</p>

<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>


		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>


<center><logic:present name="Nothingfoundmsg" scope="request"><span style="color: black;font-family: Segoe UI;font-size:12px;"><bean:write name="Nothingfoundmsg"/></span></logic:present></center>





		<form method="post">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary" style="margin-top: -20px;">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion2"
								href="#collapseOne" style="color: #767676;vertical-align: text-top"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;PayCertificate
							</h4></a>
							

							<div class="navbar-right">
						
							<!-- <img src="images/rightline.png" style="margin-top: -2px;margin-right: 11px;">
							<img src="images/download.png" class="" id="iconsimg" style="margin-top:4px"
								data-toggle="modal" data-target="#myModal" data-toggle="tooltip"
								data-placement="bottom" title="Download"> -->	<span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						 data-toggle="tooltip" data-placement="bottom"  style="top:-8px;">Download </span>

						</div>
						
						<script>
							$(document).ready(function() {
								$('[data-toggle="tooltip"]').tooltip();
							});
						</script>
				
				</div>
				
				
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">Download</h4>
								</div>
								<div class="modal-body">
									<!-- <span id="excelDownload"><img src="images/xl.png"
										class="xl"></span> -->
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
										id="pdfDownload"><img src="images/pdf.png" class="pdf"></span>
								</div>

							</div>
						</div>
					</div>
					
						
					
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body" style="margin-top: 19px;">
							<div class="col-md-6" id="txtstyle" style="font-size: 11pt; color: #5d5d5d;">
								<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Academic Year
								</label>		
									<div class="col-xs-7">
										<select id="accyear" name="accyear" class="form-control"
											required>
											<option value=""></option>
											<logic:present name="AccYearList">
												<logic:iterate id="AccYear" name="AccYearList">
													<option
														value="<bean:write name="AccYear" property="accyearname"/>"><bean:write name="AccYear" property="accyearname" /></option>

												</logic:iterate>

											</logic:present>
										</select>
									</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Teacher Name</label>
									<div class="col-xs-7">
					               <select id="teachername" name="teachername" class="form-control"
											required>
											<option value=""></option>
											<logic:present name="teacherlist">
											<logic:iterate id="teacherlist" name="teacherlist">
													<option
														value="<bean:write name="teacherlist" property="teacherId"/>"><bean:write name="teacherlist" property="teacherName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Month
								</label>		
									<div class="col-xs-7">
										<select id="monthid" name="monthid" class="form-control"
											required>
											<option value="0"></option>
											<option value="1">January</option>
											<option value="2">February</option>
											<option value="3">March</option>
											<option value="4">April</option>
											<option value="5">May</option>
											<option value="6">June</option>
											<option value="7">July</option>
											<option value="8">August</option>
											<option value="9">September</option>
											<option value="10">October</option>
											<option value="11">November</option>
											<option value="12">December</option>
																
										</select>
									</div>
								</div>
								
								
								
								<%-- <div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Description</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="className"
											placeholder="" value="<logic:present name="editClasslist" ><bean:write name="editClasslist" property="className"/></logic:present>"/>
											<input type="hidden" name="status" id="statusId" value="<logic:present name="editClasslist" ><bean:write name="editClasslist" property="status"/></logic:present>"/>
											<input type="hidden" name="updateClassCode" id="updateClassCode" value="<logic:present name="editClasslist" ><bean:write name="editClasslist" property="classId"/></logic:present>"/>
											<input type="hidden" name="hiddenStreamId" id="hiddenStreamId" value="<logic:present name="editClasslist" ><bean:write name="editClasslist" property="streamId"/></logic:present>"/>
									</div>
								</div> --%>
							
						
							</div>
						</div>
					</div>
				</div>




			
		</form>
	</div>
	</div>
</body>

</html>
