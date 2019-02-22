<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Admin/AcademicYearDetails.js"></script>

<style>
.save:hover {
	cursor: pointer;
}
</style>

<style>
#list:hover {
	cursor: pointer;
}
</style>

<style>
.buttons{

vertical-align: 0px;

}
</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present name="title"><bean:write name="title"/></logic:present></span>
		</p>

		
			<div class="successmessagediv" align ="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span
						class="successmessage"></span></a>
				</div>
			</div>

			<div class="errormessagediv1" align ="center" style="display: none;">
				<div class="message-item1">
					<!-- Warning -->
					<a href="#" class="msg-warning1 bg-msg-warning1"
						style="width: 30%;"><span class="validateTips1"></span></a>
				</div>
			</div>
		

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne" style="color: #767676"> <i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;<h4 class="panel-title">Academic Year Details
						</h4></a>
					

						<div class="navbar-right" >
							 <span id="save" class="buttons">Save</span>
							 <span class="buttons" id="back">Back</span>
						</div>
					
				</div>
			
				
				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">

						<div class="col-md-6" id="txtstyle">
						
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Academic
									Year
								</label>
								<div class="col-xs-7">
									<input type="text" name="accyearname" id="accyearname"
										maxlength="25" class="form-control" onkeypress="HideError()"
										placeholder=""
										value='<logic:present name="editacademicyear" property="acadamic_name"><bean:write name="editacademicyear" property="acadamic_name"></bean:write></logic:present>'></input>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Description
								</label>
								<div class="col-xs-7">
									<textarea style="font-size: 12px;" name="description"
										class="form-control" id="description"
										placeholder="Enter Description">
										</textarea>
								</div>
							</div>
						
						</div>
						
						<div class="col-md-6" id="txtstyle">

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Start
									Date
								</label>
								<div class="col-xs-7">
									<input type="text" name="startdate" id="startdate"
										readonly="readonly" placeholder="" onkeypress="HideError()"
										class="form-control"
										value='<logic:present name="editacademicyear" property="startDate"><bean:write name="editacademicyear" property="startDate" /></logic:present>'
										onchange="setEndDate()"></input>
								</div>
							</div>
						
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">End Date</label>
								<div class="col-xs-7">
									<input type="text" name="enddate" id="enddate"
										readonly="readonly" placeholder="" onkeypress="HideError()"
										class="form-control"
										value='<logic:present name="editacademicyear" property="endDate"><bean:write name="editacademicyear" property="endDate" /></logic:present>'></input>

								</div>
							</div>
						
						</div>
						<input type="hidden" name="accid" id="accid"
							value='<logic:present name="editacademicyear" property="acadamic_id"><bean:write name="editacademicyear" property="acadamic_id" />
													</logic:present>' />

						<input type="hidden" id="descriptionId" name="descriptionId"
							value="<logic:present name="editacademicyear" property="description">
											<bean:write name="editacademicyear" property="description" />
										</logic:present>">
					</div>
				</div>

			</div>
		</div>
	</div>
</body>

</html>
