<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>

<title>eCampus Pro</title>
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-blind.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.menu.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />

<link href="CSS/newUI/custome.css" rel="stylesheet">
<script src="JQUERY/kendoJs/kendo.all.min.js"></script>
<script src="JS/backOffice/Reports/ExamClassWiseReport.js"></script>


<style>
.modal-body {
	text-align: center;
}


</style>
</head>



<body>


	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<div class="searchWrap">
		<p>
			<img src="images/addstu.png" />&nbsp;<span style="font-size: 16pt;">Double Payment Details </span>
		</p>
	</div>
		<center>


			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"></span></a>
				</div>
			</div>



			<div class="successmessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span
						class="successmessage"></span></a>
				</div>
			</div>
		</center>

		<form action="reportaction.html?method=examReportClassWiseDetails"
			method="post">
			<p id="parent1" style="display: none;">
				<a href="">Expand all</a>
			</p>
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary clearfix"  >

					<div class="panel-heading" role="tab" id="headingOne">

						
							<a data-toggle="collapse" data-parent="#accordion2"
								href="#collapseOne" style="color: #767676;vertical-align: text-top"><h4 class="panel-title" id="beforeparent"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Double Payment Details
							</h4></a>
						<div class="navbar-right">
							<span class="buttons" id="print">Print</span>
								
						</div>

						<!--div class="navbar-right">
						
							
							<span class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" style="top:-8px;">Download</span>

						</div-->
						
					</div>
					<!-- pop up -->

					<!--div class="modal fade" id="myModal" tabindex="-1" role="dialog"
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
									<span id="excelDownload"><img src="images/xl.png"
										class="xl"></span>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
										id="pdfDownload"><img src="images/pdf.png" class="pdf"></span>
								</div>

							</div>
						</div>
					</div-->

					<!-- Filters -->

					<div id="collapseOne1" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body" id="tabletxt" style="padding: 15px;margin-top: 19px;">
							<div class="col-md-6" id="txtstyle">
							
			<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-5" align="right"
					id="inputnames">School Name</label>
					<div class="col-xs-7">
						<select id="locationName" name="locationName" class="form-control">
							<option value="">----select----</option>
							 <logic:present name="locationList">
								<logic:iterate id="Location" name="locationList">
									<option  value='<bean:write name="Location" property="locationId"/>'><bean:write name="Location" property="locationName" /></option>
								</logic:iterate>
							</logic:present> 
						</select>
					</div>
			</div>
				
			<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-5" align="right"
					id="inputnames">Class </label>
					<div class="col-xs-7">
						<select id="className" name="className" class="form-control">
							<option value="">----select----</option>
						</select>
					</div>
			</div>
			
				<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-5" align="right"
					id="inputnames">Terms </label>
					<div class="col-xs-7">
						<select id="termName" class="form-control" 	onkeypress="HideError()">
							<option value="">----select----</option>
						</select>
					</div>
			</div>
		</div>
		<div class="col-md-6" id="txtstyle">
			<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-5" align="right"
					id="inputnames">Academic Year</label>
					<div class="col-xs-7">
						<select id="academicYear" name="accyear" class="form-control">
								<logic:present name="AccYearList">
												<logic:iterate id="accyear" name="AccYearList">
													<option value="<bean:write name="accyear" property="accyearId"/>"><bean:write name="accyear" property="accyearname" /></option>
												</logic:iterate>
											</logic:present> 
						</select>
					</div>
					</div>
					
				<div class="form-group clearfix">
				<label for="inputPassword" class="control-label col-xs-5" align="right"
					id="inputnames">Division</label>
					<div class="col-xs-7">
						<select id="divisionName" class="form-control">
								<option value="all">ALL</option>
						</select>
					</div>
					</div>
					
				<div class="form-group clearfix">
					<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Name/AdmissionNo</label>
						<div class="col-xs-7">
							<input type="text" name="studentName" id="studentName" class="form-control" />
							<input type="hidden" id="studentIdIntId" value="" />
						</div>
				</div>
					
					
					
			
		</div>
						</div>
						<div id="collapseOne" class="panel-collapse collapse in table-responsive">
					<table class='table' id='allstudent' style="width: 100%">
						<thead>
							<tr>
								
								<th style="width:4%;">Sl no</th>
								<th style="width:7%;">Admn No</th>
								<th style="width:16%;">Name</th>
								<th style="width:3%;">Class</th>
								<th style="width:5%;">Division</th> 
								<th style="width:12%;">Transaction Id</th>
								<th style="width:18%;">Bank</th> 
								<th style="width:5%;">Amount</th> 
								<th style="width:5%;">Status</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
						
					</table>
		                

</div>
					</div>
					
				</div>
				
			</div>
			
			<!-- Button trigger modal -->

		</form>
	</div>


	

</body>

</html>
