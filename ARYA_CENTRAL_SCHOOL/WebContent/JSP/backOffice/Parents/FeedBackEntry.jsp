<!DOCTYPE html>
<html lang="en">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
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
<script type="text/javascript" src="JS/Admin/Latheef.js"></script>


 <script type="text/javascript" src="JS/backOffice/Parents/FeedbackEntry.js"></script> 
<style>
.buttons{

vertical-align: 0px;

}
#addfile{height: auto;}
</style>
</head>

<body>

<form id="feedbackid" action="parentfeedback.html?method=savefeedback" method="post" enctype="multipart/form-data">
	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">

		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Send
				Feed Back</span>
		</p>




             
					<logic:present name="successMessage" scope="request">
						<div class="successmessagediv">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-success bg-msg-succes"><span><bean:write	name="successMessage" scope="request" /></span></a>
							</div>
						</div>
					</logic:present>
					
						<logic:present name="errorMessage" scope="request">
							<div class="successmessagediv">
								<div class="message-item">
									<!-- Warning -->
									<a href="#" class="msg-warning bg-msg-warning"><span>
											<bean:write name="errorMessage" scope="request" />
									</span></a>
								</div>

							</div>
						</logic:present>
		          


		<div class="errormessagediv" align="left" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>


		<div class="successmessagediv1" align="left" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>



	
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Send
								Feed Back
							</h4></a>

						
						
						<div class="navbar-right" >
							
							
							 <span id="saveid" class="buttons"  data-toggle="modal" data-toggle="tooltip" data-placement="bottom" title="Save">Save
									
							</span>&nbsp;
							
							<span id="back" class="buttons"  >Back</span>
							</a>
							</div>
						
						
						
						
					</div>


					
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
							

		
	<input type="hidden" name="parentid"	id="parentvalid" value="<logic:present name="parentid" ><bean:write name="parentid" property="parentID" /></logic:present>"  />		
	<input type="hidden" name="feedbackid"	id="feedbackcodeid" value="<logic:present name="feedbacklist" ><bean:write name="feedbacklist" property="feedbackid" /></logic:present>"  />	


							<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4" id="streamlableid"
										style="text-align: right; line-height: 35px;">To</label>
									<div class="col-xs-7">
										
								      
								        <select name="feedbackto" id="toid" class="form-control" >
								         <option value="">---Select--- </option>
									     <option value="admin">Admin </option>
									      <option value="principle">Principle </option>
									       <option value="teacher">Teacher </option>
										   
							                </select>
											
							   
									</div>
								</div>




								<br>
								
							
								
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Add File</label>
									<div class="col-xs-7">
											<input type="file"  name="addfile" id="addfile" class="form-control"  />
									</div>
								</div>
								
								
								
								
								 <br />
								
								
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Description
									</label>
									<div class="col-xs-7">
										<textarea name="description" id="descriptionid" class="form-control" style=" width: 100%; height: 64px;"><logic:present name="driverlist"><bean:write name="driverlist" property="address"/></logic:present></textarea>
									</div>
								</div>

							</div>



						</div>
					</div>

					<div class="panel panel-default">


						<input type="hidden" name="updatevehicleCode"
							id="updatevehicleCode"
							value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="vehiclecode"/></logic:present>" />
					</div>



				</div>
			</div>
	</div>

	</form>


</body>

</html>


