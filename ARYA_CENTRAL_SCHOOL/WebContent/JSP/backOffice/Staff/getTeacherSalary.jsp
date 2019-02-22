<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html lang="en">

<head>

<script type="text/javascript" src="JQUERY/jquery-1.9.1.js"></script>
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="JS/backOffice/Staff/StaffEmployement.js"></script>


<style>
.save:hover {
	cursor: pointer;
}
</style>
<style>
.buttons {
	vertical-align: 0px;
}
table th,table td {
	border: 1px solid #ddd !important;
	color: #767676;
	font-weight: 300;
	font-family: Open Sans Light;
}

table th:first-child,table td:first-child {
	width: 400px;
	line-height: 30px;
}

td,th {
	padding: 0;
	border: 2px solid #ccc;
}

.table td:nth-child(1) {
	max-width: 150px;
}

#earningsalary td:nth-child(1) {
	width: 413px;
}
  #earningsalary input[type="text"]{
   height: 24px !important;
    border-radius: 4px !important;
    padding: 0 5px !important;
    width: 100%;
    margin: 0 auto;
    line-height: 23px !important;
  }
 #collapseTwo label {
    line-height: 23px !important;
    margin-bottom: 0px;
}

#collapseTwo #allstudent  tr td, .allstudent  tr td
{
padding: 0;
margin: 0;
}
#collapseTwo .form-group{
margin-bottom: 0px ! important;
}
#collapseTwo input[type="text"], select {
    height: 23px !important;
    border-radius: 4px !important;
    padding: 0 5px !important;
    background: transparent;
}
.tabledesign{
	text-align: right;
	margin: 0 auto;
	border: 0;
}

.tabledesign1{
	text-align: right;
	margin: 0 auto;
	border: 0;
	padding-right: 10px;
}

.peramount{
  	text-align: right;
	margin: 0 auto;
	border: 0;
	background: transparent;
}

.centretext{
	text-align: center;
}
form .panel.panel-primary {
    padding-bottom: 0px;
}
</style>
</head>

<body>



	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Staff
				Employment</span>
		</p>
		<logic:present name="successMessage" scope="request">

			<div class="successmessagediv">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span> <bean:write
								name="successMessage" scope="request" />
					</span></a>
				</div>
			</div>

		</logic:present>

		<logic:present name="errorMessage" scope="request">

			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
								name="errorMessage" scope="request" />
					</span></a>
				</div>

			</div>

		</logic:present>

		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>



		<form action="staffemployement.html?method=saveStaffSalaryDetails"
			id="teacherform" method="post">

			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">


				<!-- Staff Details  -->

				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676"> 
							<h4 class="panel-title"><i class="glyphicon glyphicon-menu-hamburger"></i>&nbsp;&nbsp;Staff Details
							</h4></a>
						

						<div class="navbar-right" style="margin-top: -3px;">


							<span class="buttons" id="submit" class="save">Save
							</span>&nbsp; 
								<span id="back" class="buttons">Back</a></span>
							
						</div>

					</div>

					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body" style="padding: 10px;">
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">


								<input type="hidden" name="hemloyeeid" id="hemloyeeid"
									value="<logic:present name="teachercode"><bean:write name="teachercode" /></logic:present>" />
								<input type="hidden" name="hempid" id="hempid"
									value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="empid"/></logic:present>" />
								<input type="hidden" id="hpaymenttype"
									value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="paymenttype"/></logic:present>" />
								<input type="hidden" id="hbankname"
									value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="bankname"/></logic:present>" />
								<input type="hidden" id="hmonths"
									value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="month"/></logic:present>" />

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="line-height: 35px;">Employee
										ID </label>
									<div class="col-xs-7">
										<input type="text" name="staffId" id="staffId" maxlength="25"
											class="form-control" readonly="readonly"
											value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="empid"/></logic:present>" />
									</div>
								</div>


								



								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="line-height: 35px;">Bank
										Account No </label>
									<div class="col-xs-7">
										<input type="text" name="bankaccnumber" id="bankaccnumber"
											maxlength="25" class="form-control"
											value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="bankaccnumber"/></logic:present>" />
									</div>
								</div>
								

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="  line-height: 35px;">Branch
										Name </label>
									<div class="col-xs-7">
										<input type="text" name="branchname" id="branchname"
											maxlength="25" class="form-control"
											value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="IFSCCode"/></logic:present>" />
									</div>
								</div>
								

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="  line-height: 35px;">Employee
										PF No</label>
									<div class="col-xs-7">
										<input type="text" name="employeepfno" id="employeepfno"
											maxlength="25" class="form-control"
											value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="employeepfno"/></logic:present>" />
									</div>
								</div>


							</div>


							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="  line-height: 35px;">Employee
										Name </label>
									<div class="col-xs-7">
										<input type="text" name="empname" id="empname"
											class="form-control" readonly="readonly"
											value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="empname"/></logic:present>" />
									</div>
								</div>

								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="  line-height: 35px;">Bank
										Name </label>
									<div class="col-xs-7">
										<select class="form-control" name="bankname" id="bankid">
											<option value="">----Select----</option>
											<option value="allahabadbank">Allahabad Bank</option>
											<option value="andhrabank">Andhra Bank</option>
											<option value="axisbank">Axis Bank</option>
											<option value="canarabank">Canara Bank</option>
											<option value="federalbank">Federal Bank</option>
											<option value="hdfcbank">HDFC Bank</option>
											<option value="icicibank">ICICI Bank</option>
											<option value="idbibank">IDBI Bank</option>
											<option value="indianbank">Indian Bank</option>
											<option value="ingvysyabank">ING Vysya Bank</option>
											<option value="karnatakabank">Karnataka Bank</option>
											<option value="karurvysyabank">Karur Vysya Bank</option>
											<option value="kotakbank">Kotak Bank</option>
											<option value="statebankofhyderabad">State Bank Of
												Hyderabad</option>
											<option value="statebankofmysore">State Bank Of
												India</option>
											<option value="statebankofmysore">State Bank Of
												Mysore</option>
											<option value="syndicatebank">Syndicate Bank</option>
											<option value="vijayanank">Vijaya Bank</option>
										</select>

									</div>
								</div>
								

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="  line-height: 35px;">IFSC
										Code </label>
									<div class="col-xs-7">
										<input type="text" name="IFSCCode" id="IFSCCode"
											maxlength="25" class="form-control"
											value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="IFSCCode"/></logic:present>" />
									</div>
								</div>
								

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="  line-height: 35px;">Payment
										Type </label>
									<div class="col-xs-7">
										<select class="form-control" name="paymenttype"
											id="paymenttype">
											<option value="">----Select----</option>
											<option value="bank">Bank</option>
											<option value="check">Cheque</option>
											<option value="cash">Cash</option>

										</select>
									</div>
								</div>




							</div>
						</div>
					</div>
				</div>



				<!-- Earnings -->



				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingTwo">
						
							<a class="collapsed" role="button" data-toggle="collapse"
								data-parent="#accordion" href="#collapseTwo"
								style="color: #767676" aria-expanded="false"
								aria-controls="collapseTwo"><h4 class="panel-title"> <i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Earnings
							</h4></a>
						
					</div>
					<div id="collapseTwo" class="panel-collapse collapse"	role="tabpanel" aria-labelledby="headingTwo">
						<div class="panel-body" style="padding: 10px;">
							<div class="col-md-12" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<table id="allstudent" class="table" style="width:1042px;">
									<tr>
										<th	style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;width: 400px;">
											Particulars</th>
										<th style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">
											Per Month Amount</th>
										<th style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">
											Months</th>
										<th	style="font-size: 14px; text-align: center; background: #f9f9f9 !important; font-family: Roboto Medium;">
											Per Year Amount</th>
									</tr>
									<tr>
										<td>
											<div class="form-group clearfix">
												<label for="inputEmail" class="control-label col-xs-12"
													style="text-align: left; line-height: 35px;">Basic </label>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" name="basic" id="basic" maxlength="25" class="form-control tabledesign"	
													value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="basic"/></logic:present>" />
											</div>
										</td>
										<td>
											<div class="centretext">
												<span>12</span>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" readonly="readonly" name="basictotal" id="basictotal" class="form-control peramount" />
											</div>
										</td>
									</tr>
									
									<tr>
										<td>
											<div class="form-group clearfix">
												<label for="inputEmail" class="control-label col-xs-12"
													style="text-align: left; line-height: 35px;">DA </label>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" name="da" id="da" maxlength="25" class="form-control tabledesign" 
													value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="da"/></logic:present>" />
											</div>
										</td>
										<td>
											<div class="centretext">
												<span>12</span>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" readonly="readonly" name="datotal" id="datotal"   class="form-control peramount" />
											</div>
										</td>
									</tr>
									
									<tr>
										<td>
											<div class="form-group clearfix">
												<label for="inputEmail" class="control-label col-xs-12"
													style="text-align: left; line-height: 35px;">HRA </label>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" name="hra" id="hra" class="form-control tabledesign" 
													value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="hra"/></logic:present>" />
											</div>
										</td>
										<td>
											<div class="centretext">
												<span >12</span>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" readonly="readonly" name="hratotal" id="hratotal"   class="form-control peramount" />
											</div>
										</td>
									</tr>
									
									<tr>
										<td>
											<div class="form-group clearfix">
												<label for="inputEmail" class="control-label col-xs-12"
													style="text-align: left; line-height: 35px;">Transport Allowance/Conveyance </label>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" name="allowance" id="allowance" maxlength="25" class="form-control tabledesign" 
													value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="allowance"/></logic:present>" />
											</div>
										</td>
										<td>
											<div class="centretext">
												<span  >12</span>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" readonly="readonly" name="allowancetotal" id="allowancetotal"   class="form-control peramount" />
											</div>
										</td>
									</tr>
									
									<tr>
										<td>
											<div class="form-group clearfix">
												<label for="inputEmail" class="control-label col-xs-12"
													style="text-align: left; line-height: 35px;">Child Education </label>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" name="childedu" id="childedu" maxlength="25" class="form-control tabledesign"  
													value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="childedu"/></logic:present>" />
											</div>
										</td>
										<td>
											<div class="centretext">
												<span  >12</span>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" readonly="readonly" name="childedutotal" id="childedutotal"   class="form-control peramount" />
											</div>
										</td>
									</tr>
									
									<tr>
										<td>
											<div class="form-group clearfix">
												<label for="inputEmail" class="control-label col-xs-12"
													style="text-align: left; line-height: 35px;">Special Allowance </label>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" name="special" id="special" maxlength="25" class="form-control tabledesign"  
													value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="special"/></logic:present>" />
											</div>
										</td>
										<td>
											<div class="centretext">
												<span  >12</span>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" readonly="readonly" name="specialtotal" id="specialtotal"   class="form-control peramount" />
											</div>
										</td>
									</tr>
									
									<tr>
										<td>
											<div class="form-group clearfix">
												<label for="inputEmail" class="control-label col-xs-12"
													style="text-align: left; line-height: 35px;">Arrears </label>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" name="arrears" id="arrears" maxlength="25" class="form-control tabledesign" 	
													value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="arrears"/></logic:present>" />
											</div>
										</td>
										<td>
											<div class="centretext">
												<span  >12</span>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" readonly="readonly" name="arrearstotal" id="arrearstotal"   class="form-control peramount" />
											</div>
										</td>
									</tr>
									
									<tr>
										<td>
											<div class="form-group clearfix">
												<label for="inputEmail" class="control-label col-xs-12"
													style="text-align: left; line-height: 35px;">Performance Incentive </label>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" name="performace" id="performace" maxlength="25" class="form-control tabledesign"  
													value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="perfincentive"/></logic:present>" />
											</div>
										</td>
										<td>
											<div class="centretext">
												<span  >12</span>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" readonly="readonly" name="performacetotal" id="performacetotal"   class="form-control peramount" />
											</div>
										</td>
									</tr>
									
									<tr>
										<td>
											<div class="form-group clearfix">
												<label for="inputEmail" class="control-label col-xs-12"
													style="text-align: left; line-height: 35px;">Medical Reimbursement </label>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" name="medical" id="medical" maxlength="25" class="form-control tabledesign" 	
													value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="medical"/></logic:present>" />
											</div>
										</td>
										<td>
											<div class="centretext">
												<span  >12</span>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" readonly="readonly" name="medicaltotal" id="medicaltotal"   class="form-control peramount" />
											</div>
										</td>
									</tr>
									
									<tr>
										<td>
											<div class="form-group clearfix">
												<label for="inputEmail" class="control-label col-xs-12"
													style="text-align: left; line-height: 35px;">Leave Encashment </label>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" name="leave" id="leave" maxlength="25" class="form-control tabledesign" 	
													value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="leave"/></logic:present>" />
											</div>
										</td>
										<td>
											<div class="centretext">
												<span  >12</span>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" readonly="readonly" name="leavetotal" id="leavetotal"   class="form-control peramount" />
											</div>
										</td>
									</tr>
									
									<tr>
										<td>
											<div class="form-group clearfix">
												<label for="inputEmail" class="control-label col-xs-12"
													style="text-align: left; line-height: 35px;">Food Coupons </label>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" name="food" id="food" maxlength="25" class="form-control tabledesign" 	
													value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="foodallowance"/></logic:present>" />
											</div>
										</td>
										<td>
											<div class="centretext">
												<span  >12</span>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" readonly="readonly" name="foodtotal" id="foodtotal"   class="form-control peramount" />
											</div>
										</td>
									</tr>
									
									<tr>
										<td>
											<div class="form-group clearfix">
												<label for="inputEmail" class="control-label col-xs-12"
													style="text-align: left; line-height: 35px;">Reimbursement </label>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" name="reimburse" id="reimburse" maxlength="25" class="form-control tabledesign" 	
													value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="reimburse"/></logic:present>" />
											</div>
										</td>
										<td>
											<div class="centretext">
												<span  >12</span>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" readonly="readonly" name="reimbursetotal" id="reimbursetotal"   class="form-control peramount" />
											</div>
										</td>
									</tr>
									
									<tr>
										<td>
											<div class="form-group clearfix">
												<label for="inputEmail" class="control-label col-xs-12"
													style="text-align: left; line-height: 35px;">Internet Expenses </label>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" name="internet" id="internet" maxlength="25" class="form-control tabledesign"  
													value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="internet"/></logic:present>" />
											</div>
										</td>
										<td>
											<div class="centretext">
												<span  >12</span>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" readonly="readonly" name="internettotal" id="internettotal"   class="form-control peramount" />
											</div>
										</td>
									</tr>
									
									<tr>
										<td>
											<div class="form-group clearfix">
												<label for="inputEmail" class="control-label col-xs-12"
													style="text-align: left; line-height: 35px;">Driver Salary </label>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" name="driver" id="driver" maxlength="25" class="form-control tabledesign" 
													value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="driver"/></logic:present>" />
											</div>
										</td>
										<td>
											<div class="centretext">
												<span  >12</span>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" readonly="readonly" name="drivertotal" id="drivertotal"   class="form-control peramount" />
											</div>
										</td>
									</tr>
									
									<tr>
										<td>
											<div class="form-group clearfix">
												<label for="inputEmail" class="control-label col-xs-12"
													style="text-align: left; line-height: 35px;">Other </label>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" name="other" id="other" maxlength="25" class="form-control tabledesign" 
													value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="others"/></logic:present>" />
											</div>
										</td>
										<td>
											<div class="centretext">
												<!-- <input type="text" readonly="readonly" class="form-control" style="width: 50px;border: 0px;text-align: center;background-color: white;" value="12" /> -->
												<span  >12</span>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" readonly="readonly" name="othertotal" id="othertotal"   class="form-control peramount" />
											</div>
										</td>
									</tr>
									
									<tr>
										<th style="padding: 0;">
											<div class="form-group clearfix">
												<label for="inputEmail" class="control-label col-xs-12"	style="text-align: left; line-height: 35px;">Total Salary</label>
											</div>
										</th>
										<td>
											<div class="">
												<input type="text" readonly="readonly" name="totalsalary" id="totalsalary" maxlength="25" class="form-control tabledesign" 
													value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="totalsalary"/></logic:present>" />
											</div>
										</td>
										<td>
											
										</td>
										<td>
											<div class="">
												<input type="text" readonly="readonly" name="yearlytotalamount" id="yearlytotalamount" class="form-control tabledesign"/>
											</div>
										</td>
									</tr>
									
								</table>
								<br/>
								
								<table id="allstudent" class="table" style="width:1042px;">
								
									<tr>
										<th style="padding: 0;">
											<div class="form-group clearfix">
												<label for="inputEmail" class="control-label col-xs-12"	style="text-align: left; line-height: 35px;">HRA(%)</label>
											</div>
										</th>
										<td>
											<div class="">
												<select name="hrapercentage" id="hrapercentage" class="form-control tabledesign" style="text-align-last: center;">
													<option value="0">-------Select-------</option>
													<option value="20">20</option>
													<option value="25">25</option>
													<option value="30">30</option>
													<option value="35">35</option>
													<option value="40">40</option>
													<option value="45">45</option>
													<option value="50">50</option>
												</select>
											</div>
											<input type="hidden" id="hiddenhrapercentage" value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="hrapercentage"/></logic:present>"/>
											<input type="hidden" id="hiddenmonthlyhraamount" value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="monthlyhraamount"/></logic:present>"/>
										</td>
										<td>
											<div class="">
												<input type="text" name="monthlyhraamount" id="monthlyhraamount" readonly="readonly" class="form-control" style="width: 85px;border: 0px;text-align: right;  margin: 0 auto;" 
													value="0.00" />
											</div>
										</td>
										<td>
											<div class="centretext">
												<span>12</span>
											</div>
										</td>
										<td>
											<div class="">
												<input type="text" readonly="readonly" name="yearlyhraamount" id="yearlyhraamount" value="0.00" class="form-control tabledesign"/>
											</div>
										</td>
									</tr>
									<tr>
										<th style="width: 398px;padding: 0;">
											<div class="form-group clearfix">
												<label for="inputEmail" class="control-label col-xs-12"
													style="text-align: left; line-height: 35px;">PF Employer </label>
											</div>
										</th>
										<td style="width: 13%;">
											<div class="" >
												<input type="text" name="pfemployer" id="pfemployerbasic" maxlength="25" class="form-control tabledesign" 
													value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="employerpf"/></logic:present>" />
											</div>
										</td>
										<td style="width:134px;">
											<div class="" >
												<input type="text" name="pfamount" id="pfamount" readonly="readonly" class="form-control" style="width: 85px;border: 0px;text-align: right;  margin: 0 auto;" 
													value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="pfemployer"/></logic:present>" />
											</div>
										</td>
										<td style="width:106px;">
											<div class="centretext">
												<span>12</span>
												<!-- <input type="text" readonly="readonly" class="form-control" style="width: 50px;border: 0px;text-align: center; background-color:white; margin: 0 auto;" value="12" /> -->
											</div>
										</td>
										<td>
											<div class="" >
												<input type="text" readonly="readonly" name="yearlytotalpfamount" id="yearlytotalpfamount" class="form-control tabledesign" value="0.00" />
											</div>
										</td>
									</tr>
									
									<tr>
										<th style="width: 398px;padding: 0;">
											<div class="form-group clearfix">
												<label for="inputEmail" class="control-label col-xs-12"
													style="text-align: left; line-height: 35px;">ESI Employer </label>
											</div>
										</th>
										<td style="width: 13%;">
											<div class="" >
												<input type="text" name="esiemployer" id="esiemployertotal" maxlength="25" class="form-control tabledesign" 
													value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="employerESI"/></logic:present>" />
											</div>
										</td>
										<td>
											<div class="" >
												<input type="text" name="esiamount" id="esiamount" readonly="readonly" class="form-control" style="width: 85px;border: 0px;text-align: right; margin: 0 auto;" 
													value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="esiemployer"/></logic:present>" />
											</div>
										</td>
										<td>
											<div class="centretext">
												<span>12</span>
												<!-- <input type="text" readonly="readonly" class="form-control" style="width: 50px;border: 0px;text-align: center; background-color:white; margin: 0 auto;" value="12" /> -->
											</div>
										</td>
										<td>
											<div class="" >
												<input type="text" readonly="readonly" name="yearlytotalesiamount" id="yearlytotalesiamount" class="form-control tabledesign"  value="0.00" />
											</div>
										</td>
									</tr>
								</table>
							
							</div>
						</div>
					</div>
				</div>


				<!-- Deductions -->

				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingThree">
						
							<a class="collapsed" role="button" data-toggle="collapse"
								data-parent="#accordion" href="#collapseThree"
								style="color: #767676" aria-expanded="false"
								aria-controls="collapseThree"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Deductions
							</h4></a>
						
					</div>
					<div id="collapseThree" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingThree">
						<div class="panel-body" style="padding: 10px;">
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">




								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="  line-height: 35px;">Employer
										PF </label>
									<div class="col-xs-7">
										<input type="text" name="demployerpf" id="demployerpf"
											maxlength="25" class="form-control" readonly="readonly"
											value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="pfemployer"/></logic:present>" />
									</div>
								</div>
								


								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="  line-height: 35px;">Employer
										ESI </label>
									<div class="col-xs-7">
										<input type="text" name="dedmployerESI" id="dedmployerESI"
											maxlength="25" class="form-control" readonly="readonly"
											value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="esiemployer"/></logic:present>" />
									</div>
								</div>
								


								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="  line-height: 35px;">PT </label>
									<div class="col-xs-7">
										<input type="text" name="pt" id="pt" maxlength="25"
											class="form-control" readonly="readonly"
											value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="pt"/></logic:present>" />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="  line-height: 35px;">Other
										Deductions</label>
									<div class="col-xs-7">
										<input type="text" name="otherdeduction" id="otherdeduction"
											maxlength="25" class="form-control"
											value="" />
									</div>
								</div>

								<%-- <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="  line-height: 35px;">Leave
										Deductions</label>
									<div class="col-xs-7" id="radiostyle" style="margin-top: 8px">


										<logic:present name="Stafflist" scope="request">

											<logic:match name="Stafflist" property="leavedeductions"
												value="Y">

												<input type="radio" name="leavedeductions"
													id="leavedeductions" value="Y" checked>Yes
										<input type="radio" name="leavedeductions"
													id="leavedeductions" value="N">No
					
									</logic:match>

											<logic:match name="Stafflist" property="leavedeductions"
												value="N">
												<input type="radio" name="leavedeductions"
													id="leavedeductions" value="Y">Yes
										<input type="radio" name="leavedeductions"
													id="leavedeductions" value="N" checked>No
									</logic:match>

										</logic:present>

										<logic:notPresent name="Stafflist" scope="request">

											<input type="radio" name="leavedeductions"
												id="leavedeductions" value="Y" checked>Yes
                   			<input type="radio" name="leavedeductions"
												id="leavedeductions" value="N"> No
					
						 </logic:notPresent>

									</div>
								</div> --%>



							</div>
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">


								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="  line-height: 35px;">Employee
										PF </label>
									<div class="col-xs-7">
										<input type="text" name="demployeepf" id="demployeepf"
											maxlength="25" class="form-control" readonly="readonly"
											value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="employeepf"/></logic:present>" />
									</div>
								</div>
								

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="  line-height: 35px;">Employee
										ESI </label>
									<div class="col-xs-7">
										<input type="text" name="demployeeESI" id="demployeeESI"
											maxlength="25" class="form-control" readonly="readonly"
											value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="employeeESI"/></logic:present>" />
									</div>
								</div>
								

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="  line-height: 35px;">Staff
										Advance </label>
									<div class="col-xs-7">
										<input type="text" name="staffadvance" id="staffadvance"
											maxlength="25" class="form-control"
											value="" />
									</div>
								</div>

								

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="  line-height: 35px;">Income
										Tax </label>
									<div class="col-xs-7">
										<input type="text" name="incometax" id="incometax"
											maxlength="25" class="form-control"
											value="<logic:present name="Stafflist"><bean:write name="Stafflist" property="incometax"/></logic:present>" />
									</div>
								</div>

								
								<%-- <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="  line-height: 35px;">Late
										Deductions<font color="red"></font>
									</label>
									<div class="col-xs-7" id="radiostyle" style="margin-top: 8px">


										<logic:present name="Stafflist" scope="request">
											<logic:match name="Stafflist" property="latedeductions"
												value="Y">

												<input type="radio" name="latedeductions"
													id="latedeductions" value="Y" checked>Yes 
					 							<input type="radio" name="latedeductions"
													id="latedeductions" value="N"> No
											</logic:match>

											<logic:match name="Stafflist" property="latedeductions"
												value="N">

												<input type="radio" name="latedeductions"
													id="latedeductions" value="Y">Yes 
					 							<input type="radio" name="latedeductions"
													id="latedeductions" value="N" checked> No
					 						</logic:match>
										</logic:present>
											<logic:notPresent name="Stafflist" scope="request">
											<input type="radio" name="latedeductions" id="latedeductions"
												value="Y" checked>Yes
                    							<input type="radio" name="latedeductions"
												id="latedeductions" value="N">No
                    					</logic:notPresent>

									</div>
								</div> --%>




							</div>
						</div>
					</div>
				</div>
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingFive">
						
							<a class="collapsed" role="button" data-toggle="collapse"
								data-parent="#accordion" href="#collapseFive"
								style="color: #767676" aria-expanded="false"
								aria-controls="collapseThree"><h4 class="panel-title"> <i
								class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;TDS
							</h4></a>
						
					</div>


					<div id="collapseFive" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingFive">
						<html:hidden property="count" styleId="count" value="0" />
						<div class="panel-body" style="padding: 10px;">

							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<input type="hidden" name="depId" id="departmentid"
									value="<logic:present name="deptdetails" scope="request"><bean:write name="deptdetails" property="depId" ></bean:write></logic:present>" />

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="  line-height: 35px;">CTC(per
										year)<font color="red"></font>
									</label>
									<div class="col-xs-7">
										<input type="text" name="tdsctc" id="tdsctc"
											class="form-control" readonly="readonly"
											value="<logic:present name="Stafflist" scope="request"><bean:write name="Stafflist" property="taxableTotalAmount" /></logic:present>" />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="line-height: 35px;">Total Exemption<font color="red"></font>
									</label>
									<div class="col-xs-7">
										<input type="text" name="tdsexmption"
											id="tdsexmption" class="form-control"
											readonly="readonly"
											value="<logic:present name="Stafflist" scope="request"><bean:write name="Stafflist" property="totalExemptions" /></logic:present>" />
									</div>
								</div>
								

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="  line-height: 35px;">PT<font
										color="red"></font></label>
									<div class="col-xs-7">
										<input type="text" name="tdspt" id="tdspt"
											class="form-control" readonly="readonly"
											value="<logic:present name="Stafflist" scope="request"><bean:write name="Stafflist" property="tdspt" /></logic:present>" />
									</div>
								</div>
								

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="  line-height: 35px;">Taxble
										Income<font color="red"></font>
									</label>
									<div class="col-xs-7">
										<input type="text" name="taxableincome" id="taxableincome"
											class="form-control clearfix" readonly="readonly"
											value="<logic:present name="Stafflist" scope="request"><bean:write name="Stafflist" property="taxableincome" /></logic:present>"
											onchange="salarycalculations()" />
									</div>
								</div>

							</div>
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="  line-height: 35px;">Standard
										Deductions<font color="red"></font>
									</label>
									<div class="col-xs-7">
										<input type="text" name="standarddedu" id="standarddedu"
											class="form-control" readonly="readonly"
											value="<logic:present name="Stafflist" scope="request"><bean:write name="Stafflist" property="standarddedu" /></logic:present>" />
									</div>
								</div>
								

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="  line-height: 35px;">Under Chapter VI-A<font color="red"></font>
									</label>
									<div class="col-xs-7">
										<input type="text" name="tdsundervia" id="tdsundervia"
											class="form-control" readonly="readonly"
											value="<logic:present name="Stafflist" scope="request"><bean:write name="Stafflist" property="totaltdsundervia" /></logic:present>" />
									</div>
									<input type="hidden" id="htdsundervia" value="<logic:present name="Stafflist" scope="request"><bean:write name="Stafflist" property="totaltdsundervia" /></logic:present>" />
								</div>
								

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="  line-height: 35px;">Income From Others<font color="red"></font>
									</label>
									<div class="col-xs-7">
										<input type="text" name="taxableincomefromothers" id="taxableincomefromothers"
											class="form-control clearfix" readonly="readonly"
											value="<logic:present name="Stafflist" scope="request"><bean:write name="Stafflist" property="totalTDSIncomeFromOther" /></logic:present>"
											onchange="salarycalculations()" />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="  line-height: 35px;">Tax
										Payble<font color="red"></font>
									</label>
									<div class="col-xs-7">
										<input type="text" name="taxpayble" id="taxpayble"
											class="form-control" readonly="readonly"
											value="<logic:present name="Stafflist" scope="request"><bean:write name="Stafflist" property="taxpayble" /></logic:present>" />
									</div>
								</div>
								

							</div>
						</div>
					</div>
				</div>

			</div>

		</form>
	</div>
</html>
