<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<title>Campus School Stream</title>
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
<script type="text/javascript"
	src="JS/backOffice/Exam/CreateExamination.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
.glyphicon:hover {
	cursor: pointer;
}

#examcreateid:hover {
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
		
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Add Exam</span>
		</p>
		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" >
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>



		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>


		<logic:present name="fail" scope="request">
			<div class="successmessagediv1">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span><bean:write
								name="fail" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>



		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne" style="color: #767676"><h4 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Exam
							Details
						</h4></a>
					

					<div class="navbar-right">
						<span id="examcreateid" class="buttons" data-toggle="tooltip"
							data-placement="bottom" title="Save">Save </span>&nbsp; 
							<span id="back" class="buttons" >Back</span></a>
					</div>
				</div>



				
		 <input type="hidden" name="examid" class="examidclass"
					id="examId"
					value='<logic:present name="examlist"><bean:write name="examlist" property="examid" />
													</logic:present>'></input>
				<input type="hidden" name="acaid" class="examidclass" id="acaid"
					value='<logic:present name="examlist"><bean:write name="examlist" property="accyearId" />
													</logic:present>'></input>
				<input type="hidden" name="acaname" class="examidclass" id="acaname"
					value='<logic:present name="examlist"><bean:write name="examlist" property="accyear" />
													</logic:present>'></input>



				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">
						<div class="col-md-6" id="txtstyle">
							<div class="form-group">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Exam Name</label>
								<div class="col-xs-7">
									<input type="text" name="examname" class="form-control"
										id="examnames" onkeypress="HideError()"
										value='<logic:present name="examlist"><bean:write name="examlist" property="examName" /></logic:present>'></input>
								</div>
							</div>
							<br />
							<div class="form-group">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Start
									Date</label>
								<div class="col-xs-7">
									<input type="text" name="examdate" id="examdates"
										class="form-control" readonly="readonly" onkeypress="HideError()"
										placeholder="Click here"
										value='<logic:present name="examlist"><bean:write name="examlist" property="startDate" /></logic:present>'></input>


								</div>
							</div>

							<br />

							<div class="form-group">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">End Date</label>
								<div class="col-xs-7">
									<input type="text" name="enddate" id="enddate"
										class="form-control" readonly="readonly" onkeypress="HideError()"
										placeholder="Click here"
										value='<logic:present name="examlist"><bean:write name="examlist" property="endDate" /></logic:present>'></input>
								</div>
							</div>


							<br />


							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Academic
									Year </label>
								<div class="col-xs-7">
									<select name="accyear" id="accyear" onkeypress="HideError()" class="form-control">
										<option value="">-----Select-----</option>
										<logic:notEmpty name="ALLACCYEARS" scope="request">
											<logic:iterate id="map" name="ALLACCYEARS" scope="request">
												<option value='<bean:write name='map' property='key'/>'>
													<bean:write name="map" property="value" />
												</option>

											</logic:iterate>

										</logic:notEmpty>
									</select>
								</div>
							</div>





							<%-- <div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
											<!-- <input type="text" name="accyear" id="accyear" class="form-control" placeholder="Click here" readonly="readonly"/> -->
									<select name="accyear" id="accyear" class="textBoxInDialog">
									<option value="">-----Select-----</option>
									<logic:notEmpty name="ALLACCYEARS" scope="request">
										<logic:iterate id="map" name="ALLACCYEARS" scope="request">
											<option value='<bean:write name='map' property='key'/>'>
												<bean:write name="map" property="value" />
											</option>

										</logic:iterate>
											
									</logic:notEmpty>
									
									</select>
									
									</div>
								</div> --%>

							<br />
							<div class="form-group">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Description</label>
								<div class="col-xs-7">
									<input type="text" name="description" class="form-control"
										id="descriptions"
										value='<logic:present name="examlist"><bean:write name="examlist" property="description" /></logic:present>'></input>
								</div>
							</div>
						</div>

					</div>

				</div>
			</div>


		</div>
	</div>

</body>
</html:html>