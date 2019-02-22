<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome1.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Fee/FeeCollectionNew.js"></script>
<style>
.save:hover {
	cursor: pointer;
} 
</style>

</head>

<body>
	<div class="col-md-10 col-md-offset-2"
		style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Fee
				Collection </span>
		</p>
		<logic:present name="successMessage" scope="request">

			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span> <bean:write
								name="successMessage" scope="request" />
					</span></a>
				</div>
			</div>

		</logic:present>

		<logic:present name="errorMessage" scope="request">

			<div class="errormessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
								name="errorMessage" scope="request" />
					</span></a>
				</div>

			</div>

		</logic:present>

		<logic:present name="duplicateMessage" scope="request">

			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
								name="duplicateMessage" scope="request" />
					</span></a>
				</div>

			</div>

		</logic:present>

		<div class="errormessagediv1" align="center" style="display: none;">
			<div class="message-item1">
				<!-- Warning -->
				<a href="#" class="msg-warning1 bg-msg-warning1" style="width: 30%;"><span
					class="validateTips1"></span></a>
			</div>
		</div>


		<!-- <div class="successmessagediv" style="display: none;">
				<div class="message-item">
					Warning <a href="#" class="msg-success bg-msg-succes"><span
						class="successmessage"></span></a>
				</div>
			</div> -->
		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<form id="formfeecollection"
			action="feecollection.html?method=insertfeecollectiondetails"
			enctype="multipart/form-data" method="post">

			<input type="hidden" id="hstudentid" name="studentid"
				value="<logic:present name="studentval" property="studentid"><bean:write name="studentval" property="studentid" />
													</logic:present>" />

			<input type="hidden" id="hclassid" name="hclassname"
				value="<logic:present name="studentval" property="classid"><bean:write name="studentval" property="classid" />
													</logic:present>" />

			<input type="hidden" id="hfmscode" name="hfmsname"
				value="<logic:present name="fmscode"><bean:write name="fmscode" /></logic:present>">

			<input type="hidden" id="totalfeeamountid"
				name="total_feeamount_for_student"
				value="<logic:present name="totalfeeamount"><bean:write name="totalfeeamount" /></logic:present>">

			<input type="hidden" id="monthlist" name="monthlist" value="">


				<input type="hidden" id="hidden_academicyear_id"
				name="hidden_academicyear_id"
				value="<logic:present name="currentaccyear"><bean:write name="currentaccyear"/></logic:present>">
										
										

			<!-- chiru -->

			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne"
								style="color: #767676; vertical-align: text-top;"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Fee
								Particulars
							</h4></a>
						

						<div class="navbar-right">

							<span class="save2" id="save"> <img src="images/save.png"
								class="save" data-toggle="tooltip" style="margin-top: 6px;"
								data-placement="bottom" title="Save" />
							</span>
							<!-- <a href="adminMenu.html?method=studentList"> <span
								class="glyphicon glyphicon-list2" data-toggle="tooltip"
								data-placement="bottom" title="List"></span>
							</a> -->
						</div>

					</div>

					<script>
						$(document).ready(function() {
							$('[data-toggle="tooltip"]').tooltip();
						});
					</script>

					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">



							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">


								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Student
										Name</label>
									<div class="col-xs-7">
										<input type="text" name="studentcastename" id="studentid"
											maxlength="30" class="form-control" readonly="readonly"
											value='<logic:present name="studentval"><bean:write name="studentval" property="studentname"></bean:write></logic:present>' />
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Admission No:</label>
									<div class="col-xs-7">
										<input type="text" name="admissionNum" id="admissionNum"
											maxlength="30" class="form-control" readonly="readonly"
											value='<logic:present name="studentval"><bean:write name="studentval" property="stdadmissionNo"></bean:write></logic:present>' />
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Class</label>
									<div class="col-xs-7">
										<input type="text" name="classid" id="classid" maxlength="30"
											class="form-control" readonly="readonly"
											value='<logic:present name="studentval"><bean:write name="studentval" property="classname"></bean:write></logic:present>' />
									</div>
								</div>
								<br />

								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Fee Per
										Month</label>
									<div class="col-xs-7">
										<input type="text" name="total_fee" id="total_fee"
											maxlength="30" class="form-control" readonly="readonly"
											value='<logic:present name="getActiveFeeMasterSetupDetails" scope="request"  ><bean:write name="feeamt" ></bean:write></logic:present>' />
									</div>
								</div>


								<br />
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Total
										Fee</label>
									<div class="col-xs-7">
										<input type="text" name="complete_total_fee"
											id="complete_total_fee" maxlength="30" class="form-control"
											readonly="readonly"
											value='<logic:present name="totalfeeamt" scope="request"  ><bean:write name="totalfeeamt" ></bean:write></logic:present>' />
									</div>
								</div>

							</div>


							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

								<logic:present name="getActiveFeeMasterSetupDetails"
									scope="request">

									<div class="form-group">

										<logic:iterate id="AccYear"
											name="getActiveFeeMasterSetupDetails">

											<label for="inputEmail" class="control-label col-xs-4"
												style="text-align: right; line-height: 35px;"><bean:write
													name="AccYear" property="feename" /></label>
											<div class="col-xs-7">
												<input type="text" name="studentFirstName"
													onkeypress="HideError()" id="studentFirstNameId"
													maxlength="25" class="form-control" readonly="readonly"
													value='<bean:write name="AccYear" property="feeamount" />' />

												<br />

											</div>

										</logic:iterate>
									</div>



								</logic:present>

							</div>


						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingTwo">
						<h4 class="panel-title">
							<a class="collapsed" role="button" data-toggle="collapse"
								data-parent="#accordion" href="#collapseTwo"
								style="color: #767676" aria-expanded="false"
								aria-controls="collapseTwo"> <i
								class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Mode
								Of Payment
							</a>
						</h4>
					</div>
					<div id="collapseTwo" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingTwo">
						<div class="panel-body">
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">





								<div class="form-group">

									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Academic
										Year </label>

									<div class="col-xs-7">
										<select id="accyear" name="accyear" class="form-control"
											readonly>
											<option value=""></option>

											<logic:present name="AccYearList">

												<logic:iterate id="AccYear" name="AccYearList">

													<option
														value="<bean:write name="AccYear" property="accyearId"/>">
														<bean:write name="AccYear" property="accyearname" />
													</option>

												</logic:iterate>

											</logic:present>
										</select>
									</div>
								</div>


							</div>
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">


								<div class="form-group">

									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Payment
										Type </label>

									<div class="col-xs-7">
										<select id="paymentTypeId" name="paymentType"
											class="form-control" required>
											<option value=""></option>
											<option value="monthly">Monthly</option>
											<option value="quarterly">Quarterly</option>
											<option value="halfyearly">Half Yearly</option>
											<option value="annually">Annually</option>

										</select>
									</div>
								</div>

								<br />

							</div>




							<table id="allstudent"
								style="border-right: 1px solid rgba(221, 221, 221, 0);"
								class="table">
								<tr>
									<td align="right" valign="middle" width="10%"><input
										type="checkbox" class="paymentMonth" name="paymentMonth"
										id="JUN" value="JUN" /></td>
									<td align="left" valign="middle" width="10%"><label><font>June</font></label></td>
									<td align="right" valign="middle" width="10%"><input
										type="checkbox" class="paymentMonth" name="paymentMonth"
										id="JUL" value="JUL" /></td>
									<td align="left" valign="middle" width="10%"><label><font>July</font></label></td>
									<td align="right" valign="middle" width="10%"><input
										type="checkbox" class="paymentMonth" name="paymentMonth"
										id="AUG" value="AUG" /></td>
									<td align="left" valign="middle" width="10%"><label><font>August</font></label></td>
									<td align="right" valign="middle" width="10%"><input
										type="checkbox" class="paymentMonth" name="paymentMonth"
										id="SEP" value="SEP" /></td>
									<td align="left" valign="middle" width="10%"><label><font>September</font></label></td>
									<td align="right" valign="middle" width="10%"><input
										type="checkbox" class="paymentMonth" name="paymentMonth"
										id="OCT" value="OCT" /></td>
									<td align="left" valign="middle" width="10%"><label><font>October</font></label></td>
									<td align="right" valign="middle" width="10%"><input
										type="checkbox" class="paymentMonth" name="paymentMonth"
										id="NOV" value="NOV" /></td>
									<td align="left" valign="middle" width="10%"><label><font>November</font></label></td>
								</tr>
								<tr>
									<td align="right" valign="middle" width="10%"><input
										type="checkbox" class="paymentMonth" name="paymentMonth"
										id="DEC" value="DEC" /></td>
									<td align="left" valign="middle" width="10%"><label><font>December</font></label></td>

									<td align="right" valign="middle" width="10%"><input
										type="checkbox" class="paymentMonth" name="paymentMonth"
										id="JAN" value="JAN" /></td>
									<td align="left" valign="middle" width="10%"><label><font>January</font></label></td>
									<td align="right" valign="middle" width="10%"><input
										type="checkbox" class="paymentMonth" name="paymentMonth"
										id="FEB" value="FEB" /></td>
									<td align="left" valign="middle" width="10%"><label><font>Febraury</font></label></td>
									<td align="right" valign="middle" width="10%"><input
										type="checkbox" class="paymentMonth" name="paymentMonth"
										id="MAR" value="MAR" /></td>
									<td align="left" valign="middle" width="10%"><label><font>March</font></label></td>
									<td align="right" valign="middle" width="10%"><input
										type="checkbox" class="paymentMonth" name="paymentMonth"
										id="APR" value="APR" /></td>
									<td align="left" valign="middle" width="10%"><label><font>April</font></label></td>
									<td align="right" valign="middle" width="10%"><input
										type="checkbox" class="paymentMonth" name="paymentMonth"
										id="MAY" value="MAY" /></td>
									<td align="left" valign="middle" width="10%"><label><font>May</font></label></td>
								</tr>
							</table>

						</div>
					</div>
				</div>




				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingThree">
						<h4 class="panel-title">
							<a class="collapsed" role="button" data-toggle="collapse"
								data-parent="#accordion" href="#collapseThree"
								style="color: #767676" aria-expanded="false"
								aria-controls="collapseThree"> <i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Payment Details
							</a>
						</h4>
					</div>
					<div id="collapseThree" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingThree">
						<div class="panel-body">


							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<%-- <div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">PaymentID</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" onchange="readyOnly()"
											name="searchTerm" id="SearchStudent"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentSibilingName"></bean:write></logic:present>' />
										<input type="hidden" name="studentSibilingIdInt" id="studentSibilingIdIntId" 
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentSibilingIdInt"></bean:write></logic:present>' />
									</div>
								</div> --%>


								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Paid
										Amount</label>
									<div class="col-xs-7">
										<input name="paidamount" id="paidamountid" readonly="readonly"
											onkeyup="calculateamount()" maxlength="30"
											class="form-control" value="" />
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Payment
										Date </label>
									<div class="col-xs-7">
										<input type="text" placeholder="Click Here"
											name="payment_date_id" id="payment_date_id"
											class="form-control" />
									</div>
								</div>
								<br />
								
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Cheque Number</label>
									<div class="col-xs-7">
										<input name=cheque_no id="cheque_no"
											 maxlength="50" class="form-control"
											value='' />
									</div>
								</div>
								<br />
								
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Due
										Amount </label>
									<div class="col-xs-7">
										<input type="text" name="dueamount" id="dueamountid"
											readonly="readonly" maxlength="10" class="form-control"
											value='' />
									</div>
								</div>
								<br />

							</div>


							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Balance
										Amount</label>
									<div class="col-xs-7">
										<input name=balanceamount id="balanceamountid"
											readonly="readonly" maxlength="30" class="form-control"
											value='' />
									</div>
								</div>

								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Payment
										Mode </label>
									<div class="col-xs-7">
										<select name="payment_mode" id="payment_mode"
											class="form-control">
											<option value=""></option>
											<option value="cash">Cash</option>
											<option value="cheque">Cheque</option>

										</select>
									</div>
								</div>
								<br />


								<%-- <div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Concession Amount
										 </label>
									<div class="col-xs-7">
										<input name="fatherQualification" id="fatherQualificationId" onkeypress="HideError()"
											maxlength="30" class="form-control" readonly="readonly"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="fatherQualification"></bean:write></logic:present>'/>
									</div>
									<input type="hidden" id="hprymarycntperson" name="primaryPerson"/>
								</div>
								
								<br /> --%>
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Paying
										Amount</label>
									<div class="col-xs-7">
										<input name="payingamount" id="payingamount"
											readonly="readonly" maxlength="30" class="form-control"
											value='' />
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Total
										Amount</label>
									<div class="col-xs-7">
										<input type="text" name="totalfeeamt" id="totalfeeamtid"
											readonly="readonly" maxlength="45"
											onblur="fathervalidateEmail()" class="form-control"
											value='<logic:present name="totalfeeamt" scope="request" ><bean:write name="totalfeeamt" ></bean:write></logic:present>' />
									</div>
								</div>




							</div>
						</div>
					</div>
				</div>

				<!-- <div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingfour">
						<h4 class="panel-title">
							<a class="collapsed" role="button" data-toggle="collapse"
								data-parent="#accordion" href="#collapsefour"
								style="color: #767676" aria-expanded="false"
								aria-controls="collapsefour"> <i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Document Details
							</a>
						</h4>
					</div>
					<div id="collapsefour" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingfour">
						<div class="panel-body">
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Birth
										Certificate</label>
									<div class="col-xs-7">
									
										<input type="file" id="uploadBirth" name="birthFile" class="custom-file-uploadfile" style="height: 20%;" />
										<input type="button" id="document1btn" name="profile" class="downloadDoc" value="Download" />
										<span style="font-size:20px;color:red;cursor:pointer;" id="deleteProfile">  x</span>
								
									</div>
								</div>
								<br />
							</div>
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Transfer Certificate</label>
									<div class="col-xs-7">
									
										<input type="file" id="uploadTransfer" name="transferFile" class="custom-file-uploadfile" style="height: 20%;" />
										<input type="button" id="document2btn" name="idproof" class="downloadDoc" value="Download" />
										<span style="font-size:20px;color:red;cursor:pointer;" id="deleteIDProof">x</span>
								
									</div>
								</div>
							</div>
						</div>
					</div>
					
				</div> -->
			</div>
			<!-- <button type="submit" class="btn btn-info col-md-offset-5">Savechanges</button>
				<button type="reset" class="btn btn-info">Clear</button> -->
		</form>
	</div>
</html>
