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
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/timepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>

<script type="text/javascript"
	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet" />
<link href="CSS/newUI/modern-business.css" rel="stylesheet" />
<link href="CSS/newUI/custome.css" rel="stylesheet" />
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript"
	src="JS/backOffice/Library/Books_issue.js"></script>

<style>
.buttons{

vertical-align: 0px;

}
</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span>Add Book Issue
				Details</span>
		</p>


		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>



		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>


		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<logic:present name="errormessagediv" scope="request">

			<div class="errormessagediv" align="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"></span> <bean:write name="errormessagediv"
							scope="request" /></a>
				</div>
			</div>
		</logic:present>



		<form>
			<div class="panel-group" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676;vertical-align: text-top;"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Add
								Book Issue Details
								</h4></a>
					

						<div class="navbar-right">

							<a id="savelibrary_issue" class="buttons" data-toggle="tooltip"
								data-placement="bottom" title="Save">Save
							</a>&nbsp; <span
								class="buttons" id="back" >Back</span></a>
						</div>

					</div>



					<input type="hidden" name="drivercode" id="drivercode"
						value="<logic:present name="driverlist" ><bean:write name="driverlist" property="driverCode"/></logic:present>" />
					<input type="hidden" name="vehiclecode" id="vehiclecode"
						value="<logic:present name="fuellist" ><bean:write name="fuellist" property="vehicleCode"/></logic:present>" />
					<input type="hidden" name="fuelcode" id="fuelcode"
						value="<logic:present name="fuellist" ><bean:write name="fuellist" property="fuelCode"/></logic:present>" />
					<input type="hidden" name="license" id="hlicensetodrive"
						value='<logic:present name="driverlist"><bean:write name="driverlist" property="license" /></logic:present>'></input>
					<input type="hidden" name="licenseupload" id="hlicenseupload"
						value='<logic:present name="driverlist"><bean:write name="driverlist" property="licensedrive" /></logic:present>'></input>




					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6" id="txtstyle">

								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Book
										Issuer Name</label>
									<div class="col-xs-7">

										<input type="text" name="Bookissuername" class="form-control"
											id="Bookissuerid" onchange="HideError()" readonly="readonly"
											value="<logic:present name="AddBookIssuelibrary" ><bean:write name="AddBookIssuelibrary"/></logic:present>" />
									</div>
								</div>
								<br />


								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Borrower
										Type</label>
									<div class="col-xs-7">
										<select name="Borrowertype" id="Borrowertype"
											class="form-control" onchange="HideError()">
											<option value=""></option>
											<option value="stu">Student</option>
											<option value="tea">Teacher</option>


										</select>
									</div>
								</div>
								<br />


								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Book
										Name </label>
									<div class="col-xs-7">
										<select name="bookname" id="bookname" class="form-control"
											onchange="HideError()">
											<option value=""></option>
											<logic:present name="booksnames">
												<logic:iterate id="booksnamesid" name="booksnames">
													<option
														value=" <bean:write name="booksnamesid" property="bookname"/>">
														<bean:write name="booksnamesid" property="bookname" />
													</option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>

								<br />

								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Actual
										Return Date </label>
									<div class="col-xs-7">
										<input type="text" class="form-control" onKeypress="HideError()"
											onchange="DaysCalculator()" name="actual_return_date"
											id="actual_return_date" value="" />
									</div>
								</div>

								<br />


								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Fine Per
										Day</label>
									<div class="col-xs-7">

										<input type="text" name="fine_per_day" class="form-control"
											id="fine_per_day" onchange="HideError()" value="" />
									</div>
								</div>

								<br />
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;" id="returndate">
										Return Date </label>
									<div class="col-xs-7">
										<input type="text" class="form-control" onchange="HideError()"
											name="return_date" id="return_date" value="">
									</div>
								</div>

								<br />


								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;" id="fineamount">Fine
										Amount</label>
									<div class="col-xs-7">

										<input type="text" name="fine_amount" class="form-control"
											id="fine_amount" onchange="HideError()" readonly="readonly"
											value="" />
									</div>
								</div>

							</div>




							<div class="col-md-6" id="txtstyle">



								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Book
										Issued Date </label>
									<div class="col-xs-7">
										<input type="text" class="form-control" onchange="HideError()"
											name="book_issue_date" id="book_issue_date" value="" />
									</div>
								</div>

								<br />

								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Borrower
										Name </label>
									<div class="col-xs-7">
										<select name="borrowername" id="borrowername"
											class="form-control" onchange="HideError()">
											<option value=""></option>


										</select>
									</div>
								</div>

								<br />
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Barcode
										Number </label>
									<div class="col-xs-7">
										<select name="barcode_number" id="barcode_number"
											class="form-control" onchange="HideError()">
											<option value=""></option>


										</select>
									</div>
								</div>
								<br />

								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Actual
										Days</label>
									<div class="col-xs-7">

										<input type="text" name="actual_days" class="form-control"
											id="actual_days" onchange="HideError()" readonly="readonly"
											value="" />
									</div>
								</div>

								<br />

								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Status </label>
									<div class="col-xs-7">
										<select name="status" id="status" class="form-control"
											onchange="HideError()">
											<option value=""></option>
											<option value="yet_to_return">Yet To Return</option>
											<option value="retuned">Returned</option>


										</select>
									</div>
								</div>

								<br />

								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;" id="delayeddays">Delayed
										Days</label>
									<div class="col-xs-7">

										<input type="text" name="delayed_days" class="form-control"
											id="delayed_days" readonly="readonly" onchange="HideError()"
											value="" />
									</div>
								</div>
							</div>
						</div>
					</div>
		</form>
	</div>
</body>

</html>
