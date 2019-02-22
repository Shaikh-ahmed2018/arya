<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>

<title>eCampus Pro</title>

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
<script type="text/javascript"
	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />

<link href="CSS/newUI/custome.css" rel="stylesheet">

<script type="text/javascript"  src="JS/backOffice/Reports/addFeeReport.js"></script>

<style>
.modal-body {
	text-align: center;
}

</style><style>
.buttons{

vertical-align: -28px;

}
</style>
</head>



<body>


	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		
		
		<p>
			<img src="images/addstu.png" />&nbsp;<span>Fee Report
			</span>
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
						class="successmessage"></span></a>
				</div>
			</div>
			<input type="hidden" id="hstartdate" value="" />
		<input type="hidden" id="henddate" value="" />

			<p id="parent1" style="display: none;">
				<a href="">Expand all</a>
			</p>
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">

					<div class="panel-heading" role="tab" id="headingOne">

						
							<a data-toggle="collapse" data-parent="#accordion2"
								href="#collapseOne" style="color: #767676;vertical-align: text-top"><h4 class="panel-title" id="beforeparent"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Fee
							</h4></a>
						

						<div class="navbar-right">
						
							<span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						 data-toggle="tooltip" data-placement="bottom" title="Download">Download</span>

						</div>
						<script>
							$(document).ready(function() {
								$('[data-toggle="tooltip"]').tooltip();
							});
						</script>
					</div>
					<!-- pop up -->

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
									<span id="excelDownload"><img src="images/xl.png"
										class="xl"></span>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
										id="pdfDownload"><img src="images/pdf.png" class="pdf"></span>
								</div>

							</div>
						</div>
					</div>

					<!-- Filters -->

					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body" id="tabletxt" style="padding: 15px;">

							<div class="col-md-6" id="txtstyle">
									
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames">Location</label>
										
									<div class="col-xs-7">
										
										<select id="location" name="location" class="form-control" onchange="HideError()">
											<option value="---Select---"></option>

											 <logic:present name="locationList" scope="request">

												<logic:iterate id="Location" name="locationList">

												 <option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option> 

												</logic:iterate>

											</logic:present> 
										</select>
									</div>
								</div>
									<br />
									
									
								<div class="form-group">
							<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames">Class</label>
										<div class="col-xs-7">
										
										<select name="classname" id="classid" class="form-control"  onchange="HideError()">
									      <option value="---Select---"></option>
									      
									      
											    <logic:present name="classList">

												<logic:iterate id="classrec" name="classList">

													<option
														value="<bean:write name="classrec" property="classId"/>">
														<bean:write name="classrec" property="classname" />
													</option>

												</logic:iterate>

											</logic:present> 
							               
							                </select>
											
									</div>
								</div> 					
								
									<br />


							<div class="form-group">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align:left; line-height: 35px;">Start
									Date</label>
								<div class="col-xs-7">
									<input type="text" name="examdate" id="examdate" onchange="HideError()"
										class="form-control" readonly="readonly" onkeypress="HideError()"
										placeholder="Click here"
										value='<logic:present name="examlist"><bean:write name="examlist" property="startDate" /></logic:present>'></input>


								</div>
							</div>
	
									<br />
								
							

							
								<br />
							</div>
							<div class="col-md-6" id="txtstyle">
								
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames">Academic Year</label>
									<div class="col-xs-7">
										<select id="accyear" name="accyear" class="form-control"
											required>
											<option value="---Select---"></option>

											<logic:present name="AccYearList">

												<logic:iterate id="AccYear" name="AccYearList">

													<option value="<bean:write name="AccYear" property="accyearId"/>"><bean:write name="AccYear" property="accyearname" /></option>

												</logic:iterate>

											</logic:present>
										</select>
									</div>
								</div>
								
							
								
									
								<br />
								
						<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4" 
										id="inputnames">Section</label>
									<div class="col-xs-7">
										<select id="section" name="section" class="form-control"
											required>
											<option value="---Select---"></option>
											
											
											 <logic:present name="sectionList">

												<logic:iterate id="classrec" name="sectionList">

													<option
														value="<bean:write name="classrec" property="sectionId"/>">
														<bean:write name="classrec" property="sectionname" />
													</option>

												</logic:iterate>

											</logic:present>
											
											
											
											
											

										</select>
									</div>
								</div> 
					
								
								
								<br />
								
							<div class="form-group">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align:left; line-height: 35px;">End Date</label>
								<div class="col-xs-7">
									<input type="text" name="enddate" id="enddate"
										class="form-control" readonly="readonly" onkeypress="HideError()"
										placeholder="Click here"
										value='<logic:present name="examlist"><bean:write name="examlist" property="endDate" /></logic:present>'></input>
								</div>
							</div>
								
								<br />	<br />
							</div>
							<center>
							<button type="b" class="btn btn-info" id="search" >Search Report</button></center>
							<!-- data-toggle="modal" data-target="#myModal" -->
							<br />

							<div class="col-md-12 selecteditems">
								<br /> <input type="hidden" id="haccyear"
									value="<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="accyearid"/></logic:present>" />
								<input type="hidden" id="hstream"
									value="<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="streamid"/></logic:present>" />
								<input type="hidden" id="hclass"
									value="<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="classnameid"/></logic:present>" />
								<input type="hidden" id="hsection"
									value="<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="sectionid"/></logic:present>" />
								<input type="hidden" id="hlocation"
									value="<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="locationid"/></logic:present>" />
								
							<div id="feetable"></div>	
						
							
							</div>
							<br />
							<br />

							
							
						

						</div>
					</div>
				</div>
			</div>
			<!-- Button trigger modal -->

	</div>

	<span>&nbsp;</span>


	<!-- jQuery -->
	<script src="js/jquery.js"></script>


	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>


	<script>
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    })
    </script>

</body>

</html>
